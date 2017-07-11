grammar silver:extension:doc:core;

aspect production globalValueDclConcrete
top::AGDcl ::= 'global' id::Name '::' t::Type '=' e::Expr ';'
{
  top.docs := [bodilessDclCommentItem("global", id.name, t.pp, id.location.filename)];
}

concrete production docGlobalValueDclConcrete
top::AGDcl ::= comment::DclComment 'global' id::Name '::' t::Type '=' e::Expr ';'
{
  top.docs := [dclCommentItem("global", id.name, t.pp, id.location.filename, comment)];

  forwards to globalValueDclConcrete('global', id, '::', t, '=', e, ';', location=top.location);
}

concrete production noDocGlobalValueDclConcrete
top::AGDcl ::= noDoc::NoDclComment_t 'global' id::Name '::' t::Type '=' e::Expr ';'
{
  top.docs := [];

  forwards to globalValueDclConcrete('global', id, '::', t, '=', e, ';', location=top.location);
}
