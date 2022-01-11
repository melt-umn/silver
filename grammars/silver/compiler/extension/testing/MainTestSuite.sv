grammar silver:compiler:extension:testing;

import silver:compiler:definition:core;
import silver:compiler:definition:env;
import silver:compiler:definition:concrete_syntax;
import silver:compiler:definition:type;
import silver:compiler:definition:type:syntax;

import silver:compiler:modification:ffi;
import silver:compiler:modification:collection;
import silver:compiler:modification:list;

terminal MainTestSuite_t 'mainTestSuite' lexer classes {KEYWORD};
terminal MakeTestSuite_t 'makeTestSuite' lexer classes {KEYWORD};

concrete production makeTestSuite_p
top::AGDcl ::= 'makeTestSuite' nme::IdLower_t ';'
{
  top.unparse = "makeTestSuite " ++ nme.lexeme ++ ";\n";

  local sig :: ProductionSignature =
    productionSignature(
      nilConstraint(location=top.location), '=>',
      productionLHS(name("t", top.location), '::',
        nominalTypeExpr(qNameTypeId(terminal(IdUpper_t, "TestSuite", top.location), location=top.location), location=top.location), location=top.location),
     '::=', productionRHSNil(location=top.location), location=top.location);

  local bod :: [ProductionStmt] =
    [forwardsTo('forwards', 'to', mkStrFunctionInvocation(top.location, "testsAsNT", [mkNameExpr("testsToPerform", top.location)]), ';', location=top.location),
     collectionAttributeDclProd('production', 'attribute', name("testsToPerform", top.location), '::',
       listTypeExpr('[', nominalTypeExpr(qNameTypeId(terminal(IdUpper_t, "Test", top.location), location=top.location), location=top.location), ']', location=top.location),
       'with', plusplusOperator('++', location=top.location), ';', location=top.location),
     valContainsBase(qName(top.location, "testsToPerform"), ':=', emptyList('[',']', location=top.location), ';', location=top.location)
    ];

  forwards to
    productionDcl('abstract', 'production', nameIdLower(nme, location=nme.location), sig,
      productionBody('{',
        foldl(productionStmtsSnoc(_, _, location=top.location), productionStmtsNil(location=top.location), bod), '}', location=top.location), location=top.location);

  {-
    abstract production core_tests
    t::TestSuite ::= 
    {
      forwards to testsAsNT(testsToPerform);
       production attribute testsToPerform :: [Test] with ++;
       testsToPerform := [];
    }
  -}
}

concrete production mainTestSuite_p
top::AGDcl ::= 'mainTestSuite' nme::IdLower_t ';'
{
  top.unparse = "mainTestSuite " ++ nme.lexeme ++ ";\n";

  forwards to 
  appendAGDcl(
   functionDcl(
    -- function main
    'function', name("main", top.location),
    -- IOVal<Integer> ::= args::[String]  mainIO::IO
    functionSignature(
      nilConstraint(location=top.location), '=>',
     functionLHS(
       appTypeExpr(
         nominalTypeExpr(qNameTypeId(terminal(IdUpper_t, "IOVal", top.location), location=top.location), location=top.location),
         bTypeList('<', typeListSingle(integerTypeExpr('Integer', location=top.location), location=top.location), '>', location=top.location), location=top.location), location=top.location),
     '::=',
     productionRHSCons(
      productionRHSElemType(listTypeExpr('[', stringTypeExpr('String', location=top.location), ']', location=top.location), location=top.location),
      productionRHSCons(
       productionRHSElem(
        name("mainIO", top.location),
        '::', typerepTypeExpr(ioForeignType, location=top.location), location=top.location),
       productionRHSNil(location=top.location),
      location=top.location),
     location=top.location),
    location=top.location),
    -- body::ProductionBody 
   productionBody('{',
    foldl(productionStmtsSnoc(_, _, location=top.location), productionStmtsNil(location=top.location), [
     --  local testResults :: TestSuite;
     localAttributeDcl(
      'local', 'attribute', name("testResults", top.location), '::',
      nominalTypeExpr( qNameTypeId(terminal(IdUpper_t,"TestSuite", top.location), location=top.location), location=top.location), ';', location=top.location),
     -- testResults = name()
     valueEq( qName(top.location, "testResults"), '=', 
                 applicationEmpty( baseExpr( qNameId(nameIdLower(nme, location=top.location), location=top.location), location=top.location), 
                  '(', ')', location=top.location),
                 ';', location=top.location),
     -- testResults.ioIn = ...
     attributeDef( 
         concreteDefLHS( qName(top.location, "testResults"), location=top.location), '.', qNameAttrOccur(qName(top.location, "ioIn"), location=top.location),
         '=', mkNameExpr("mainIO", top.location), ';', location=top.location),
     -- return ...
     returnDef('return',
        mkStrFunctionInvocation(top.location, "ioval",
         [
          mkStrFunctionInvocation(top.location, "exit",
           [ attrAcc("testResults","numFailed", top.location),
             mkStrFunctionInvocation(top.location, "print", 
              [ foldStringExprs(
                 [ strCnst("\n\n"),
                   strCnst("============================================================\n"),
                   strCnst("Test Results:\n"), 
                   attrAcc("testResults","msg", top.location),
                   strCnst("\n\n"), 
                   strCnst("Passed "), 
                   Silver_Expr { silver:core:integerToString(testResults.numPassed) },
                   strCnst(" tests out of "), 
                   Silver_Expr { silver:core:integerToString(testResults.numTests) },
                   strCnst("\n"), 
                   strCnst("============================================================\n") 
                 ]),
                attrAcc("testResults", "ioOut", top.location)
              ])
           ]),
           intConst( terminal(Int_t, "0", top.location), location=top.location) 
         ]), 
         ';', location=top.location)
    ]), '}', location=top.location), location=top.location),

  makeTestSuite_p( 'makeTestSuite', nme, ';', location=top.location),
  location=top.location);
}


{-
function main
IO ::= args::String mainIO::IO
{
 local testResults :: TestSuite = core_tests();
 testResults.ioIn = mainIO;

 return
   exit( testResults.numTests - testResults.numPassed,
     print("\n\n" ++
            "============================================================\n" ++
            "Test results: \n" ++
            testResults.msg ++ "\n\n" ++ 
            "Passed " ++ toString(testResults.numPassed) ++
            " tests out of " ++ 
            toString(testResults.numTests) ++ "\n" ++
            "============================================================\n",
            testResults.ioOut) 
);
}

abstract production core_tests
t::TestSuite ::= 
{
 forwards to tsAsNT;
 local tsAsNT :: TestSuite = testsAsNT( testsToPerform);
 production attribute testsToPerform :: [ Test ] with ++;
 testsToPerform := [ ];
}

mainTestSuite core_tests;
-}
