grammar silver:compiler:definition:core;

concrete production dispatchSigDcl
top::AGDcl ::= 'dispatch' id::Name '=' sig::DispatchSignature ';'
{
  top.unparse = "dispatch " ++ id.unparse ++ " = " ++ sig.unparse ++ ";";

  production fName :: String = top.grammarName ++ ":" ++ id.name;
  sig.signatureName = fName;
  sig.env = newScopeEnv(sig.defs, top.env);

  top.errors <- 
    if length(getTypeDclAll(fName, top.env)) > 1 
    then [errFromOrigin(id, "Type '" ++ fName ++ "' is already bound.")]
    else [];

  top.errors <-
    if isLower(substring(0,1,id.name))
    then [errFromOrigin(id, "Types must be capitalized. Invalid dispatch name " ++ id.name)]
    else [];
}

nonterminal DispatchSignature with config, grammarName, defs, env, unparse, errors, signatureName, namedSignature;
nonterminal DispatchRHS with config, grammarName, defs, env, unparse, errors, inputElements, elementCount;
nonterminal DispatchRHSElem with config, grammarName, defs, env, unparse, errors, inputElements, deterministicCount;
propagate config, grammarName, defs, env, errors on DispatchSignature, DispatchRHS, DispatchRHSElem;

concrete production dispatchSignature
top::DispatchSignature ::= lhs::ProductionLHS '::=' rhs::DispatchRHS
{
  top.unparse = lhs.unparse ++ " ::= " ++ rhs.unparse;
  top.namedSignature =
    namedSignature(
      top.signatureName,
      nilContext(),
      foldNamedSignatureElements(rhs.inputElements),
      lhs.outputElement,
      foldNamedSignatureElements(annotationsForNonterminal(lhs.outputElement.typerep, top.env)));
  lhs.implementedSig = nothing();
}

concrete production dispatchRHSNil
top::DispatchRHS ::=
{
  top.unparse = "";

  top.inputElements = [];
  top.elementCount = 0;
}

concrete production dispatchRHSCons
top::DispatchRHS ::= h::DispatchRHSElem t::DispatchRHS
{
  top.unparse = h.unparse ++ " " ++ t.unparse;

  top.inputElements = h.inputElements ++ t.inputElements;
  top.elementCount = 1 + t.elementCount;
  h.deterministicCount = t.elementCount;
}

concrete production dispatchRHSElem
top::DispatchRHSElem ::= ms::MaybeShared id::Name '::' t::TypeExpr
{
  top.unparse = ms.unparse ++ id.unparse ++ "::" ++ t.unparse;

  top.inputElements = [namedSignatureElement(id.name, t.typerep, ms.elementShared)];

  top.defs <- [childDef(top.grammarName, id.nameLoc, id.name, t.typerep)];

  top.errors <-
    if length(getValueDclInScope(id.name, top.env)) > 1 
    then [errFromOrigin(id, "Value '" ++ id.name ++ "' is already bound.")]
    else [];
}

concrete production dispatchRHSElemType
top::DispatchRHSElem ::= ms::MaybeShared t::TypeExpr
{
  top.unparse = t.unparse;

  forwards to dispatchRHSElem(@ms, name("_G_" ++ toString(top.deterministicCount)), '::', @t);
}

nonterminal MaybeShared with unparse, elementShared;
concrete productions top::MaybeShared
| '@'  { top.unparse = "@"; top.elementShared = true; }
|      { top.unparse = "";  top.elementShared = false; }
