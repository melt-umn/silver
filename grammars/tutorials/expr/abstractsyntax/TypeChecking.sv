grammar edu:umn:cs:melt:tutorial:expr:abstractsyntax ;

import edu:umn:cs:melt:tutorial:expr:terminals ;

-- Root --
----------
attribute typerep occurs on Root ;

aspect production root
r::Root ::= e::Expr
{
 r.typerep = e.typerep ;
}

-- Expr --
----------
attribute typerep occurs on Expr ;

-- A utility functions to check if two type reps have the required
-- (reqd) type and generate appropriate error message if they do not.
function both_same_type_as
[ String ] ::= ltr::TypeRep rtr::TypeRep reqd::TypeRep
               l::Integer c::Integer
{
 return
  (if   equal_types(ltr, reqd)
   then [ ] 
   else [ mk_error(l, c, "left operand is not of type " ++ 
                         reqd.pp) ] )
  ++
  (if   equal_types(rtr, reqd)
   then [ ] 
   else [ mk_error(l, c, "right operand is not of type " ++ 
                         reqd.pp) ] )
  ;
}

aspect production let_expr
l::Expr ::= n::Id_t v::Expr body::Expr
{
 l.typerep = body.typerep ;
}

aspect production add
sum::Expr ::= l::Expr  op::Plus_t  r::Expr
{
 sum.typerep = intType();

 local attribute my_errors :: [ String ] ;

 my_errors = both_same_type_as (l.typerep, r.typerep, intType(),
                                op.line, op.column);

 sum.errors <- my_errors;
}

aspect production sub
dff::Expr ::= l::Expr  op::Dash_t  r::Expr
{
 dff.typerep = intType();
 dff.errors <- both_same_type_as (l.typerep, r.typerep, intType(), 
                                  op.line, op.column);
}

aspect production mul
prd::Expr ::= l::Expr  op::Star_t  r::Expr
{
 prd.typerep = intType();
 prd.errors <- both_same_type_as (l.typerep, r.typerep, intType(), 
                                  op.line, op.column);
}

aspect production div
quo::Expr ::= l::Expr  op::Slash_t  r::Expr
{
 quo.typerep = intType();
 quo.errors <- both_same_type_as (l.typerep, r.typerep, intType(),
                                  op.line, op.column);
}

aspect production integerConstant
e::Expr ::= i::IntLit_t
{
 e.typerep = intType();
}

aspect production lessthan
e::Expr ::= l::Expr op::LT_t r::Expr
{
 e.typerep = booleanType();
 e.errors <- both_same_type_as (l.typerep, r.typerep, intType(),
                                op.line, op.column);
}

aspect production greaterthan
e::Expr ::= l::Expr op::GT_t r::Expr
{
 e.typerep = booleanType();
 e.errors <- both_same_type_as (l.typerep, r.typerep, intType(),
                                op.line, op.column);
}

aspect production equals
e::Expr ::= l::Expr op::EqEq_t r::Expr
{
 e.typerep = booleanType();
 e.errors <- 
    if equal_types(l.typerep, r.typerep)
    then [ ]
    else [ mk_error (op.line, op.column, 
                     "expressions on = must have same type") ];
}

aspect production and
e::Expr ::= l::Expr op::And_t r::Expr
{
 e.typerep = booleanType();
 e.errors <- both_same_type_as (l.typerep, r.typerep, booleanType(), 
                                op.line, op.column);
}

aspect production not
e::Expr ::=  op::Not_t  n::Expr
{
 e.typerep = booleanType();
 e.errors <- 
    if   equal_types(n.typerep, booleanType())
    then [ ] 
    else [ mk_error(op.line, op.column, 
                    "operand is not of type boolean.") ]  ;
}

aspect production cond
e::Expr ::= c::Expr q::Quest_t then_expr::Expr 
            cln::Colon_t else_expr::Expr
{
 e.typerep = then_expr.typerep ;
 e.errors <-
    (if   equal_types(c.typerep, booleanType())
     then [ ]
     else [ mk_error(q.line, q.column, 
                     "condition is not of type boolean.") ] )
    ++
    (if   equal_types(then_expr.typerep, else_expr.typerep) 
     then [  ]
     else [ mk_error (q.line, q.column, 
                      "expressions on conditional must have " ++ 
                       "same type") ] )
    ;
}
