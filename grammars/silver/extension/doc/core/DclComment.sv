grammar silver:extension:doc:core;

nonterminal DclComment with body, env, location;
nonterminal DclCommentComponent with body, env, location;
nonterminal DclCommentComponents with body, env, location;

attribute docEnv occurs on DclComment, DclCommentComponent, DclCommentComponents;

{-
Used by the parser to parse documentation 
comments out of Silver code
-}

concrete production dclComment
top::DclComment ::= '{@comment' components::DclCommentComponents '@}'
{
  top.body = components.body;

  components.docEnv = treeConvert([pair("core:foldr", functionDocDclInfoP("foldr", "List.sv", "core#foldr"))], treeNew(compareString));
}

concrete production consCommentComps
top::DclCommentComponents ::= h::DclCommentComponent t::DclCommentComponents
{
  top.body = h.body ++ t.body;

  h.docEnv = top.docEnv;
  t.docEnv = top.docEnv;
}

concrete production nilCommentComps
top::DclCommentComponents ::=
{
  top.body = "";
}

concrete production componentLink
top::DclCommentComponent ::= '@link' '[' id::CommentId_t ']'
{
  local dclInfo::DocDclInfo = head(treeLookup(id.lexeme, top.docEnv));
  top.body = "[" ++ dclInfo.id ++ "](http://melt.cs.umn.edu/alpha/silver/doc/ref/generated/" ++ dclInfo.path ++ ")";
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



