grammar silver:definition:core;
import silver:definition:env;

concrete production aspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody 
{
  top.pp = "aspect production " ++ id.pp ++ "\n" ++ ns.pp ++ "\n" ++ body.pp;
  top.location = loc(top.file, $1.line, $1.column);

  top.moduleNames = [];

  top.defs = emptyDefs(); -- TODO let production attributes get defined in aspects

  production attribute namedSig :: Decorated NamedSignature;
  namedSig = namedSignatureDcl(id.lookupFunction.fullName, ns.inputElements, ns.outputElement);

  production attribute realSig :: Decorated NamedSignature;
  realSig = head(id.lookupProduction.envItems).namedSignature;

  top.errors := id.lookupProduction.errors ++ ns.errors ++ body.errors;
  top.warnings := [];

  local attribute prodAtts :: Decorated Defs;
  prodAtts = getProductionAttributes(id.lookupProduction.fullName, top.env);

  body.env = newScopeEnv(appendDefs(body.defs, ns.defs), newScopeEnv(prodAtts, top.env));

  body.signatureEnv = toEnv(ns.defs);
  body.localsEnv = toEnv(appendDefs(prodAtts, body.defs));
  body.signature = namedSig;

  ns.env = newScopeEnv(ns.defs, top.env);  
  ns.realSignature = if null(id.lookupProduction.envItems) then [] else [realSig.outputElement] ++ realSig.inputElements;
}

concrete production aspectFunctionDcl
top::AGDcl ::= 'aspect' 'function' id::QName ns::AspectFunctionSignature body::ProductionBody 
{
  top.pp = "aspect function " ++ id.pp ++ "\n" ++ ns.pp ++ "\n" ++ body.pp;
  top.location = loc(top.file, $1.line, $1.column);

  top.defs = emptyDefs();

  top.moduleNames = [];

  production attribute namedSig :: Decorated NamedSignature;
  namedSig = namedSignatureDcl(id.lookupFunction.fullName, ns.inputElements, ns.outputElement);

  production attribute realSig :: Decorated NamedSignature;
  realSig = head(id.lookupFunction.envItems).namedSignature;

  top.errors := id.lookupFunction.errors ++ ns.errors ++ body.errors;
  top.warnings := [];

  local attribute prodAtts :: Decorated Defs;
  prodAtts = getProductionAttributes(id.lookupFunction.fullName, top.env);

  body.env = newScopeEnv(appendDefs(body.defs, ns.defs), newScopeEnv(prodAtts, top.env));

  body.signatureEnv = toEnv(ns.defs);
  body.localsEnv = toEnv(appendDefs(prodAtts, body.defs));
  body.signature = namedSig;

  ns.env = newScopeEnv(ns.defs, top.env);  
  ns.realSignature = if null(id.lookupFunction.envItems) then [] else [realSig.outputElement] ++ realSig.inputElements;
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

  production attribute rType :: Decorated TypeRep;
  rType = if null(top.realSignature) then topTypeRep() else head(top.realSignature).typerep;

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
top::AspectProductionLHS ::= id::Name t::Decorated TypeRep
{
  top.pp = id.pp ++ "::" ++ t.unparse;

  production attribute rName :: String;
  rName = if null(top.realSignature) then id.name else head(top.realSignature).realName;

  production attribute fName :: String;
  fName = if null(top.realSignature) then id.name else head(top.realSignature).fullName;

  top.outputElement = namedSignatureElement(id.name, rName, fName, t);
  
  top.defs = addValueDcl(fName, t, 
	     addFullNameDcl(id.name, fName,  emptyDefs()));

  local attribute er1 :: [Decorated Message];
  er1 = if length(getFullNameDclOne(id.name, top.env)) > 1
        then [err(top.location, "Name '" ++ id.name ++ "' is already bound.")]
        else [];	

  local attribute er2 :: [Decorated Message];
  er2 = if length(getValueDclOne(fName, top.env)) > 1
        then [err(top.location, "Value '" ++ fName ++ "' is already bound.")]
        else [];

  top.errors := er1 ++ er2;
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

  production attribute rType :: Decorated TypeRep;
  rType = if null(top.realSignature) then topTypeRep() else head(top.realSignature).typerep;

  forwards to aspectRHSElemFull(id, rType);
}

concrete production aspectRHSElemTyped
top::AspectRHSElem ::= id::Name '::' t::Type
{
  top.pp = id.pp ++ "::" ++ t.pp;
  top.location = loc(top.file, $2.line, $2.column);

  forwards to aspectRHSElemFull(id, t.typerep);
}

abstract production aspectRHSElemFull
top::AspectRHSElem ::= id::Name t::Decorated TypeRep
{
  top.pp = id.pp ++ "::" ++ t.unparse;
  top.location = id.location;

  production attribute rName :: String;
  rName = if null(top.realSignature) then id.name else head(top.realSignature).realName;

  production attribute fName :: String;
  fName = if null(top.realSignature) then id.name else head(top.realSignature).fullName;

  top.inputElements = [namedSignatureElement(id.name, rName, fName, t)];


  top.defs = addValueDcl(fName, t, 
	     addFullNameDcl(id.name, fName,  emptyDefs()));

  local attribute er1 :: [Decorated Message];
  er1 = if length(getFullNameDclOne(id.name, top.env)) > 1
        then [err(top.location, "Name '" ++ id.name ++ "' is already bound.")]
        else [];	

  local attribute er2 :: [Decorated Message];
  er2 = if length(getValueDclOne(fName, top.env)) > 1
        then [err(top.location, "Value '" ++ fName ++ "' is already bound.")]
        else [];	

  top.errors := er1 ++ er2;
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

  production attribute rName :: String;
  rName = if null(top.realSignature) then "_NULL_" else head(top.realSignature).realName;

  production attribute fName :: String;
  fName = if null(top.realSignature) then rName else head(top.realSignature).fullName;

  top.outputElement = namedSignatureElement(rName, rName, fName, t.typerep);
  
  top.defs = addValueDcl(fName, t.typerep, 
	     addFullNameDcl(rName, fName,  emptyDefs()));

  top.errors := [];
  top.warnings := [];
}
