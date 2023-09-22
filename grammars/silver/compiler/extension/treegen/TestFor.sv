
import silver:compiler:extension:testing;

-- TODO: Resurrect this at some point

{-
terminal TestFor_T 'testFor';


concrete production testforagdcl
top::AGDcl ::= 'testFor' testSuite::Name ':' n::Name '::' id::QName ',' e::Expr ';'
{
  top.unparse = s"testFor ${testSuite.unparse} : ${n.unparse} :: ${id.unparse}, ${e.unparse};";
  top.defs := [];
  top.moduleNames := [];
  top.flowDefs := [];
  
  forward.env = newScopeEnv(forward.defs, top.env);



  -- all known productions, including forwarding ones
  local prods :: [ValueDclInfo] = getKnownProds(id.lookupType.fullName, top.env);

  local l :: Location = top.location;
  local generatedName :: String = "checkPropOn" ++ id.name ++ toString(genInt());
  
  local fundcl :: AGDcl =
    functionDcl(
      'function',
      name(generatedName, l),
      sig,
      body);

  local sig :: FunctionSignature =
    functionSignature(
      nilConstraint(), '=>',
      functionLHS(typerepTypeExpr(boolType())),
      '::=',
      productionRHSCons(
        productionRHSElem(n, '::', typerepTypeExpr(id.lookupType.typeScheme.typerep)),
        productionRHSNil()),
      location=l);
  
  local body :: ProductionBody =
    productionBody('{', foldl(productionStmtsSnoc(_, _), productionStmtsNil(), stmts), '}');
  
  local stmts :: [ProductionStmt] =
    [
      returnDef('return', e, ';')
    ];

  -- emit function taking a ID tree GENERATING FUNCTION and check e on it
  forwards to 
    foldr(
      appendAGDcl(_, _),
      emptyAGDcl(),
      fundcl ::
        map(
          generateTestFor(_, generatedName, l, testSuite), prods));

}

function generateTestFor
AGDcl ::= d::ValueDclInfo  testfunname::String  l::Location  testSuite::Name
{
  local generatedName :: String = "genSpecificProduction" ++ substring(lastIndexOf(":", d.fullName) + 1, length(d.fullName), d.fullName) ++ toString(genInt());
  
  local sig :: FunctionSignature =
    -- id ::= 
    functionSignature(
      nilConstraint(), '=>',
      functionLHS(typerepTypeExpr(boolType())),
      '::=',
      productionRHSNil(),
      location=l);
  
  local body :: ProductionBody =
    productionBody('{', foldl(productionStmtsSnoc(_, _), productionStmtsNil(), stmts), '}');
  
  local stmts :: [ProductionStmt] =
    [
      shortLocalDecl('local', name("current__depth"), '::', typerepTypeExpr(intType()), '=', intConst(terminal(Int_t, "1")), ';'),
      returnDef('return', mkStrFunctionInvocation(l, testfunname, [deriveGenerateOn(d, l)]), ';')
    ];

  -- emit function 'testArbitraryProduction_ID' that generates a production-rooted tree, and calls above generating function on it
  -- emit test case calling 'repeatTestTimes(testArbitraryProduction_ID, 100)'
  return 
    foldr(
      appendAGDcl(_, _),
      emptyAGDcl(),
      [
        functionDcl(
          'function',
          name(generatedName, l),
          sig,
          body),
        equalityTest2_p(
          terminal(EqualityTest_t, "equalityTest", l), '(',
          mkStrFunctionInvocation(l, "repeatTestTimes", [baseExpr(qName(generatedName)), intConst(terminal(Int_t, "10"))]), ',',
          trueConst('true'), ',',
          booleanTypeExpr('Boolean'), ',',
          testSuite, ')', ';', location=l
        )
      ]
      );
}
-}


