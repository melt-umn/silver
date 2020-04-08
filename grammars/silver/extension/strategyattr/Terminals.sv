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

terminal Try_t   'try'  lexer classes {KEYWORD, Strategy};

terminal StrategyName_t /[a-z][A-Za-z0-9\_]*/ lexer classes {IDENTIFIER};
