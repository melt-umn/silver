grammar silver:definition:core;
import silver:definition:env;

concrete production aspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody 
{
  top.pp = "aspect production " ++ id.pp ++ "\n" ++ ns.pp ++ "\n" ++ body.pp;
  top.location = loc(top.file, $1.line, $1.column);

  top.moduleNames = [::String];

  top.defs = emptyDefs();

  production attribute fName :: String;
  fName = if !null(fNames) then head(fNames).fullName else id.name;

  production attribute fNames :: [Decorated EnvItem];
  fNames = getFullNameDcl(id.name, top.env);

  production attribute prods :: [Decorated EnvItem];
  prods = getProductionDcl(fName, top.env);

  local attribute er :: [Decorated Message];
  er = if null(prods)
       then [err(top.location, "Production for aspect '" ++ id.name ++ "' is not declared.")]
       else [::Decorated Message];	

  production attribute namedSig :: Decorated NamedSignature;
  namedSig = namedSignatureDcl(fName, ns.inputElements, ns.outputElement);

  production attribute realSig :: Decorated NamedSignature;
  realSig = head(prods).namedSignature;

  top.errors := er ++ ns.errors ++ body.errors;
  top.warnings := [::Decorated Message];

  local attribute prodAtts :: Decorated Defs;
  prodAtts = getProductionAttributes(fName, top.env);

  body.env = appendDefsEnv(
	     	appendDefs(body.defs, 
	     	appendDefs(ns.defs, 
	     	addThisDcl(fName, emptyDefs()))), pushScope(appendDefsEnv(prodAtts, pushScope(top.env))));
		

  body.signatureEnv = toEnv(ns.defs);
  body.localsEnv = toEnv(appendDefs(prodAtts, body.defs));
  body.signature = namedSig;

  ns.env = appendDefsEnv(ns.defs, pushScope(top.env));  
  ns.realSignature = if null(prods) then [::Decorated NamedSignatureElement] else [realSig.outputElement] ++ realSig.inputElements;
}

concrete production aspectFunctionDcl
top::AGDcl ::= 'aspect' 'function' id::QName ns::AspectFunctionSignature body::ProductionBody 
{
  top.pp = "aspect function " ++ id.pp ++ "\n" ++ ns.pp ++ "\n" ++ body.pp;
  top.location = loc(top.file, $1.line, $1.column);

  top.defs = emptyDefs();

  top.moduleNames = [::String];

  production attribute fName :: String;
  fName = if !null(fNames) then head(fNames).fullName else id.name;

  production attribute fNames :: [Decorated EnvItem];
  fNames = getFullNameDcl(id.name, top.env);

  production attribute funs :: [Decorated EnvItem];
  funs = getFunctionDcl(fName, top.env);

  local attribute er :: [Decorated Message];
  er = if null(funs)
       then [err(top.location, "Function for aspect '" ++ id.name ++ "' is not declared.")]
       else [::Decorated Message];	

  production attribute namedSig :: Decorated NamedSignature;
  namedSig = namedSignatureDcl(fName, ns.inputElements, ns.outputElement);

  production attribute realSig :: Decorated NamedSignature;
  realSig = head(funs).namedSignature;

  top.errors := er ++ ns.errors ++ body.errors;
  top.warnings := [::Decorated Message];

  local attribute prodAtts :: Decorated Defs;
  prodAtts = getProductionAttributes(fName, top.env);

  body.env = appendDefsEnv(
	     	appendDefs(body.defs, 
	     	appendDefs(ns.defs, 
	     	addThisDcl(fName, emptyDefs()))), pushScope(appendDefsEnv(prodAtts, pushScope(top.env))));
		

  body.signatureEnv = toEnv(ns.defs);
  body.localsEnv = toEnv(appendDefs(prodAtts, body.defs));
  body.signature = namedSig;

  ns.env = appendDefsEnv(ns.defs, pushScope(top.env));  
  ns.realSignature = if null(funs) then [::Decorated NamedSignatureElement] else [realSig.outputElement] ++ realSig.inputElements;
}

concrete production aspectProductionSignatureEmptyRHS
top::AspectProductionSignature ::= lhs::AspectProductionLHS '::=' 
{
  top.pp = lhs.pp ++ " ::= ";
  top.location = loc(top.file, $2.line, $2.column);

  top.defs = lhs.defs;
  top.errors := lhs.errors;
  top.warnings := [::Decorated Message];

  top.inputElements = [::Decorated NamedSignatureElement];
  top.outputElement = lhs.outputElement;

  lhs.realSignature = if null(top.realSignature) then [::Decorated NamedSignatureElement] else [head(top.realSignature)];
}

