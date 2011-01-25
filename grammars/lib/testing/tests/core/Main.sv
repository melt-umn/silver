grammar lib:testing:tests:core ;

import lib:testing ;
import lib:extcore ;

mainTestSuite core_tests ;

equalityTest ( 1 + 10, 4 + 7, Integer, core_tests ) ;

equalityTest ( 31 + 10, 34 + 7 , Integer, core_tests ) ;

equalityTest ( nubBy (equalsInteger, [1,2,3,4,3,2,1]), [1,2,3,4], 
               [Integer], core_tests ) ;

equalityTest ( nubBy (equalsInteger, [1,2,3,4]), [1,2,3,4], 
               [Integer], core_tests ) ;

equalityTest ( nubBy (equalsInteger, [ ]), [ ], 
               [Integer], core_tests ) ;


equalityTest ( removeBy (equalsInteger, 2, [1,2,3,4,3,2,1]), [1,3,4,3,1],
               [Integer], core_tests ) ;

equalityTest ( removeBy (equalsInteger, 9, [1,2,3,4,3,2,1]), [1,2,3,4,3,2,1],
               [Integer], core_tests ) ;

equalityTest ( removeBy (equalsInteger, 9, [ ]), [ ],
               [Integer], core_tests ) ;


equalityTest ( removeAllBy (equalsInteger, [], [1,2,3]), [1,2,3],
               [Integer], core_tests ) ;

equalityTest ( removeAllBy (equalsInteger, [1,3,5], [1,2,3,4,5,6]), [2,4,6],
               [Integer], core_tests ) ;

equalityTest ( removeAllBy (equalsInteger, [1,3,5], [1,1,3,4,5,3]), [4],
               [Integer], core_tests ) ;
equalityTest ( removeAllBy (equalsInteger, [1,3,5], [5,3,1,3,5]), [],
               [Integer], core_tests ) ;

function equals1  Boolean ::= x::Integer { return x==1;}
function notEquals1  Boolean ::= x::Integer { return x!=1;}
equalityTest ( takeWhile (equals1, [2,3,1,3,5]), [],
               [Integer], core_tests ) ;
equalityTest ( takeWhile (equals1, [1,1,1,2,3,1,3,5]), [1,1,1],
               [Integer], core_tests ) ;
equalityTest ( takeWhile (equals1, [1,1,1]), [1,1,1],
               [Integer], core_tests ) ;

equalityTest ( dropWhile (equals1, [2,3,1,3,5]), [2,3,1,3,5],
               [Integer], core_tests ) ;
equalityTest ( dropWhile (equals1, [1,1,1,2,3,1,3,5]), [2,3,1,3,5],
               [Integer], core_tests ) ;
equalityTest ( dropWhile (equals1, [1,1,1]), [],
               [Integer], core_tests ) ;

equalityTest ( takeUntil (equals1, [2,3,1,3,5]), [2,3],
               [Integer], core_tests ) ;
equalityTest ( takeUntil (equals1, [1,1,1,2,3,1,3,5]), [],
               [Integer], core_tests ) ;
equalityTest ( takeUntil (equals1, [2,3,4]), [2,3,4],
               [Integer], core_tests ) ;
equalityTest ( takeUntil (equals1, []), [],
               [Integer], core_tests ) ;

equalityTest ( positionOf ( equalsInteger, 1, []), -1, Integer, core_tests) ;
equalityTest ( positionOf ( equalsInteger, 1, [1,2]), 0, Integer, core_tests) ;
equalityTest ( positionOf ( equalsInteger, 3, [0,1,2,3]), 3, Integer, core_tests) ;

equalityTest ( notEqualsList ( notEqualsInteger, [], []), false, Boolean, core_tests) ;
equalityTest ( notEqualsList ( notEqualsInteger, [1], [1]), false, Boolean, core_tests) ;
equalityTest ( notEqualsList ( notEqualsInteger, [1,2], [1,2]), false, Boolean, core_tests) ;

equalityTest ( notEqualsList ( notEqualsInteger, [1], [2]), true, Boolean, core_tests) ;
equalityTest ( notEqualsList ( notEqualsInteger, [1], [1,2]), true, Boolean, core_tests) ;
equalityTest ( notEqualsList ( notEqualsInteger, [1,2], []), true, Boolean, core_tests) ;

{-
function main
IO ::= args::String mainIO::IO
{
 local testResults :: TestSuite = core_tests( ) ;
 testResults.ioIn = mainIO ;

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
   ) ;
}

abstract production core_tests
t::TestSuite ::= 
{
 forwards to tsAsNT ;
 local tsAsNT :: TestSuite = testsAsNT ( testsToPerform ) ;
 production attribute testsToPerform :: [ Test ] with ++ ;
 testsToPerform := [ ] ;
}

aspect production core_tests
t::TestSuite ::=
{ testsToPerform <- [ test1() ] ; }

abstract production test1
t::Test ::=
{
 t.pass = equalsList(equalsInteger, result, expected) ;
 t.msg 
   = "\nTest 1 failed.\n" ++ 
     "Expected:\n   " ++ toStringList (toStringInteger, expected) ++ "\n" ++
     "But result was:\n   " ++ toStringList (toStringInteger, result) ++ "\n";

 -- nubBy (equalsInteger, [1,2,3,4,3,2,1]) == [1,2,3,4]

 local expected :: [Integer] = [1,2,3,4] ;
 local result :: [Integer] = nubBy (equalsInteger, [1,2,3,4,3,2,1]) ;

 forwards to defTest();
}

aspect production core_tests
t::TestSuite ::=
{ testsToPerform <- [ test2() ] ; }

abstract production test2
t::Test ::=
{ t.pass = equalsList(equalsInteger, result, expected) ; t.msg 
   = "\nTest 1 failed.\n" ++ 
     "Expected:\n   " ++ toStringList (toStringInteger, expected) ++ "\n" ++
     "But result was:\n   " ++ toStringList (toStringInteger, result) ++ "\n";

 -- removeBy (equalsInteger, 2 [1,2,3,4,3,2,1]) == [1,3,4,3,1]

 local expected :: [Integer] = [1,3,4,3,1] ;
 local result :: [Integer] = removeBy (equalsInteger, 2, [1,2,3,4,3,2,1]) ;

 forwards to defTest();
}

-}
--aspect production core_tests
--t::TestSuite ::=
--{ testsToPerform <- [ generatedTest() ] ; }

--aspect production generatedTest
--t::Test ::=
--{ --t.pass = equalsInteger (value, expected) ; 
  --t.msg 
   --= "\nTest failed.\n" ++ 
   --  "Expected:\n   " ++ toStringInteger(expected) ++ "\n" ++
   --  "But result was:\n   " ++ toStringInteger(value) ++ "\n";


-- local expected :: Integer = 34 ;
-- local value :: Integer = 1 + 2 ;

-- forwards to defTest();
--}
