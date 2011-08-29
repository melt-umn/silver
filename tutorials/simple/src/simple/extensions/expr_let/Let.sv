grammar simple:extensions:expr_let;

imports silver:langutil;
imports simple:concretesyntax as cst;
imports simple:abstractsyntax;

terminal Let 'let' lexer classes { KEYWORDS };
terminal In 'in'   lexer classes { KEYWORDS };
terminal End 'end' lexer classes { KEYWORDS };

concrete production letExpr_c
e::cst:Expr ::= 'let' s::cst:Stmts 'in' e1::cst:Expr 'end'
{
  e.pp = "let " ++ s.pp ++ " in " ++ e1.pp ++ " end";
  e.ast = letExpr(s.ast, e1.ast);
}

abstract production letExpr
e::Expr ::= s::Stmt e1::Expr
{
  e.pp = "let " ++ s.pp ++ " in " ++ e1.pp ++ " end";
  e.type = e1.type;
  e.errors := s.errors ++ e1.errors;

  e1.env = appendEnv(s.defs, e.env);

  e.c_code = "({" ++ s.c_code ++ e1.c_code ++ ";})";
}
