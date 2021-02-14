grammar silver:compiler:analysis:typechecking:core;

aspect production annotationDcl
top::AGDcl ::= 'annotation' a::QName tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.errors <- te.errorsKindStar;
}
