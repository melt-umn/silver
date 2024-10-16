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
    then [errFromOrigin(inh, "Attribute '" ++ inhFName ++ "' is already bound.")]
    else [];
  top.errors <-
    if length(getAttrDclAll(synFName, top.env)) > 1
    then [errFromOrigin(syn, "Attribute '" ++ synFName ++ "' is already bound.")]
    else [];
  
  forwards to
    defsAGDcl(
      [attrDef(defaultEnvItem(threadedInhDcl(inhFName, synFName, tl.freeVariables, te.typerep, nothing(), d.reversed, sourceGrammar=top.grammarName, sourceLocation=inh.nameLoc))),
       attrDef(defaultEnvItem(threadedSynDcl(inhFName, synFName, tl.freeVariables, te.typerep, nothing(), d.reversed, sourceGrammar=top.grammarName, sourceLocation=syn.nameLoc)))]);
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
    then [errFromOrigin(inh, "Attribute '" ++ inhFName ++ "' is already bound.")]
    else [];
  top.errors <-
    if length(getAttrDclAll(synFName, top.env)) > 1
    then [errFromOrigin(syn, "Attribute '" ++ synFName ++ "' is already bound.")]
    else [];
  
  -- TODO: We want to forward to collection attr decls for the translation, but provide our own defs.
  -- The non-interfering way of doing this would be to split up the collection attr decl prodictions
  -- into a prod providing the defs, and a prod providing the translation...
  top.defs := 
    [attrDef(defaultEnvItem(threadedInhDcl(inhFName, synFName, tl.freeVariables, te.typerep, just(q.operation), d.reversed, sourceGrammar=top.grammarName, sourceLocation=inh.nameLoc))),
     attrDef(defaultEnvItem(threadedSynDcl(inhFName, synFName, tl.freeVariables, te.typerep, just(q.operation), d.reversed, sourceGrammar=top.grammarName, sourceLocation=syn.nameLoc)))];
  
  forwards to
    appendAGDcl(
      collectionAttributeDclInh('inherited', 'attribute', @inh, @tl, '::', @te, 'with', @q, ';'),
      collectionAttributeDclSyn('synthesized', 'attribute', @syn, ^tl, '::', ^te, 'with', ^q, ';'));
}

synthesized attribute reversed::Boolean;

tracked nonterminal OptDirectionMod with unparse, reversed;
concrete productions top::OptDirectionMod
| 'direction' '=' d::Direction
  { top.unparse = " direction=" ++ d.unparse;
    top.reversed = d.reversed; }
|
  { top.unparse = "";
    top.reversed = false; }

tracked nonterminal Direction with unparse, reversed;
concrete productions top::Direction
| 'left' 'to' 'right'
  { top.unparse = "left to right";
    top.reversed = false; }
| 'right' 'to' 'left'
  { top.unparse = "right to left";
    top.reversed = true; }

abstract production propagateThreadedInh implements Propagate
top::ProductionStmt ::= includeShared::Boolean @inh::QName isCol::Boolean rev::Boolean syn::String
{
  top.unparse = s"propagate ${if includeShared then "@" else ""}${inh.unparse};";
  
  local lhsName::String = top.frame.signature.outputElement.elementName;
  local occursChildren::[String] =
    map(
      (.elementName),
      filter(
        \ ie::NamedSignatureElement ->
          isDecorable(ie.elementDclType, top.env) &&
          !null(getOccursDcl(inh.lookupAttribute.fullName, ie.typerep.typeName, top.env)) &&
          !null(getOccursDcl(syn, ie.typerep.typeName, top.env)) &&
          (includeShared || !ie.elementShared),
        if null(getOccursDcl(syn, top.frame.lhsNtName, top.env)) && !null(top.frame.signature.inputElements)
        then init(top.frame.signature.inputElements)
        else top.frame.signature.inputElements));
  forwards to
    threadInhDcl(
      isCol, inh.name, syn,
      map(name,
        lhsName ::
        (if rev then reverse(occursChildren) else occursChildren) ++
        if null(getOccursDcl(syn, top.frame.lhsNtName, top.env)) && !null(top.frame.signature.inputElements)
        then if !null(getOccursDcl(inh.lookupAttribute.fullName, last(top.frame.signature.inputElements).typerep.typeName, top.env))
          then [last(top.frame.signature.inputElements).elementName]
          else []
        else [if !null(getValueDcl("forward", top.env)) then "forward" else lhsName]));
}

