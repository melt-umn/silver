grammar silver:extension:strategyattr;

terminal Strategy_kwd 'strategy' lexer classes {KEYWORD,RESERVED};

terminal Sequence_t '<*'  precedence = 12, association = left; -- Same as *
terminal Choice_t   '<+'  precedence = 11, association = left; -- Same as +

lexer class STRATEGY_COMB dominates StrategyName_t;

terminal Id_t    'id'   lexer classes {KEYWORD, STRATEGY_COMB};
terminal Fail_t  'fail' lexer classes {KEYWORD, STRATEGY_COMB};
terminal All_t   'all'  lexer classes {KEYWORD, STRATEGY_COMB};
terminal Some_t  'some' lexer classes {KEYWORD, STRATEGY_COMB};
terminal One_t   'one'  lexer classes {KEYWORD, STRATEGY_COMB};
terminal Rule_t  'rule' lexer classes {KEYWORD, STRATEGY_COMB};
terminal Rec_t   'rec'  lexer classes {KEYWORD, STRATEGY_COMB};

terminal StrategyName_t /[a-z][A-Za-z0-9\_]*/ lexer classes {IDENTIFIER};
