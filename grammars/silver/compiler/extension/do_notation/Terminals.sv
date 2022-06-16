grammar silver:compiler:extension:do_notation;

marking terminal Do_kwd 'do' lexer classes {KEYWORD,RESERVED};
marking terminal MDo_kwd 'mdo' lexer classes {KEYWORD,RESERVED};

terminal LArrow_t '<-' lexer classes {SPECOP};
terminal DoDoubleColon_t '::' lexer classes {SPECOP}, precedence=15; -- Higher precedence than cons
disambiguate ColonColon_t, DoDoubleColon_t { pluck DoDoubleColon_t; }
