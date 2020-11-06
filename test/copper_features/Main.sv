grammar copper_features;

imports silver:testing ;
imports lib:extcore ;

import copper_features:test_layout;
import copper_features:mdatests;
import copper_features:token_pushing;
import copper_features:disambiguation_class;
import copper_features:lexer_class;

mainTestSuite copper_tests ;

{- Needed: 
layout tests
precedence / associativity tests
lexer classes & dominates submits tests
production precedence / operator tests
-}
