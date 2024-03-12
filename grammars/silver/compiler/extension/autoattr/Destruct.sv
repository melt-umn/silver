grammar silver:compiler:extension:autoattr;

concrete production destructAttributeDcl
top::AGDcl ::= 'destruct' 'attribute' inh::Name ';'
{
  top.unparse = s"destruct attribute ${inh.unparse};";
  top.moduleNames := [];

  production attribute inhFName :: String;
  inhFName = top.grammarName ++ ":" ++ inh.name;
  
  top.errors <-
    if length(getAttrDclAll(inhFName, top.env)) > 1
    then [errFromOrigin(inh, "Attribute '" ++ inhFName ++ "' is already bound.")]
    else [];
  
  forwards to
    defsAGDcl(
      [attrDef(defaultEnvItem(destructDcl(inhFName, sourceGrammar=top.grammarName, sourceLocation=inh.nameLoc)))]);
}

abstract production destructAttributionDcl implements AttributionDcl
top::AGDcl ::= @at::QName attl::BracketedOptTypeExprs nt::QName nttl::BracketedOptTypeExprs
{
  top.unparse = "attribute " ++ at.unparse ++ attl.unparse ++ " occurs on " ++ nt.unparse ++ nttl.unparse ++ ";";
  top.moduleNames := [];

  propagate grammarName, env, flowEnv;
  
  forwards to
    defaultAttributionDcl(
      at,
      case attl.types of
      | [] ->
        botlSome(
          bTypeList(
            '<',
            typeListCons(
              case nttl of
              | botlSome(tl) -> 
                appTypeExpr(
                  nominalTypeExpr(nt.qNameType),
                  tl)
              | botlNone() -> nominalTypeExpr(nt.qNameType)
              end,
              typeListSingle(
                typerepTypeExpr(inhSetType([])))),
            '>'))
      | [i] ->
        botlSome(
          bTypeList(
            '<',
            typeListCons(
              case nttl of
              | botlSome(tl) -> 
                appTypeExpr(
                  nominalTypeExpr(nt.qNameType),
                  tl)
              | botlNone() -> nominalTypeExpr(nt.qNameType)
              end,
              typeListSingle(
                typerepTypeExpr(i))),
            '>'))
      | _ -> attl
      end,
      nt, nttl);
}

{--
 - Propagate a destruct inherited attribute on the enclosing production
 - @param attr  The name of the attribute to propagate
 -}
abstract production propagateDestruct implements Propagate
top::ProductionStmt ::= @attr::QName
{
  top.unparse = s"propagate ${attr.unparse};";
  
  local numChildren::Integer = length(top.frame.signature.inputElements);
  forwards to
    foldr(
      productionStmtAppend(_, _),
      errorProductionStmt([]), -- No emptyProductionStmt?
      map(
        \ ie::Pair<Integer NamedSignatureElement> ->
          Silver_ProductionStmt {
            $name{ie.snd.elementName}.$QName{new(attr)} =
              case $name{top.frame.signature.outputElement.elementName}.$QName{new(attr)} of
              | $Pattern{
                  prodAppPattern(
                    qName(top.frame.signature.fullName),
                    '(',
                    foldr(
                      patternList_more(_, ',', _),
                      patternList_nil(),
                      repeat(wildcPattern('_'), ie.fst) ++
                      Silver_Pattern { a } ::
                      repeat(wildcPattern('_'), numChildren - (ie.fst + 1)) ),
                    ')')} -> a
              | a ->
                error(
                  "Destruct attribute " ++ $Expr{stringConst(terminal(String_t, s"\"${attr.name}\"", attr.nameLoc))} ++
                  " demanded on child " ++ $Expr{stringConst(terminal(String_t, s"\"${ie.snd.elementName}\"", attr.nameLoc))} ++
                  " of production " ++ $Expr{stringConst(terminal(String_t, s"\"${top.frame.signature.fullName}\"", attr.nameLoc))} ++
                  " when given value " ++ silver:core:genericShow(a) ++ " does not match.")
              end;
          },
        filter(
          \ ie::Pair<Integer NamedSignatureElement> ->
            !null(getOccursDcl(attr.lookupAttribute.dcl.fullName, ie.snd.typerep.typeName, top.env)),
          zip(range(0, numChildren), top.frame.signature.inputElements))));
}

