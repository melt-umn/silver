grammar silver:analysis:typechecking:core;

aspect production attributeDclInh
top::AGDcl ::= 'inherited' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.errors <- te.errorsFullyApplied;
}

aspect production attributeDclSyn
top::AGDcl ::= 'synthesized' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.errors <- te.errorsFullyApplied;
}

