grammar silver:extension:doc:core;

aspect production attributionDcl
top::AGDcl ::= 'attribute' at::QName attl::BracketedOptTypeExprs 'occurs' 'on' nt::QName nttl::BracketedOptTypeExprs ';'
{
  top.docs := [bodilessDclCommentItem("attribute", at.name, "", at.location.filename)];
}

concrete production docAttributionDcl
top::AGDcl ::= comment::DclComment 'attribute' at::QName attl::BracketedOptTypeExprs 'occurs' 'on' nt::QName nttl::BracketedOptTypeExprs ';'
{
  top.docs := [dclCommentItem("attribute", at.name, "", at.location.filename, comment)];

  forwards to attributionDcl('attribute', at, attl, 'occurs', 'on', nt, nttl, ';', location=top.location);
}

concrete production noDocAttributionDcl
top::AGDcl ::= noDoc::NoDclComment_t 'attribute' at::QName attl::BracketedOptTypeExprs 'occurs' 'on' nt::QName nttl::BracketedOptTypeExprs ';'
{
  top.docs := [];

  forwards to attributionDcl('attribute', at, attl, 'occurs', 'on', nt, nttl, ';', location=top.location);
}
