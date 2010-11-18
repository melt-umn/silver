grammar tutorials:simple:extensions:repeat_until ;

imports tutorials:simple:host ;

terminal Repeat_t 'repeat' lexer classes { keywords } ;
terminal Until_t  'until'  lexer classes { keywords } ;

concrete production repeat_c 
s::StmtMatched_c ::= 'repeat' body::Stmts_c 'until' cond::Expr_c ';'
{
  s.pp = "repeat \n" ++ body.pp ++ "\n" ++ "until " ++ cond.pp ++ " ; \n" ;
  s.ast_Stmt = repeat (body.ast_Stmt, cond.ast_Expr ) ; 
}

abstract production repeat 
s::Stmt ::= body::Stmt cond::Expr
{
  -- s.pp = "repeat \n" ++ body.pp ++ "\n" ++ "until " ++ cond.pp ++ " ; \n" ;
  forwards to 
    {-  body
        while (! cond) { body }
     -}
    seq ( body ,
          while ( not(cond), block(body))
        ) ;
}

