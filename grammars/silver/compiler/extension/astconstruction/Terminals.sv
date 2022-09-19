grammar silver:compiler:extension:astconstruction;

marking terminal AST_t 'AST' lexer classes {KEYWORD};

temp_imp_ide_font font_escape color(160, 32, 240) bold italic;
lexer class Escape font=font_escape;

terminal EscapeAST_t '$' lexer classes {Escape, SPECOP};

disambiguate AST_t, IdUpper_t {
  pluck AST_t;
}
