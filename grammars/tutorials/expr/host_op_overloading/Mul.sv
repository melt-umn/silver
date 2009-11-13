grammar edu:umn:cs:melt:tutorial:expr:host_op_overloading ;

-- This file defines the new multiplication operator that 
-- supports operator overloading.

import edu:umn:cs:melt:tutorial:expr:terminals ;
import edu:umn:cs:melt:tutorial:expr:abstractsyntax ;
import edu:umn:cs:melt:tutorial:expr:concretesyntax ;

-- Concrete Syntax --
---------------------
concrete production mul_overload_c
prd::Expr_c ::= e::Expr_c  op::Star_t  t::Expr_c
{
 prd.pp = e.pp ++ " " ++ op.lexeme ++ " " ++ t.pp ;
 prd.ast_Expr = mul_overload(e.ast_Expr, op, t.ast_Expr );
}


-- Abstract Syntax --
---------------------
abstract production mul_overload
prd::Expr ::= l::Expr  op::Star_t  r::Expr
{
 prd.pp = "(" ++ l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ++ ")";

 production attribute overloads :: [ Expr ] with ++ ;
 overloads := [ ] ;

 forwards to
   if   length(overloads) == 1
   then head(overloads)
   else
   if   length(overloads) == 0
   then error_Expr (
          op.line, op.column,
          "Multiplication not supported on types " ++
          l.typerep.pp ++ " and " ++ r.typerep.pp ) 
   else error_Expr (
          op.line, op.column,
          "Multipliation implemented in multiple ways on types " ++
          l.typerep.pp ++ " and " ++ r.typerep.pp ) ;
}

aspect production mul_overload
prd::Expr ::= l::Expr  op::Star_t  r::Expr
{
 overloads <- if   equal_types(l.typerep, intType()) &&
                   equal_types(r.typerep, intType())
              then [ mul(l,op,r) ]
              else [ ] ;
}
