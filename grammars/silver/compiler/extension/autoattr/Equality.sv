grammar silver:compiler:extension:autoattr;

concrete production equalityAttributeDcl
top::AGDcl ::= 'equality' 'attribute' syn::Name 'with' inh::QName ';'
{
  top.unparse = s"equality attribute ${syn.unparse} with ${inh.unparse};";
  top.moduleNames := [];

  production attribute inhFName :: String;
  inhFName = inh.lookupAttribute.fullName;
  production attribute synFName :: String;
  synFName = top.grammarName ++ ":" ++ syn.name;
  
  top.errors <-
    if length(getAttrDclAll(synFName, top.env)) > 1
    then [err(syn.location, "Attribute '" ++ synFName ++ "' is already bound.")]
    else [];
  
  forwards to
    defsAGDcl(
      [attrDef(defaultEnvItem(equalityDcl(inhFName, synFName, sourceGrammar=top.grammarName, sourceLocation=syn.location)))],
      location=top.location);
}

{--
 - Propagate a equality synthesized attribute on the enclosing production
 - @param attr  The name of the attribute to propagate
 -}
abstract production propagateEquality
top::ProductionStmt ::= inh::String syn::PartiallyDecorated QName
{
  top.unparse = s"propagate ${syn.unparse};";
  
  forwards to
    Silver_ProductionStmt {
      $name{top.frame.signature.outputElement.elementName}.$QName{new(syn)} =
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
                  if null(getOccursDcl(syn.lookupAttribute.dcl.fullName, ie.typerep.typeName, top.env))
                  then Silver_Expr { silver:core:eq($name{ie.elementName}, $name{ie.elementName ++ "2"}) }
                  else Silver_Expr { $name{ie.elementName}.$QName{new(syn)} },
                top.frame.signature.inputElements))}
        | _ -> false
        end;
    };
}
