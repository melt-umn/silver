grammar simple:concretesyntax;

{--
 - A statement declaring a variable and its type.
 -}
nonterminal Decl with pp, ast<ast:Decl>;

concrete production decl
d::Decl ::= te::TypeExpr id::term:Id ';'
{
  d.pp = te.pp ++ " " ++ id.lexeme ++ "; \n";
  d.ast = ast:decl(te.ast, name(id));
}

{--
 - A concrete expression denoting a type
 -}
nonterminal TypeExpr with pp, ast<ast:TypeExpr>;

concrete production typeExprInteger
t::TypeExpr ::= 'Integer' 
{
  t.pp = "Integer";
  t.ast = ast:typeExprInteger(); 
}

concrete production typeExprFloat
t::TypeExpr ::=  'Float' 
{
  t.pp = "Float";
  t.ast = ast:typeExprFloat(); 
}

concrete production typeExprBoolean
t::TypeExpr ::=  'Boolean' 
{
  t.pp = "Boolean";
  t.ast = ast:typeExprBoolean(); 
}
