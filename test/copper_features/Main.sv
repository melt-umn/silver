grammar copper_features;

imports silver:testing ;
imports lib:extcore ;

import copper_features:test_layout;
--import copper_features:mdatests; -- temporarily disable the mda tests until I have a chance to fix them.

mainTestSuite copper_tests ;

{- Needed: 
layout tests
precedence / associativity tests
lexer classes & dominates submits tests
production precedence / operator tests
-}
