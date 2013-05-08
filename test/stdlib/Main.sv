grammar stdlib ;

imports silver:testing ;
imports lib:extcore ;

import stdlib:treemap; -- run test tests, too.
import stdlib:fixedmap;
import stdlib:cmdargs;
import stdlib:deque;
import stdlib:pplib;
import stdlib:rawtreeset;
import stdlib:rawtreemap;
import stdlib:rawgraph;
import stdlib:xml;


mainTestSuite core_tests ;

equalityTest ( 1 + 10, 4 + 7, Integer, core_tests ) ;

equalityTest ( 31 + 10, 34 + 7 , Integer, core_tests ) ;




