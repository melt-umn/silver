grammar copper_features:test_layout:lookahead;

imports silver:testing ;
imports copper_features;
imports copper_features:test_layout:lookahead:host;
imports copper_features:test_layout:lookahead:ext;

parser parse_host :: Stmt {
  copper_features:test_layout:lookahead:host;
}

parser parse_ext :: Stmt {
  copper_features:test_layout:lookahead:host;
  copper_features:test_layout:lookahead:ext;
}

copper_mda test_ext(parse_host) {
  copper_features:test_layout:lookahead:ext;
}

global testProg::String = s"""
{
  a % b;
}
""";

equalityTest ( parse_host(testProg, "").parseSuccess, true, Boolean, copper_tests );
--equalityTest ( parse_host(testProg, "").parseErrors, "", String, copper_tests );

-- Unexpected behavior here: including ext causes parse to fail
equalityTest ( parse_ext(testProg, "").parseSuccess, false, Boolean, copper_tests );
--equalityTest ( parse_ext(testProg, "").parseErrors, "", String, copper_tests );
