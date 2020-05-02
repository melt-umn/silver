grammar copper_features:test_layout;

import copper_features:test_layout:basics hiding basic_parse;

parser basic_parse :: BRoot {
  copper_features:test_layout:basics;
  copper_features:test_layout; -- Get the ignore terminal, should have no effect
}

parser basic_parse_space :: BRoot {
  copper_features:test_layout:basics;
  copper_features:test_layout;
  layout {NormalWhiteSpace_t}; -- Now add it to the start layout
}

equalityTest ( basic_parse("", "").parseSuccess, false, Boolean, copper_tests );
equalityTest ( basic_parse("asdfasdf", "").parseSuccess, true, Boolean, copper_tests );
equalityTest ( basic_parse(" asdfasdf", "").parseSuccess, false, Boolean, copper_tests );
equalityTest ( basic_parse("asdfasdf ", "").parseSuccess, false, Boolean, copper_tests );
equalityTest ( basic_parse(" asdfasdf ", "").parseSuccess, false, Boolean, copper_tests );
equalityTest ( basic_parse(" asdf asdf ", "").parseSuccess, false, Boolean, copper_tests );

equalityTest ( basic_parse_space("", "").parseSuccess, false, Boolean, copper_tests );
equalityTest ( basic_parse_space("asdfasdf", "").parseSuccess, true, Boolean, copper_tests );
equalityTest ( basic_parse_space(" asdfasdf", "").parseSuccess, true, Boolean, copper_tests );
equalityTest ( basic_parse_space("asdfasdf ", "").parseSuccess, true, Boolean, copper_tests );
equalityTest ( basic_parse_space(" asdfasdf ", "").parseSuccess, true, Boolean, copper_tests );
equalityTest ( basic_parse_space(" asdf asdf ", "").parseSuccess, false, Boolean, copper_tests );
