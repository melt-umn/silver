grammar edu:umn:cs:melt:tutorial:expr:abstractsyntax ;

import edu:umn:cs:melt:tutorial:expr:terminals ;

nonterminal TypeRep ;

synthesized attribute typerep :: TypeRep ;

-- TypeRep trees represent the type of values on expressions (Expr)
-- and on type expressions (TypeExpr).

attribute pp occurs on TypeRep ;

synthesized attribute is_equal :: Boolean occurs on TypeRep ;
inherited attribute check_for_equal :: TypeRep occurs on TypeRep ;

abstract production intType
tr::TypeRep ::= 
{
 tr.pp = "int" ;
 tr.is_equal = case tr.check_for_equal of
                 intType() => true 
               | _         => false end ;
}

abstract production booleanType
tr::TypeRep ::= 
{
 tr.pp = "boolean" ;
 tr.is_equal = case tr.check_for_equal of
                 booleanType() => true 
               | _             => false end ;
}

abstract production errorType
tr::TypeRep ::= 
{
 tr.pp = "error" ;
 tr.is_equal = case tr.check_for_equal of
                 errorType() => true 
               | _           => false end ;
}


function equal_types
Boolean ::= t1::TypeRep t2::TypeRep
{
 return t1.is_equal ;
 t1.check_for_equal = t2 ;
}
