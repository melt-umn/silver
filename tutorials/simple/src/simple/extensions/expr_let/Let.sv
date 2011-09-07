grammar simple:extensions:expr_let;

imports silver:langutil;
imports silver:langutil:pp;
imports simple:concretesyntax as cst;
imports simple:abstractsyntax;

terminal Let 'let' lexer classes { KEYWORDS };
terminal In 'in'   lexer classes { KEYWORDS };
terminal End 'end' lexer classes { KEYWORDS };

concrete production letExpr_c
e::cst:Expr ::= 'let' s::cst:Stmts 'in' e1::cst:Expr 'end'
{
  e.unparse = "let " ++ s.unparse ++ " in " ++ e1.unparse ++ " end";
  e.ast = letExpr(s.ast, e1.ast);
}

abstract production letExpr
e::Expr ::= s::Stmt e1::Expr
{
  e.pp = box(concat([text("let "), box(s.pp), line(), -- BUG: stmts end with lines, but we need this line OUTSIDE the box()
                     text(" in "), box(e1.pp), line(),
                     text("end")]));
  e.type = e1.type;
  e.errors := s.errors ++ e1.errors;

  e1.env = appendEnv(s.defs, e.env);

  e.c_code = "({" ++ s.c_code ++ e1.c_code ++ ";})";
}
