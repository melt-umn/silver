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

  forwards to 
  appendAGDcl(
   functionDcl(
    -- function main
    'function', name("main"),
    -- IOVal<Integer> ::= args::[String]  mainIO::IOToken
    functionSignature(
      nilConstraint(), '=>',
     functionLHS(
       appTypeExpr(
         nominalTypeExpr(qNameTypeId(terminal(IdUpper_t, "IOVal"))),
         bTypeList('<', typeListSingle(integerTypeExpr('Integer')), '>'))),
     '::=',
     productionRHSCons(
      productionRHSElemType(elemNotShared(), listTypeExpr('[', stringTypeExpr('String'), ']')),
      productionRHSCons(
       productionRHSElem(
        elemNotShared(),
        name("mainIO"),
        '::', typerepTypeExpr(ioForeignType)),
       productionRHSNil()))),
    -- body::ProductionBody 
   productionBody('{',
    foldl(productionStmtsSnoc(_, _), productionStmtsNil(), [
     --  local testResults :: TestSuite;
     localAttributeDcl(
      'local', 'attribute', name("testResults"), '::',
      nominalTypeExpr( qNameTypeId(terminal(IdUpper_t,"TestSuite"))), ';'),
     -- testResults = name()
     valueEq( qName("testResults"), '=', 
                 applicationEmpty( baseExpr( qNameId(nameIdLower(nme))), 
                  '(', ')'),
                 ';'),
     -- testResults.ioIn = ...
     attributeDef( 
         concreteDefLHS( qName("testResults")), '.', qNameAttrOccur(qName("ioIn")),
         '=', mkNameExpr("mainIO"), ';'),
     -- return ...
     returnDef('return',
        mkStrFunctionInvocation("ioval",
         [
          mkStrFunctionInvocation("exitT",
           [ attrAcc("testResults","numFailed"),
             mkStrFunctionInvocation("printT",
              [ foldStringExprs(
                 [ strCnst("\n\n"),
                   strCnst("============================================================\n"),
                   strCnst("Test Results:\n"), 
                   attrAcc("testResults","msg"),
                   strCnst("\n\n"), 
                   strCnst("Passed "), 
                   Silver_Expr { silver:core:integerToString(testResults.numPassed) },
                   strCnst(" tests out of "), 
                   Silver_Expr { silver:core:integerToString(testResults.numTests) },
                   strCnst("\n"), 
                   strCnst("============================================================\n") 
                 ]),
                attrAcc("testResults", "ioOut")
              ])
           ]),
           intConst( terminal(Int_t, "0")) 
         ]), 
         ';')
    ]), '}')),

  makeTestSuite_p( 'makeTestSuite', nme, ';'));
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
