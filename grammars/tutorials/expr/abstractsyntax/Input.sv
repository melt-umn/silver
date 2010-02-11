grammar tutorials:expr:abstractsyntax ;

import tutorials:expr:terminals ;

abstract production input
r::Root ::= id::Id_t body::Expr
{
 r.pp = "input ( " ++ id.lexeme ++ " ) \n"  ++ body.pp ;
 r.value = body.value ;
 r.typerep = body.typerep ;
 body.env = -- [ bind_type(id.lexeme, intType(), r.input_value) ]  ;
            [ id_binding ] ;

 production attribute id_binding :: Binding ;
 id_binding = bind_type(id, intType(), r.input_value);
}

inherited attribute input_value :: Integer occurs on Root ;






--nonterminal IdList with pp ;
--
--abstract production ids_cons
--ids::IdList ::= id::Id_t rest::IdList
--{
-- ids.pp = id.lexeme ++ ", " ++ rest.pp ;
--}
--
--abstract production ids_one
--ids::IdList ::= id::Id_t
--{
-- ids.pp = id.lexeme ;
--}
--
--abstract production ids_none
--ids::IdList ::= 
--{
-- ids.pp = "" ;
--}
