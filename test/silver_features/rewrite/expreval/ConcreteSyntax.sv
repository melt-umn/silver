grammar silver_features:rewrite:expreval;

lexer class Keyword;

terminal Num_t /[0-9]+/;
terminal Var_t /[a-zA-Z]+/ submits to Keyword;

terminal Let_t 'let' lexer classes {Keyword};
terminal In_t 'in' precedence=0, lexer classes {Keyword};
terminal Eq_t '=';

terminal Plus_t '+' precedence=1, association=left;
terminal Minus_t '-' precedence=1, association=left;
terminal Times_t '*' precedence=2, association=left;
terminal Divide_t '/' precedence=2, association=left;

terminal LParen_t '(';
terminal RParen_t ')';

ignore terminal WhiteSpace_t /[\n\r\ ]+/;

nonterminal Expr_c with location, ast<Expr>;

concrete productions top::Expr_c
| e1::Expr_c '+' e2::Expr_c
  { abstract add; }
| e1::Expr_c '-' e2::Expr_c
  { abstract sub; }
| e1::Expr_c '*' e2::Expr_c
  { abstract mul; }
| e1::Expr_c '/' e2::Expr_c
  { abstract div; }
| 'let' n::Var_t '=' e1::Expr_c 'in' e2::Expr_c
  { top.ast = letE(n.lexeme, e1.ast, e2.ast); }
| n::Num_t
  { top.ast = const(toInteger(n.lexeme)); }
| n::Var_t
  { top.ast = var(n.lexeme); }
| '(' e::Expr_c ')'
  { top.ast = e.ast; }

parser parse :: Expr_c {
  silver_features:rewrite:expreval;
}

function parseExpr
Expr ::= s::String
{
  local result::ParseResult<Expr_c> = parse(s, "");
  return
    if result.parseSuccess
    then result.parseTree.ast
    else error(result.parseErrors);
}