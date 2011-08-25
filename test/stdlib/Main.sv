grammar stdlib ;

imports silver:testing ;
imports lib:extcore ;

import stdlib:treemap; -- run test tests, too.
import stdlib:cmdargs;
import stdlib:deque;


mainTestSuite core_tests ;

equalityTest ( 1 + 10, 4 + 7, Integer, core_tests ) ;

equalityTest ( 31 + 10, 34 + 7 , Integer, core_tests ) ;




