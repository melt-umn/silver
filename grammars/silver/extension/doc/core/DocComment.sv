grammar silver:extension:doc:core;

import silver:extension:doc:doclang as doclang;

terminal DocComment_t /\{\-\-(\{\-([^\-]|\-+[^\}\-])*\-+\}|[^\-]|\-+[^\}\-])*\-\-\}/ dominates {COMMENT};
terminal NoDocComment_t /{\-\-(\ )*nodoc(\ )*\-\-}/ dominates {DocComment_t, COMMENT};

synthesized attribute body :: String;
nonterminal DocComment with body, location;

parser doclangParser::doclang:DocComment
{
  silver:extension:doc:doclang;
}

{--
This production takes a raw DocComment_t terminal and runs it through 
the doclang parser to generate a DocComment nonterminal.
--}
concrete production documentationComments
top::DocComment ::= rawComment::DocComment_t
{
  local parseResult::ParseResult<doclang:DocComment> = doclangParser(rawComment.lexeme, "documentation");
  local markdown::String = parseResult.parseTree.doclang:markdown;

  top.body = if parseResult.parseSuccess
  	     then markdown
  	     else parseResult.parseErrors;
}

{--
Takes the information from a declaration with a documentation comment and turns it into markdown.
--}
function toMarkdown
String ::= modifiers::String name::String signiture::String body::DocComment
{
  local sig::String = if 0 == length(signiture) then "" else "\n ######`" ++ signiture ++ "`";
  return "#### _" ++ modifiers ++ "_ `" ++ name ++ "`" ++ sig ++ "\n" ++ "> " ++ body.body;
}

{--
Take the information from a declaration with no documentation comment and turns it into markdown.
--}
function toNoCommentMarkdown
String ::= modifiers::String name::String signiture::String
{
  local sig::String = if 0 == length(signiture) then "" else "\n ######`" ++ signiture ++ "`";
  return "#### _" ++ modifiers ++ "_ `" ++ name ++ "`" ++ sig;
}

-- TODO: Find a way to indent doc information instead of quoting
-- TODO: Add document comments for parser declarations.
-- TODO: Add document comments for type declarations.
-- TODO: Do a tab instead of a quote
--TODO: Use special string for above variables


