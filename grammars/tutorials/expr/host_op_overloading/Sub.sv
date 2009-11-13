grammar edu:umn:cs:melt:tutorial:expr:host_op_overloading ;

-- This file defines the new subtraction operator that 
-- supports operator overloading.

import edu:umn:cs:melt:tutorial:expr:terminals ;
import edu:umn:cs:melt:tutorial:expr:abstractsyntax ;
import edu:umn:cs:melt:tutorial:expr:concretesyntax ;

-- Concrete Syntax --
---------------------
concrete production sub_overload_c
dff::Expr_c ::= e::Expr_c  op::Dash_t  t::Expr_c
{
 dff.pp = e.pp ++ " " ++ op.lexeme ++ " " ++ t.pp ;
 dff.ast_Expr = sub_overload(e.ast_Expr, op, t.ast_Expr );
}


-- Abstract Syntax --
---------------------
abstract production sub_overload
dff::Expr ::= l::Expr  op::Dash_t  r::Expr
{
 dff.pp = "(" ++ l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ++ ")";

 production attribute overloads :: [ Expr ] with ++ ;
 overloads := [ ] ;

 forwards to
   if   length(overloads) == 1
   then head(overloads)
   else
   if   length(overloads) == 0
   then error_Expr (
          op.line, op.column,
          "Subtraction not supported on types " ++
          l.typerep.pp ++ " and " ++ r.typerep.pp ) 
   else error_Expr (
          op.line, op.column,
          "Subtraction implemented in multiple ways on types " ++
          l.typerep.pp ++ " and " ++ r.typerep.pp ) ;
}

aspect production sub_overload
dff::Expr ::= l::Expr  op::Dash_t  r::Expr
{
 overloads <- if   equal_types(l.typerep, intType()) &&
                   equal_types(r.typerep, intType())
              then [ sub(l,op,r) ]
              else [ ] ;
}
