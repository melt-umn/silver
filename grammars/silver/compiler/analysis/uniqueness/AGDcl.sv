grammar silver:compiler:analysis:uniqueness;

attribute uniqueRefs occurs on Grammar, Root, AGDcls, AGDcl;
propagate uniqueRefs on Grammar, Root, AGDcls, AGDcl;

aspect production globalValueDclConcrete
top::AGDcl ::= 'global' id::Name '::' cl::ConstraintList '=>' t::TypeExpr '=' e::Expr ';'
{
  top.errors <- uniqueContextErrors(e.uniqueRefs);
}
aspect production defaultConstraintClassBodyItem
top::ClassBodyItem ::= id::Name '::' cl::ConstraintList '=>' ty::TypeExpr '=' e::Expr ';'
{
  top.errors <- uniqueContextErrors(e.uniqueRefs);
}
aspect production instanceBodyItem
top::InstanceBodyItem ::= id::QName '=' e::Expr ';'
{
  top.errors <- uniqueContextErrors(e.uniqueRefs);
}
