grammar copper_features:mdatests:ext;

import copper_features:mdatests:host;

marking terminal C 'c' lexer classes ColonSep;

terminal D 'd';

concrete production ccc
top::Root ::= 'c' Root
{}

{- uncommenting this will make it fail

concrete production ddd
top::Root ::= 'b' 'd' 'b' Root
{}

-}

