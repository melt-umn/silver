grammar edu:umn:cs:melt:tutorial:expr:exts:float ;

import edu:umn:cs:melt:tutorial:expr:host_op_overloading
  hiding add, sub, mul, div ;

import edu:umn:cs:melt:tutorial:expr:exts:ctrans ;

-- Note that the names of productions here are the same as those in 
-- expr:abstractsyntax/Expr.sv.  Since names are qualified by the grammar
-- name there is no conflict in exporting these names.  We do need to
-- not import the short names of the arithmetic productions in the 
-- host language.

-- Addition --
--------------
abstract production add
sum::Expr ::= l::Expr  op::Plus_t  r::Expr
{
 sum.pp = "(" ++ l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ++ ")";
 sum.typerep = floatType();
 sum.value = -1 ;
 sum.errors := l.errors ++ r.errors ;

 sum.haskell = "(" ++ l.haskell ++ " " ++ op.lexeme ++ " " ++ r.haskell ++ ")";
 sum.c_trans = "(" ++ l.c_trans ++ " " ++ op.lexeme ++ " " ++ r.c_trans ++ ")";
 sum.lifted_Dcls = l.lifted_Dcls ++ r.lifted_Dcls ;
 sum.lifted_Expr = add(l.lifted_Expr, op, r.lifted_Expr);
}

aspect production add_overload
sum::Expr ::= l::Expr  op::Plus_t  r::Expr
{
 overloads <- if   equal_types(l.typerep, floatType()) &&
                   equal_types(r.typerep, floatType()) 
              then [ add(l,op,r) ]
              else [ ] ;
}


--overload add_overload sum ::= l op r
-- with add (l, op, r)
-- when equal_types ....


-- Subtraction --
-----------------
abstract production sub
dff::Expr ::= l::Expr  op::Dash_t  r::Expr
{
 dff.pp = "(" ++ l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ++ ")";
 dff.typerep = floatType();
 dff.value = -1 ;
 dff.errors := l.errors ++ r.errors ;

 dff.haskell = "(" ++ l.haskell ++ " " ++ op.lexeme ++ " " ++ r.haskell ++ ")";
 dff.c_trans = "(" ++ l.c_trans ++ " " ++ op.lexeme ++ " " ++ r.c_trans ++ ")";
 dff.lifted_Dcls = l.lifted_Dcls ++ r.lifted_Dcls ;
 dff.lifted_Expr = sub(l.lifted_Expr, op, r.lifted_Expr);
}

aspect production sub_overload
dff::Expr ::= l::Expr  op::Dash_t  r::Expr
{
 overloads <- if   equal_types(l.typerep, floatType()) &&
                   equal_types(r.typerep, floatType()) 
              then [ sub(l,op,r) ]
              else [ ] ;
}

-- Multiplication --
--------------------
abstract production mul
prd::Expr ::= l::Expr  op::Star_t  r::Expr
{
 prd.pp = "(" ++ l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ++ ")";
 prd.typerep = floatType();
 prd.value = -1 ;
 prd.errors := l.errors ++ r.errors ;

 prd.haskell = "(" ++ l.haskell ++ " " ++ op.lexeme ++ " " ++ r.haskell ++ ")";
 prd.c_trans = "(" ++ l.c_trans ++ " " ++ op.lexeme ++ " " ++ r.c_trans ++ ")";
 prd.lifted_Dcls = l.lifted_Dcls ++ r.lifted_Dcls ;
 prd.lifted_Expr = mul(l.lifted_Expr, op, r.lifted_Expr);
}

aspect production mul_overload
prd::Expr ::= l::Expr  op::Star_t  r::Expr
{
 overloads <- if   equal_types(l.typerep, floatType()) &&
                   equal_types(r.typerep, floatType()) 
              then [ mul(l,op,r) ]
              else [ ] ;
}

-- Division --
--------------
abstract production div
quo::Expr ::= l::Expr  op::Slash_t  r::Expr
{
 quo.pp = "(" ++ l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ++ ")";
 quo.typerep = floatType();
 quo.value = -1 ;
 quo.errors := l.errors ++ r.errors ;

 quo.haskell = "(" ++ l.haskell ++ " " ++ op.lexeme ++ " " ++ r.haskell ++ ")";
 quo.c_trans = "(" ++ l.c_trans ++ " " ++ op.lexeme ++ " " ++ r.c_trans ++ ")";
 quo.lifted_Dcls = l.lifted_Dcls ++ r.lifted_Dcls ;
 quo.lifted_Expr = div(l.lifted_Expr, op, r.lifted_Expr);
}

aspect production div_overload
quo::Expr ::= l::Expr  op::Slash_t  r::Expr
{
 overloads <- if   equal_types(l.typerep, floatType()) &&
                   equal_types(r.typerep, floatType()) 
              then [ div(l,op,r) ]
              else [ ] ;
}



