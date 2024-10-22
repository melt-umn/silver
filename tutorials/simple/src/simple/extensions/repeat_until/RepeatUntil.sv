grammar simple:extensions:repeat_until;

imports silver:langutil;
imports silver:langutil:pp;
imports simple:concretesyntax as cst;
imports simple:abstractsyntax;

terminal Repeat 'repeat' lexer classes { cst:KEYWORDS };
terminal Until  'until'  lexer classes { cst:KEYWORDS };

concrete productions s::cst:StmtMatched
 | 'repeat' body::cst:Stmts 'until' cond::cst:Expr ';'
     { s.unparse = s"repeat \n${body.unparse}\nuntil ${cond.unparse}; \n";
       s.ast = repeatStmt(body.ast, cond.ast); }

abstract production repeatStmt
s::Stmt ::= body::Stmt cond::Expr
{
  s.pp = pp"repeat ${ppblock(body)}until ${cond};";
  forwards to 
    {-  body
        while (! cond) { body }
     -}
    seq(
      @body,
      while(notOp(@cond), block(^body))
    );
}

