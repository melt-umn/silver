grammar silver:compiler:extension:autoattr;

import silver:compiler:definition:concrete_syntax only Left_kwd, Right_kwd;

concrete production threadedAttributeDcl
top::AGDcl ::= 'threaded' 'attribute' inh::Name ',' syn::Name tl::BracketedOptTypeExprs '::' te::TypeExpr d::OptDirectionMod ';'
{
  top.unparse = s"threaded attribute ${inh.unparse}, ${syn.unparse} ${tl.unparse} :: ${te.unparse}${d.unparse};";
  top.moduleNames := [];

  propagate grammarName, flowEnv;

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
      [attrDef(defaultEnvItem(threadedInhDcl(inhFName, synFName, tl.freeVariables, te.typerep, nothing(), d.reversed, sourceGrammar=top.grammarName, sourceLocation=inh.location))),
       attrDef(defaultEnvItem(threadedSynDcl(inhFName, synFName, tl.freeVariables, te.typerep, nothing(), d.reversed, sourceGrammar=top.grammarName, sourceLocation=syn.location)))],
      location=top.location);
}

concrete production collectionThreadedAttributeDcl
top::AGDcl ::= 'threaded' 'attribute' inh::Name ',' syn::Name tl::BracketedOptTypeExprs '::' te::TypeExpr 'with' q::NameOrBOperator d::OptDirectionMod ';'
{
  top.unparse = s"threaded attribute ${inh.unparse}, ${syn.unparse} ${tl.unparse} :: ${te.unparse} with ${q.unparse}${d.unparse};";
  top.moduleNames := [];

  propagate grammarName, compiledGrammars, config, flowEnv;

  production attribute inhFName :: String;
  inhFName = top.grammarName ++ ":" ++ inh.name;
  production attribute synFName :: String;
  synFName = top.grammarName ++ ":" ++ syn.name;
  
  tl.initialEnv = top.env;
  tl.env = tl.envBindingTyVars;
  te.env = tl.envBindingTyVars;

  q.env = top.env;
  q.operatorForType = te.typerep;
  
  top.errors <-
    if length(getAttrDclAll(inhFName, top.env)) > 1
    then [err(inh.location, "Attribute '" ++ inhFName ++ "' is already bound.")]
    else [];
  top.errors <-
    if length(getAttrDclAll(synFName, top.env)) > 1
    then [err(syn.location, "Attribute '" ++ synFName ++ "' is already bound.")]
    else [];
  
  -- TODO: We want to forward to collection attr decls for the translation, but provide our own defs.
  -- The non-interfering way of doing this would be to split up the collection attr decl prodictions
  -- into a prod providing the defs, and a prod providing the translation...
  top.defs := 
    [attrDef(defaultEnvItem(threadedInhDcl(inhFName, synFName, tl.freeVariables, te.typerep, just(q.operation), d.reversed, sourceGrammar=top.grammarName, sourceLocation=inh.location))),
     attrDef(defaultEnvItem(threadedSynDcl(inhFName, synFName, tl.freeVariables, te.typerep, just(q.operation), d.reversed, sourceGrammar=top.grammarName, sourceLocation=syn.location)))];
  
  forwards to
    appendAGDcl(
      collectionAttributeDclInh('inherited', 'attribute', inh, tl, '::', te, 'with', q, ';', location=top.location),
      collectionAttributeDclSyn('synthesized', 'attribute', syn, tl, '::', te, 'with', q, ';', location=top.location),
      location=top.location);
}

synthesized attribute reversed::Boolean;

nonterminal OptDirectionMod with unparse, reversed;
concrete productions top::OptDirectionMod
| 'direction' '=' d::Direction
  { top.unparse = " direction=" ++ d.unparse;
    top.reversed = d.reversed; }
|
  { top.unparse = "";
    top.reversed = false; }

nonterminal Direction with unparse, reversed;
concrete productions top::Direction
| 'left' 'to' 'right'
  { top.unparse = "left to right";
    top.reversed = false; }
| 'right' 'to' 'left'
  { top.unparse = "right to left";
    top.reversed = true; }

abstract production propagateThreadedInh
top::ProductionStmt ::= isCol::Boolean rev::Boolean inh::Decorated! QName syn::String
{
  undecorates to propagateOneAttr(inh, location=top.location);
  top.unparse = s"propagate ${inh.unparse};";
  
  local lhsName::String = top.frame.signature.outputElement.elementName;
  local occursChildren::[String] =
    map(
      (.elementName),
      filter(
        \ ie::NamedSignatureElement ->
          isDecorable(ie.typerep, top.env) &&
          -- Only propagate for unique decorated children that don't have the inh attribute
          case getMaxRefSet(ie.typerep, top.env) of
          | just(inhs) -> !contains(inh.lookupAttribute.fullName, inhs)
          | nothing() -> false
          end &&
          !null(getOccursDcl(inh.lookupAttribute.fullName, ie.typerep.typeName, top.env)) &&
          !null(getOccursDcl(syn, ie.typerep.typeName, top.env)),
        if null(getOccursDcl(syn, top.frame.lhsNtName, top.env)) && !null(top.frame.signature.inputElements)
        then init(top.frame.signature.inputElements)
        else top.frame.signature.inputElements));
  forwards to
    threadInhDcl(
      isCol, inh.name, syn,
      map(
        name(_, top.location),
        lhsName ::
        (if rev then reverse(occursChildren) else occursChildren) ++
        if null(getOccursDcl(syn, top.frame.lhsNtName, top.env)) && !null(top.frame.signature.inputElements)
        then if !null(getOccursDcl(inh.lookupAttribute.fullName, last(top.frame.signature.inputElements).typerep.typeName, top.env))
          then [last(top.frame.signature.inputElements).elementName]
          else []
        else [if !null(getValueDcl("forward", top.env)) then "forward" else lhsName]),
      location=top.location);
}

