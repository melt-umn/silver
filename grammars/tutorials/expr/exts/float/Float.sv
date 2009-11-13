grammar edu:umn:cs:melt:tutorial:expr:exts:float ;
 export edu:umn:cs:melt:tutorial:expr:exts:float ;

import edu:umn:cs:melt:tutorial:expr:host_op_overloading ;
import edu:umn:cs:melt:tutorial:expr:exts:ctrans ;

terminal FloatLit_t /[0-9]+[\.][0-9]+/ ;

concrete production floatConstant_c
fc::Expr_c ::= f::FloatLit_t
{
 fc.pp = f.lexeme ;
 fc.ast_Expr = floatConstant(f);
}

abstract production floatConstant
fc::Expr ::= f::FloatLit_t
{
 fc.pp = f.lexeme ;
 fc.errors := [ ] ;

 fc.typerep = floatType();

 fc.haskell = f.lexeme ;
 fc.c_trans = f.lexeme ;
 fc.lifted_Dcls = [ ] ;
 fc.lifted_Expr = fc ;
}
