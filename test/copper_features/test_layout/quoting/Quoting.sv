grammar copper_features:test_layout:quoting;

imports silver:testing ;
imports copper_features hiding A;

-- This grammar mimics embedding one host language as an extension to another,
-- with quote/antiquote productions in both directions.

terminal WSA_t /[\n\t\r\ ]*/;
terminal WSB_t /[\n\t\r\ ]*/;

terminal AChar 'a';
terminal BChar 'b';
terminal EndAChar 'enda';
terminal EndBChar 'endb';

terminal AConst_t /[0-9]+/;
terminal APlus_t '+' association=left;
terminal BConst_t /[0-9]+/;
terminal BPlus_t '+' association=left;


nonterminal A layout { WSA_t };
nonterminal B layout { WSB_t };

concrete production quoteA
top::A ::= 'b' B 'endb'
layout {WSB_t}
{}
concrete production addA
top::A ::= A APlus_t A
{}
concrete production constA
top::A ::= AConst_t
{}


concrete production quoteB
top::B ::= 'a' A 'enda'
layout {WSA_t}
{}
concrete production addB
top::B ::= B BPlus_t B
{}
concrete production constB
top::B ::= BConst_t
{}

parser parse_quoting :: A {
  copper_features:test_layout:quoting;
}

equalityTest ( parse_quoting("5 + b 42 + a 17 enda endb", "").parseSuccess, true, Boolean, copper_tests );
equalityTest ( parse_quoting(" 5 + b 42 + a 17 enda endb ", "").parseSuccess, true, Boolean, copper_tests );
equalityTest ( parse_quoting("3 + a 42 enda", "").parseSuccess, false, Boolean, copper_tests );
