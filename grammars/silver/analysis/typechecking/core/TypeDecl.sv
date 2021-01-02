grammar silver:analysis:typechecking:core;

aspect production typeAliasDecl
top::AGDcl ::= 'type' id::Name tl::BracketedOptTypeExprs '=' te::TypeExpr ';'
{
  top.errors <- te.errorsFullyApplied;
}
