grammar silver:compiler:definition:core;

nonterminal AspectProductionSignature with config, grammarName, env, location, unparse, errors, defs, realSignature, namedSignature, signatureName;
nonterminal AspectProductionLHS with config, grammarName, env, location, unparse, errors, defs, outputElement, realSignature;

nonterminal AspectFunctionSignature with config, grammarName, env, location, unparse, errors, defs, realSignature, namedSignature, signatureName;
nonterminal AspectFunctionLHS with config, grammarName, env, location, unparse, errors, defs, realSignature, outputElement;

nonterminal AspectRHS with config, grammarName, env, location, unparse, errors, defs, inputElements, realSignature;
nonterminal AspectRHSElem with config, grammarName, env, location, unparse, errors, defs, realSignature, inputElements, deterministicCount;

flowtype forward {realSignature, env} on AspectProductionSignature, AspectProductionLHS, AspectFunctionSignature, AspectFunctionLHS, AspectRHS;
flowtype forward {deterministicCount, realSignature, env} on AspectRHSElem;

{--
 - The signature elements from the fun/produciton being aspected.
 -}
autocopy attribute realSignature :: [NamedSignatureElement];

propagate errors on AspectProductionSignature, AspectProductionLHS, AspectFunctionSignature, AspectFunctionLHS, AspectRHS, AspectRHSElem;

concrete production aspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody 
{
  top.unparse = "aspect production " ++ id.unparse ++ "\n" ++ ns.unparse ++ "\n" ++ body.unparse;

  top.defs := 
    if null(body.productionAttributes) then []
    else [prodOccursDef(top.grammarName, id.location, namedSig, body.productionAttributes)];

  production namedSig :: NamedSignature = ns.namedSignature;

  production attribute realSig :: NamedSignature;
  realSig = if id.lookupValue.found
            then freshenNamedSignature(id.lookupValue.dcl.namedSignature)
            else bogusNamedSignature();

  -- Making sure we're aspecting a production is taken care of by type checking.

  top.errors <- id.lookupValue.errors;

  production attribute sigDefs :: [Def] with ++;
  sigDefs := ns.defs;

  ns.signatureName = id.lookupValue.fullName;
  ns.env = newScopeEnv(sigDefs, top.env);  
  ns.realSignature = if null(id.lookupValue.dcls) then [] else [realSig.outputElement] ++ realSig.inputElements;

  local attribute prodAtts :: [Def];
  prodAtts = if id.lookupValue.found
             then defsFromPADcls(getProdAttrs(id.lookupValue.fullName, top.env), namedSig)
             else [];

  local contextSigDefs::[Def] =
    flatMap(
      \ c::Context -> c.contextSigDefs(realSig, top.grammarName, top.location),
      realSig.contexts);
  local contextSigOccursDefs::[OccursDclInfo] =
    flatMap(
      \ c::Context -> c.contextSigOccursDefs(realSig, top.grammarName, top.location),
      realSig.contexts);

  body.env =
    occursEnv(contextSigOccursDefs,
      newScopeEnv(body.defs ++ sigDefs ++ contextSigDefs,
        newScopeEnv(prodAtts, top.env)));
  body.frame = aspectProductionContext(namedSig, myFlowGraph, sourceGrammar=id.lookupValue.dcl.sourceGrammar); -- graph from flow:env
}

