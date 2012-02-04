grammar silver:definition:core;

nonterminal AspectProductionSignature with grammarName, file, env, location, pp, errors, defs, inputElements, outputElement, realSignature;
nonterminal AspectProductionLHS with grammarName, file, env, location, pp, errors, defs, outputElement, realSignature;

nonterminal AspectFunctionSignature with grammarName, file, env, location, pp, errors, defs, inputElements, outputElement, realSignature;
nonterminal AspectFunctionLHS with grammarName, file, env, location, pp, errors, defs, realSignature, outputElement;

nonterminal AspectRHS with grammarName, file, env, location, pp, errors, defs, inputElements, realSignature;
nonterminal AspectRHSElem with grammarName, file, env, location, pp, errors, defs, realSignature, inputElements;

{--
 - The signature elements from the fun/produciton being aspected.
 -}
autocopy attribute realSignature :: [NamedSignatureElement];


concrete production aspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody 
{
  top.pp = "aspect production " ++ id.pp ++ "\n" ++ ns.pp ++ "\n" ++ body.pp;
  top.location = loc(top.file, $1.line, $1.column);

  top.defs = if isEmptyOfValues(body.productionAttributes)
             then emptyDefs()
             else addPaDcl(top.grammarName, id.location, id.lookupValue.fullName,
                       namedSig.outputElement.typerep, namedSig.inputTypes,
                       body.productionAttributes,
                        emptyDefs());

  production attribute namedSig :: NamedSignature;
  namedSig = namedSignature(id.lookupValue.fullName, ns.inputElements, ns.outputElement);

  production attribute realSig :: NamedSignature;
  realSig = if null(id.lookupValue.errors)
            then id.lookupValue.dcl.namedSignature
            else bogusNamedSignature();

  -- Making sure we're aspecting a production is taken care of by type checking.

  top.errors := id.lookupValue.errors ++ ns.errors ++ body.errors;
  top.warnings := body.warnings;

  production attribute sigDefs :: Defs with appendDefs;
  sigDefs := ns.defs;

  ns.env = newScopeEnv(sigDefs, top.env);  
  ns.realSignature = if null(id.lookupValue.dcls) then [] else [realSig.outputElement] ++ realSig.inputElements;

  local attribute prodAtts :: Defs;
  prodAtts = if null(id.lookupValue.errors)
             then defsFromPADcls(getProdAttrs(id.lookupValue.fullName, top.env), namedSig)
             else emptyDefs();

  body.env = newScopeEnv(appendDefs(body.defs, sigDefs), newScopeEnv(prodAtts, top.env));
  body.signature = namedSig;
  body.blockContext = defaultContext();
  
  forwards to defaultAGDcl();
}

concrete production aspectFunctionDcl
top::AGDcl ::= 'aspect' 'function' id::QName ns::AspectFunctionSignature body::ProductionBody 
{
  top.pp = "aspect function " ++ id.pp ++ "\n" ++ ns.pp ++ "\n" ++ body.pp;
  top.location = loc(top.file, $1.line, $1.column);

  top.defs = addPaDcl(top.grammarName, id.location, id.lookupValue.fullName,
                       namedSig.outputElement.typerep, namedSig.inputTypes,
                       body.productionAttributes,
               emptyDefs());

  production attribute namedSig :: NamedSignature;
  namedSig = namedSignature(id.lookupValue.fullName, ns.inputElements, ns.outputElement);

  production attribute realSig :: NamedSignature;
  realSig = if null(id.lookupValue.errors)
            then id.lookupValue.dcl.namedSignature
            else bogusNamedSignature();

  -- Making sure we're aspecting a function is taken care of by type checking.

  top.errors := id.lookupValue.errors ++ ns.errors ++ body.errors;
  top.warnings := body.warnings;

  production attribute sigDefs :: Defs with appendDefs;
  sigDefs := ns.defs;

  ns.env = newScopeEnv(sigDefs, top.env);
  ns.realSignature = if null(id.lookupValue.dcls) then [] else [realSig.outputElement] ++ realSig.inputElements;

  local attribute prodAtts :: Defs;
  prodAtts = if null(id.lookupValue.errors)
             then defsFromPADcls(getProdAttrs(id.lookupValue.fullName, top.env), namedSig)
             else emptyDefs();

  body.env = newScopeEnv(appendDefs(body.defs, sigDefs), newScopeEnv(prodAtts, top.env));
  body.signature = namedSig;
  body.blockContext = defaultContext();

  forwards to defaultAGDcl();
}

concrete production aspectProductionSignature
top::AspectProductionSignature ::= lhs::AspectProductionLHS '::=' rhs::AspectRHS 
{
  top.pp = lhs.pp ++ " ::= " ++ rhs.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.defs = appendDefs(lhs.defs, rhs.defs);
  top.errors := lhs.errors ++ rhs.errors;

  top.inputElements = rhs.inputElements;
  top.outputElement = lhs.outputElement;

  lhs.realSignature = if null(top.realSignature) then [] else [head(top.realSignature)];
  rhs.realSignature = if null(top.realSignature) then [] else tail(top.realSignature);
}

concrete production aspectProductionLHSNone
top::AspectProductionLHS ::= '_'
{
  top.pp = "_";
  top.location = loc(top.file, $1.line, $1.column);
  forwards to aspectProductionLHSId(nameIdLower(terminal(IdLower_t, "p_top")));
}

