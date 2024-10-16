grammar simple:extensions:do_while;

{- We import the Simple language extended with the repeat-until loop
   instead of importing the Simple host language.  This is because the
   do-while loop forwards to a repeat-until loop.  We could also
   import both the host language and the repeat-until extensions, but
   this way provides an example of building an extension for an
   extended language.
-}
imports silver:langutil;
imports silver:langutil:pp;
imports simple:concretesyntax as cst;
imports simple:abstractsyntax;
imports simple:extensions:repeat_until;

terminal Do 'do' lexer classes { cst:KEYWORDS };

concrete productions s::cst:StmtMatched
 | 'do' body::cst:Stmt 'while' '(' cond::cst:Expr ')' ';'
     { s.unparse = s"do \n${body.unparse}\nwhile ${cond.unparse}; \n";
       s.ast = dowhile(body.ast, cond.ast); }

abstract production dowhile
s::Stmt ::= body::Stmt cond::Expr
{
  s.pp = pp"do ${ppblock(body)}while (${cond});";
  forwards to 
    {-  repeat
          body
        until (! cond);
     -}
    repeatStmt(@body, notOp(@cond));
}

