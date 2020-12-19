grammar silver:definition:type:syntax;

attribute lexicalTypeVariables occurs on ProductionSignature, ProductionLHS, ProductionRHS, ProductionRHSElem;

flowtype lexicalTypeVariables {env} on ProductionSignature, ProductionLHS, ProductionRHS;
flowtype lexicalTypeVariables {deterministicCount, env} on ProductionRHSElem;

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  production attribute allLexicalTyVars :: [String];
  allLexicalTyVars = makeSet(ns.lexicalTypeVariables);
  
  sigDefs <- addNewLexicalTyVars(top.grammarName, top.location, allLexicalTyVars);
}

aspect production productionSignature
top::ProductionSignature ::= cl::ConstraintList '=>' lhs::ProductionLHS '::=' rhs::ProductionRHS 
{
  top.lexicalTypeVariables := makeSet(cl.lexicalTypeVariables ++ lhs.lexicalTypeVariables ++ rhs.lexicalTypeVariables);
}

propagate lexicalTypeVariables on ProductionLHS, ProductionRHS excluding productionRHSCons;

aspect production productionRHSCons
top::ProductionRHS ::= h::ProductionRHSElem t::ProductionRHS
{
  top.lexicalTypeVariables := makeSet(h.lexicalTypeVariables ++ t.lexicalTypeVariables);
}

