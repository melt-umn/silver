grammar silver:compiler:extension:autoattr;

concrete production orderingAttributeDcl
top::AGDcl ::= 'ordering' 'attribute' keySyn::Name ',' syn::Name 'with' inh::QName ';'
{
  top.unparse = s"ordering attribute ${keySyn.unparse}, ${syn.unparse} with ${inh.unparse};";
  top.moduleNames := [];

  propagate env;

  production attribute inhFName :: String;
  inhFName = inh.lookupAttribute.fullName;
  production attribute keySynFName :: String;
  keySynFName = top.grammarName ++ ":" ++ keySyn.name;
  production attribute synFName :: String;
  synFName = top.grammarName ++ ":" ++ syn.name;

  top.errors <-
    if length(getAttrDclAll(synFName, top.env)) > 1
    then [errFromOrigin(syn, "Attribute '" ++ synFName ++ "' is already bound.")]
    else [];

  top.errors <-
    if length(getAttrDclAll(keySynFName, top.env)) > 1
    then [errFromOrigin(syn, "Attribute '" ++ keySynFName ++ "' is already bound.")]
    else [];

  forwards to
    defsAGDcl(
      [attrDef(defaultEnvItem(orderingKeyDcl(keySynFName, sourceGrammar=top.grammarName, sourceLocation=syn.nameLoc))),
       attrDef(defaultEnvItem(orderingDcl(inhFName, keySynFName, synFName, sourceGrammar=top.grammarName, sourceLocation=syn.nameLoc)))]);
}

{--
 - Propagate a ordering key synthesized attribute on the enclosing production
 -}
abstract production propagateOrderingKey implements Propagate
top::ProductionStmt ::= includeShared::Boolean @syn::QName
{
  top.unparse = s"propagate ${if includeShared then "@" else ""}${syn.unparse};";

  forwards to
    Silver_ProductionStmt {
      $name{top.frame.signature.outputElement.elementName}.$QName{^syn} =
        $Expr{stringConst(terminal(String_t, s"\"${top.frame.fullName}\""))};
    };
}

{--
 - Propagate a ordering synthesized attribute on the enclosing production
 -}
abstract production propagateOrdering implements Propagate
top::ProductionStmt ::= includeShared::Boolean @syn::QName inh::String keySyn::String
{
  top.unparse = s"propagate ${if includeShared then "@" else ""}${syn.unparse};";
  
  local topName::String = top.frame.signature.outputElement.elementName;
  forwards to
    Silver_ProductionStmt {
      $name{topName}.$QName{^syn} =
        case $name{topName}.$name{inh} of
        | $Pattern{
            prodAppPattern(
              qName(top.frame.signature.fullName),
              '(',
              foldr(
                patternList_more(_, ',', _),
                patternList_nil(),
                map(
                  \ ie::NamedSignatureElement -> Silver_Pattern { $name{ie.elementName ++ "2"} },
                  top.frame.signature.inputElements)),
              ')')} ->
          $Expr{
            if null(top.frame.signature.inputElements)
            then Silver_Expr { 0 }
            else
              foldr1(
                \ e1::Expr e2::Expr ->
                  Silver_Expr { let res::Integer = $Expr{e1} in if res == 0 then $Expr{e2} else res end },
                map(
                  \ ie::NamedSignatureElement ->
                    if !null(getOccursDcl(syn.lookupAttribute.dcl.fullName, ie.typerep.typeName, top.env))
                    then Silver_Expr { $name{ie.elementName}.$QName{^syn} }
                    else if isDecorable(ie.typerep, top.env)
                    then Silver_Expr {
                      silver:core:compare(silver:core:new($name{ie.elementName}), silver:core:new($name{ie.elementName ++ "2"}))
                    }
                    else Silver_Expr {
                      silver:core:compare($name{ie.elementName}, $name{ie.elementName ++ "2"})
                    },
                  top.frame.signature.inputElements))}
        | _ -> silver:core:compare($name{topName}.$name{keySyn}, $name{topName}.$name{inh}.$name{keySyn})
        end;
    };
}
