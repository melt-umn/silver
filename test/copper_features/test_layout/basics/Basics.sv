grammar copper_features:test_layout:basics;

imports silver:testing ;
imports lib:extcore ;
imports copper_features hiding A;

nonterminal BRoot;
terminal BTerm 'asdf';
terminal IGN 'IGN';

concrete production anASDF
top::BRoot ::= BTerm BTerm
layout { IGN }
{}

parser basic_parse :: BRoot {
  copper_features:test_layout:basics;
}

equalityTest ( basic_parse("", "").parseSuccess, false, Boolean, copper_tests );
equalityTest ( basic_parse("asdfasdf", "").parseSuccess, true, Boolean, copper_tests );

equalityTest ( basic_parse(" asdfasdf", "").parseSuccess, false, Boolean, copper_tests );
equalityTest ( basic_parse("asdfasdf ", "").parseSuccess, false, Boolean, copper_tests );
equalityTest ( basic_parse(" asdfasdf ", "").parseSuccess, false, Boolean, copper_tests );
equalityTest ( basic_parse(" asdf asdf ", "").parseSuccess, false, Boolean, copper_tests );

-- in between only:
equalityTest ( basic_parse("asdfIGNasdf", "").parseSuccess, true, Boolean, copper_tests );
equalityTest ( basic_parse("asdfIGNIGNasdf", "").parseSuccess, true, Boolean, copper_tests );

equalityTest ( basic_parse("IGNasdfasdf", "").parseSuccess, false, Boolean, copper_tests );
equalityTest ( basic_parse("asdfasdfIGN", "").parseSuccess, false, Boolean, copper_tests );
equalityTest ( basic_parse("IGNasdfasdfIGN", "").parseSuccess, false, Boolean, copper_tests );
equalityTest ( basic_parse("IGNasdfIGNasdfIGN", "").parseSuccess, false, Boolean, copper_tests );
equalityTest ( basic_parse("IGNasdfIGNasdf", "").parseSuccess, false, Boolean, copper_tests );

