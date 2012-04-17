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
top::FunctionSignature ::= lhs::FunctionLHS '::=' rhs::ProductionRHS 
{
  top.lexicalTypeVariables = makeSet(lhs.lexicalTypeVariables ++ rhs.lexicalTypeVariables);
}

aspect production functionLHS
top::FunctionLHS ::= t::Type
{
  top.lexicalTypeVariables = t.lexicalTypeVariables;
}

