grammar silver:definition:core;
import silver:definition:env;

concrete production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  top.pp = "abstract production " ++ id.pp ++ "\n" ++ ns.pp ++ "\n" ++ body.pp; 
  top.location = loc(top.file, $1.line, $1.column);

  top.moduleNames = [];

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ id.name;

  production attribute namedSig :: Decorated NamedSignature;
  namedSig = namedSignatureDcl(fName, ns.inputElements, ns.outputElement);

  local attribute pattrs :: Decorated Defs;
  pattrs = if body.productionAttributes.size > 0 then addProductionAttributesDcl(fName, body.productionAttributes, emptyDefs()) else emptyDefs();

  top.defs = addValueDcl(fName, prodTypeRep(getTypesSignature(namedSig.inputElements), namedSig.outputElement.typerep), 
	     addProductionDcl(namedSig,
	     addFullNameDcl(id.name, fName, pattrs)));

  local attribute er1 :: [Decorated Message];
  er1 = if length(getFullNameDclOne(id.name, top.env)) > 1
        then [err(top.location, "Name '" ++ id.pp ++ "' is already bound.")]
        else [];	

  local attribute er2 :: [Decorated Message];
  er2 = if length(getValueDclOne(fName, top.env)) > 1
        then [err(top.location, "Value '" ++ fName ++ "' is already bound.")]
        else [];
  
  local attribute er3 :: [Decorated Message];
  er3 = if length(getFullNameDcl(id.name, top.env)) > 1 && null(er1)
        then [err(top.location, "Production " ++ id.pp ++ " shares a name with another production from an imported grammar. Either this production is meant to be an aspect, or you should use 'import ... with " ++ id.pp ++ " as ...' to change the other production's apparent name.")]
        else [];

  top.errors := er1 ++ er2 ++ er3 ++ ns.errors ++ body.errors;
  top.warnings := [];

  ns.env = newScopeEnv(ns.defs, top.env);

  body.env = newScopeEnv(appendDefs(body.defs, ns.defs), top.env);
  body.signature = namedSig;
  body.signatureEnv = toEnv(ns.defs);
  body.localsEnv = toEnv(body.defs);
}

concrete production productionSignatureEmptyRHS
top::ProductionSignature ::= lhs::ProductionLHS '::='
{
  top.pp = lhs.pp ++ " ::= ";
  top.location = loc(top.file, $2.line, $2.column);
  
  top.defs = lhs.defs;
  top.errors := lhs.errors;
  top.warnings := [];

  top.inputElements = [];
  top.outputElement = lhs.outputElement;
}

concrete production productionSignature
top::ProductionSignature ::= lhs::ProductionLHS '::=' rhs::ProductionRHS 
{
  top.pp = lhs.pp ++ " ::= " ++ rhs.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.defs = appendDefs(lhs.defs, rhs.defs);
  top.errors := lhs.errors ++ rhs.errors;
  top.warnings := [];

  top.inputElements = rhs.inputElements;
  top.outputElement = lhs.outputElement;
}

concrete production productionLHS
top::ProductionLHS ::= id::Name '::' t::Type
{
  top.pp = id.pp ++ "::" ++ t.pp;
  top.location = loc(top.file, $2.line, $2.column);

  production attribute fName :: String;
  fName = id.name;

  top.outputElement = namedSignatureElement(id.name, id.name, fName, t.typerep);

  top.defs = addValueDcl(fName, t.typerep, 
	     addFullNameDcl(id.name, fName,  emptyDefs()));

  local attribute er1 :: [Decorated Message];
  er1 = if length(getFullNameDclOne(id.name, top.env)) > 1
       then [err(top.location, "Name '" ++ id.name ++ "' is already bound.")]
       else [];	

  local attribute er2 :: [Decorated Message];
  er2 = if length(getValueDclOne(fName, top.env)) > 1
       then [err(top.location, "Value '" ++ fName ++ "' is already bound.")]
       else [];	

  top.errors := er1 ++ er2 ++ t.errors;
  top.warnings := [];
}

concrete production productionRHSSingle
top::ProductionRHS ::= rhs::ProductionRHSElem
{
  top.pp = rhs.pp;
  top.location = rhs.location;

  top.defs = rhs.defs;
  top.errors := rhs.errors;
  top.warnings := [];

  top.inputElements = rhs.inputElements;
  rhs.deterministicCount = 0;
}

concrete production productionRHSCons
top::ProductionRHS ::= h::ProductionRHSElem t::ProductionRHS
{
  top.pp = h.pp ++ " " ++ t.pp;
  top.location = h.location;

  top.defs = appendDefs(h.defs, t.defs);
  top.errors := h.errors ++ t.errors;
  top.warnings := [];

  top.inputElements = h.inputElements ++ t.inputElements;
  h.deterministicCount = length(t.inputElements);
}

-- used to avoid using gen int when not given an explicit name
inherited attribute deterministicCount :: Integer occurs on ProductionRHSElem;

concrete production productionRHSElem
top::ProductionRHSElem ::= id::Name '::' t::Type
{
  top.pp = id.pp ++ "::" ++ t.pp;
  top.location = loc(top.file, $2.line, $2.column);

  production attribute fName :: String;
  fName = id.name;

  top.inputElements = [namedSignatureElement(id.name, id.name, fName, t.typerep)];

  top.defs = addValueDcl(fName, t.typerep, 
	     addFullNameDcl(id.name, fName,  emptyDefs()));

  local attribute er1 :: [Decorated Message];
  er1 = if length(getFullNameDclOne(id.name, top.env)) > 1
       then [err(top.location, "Name '" ++ id.name ++ "' is already bound.")]
       else [];	

  local attribute er2 :: [Decorated Message];
  er2 = if length(getValueDclOne(fName, top.env)) > 1
       then [err(top.location, "Value '" ++ fName ++ "' is already bound.")]
       else [];	

  top.errors := er1 ++ er2 ++ t.errors;
  top.warnings := [];
}

concrete production productionRHSElemType
top::ProductionRHSElem ::= t::Type
{
  top.pp = t.pp;
  top.location = t.location;

  forwards to productionRHSElem(nameId(terminal(Id_t, "_G_" ++ toString(top.deterministicCount))), terminal(HasType_t, "::="), t);
}
