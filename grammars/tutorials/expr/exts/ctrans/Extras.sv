grammar edu:umn:cs:melt:tutorial:expr:exts:ctrans ;

import edu:umn:cs:melt:tutorial:expr:host ;


aspect production input
r::Root ::= id::Id_t body::Expr
{
 r.lifted_Root = input(id, flatten_expr(body.lifted_Dcls, body.lifted_Expr) ) ;
}

