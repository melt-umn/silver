grammar silver:compiler:definition:core;

nonterminal FunctionSignature with config, grammarName, env, location, unparse, errors, defs, constraintDefs, occursDefs, namedSignature, signatureName;
nonterminal FunctionLHS with config, grammarName, env, location, unparse, errors, defs, outputElement;

propagate errors on FunctionSignature, FunctionLHS;

concrete production functionDcl
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody
{
  top.unparse = "function " ++ id.unparse ++ "\n" ++ ns.unparse ++ "\n" ++ body.unparse; 

  production fName :: String = top.grammarName ++ ":" ++ id.name;
  production namedSig :: NamedSignature = ns.namedSignature;

  top.defs := funDef(top.grammarName, id.location, namedSig) ::
    if null(body.productionAttributes) then []
    else [prodOccursDef(top.grammarName, id.location, namedSig, body.productionAttributes)];

  top.errors <-
        if length(getValueDclAll(fName, top.env)) > 1
        then [err(id.location, "Value '" ++ fName ++ "' is already bound.")]
        else [];

  top.errors <-
        if null(body.uniqueSignificantExpression)
        then [err(top.location, "Function '" ++ id.name ++ "' does not have a return value.")]
        else if length(body.uniqueSignificantExpression) > 1
        then [err(top.location, "Function '" ++ id.name ++ "' has more than one declared return value.")]
        else [];

  production attribute sigDefs :: [Def] with ++;
  sigDefs := ns.defs;

  ns.signatureName = fName;
  ns.env = newScopeEnv(sigDefs, top.env);

  local attribute prodAtts :: [Def];
  prodAtts = defsFromPADcls(getProdAttrs(fName, top.env), namedSig);

  body.env = occursEnv(ns.occursDefs, newScopeEnv(body.defs ++ sigDefs ++ ns.constraintDefs, newScopeEnv(prodAtts, top.env)));
  body.frame = functionContext(namedSig, myFlowGraph, sourceGrammar=top.grammarName); -- graph from flow:env
} action {
  insert semantic token IdFnProdDcl_t at id.location;
  sigNames = [];
}

concrete production functionSignature
top::FunctionSignature ::= cl::ConstraintList '=>' lhs::FunctionLHS '::=' rhs::ProductionRHS 
{
  top.unparse = s"${cl.unparse} => ${lhs.unparse} ::= ${rhs.unparse}";

  cl.constraintPos = signaturePos(top.namedSignature);
  rhs.env = occursEnv(cl.occursDefs, top.env);

  top.defs := lhs.defs ++ rhs.defs;
  top.constraintDefs = cl.defs;
  top.occursDefs := cl.occursDefs;

  top.namedSignature =
    namedSignature(
      top.signatureName,
      foldContexts(cl.contexts),
      foldNamedSignatureElements(rhs.inputElements),
      lhs.outputElement,
      -- For the moment, functions do not have named parameters (hence, nilNamedSignatureElement)
      nilNamedSignatureElement());
} action {
  sigNames = foldNamedSignatureElements(rhs.inputElements).elementNames;
}

concrete production functionSignatureNoCL
top::FunctionSignature ::= lhs::FunctionLHS '::=' rhs::ProductionRHS 
{
  top.unparse = s"${lhs.unparse} ::= ${rhs.unparse}";

  forwards to functionSignature(nilConstraint(location=top.location), '=>', lhs, $2, rhs, location=top.location);
} action {
  sigNames = foldNamedSignatureElements(rhs.inputElements).elementNames;
}

concrete production functionLHS
top::FunctionLHS ::= t::TypeExpr
{
  top.unparse = t.unparse;

  production attribute fName :: String;
  fName = "__func__lhs";

  top.outputElement = namedSignatureElement(fName, t.typerep);

  -- TODO: think about this. lhs doesn't really have an fName.
  top.defs := [lhsDef(top.grammarName, t.location, fName, t.typerep)];
}

