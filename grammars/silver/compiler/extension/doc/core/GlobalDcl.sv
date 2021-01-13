grammar silver:compiler:extension:doc:core;

aspect production globalValueDclConcrete
top::AGDcl ::= 'global' id::Name '::' t::TypeExpr '=' e::Expr ';'
{
  top.docs := [bodilessDclCommentItem("global", top.grammarName, id.name, t.unparse, id.location)];
}

concrete production docGlobalValueDclConcrete
top::AGDcl ::= comment::DocComment_t 'global' id::Name '::' t::TypeExpr '=' e::Expr ';'
{
  top.docs := [dclCommentItem("global", top.grammarName, id.name, t.unparse, id.location, comment)];

  forwards to globalValueDclConcrete('global', id, '::', t, '=', e, ';', location=top.location);
}
