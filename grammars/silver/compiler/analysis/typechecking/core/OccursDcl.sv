grammar silver:compiler:analysis:typechecking:core;

aspect production defaultAttributionDcl
top::AGDcl ::= at::Decorated! QName _ _ _
{
  top.errors <-
    if at.lookupAttribute.found && at.lookupAttribute.dcl.isTranslation && !protoatty.isNonterminal
    then [err(top.location, s"Occurrence of translation attribute ${at.lookupAttribute.fullName} must have a nonterminal type.  Instead it is of type " ++ protoatty.typepp)]
    else [];
}
