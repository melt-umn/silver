grammar silver:extension:autoattr;

concrete production unificationAttributeDcl
top::AGDcl ::= 'unification' 'attribute' inh::Name ',' synPartial::Name  ',' syn::Name ';'
{
  top.unparse = s"unification attribute ${inh.unparse}, ${synPartial.unparse}, ${syn.unparse};";
  top.moduleNames := [];

  production attribute inhFName :: String;
  inhFName = top.grammarName ++ ":" ++ inh.name;
  production attribute synPartialFName :: String;
  synPartialFName = top.grammarName ++ ":" ++ synPartial.name;
  production attribute synFName :: String;
  synFName = top.grammarName ++ ":" ++ syn.name;
  
  top.errors <-
    if length(getAttrDclAll(inhFName, top.env)) > 1
    then [err(inh.location, "Attribute '" ++ inhFName ++ "' is already bound.")]
    else [];
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
      [attrDef(defaultEnvItem(unificationInhDcl(top.grammarName, inh.location, inhFName, freshTyVar()))),
       attrDef(defaultEnvItem(unificationSynPartialDcl(top.grammarName, syn.location, inhFName, synPartialFName, synFName))),
       attrDef(defaultEnvItem(unificationSynDcl(top.grammarName, syn.location, inhFName, synPartialFName, synFName)))],
      location=top.location);
}

abstract production unificationInhAttributionDcl
top::AGDcl ::= at::Decorated QName attl::BracketedOptTypeExprs nt::QName nttl::BracketedOptTypeExprs
{
  top.unparse = "attribute " ++ at.unparse ++ attl.unparse ++ " occurs on " ++ nt.unparse ++ nttl.unparse ++ ";";
  top.moduleNames := [];
  
  forwards to
    defaultAttributionDcl(
      at,
      if length(attl.types) > 0
      then attl
      else
        botlSome(
          '<',
          typeListSingle(
            refTypeExpr(
              'Decorated',
              nominalTypeExpr(nt.qNameType, nttl, location=top.location),
              location=top.location),
            location=top.location),
          '>', location=top.location),
      nt, nttl,
      location=top.location);
}

abstract production propagateUnificationSynPartial
top::ProductionStmt ::= inh::String synPartial::Decorated QName syn::String
{
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
                  then Silver_Expr { $name{ie.elementName} == $name{ie.elementName ++ "2"} }
                  else Silver_Expr { $name{ie.elementName}.$qName{syn} },
                top.frame.signature.inputElements))}
        | _ -> false
        end;
    };
}

abstract production propagateUnificationSyn
top::ProductionStmt ::= inh::String synPartial::String syn::Decorated QName
{
  top.unparse = s"propagate ${syn.unparse};";
  
  forwards to
    Silver_ProductionStmt {
      $name{top.frame.signature.outputElement.elementName}.$QName{new(syn)} =
        $name{top.frame.signature.outputElement.elementName}.$qName{synPartial} ||
        $name{top.frame.signature.outputElement.elementName}.$qName{inh}.$qName{synPartial};
    };
}
