grammar silver:definition:core;

concrete production aspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody 
{
  top.pp = "aspect production " ++ id.pp ++ "\n" ++ ns.pp ++ "\n" ++ body.pp;
  top.location = loc(top.file, $1.line, $1.column);

  top.moduleNames = [];

  top.defs = body.productionAttributes;

  production attribute namedSig :: Decorated NamedSignature;
  namedSig = namedSignatureDcl(id.lookupValue.fullName, ns.inputElements, ns.outputElement);

  production attribute realSig :: Decorated NamedSignature;
  realSig = if null(id.lookupValue.errors)
            then id.lookupValue.dcl.namedSignature
            else decorate namedSignatureDefault() with {};

  -- Making sure we're aspecting a production is taken care of by type checking.

  top.errors := id.lookupValue.errors ++ ns.errors ++ body.errors;
  top.warnings := [];

  ns.env = newScopeEnv(ns.defs, top.env);  
  ns.realSignature = if null(id.lookupValue.dcls) then [] else [realSig.outputElement] ++ realSig.inputElements;

  local attribute prodAtts :: Defs;
  prodAtts = if null(id.lookupValue.errors)
             then valueDefsFromDcls(getProdAttrs(id.lookupValue.fullName, top.env))
             else emptyDefs();

  body.env = newScopeEnv(appendDefs(body.defs, ns.defs), newScopeEnv(prodAtts, top.env));
  body.signature = namedSig;
}

concrete production aspectFunctionDcl
top::AGDcl ::= 'aspect' 'function' id::QName ns::AspectFunctionSignature body::ProductionBody 
{
  top.pp = "aspect function " ++ id.pp ++ "\n" ++ ns.pp ++ "\n" ++ body.pp;
  top.location = loc(top.file, $1.line, $1.column);

  top.defs = body.productionAttributes;

  top.moduleNames = [];

  production attribute namedSig :: Decorated NamedSignature;
  namedSig = namedSignatureDcl(id.lookupValue.fullName, ns.inputElements, ns.outputElement);

  production attribute realSig :: Decorated NamedSignature;
  realSig = if null(id.lookupValue.errors)
            then id.lookupValue.dcl.namedSignature
            else decorate namedSignatureDefault() with {};

  -- Making sure we're aspecting a function is taken care of by type checking.

  top.errors := id.lookupValue.errors ++ ns.errors ++ body.errors;
  top.warnings := [];

  ns.env = newScopeEnv(ns.defs, top.env);
  ns.realSignature = if null(id.lookupValue.dcls) then [] else [realSig.outputElement] ++ realSig.inputElements;

  local attribute prodAtts :: Defs;
  prodAtts = if null(id.lookupValue.errors)
             then valueDefsFromDcls(getProdAttrs(id.lookupValue.fullName, top.env))
             else emptyDefs();

  body.env = newScopeEnv(appendDefs(body.defs, ns.defs), newScopeEnv(prodAtts, top.env));
  body.signature = namedSig;
}

concrete production aspectProductionSignatureEmptyRHS
top::AspectProductionSignature ::= lhs::AspectProductionLHS '::=' 
{
  top.pp = lhs.pp ++ " ::= ";
  top.location = loc(top.file, $2.line, $2.column);

  top.defs = lhs.defs;
  top.errors := lhs.errors;
  top.warnings := [];

  top.inputElements = [];
  top.outputElement = lhs.outputElement;

  lhs.realSignature = if null(top.realSignature) then [] else [head(top.realSignature)];
}

concrete production aspectProductionSignature
top::AspectProductionSignature ::= lhs::AspectProductionLHS '::=' rhs::AspectRHS 
{
  top.pp = lhs.pp ++ " ::= " ++ rhs.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.defs = appendDefs(lhs.defs, rhs.defs);
  top.errors := lhs.errors ++ rhs.errors;
  top.warnings := [];

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
  forwards to aspectProductionLHSId(nameId(terminal(Id_t, "p_top")));
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

  production attribute fName :: String;
  fName = if null(top.realSignature) then id.name else head(top.realSignature).elementName;

  top.outputElement = namedSignatureElement(id.name, t);
  
  top.defs = addAliasedLhsDcl(top.grammarName, id.location, fName, t, id.name, emptyDefs());

  top.errors := if length(getValueDclInScope(id.name, top.env)) > 1
                then [err(top.location, "Value '" ++ fName ++ "' is already bound.")]
                else [];
  top.warnings := [];
}

concrete production aspectRHSElem
top::AspectRHS ::= rhs::AspectRHSElem
{
  top.pp = rhs.pp;
  top.location = rhs.location;

  top.defs = rhs.defs;
  top.errors := rhs.errors;
  top.warnings := [];
  top.inputElements = rhs.inputElements;

  rhs.deterministicCount = 0;
  rhs.realSignature = if null(top.realSignature) then [] else [head(top.realSignature)];
}

concrete production aspectRHSElemCons
top::AspectRHS ::= h::AspectRHSElem t::AspectRHS
{
  top.pp = h.pp ++ " " ++ t.pp;
  top.location = h.location;

  top.defs = appendDefs(h.defs, t.defs);
  top.errors := h.errors ++ t.errors;
  top.warnings := [];

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

  forwards to aspectRHSElemId(nameId(terminal(Id_t, "p_" ++ toString(top.deterministicCount), $1.line, $1.column)));
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

  top.inputElements = [namedSignatureElement(id.name, t)];

  top.defs = addAliasedChildDcl(top.grammarName, id.location, fName, t, id.name, emptyDefs());

  top.errors := if length(getValueDclInScope(id.name, top.env)) > 1
                then [err(top.location, "Value '" ++ fName ++ "' is already bound.")]
                else [];
  top.warnings := [];
}

concrete production aspectFunctionSignatureEmptyRHS
top::AspectFunctionSignature ::= lhs::AspectFunctionLHS '::=' 
{
  top.pp = lhs.pp ++ " ::= ";
  top.location = loc(top.file, $2.line, $2.column);

  top.defs = lhs.defs;
  top.errors := lhs.errors;
  top.warnings := [];

  top.inputElements = [];
  top.outputElement = lhs.outputElement;

  lhs.realSignature = if null(top.realSignature) then [] else [head(top.realSignature)];
}

concrete production aspectFunctionSignature
top::AspectFunctionSignature ::= lhs::AspectFunctionLHS '::=' rhs::AspectRHS 
{
  top.pp = lhs.pp ++ " ::= " ++ rhs.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.defs = appendDefs(lhs.defs, rhs.defs);
  top.errors := lhs.errors ++ rhs.errors;
  top.warnings := [];

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

  top.outputElement = namedSignatureElement(fName, t.typerep);
  
  -- TODO: this needs thinking. is it broken? maybe __return? or wait, it's doing that automatically isnt it...
  top.defs = addAliasedLhsDcl(top.grammarName, t.location, fName, t.typerep, fName, emptyDefs());

  top.errors := [];
  top.warnings := [];
}