concrete production aspectProductionLHSId
top::AspectProductionLHS ::= id::Name
{
  top.pp = id.pp;
  top.location = id.location;

  production attribute rType :: TypeExp;
  rType = if null(top.realSignature) then errorType() else head(top.realSignature).typerep;

  forwards to aspectProductionLHSFull(id, rType);
}

concrete production aspectProductionLHSTyped
top::AspectProductionLHS ::= id::Name '::' t::Type
{
  top.pp = id.pp;
  top.location = id.location;

  top.errors <- t.errors;
  
  forwards to aspectProductionLHSFull(id, t.typerep);
}

abstract production aspectProductionLHSFull
top::AspectProductionLHS ::= id::Name t::TypeExp
{
  top.pp = id.pp ++ "::" ++ t.unparse;
  top.location = id.location;

  production attribute fName :: String;
  fName = if null(top.realSignature) then id.name else head(top.realSignature).elementName;
  production attribute rType :: TypeExp;
  rType = if null(top.realSignature) then errorType() else head(top.realSignature).typerep;

  top.outputElement = namedSignatureElement(id.name, t);
  
  top.defs = addAliasedLhsDcl(top.grammarName, id.location, fName, t, id.name, emptyDefs());

  top.errors := if length(getValueDclInScope(id.name, top.env)) > 1
                then [err(top.location, "Value '" ++ fName ++ "' is already bound.")]
                else [];
}

concrete production aspectRHSElemNil
top::AspectRHS ::= 
{
  top.pp = "";
  top.location = loc(top.file,-1,-1);

  top.defs = emptyDefs();
  top.errors := [];
  top.inputElements = [];
}

concrete production aspectRHSElemCons
top::AspectRHS ::= h::AspectRHSElem t::AspectRHS
{
  top.pp = h.pp ++ " " ++ t.pp;
  top.location = h.location;

  top.defs = appendDefs(h.defs, t.defs);
  top.errors := h.errors ++ t.errors;

  top.inputElements = h.inputElements ++ t.inputElements;

  h.deterministicCount = length(t.inputElements);
  h.realSignature = if null(top.realSignature) then [] else [head(top.realSignature)];
  t.realSignature = if null(top.realSignature) then [] else tail(top.realSignature);
}

attribute deterministicCount occurs on AspectRHSElem;

concrete production aspectRHSElemNone
top::AspectRHSElem ::= '_'
{
  top.pp = "_";
  top.location = loc(top.file, $1.line, $1.column);

  forwards to aspectRHSElemId(nameIdLower(terminal(IdLower_t, "p_" ++ toString(top.deterministicCount), $1.line, $1.column)));
}

concrete production aspectRHSElemId
top::AspectRHSElem ::= id::Name
{
  top.pp = id.pp;
  top.location = id.location;

  production attribute rType :: TypeExp;
  rType = if null(top.realSignature) then errorType() else head(top.realSignature).typerep;

  forwards to aspectRHSElemFull(id, rType);
}

concrete production aspectRHSElemTyped
top::AspectRHSElem ::= id::Name '::' t::Type
{
  top.pp = id.pp ++ "::" ++ t.pp;
  top.location = loc(top.file, $2.line, $2.column);
  
  top.errors <- t.errors;

  forwards to aspectRHSElemFull(id, t.typerep);
}

abstract production aspectRHSElemFull
top::AspectRHSElem ::= id::Name t::TypeExp
{
  top.pp = id.pp ++ "::" ++ t.unparse;
  top.location = id.location;

  production attribute fName :: String;
  fName = if null(top.realSignature) then id.name else head(top.realSignature).elementName;
  production attribute rType :: TypeExp;
  rType = if null(top.realSignature) then errorType() else head(top.realSignature).typerep;

  top.inputElements = [namedSignatureElement(id.name, t)];

  top.defs = addAliasedChildDcl(top.grammarName, id.location, fName, t, id.name, emptyDefs());

  top.errors := if length(getValueDclInScope(id.name, top.env)) > 1
                then [err(top.location, "Value '" ++ fName ++ "' is already bound.")]
                else [];
}

concrete production aspectFunctionSignature
top::AspectFunctionSignature ::= lhs::AspectFunctionLHS '::=' rhs::AspectRHS 
{
  top.pp = lhs.pp ++ " ::= " ++ rhs.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.defs = appendDefs(lhs.defs, rhs.defs);
  top.errors := lhs.errors ++ rhs.errors;

  top.inputElements = rhs.inputElements;
  top.outputElement = lhs.outputElement;

  lhs.realSignature = if null(top.realSignature) then [] else [head(top.realSignature)];
  rhs.realSignature = if null(top.realSignature) then [] else tail(top.realSignature);
}

concrete production functionLHSType
top::AspectFunctionLHS ::= t::Type
{
  top.pp = t.pp;
  top.location = t.location;

  production attribute fName :: String;
  fName = if null(top.realSignature) then "_NULL_" else head(top.realSignature).elementName;
  production attribute rType :: TypeExp;
  rType = if null(top.realSignature) then errorType() else head(top.realSignature).typerep;

  top.outputElement = namedSignatureElement(fName, t.typerep);
  
  -- TODO: this needs thinking. is it broken? maybe __return? or wait, it's doing that automatically isnt it...
  top.defs = addAliasedLhsDcl(top.grammarName, t.location, fName, t.typerep, fName, emptyDefs());

  top.errors := [];
}