abstract production propagateThreadedSyn implements Propagate
top::ProductionStmt ::= includeShared::Boolean @syn::QName isCol::Boolean rev::Boolean inh::String
{
  top.unparse = s"propagate ${if includeShared then "@" else ""}${syn.unparse};";
  
  local lhsName::String = top.frame.signature.outputElement.elementName;
  local occursChildren::[String] =
    map(
      (.elementName),
      filter(
        \ ie::NamedSignatureElement ->
          isDecorable(ie.elementDclType, top.env) &&
          !null(getOccursDcl(inh, ie.typerep.typeName, top.env)) &&
          !null(getOccursDcl(syn.lookupAttribute.fullName, ie.typerep.typeName, top.env)) &&
          (includeShared || !ie.elementShared),
        top.frame.signature.inputElements));
  forwards to
    threadSynDcl(
      isCol, inh, syn.name,
      map(name,
        lhsName ::
        (if rev then reverse(occursChildren) else occursChildren) ++
        [if !null(getValueDcl("forward", top.env)) then "forward" else lhsName]));
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
        inh.name, syn.name, children.ids),
      threadSynDcl(
        syn.lookupAttribute.found && syn.lookupAttribute.dcl.isCollection,
        inh.name, syn.name, children.ids));
}

abstract production threadInhDcl
top::ProductionStmt ::= isCol::Boolean inh::String syn::String children::[Name]
{
  top.unparse = s"thread ${inh}, ${syn} on ${implode(", ", map((.unparse), children))};";
  
  local lhsName::String = top.frame.signature.outputElement.elementName;
  local attrDefProd::(ProductionStmt ::= DefLHS QNameAttrOccur Expr) =
    if isCol
    then attrContainsBase(_, '.', _, ':=', _, ';')
    else attributeDef(_, '.', _, '=', _, ';');
  forwards to
    foldr(
      productionStmtAppend(_, _),
      emptyProductionStmt(),
      zipWith(
        \ c1::Name c2::Name ->
          if c1.name != lhsName
          then
            attrDefProd(
              concreteDefLHS(qNameId(c1)),
              qNameAttrOccur(qName(inh)),
              access(
                baseExpr(qNameId(c2)), '.',
                qNameAttrOccur(qName(if c2.name == lhsName then inh else syn))))
          else emptyProductionStmt(),
        tail(children), children));
}

abstract production threadSynDcl
top::ProductionStmt ::= isCol::Boolean inh::String syn::String children::[Name]
{
  top.unparse = s"thread ${inh}, ${syn} on ${implode(", ", map((.unparse), children))};";
  
  local lhsName::String = top.frame.signature.outputElement.elementName;
  local attrDefProd::(ProductionStmt ::= DefLHS QNameAttrOccur Expr) =
    if isCol
    then attrContainsBase(_, '.', _, ':=', _, ';')
    else attributeDef(_, '.', _, '=', _, ';');
  forwards to
    foldr(
      productionStmtAppend(_, _),
      emptyProductionStmt(),
      zipWith(
        \ c1::Name c2::Name ->
          if c1.name == lhsName
          then
            attrDefProd(
              concreteDefLHS(qNameId(c1)),
              qNameAttrOccur(qName(syn)),
              access(
                baseExpr(qNameId(c2)), '.',
                qNameAttrOccur(qName(if c2.name == lhsName then inh else syn))))
          else emptyProductionStmt(),
        tail(children), children));
}

synthesized attribute ids :: [Name];

tracked nonterminal ChildNameList with unparse, ids;
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

tracked nonterminal ChildName with unparse, id;
concrete production idName
top::ChildName ::= id::Name
{
  top.unparse = id.unparse;
  top.id = ^id;
}

concrete production idForward
top::ChildName ::= 'forward'
{
  top.unparse = "forward";
  top.id = name("forward");
}

