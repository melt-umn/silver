grammar silver:analysis:typechecking:core;

aspect production typeAliasDecl
top::AGDcl ::= 'type' id::Name tl::BracketedOptTypeExprs '=' te::TypeExpr ';'
{
  top.errors <-
    if te.typerep.kindArity > 0
    then [err(te.location, s"Type ${te.unparse} is not fully applied")]
    else [];
}
