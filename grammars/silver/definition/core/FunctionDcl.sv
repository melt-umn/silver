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

  top.defs = addFunDcl(top.grammarName, id.location, namedSig,
               body.productionAttributes);

  local attribute er2 :: [Decorated Message];
  er2 = if length(getValueDclAll(fName, top.env)) > 1
        then [err(top.location, "Value '" ++ fName ++ "' is already bound.")]
        else [];

  top.errors := er2 ++ ns.errors ++ body.errors;
  top.warnings := [];

  ns.env = newScopeEnv(ns.defs, top.env);

  local attribute prodAtts :: Defs;
  prodAtts = valueDefsFromDcls(getProdAttrs(fName, top.env));

  body.env = newScopeEnv(appendDefs(body.defs, ns.defs), newScopeEnv(prodAtts, top.env));
  body.signature = namedSig;
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

  top.outputElement = namedSignatureElement(fName, t.typerep);

  -- TODO: think about this. lhs doesn't really have an fName.
  top.defs = addLhsDcl(top.grammarName, t.location, fName, t.typerep, emptyDefs());

  top.errors := t.errors;
  top.warnings := [];
}
