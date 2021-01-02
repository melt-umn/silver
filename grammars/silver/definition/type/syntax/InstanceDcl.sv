grammar silver:definition:type:syntax;

aspect production instanceDcl
top::AGDcl ::= 'instance' cl::ConstraintList '=>' id::QNameType ty::TypeExpr '{' body::InstanceBody '}'
{
  production attribute allLexicalTyVars :: [String];
  allLexicalTyVars = makeSet(cl.lexicalTypeVariables ++ ty.lexicalTypeVariables);
  
  headPreDefs <- addNewLexicalTyVars(top.grammarName, top.location, cl.lexicalTyVarKinds ++ ty.lexicalTyVarKinds, allLexicalTyVars);
}
