grammar silver:definition:type:syntax;

attribute lexicalTypeVariables occurs on FunctionSignature, FunctionLHS;

aspect production functionDcl
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody 
{
  production attribute allLexicalTyVars :: [String];
  allLexicalTyVars = makeSet(ns.lexicalTypeVariables);
  
  sigDefs <- addNewLexicalTyVars(top.grammarName, top.location, allLexicalTyVars);
}

aspect production functionSignature
top::FunctionSignature ::= cl::OptConstraintList lhs::FunctionLHS '::=' rhs::ProductionRHS 
{
  top.lexicalTypeVariables := makeSet(cl.lexicalTypeVariables ++ lhs.lexicalTypeVariables ++ rhs.lexicalTypeVariables);
}

propagate lexicalTypeVariables on FunctionLHS;

