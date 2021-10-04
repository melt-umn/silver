import silver:compiler:definition:flow:ast only ExprVertexInfo, FlowVertex;
import silver:compiler:definition:env;
import silver:util:treeset as ts;

--- Concrete Syntax for lambdas
--------------------------------------------------------------------------------

terminal Lambda_kwd '\' lexer classes {KEYWORD,RESERVED};
terminal Arrow_t '->' precedence = 0, lexer classes {SPECOP};

-- Using ProductionRHS here, it is basicly just a list of names with type expressions
-- It is also used for the parameter definitions in functions, so using it here for consistancy
concrete production lambda_c
top::Expr ::= '\' params::ProductionRHS '->' e::Expr
{
  top.unparse = "\\ " ++ params.unparse ++ " -> " ++ e.unparse;

  forwards to lambdap(params, e, location=top.location);
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

monoid attribute lambdaDefs::[Def];
monoid attribute lambdaBoundVars::[String];
attribute lambdaDefs, lambdaBoundVars occurs on ProductionRHS, ProductionRHSElem;

flowtype ProductionRHSElem = lambdaDefs {decorate}, lambdaBoundVars {decorate};

propagate lambdaDefs, lambdaBoundVars on ProductionRHS;

aspect production productionRHSElem
top::ProductionRHSElem ::= id::Name '::' t::TypeExpr
{
  production fName :: String = toString(genIntT()) ++ ":" ++ id.name;
--  production transName :: String = "lambda_param" ++ id.name ++ toString(genIntT());
  top.lambdaDefs := [lambdaParamDef(top.grammarName, t.location, fName, t.typerep)];
  top.lambdaBoundVars := [id.name];
}

abstract production lambdaParamReference
top::Expr ::= q::Decorated QName
{
  top.unparse = q.unparse;
  propagate errors;
  top.freeVars := ts:fromList([q.name]);
  
  top.typerep = q.lookupValue.typeScheme.monoType;

  propagate downSubst, upSubst;
  
  -- TODO?
  propagate flowDeps, flowDefs;
}
