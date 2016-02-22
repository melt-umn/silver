grammar silver:extension:doc:doclang;

terminal CommentOpen '{--';
terminal CommentClose '--}';
terminal CommentBody /(\{\-([^\-]|\-+[^\}\-])*\-+\}|[^\-]|\-+[^\}\-])*/;

synthesized attribute markdown::String;

nonterminal DocComment with markdown;

{--
Takes the begin and end terminals off of a comment as the markdown.
--}
concrete production documentationComments
top::DocComment ::= '{--' body::CommentBody '--}'
{
  top.markdown = body.lexeme;
}
