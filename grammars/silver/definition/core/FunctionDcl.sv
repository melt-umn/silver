grammar silver:definition:core;

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
              addPaDcl(top.grammarName, id.location, fName,
                       namedSig.outputElement.typerep, getTypesSignature(namedSig.inputElements),
                       body.productionAttributes,
               emptyDefs()));

  top.errors <-
        if length(getValueDclAll(fName, top.env)) > 1
        then [err(top.location, "Value '" ++ fName ++ "' is already bound.")]
        else [];

  top.errors := ns.errors ++ body.errors;
  top.warnings := body.warnings;

  production attribute sigDefs :: Defs with appendDefs;
  sigDefs := ns.defs;

  ns.env = newScopeEnv(sigDefs, top.env);

  local attribute prodAtts :: Defs;
  prodAtts = defsFromPADcls(getProdAttrs(fName, top.env), namedSig);

  body.env = newScopeEnv(appendDefs(body.defs, sigDefs), newScopeEnv(prodAtts, top.env));
  body.signature = namedSig;
  body.blockContext = functionContext();
}

concrete production functionSignatureEmptyRHS
top::FunctionSignature ::= lhs::FunctionLHS '::='
{
  top.pp = lhs.pp ++ " ::= ";
  top.location = loc(top.file, $2.line, $2.column);
  
  top.defs = lhs.defs;
  top.errors := lhs.errors;

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

  top.inputElements = rhs.inputElements;
  top.outputElement = lhs.outputElement;
}

concrete production functionLHS
top::FunctionLHS ::= t::Type
{
  top.pp = t.pp;
  top.location = t.location;

  production attribute fName :: String;
  fName = "__func__lhs";

  top.outputElement = namedSignatureElement(fName, t.typerep);

  -- TODO: think about this. lhs doesn't really have an fName.
  top.defs = addLhsDcl(top.grammarName, t.location, fName, t.typerep, emptyDefs());

  top.errors := t.errors;
}
