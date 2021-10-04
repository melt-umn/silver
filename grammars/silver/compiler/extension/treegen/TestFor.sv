
import silver:compiler:extension:testing;

terminal TestFor_T 'testFor';


concrete production testforagdcl
top::AGDcl ::= 'testFor' testSuite::Name ':' n::Name '::' id::QName ',' e::Expr ';'
{
  top.defs := [];
  top.moduleNames := [];
  top.flowDefs := [];
  
  forward.env = newScopeEnv(forward.defs, top.env);



  -- all known productions, including forwarding ones
  local prods :: [ValueDclInfo] = getKnownProds(id.lookupType.fullName, top.env);

  local l :: Location = top.location;
  local generatedName :: String = "checkPropOn" ++ id.name ++ toString(genIntT());
  
  local fundcl :: AGDcl =
    functionDcl(
      'function',
      name(generatedName, l),
      sig,
      body, location=l);

  local sig :: FunctionSignature =
    functionSignature(
      nilConstraint(location=l), '=>',
      functionLHS(typerepTypeExpr(boolType(), location=l), location=l),
      '::=',
      productionRHSCons(
        productionRHSElem(n, '::', typerepTypeExpr(id.lookupType.typeScheme.typerep, location=l), location=l),
        productionRHSNil(location=l), location=l),
      location=l);
  
  local body :: ProductionBody =
    productionBody('{', foldl(productionStmtsSnoc(_, _, location=l), productionStmtsNil(location=l), stmts), '}', location=l);
  
  local stmts :: [ProductionStmt] =
    [
      returnDef('return', e, ';', location=l)
    ];

  -- emit function taking a ID tree GENERATING FUNCTION and check e on it
  forwards to 
    foldr(
      appendAGDcl(_, _, location=top.location),
      emptyAGDcl(location=top.location),
      fundcl ::
        map(
          generateTestFor(_, generatedName, l, testSuite), prods));

}

function generateTestFor
AGDcl ::= d::ValueDclInfo  testfunname::String  l::Location  testSuite::Name
{
  local generatedName :: String = "genSpecificProduction" ++ substring(lastIndexOf(":", d.fullName) + 1, length(d.fullName), d.fullName) ++ toString(genIntT());
  
  local sig :: FunctionSignature =
    -- id ::= 
    functionSignature(
      nilConstraint(location=l), '=>',
      functionLHS(typerepTypeExpr(boolType(), location=l), location=l),
      '::=',
      productionRHSNil(location=l),
      location=l);
  
  local body :: ProductionBody =
    productionBody('{', foldl(productionStmtsSnoc(_, _, location=l), productionStmtsNil(location=l), stmts), '}', location=l);
  
  local stmts :: [ProductionStmt] =
    [
      shortLocalDecl('local', name("current__depth", l), '::', typerepTypeExpr(intType(), location=l), '=', intConst(terminal(Int_t, "1"), location=l), ';', location=l),
      returnDef('return', mkStrFunctionInvocation(l, testfunname, [deriveGenerateOn(d, l)]), ';', location=l)
    ];

  -- emit function 'testArbitraryProduction_ID' that generates a production-rooted tree, and calls above generating function on it
  -- emit test case calling 'repeatTestTimes(testArbitraryProduction_ID, 100)'
  return 
    foldr(
      appendAGDcl(_, _, location=l),
      emptyAGDcl(location=l),
      [
        functionDcl(
          'function',
          name(generatedName, l),
          sig,
          body, location=l),
        equalityTest2_p(
          terminal(EqualityTest_t, "equalityTest", l), '(',
          mkStrFunctionInvocation(l, "repeatTestTimes", [baseExpr(qName(l, generatedName), location=l), intConst(terminal(Int_t, "10"), location=l)]), ',',
          trueConst('true', location=l), ',',
          booleanTypeExpr('Boolean', location=l), ',',
          testSuite, ')', ';', location=l
        )
      ]
      );
}



