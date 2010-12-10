grammar tutorials:xrobots:terminals ;
terminal IntLit_t /[0-9]+/ ; 
terminal Dot_t '.' ;
terminal Plus_t   '+' precedence = 10, association = left ;
terminal Star_t   '*' precedence = 12, association = left ;
terminal Dash_t   '-' precedence = 10, association = left ;
terminal Slash_t  '/' precedence = 12, association = left ;
terminal Power_t '^'  precedence = 12, association = left ;
terminal LParen_t '(' ;
terminal RParen_t ')' ;
terminal LBrace_t '{' ;
terminal RBrace_t '}' ;
terminal LBracket_t '[' ;
terminal RBracket_t ']' ;
terminal Comma_t ',' ;
terminal Semi_t ';' ;


-- white space
ignore terminal WhiteSpace_t /[\t\n\ ]+/  ;

-- line comments
ignore terminal LineComment_P  /[\/][\/].*/  ;

-- let expressions
--terminal Let_t 'let' dominates { Id_t } ;
--terminal In_t  'in'  dominates { Id_t } ;
--terminal End_t 'end' dominates { Id_t } ;
terminal Id_t  /[a-zA-Z]+/ ;
--terminal Float_t  'f' dominates { Id_t };
terminal Equals_t '=' ;
terminal Assign_t ':=' ;
terminal If_t 'if'dominates { Id_t };
terminal Then_t 'then' dominates { Id_t };
terminal Else_t 'else' dominates { Id_t };
terminal Rand_t 'rand' dominates { Id_t };
terminal Function_t 'function' dominates { Id_t };
terminal LeftBump_t 'LeftBump' dominates { Id_t };
terminal RightBump_t 'RightBump' dominates { Id_t };
terminal RightWheelDrop_t 'RightWheelDrop' dominates { Id_t };
terminal LeftWheelDrop_t 'LeftWheelDrop' dominates { Id_t };
terminal SetRSpeed_t 'SetRSpeed' dominates { Id_t };
terminal SetLSpeed_t 'SetLSpeed' dominates { Id_t };
terminal Wait_t 'Wait' dominates { Id_t };
terminal Behavior_t 'Behavior' dominates { Id_t };
terminal PBehavior_t 'pBehavior' dominates { Id_t };
terminal On_t 'On' dominates { Id_t };
terminal Entry_t 'Entry' dominates { Id_t };
terminal Exit_t 'Exit' dominates { Id_t };
terminal ExitTo_t 'ExitTo' dominates { Id_t };
terminal ExitBehavior_t 'ExitBehavior' dominates { Id_t };
terminal Dec_t 'Dec' dominates { Id_t };
terminal Condition_t 'Condition' dominates {Id_t };	
terminal Apply_t 'Apply' dominates { Id_t };
terminal Under_t 'Under' dominates { Id_t };
terminal Initial_t 'Initial' dominates {Id_t};
terminal False_t 'false' dominates {Id_t};
terminal True_t 'true' dominates {Id_t};
terminal Sin_t 'sin' dominates {Id_t};
terminal Cos_t 'cos' dominates {Id_t};
terminal Sqrt_t 'sqrt' dominates {Id_t};
terminal Pre_t 'pre' dominates {Id_t};
