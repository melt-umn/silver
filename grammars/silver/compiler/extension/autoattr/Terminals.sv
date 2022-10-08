grammar silver:compiler:extension:autoattr;

terminal Propagate_kwd 'propagate' lexer classes {KEYWORD,RESERVED};
terminal Excluding_kwd 'excluding' lexer classes {KEYWORD};
terminal Thread_kwd    'thread'    lexer classes {KEYWORD,RESERVED};
terminal Direction_kwd 'direction' lexer classes {KEYWORD};

terminal Functor_kwd     'functor'     lexer classes {KEYWORD};
terminal Monoid_kwd      'monoid'      lexer classes {KEYWORD};
terminal Destruct_kwd    'destruct'    lexer classes {KEYWORD};
terminal Equality_kwd    'equality'    lexer classes {KEYWORD};
terminal Ordering_kwd    'ordering'    lexer classes {KEYWORD};
terminal Unification_kwd 'unification' lexer classes {KEYWORD};
terminal Threaded_kwd    'threaded'    lexer classes {KEYWORD};