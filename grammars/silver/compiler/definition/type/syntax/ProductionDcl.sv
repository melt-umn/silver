grammar silver:compiler:definition:type:syntax;

attribute lexicalTypeVariables, lexicalTyVarKinds occurs on ProductionSignature, ProductionLHS, ProductionRHS, ProductionRHSElem;

flowtype lexicalTypeVariables {env} on ProductionSignature, ProductionLHS, ProductionRHS;
flowtype lexicalTypeVariables {deterministicCount, env} on ProductionRHSElem;

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  production attribute allLexicalTyVars :: [String];
  allLexicalTyVars = nub(ns.lexicalTypeVariables);
  
  sigDefs <- addNewLexicalTyVars(top.grammarName, top.location, ns.lexicalTyVarKinds, allLexicalTyVars);
}

propagate lexicalTyVarKinds on ProductionSignature, ProductionLHS, ProductionRHS, ProductionRHSElem;

aspect production productionSignature
top::ProductionSignature ::= cl::ConstraintList '=>' lhs::ProductionLHS '::=' rhs::ProductionRHS 
{
  top.lexicalTypeVariables := nub(cl.lexicalTypeVariables ++ lhs.lexicalTypeVariables ++ rhs.lexicalTypeVariables);
}

propagate lexicalTypeVariables on ProductionLHS, ProductionRHS, ProductionRHSElem excluding productionRHSCons;

aspect production productionRHSCons
top::ProductionRHS ::= h::ProductionRHSElem t::ProductionRHS
{
  top.lexicalTypeVariables := nub(h.lexicalTypeVariables ++ t.lexicalTypeVariables);
}

