grammar silver:compiler:extension:doc:core;

aspect production globalValueDclConcrete
top::AGDcl ::= 'global' id::Name '::' cl::ConstraintList '=>' t::TypeExpr '=' e::Expr ';'
{
  top.docs := [bodilessDclCommentItem("global", id.name, t.unparse, id.location.filename)];
}

concrete production docGlobalValueDclConcrete
top::AGDcl ::= comment::DclComment 'global' id::Name '::' t::TypeExpr '=' e::Expr ';'
{
  top.docs := [dclCommentItem("global", id.name, t.unparse, id.location.filename, comment)];

  forwards to globalValueDclConcrete('global', id, '::', nilConstraint(location=top.location), '=>', t, '=', e, ';', location=top.location);
}

concrete production noDocGlobalValueDclConcrete
top::AGDcl ::= noDoc::NoDclComment_t 'global' id::Name '::' t::TypeExpr '=' e::Expr ';'
{
  top.docs := [];

  forwards to globalValueDclConcrete('global', id, '::', nilConstraint(location=top.location), '=>', t, '=', e, ';', location=top.location);
}
