grammar silver:extension:doc:core;

import silver:extension:doc:doclang as doclang;

terminal DocComment_t /\{\-\-(\{\-([^\-]|\-+[^\}\-])*\-+\}|[^\-]|\-+[^\}\-])*\-\-\}/ dominates {COMMENT};
terminal NoDocComment_t /{\-\-(\ )*nodoc(\ )*\-\-}/ dominates {DocComment_t, COMMENT};

synthesized attribute body :: String;
nonterminal DocComment with body, location;

parser docParser::doclang:DocComment
{
  silver:extension:doc:doclang;
}

concrete production documentationComments
top::DocComment ::= rawComment::DocComment_t
{
  local parseResult::ParseResult<doclang:DocComment> = docParser(rawComment.lexeme, "documentation");
  local markdown::String = parseResult.parseTree.doclang:markdown;

  top.body = if parseResult.parseSuccess
  	     then markdown
  	     else parseResult.parseErrors;
}

function toMarkdown
String ::= modifiers::String name::String signiture::String body::DocComment
{
  local sig::String = if 0 == length(signiture) then "" else "\n######`" ++ signiture ++ "`";
  return "####_" ++ modifiers ++ "_ `" ++ name ++ "`" ++ sig ++ "\n" ++ "> " ++ body.body;
}

function toNoCommentMarkdown
String ::= modifiers::String name::String signiture::String
{
  local sig::String = if 0 == length(signiture) then "" else "\n######`" ++ signiture ++ "`";
  return "####_" ++ modifiers ++ "_ `" ++ name ++ "`" ++ sig;
}

--TODO: Add seperate parser for doclang
-- TODO: Add document comments for type declarations.
-- TODO: Do a tab instead of a quote
-- TODO: Add terminal for nodoc
--TODO: Use special string for above variables


