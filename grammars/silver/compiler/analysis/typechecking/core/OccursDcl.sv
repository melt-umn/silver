grammar silver:compiler:analysis:typechecking:core;

aspect production defaultAttributionDcl
top::AGDcl ::= at::Decorated! QName attl::BracketedOptTypeExprs nt::QName nttl::BracketedOptTypeExprs
{
  local checkNT::TypeCheck = checkNonterminal(top.env, false, protoatty);
  checkNT.downSubst = emptySubst();
  checkNT.finalSubst = emptySubst();
  
  top.errors <-
    if at.lookupAttribute.found && at.lookupAttribute.dcl.isTranslation && checkNT.typeerror
    then [errFromOrigin(top, s"Occurrence of translation attribute ${at.lookupAttribute.fullName} must have a nonterminal type.  Instead it is of type " ++ checkNT.leftpp)]
    else [];
}
