import silver:definition:flow:ast only ExprVertexInfo, FlowVertex;

--- Concrete Syntax for lets
--------------------------------------------------------------------------------

terminal Lambda_kwd 'lambda' lexer classes {KEYWORD,RESERVED};
terminal Arrow_t '->' precedence = 0, lexer classes {SPECOP};

concrete production lambda_c
top::Expr ::= 'lambda' params::ProductionRHS '->' e::Expr
{
  top.pp = "lambda " ++ params.pp ++ " -> " ++ e.pp;

  forwards to lambdap(params, e, location=top.location);
}

abstract production lambdap
top::Expr ::= params::ProductionRHS e::Expr
{
  top.pp = "lambda " ++ params.pp ++ " -> " ++ e.pp;
  
  top.errors := params.errors ++ e.errors;
  
  top.typerep = functionTypeExp(e.typerep, map((.typerep), params.inputElements), []);
  
  -- TODO?
  e.downSubst = top.downSubst;
  top.upSubst = e.upSubst;
  
  -- TODO?
  top.flowDeps = e.flowDeps;
  top.flowDefs = e.flowDefs;
  
  e.env = newScopeEnv(params.lambdaDefs, top.env);
}

synthesized attribute lambdaDefs::[Def] occurs on ProductionRHS, ProductionRHSElem;

aspect production productionRHSCons
top::ProductionRHS ::= h::ProductionRHSElem t::ProductionRHS
{
  top.lambdaDefs = h.lambdaDefs ++ t.lambdaDefs;
}
aspect production productionRHSNil
top::ProductionRHS ::= 
{
  top.lambdaDefs = [];
}

aspect production productionRHSElem
top::ProductionRHSElem ::= id::Name '::' t::Type
{
  production fName :: String = toString(genInt()) ++ ":" ++ id.name;
--  production transName :: String = "lambda_param" ++ id.name ++ toString(genInt());
  top.lambdaDefs = [lambdaParamDef(top.grammarName, t.location, fName, t.typerep)];
}

abstract production lambdaParamReference
top::Expr ::= q::Decorated QName
{
  top.pp = q.pp;
  top.errors := []; -- TODO?
  
  top.typerep = q.lookupValue.typerep;

  top.upSubst = top.downSubst;
  
  -- TODO?
  top.flowDeps = [];
  top.flowDefs = [];
}
