grammar silver:compiler:extension:doc:core:doclang;
imports silver:compiler:extension:doc:core;
imports silver:langutil;

-- Comment is sequence of blocks
-- Blocks start with a newline or a @param/@return/@prodattr/@forward/...
-- Initial block is a 'normal' block

nonterminal DclComment layout {} with body, location;

nonterminal DclCommentBlocks layout {} with body, location;
nonterminal DclCommentStrictBlocks layout {} with body, location;
nonterminal DclCommentBlock layout {} with body, location;

nonterminal DclCommentLines layout {} with body, location;

nonterminal DclCommentParts layout {} with body, location;
nonterminal DclCommentStrictPart layout {} with body, location;
nonterminal DclCommentPart layout {} with body, location;

parser parseDocComment::DclComment {
	silver:compiler:extension:doc:core:doclang;
}

concrete production normalDclComment
top::DclComment ::= InitialIgnore_t blocks::DclCommentBlocks FinalIgnore_t
{
	top.body = blocks.body ++ "\n\n\n\n\n\n" ++ hackUnparse(top);
}

abstract production errorDclComment
top::DclComment ::= content::String errors::String
{
	top.body = s"""---comment parse error---

Raw comment content:
```${content}```

Errors:
```${errors}```
""";
}




concrete production initialCommentBlocks
top::DclCommentBlocks ::= block::DclCommentLines blocks::DclCommentStrictBlocks
{
	top.body = "<" ++ block.body ++ ">\n\n" ++ blocks.body;
}

concrete production passThruCommentBlocks
top::DclCommentBlocks ::= blocks::DclCommentStrictBlocks
{
	top.body = blocks.body;
}



concrete production nilCommentBlocks
top::DclCommentStrictBlocks ::=
{
	top.body = "";
}

concrete production consCommentBlocks
top::DclCommentStrictBlocks ::= block::DclCommentBlock rest::DclCommentStrictBlocks  
{
	top.body = block.body ++ "\n\n" ++ rest.body;
}




concrete production commentBlock
top::DclCommentBlock ::= EmptyLines_t content::DclCommentLines
{
	top.body = "<" ++ content.body ++ ">";
}

concrete production paramBlock
top::DclCommentBlock ::= Param_t Whitespace_t id::Id_t Whitespace_t content::DclCommentLines
{
	top.body = "@param " ++ id.lexeme ++ " <" ++ content.body ++ ">";
}

concrete production returnBlock
top::DclCommentBlock ::= Return_t Whitespace_t id::Id_t Whitespace_t content::DclCommentLines
{
	top.body = "@return " ++ id.lexeme ++ " <" ++ content.body ++ ">";
}



concrete production lastCommentLines
top::DclCommentLines ::= body::DclCommentParts
{
	top.body = body.body;
}

concrete production consCommentLines
top::DclCommentLines ::= body::DclCommentParts Newline_t rest::DclCommentLines
{
	top.body = body.body ++ " " ++ rest.body;
}





concrete production firstCommentParts
top::DclCommentParts ::= part::DclCommentStrictPart
{
	top.body = part.body;
}

concrete production snocCommentParts
top::DclCommentParts ::= rest::DclCommentParts part::DclCommentPart
{
	top.body = rest.body ++ part.body;
}




concrete production laxTextCommentPart
top::DclCommentPart ::= part::CommentContent_t
{
	top.body = part.lexeme;
}

concrete production passThruCommentPart
top::DclCommentPart ::= part::DclCommentStrictPart
{
	top.body = part.body;
}



concrete production strictTextCommentPart
top::DclCommentStrictPart ::= part::StartCommentContent_t
{
	top.body = part.lexeme;
}

concrete production linkCommentPart
top::DclCommentStrictPart ::= '@link' '[' id::Id_t ']'
{
	top.body = s"[${id.lexeme}](${id.lexeme})";
}

concrete production escapedAtPart
top::DclCommentStrictPart ::= '@@'
{
	top.body = "@";
}


terminal InitialIgnore_t /@+\{\-[\- ]*/;
terminal FinalIgnore_t /[\- \r\n]*\-\}/ dominates {CommentContent_t};

terminal EmptyLines_t /\n([\- ]*\r?\n)+[ \-]*/;
terminal Newline_t /\r?\n *\-* */;

terminal CommentContent_t /([^@\r\n\-]|\-[^\}])+/;
terminal StartCommentContent_t /[^@\r\n\- ]+/ dominates {CommentContent_t};
terminal Whitespace_t /[\t ]*/;

terminal EscapedAt_t '@@';

terminal Param_t /([\- ]*\r?\n)*[ \-]*@param/ dominates {CommentContent_t};
terminal Return_t /([\- ]*\r?\n)*[ \-]*@return/ dominates {CommentContent_t};

terminal Link_t '@link';
terminal Open_t '[';
terminal Close_t ']';
terminal Id_t /[a-zA-Z][a-zA-Z0-9_]*/;