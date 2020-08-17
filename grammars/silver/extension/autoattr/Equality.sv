grammar silver:extension:autoattr;

concrete production equalityAttributeDcl
top::AGDcl ::= 'equality' 'attribute' inh::Name ',' syn::Name ';'
{
  top.unparse = s"equality attribute ${inh.unparse}, ${syn.unparse};";
  top.moduleNames := [];

  production attribute inhFName :: String;
  inhFName = top.grammarName ++ ":" ++ inh.name;
  production attribute synFName :: String;
  synFName = top.grammarName ++ ":" ++ syn.name;
  
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
      [attrDef(defaultEnvItem(equalityInhDcl(top.grammarName, inh.location, inhFName, freshTyVar()))),
       attrDef(defaultEnvItem(equalitySynDcl(top.grammarName, syn.location, inhFName, synFName)))],
      location=top.location);
}

{--
 - Propagate a equality inherited attribute on the enclosing production
 - @param attr  The name of the attribute to propagate
 -}
abstract production propagateEqualityInh
top::ProductionStmt ::= attr::Decorated QName
{
  top.unparse = s"propagate ${attr.unparse};";
  
  local numChildren::Integer = length(top.frame.signature.inputElements);
  forwards to
    foldr(
      productionStmtAppend(_, _, location=top.location),
      errorProductionStmt([], location=top.location), -- No emptyProductionStmt?
      map(
        \ ie::Pair<Integer NamedSignatureElement> ->
          Silver_ProductionStmt {
            $name{ie.snd.elementName}.$QName{new(attr)} =
              case $name{top.frame.signature.outputElement.elementName}.$QName{new(attr)} of
              | $Pattern{
                  prodAppPattern(
                    qName(top.location, top.frame.signature.fullName),
                    '(',
                    foldr(
                      patternList_more(_, ',', _, location=top.location),
                      patternList_nil(location=top.location),
                      repeat(wildcPattern('_', location=top.location), ie.fst) ++
                      Silver_Pattern { a } ::
                      repeat(wildcPattern('_', location=top.location), numChildren - (ie.fst + 1)) ),
                    ')',
                    location=top.location)} -> a
              end;
          },
        filter(
          \ ie::Pair<Integer NamedSignatureElement> ->
            !null(getOccursDcl(attr.lookupAttribute.dcl.fullName, ie.snd.typerep.typeName, top.env)),
          zipWith(pair, range(0, numChildren), top.frame.signature.inputElements))));
}

{--
 - Propagate a equality synthesized attribute on the enclosing production
 - @param attr  The name of the attribute to propagate
 -}
abstract production propagateEqualitySyn
top::ProductionStmt ::= inh::String syn::Decorated QName
{
  top.unparse = s"propagate ${syn.unparse};";
  
  local numChildren::Integer = length(top.frame.signature.inputElements);
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
                  then Silver_Expr { $name{ie.elementName} == $name{ie.elementName ++ "2"} }
                  else Silver_Expr { $name{ie.elementName}.$QName{new(syn)} },
                top.frame.signature.inputElements))}
        | _ -> false
        end;
    };
}
