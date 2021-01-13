grammar silver:compiler:extension:doc:core;

aspect production attributionDcl
top::AGDcl ::= 'attribute' at::QName attl::BracketedOptTypeExprs 'occurs' 'on' nt::QName nttl::BracketedOptTypeExprs ';'
{
  top.docs := [bodilessDclCommentItem("attribute", top.grammarName, at.name, "", at.location)];
}

concrete production docAttributionDcl
top::AGDcl ::= comment::DocComment_t 'attribute' at::QName attl::BracketedOptTypeExprs 'occurs' 'on' nt::QName nttl::BracketedOptTypeExprs ';'
{
  top.docs := [dclCommentItem("attribute", top.grammarName, at.name, "", at.location, comment)];

  forwards to attributionDcl('attribute', at, attl, 'occurs', 'on', nt, nttl, ';', location=top.location);
}