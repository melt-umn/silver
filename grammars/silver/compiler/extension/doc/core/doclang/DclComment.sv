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

nonterminal ConfigValue layout {} with body, location;

nonterminal DclCommentLines layout {} with body, location;

nonterminal DclCommentParts layout {} with body, location;
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

concrete production prodattrBlock
top::DclCommentBlock ::= Prodattr_t Whitespace_t id::Id_t Whitespace_t content::DclCommentLines
{
	top.body = "@prodattr " ++ id.lexeme ++ " <" ++ content.body ++ ">";
}

concrete production returnBlock
top::DclCommentBlock ::= Return_t Whitespace_t content::DclCommentLines
{
	top.body = "@return <" ++ content.body ++ ">";
}

concrete production forwardBlock
top::DclCommentBlock ::= Forward_t Whitespace_t content::DclCommentLines
{
	top.body = "@forward <" ++ content.body ++ ">";
}

concrete production warningBlock
top::DclCommentBlock ::= Warning_t Whitespace_t content::DclCommentLines
{
	top.body = "@warning <" ++ content.body ++ ">";
}

concrete production configBlock
top::DclCommentBlock ::= Config_t Whitespace_t param::Id_t Whitespace_t Equals_t Whitespace_t value::ConfigValue
{
	top.body = "@config " ++ param.lexeme ++ " = " ++ value.body;
}

concrete production kwdValue
top::ConfigValue ::= v::ConfigValueKeyword_t
{
	top.body = v.lexeme;
}

concrete production stringValue
top::ConfigValue ::= v::ConfigValueString_t
{
	top.body = v.lexeme;
}

concrete production integerValue
top::ConfigValue ::= v::ConfigValueInt_t
{
	top.body = v.lexeme;
}



concrete production lastCommentLines
top::DclCommentLines ::= body::DclCommentParts
{
	top.body = body.body;
}

concrete production consCommentLines
top::DclCommentLines ::= body::DclCommentParts Newline_t rest::DclCommentLines
{
	top.body = body.body ++ "\n" ++ rest.body;
}





concrete production firstCommentParts
top::DclCommentParts ::= part::DclCommentPart
{
	top.body = part.body;
}

concrete production snocCommentParts
top::DclCommentParts ::= rest::DclCommentParts part::DclCommentPart
{
	top.body = rest.body ++ part.body;
}


concrete production textCommentPart
top::DclCommentPart ::= part::CommentContent_t
{
	top.body = part.lexeme;
}

concrete production linkCommentPart
top::DclCommentPart ::= '@link' '[' id::Id_t ']'
{
	top.body = s"[${id.lexeme}](${id.lexeme})";
}
concrete production fileLinkCommentPart
top::DclCommentPart ::= '@file' '[' path::Path_t ']'
{
	top.body = s"[${path.lexeme}](github -> ${path.lexeme})";
}

concrete production escapedAtPart
top::DclCommentPart ::= '@@'
{
	top.body = "@";
}


terminal InitialIgnore_t /@+\{\- *\-* */;
terminal FinalIgnore_t /[\- \r\n]*\-\}/ dominates {CommentContent_t};

terminal EmptyLines_t /\n( *\-* *\r?\n)+ *\-* */;
terminal Newline_t /\r?\n *\-* */;

terminal CommentContent_t /([^@\r\n\-]|\-[^\r\n}])+/;

terminal EscapedAt_t '@@';

terminal Param_t /( *\-* *\r?\n)* *\-* *@(param|child)/ lexer classes {BLOCK_KWD};
terminal Return_t /( *\-* *\r?\n)* *\-* *@return/ lexer classes {BLOCK_KWD};
terminal Forward_t /( *\-* *\r?\n)* *\-* *@forward/ lexer classes {BLOCK_KWD};
terminal Prodattr_t /( *\-* *\r?\n)* *\-* *@prodattr/ lexer classes {BLOCK_KWD};
terminal Warning_t /( *\-* *\r?\n)* *\-* *@warning/ lexer classes {BLOCK_KWD};
terminal Config_t /( *\-* *\r?\n)* *\-* *@config/ lexer classes {BLOCK_KWD};

terminal ConfigValueKeyword_t /(on|off|true|false)/;
terminal ConfigValueString_t /[\"]([^\r\n\"\\]|[\\][\"]|[\\][\\]|[\\]b|[\\]n|[\\]r|[\\]f|[\\]t)*[\"]/;
terminal ConfigValueInt_t /[0-9]+/;

terminal Whitespace_t /[\t ]*/;
terminal Equals_t /=?/;

terminal Link_t '@link';
terminal FileLink_t '@file';
terminal OpenBracket_t '[';
terminal CloseBracket_t ']';
terminal Id_t /[a-zA-Z][a-zA-Z0-9_]*/;
terminal Path_t /[a-zA-Z0-9_\-\/\.]+/;

lexer class BLOCK_KWD dominates CommentContent_t;