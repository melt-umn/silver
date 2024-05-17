grammar silver:compiler:definition:core;

tracked nonterminal AspectProductionSignature with config, grammarName, env, unparse, errors, defs, realSignature, namedSignature, signatureName;
tracked nonterminal AspectProductionLHS with config, grammarName, env, unparse, errors, defs, outputElement, realSignature;

tracked nonterminal AspectFunctionSignature with config, grammarName, env, unparse, errors, defs, realSignature, namedSignature, signatureName;
tracked nonterminal AspectFunctionLHS with config, grammarName, env, unparse, errors, defs, realSignature, outputElement;

tracked nonterminal AspectRHS with config, grammarName, env, unparse, errors, defs, inputElements, realSignature;
tracked nonterminal AspectRHSElem with config, grammarName, env, unparse, errors, defs, realSignature, inputElements, deterministicCount;

flowtype forward {realSignature, grammarName, env, flowEnv} on AspectProductionSignature, AspectProductionLHS, AspectFunctionSignature, AspectFunctionLHS, AspectRHS;
flowtype forward {deterministicCount, realSignature, grammarName, env, flowEnv} on AspectRHSElem;

{--
 - The signature elements from the fun/produciton being aspected.
 -}
inherited attribute realSignature :: [NamedSignatureElement];

propagate config, grammarName, env, grammarDependencies, errors
  on AspectProductionSignature, AspectProductionLHS, AspectFunctionSignature, AspectFunctionLHS, AspectRHS, AspectRHSElem;

concrete production aspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody
{
  top.unparse = "aspect production " ++ id.unparse ++ "\n" ++ ns.unparse ++ "\n" ++ body.unparse;
  id.env = top.env;

  top.defs := 
    if null(body.productionAttributes) then []
    else [prodOccursDef(top.grammarName, id.nameLoc, namedSig, body.productionAttributes)];

  production namedSig :: NamedSignature = ns.namedSignature;

  production attribute realSig :: NamedSignature;
  realSig = if id.lookupValue.found
            then id.lookupValue.dcl.namedSignature.freshenNamedSignature
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
      \ c::Context -> c.contextSigDefs(realSig, top.grammarName, id.nameLoc),
      realSig.contexts);
  local contextSigOccursDefs::[OccursDclInfo] =
    flatMap(
      \ c::Context -> c.contextSigOccursDefs(realSig, top.grammarName, id.nameLoc),
      realSig.contexts);
  local sourceGrammar::String =
    if id.lookupValue.found
    then id.lookupValue.dcl.sourceGrammar
    -- Default since we need to supply something, in case the production doesn't exist.
    else top.grammarName;

  body.env =
    occursEnv(contextSigOccursDefs,
      newScopeEnv(body.defs ++ sigDefs ++ contextSigDefs,
        newScopeEnv(prodAtts, top.env)));
  body.frame = aspectProductionContext(namedSig, myFlowGraph, sourceGrammar=sourceGrammar); -- graph from flow:env
} action {
  insert semantic token IdFnProd_t at id.nameLoc;
  sigNames = [];
}

concrete production aspectFunctionDcl
top::AGDcl ::= 'aspect' 'function' id::QName ns::AspectFunctionSignature body::ProductionBody
{
  top.unparse = "aspect function " ++ id.unparse ++ "\n" ++ ns.unparse ++ "\n" ++ body.unparse;
  id.env = top.env;

  top.defs := 
    if null(body.productionAttributes) then []
    else [prodOccursDef(top.grammarName, id.nameLoc, namedSig, body.productionAttributes)];

  production namedSig :: NamedSignature = ns.namedSignature;

  production attribute realSig :: NamedSignature;
  realSig = if id.lookupValue.found
            then id.lookupValue.dcl.namedSignature.freshenNamedSignature
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
      \ c::Context -> c.contextSigDefs(realSig, top.grammarName, id.nameLoc),
      realSig.contexts);
  local contextSigOccursDefs::[OccursDclInfo] =
    flatMap(
      \ c::Context -> c.contextSigOccursDefs(realSig, top.grammarName, id.nameLoc),
      realSig.contexts);
  local sourceGrammar::String =
    if id.lookupValue.found
    then id.lookupValue.dcl.sourceGrammar
    -- Default since we need to supply something, in case the production doesn't exist.
    else top.grammarName;

  body.env =
    occursEnv(contextSigOccursDefs,
      newScopeEnv(body.defs ++ sigDefs ++ contextSigDefs,
        newScopeEnv(prodAtts, top.env)));
  body.frame = aspectFunctionContext(namedSig, myFlowGraph, sourceGrammar=sourceGrammar); -- graph from flow:env
} action {
  insert semantic token IdFnProd_t at id.nameLoc;
  sigNames = [];
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
} action {
  sigNames = foldNamedSignatureElements(lhs.outputElement :: rhs.inputElements).elementNames;
}

concrete production aspectProductionLHSNone
top::AspectProductionLHS ::= '_'
{
  top.unparse = "_";
  forwards to aspectProductionLHSId(name("p_top"));
}

