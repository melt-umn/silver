grammar edu:umn:cs:melt:tutorial:expr:host_op_overloading ;

-- This file defines the new division operator that 
-- supports operator overloading.

import edu:umn:cs:melt:tutorial:expr:terminals ;
import edu:umn:cs:melt:tutorial:expr:abstractsyntax ;
import edu:umn:cs:melt:tutorial:expr:concretesyntax ;

-- Concrete Syntax --
---------------------
concrete production div_overload_c
quo::Expr_c ::= e::Expr_c  op::Slash_t  t::Expr_c
{
 quo.pp = e.pp ++ " " ++ op.lexeme ++ " " ++ t.pp ;
 quo.ast_Expr = div_overload(e.ast_Expr, op, t.ast_Expr );
}


-- Abstract Syntax --
---------------------
abstract production div_overload
quo::Expr ::= l::Expr  op::Slash_t  r::Expr
{
 quo.pp = "(" ++ l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ++ ")";

 production attribute overloads :: [ Expr ] with ++ ;
 overloads := [ ] ;

 forwards to
   if   length(overloads) == 1
   then head(overloads)
   else
   if   length(overloads) == 0
   then error_Expr (
          op.line, op.column,
          "Division not supported on types " ++
          l.typerep.pp ++ " and " ++ r.typerep.pp ) 
   else error_Expr (
          op.line, op.column,
          "Division implemented in multiple ways on types " ++
          l.typerep.pp ++ " and " ++ r.typerep.pp ) ;
}

aspect production div_overload
quo::Expr ::= l::Expr  op::Slash_t  r::Expr
{
 overloads <- if   equal_types(l.typerep, intType()) &&
                   equal_types(r.typerep, intType())
              then [ div(l,op,r) ]
              else [ ] ;
}
