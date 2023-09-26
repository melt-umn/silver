grammar silver:compiler:definition:type:syntax;

attribute lexicalTypeVariables, lexicalTyVarKinds occurs on
  AspectProductionSignature, AspectProductionLHS, AspectRHS, AspectRHSElem, AspectFunctionSignature, AspectFunctionLHS;

flowtype lexicalTypeVariables {realSignature, env, flowEnv, grammarName} on AspectProductionSignature, AspectProductionLHS, AspectRHS, AspectFunctionSignature, AspectFunctionLHS;
flowtype lexicalTypeVariables {realSignature, env, flowEnv, grammarName, deterministicCount} on AspectRHSElem;

function addNewLexicalTyVars_ActuallyVariables
[Def] ::= gn::String sl::Location lk::[Pair<String Kind>] l::[String]
{
  return map(\ n::String -> aspectLexTyVarDef(gn, sl, n, freshTyVarNamed(fromMaybe(starKind(), lookup(n, lk)), n)), l);
}

-- This binds variables that appear in the signature to type variables, rather than skolem constants
-- as in productions declarations.  They will be unified with the "real" type, and therefore
-- will become those skolem constants.

aspect production aspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody 
{
  production attribute allLexicalTyVars :: [String];
  allLexicalTyVars = nub(ns.lexicalTypeVariables);
  
  sigDefs <- addNewLexicalTyVars_ActuallyVariables(top.grammarName, top.location, ns.lexicalTyVarKinds, allLexicalTyVars);
}

aspect production aspectFunctionDcl
top::AGDcl ::= 'aspect' 'function' id::QName ns::AspectFunctionSignature body::ProductionBody 
{
  production attribute allLexicalTyVars :: [String];
  allLexicalTyVars = nub(ns.lexicalTypeVariables);
  
  sigDefs <- addNewLexicalTyVars_ActuallyVariables(top.grammarName, top.location, ns.lexicalTyVarKinds, allLexicalTyVars);
}

propagate lexicalTypeVariables on AspectProductionLHS, AspectFunctionLHS, AspectRHS, AspectRHSElem excluding aspectRHSElemCons;
propagate lexicalTyVarKinds on AspectProductionSignature, AspectFunctionSignature, AspectProductionLHS, AspectFunctionLHS, AspectRHS, AspectRHSElem;

aspect production aspectProductionSignature
top::AspectProductionSignature ::= lhs::AspectProductionLHS '::=' rhs::AspectRHS
{
  top.lexicalTypeVariables := nub(lhs.lexicalTypeVariables ++ rhs.lexicalTypeVariables);
}

aspect production aspectRHSElemCons
top::AspectRHS ::= h::AspectRHSElem t::AspectRHS
{
  top.lexicalTypeVariables := nub(h.lexicalTypeVariables ++ t.lexicalTypeVariables);
}

aspect production aspectProductionLHSTyped
top::AspectProductionLHS ::= id::Name '::' t::TypeExpr
{
  propagate lexicalTypeVariables, lexicalTyVarKinds;
}

aspect production aspectRHSElemTyped
top::AspectRHSElem ::= id::Name '::' t::TypeExpr
{
  propagate lexicalTypeVariables, lexicalTyVarKinds;
}

aspect production aspectFunctionSignature
top::AspectFunctionSignature ::= lhs::AspectFunctionLHS '::=' rhs::AspectRHS 
{
  top.lexicalTypeVariables := nub(lhs.lexicalTypeVariables ++ rhs.lexicalTypeVariables);
}