concrete production aspectProductionLHSId
top::AspectProductionLHS ::= id::Name
{
  top.unparse = id.unparse;

  nondecorated production attribute rType :: Type;
  rType = if null(top.realSignature) then errorType() else head(top.realSignature).typerep;

  forwards to aspectProductionLHSFull(@id, rType);
} action {
  insert semantic token IdSigNameDcl_t at id.nameLoc;
}

concrete production aspectProductionLHSTyped
top::AspectProductionLHS ::= id::Name '::' t::TypeExpr
{
  top.unparse = id.unparse;
  propagate env, grammarName, config;

  top.errors <- t.errors;
  
  forwards to aspectProductionLHSFull(@id, t.typerep);
} action {
  insert semantic token IdSigNameDcl_t at id.nameLoc;
}

abstract production aspectProductionLHSFull
top::AspectProductionLHS ::= id::Name t::Type
{
  top.unparse = id.unparse ++ "::" ++ prettyType(new(t));

  production attribute fName :: String;
  fName = if null(top.realSignature) then id.name else head(top.realSignature).elementName;
  nondecorated production attribute rType :: Type;
  rType = if null(top.realSignature) then errorType() else head(top.realSignature).elementDclType;

  top.outputElement = namedSignatureElement(id.name, new(t), false);
  
  top.defs := [aliasedLhsDef(top.grammarName, id.nameLoc, fName, performSubstitution(new(t), top.upSubst), id.name)];

  top.errors <- if length(getValueDclInScope(id.name, top.env)) > 1
                then [errFromOrigin(id, "Value '" ++ fName ++ "' is already bound.")]
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

  nondecorated production attribute rType :: Type;
  rType = if null(top.realSignature) then errorType() else head(top.realSignature).typerep;
  production shared :: Boolean = !null(top.realSignature) && head(top.realSignature).elementShared;

  forwards to aspectRHSElemFull(shared, name("p_" ++ toString(top.deterministicCount)), rType);
}

concrete production aspectRHSElemIdConcrete
top::AspectRHSElem ::= id::Name
{
  -- aspectRHSElemId is used by extensions, so avoid giving the warning there:
  top.errors <- [wrnFromOrigin(top, "Giving just a name '" ++ id.name ++ "' is deprecated in aspect signature. Please explicitly use a name and type.")];
  
  forwards to aspectRHSElemId(@id);
} action {
  insert semantic token IdSigNameDcl_t at id.nameLoc;
}

abstract production aspectRHSElemId
top::AspectRHSElem ::= id::Name
{
  top.unparse = id.unparse;

  nondecorated production attribute rType :: Type;
  rType = if null(top.realSignature) then errorType() else head(top.realSignature).elementDclType;
  production shared :: Boolean = !null(top.realSignature) && head(top.realSignature).elementShared;

  forwards to aspectRHSElemFull(shared, @id, rType);
}

concrete production aspectRHSElemTyped
top::AspectRHSElem ::= id::Name '::' t::TypeExpr
{
  top.unparse = id.unparse ++ "::" ++ t.unparse;
  propagate env, grammarName, config;
  
  top.errors <- t.errors;

  forwards to aspectRHSElemFull(false, @id, t.typerep);
} action {
  insert semantic token IdSigNameDcl_t at id.nameLoc;
}

concrete production aspectRHSElemSharedTyped
top::AspectRHSElem ::= '@' id::Name '::' t::TypeExpr
{
  top.unparse = "@" ++ id.unparse ++ "::" ++ t.unparse;
  propagate env, grammarName, config;
  
  top.errors <- t.errors;

  forwards to aspectRHSElemFull(true, @id, t.typerep);
} action {
  insert semantic token IdSigNameDcl_t at id.nameLoc;
}

abstract production aspectRHSElemFull
top::AspectRHSElem ::= shared::Boolean id::Name t::Type
{
  top.unparse = (if shared then "@" else "") ++ id.unparse ++ "::" ++ prettyType(new(t));

  production attribute fName :: String;
  fName = if null(top.realSignature) then id.name else head(top.realSignature).elementName;
  nondecorated production attribute rType :: Type;
  rType = if null(top.realSignature) then errorType() else head(top.realSignature).elementDclType;

  top.inputElements = [namedSignatureElement(id.name, new(t), shared)];

  top.defs := [aliasedChildDef(top.grammarName, id.nameLoc, fName, performSubstitution(new(t), top.upSubst), shared, id.name)];

  top.errors <- if length(getValueDclInScope(id.name, top.env)) > 1
                then [errFromOrigin(id, "Value '" ++ id.name ++ "' is already bound.")]
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
  nondecorated production attribute rType :: Type;
  rType = if null(top.realSignature) then errorType() else head(top.realSignature).typerep;

  top.outputElement = namedSignatureElement(fName, t.typerep, false);
  
  -- TODO: this needs thinking. is it broken? maybe __return? or wait, it's doing that automatically isnt it...
  top.defs := [aliasedLhsDef(top.grammarName, getParsedOriginLocationOrFallback(t), fName, performSubstitution(t.typerep, top.upSubst), fName)];
}
