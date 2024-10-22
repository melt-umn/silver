grammar silver_features;

imports silver:testing;

import silver_features:defs;
import silver_features:cond;
import silver_features:anno;
import silver_features:rewrite;
import silver_features:treegen;

mainTestSuite silver_tests;

-- Some basic aspects of arithmetic

-- Truncates, not rounds
equalityTest( toInteger(0.1), 0, Integer, silver_tests );
equalityTest( toInteger(0.99), 0, Integer, silver_tests );
equalityTest( 1 / 3, 0, Integer, silver_tests );
equalityTest( 4 / 3, 1, Integer, silver_tests );

-- Modulo works as expected
equalityTest( 4 % 3, 1, Integer, silver_tests );
-- Cares about sign of first operand, does NOT care for second operand!
equalityTest( -4 % 3, -1, Integer, silver_tests );
equalityTest( 4 % -3, 1, Integer, silver_tests );
equalityTest( -4 % -3, -1, Integer, silver_tests );

-- String tests
equalityTest( "abc" == "abc", true, Boolean, silver_tests );
equalityTest( "abc" == "ABC", false, Boolean, silver_tests );
equalityTest( "abc" != "ABC", true, Boolean, silver_tests );

-- Casting floats and ints should compile correctly to objects, not primitives
-- (previously mostly worked due to autoboxing, but exposed when doing immediate equality checks with the cast on the left-hand side)
equalityTest( toInteger(0.0) == 0, true, Boolean, silver_tests );
equalityTest( toFloat(0) == 0.0, true, Boolean, silver_tests );

-- Basic oddball tests, just to have called the functions.
equalityTest( genInt() >= 0, true, Boolean, silver_tests );
equalityTest( genRand() >= 0.0, true, Boolean, silver_tests );