abstract production propagateThreadedSyn
top::ProductionStmt ::= isCol::Boolean rev::Boolean inh::String syn::Decorated! QName
{
  undecorates to propagateOneAttr(syn, location=top.location);
  top.unparse = s"propagate ${syn.unparse};";
  
  local lhsName::String = top.frame.signature.outputElement.elementName;
  local occursChildren::[String] =
    map(
      (.elementName),
      filter(
        \ ie::NamedSignatureElement ->
          isDecorable(ie.typerep, top.env) &&
          -- Only propagate for unique decorated children that don't have the inh attribute
          case getMaxRefSet(ie.typerep, top.env) of
          | just(inhs) -> !contains(inh, inhs)
          | nothing() -> false
          end &&
          !null(getOccursDcl(inh, ie.typerep.typeName, top.env)) &&
          !null(getOccursDcl(syn.lookupAttribute.fullName, ie.typerep.typeName, top.env)),
        top.frame.signature.inputElements));
  forwards to
    threadSynDcl(
      isCol, inh, syn.name,
      map(
        name(_, top.location),
        lhsName ::
        (if rev then reverse(occursChildren) else occursChildren) ++
        [if !null(getValueDcl("forward", top.env)) then "forward" else lhsName]),
      location=top.location);
}

concrete production threadDcl_c
top::ProductionStmt ::= 'thread' inh::QName ',' syn::QName 'on' children::ChildNameList ';'
{
  top.unparse = s"thread ${inh.unparse}, ${syn.unparse} on ${children.unparse};";
  propagate env;

  -- Needed to avoid circularity due to forwarding based on env
  top.productionAttributes := [];
  top.defs := [];

  forwards to
    productionStmtAppend(
      threadInhDcl(
        inh.lookupAttribute.found && inh.lookupAttribute.dcl.isCollection,
        inh.name, syn.name, children.ids,
        location=top.location),
      threadSynDcl(
        syn.lookupAttribute.found && syn.lookupAttribute.dcl.isCollection,
        inh.name, syn.name, children.ids,
        location=top.location),
      location=top.location);
}

abstract production threadInhDcl
top::ProductionStmt ::= isCol::Boolean inh::String syn::String children::[Name]
{
  top.unparse = s"thread ${inh}, ${syn} on ${implode(", ", map((.unparse), children))};";
  
  local lhsName::String = top.frame.signature.outputElement.elementName;
  local attrDefProd::(ProductionStmt ::= DefLHS QNameAttrOccur Expr) =
    if isCol
    then attrContainsBase(_, '.', _, ':=', _, ';', location=top.location)
    else attributeDef(_, '.', _, '=', _, ';', location=top.location);
  forwards to
    foldr(
      productionStmtAppend(_, _, location=top.location),
      errorProductionStmt([], location=top.location), -- No emptyProductionStmt?
      zipWith(
        \ c1::Name c2::Name ->
          if c1.name != lhsName
          then
            attrDefProd(
              concreteDefLHS(qNameId(c1, location=top.location), location=top.location),
              qNameAttrOccur(qName(top.location, inh), location=top.location),
              access(
                baseExpr(qNameId(c2, location=top.location), location=top.location), '.',
                qNameAttrOccur(qName(top.location, if c2.name == lhsName then inh else syn), location=top.location),
                location=top.location))
          else errorProductionStmt([], location=top.location),
        tail(children), children));
}

abstract production threadSynDcl
top::ProductionStmt ::= isCol::Boolean inh::String syn::String children::[Name]
{
  top.unparse = s"thread ${inh}, ${syn} on ${implode(", ", map((.unparse), children))};";
  
  local lhsName::String = top.frame.signature.outputElement.elementName;
  local attrDefProd::(ProductionStmt ::= DefLHS QNameAttrOccur Expr) =
    if isCol
    then attrContainsBase(_, '.', _, ':=', _, ';', location=top.location)
    else attributeDef(_, '.', _, '=', _, ';', location=top.location);
  forwards to
    foldr(
      productionStmtAppend(_, _, location=top.location),
      errorProductionStmt([], location=top.location), -- No emptyProductionStmt?
      zipWith(
        \ c1::Name c2::Name ->
          if c1.name == lhsName
          then
            attrDefProd(
              concreteDefLHS(qNameId(c1, location=top.location), location=top.location),
              qNameAttrOccur(qName(top.location, syn), location=top.location),
              access(
                baseExpr(qNameId(c2, location=top.location), location=top.location), '.',
                qNameAttrOccur(qName(top.location, if c2.name == lhsName then inh else syn), location=top.location),
                location=top.location))
          else errorProductionStmt([], location=top.location),
        tail(children), children));
}

synthesized attribute ids :: [Name];

nonterminal ChildNameList with location, unparse, ids;
concrete production idSingle
top::ChildNameList ::= id::ChildName
{
  top.unparse = id.unparse;
  top.ids = [id.id];
}

concrete production idCons
top::ChildNameList ::= id1::ChildName ',' id2::ChildNameList
{
  top.unparse = id1.unparse ++ ", " ++ id2.unparse;
  top.ids = id1.id :: id2.ids;
}

synthesized attribute id :: Name;

nonterminal ChildName with location, unparse, id;
concrete production idName
top::ChildName ::= id::Name
{
  top.unparse = id.unparse;
  top.id = id;
}

concrete production idForward
top::ChildName ::= 'forward'
{
  top.unparse = "forward";
  top.id = name("forward", top.location);
}

