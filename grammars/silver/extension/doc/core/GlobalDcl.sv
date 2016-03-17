grammar silver:extension:doc:core;

aspect production globalValueDclConcrete
top::AGDcl ::= 'global' id::Name '::' t::Type '=' e::Expr ';'
{
  top.docs := [commentDocItem(bodilessCommentItem("global", id.name, t.pp, id.location.filename))];
}

concrete production docGlobalValueDclConcrete
top::AGDcl ::= comment::DocComment 'global' id::Name '::' t::Type '=' e::Expr ';'
{
  top.docs := [commentDocItem(commentItem("global", id.name, t.pp, id.location.filename, comment))];

  forwards to globalValueDclConcrete('global', id, '::', t, '=', e, ';', location=top.location);
}

concrete production noDocGlobalValueDclConcrete
top::AGDcl ::= noDoc::NoDocComment_t 'global' id::Name '::' t::Type '=' e::Expr ';'
{
  top.docs := [];

  forwards to globalValueDclConcrete('global', id, '::', t, '=', e, ';', location=top.location);
}
