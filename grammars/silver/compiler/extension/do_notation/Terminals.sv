grammar silver:compiler:extension:do_notation;

marking terminal Do_kwd 'do' lexer classes {KEYWORD,RESERVED};
terminal Rec_kwd 'rec' lexer classes {KEYWORD};

disambiguate IdLower_t, Rec_kwd { pluck Rec_kwd; }

terminal LArrow_t '<-' lexer classes {SPECOP};
terminal DoDoubleColon_t '::' lexer classes {SPECOP}, precedence=15; -- Higher precedence than cons
disambiguate ColonColon_t, DoDoubleColon_t { pluck DoDoubleColon_t; }
