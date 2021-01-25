
--marking terminal Do_kwd 'do' lexer classes {KEYWORD,RESERVED};

terminal LArrow_t '<-' lexer classes {SPECOP};
--terminal DoubleColon_t '::' lexer classes {SPECOP};

-- Not currently used, but reserving these just in case
--terminal Monad_kwd 'monad' lexer classes {KEYWORD,RESERVED};
terminal Sequence_t '>>'  precedence = 2, association = left;
terminal Bind_kwd   '>>=' precedence = 2, association = left;

terminal NoElse_t '' precedence = 0;