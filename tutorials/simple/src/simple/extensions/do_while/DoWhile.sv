grammar simple:extensions:do_while ;

{- We import the Simple language extended with the repeat-until loop
   instead of importing the Simple host language.  This is because the
   do-while loop forwards to a repeat-until loop.  We could also
   import both the host language and the repeat-until extensions, but
   this way provides an example of building an extension for an
   extended language.
-}
imports simple:composed:simple_repeat_until ;

terminal Do_t 'do' lexer classes { keywords } ;

concrete production dowhile_c 
s::StmtMatched_c ::= 'do' body::Stmt_c 'while' '(' cond::Expr_c ')' ';'
{
  s.pp = "do \n" ++ body.pp ++ "\n" ++ "while " ++ cond.pp ++ " ; \n" ;
  s.ast_Stmt = dowhile (body.ast_Stmt, cond.ast_Expr ) ; 
}

abstract production dowhile
s::Stmt ::= body::Stmt cond::Expr
{
  -- s.pp = "do \n" ++ body.pp ++ "\n" ++ "while " ++ cond.pp ++ " ; \n" ;
  forwards to 
    {-  repeat
          body
        until (! cond) ;
     -}
    repeat ( body, not(cond) ) ;
}

