grammar silver:extension:doc:core;

synthesized attribute body :: String;
synthesized attribute modifiers :: String;
synthesized attribute dclName :: String;
synthesized attribute signature :: String;
synthesized attribute file :: String;
nonterminal DocComment with body, location;
nonterminal CommentItem with modifiers, dclName, signature, body, file;

concrete production docComment
top::DocComment ::= '{@comment' body::CommentBody_t '@}'
{
  top.body = body.lexeme;
}

abstract production commentItem
top::CommentItem ::= modifiers::String name::String signature::String file::String body::DocComment
{
  top.modifiers = modifiers;
  top.dclName = name;
  top.signature = signature;
  top.body = body.body;
  top.file = file;
}

abstract production bodilessCommentItem
top::CommentItem ::= modifiers::String name::String signature::String file::String
{
  top.modifiers = modifiers;
  top.dclName = name;
  top.signature = signature;
  top.body = "";
  top.file = file;
}

-- TODO: Find a way to indent doc information instead of quoting in markdown
-- TODO: Add document comments for parser declarations.
-- TODO: Add document comments for type declarations.



