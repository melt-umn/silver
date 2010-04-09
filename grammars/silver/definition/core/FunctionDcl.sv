grammar silver:definition:core;
import silver:definition:env;

concrete production functionDcl
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody 
{
  top.pp = "function " ++ id.pp ++ "\n" ++ ns.pp ++ "\n" ++ body.pp; 
  top.location = loc(top.file, $1.line, $1.column);

  top.moduleNames = [];

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ id.name;

  production attribute namedSig :: Decorated NamedSignature;
  namedSig = namedSignatureDcl(fName, ns.inputElements, ns.outputElement);

  local attribute pattrs :: Decorated Defs;
  pattrs = if body.productionAttributes.size > 0 
	   then addProductionAttributesDcl(fName, body.productionAttributes, emptyDefs()) 
	   else emptyDefs();

  top.defs = addValueDcl(fName, funTypeRep(getTypesSignature(namedSig.inputElements), namedSig.outputElement.typerep),
	     addFunctionDcl(namedSig,
	     addFullNameDcl(id.name, fName, pattrs)));

  local attribute er1 :: [Decorated Message];
  er1 = if length(getFullNameDclOne(id.name, top.env)) > 1
        then [err(top.location, "Name '" ++ id.pp ++ "' is already bound.")]
        else [];	

  local attribute er2 :: [Decorated Message];
  er2 = if length(getValueDclOne(fName, top.env)) > 1
        then [err(top.location, "Value '" ++ fName ++ "' is already bound.")]
        else [];

  top.errors := er1 ++ er2 ++ ns.errors ++ body.errors;
  top.warnings := [];

  ns.env = newScopeEnv(ns.defs, top.env);

  body.env = newScopeEnv(appendDefs(body.defs, appendDefs(ns.defs, addThisDcl(fName, emptyDefs()))), top.env);
  body.signature = namedSig;
  body.signatureEnv = toEnv(ns.defs);
  body.localsEnv = toEnv(body.defs);
}

concrete production functionSignatureEmptyRHS
top::FunctionSignature ::= lhs::FunctionLHS '::='
{
  top.pp = lhs.pp ++ " ::= ";
  top.location = loc(top.file, $2.line, $2.column);
  
  top.defs = lhs.defs;
  top.errors := lhs.errors;
  top.warnings := [];

  top.inputElements = [];
  top.outputElement = lhs.outputElement;
}

concrete production functionSignature
top::FunctionSignature ::= lhs::FunctionLHS '::=' rhs::ProductionRHS 
{
  top.pp = lhs.pp ++ " ::= " ++ rhs.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.defs = appendDefs(lhs.defs, rhs.defs);
  top.errors := lhs.errors ++ rhs.errors;
  top.warnings := [];

  top.inputElements = rhs.inputElements;
  top.outputElement = lhs.outputElement;
}

concrete production functionLHS
top::FunctionLHS ::= t::Type
{
  top.pp = t.pp;
  top.location = t.location;

  production attribute fName :: String;
  fName = "__return";

  top.outputElement = namedSignatureElement(fName, fName, fName, t.typerep);

  top.defs = addValueDcl(fName, t.typerep, 
	     addFullNameDcl(fName, fName,  emptyDefs()));

  top.errors := t.errors;
  top.warnings := [];
}
