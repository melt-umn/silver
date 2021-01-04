grammar stdlib ;

imports silver:testing ;

import stdlib:cmdargs;
import stdlib:deque;
import stdlib:pplib;
import stdlib:treeset;
import stdlib:treemap;
import stdlib:graph;
import stdlib:xml;


mainTestSuite core_tests ;

equalityTest ( 1 + 10, 4 + 7, Integer, core_tests ) ;

equalityTest ( 31 + 10, 34 + 7 , Integer, core_tests ) ;



