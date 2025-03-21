grammar silver:compiler:extension:testing;

import silver:compiler:definition:core;
import silver:compiler:definition:env;
import silver:compiler:definition:concrete_syntax;
import silver:compiler:definition:type;
import silver:compiler:definition:type:syntax;

import silver:compiler:modification:ffi;
import silver:compiler:modification:collection;
import silver:compiler:modification:list;

import silver:compiler:extension:convenience;
import silver:compiler:extension:do_notation hiding DoDoubleColon_t;

terminal MainTestSuite_t 'mainTestSuite' lexer classes {KEYWORD};
terminal MakeTestSuite_t 'makeTestSuite' lexer classes {KEYWORD};

concrete production makeTestSuite_p
top::AGDcl ::= 'makeTestSuite' nme::IdLower_t ';'
{
  top.unparse = "makeTestSuite " ++ nme.lexeme ++ ";\n";

  nondecorated local sig::ProductionSignature =
    productionSignature(
      nilConstraint(), '=>',
      productionLHS(name("t"), '::',
        nominalTypeExpr(qNameTypeId(terminal(IdUpper_t, "TestSuite")))),
     '::=', productionRHSNil());

  local bod :: [ProductionStmt] =
    [forwardsTo('forwards', 'to', mkStrFunctionInvocation("testsAsNT", [mkNameExpr("testsToPerform")]), ';'),
     collectionAttributeDclProd('production', 'attribute', name("testsToPerform"), '::',
       listTypeExpr('[', nominalTypeExpr(qNameTypeId(terminal(IdUpper_t, "Test"))), ']'),
       'with', plusplusOperator('++'), ';'),
     valContainsBase(qName("testsToPerform"), ':=', emptyList('[',']'), ';')
    ];

  forwards to
    productionDcl('abstract', 'production', nameIdLower(nme), 
      productionImplementsNone(), sig,
      productionBody('{',
        foldl(productionStmtsSnoc(_, _), productionStmtsNil(), bod), '}'));

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

  local mainDcl::AGDcl = Silver_AGDcl {
    function main
    IOVal<a> ::= args::[String] mainIO::IOToken
    {
      local testResults :: TestSuite = $QName{qNameId(nameIdLower(nme))}();
      testResults.ioIn = mainIO;

      return evalIO(
        do {
          print("\n\n" ++
                "============================================================\n" ++
                "Test results: \n" ++
                testResults.msg ++ "\n\n" ++ 
                "Passed " ++ toString(testResults.numPassed) ++
                " tests out of " ++ 
                toString(testResults.numTests) ++ "\n" ++
                "============================================================\n");

          exit( testResults.numTests - testResults.numPassed );    
        },
        testResults.ioOut
      );
    }
  };

  forwards to appendAGDcl(@mainDcl, makeTestSuite_p( 'makeTestSuite', nme, ';'));
}


{-
function main
IOToken ::= args::String mainIO::IOToken
{
 local testResults :: TestSuite = core_tests();
 testResults.ioIn = mainIO;

 return
   exitT( testResults.numTests - testResults.numPassed,
     printT("\n\n" ++
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
