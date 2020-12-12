grammar silver:analysis:typechecking:core;

aspect production typeClassDcl
top::AGDcl ::= 'class' cl::OptConstraintList id::Name var::TypeExpr '{' body::ClassBody '}'
{
  
}

aspect production consClassBody
top::ClassBody ::= h::ClassBodyItem t::ClassBody
{
  
}
aspect production nilClassBody
top::ClassBody ::= 
{
  
}

aspect production classBodyItem
top::ClassBodyItem ::= id::Name '::' ty::TypeExpr ';'
{
  
}
