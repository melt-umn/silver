grammar silver:compiler:extension:doc:core;

synthesized attribute docUnparse :: String occurs on AGDcl;

concrete production documentedAGDcl
top::AGDcl ::= comment::DocComment_t dcl::AGDcl
{
  top.docs := [dclCommentItem(dcl, comment)];

  forwards to dcl;
}

concrete production standaloneCommentAGDcl
top::AGDcl ::= '@' comment::DocComment_t
{
	top.docs := [dclCommentItem(top, comment)];
	top.unparse = "";
	forwards to emptyAGDcl(location=top.location);
}