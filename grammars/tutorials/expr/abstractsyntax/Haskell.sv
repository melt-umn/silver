grammar tutorials:expr:abstractsyntax ;

-- Compute the translation of expr to  Haskell


import tutorials:expr:terminals ;

synthesized attribute haskell :: String 
  occurs on Root, Expr ;

-- Root --

aspect production root
r::Root ::= e::Expr
{
 r.haskell = "result = " ++ e.haskell ++ "\n" ;
}

-- Expr --

aspect production let_expr
l::Expr ::= n::Id_t v::Expr body::Expr
{
 l.haskell = "( let " ++ n.lexeme ++ " = " ++ v.haskell ++
              " in "  ++ body.haskell ++ " )";
}

aspect production idRef
e::Expr ::= id::Id_t
{
 e.haskell = id.lexeme ;
}

aspect production add
sum::Expr ::= l::Expr  op::Plus_t  r::Expr
{
 sum.haskell = "(" ++ l.haskell ++ " + " ++ r.haskell ++ ")" ;
}

aspect production sub
dff::Expr ::= l::Expr  op::Dash_t  r::Expr
{
 dff.haskell = "(" ++ l.haskell ++ " - " ++ r.haskell ++ ")" ;
}

aspect production mul
prd::Expr ::= l::Expr  op::Star_t  r::Expr
{
 prd.haskell = "(" ++ l.haskell ++ " * " ++ r.haskell ++ ")" ;
}

aspect production div
quo::Expr ::= l::Expr  op::Slash_t  r::Expr
{
 quo.haskell = "(" ++ l.haskell ++ " / " ++ r.haskell ++ ")" ;
}

aspect production integerConstant
e::Expr ::= i::IntLit_t
{
 e.haskell = i.lexeme ;
}

aspect production lessthan
e::Expr ::= l::Expr op::LT_t r::Expr
{
 e.haskell = "(" ++ l.haskell ++ " < " ++ r.haskell ++ ")" ;
}

aspect production greaterthan
e::Expr ::= l::Expr op::GT_t r::Expr
{
 e.haskell = "(" ++ l.haskell ++ " > " ++ r.haskell ++ ")" ;
}

aspect production equals
e::Expr ::= l::Expr op::EqEq_t r::Expr
{
 e.haskell = "(" ++ l.haskell ++ " == " ++ r.haskell ++ ")" ;
}

aspect production and
e::Expr ::= l::Expr op::And_t r::Expr
{
 e.haskell = "(" ++ l.haskell ++ " && " ++ r.haskell ++ ")" ;
}

aspect production not
e::Expr ::=  op::Not_t  n::Expr
{
 e.haskell = "( not " ++ n.haskell ++ ")" ;
}

aspect production cond
e::Expr ::= c::Expr q::Quest_t then_expr::Expr 
            cln::Colon_t else_expr::Expr
{
 e.haskell = "(if " ++ c.haskell ++ 
             " then " ++ then_expr.haskell ++ 
             " else " ++ else_expr.haskell ++ ")" ;
}

aspect production trueConstant
bc::Expr ::= t::True_t
{
 bc.haskell = "True" ; 
}

aspect production falseConstant
bc::Expr ::= f::False_t
{
 bc.haskell = "False" ; 
}
