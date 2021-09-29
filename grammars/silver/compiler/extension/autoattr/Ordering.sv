grammar silver:compiler:extension:autoattr;

concrete production orderingAttributeDcl
top::AGDcl ::= 'ordering' 'attribute' syn::Name 'with' inh::Name ';'
{
  top.unparse = s"ordering attribute ${syn.unparse} with ${inh.unparse};";
  top.moduleNames := [];

  production attribute inhFName :: String;
  inhFName = top.grammarName ++ ":" ++ inh.name;
  production attribute synFName :: String;
  synFName = top.grammarName ++ ":" ++ syn.name;
  
  top.errors <-
    if length(getAttrDclAll(synFName, top.env)) > 1
    then [err(syn.location, "Attribute '" ++ synFName ++ "' is already bound.")]
    else [];
  
  forwards to
    defsAGDcl(
      [attrDef(defaultEnvItem(orderingDcl(inhFName, synFName, sourceGrammar=top.grammarName, sourceLocation=syn.location)))],
      location=top.location);
}

{--
 - Propagate a ordering synthesized attribute on the enclosing production
 - @param attr  The name of the attribute to propagate
 -}
abstract production propagateOrdering
top::ProductionStmt ::= inh::String syn::Decorated QName
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
            if null(top.frame.signature.inputElements)
            then Silver_Expr { 0 }
            else
              foldr1(
                \ e1::Expr e2::Expr ->
                  Silver_Expr { if $Expr{e1} == 0 then $Expr{e2} else $Expr{e1} },
                map(
                  \ ie::NamedSignatureElement ->
                    if null(getOccursDcl(syn.lookupAttribute.dcl.fullName, ie.typerep.typeName, top.env))
                    then Silver_Expr { silver:core:compare($name{ie.elementName}, $name{ie.elementName ++ "2"}) }
                    else Silver_Expr { $name{ie.elementName}.$QName{new(syn)} },
                  top.frame.signature.inputElements))}
        | _ -> false
        end;
    };
}
