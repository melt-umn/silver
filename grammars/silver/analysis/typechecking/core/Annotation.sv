grammar silver:analysis:typechecking:core;

aspect production annotationDcl
top::AGDcl ::= 'annotation' a::QName tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.errors <- te.errorsFullyApplied;
}
