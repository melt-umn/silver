grammar edu:umn:cs:melt:tutorial:expr:abstractsyntax ;

import edu:umn:cs:melt:tutorial:expr:terminals ;

abstract production lessthan
e::Expr ::= l::Expr op::LT_t r::Expr
{
 e.pp = "(" ++ l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ++ ")" ;
 e.value = if l.value <  r.value then 1 else 0 ;
 e.errors := l.errors ++ r.errors ;
}

abstract production greaterthan
e::Expr ::= l::Expr op::GT_t r::Expr
{
 e.pp = "(" ++ l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ++ ")" ;
 e.value = if l.value >  r.value then 1 else 0 ;
 e.errors := l.errors ++ r.errors ;
}

abstract production equals
e::Expr ::= l::Expr op::EqEq_t r::Expr
{
 e.pp = "(" ++ l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ++ ")" ;
 e.value = if l.value == r.value then 1 else 0 ;
 e.errors := l.errors ++ r.errors ;
}


abstract production and
e::Expr ::= l::Expr op::And_t r::Expr
{
 e.pp = "(" ++ l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ++ ")" ;
 e.value = if l.value != 0 && r.value != 0 then 1 else 0 ;
 e.errors := l.errors ++ r.errors ;
}

abstract production not
e::Expr ::=  op::Not_t  n::Expr
{
 e.pp = "!" ++ n.pp ;
 e.value = if n.value == 0 then 1 else 0 ;
 e.errors := n.errors ;
}

abstract production cond
e::Expr ::= c::Expr  q::Quest_t then_expr::Expr cln::Colon_t else_expr::Expr
{
 e.pp = "(" ++ c.pp ++ " ? " ++ then_expr.pp ++ " : " ++ else_expr.pp ++ ")" ;
 e.value = if c.value != 0 then then_expr.value else else_expr.value ;
 e.errors := c.errors ++ then_expr.errors ++ else_expr.errors ;
}

abstract production trueConstant
bc::Expr ::= t::True_t
{
 bc.pp = t.lexeme ;
 bc.value = 1 ;
 bc.errors := [ ] ;
}

abstract production falseConstant
bc::Expr ::= f::False_t
{
 bc.pp = f.lexeme ;
 bc.value = 0 ;
 bc.errors := [ ] ;
}
