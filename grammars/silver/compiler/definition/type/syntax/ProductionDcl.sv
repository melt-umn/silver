grammar silver:compiler:definition:type:syntax;

attribute lexicalTypeVariables, lexicalTyVarKinds occurs on ProductionSignature, ProductionLHS, ProductionRHS, ProductionRHSElem;

flowtype lexicalTypeVariables {decorate} on ProductionSignature, ProductionLHS, ProductionRHS, ProductionRHSElem;

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name d::ProductionImplements ns::ProductionSignature body::ProductionBody
{
  production attribute allLexicalTyVars :: [String];
  allLexicalTyVars = nub(ns.lexicalTypeVariables);
  
  sigDefs <- addNewLexicalTyVars(top.grammarName, ns.lexicalTyVarKinds, allLexicalTyVars);
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

--aspect production lambdaRHSCons
--top::ProductionRHS ::= h::LambdaRHSElem t::LambdaRHS
--{
--  top.lexicalTypeVariables := nub(h.lexicalTypeVariables ++ t.lexicalTypeVariables);
--}

