grammar silver:analysis:typechecking:core;

aspect production annotationDcl
top::AGDcl ::= 'annotation' a::QName tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.errors <-
    if te.typerep.kindArity > 0
    then [err(te.location, s"Type ${te.unparse} is not fully applied")]
    else [];
}
