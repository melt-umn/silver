grammar silver:extension:testing;

import silver:definition:core;
import silver:definition:env;
import silver:definition:concrete_syntax;
import silver:definition:type;
import silver:definition:type:syntax;

import silver:modification:ffi;
import silver:modification:collection;
import silver:extension:list;

terminal MainTestSuite_t 'mainTestSuite' lexer classes {KEYWORD};
terminal MakeTestSuite_t 'makeTestSuite' lexer classes {KEYWORD};

concrete production makeTestSuite_p
top::AGDcl ::= 'makeTestSuite' nme::IdLower_t ';'
{
  top.pp = "makeTestSuite " ++ nme.lexeme ++ ";\n";

  local sig :: ProductionSignature =
    productionSignature(
      productionLHS(name("t", top.location), '::',
        nominalType(qNameTypeId(terminal(IdUpper_t, "TestSuite", top.location), location=top.location), botlNone(location=top.location), location=top.location), location=top.location),
     '::=', productionRHSNil(location=top.location), location=top.location);

  local bod :: [ProductionStmt] =
    [forwardsTo('forwards', 'to', mkStrFunctionInvocation(top.location, "testsAsNT", [mkNameExpr("testsToPerform", top.location)]), ';', location=top.location),
     collectionAttributeDclProd('production', 'attribute', name("testsToPerform", top.location), '::',
       listType('[', nominalType(qNameTypeId(terminal(IdUpper_t, "Test", top.location), location=top.location), botlNone(location=top.location), location=top.location), ']', location=top.location),
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
  top.pp = "mainTestSuite " ++ nme.lexeme ++ ";\n";

  forwards to 
  appendAGDcl(
   functionDcl(
    -- function main
    'function', name("main", top.location),
    -- IOVal<Integer> ::= args::[String]  mainIO::IO
    functionSignature(
     functionLHS(
       nominalType( 
         qNameTypeId(terminal(IdUpper_t, "IOVal", top.location), location=top.location),
         botlSome('<', typeListSingle(integerType('Integer', location=top.location), location=top.location), '>', location=top.location), location=top.location), location=top.location),
     '::=',
     productionRHSCons(
      productionRHSElemType(listType('[', stringType('String', location=top.location), ']', location=top.location), location=top.location),
      productionRHSCons(
       productionRHSElem(
        name("mainIO", top.location),
        '::', typerepType(foreignTypeExp("core:IO", []), location=top.location), location=top.location),
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
      nominalType( qNameTypeId(terminal(IdUpper_t,"TestSuite", top.location), location=top.location), botlNone(location=top.location), location=top.location), ';', location=top.location),
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
                   mkStrFunctionInvocation(top.location, "toStringFromInteger", [ attrAcc("testResults","numPassed", top.location) ]),
                   strCnst(" tests out of "), 
                   mkStrFunctionInvocation(top.location, "toStringFromInteger", [ attrAcc("testResults","numTests", top.location) ]),
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
