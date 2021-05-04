grammar silver:compiler:definition:core;

nonterminal ProductionSignature with config, grammarName, env, location, unparse, errors, defs, constraintDefs, namedSignature, signatureName;
nonterminal ProductionLHS with config, grammarName, env, location, unparse, errors, defs, outputElement;
nonterminal ProductionRHS with config, grammarName, env, location, unparse, errors, defs, inputElements;
nonterminal ProductionRHSElem with config, grammarName, env, location, unparse, errors, defs, inputElements, deterministicCount;

flowtype forward {env} on ProductionSignature, ProductionLHS, ProductionRHS;
flowtype forward {deterministicCount, env} on ProductionRHSElem;

propagate errors on ProductionSignature, ProductionLHS, ProductionRHS, ProductionRHSElem;
propagate defs on ProductionRHS;

{--
 - Used to help give names to children, when names are omitted.
 -}
inherited attribute deterministicCount :: Integer;

{--
 - Given to signature syntax, so as to construct a named signature representation.
 -}
inherited attribute signatureName :: String;

{--
 - Defs from the constraint list are passed seperately from the rest of the signature defs,
 - to avoid an infinite recursion.
 -}
synthesized attribute constraintDefs::[Def];

concrete production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  top.unparse = "abstract production " ++ id.unparse ++ "\n" ++ ns.unparse ++ "\n" ++ body.unparse; 

  production fName :: String = top.grammarName ++ ":" ++ id.name;
  production namedSig :: NamedSignature = ns.namedSignature;

  top.defs := prodDef(top.grammarName, id.location, namedSig, length(body.uniqueSignificantExpression) > 0) ::
    if null(body.productionAttributes) then []
    else [prodOccursDef(top.grammarName, id.location, namedSig, body.productionAttributes)];

  top.errors <-
    if length(getValueDclAll(fName, top.env)) > 1
    then [err(id.location, "Value '" ++ fName ++ "' is already bound.")]

    -- TODO: Narrow this down to just a list of productions of the same nonterminal before deciding to error.
    else if length(getValueDclAll(id.name, top.env)) > 1
    then [err(top.location, "Production " ++ id.name ++ " shares a name with another production from an imported grammar. Either this production is meant to be an aspect, or you should use 'import ... with " ++ id.name ++ " as ...' to change the other production's apparent name.")]
    else [];
  
  top.errors <-
    if length(body.uniqueSignificantExpression) > 1
    then [err(top.location, "Production '" ++ id.name ++ "' has more than one forward declaration.")]
    else [];

  top.errors <-
    if isLower(substring(0,1,id.name)) then []
    else [wrn(id.location, s"(future) ${id.name}: productions may be required to begin with a lower-case letter.")];

  production attribute sigDefs :: [Def] with ++;
  sigDefs := ns.defs;

  ns.signatureName = fName;
  ns.env = newScopeEnv(sigDefs, top.env);

  local attribute prodAtts :: [Def];
  prodAtts = defsFromPADcls(getProdAttrs(fName, top.env), namedSig);

  body.env = newScopeEnv(body.defs ++ sigDefs ++ ns.constraintDefs, newScopeEnv(prodAtts, top.env));
  body.frame = productionContext(namedSig, myFlowGraph, sourceGrammar=top.grammarName); -- graph from flow:env
}

concrete production productionSignature
top::ProductionSignature ::= cl::ConstraintList '=>' lhs::ProductionLHS '::=' rhs::ProductionRHS
{
  top.unparse = s"${cl.unparse} => ${lhs.unparse} ::= ${rhs.unparse}";
  
  cl.constraintPos = signaturePos(top.namedSignature);

  top.defs := lhs.defs ++ rhs.defs;
  top.constraintDefs = cl.defs;
  top.namedSignature =
    namedSignature(
      top.signatureName,
      foldContexts(cl.contexts),
      foldNamedSignatureElements(rhs.inputElements),
      lhs.outputElement,
      foldNamedSignatureElements(annotationsForNonterminal(lhs.outputElement.typerep, top.env)));
}

concrete production productionSignatureNoCL
top::ProductionSignature ::= lhs::ProductionLHS '::=' rhs::ProductionRHS
{
  top.unparse = s"${lhs.unparse} ::= ${rhs.unparse}";
  
  forwards to productionSignature(nilConstraint(location=top.location), '=>', lhs, $2, rhs, location=top.location);
}

concrete production productionLHS
top::ProductionLHS ::= id::Name '::' t::TypeExpr
{
  top.unparse = id.unparse ++ "::" ++ t.unparse;

  top.outputElement = namedSignatureElement(id.name, t.typerep);

  top.defs := [lhsDef(top.grammarName, t.location, id.name, t.typerep)];

  top.errors <-
    if length(getValueDclInScope(id.name, top.env)) > 1
    then [err(id.location, "Value '" ++ id.name ++ "' is already bound.")]
    else [];
}

concrete production productionRHSNil
top::ProductionRHS ::=
{
  top.unparse = "";

  top.inputElements = [];
}

concrete production productionRHSCons
top::ProductionRHS ::= h::ProductionRHSElem t::ProductionRHS
{
  top.unparse = h.unparse ++ " " ++ t.unparse;

  top.inputElements = h.inputElements ++ t.inputElements;
  h.deterministicCount = length(t.inputElements);
}

concrete production productionRHSElem
top::ProductionRHSElem ::= id::Name '::' t::TypeExpr
{
  top.unparse = id.unparse ++ "::" ++ t.unparse;

  top.inputElements = [namedSignatureElement(id.name, t.typerep)];

  top.defs := [childDef(top.grammarName, t.location, id.name, t.typerep)];

  top.errors <-
    if length(getValueDclInScope(id.name, top.env)) > 1 
    then [err(id.location, "Value '" ++ id.name ++ "' is already bound.")]
    else [];
}

concrete production productionRHSElemType
top::ProductionRHSElem ::= t::TypeExpr
{
  top.unparse = t.unparse;

  forwards to productionRHSElem(name("_G_" ++ toString(top.deterministicCount), t.location), '::', t, location=top.location);
}

