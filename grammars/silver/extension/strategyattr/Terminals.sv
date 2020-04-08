grammar silver:extension:strategyattr;

terminal Strategy_kwd 'strategy' lexer classes {KEYWORD, RESERVED};

terminal Sequence_t '<*'  precedence = 12, association = left; -- Same as *
terminal Choice_t   '<+'  precedence = 11, association = left; -- Same as +

lexer class Strategy dominates StrategyName_t;

terminal Id_t    'id'   lexer classes {KEYWORD, Strategy};
terminal Fail_t  'fail' lexer classes {KEYWORD, Strategy};
terminal All_t   'all'  lexer classes {KEYWORD, Strategy};
terminal Some_t  'some' lexer classes {KEYWORD, Strategy};
terminal One_t   'one'  lexer classes {KEYWORD, Strategy};
terminal Rule_t  'rule' lexer classes {KEYWORD, Strategy};
terminal Rec_t   'rec'  lexer classes {KEYWORD, Strategy};

terminal Try_t          'try'          lexer classes {KEYWORD, Strategy};
terminal Repeat_t       'repeat'       lexer classes {KEYWORD, Strategy};
terminal Reduce_t       'reduce'       lexer classes {KEYWORD, Strategy};
terminal BottomUp_t     'bottomUp'     lexer classes {KEYWORD, Strategy};
terminal TopDown_t      'topDown'      lexer classes {KEYWORD, Strategy};
terminal OnceBottomUp_t 'onceBottomUp' lexer classes {KEYWORD, Strategy};
terminal OnceTopDown_t  'onceTopDown'  lexer classes {KEYWORD, Strategy};
terminal Innermost_t    'innermost'    lexer classes {KEYWORD, Strategy};
terminal Outermost_t    'outermost'    lexer classes {KEYWORD, Strategy};

terminal StrategyName_t /[a-z][A-Za-z0-9\_]*/ lexer classes {IDENTIFIER};
