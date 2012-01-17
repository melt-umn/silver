-- This will be used in issue #17.
-- The following generates an inappropriate error message.
-- The intTestProd is defined in FuncProdTypes.sv

wrongCode "Type of first and second experssions in equalityTest do not match." {

equalityTest ( intTestProd(0), 0, Integer, silver_tests ) ;

}
