grammar silver:compiler:extension:autoattr;

concrete production biequalityAttributeDcl
top::AGDcl ::= 'biequality' 'attribute' synPartial::Name ',' syn::Name 'with' inh::QName ';'
{
  top.unparse = s"biequality attribute ${synPartial.unparse}, ${syn.unparse} with ${inh.unparse};";
  top.moduleNames := [];

  propagate grammarName, env, flowEnv;

  production attribute inhFName :: String;
  inhFName = inh.lookupAttribute.fullName;
  production attribute synPartialFName :: String;
  synPartialFName = top.grammarName ++ ":" ++ synPartial.name;
  production attribute synFName :: String;
  synFName = top.grammarName ++ ":" ++ syn.name;

  top.errors <-
    if length(getAttrDclAll(synPartialFName, top.env)) > 1
    then [err(syn.location, "Attribute '" ++ synPartialFName ++ "' is already bound.")]
    else [];
  top.errors <-
    if length(getAttrDclAll(synFName, top.env)) > 1
    then [err(syn.location, "Attribute '" ++ synFName ++ "' is already bound.")]
    else [];

  forwards to
    defsAGDcl(
      [attrDef(defaultEnvItem(biequalityPartialDcl(inhFName, synPartialFName, synFName, sourceGrammar=top.grammarName, sourceLocation=synPartial.location))),
       attrDef(defaultEnvItem(biequalityDcl(inhFName, synPartialFName, synFName, sourceGrammar=top.grammarName, sourceLocation=syn.location)))],
      location=top.location);
}

abstract production biequalityInhAttributionDcl
top::AGDcl ::= at::Decorated! QName attl::BracketedOptTypeExprs nt::QName nttl::BracketedOptTypeExprs
{
  undecorates to attributionDcl('attribute', at, attl, 'occurs', 'on', nt, nttl, ';', location=top.location);
  top.unparse = "attribute " ++ at.unparse ++ attl.unparse ++ " occurs on " ++ nt.unparse ++ nttl.unparse ++ ";";
  top.moduleNames := [];

  attl.env = top.env;
  attl.grammarName = top.grammarName;
  attl.flowEnv = top.flowEnv;
  local fwrdAttl::BracketedOptTypeExprs =
    if length(attl.types) > 0
    then attl
    else
      botlSome(
        bTypeList(
          '<',
          typeListSingle(
            case nttl of
            | botlSome(tl) -> 
              appTypeExpr(
                nominalTypeExpr(nt.qNameType, location=top.location),
                tl, location=top.location)
            | botlNone() -> nominalTypeExpr(nt.qNameType, location=top.location)
            end,
            location=top.location),
          '>', location=top.location),
        location=top.location);
  forwards to defaultAttributionDcl(at, fwrdAttl, nt, nttl, location=top.location);
}

abstract production propagateBiequalitySynPartial
top::ProductionStmt ::= inh::String synPartial::Decorated! QName syn::String
{
  undecorates to propagateOneAttr(synPartial, location=top.location);
  top.unparse = s"propagate ${synPartial.unparse};";
  
  forwards to
    Silver_ProductionStmt {
      $name{top.frame.signature.outputElement.elementName}.$QName{new(synPartial)} =
        case $name{top.frame.signature.outputElement.elementName}.$name{inh} of
        | $Pattern{
            prodAppPattern(
              qName(top.location, top.frame.signature.fullName),
              '(',
              foldr(
                patternList_more(_, ',', _, location=top.location),
                patternList_nil(location=top.location),
                map(
                  \ ie::NamedSignatureElement -> Silver_Pattern { $name{ie.elementName ++ "2"} },
                  top.frame.signature.inputElements)),
              ')',
              location=top.location)} ->
          $Expr{
            foldr(
              and(_, '&&', _, location=top.location),
              trueConst('true', location=top.location),
              map(
                \ ie::NamedSignatureElement ->
                  if null(getOccursDcl(syn, ie.typerep.typeName, top.env))
                  then Silver_Expr { silver:core:eq($name{ie.elementName}, $name{ie.elementName ++ "2"}) }
                  else Silver_Expr { $name{ie.elementName}.$qName{syn} },
                top.frame.signature.inputElements))}
        | _ -> false
        end;
    };
}

abstract production propagateBiequalitySyn
top::ProductionStmt ::= inh::String synPartial::String syn::Decorated! QName
{
  undecorates to propagateOneAttr(syn, location=top.location);
  top.unparse = s"propagate ${syn.unparse};";
  
  forwards to
    Silver_ProductionStmt {
      $name{top.frame.signature.outputElement.elementName}.$QName{new(syn)} =
        $name{top.frame.signature.outputElement.elementName}.$qName{synPartial} ||
        $name{top.frame.signature.outputElement.elementName}.$qName{inh}.$qName{synPartial};
    };
}
