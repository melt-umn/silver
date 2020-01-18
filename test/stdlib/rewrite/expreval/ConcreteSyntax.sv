grammar stdlib:rewrite:expreval;

terminal Num_t /[0-9]+/;

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
| n::Num_t
  { top.ast = const(toInteger(n.lexeme)); }
| '(' e::Expr_c ')'
  { top.ast = e.ast; }

parser parse :: Expr_c {
  stdlib:rewrite:expreval;
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