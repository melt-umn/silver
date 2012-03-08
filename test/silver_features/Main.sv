grammar silver_features;

imports silver:testing;
imports lib:extcore;

import silver_features:defs;
import silver_features:cond;

mainTestSuite silver_tests;

-- Some basic aspects of arithmetic

-- Truncates, not rounds
equalityTest( toInt(0.1), 0, Integer, silver_tests );
equalityTest( toInt(0.99), 0, Integer, silver_tests );
equalityTest( 1 / 3, 0, Integer, silver_tests );
equalityTest( 4 / 3, 1, Integer, silver_tests );

-- Modulo works as expected
equalityTest( 4 % 3, 1, Integer, silver_tests );
-- Works on floats, too!
equalityTest( 4.0 % 3.0, 1.0, Float, silver_tests ); -- careful, float equality
equalityTest( 4.5 % 1.0, 0.5, Float, silver_tests ); -- careful, float equality
-- Cares about sign of first operand, does NOT care for second operand!
equalityTest( -4 % 3, -1, Integer, silver_tests );
equalityTest( 4 % -3, 1, Integer, silver_tests );
equalityTest( -4 % -3, -1, Integer, silver_tests );

