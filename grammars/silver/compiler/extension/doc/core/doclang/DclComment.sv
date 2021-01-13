grammar silver:compiler:extension:doc:core:doclang;
imports silver:compiler:extension:doc:core;
imports silver:langutil;

nonterminal DclComment layout {} with body, location;
nonterminal DclCommentParts layout {} with body, location;
nonterminal DclCommentPart layout {} with body, location;

parser parseDocComment::DclComment {
	silver:compiler:extension:doc:core:doclang;
}

concrete production dclCommentRoot
top::DclComment ::= parts::DclCommentParts
{
	top.body = parts.body;
}

concrete production nilCommentParts
top::DclCommentParts ::=
{
	top.body = "";
}

concrete production consCommentParts
top::DclCommentParts ::= part::DclCommentPart rest::DclCommentParts
{
	top.body = part.body ++ rest.body;
}

concrete production textCommentPart
top::DclCommentPart ::= part::CommentContent_t
{
	top.body = part.lexeme;
}

terminal CommentContent_t /[^@]+/;