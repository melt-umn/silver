grammar silver:extension:doc:core;

synthesized attribute body :: String;
synthesized attribute modifiers :: String;
synthesized attribute dclName :: String;
synthesized attribute signature :: String;
nonterminal DocComment with body, location;
nonterminal CommentItem with modifiers, dclName, signature, body;

concrete production docComment
top::DocComment ::= '{@' body::DocCommentBody_t '@}'
{
  top.body = body.lexeme;
}

abstract production commentItem
top::CommentItem ::= modifiers::String name::String signature::String body::DocComment
{
  top.modifiers = modifiers;
  top.dclName = name;
  top.signature = if 0 == length(signature) then "" else "\n ######`" ++ signature ++ "`";
  top.body = body.body;
}

abstract production bodilessCommentItem
top::CommentItem ::= modifiers::String name::String signature::String
{
  top.modifiers = modifiers;
  top.dclName = name;
  top.signature = if 0 == length(signature) then "" else "\n ######`" ++ signature ++ "`";
  top.body = "";
}

-- TODO: Find a way to indent doc information instead of quoting in markdown
-- TODO: Add document comments for parser declarations.
-- TODO: Add document comments for type declarations.



