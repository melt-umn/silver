grammar silver:definition:type:gatherfreevars;

import silver:definition:core;
import silver:definition:env;
import silver:definition:type:syntax;
import silver:util;

attribute lexicalTypeVariables occurs on ProductionSignature, ProductionLHS, ProductionRHS, ProductionRHSElem;

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  production attribute allLexicalTyVars :: [String];
  allLexicalTyVars = makeSet(ns.lexicalTypeVariables);
  
  sigDefs <- addNewLexicalTyVars(top.grammarName, top.location, allLexicalTyVars);
}

aspect production productionSignature
top::ProductionSignature ::= lhs::ProductionLHS '::=' rhs::ProductionRHS 
{
  top.lexicalTypeVariables = makeSet(lhs.lexicalTypeVariables ++ rhs.lexicalTypeVariables);
}

aspect production productionLHS
top::ProductionLHS ::= id::Name '::' t::Type
{
  top.lexicalTypeVariables = t.lexicalTypeVariables;
}

aspect production productionRHSNil
top::ProductionRHS ::= 
{
  top.lexicalTypeVariables = [];
}

aspect production productionRHSCons
top::ProductionRHS ::= h::ProductionRHSElem t::ProductionRHS
{
  top.lexicalTypeVariables = makeSet(h.lexicalTypeVariables ++ t.lexicalTypeVariables);
}

aspect production productionRHSElem
top::ProductionRHSElem ::= id::Name '::' t::Type
{
  top.lexicalTypeVariables = t.lexicalTypeVariables;
}

