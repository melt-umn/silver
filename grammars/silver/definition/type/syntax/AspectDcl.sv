grammar silver:definition:type:syntax;

attribute lexicalTypeVariables occurs on AspectProductionSignature, AspectProductionLHS, AspectRHS, AspectRHSElem, AspectFunctionSignature, AspectFunctionLHS;

flowtype lexicalTypeVariables {realSignature, env} on AspectProductionSignature, AspectProductionLHS, AspectRHS, AspectFunctionSignature, AspectFunctionLHS;
flowtype lexicalTypeVariables {deterministicCount, realSignature, env} on AspectRHSElem;

function addNewLexicalTyVars_ActuallyVariables
[Def] ::= gn::String sl::Location l::[String]
{
  return if null(l) then []
         else aspectLexTyVarDef(gn, sl, head(l), freshTyVar(), 0) ::
                  addNewLexicalTyVars_ActuallyVariables(gn, sl, tail(l));
}

-- This binds variables that appear in the signature to type variables, rather than skolem constants
-- as in productions declarations.  They will be unified with the "real" type, and therefore
-- will become those skolem constants.

aspect production aspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody 
{
  production attribute allLexicalTyVars :: [String];
  allLexicalTyVars = makeSet(ns.lexicalTypeVariables);
  
  sigDefs <- addNewLexicalTyVars_ActuallyVariables(top.grammarName, top.location, allLexicalTyVars);
  -- TODO sigDefs <- realSig.contexts as defs
}

aspect production aspectFunctionDcl
top::AGDcl ::= 'aspect' 'function' id::QName ns::AspectFunctionSignature body::ProductionBody 
{
  production attribute allLexicalTyVars :: [String];
  allLexicalTyVars = makeSet(ns.lexicalTypeVariables);
  
  sigDefs <- addNewLexicalTyVars_ActuallyVariables(top.grammarName, top.location, allLexicalTyVars);
  -- TODO sigDefs <- realSig.contexts as defs
}

propagate lexicalTypeVariables on AspectProductionLHS, AspectFunctionLHS, AspectRHS, AspectRHSElem excluding aspectRHSElemCons;

aspect production aspectProductionSignature
top::AspectProductionSignature ::= lhs::AspectProductionLHS '::=' rhs::AspectRHS
{
  top.lexicalTypeVariables := makeSet(lhs.lexicalTypeVariables ++ rhs.lexicalTypeVariables);
}

aspect production aspectRHSElemCons
top::AspectRHS ::= h::AspectRHSElem t::AspectRHS
{
  top.lexicalTypeVariables := makeSet(h.lexicalTypeVariables ++ t.lexicalTypeVariables);
}

aspect production aspectProductionLHSTyped
top::AspectProductionLHS ::= id::Name '::' t::TypeExpr
{
  propagate lexicalTypeVariables;
}

aspect production aspectRHSElemTyped
top::AspectRHSElem ::= id::Name '::' t::TypeExpr
{
  propagate lexicalTypeVariables;
}

aspect production aspectFunctionSignature
top::AspectFunctionSignature ::= lhs::AspectFunctionLHS '::=' rhs::AspectRHS 
{
  top.lexicalTypeVariables := makeSet(lhs.lexicalTypeVariables ++ rhs.lexicalTypeVariables);
}
