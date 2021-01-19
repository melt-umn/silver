grammar silver:compiler:definition:type:syntax;

attribute lexicalTypeVariables, lexicalTyVarKinds occurs on FunctionSignature, FunctionLHS;

aspect production functionDcl
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody 
{
  production attribute allLexicalTyVars :: [String];
  allLexicalTyVars = nub(ns.lexicalTypeVariables);
  
  sigDefs <- addNewLexicalTyVars(top.grammarName, top.location, ns.lexicalTyVarKinds, allLexicalTyVars);
}

aspect production functionSignature
top::FunctionSignature ::= cl::ConstraintList '=>' lhs::FunctionLHS '::=' rhs::ProductionRHS
{
  top.lexicalTypeVariables := nub(cl.lexicalTypeVariables ++ lhs.lexicalTypeVariables ++ rhs.lexicalTypeVariables);
}

propagate lexicalTyVarKinds on FunctionSignature;
propagate lexicalTypeVariables, lexicalTyVarKinds on FunctionLHS;

