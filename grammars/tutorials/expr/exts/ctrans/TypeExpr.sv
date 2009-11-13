grammar edu:umn:cs:melt:tutorial:expr:exts:ctrans ;

import edu:umn:cs:melt:tutorial:expr:host ;

attribute c_trans occurs on TypeExpr ;

aspect production intTypeExpr
te::TypeExpr ::= i::Int_t
{
 te.c_trans = i.lexeme ;
}

aspect production booleanTypeExpr
te::TypeExpr ::= b::Boolean_t
{
 te.c_trans = "int" ;
}

