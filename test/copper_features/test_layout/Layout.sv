grammar copper_features:test_layout;

imports silver:testing ;
imports copper_features hiding A;
imports copper_features:test_layout:quoting;
imports copper_features:test_layout:lookahead;

ignore terminal NormalWhiteSpace_t /[\n\t\r\ ]+/;

parser layout_parse :: LayoutRoot {
  copper_features:test_layout;
}


terminal WS_t /[\n\t\r\ ]*/;
terminal NoNewlineWhiteSpace_t /[\t\ ]*/;
terminal NewLine_t /\n/;

terminal If 'if';
terminal End 'end';
terminal AChar 'a';
terminal Comma ',';

nonterminal LayoutRoot layout {NoNewlineWhiteSpace_t};
nonterminal OptDelim;

concrete production layIf
top::LayoutRoot ::= 'if' WS_t LayoutRoot NoNewlineWhiteSpace_t OptDelim WS_t LayoutRoot WS_t 'end'
layout { }
{}
concrete production layA
top::LayoutRoot ::= 'a'
{}
concrete production layAA
top::LayoutRoot ::= 'a' 'a'
{}

concrete production optNewline
top::OptDelim ::= NewLine_t
{}
concrete production optComma
top::OptDelim ::= ',' 
{}

equalityTest ( layout_parse("if a a end", "").parseSuccess, false, Boolean, copper_tests );
equalityTest ( layout_parse("if a , a end", "").parseSuccess, true, Boolean, copper_tests );
equalityTest ( layout_parse("if a , a end", "").parseSuccess, true, Boolean, copper_tests );
equalityTest ( layout_parse("if a, a end", "").parseSuccess, true, Boolean, copper_tests );
equalityTest ( layout_parse("if a,a end", "").parseSuccess, true, Boolean, copper_tests );
equalityTest ( layout_parse("if a ,a end", "").parseSuccess, true, Boolean, copper_tests );
equalityTest ( layout_parse("if a\n a end", "").parseSuccess, true, Boolean, copper_tests );
equalityTest ( layout_parse("if a\n \n a end", "").parseSuccess, true, Boolean, copper_tests );
equalityTest ( layout_parse("if a \n \n a end", "").parseSuccess, true, Boolean, copper_tests );
equalityTest ( layout_parse("if a,\n \n a end", "").parseSuccess, true, Boolean, copper_tests );
equalityTest ( layout_parse("if a\n, \n a end", "").parseSuccess, false, Boolean, copper_tests );
equalityTest ( layout_parse("if a\n ,\n a end", "").parseSuccess, false, Boolean, copper_tests );
