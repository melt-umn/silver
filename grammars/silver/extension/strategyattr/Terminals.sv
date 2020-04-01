grammar silver:extension:strategyattr;

terminal Strategy_kwd 'strategy' lexer classes {KEYWORD,RESERVED};

terminal StrategyId_t /[a-z][A-Za-z0-9\_]*/ lexer classes {IDENTIFIER};

terminal Sequence_t '<*'  precedence = 12, association = left; -- Same as *
terminal Choice_t   '<+'  precedence = 11, association = left; -- Same as +

lexer class STRATEGY_COMB dominates StrategyId_t;

terminal Id_t    'id'   lexer classes {KEYWORD};
terminal Fail_t  'fail' lexer classes {KEYWORD};
terminal All_t   'all'  lexer classes {KEYWORD};
terminal Some_t  'some' lexer classes {KEYWORD};
terminal One_t   'one'  lexer classes {KEYWORD};
terminal Rule_t  'rule' lexer classes {KEYWORD};
terminal Rec_t   'rec'  lexer classes {KEYWORD};
