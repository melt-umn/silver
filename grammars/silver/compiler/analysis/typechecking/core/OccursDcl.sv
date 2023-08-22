grammar silver:compiler:analysis:typechecking:core;

aspect production defaultAttributionDcl
top::AGDcl ::= at::Decorated! QName attl::BracketedOptTypeExprs nt::QName nttl::BracketedOptTypeExprs
{
  top.errors <-
    if at.lookupAttribute.found && at.lookupAttribute.dcl.isTranslation && !protoatty.isNonterminal
    then [err(top.location, s"Occurrence of translation attribute ${at.lookupAttribute.fullName} must have a nonterminal type.  Instead it is of type " ++ prettyType(protoatty))]
    else [];
}
