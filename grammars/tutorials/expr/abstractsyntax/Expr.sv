grammar tutorials:expr:abstractsyntax ;

-- This file defines arithmetic as was done in tutorial:dc.

import tutorials:expr:terminals ;

nonterminal Expr with pp, value, errors ;

abstract production add
sum::Expr ::= l::Expr  op::Plus_t  r::Expr
{
 sum.pp = "(" ++ l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ++ ")";
 sum.value = l.value + r.value ;
 sum.errors := l.errors ++ r.errors ;
}

abstract production sub
dff::Expr ::= l::Expr  op::Dash_t  r::Expr
{
 dff.pp = "(" ++ l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ++ ")";
 dff.value = l.value - r.value ;
 dff.errors := l.errors ++ r.errors ;
}

abstract production mul
prd::Expr ::= l::Expr  op::Star_t  r::Expr
{
 prd.pp = "(" ++ l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ++ ")";
 prd.value = l.value * r.value ;
 prd.errors := l.errors ++ r.errors ;
}

abstract production div
quo::Expr ::= l::Expr  op::Slash_t  r::Expr
{
 quo.pp = "(" ++ l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ++ ")";
 quo.value = 9999 ; -- ftoi ( itof(l.value) / itof(r.value) ) ;
 quo.errors := l.errors ++ r.errors ;
}

abstract production integerConstant
e::Expr ::= i::IntLit_t
{
 e.pp = i.lexeme ;
 e.value = toInt(i.lexeme);
 e.errors := [ ] ;
}
