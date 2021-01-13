grammar silver:compiler:extension:doc:core;

synthesized attribute body :: String;
synthesized attribute loc :: Location;
nonterminal CommentItem with body, loc;

{-
Used by other productions to construct 
items for the list of CommentItems that 
AGDcl is given
-}

function makeStub
String ::= modifiers::String grammarName::String name::String signature::String loc::Location
{
	return
s"""## `${modifiers} ${name}` &nbsp; `${signature}`
Contained in grammar ${grammarName}. [Defined at ${substitute(":", "/", grammarName)}/${loc.filename} line ${toString(loc.line)}](https://github.com/melt-umn/silver/blob/develop/grammars/${substitute(":", "/", grammarName)}/${loc.filename}#L${toString(loc.line)})""";
}

abstract production dclCommentItem
top::CommentItem ::= modifiers::String grammarName::String name::String signature::String loc::Location body::DocComment_t
{
  local docCommentContent::String = substring(3, length(body.lexeme)-2, body.lexeme);
  local parsed::ParseResult<DclComment> = parseDocComment(docCommentContent, "DocComment");
  local text::String = if parsed.parseSuccess then parsed.parseTree.body else "--error--" ++ "\n```\n" ++ docCommentContent ++ "\n```\n" ++ parsed.parseErrors;

  top.body = makeStub(modifiers, grammarName, name, signature, loc) ++ "\n\n" ++ text;
  top.loc = loc;
}

abstract production bodilessDclCommentItem
top::CommentItem ::= modifiers::String grammarName::String name::String signature::String loc::Location
{
  top.body = makeStub(modifiers, grammarName, name, signature, loc);
  top.loc = loc;
}