grammar silver:extension:astconstruction;

import silver:definition:regex;

marking terminal AST_t 'AST' lexer classes {KEYWORD};

temp_imp_ide_font font_escape color(160, 32, 240) bold italic;
lexer class Escape font=font_escape;

terminal EscapeAST_t '$' lexer classes {Escape};

disambiguate AST_t, IdUpper_t {
  pluck AST_t;
}

disambiguate silver:definition:core:WhiteSpace, silver:reflect:concretesyntax:WhiteSpace {
  pluck silver:definition:core:WhiteSpace;
}

disambiguate RegexChar_t, silver:definition:core:WhiteSpace, silver:reflect:concretesyntax:WhiteSpace {
  pluck silver:definition:core:WhiteSpace;
}
