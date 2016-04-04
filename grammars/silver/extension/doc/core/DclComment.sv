grammar silver:extension:doc:core;

nonterminal DclComment with body, location;
nonterminal DclCommentComponent with body, location;
nonterminal DclCommentComponents with body, location;

{-
Used by the parser to parse documentation 
comments out of Silver code
-}

concrete production dclComment
top::DclComment ::= '{@comment' components::DclCommentComponents '@}'
{
  top.body = components.body;
}

concrete production consCommentComps
top::DclCommentComponents ::= h::DclCommentComponent t::DclCommentComponents
{
  top.body = h.body ++ t.body;
}

concrete production nilCommentComps
top::DclCommentComponents ::=
{
  top.body = "";
}

concrete production componentLink
top::DclCommentComponent ::= '@link' '[' id::IdLower_t ']'
{
  top.body = id.lexeme;
}

concrete production componentText
top::DclCommentComponent ::= t::CommentText_t
{
  top.body = t.lexeme;
}

{-
Used by other productions to construct 
items for the list of CommentItems that 
AGDcl is given
-}

abstract production dclCommentItem
top::CommentItem ::= modifiers::String name::String signature::String file::String body::DclComment
{
  top.body = body.body;
  top.file = file;
}

abstract production bodilessDclCommentItem
top::CommentItem ::= modifiers::String name::String signature::String file::String
{
  top.body = "";
  top.file = file;
}

-- TODO: Find a way to indent doc information instead of quoting in markdown
-- TODO: Add document comments for parser declarations.
-- TODO: Add document comments for type declarations.



