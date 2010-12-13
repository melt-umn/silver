grammar simple:concretesyntax ;

nonterminal Decl_c with pp ;
nonterminal TypeExpr_c with pp ;

synthesized attribute ast_Decl :: Decl occurs on Decl_c ;
synthesized attribute ast_TypeExpr :: TypeExpr occurs on TypeExpr_c ;

concrete production decl_c
d::Decl_c ::= te::TypeExpr_c id::Id_t ';'
{
  d.pp = te.pp ++ " " ++ id.lexeme ++ " ; \n" ;
  d.ast_Decl = decl(te.ast_TypeExpr, id) ;
}

concrete production typeExprInteger_c
t::TypeExpr_c ::= 'Integer' 
{
  t.pp = "Integer" ;
  t.ast_TypeExpr = typeExprInteger() ; 
}

concrete production typeExprFloat_c
t::TypeExpr_c ::=  'Float' 
{
  t.pp = "Float" ;
  t.ast_TypeExpr = typeExprFloat() ; 
}

concrete production typeExprBoolean_c
t::TypeExpr_c ::=  'Boolean' 
{
  t.pp = "Boolean" ;
  t.ast_TypeExpr = typeExprBoolean() ; 
}
