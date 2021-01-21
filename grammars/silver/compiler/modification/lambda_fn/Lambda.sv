import silver:compiler:definition:flow:ast only ExprVertexInfo, FlowVertex;
import silver:compiler:definition:env;

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

-- TODO: New type variables are not currently allowed on the params.  This isn't a big deal for
-- writing code since lambdas aren't generalized anyway, but it would be nice to have for generated
-- code involving lambdas.  This isn't implemented at the moment due to a mess right now with how
-- this works for ProductionDcl
-- Also issues here with lexically scoped type variables
abstract production lambdap
top::Expr ::= params::ProductionRHS e::Expr
{
  top.unparse = "\\ " ++ params.unparse ++ " -> " ++ e.unparse;
  
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
attribute lambdaDefs occurs on ProductionRHS, ProductionRHSElem;

propagate lambdaDefs on ProductionRHS;

aspect production productionRHSElem
top::ProductionRHSElem ::= id::Name '::' t::TypeExpr
{
  production fName :: String = toString(genInt()) ++ ":" ++ id.name;
--  production transName :: String = "lambda_param" ++ id.name ++ toString(genInt());
  top.lambdaDefs := [lambdaParamDef(top.grammarName, t.location, fName, t.typerep)];
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
