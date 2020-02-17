grammar copper_features:test_layout:lookahead:ext;

imports copper_features:test_layout:lookahead:host;

terminal ExtComment_t /% .*/;

marking terminal Ext_t 'ext' dominates Id_t;
terminal Dot_t '.';

concrete production extProd
top::Stmt ::= 'ext' '{' id::Id_c '(' ')' '.' '}'
layout { ExtComment_t }
{}

