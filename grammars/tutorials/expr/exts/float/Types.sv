grammar edu:umn:cs:melt:tutorial:expr:exts:float ;

import edu:umn:cs:melt:tutorial:expr:host_op_overloading ;
import edu:umn:cs:melt:tutorial:expr:exts:ctrans ;

-- Type Expression --
terminal Float_t 'float' dominates {Id_t} ;

concrete production floatTypeExpr_c
te::TypeExpr_c ::= i::Float_t
{
 te.pp = i.lexeme ;
 te.ast_TypeExpr = floatTypeExpr(i);
}

abstract production floatTypeExpr
te::TypeExpr ::= i::Float_t
{
 te.pp = i.lexeme ;
 te.typerep = floatType();
 te.c_trans = "float";


}

-- TypeRep --
abstract production floatType
tr::TypeRep ::= 
{
 tr.pp = "float" ;
 tr.is_equal = case tr.check_for_equal of
                 floatType() => true 
               | _           => false end ;

 tr.printf_format = "%f" ;
}