concrete production aspectFunctionDcl
top::AGDcl ::= 'aspect' 'function' id::QName ns::AspectFunctionSignature body::ProductionBody 
{
  top.unparse = "aspect function " ++ id.unparse ++ "\n" ++ ns.unparse ++ "\n" ++ body.unparse;

  top.defs := 
    if null(body.productionAttributes) then []
    else [prodOccursDef(top.grammarName, id.location, namedSig, body.productionAttributes)];

  production namedSig :: NamedSignature = ns.namedSignature;

  production attribute realSig :: NamedSignature;
  realSig = if id.lookupValue.found
            then freshenNamedSignature(id.lookupValue.dcl.namedSignature)
            else bogusNamedSignature();

  -- Making sure we're aspecting a function is taken care of by type checking.

  top.errors <- id.lookupValue.errors;

  production attribute sigDefs :: [Def] with ++;
  sigDefs := ns.defs;

  ns.signatureName = id.lookupValue.fullName;
  ns.env = newScopeEnv(sigDefs, top.env);
  ns.realSignature = if null(id.lookupValue.dcls) then [] else [realSig.outputElement] ++ realSig.inputElements;

  local attribute prodAtts :: [Def];
  prodAtts = if id.lookupValue.found
             then defsFromPADcls(getProdAttrs(id.lookupValue.fullName, top.env), namedSig)
             else [];

  local contextSigDefs::[Def] =
    flatMap(
      \ c::Context -> c.contextSigDefs(realSig, top.grammarName, top.location),
      realSig.contexts);
  local contextSigOccursDefs::[OccursDclInfo] =
    flatMap(
      \ c::Context -> c.contextSigOccursDefs(realSig, top.grammarName, top.location),
      realSig.contexts);

  body.env =
    occursEnv(contextSigOccursDefs,
      newScopeEnv(body.defs ++ sigDefs ++ contextSigDefs,
        newScopeEnv(prodAtts, top.env)));
  body.frame = aspectFunctionContext(namedSig, myFlowGraph, sourceGrammar=id.lookupValue.dcl.sourceGrammar); -- graph from flow:env
}

concrete production aspectProductionSignature
top::AspectProductionSignature ::= lhs::AspectProductionLHS '::=' rhs::AspectRHS 
{
  top.unparse = lhs.unparse ++ " ::= " ++ rhs.unparse;

  propagate defs;

  top.namedSignature =
    namedSignature(
      top.signatureName, nilContext(),
      foldNamedSignatureElements(rhs.inputElements),
      lhs.outputElement,
      foldNamedSignatureElements(annotationsForNonterminal(lhs.outputElement.typerep, top.env)));

  lhs.realSignature = if null(top.realSignature) then [] else [head(top.realSignature)];
  rhs.realSignature = if null(top.realSignature) then [] else tail(top.realSignature);
}

concrete production aspectProductionLHSNone
top::AspectProductionLHS ::= '_'
{
  top.unparse = "_";
  forwards to aspectProductionLHSId(name("p_top", top.location), location=top.location);
}

concrete production aspectProductionLHSId
top::AspectProductionLHS ::= id::Name
{
  top.unparse = id.unparse;

  production attribute rType :: Type;
  rType = if null(top.realSignature) then errorType() else head(top.realSignature).typerep;

  forwards to aspectProductionLHSFull(id, rType, location=top.location);
}

concrete production aspectProductionLHSTyped
top::AspectProductionLHS ::= id::Name '::' t::TypeExpr
{
  top.unparse = id.unparse;

  top.errors <- t.errors;
  
  forwards to aspectProductionLHSFull(id, t.typerep, location=top.location);
}

abstract production aspectProductionLHSFull
top::AspectProductionLHS ::= id::Name t::Type
{
  top.unparse = id.unparse ++ "::" ++ prettyType(t);

  production attribute fName :: String;
  fName = if null(top.realSignature) then id.name else head(top.realSignature).elementName;
  production attribute rType :: Type;
  rType = if null(top.realSignature) then errorType() else head(top.realSignature).typerep;

  top.outputElement = namedSignatureElement(id.name, t);
  
  top.defs := [aliasedLhsDef(top.grammarName, id.location, fName, performSubstitution(t, top.upSubst), id.name)];

  top.errors <- if length(getValueDclInScope(id.name, top.env)) > 1
                then [err(id.location, "Value '" ++ fName ++ "' is already bound.")]
                else [];
}

concrete production aspectRHSElemNil
top::AspectRHS ::= 
{
  top.unparse = "";

  propagate defs;
  top.inputElements = [];
}

