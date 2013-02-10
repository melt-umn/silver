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
top::AGDcl ::= 'makeTestSuite' name::IdLower_t ';'
{
  top.pp = "makeTestSuite " ++ name.lexeme ++ ";\n";
  top.location = loc(top.file, $1.line, $1.column);

  local sig :: ProductionSignature =
    productionSignature(
      productionLHS(mkName("t"), '::',
        nominalType(qNameUpperId(terminal(IdUpper_t, "TestSuite")), botlNone())),
     '::=', productionRHSNil());

  local bod :: [ProductionStmt] =
    [forwardsTo('forwards', 'to', prodFuncCall("testsAsNT", [mkNameExpr("testsToPerform")]), ';'),
     collectionAttributeDclProd('production', 'attribute', mkName("testsToPerform"), '::',
       listType('[', nominalType(qNameUpperId(terminal(IdUpper_t, "Test")), botlNone()), ']'),
       'with', plusplusOperator('++'), ';'),
     valContainsBase(qName(top.location, "testsToPerform"), ':=', emptyList('[',']'), ';')
    ];

  forwards to
    productionDcl('abstract', 'production', nameIdLower(name), sig,
      defaultProductionBody(
        foldr(productionStmtsCons, productionStmtsNone(), bod)));

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
top::AGDcl ::= 'mainTestSuite' name::IdLower_t ';'
{
  top.pp = "mainTestSuite " ++ name.lexeme ++ ";\n";
  top.location = loc(top.file, $1.line, $1.column);

  forwards to 
  appendAGDcl(
   functionDcl(
    -- function main
    'function', nameIdLower(terminal(IdLower_t,"main")),
    -- IOVal<Integer> ::= args::[String]  mainIO::IO
    functionSignature(
     functionLHS(
       nominalType( 
         qNameUpperId(terminal(IdUpper_t, "IOVal")) ,
         botlSome('<', typeListSingle(integerType('Integer')), '>' ))),
     '::=',
     productionRHSCons(
      productionRHSElemType(listType('[', stringType('String'), ']')),
      productionRHSCons(
       productionRHSElem(
        nameIdLower(terminal(IdLower_t, "mainIO")),
        '::', typerepType(foreignTypeExp("core:IO", []))),
       productionRHSNil()
      )
     )
    )
    ,
    -- body::ProductionBody 
   defaultProductionBody (
    productionStmtsCons (
     --  local testResults :: TestSuite;
     localAttributeDcl (
      'local', 'attribute', nameIdLower(terminal(IdLower_t,"testResults")), '::',
      nominalType( qNameUpperId (terminal(IdUpper_t,"TestSuite")), botlNone()), ';'
     ),
     productionStmtsCons (
      -- testResults = name()
      valueEq ( qName(top.location, "testResults"), '=', 
                 applicationEmpty ( baseExpr( qNameId(nameIdLower(name))) , 
                  '(', ')' ) ,
                 ';' ) ,
      productionStmtsCons (
       attributeDef ( 
         concreteDefLHS( qName(top.location, "testResults")), '.', qName(top.location, "ioIn"),
         '=' , mkNameExpr("mainIO"), ';' ) ,
      productionStmts (
       returnDef ('return' ,
        prodFuncCall( "ioval" ,
         [
          prodFuncCall( "exit" ,
           [ attrAcc("testResults","numFailed") ,
             prodFuncCall ("print", 
              [ foldStringExprs (
                 [ strCnst ("\n\n"),
                   strCnst ("============================================================\n") ,
                   strCnst ("Test Results:\n"), 
                   attrAcc("testResults","msg"),
                   strCnst ("\n\n"), 
                   strCnst ("Passed "), 
                   prodFuncCall("toStringFromInteger", [ attrAcc("testResults","numPassed") ]),
                   strCnst (" tests out of "), 
                   prodFuncCall("toStringFromInteger", [ attrAcc("testResults","numTests") ]),
                   strCnst ("\n"), 
                   strCnst ("============================================================\n") 
                 ] ) ,
                attrAcc("testResults", "ioOut" )     
              ] )
           ] ),
           intConst ( terminal(Int_t, "0") ) 
         ] ) , 
         ';' )
    ) ) ) )
   )
  )
  ,


  makeTestSuite_p ( 'makeTestSuite', name, ';' )

  );
}


{-
function main
IO ::= args::String mainIO::IO
{
 local testResults :: TestSuite = core_tests( );
 testResults.ioIn = mainIO;

 return
   exit ( testResults.numTests - testResults.numPassed,
     print ("\n\n" ++
            "============================================================\n" ++
            "Test results: \n" ++
            testResults.msg ++ "\n\n" ++ 
            "Passed " ++ toString (testResults.numPassed) ++
            " tests out of " ++ 
            toString (testResults.numTests) ++ "\n" ++
            "============================================================\n",
            testResults.ioOut ) 
   );
}

abstract production core_tests
t::TestSuite ::= 
{
 forwards to tsAsNT;
 local tsAsNT :: TestSuite = testsAsNT ( testsToPerform );
 production attribute testsToPerform :: [ Test ] with ++;
 testsToPerform := [ ];
}

mainTestSuite core_tests;
-}
