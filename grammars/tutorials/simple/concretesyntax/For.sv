grammar tutorials:simple:concretesyntax ;

terminal For_t 'for' lexer classes { keywords } ;
terminal To_t  'to'  lexer classes { keywords } ;

concrete production forMatched_c 
s::StmtMatched_c ::= 'for' '(' id::Id_t '=' lower::Expr_c 'to' 
                               upper::Expr_c ')' body::StmtMatched_c  
{
  s.pp = "for (" ++ id.lexeme ++ " = " ++ lower.pp ++ " to " ++ upper.pp ++ ")"
           ++ "\n" ++ body.pp ;
  s.ast_Stmt = for (id, lower.ast_Expr, upper.ast_Expr, body.ast_Stmt) ; 
}

concrete production forUnMatched_c 
s::StmtUnMatched_c ::= 'for' '(' id::Id_t '=' lower::Expr_c 'to' 
                                 upper::Expr_c ')' body::StmtUnMatched_c  
{
  s.pp = "for (" ++ id.lexeme ++ " = " ++ lower.pp ++ " to " ++ upper.pp ++ ")"
           ++ "\n" ++ body.pp ;
  s.ast_Stmt = for (id, lower.ast_Expr, upper.ast_Expr, body.ast_Stmt) ; 
}