concrete production aspectRHSElemCons
top::AspectRHS ::= h::AspectRHSElem t::AspectRHS
{
  top.unparse = h.unparse ++ " " ++ t.unparse;

  propagate defs;

  top.inputElements = h.inputElements ++ t.inputElements;

  h.deterministicCount = length(t.inputElements);
  h.realSignature = if null(top.realSignature) then [] else [head(top.realSignature)];
  t.realSignature = if null(top.realSignature) then [] else tail(top.realSignature);
}

concrete production aspectRHSElemNone
top::AspectRHSElem ::= '_'
{
  top.unparse = "_";

  production attribute rType :: Type;
  rType = if null(top.realSignature) then errorType() else head(top.realSignature).typerep;

  forwards to aspectRHSElemFull(
    name("p_" ++ toString(top.deterministicCount), $1.location),
    rType,
    location=top.location);
}

concrete production aspectRHSElemId
top::AspectRHSElem ::= id::Name
{
  top.unparse = id.unparse;

  production attribute rType :: Type;
  rType = if null(top.realSignature) then errorType() else head(top.realSignature).typerep;

  top.errors <- [wrn(top.location, "Giving just a name '" ++ id.name ++ "' is deprecated in aspect signature. Please explicitly use a name and type.")];
  
  forwards to aspectRHSElemFull(id, rType, location=top.location);
}

concrete production aspectRHSElemTyped
top::AspectRHSElem ::= id::Name '::' t::TypeExpr
{
  top.unparse = id.unparse ++ "::" ++ t.unparse;
  
  top.errors <- t.errors;

  forwards to aspectRHSElemFull(id, t.typerep, location=top.location);
}

abstract production aspectRHSElemFull
top::AspectRHSElem ::= id::Name t::Type
{
  top.unparse = id.unparse ++ "::" ++ prettyType(t);

  production attribute fName :: String;
  fName = if null(top.realSignature) then id.name else head(top.realSignature).elementName;
  production attribute rType :: Type;
  rType = if null(top.realSignature) then errorType() else head(top.realSignature).typerep;

  top.inputElements = [namedSignatureElement(id.name, t)];

  top.defs := [aliasedChildDef(top.grammarName, id.location, fName, performSubstitution(t, top.upSubst), id.name)];

  top.errors <- if length(getValueDclInScope(id.name, top.env)) > 1
                then [err(id.location, "Value '" ++ id.name ++ "' is already bound.")]
                else [];
}

concrete production aspectFunctionSignature
top::AspectFunctionSignature ::= lhs::AspectFunctionLHS '::=' rhs::AspectRHS 
{
  top.unparse = lhs.unparse ++ " ::= " ++ rhs.unparse;

  propagate defs;

  top.namedSignature =
    namedSignature(
      top.signatureName, nilContext(),
      foldNamedSignatureElements(rhs.inputElements),
      lhs.outputElement,
      -- For the moment, functions do not have named parameters (hence, nilNamedSignatureElement)
      nilNamedSignatureElement());

  lhs.realSignature = if null(top.realSignature) then [] else [head(top.realSignature)];
  rhs.realSignature = if null(top.realSignature) then [] else tail(top.realSignature);
}

concrete production functionLHSType
top::AspectFunctionLHS ::= t::TypeExpr
{
  top.unparse = t.unparse;

  production attribute fName :: String;
  fName = if null(top.realSignature) then "_NULL_" else head(top.realSignature).elementName;
  production attribute rType :: Type;
  rType = if null(top.realSignature) then errorType() else head(top.realSignature).typerep;

  top.outputElement = namedSignatureElement(fName, t.typerep);
  
  -- TODO: this needs thinking. is it broken? maybe __return? or wait, it's doing that automatically isnt it...
  top.defs := [aliasedLhsDef(top.grammarName, t.location, fName, performSubstitution(t.typerep, top.upSubst), fName)];
}
