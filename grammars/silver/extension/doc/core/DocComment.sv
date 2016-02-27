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
  local sig::String = if 0 == length(signature) then "" else "\n ######`" ++ signature ++ "`";
  top.modifiers = modifiers;
  top.dclName = name;
  top.signature = sig;
  top.body = body.body;
}

abstract production bodilessCommentItem
top::CommentItem ::= modifiers::String name::String signature::String
{
  local sig::String = if 0 == length(signature) then "" else "\n ######`" ++ signature ++ "`";
  top.modifiers = modifiers;
  top.dclName = name;
  top.signature = sig;
  top.body = "";
}



