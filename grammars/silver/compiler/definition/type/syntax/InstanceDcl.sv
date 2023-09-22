grammar silver:compiler:definition:type:syntax;

aspect production instanceDcl
top::AGDcl ::= 'instance' cl::ConstraintList '=>' id::QNameType ty::TypeExpr '{' body::InstanceBody '}'
{
  production attribute allLexicalTyVars :: [String];
  allLexicalTyVars = nub(cl.lexicalTypeVariables ++ ty.lexicalTypeVariables);
  
  headPreDefs <- addNewLexicalTyVars(top.grammarName, cl.lexicalTyVarKinds ++ ty.lexicalTyVarKinds, allLexicalTyVars);
}
