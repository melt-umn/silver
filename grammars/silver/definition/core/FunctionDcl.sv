grammar silver:definition:core;

nonterminal FunctionSignature with config, grammarName, file, env, location, pp, errors, defs, inputElements, outputElement;
nonterminal FunctionLHS with config, grammarName, file, env, location, pp, errors, defs, outputElement;

concrete production functionDcl
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody 
{
  top.pp = "function " ++ id.pp ++ "\n" ++ ns.pp ++ "\n" ++ body.pp; 
  top.location = loc(top.file, $1.line, $1.column);

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ id.name;

  production attribute namedSig :: NamedSignature;
  namedSig = namedSignature(fName, ns.inputElements, ns.outputElement);

  top.defs = funDef(top.grammarName, id.location, namedSig) ::
    if null(body.productionAttributes) then []
    else [prodOccursDef(top.grammarName, id.location, fName,
            namedSig.outputElement.typerep, namedSig.inputTypes,
            body.productionAttributes)];

  top.errors <-
        if length(getValueDclAll(fName, top.env)) > 1
        then [err(top.location, "Value '" ++ fName ++ "' is already bound.")]
        else [];

  top.errors <-
        if null(body.uniqueSignificantExpression)
        then [err(top.location, "Function '" ++ id.name ++ "' does not have a return value.")]
        else if length(body.uniqueSignificantExpression) > 1
        then [err(top.location, "Function '" ++ id.name ++ "' has more than one declared return value.")]
        else [];

  top.errors := ns.errors ++ body.errors;

  production attribute sigDefs :: [Def] with ++;
  sigDefs := ns.defs;

  ns.env = newScopeEnv(sigDefs, top.env);

  local attribute prodAtts :: [Def];
  prodAtts = defsFromPADcls(getProdAttrs(fName, top.env), namedSig);

  body.env = newScopeEnv(body.defs ++ sigDefs, newScopeEnv(prodAtts, top.env));
  body.signature = namedSig;
  body.blockContext = functionContext();
}

concrete production functionSignature
top::FunctionSignature ::= lhs::FunctionLHS '::=' rhs::ProductionRHS 
{
  top.pp = lhs.pp ++ " ::= " ++ rhs.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.defs = lhs.defs ++ rhs.defs;
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
  top.defs = [lhsDef(top.grammarName, t.location, fName, t.typerep)];

  top.errors := t.errors;
}
