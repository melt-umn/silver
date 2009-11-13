grammar tutorials:expr:terminals ;


terminal LT_t     '<'  operator precedence = 8, association = none ;
terminal GT_t     '>'  operator precedence = 8, association = none ;
terminal EqEq_t   '==' operator precedence = 8, association = none ;

terminal And_t    '&&' operator precedence = 6, association = none ;
terminal Not_t    '!'  operator precedence = 20, association = none ;

terminal Quest_t  '?'  operator precedence = 1, association = none ;
terminal Colon_t  ':'  operator precedence = 1, association = none ;

terminal True_t  'true'  dominates { Id_t } ;
terminal False_t 'false' dominates { Id_t } ;