concrete production aspectProductionSignature
top::AspectProductionSignature ::= lhs::AspectProductionLHS '::=' rhs::AspectRHS 
{
  top.pp = lhs.pp ++ " ::= " ++ rhs.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.defs = appendDefs(lhs.defs, rhs.defs);
  top.errors := lhs.errors ++ rhs.errors;
  top.warnings := [::Decorated Message];

  top.inputElements = rhs.inputElements;
  top.outputElement = lhs.outputElement;

  lhs.realSignature = if null(top.realSignature) then [::Decorated NamedSignatureElement] else [head(top.realSignature)];
  rhs.realSignature = if null(top.realSignature) then [::Decorated NamedSignatureElement] else tail(top.realSignature);
}

concrete production aspectProductionLHSNone
top::AspectProductionLHS ::= '_'
{
  top.pp = "_";
  top.location = loc(top.file, $1.line, $1.column);
  forwards to aspectProductionLHSId(nameId(terminal(Id_t, "_" ++ toString(genInt()))));
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

  --TODO capture errors from type
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
        else [::Decorated Message];	

  local attribute er2 :: [Decorated Message];
  er2 = if length(getValueDclOne(fName, top.env)) > 1
        then [err(top.location, "Value '" ++ fName ++ "' is already bound.")]
        else [::Decorated Message];

  top.errors := er1 ++ er2;
  top.warnings := [::Decorated Message];
}

concrete production aspectRHSElem
top::AspectRHS ::= rhs::AspectRHSElem
{
  top.pp = rhs.pp;
  top.location = rhs.location;

  top.defs = rhs.defs;
  top.errors := rhs.errors;
  top.warnings := [::Decorated Message];
  top.inputElements = rhs.inputElements;

  rhs.realSignature = if null(top.realSignature) then [::Decorated NamedSignatureElement] else [head(top.realSignature)];
}

concrete production aspectRHSElemCons
top::AspectRHS ::= h::AspectRHSElem t::AspectRHS
{
  top.pp = h.pp ++ " " ++ t.pp;
  top.location = h.location;

  top.defs = appendDefs(h.defs, t.defs);
  top.errors := h.errors ++ t.errors;
  top.warnings := [::Decorated Message];

  top.inputElements = h.inputElements ++ t.inputElements;

  h.realSignature = if null(top.realSignature) then [::Decorated NamedSignatureElement] else [head(top.realSignature)];
  t.realSignature = if null(top.realSignature) then [::Decorated NamedSignatureElement] else tail(top.realSignature);
}


concrete production aspectRHSElemNone
top::AspectRHSElem ::= '_'
{
  top.pp = "_";
  top.location = loc(top.file, $1.line, $1.column);

  forwards to aspectRHSElemId(nameId(terminal(Id_t, "_" ++ toString(genInt()))));
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
        else [::Decorated Message];	

  local attribute er2 :: [Decorated Message];
  er2 = if length(getValueDclOne(fName, top.env)) > 1
        then [err(top.location, "Value '" ++ fName ++ "' is already bound.")]
        else [::Decorated Message];	

  top.errors := er1 ++ er2;
  top.warnings := [::Decorated Message];
}

concrete production aspectFunctionSignatureEmptyRHS
top::AspectFunctionSignature ::= lhs::AspectFunctionLHS '::=' 
{
  top.pp = lhs.pp ++ " ::= ";
  top.location = loc(top.file, $2.line, $2.column);

  top.defs = lhs.defs;
  top.errors := lhs.errors;
  top.warnings := [::Decorated Message];

  top.inputElements = [::Decorated NamedSignatureElement];
  top.outputElement = lhs.outputElement;

  lhs.realSignature = if null(top.realSignature) then [::Decorated NamedSignatureElement] else [head(top.realSignature)];
}

concrete production aspectFunctionSignature
top::AspectFunctionSignature ::= lhs::AspectFunctionLHS '::=' rhs::AspectRHS 
{
  top.pp = lhs.pp ++ " ::= " ++ rhs.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.defs = appendDefs(lhs.defs, rhs.defs);
  top.errors := lhs.errors ++ rhs.errors;
  top.warnings := [::Decorated Message];

  top.inputElements = rhs.inputElements;
  top.outputElement = lhs.outputElement;

  lhs.realSignature = if null(top.realSignature) then [::Decorated NamedSignatureElement] else [head(top.realSignature)];
  rhs.realSignature = if null(top.realSignature) then [::Decorated NamedSignatureElement] else tail(top.realSignature);
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

  top.errors := [::Decorated Message];
  top.warnings := [::Decorated Message];
}
