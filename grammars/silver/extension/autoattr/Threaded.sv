grammar silver:extension:autoattr;

concrete production threadedAttributeDcl
top::AGDcl ::= 'threaded' 'attribute' inh::Name ',' syn::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.unparse = s"threaded attribute ${inh.unparse}, ${syn.unparse} ${tl.unparse} :: ${te.unparse};";
  top.moduleNames := [];

  production attribute inhFName :: String;
  inhFName = top.grammarName ++ ":" ++ inh.name;
  production attribute synFName :: String;
  synFName = top.grammarName ++ ":" ++ syn.name;
  
  tl.initialEnv = top.env;
  tl.env = tl.envBindingTyVars;
  te.env = tl.envBindingTyVars;
  
  top.errors <-
    if length(getAttrDclAll(inhFName, top.env)) > 1
    then [err(inh.location, "Attribute '" ++ inhFName ++ "' is already bound.")]
    else [];
  top.errors <-
    if length(getAttrDclAll(synFName, top.env)) > 1
    then [err(syn.location, "Attribute '" ++ synFName ++ "' is already bound.")]
    else [];
  
  forwards to
    defsAGDcl(
      [attrDef(defaultEnvItem(threadedInhDcl(top.grammarName, inh.location, inhFName, synFName, tl.freeVariables, te.typerep))),
       attrDef(defaultEnvItem(threadedSynDcl(top.grammarName, syn.location, inhFName, synFName, tl.freeVariables, te.typerep)))],
      location=top.location);
}

abstract production propagateThreadedInh
top::ProductionStmt ::= inh::Decorated QName syn::String
{
  top.unparse = s"propagate ${inh.unparse};";
  
  local lhsName::String = top.frame.signature.outputElement.elementName;
  forwards to
    threadInhDcl(
      inh.name, syn,
      map(
        name(_, top.location),
        lhsName ::
        map(
          (.elementName),
          filter(
            \ ie::NamedSignatureElement ->
              !null(getOccursDcl(inh.lookupAttribute.fullName, ie.typerep.typeName, top.env)) &&
              !null(getOccursDcl(syn, ie.typerep.typeName, top.env)),
            top.frame.signature.inputElements)) ++
        [if !null(getValueDcl("forward", top.env)) then "forward" else lhsName]),
      location=top.location);
}

abstract production propagateThreadedSyn
top::ProductionStmt ::= inh::String syn::Decorated QName
{
  top.unparse = s"propagate ${syn.unparse};";
  
  local lhsName::String = top.frame.signature.outputElement.elementName;
  forwards to
    threadSynDcl(
      inh, syn.name,
      map(
        name(_, top.location),
        lhsName ::
        map(
          (.elementName),
          filter(
            \ ie::NamedSignatureElement ->
              !null(getOccursDcl(inh, ie.typerep.typeName, top.env)) &&
              !null(getOccursDcl(syn.lookupAttribute.fullName, ie.typerep.typeName, top.env)),
            top.frame.signature.inputElements)) ++
        [if !null(getValueDcl("forward", top.env)) then "forward" else lhsName]),
      location=top.location);
}

concrete production threadDcl_c
top::ProductionStmt ::= 'thread' inh::QName ',' syn::QName 'on' children::Names ';'
{
  top.unparse = s"thread ${inh.unparse}, ${syn.unparse} on ${children.unparse};";
  forwards to
    productionStmtAppend(
      threadInhDcl(inh.name, syn.name, children.ids, location=top.location),
      threadSynDcl(inh.name, syn.name, children.ids, location=top.location),
      location=top.location);
}

abstract production threadInhDcl
top::ProductionStmt ::= inh::String syn::String children::[Name]
{
  top.unparse = s"thread ${inh}, ${syn} on ${implode(", ", map((.unparse), children))};";
  
  local lhsName::String = top.frame.signature.outputElement.elementName;
  forwards to
    foldr(
      productionStmtAppend(_, _, location=top.location),
      errorProductionStmt([], location=top.location), -- No emptyProductionStmt?
      zipWith(
        \ c1::Name c2::Name ->
          if c1.name != lhsName
          then
            attributeDef(
              concreteDefLHS(qNameId(c1, location=top.location), location=top.location),
              '.',
              qNameAttrOccur(qName(top.location, inh), location=top.location),
              '=',
              access(
                baseExpr(qNameId(c2, location=top.location), location=top.location), '.',
                qNameAttrOccur(qName(top.location, if c2.name == lhsName then inh else syn), location=top.location),
                location=top.location),
              ';',
              location=top.location)
          else errorProductionStmt([], location=top.location),
        tail(children), children));
}

abstract production threadSynDcl
top::ProductionStmt ::= inh::String syn::String children::[Name]
{
  top.unparse = s"thread ${inh}, ${syn} on ${implode(", ", map((.unparse), children))};";
  
  local lhsName::String = top.frame.signature.outputElement.elementName;
  forwards to
    foldr(
      productionStmtAppend(_, _, location=top.location),
      errorProductionStmt([], location=top.location), -- No emptyProductionStmt?
      zipWith(
        \ c1::Name c2::Name ->
          if c1.name == lhsName
          then
            attributeDef(
              concreteDefLHS(qNameId(c1, location=top.location), location=top.location),
              '.',
              qNameAttrOccur(qName(top.location, syn), location=top.location),
              '=',
              access(
                baseExpr(qNameId(c2, location=top.location), location=top.location), '.',
                qNameAttrOccur(qName(top.location, if c2.name == lhsName then inh else syn), location=top.location),
                location=top.location),
              ';',
              location=top.location)
          else errorProductionStmt([], location=top.location),
        tail(children), children));
}

synthesized attribute ids :: [Name];

nonterminal Names with unparse, ids;
concrete production idSingle
top::Names ::= id::Name
{
  top.unparse = id.name;
  top.ids = [id];
}

concrete production idCons
top::Names ::= id1::Name ',' id2::Names
{
  top.unparse = id1.name ++ ", " ++ id2.unparse ;
  top.ids = [id1] ++ id2.ids;
}
