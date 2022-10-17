grammar silver:compiler:extension:astconstruction;

marking terminal AST_t 'AST' lexer classes {KEYWORD};

lexer class Escape;

terminal EscapeAST_t '$' lexer classes {Escape, SPECOP};

disambiguate AST_t, IdUpper_t {
  pluck AST_t;
}
