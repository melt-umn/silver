grammar silver:extension:doc:core;

nonterminal DclComment with body, baseUrl, env, location;
nonterminal DclCommentComponent with body, baseUrl, env, location;
nonterminal DclCommentComponents with body, baseUrl, env, location;

attribute docEnv occurs on DclComment, DclCommentComponent, DclCommentComponents;

{-
Used by the parser to parse documentation 
comments out of Silver code
-}

concrete production dclComment
top::DclComment ::= '{@comment' components::DclCommentComponents '@}'
layout {}
{
  top.body = components.body;
}

concrete production consCommentComps
top::DclCommentComponents ::= h::DclCommentComponent t::DclCommentComponents
layout {}
{
  top.body = h.body ++ t.body;
}

concrete production nilCommentComps
top::DclCommentComponents ::=
{
  top.body = "";
}

concrete production componentLink
top::DclCommentComponent ::= '@link' '[' id::CommentId_t {- Should be QName -} ']'
layout {}
{
  --TODO: Use QName 
  local dclInfo::DocDclInfo = head(treeLookup(id.lexeme, top.docEnv));
  top.body = "[" ++ dclInfo.id ++ "](" ++ top.baseUrl ++ dclInfo.path ++ ")";
}

concrete production componentText
top::DclCommentComponent ::= t::CommentText_t
layout {}
{
  top.body = t.lexeme;
}

concrete production componentWhiteSpace
top::DclCommentComponent ::= w::WhiteSpace
layout {}
{
  top.body = w.lexeme;
}

{-
Used by other productions to construct 
items for the list of CommentItems that 
AGDcl is given
-}

abstract production dclCommentItem
top::CommentItem ::= modifiers::String name::String signature::String file::String body::Decorated DclComment
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



