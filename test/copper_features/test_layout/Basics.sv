grammar copper_features:test_layout;

import copper_features:test_layout:basics hiding basic_parse;

parser basic_parse :: BRoot {
  copper_features:test_layout:basics;
  copper_features:test_layout; -- get the ignore terminal
}

equalityTest ( basic_parse("", "").parseSuccess, false, Boolean, copper_tests );
equalityTest ( basic_parse("asdfasdf", "").parseSuccess, true, Boolean, copper_tests );
equalityTest ( basic_parse(" asdfasdf", "").parseSuccess, true, Boolean, copper_tests );
equalityTest ( basic_parse("asdfasdf ", "").parseSuccess, true, Boolean, copper_tests );
equalityTest ( basic_parse(" asdfasdf ", "").parseSuccess, true, Boolean, copper_tests );
equalityTest ( basic_parse(" asdf asdf ", "").parseSuccess, false, Boolean, copper_tests );

{-

FINDINGS:

- All ignore terminals apply to the start nonterminal, unconditionally (currently)
- All layout on the start nonterminal is permitted BEFORE and AFTER it
- Corollary: ignore terminals are the only way to permit things before/after program body

- All terminals listed in 'layout' on productions are permitted IN BETWEEN each terminal of the production
- Corollary: a production has no control on the layout BEFORE or AFTER it.
- The default EmptyString appears to be omitted when specifying manual layout... :/


SUGGESTED CHANGES:
1. Nuke EmptyString. Let's deal without that magic. Simple solution, usually: make all whitespace terminals * instead of +
2. Add 'layout' to 'parser' declaration, have this affect START nonterminal layout.
3. Find a better way of dealing with layout than global ignore terminals?
   (Suggestion: nonterminals can be given layout, and all exported ignore terminals at nonterminal declaration grammar are auto-included if left unspecified.)

-}
