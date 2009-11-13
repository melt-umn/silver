grammar edu:umn:cs:melt:tutorial:expr:concretesyntax ;

import edu:umn:cs:melt:tutorial:expr:terminals ;
import edu:umn:cs:melt:tutorial:expr:abstractsyntax ;

concrete production input_c
r::Root_c ::= i::Input_t '('  id::Id_t ')'  body::Expr_c
{
 r.pp = "input ( " ++ id.lexeme ++ " ) \n"  ++ body.pp ;
 r.ast_Root = input(id, body.ast_Expr);
}

--nonterminal IdList_c with pp ;
--synthesized attribute ast_IdList :: IdList occurs on IdList_c ;
--
--concrete production ids_cons_c
--ids::IdList_c ::= id::Id_t ',' rest::IdList_c
--{
-- ids.pp = id.lexeme ++ ", " ++ rest.pp ; 
-- ids.ast_IdList = ids_cons(id, rest.ast_IdList);
--}
--
--concrete production ids_one_c
--ids::IdList_c ::= id::Id_t
--{
-- ids.pp = id.lexeme ;
-- ids.ast_IdList = ids_one(id);
--}
--
--concrete production ids_none_c
--ids::IdList_c ::= 
--{
-- ids.pp = "" ;
-- ids.ast_IdList = ids_none();
--}

