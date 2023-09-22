grammar silver:compiler:definition:type:syntax;

aspect production typeClassDcl
top::AGDcl ::= 'class' cl::ConstraintList '=>' id::QNameType var::TypeExpr '{' body::ClassBody '}'
{
  production attribute allLexicalTyVars :: [String];
  allLexicalTyVars = nub(cl.lexicalTypeVariables ++ var.lexicalTypeVariables ++ body.lexicalTypeVariables);
  
  headPreDefs <- addNewLexicalTyVars(top.grammarName, cl.lexicalTyVarKinds ++ var.lexicalTyVarKinds ++ body.lexicalTyVarKinds, allLexicalTyVars);
}
