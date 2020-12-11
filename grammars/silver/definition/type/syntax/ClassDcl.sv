grammar silver:definition:type:syntax;

aspect production typeClassDcl
top::AGDcl ::= 'class' cl::OptConstraintList id::Name var::TypeExpr body::ClassBody
{
  production attribute allLexicalTyVars :: [String];
  allLexicalTyVars = makeSet(cl.lexicalTypeVariables ++ var.lexicalTypeVariables);
  
  headDefs <- addNewLexicalTyVars(top.grammarName, top.location, allLexicalTyVars);
}
