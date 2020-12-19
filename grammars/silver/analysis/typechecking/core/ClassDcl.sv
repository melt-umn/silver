grammar silver:analysis:typechecking:core;

aspect production typeClassDcl
top::AGDcl ::= 'class' cl::ConstraintList '=>' id::QNameType var::TypeExpr '{' body::ClassBody '}'
{
  -- TODO should anything move here?
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
