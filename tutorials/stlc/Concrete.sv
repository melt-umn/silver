grammar stlc;


terminal ID_t     /[a-zA-Z][a-zA-Z0-9]*/;
terminal True_t   'true' dominates {ID_t};
terminal False_t  'false' dominates {ID_t};

terminal Abs_t    'lambda' dominates {ID_t};
terminal Colon_t  ':';
terminal Dot_t    '.';
terminal Or_t     '||';
terminal And_t    '&&';
terminal Not_t    '!';

terminal TyBool_t 'Bool' dominates {ID_t} ;
terminal TyArr_t  '->' association = right;

terminal LParen_t '(';
terminal RParen_t ')';

ignore terminal WhiteSpace_t /[\t\r\n\ ]+/;



synthesized attribute ast<a>::a;


-- Root
closed nonterminal Root_c with ast<Root>;

concrete production root_c
r::Root_c ::= t::Abs_c
{
  r.ast = root(t.ast);
}


-- Terms
closed nonterminal Abs_c with ast<Expression>;
closed nonterminal Term_c with ast<Expression>;
closed nonterminal And_c with ast<Expression>;
closed nonterminal App_c with ast<Expression>;
closed nonterminal Expr_c with ast<Expression>;


-- Abs_c
concrete production abs_c
top::Abs_c ::= 'lambda' n::ID_t ':' t::Type_c '.' body::Abs_c
{
  top.ast = abs(n.lexeme, t.ast, body.ast);
}

concrete production abs_term_c
top::Abs_c ::= t::Term_c
{
  top.ast = t.ast;
}


-- Term_c
concrete production or_c
top::Term_c ::= t1::And_c '||' t2::Term_c
{
  top.ast = or(t1.ast, t2.ast);
}

concrete production term_and_c
top::Term_c ::= a::And_c
{
  top.ast = a.ast;
}


-- And_c
concrete production and_c
top::And_c ::= t1::App_c '&&' t2::And_c
{
  top.ast = and(t1.ast, t2.ast);
}

concrete production and_app_c
top::And_c ::= a::App_c
{
  top.ast = a.ast;
}


-- App_c
concrete production app_c
top::App_c ::= t1::App_c t2::Expr_c
{
  top.ast = app(t1.ast, t2.ast);
}

concrete production app_expr_c
top::App_c ::= e::Expr_c
{
  top.ast = e.ast;
}


-- Expr_c
concrete production var_c
top::Expr_c ::= n::ID_t
{
  top.ast = var(n.lexeme);
}

concrete production tru_c
top::Expr_c ::= 'true'
{
  top.ast = tru_a();
}

concrete production fals_c
top::Expr_c ::= 'false'
{
  top.ast = fals_a();
}

concrete production not_c
top::Expr_c ::= '!' e::Expr_c
{
  top.ast = notOp(e.ast);
}

concrete production parens_c
top::Expr_c ::= '(' t::Abs_c ')'
{
  top.ast = t.ast;
}


-- Types
closed nonterminal Type_c with ast<Type>;

concrete production boolTy_c
top::Type_c ::= 'Bool'
{
  top.ast = bool();
}

concrete production arrTy_c
top::Type_c ::= t1::Type_c '->' t2::Type_c
{
  top.ast = arrow(t1.ast, t2.ast);
}

concrete production parenTy_c
top::Type_c ::= '(' t::Type_c ')'
{
  top.ast = t.ast;
}


