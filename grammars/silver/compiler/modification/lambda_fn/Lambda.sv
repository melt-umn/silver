import silver:compiler:definition:flow:ast only ExprVertexInfo, FlowVertex;
import silver:compiler:definition:env;
import silver:util:treeset as ts;

--- Concrete Syntax for lambdas
--------------------------------------------------------------------------------

terminal Lambda_kwd '\' lexer classes {KEYWORD,RESERVED};
terminal Arrow_t '->' precedence = 0, lexer classes {SPECOP};

nonterminal LambdaParams with config, grammarName, env, location, unparse, errors, defs, inputElements,
                              lambdaDefs, lambdaBoundVars, lexicalTyVarKinds, lexicalTypeVariables;
nonterminal LambdaParam with config, grammarName, env, location, unparse, errors, defs, inputElements, deterministicCount,
                              lambdaDefs, lambdaBoundVars, lexicalTyVarKinds, lexicalTypeVariables;

concrete production lambdaParamsOne
top::LambdaParams ::= p::LambdaParam {

  top.unparse = p.unparse;
  top.inputElements = p.inputElements;

  p.deterministicCount = 0;

}

concrete production lambdaParamsCons
top::LambdaParams ::= h::LambdaParam t::LambdaParams
{
  top.unparse = h.unparse ++ " " ++ t.unparse;

  top.inputElements = h.inputElements ++ t.inputElements;
  h.deterministicCount = length(t.inputElements);
}

concrete production lambdaParam
top::LambdaParam ::= id::Name
{
  top.unparse = id.unparse;

  local t::Type = freshType();

  top.inputElements = [namedSignatureElement(id.name, t)];
  top.defs := [childDef(top.grammarName, id.location, id.name, t)];

  top.lexicalTyVarKinds <- []; -- TO DO
  top.lexicalTypeVariables <- [];

  production fName :: String = toString(genInt()) ++ ":" ++ id.name;
  top.lambdaDefs := [lambdaParamDef(top.grammarName, id.location, fName, t)];
  top.lambdaBoundVars := [id.name];

  top.errors <-
    if length(getValueDclInScope(id.name, top.env)) > 1 
    then [err(id.location, "Value '" ++ id.name ++ "' is already bound.")]
    else [];

}

-- Using ProductionRHS here, it is basically just a list of names with type expressions
-- It is also used for the parameter definitions in functions, so using it here for consistancy
concrete production lambda_c
top::Expr ::= '\' params::ProductionRHS '->' e::Expr
{
  top.unparse = "\\ " ++ params.unparse ++ " -> " ++ e.unparse;

  forwards to lambdap(params, e, location=top.location);
}

concrete production lambda_c_wot
top::Expr ::= '\' params::LambdaParams '->' e::Expr
{

  top.unparse = "\\ " ++ params.unparse ++ "->" ++ e.unparse;

  forwards to lambdap_wot(params, e, location=top.location);

}

abstract production lambdap
top::Expr ::= params::ProductionRHS e::Expr
{
  top.unparse = "\\ " ++ params.unparse ++ " -> " ++ e.unparse;
  top.freeVars := ts:removeAll(params.lambdaBoundVars, e.freeVars);
  
  propagate errors;
  
  top.typerep = appTypes(functionType(length(params.inputElements), []), map((.typerep), params.inputElements) ++ [e.typerep]);

  production attribute sigDefs::[Def] with ++;
  sigDefs := params.lambdaDefs;
  sigDefs <-
    addNewLexicalTyVars_ActuallyVariables(
      top.grammarName, top.location, params.lexicalTyVarKinds,
      filter(\ tv::String -> null(getTypeDcl(tv, top.env)), nub(params.lexicalTypeVariables)));

  propagate downSubst, upSubst;
  propagate flowDeps, flowDefs;
  
  params.env = newScopeEnv(sigDefs, top.env);
  e.env = params.env;
  e.frame = inLambdaContext(top.frame, sourceGrammar=top.frame.sourceGrammar); --TODO: Is this sourceGrammar correct?
}

abstract production lambdap_wot
top::Expr ::= params::LambdaParams e::Expr
{
  top.unparse = "\\ " ++ params.unparse ++ " -> " ++ e.unparse;
  top.freeVars := ts:removeAll(params.lambdaBoundVars, e.freeVars);
  
  propagate errors;
  
  top.typerep = appTypes(functionType(length(params.inputElements), []), map((.typerep), params.inputElements) ++ [e.typerep]);

  production attribute sigDefs::[Def] with ++;
  sigDefs := params.lambdaDefs;
  sigDefs <-
    addNewLexicalTyVars_ActuallyVariables(
      top.grammarName, top.location, params.lexicalTyVarKinds,
      filter(\ tv::String -> null(getTypeDcl(tv, top.env)), nub(params.lexicalTypeVariables)));

  propagate downSubst, upSubst;
  propagate flowDeps, flowDefs;
  
  params.env = newScopeEnv(sigDefs, top.env);
  e.env = params.env;
  e.frame = inLambdaContext(top.frame, sourceGrammar=top.frame.sourceGrammar); --TODO: Is this sourceGrammar correct?
}

monoid attribute lambdaDefs::[Def];
monoid attribute lambdaBoundVars::[String];
attribute lambdaDefs, lambdaBoundVars occurs on ProductionRHS, ProductionRHSElem;

propagate lambdaDefs, lambdaBoundVars on ProductionRHS;

aspect production productionRHSElem
top::ProductionRHSElem ::= id::Name '::' t::TypeExpr
{
  production fName :: String = toString(genInt()) ++ ":" ++ id.name;
--  production transName :: String = "lambda_param" ++ id.name ++ toString(genInt());
  top.lambdaDefs := [lambdaParamDef(top.grammarName, t.location, fName, t.typerep)];
  top.lambdaBoundVars := [id.name];
}

abstract production lambdaParamReference
top::Expr ::= q::Decorated QName
{
  top.unparse = q.unparse;
  propagate errors;
  
  top.typerep = q.lookupValue.typeScheme.monoType;

  propagate downSubst, upSubst;
  
  -- TODO?
  propagate flowDeps, flowDefs;
}
