grammar silver:extension:doc:doclang;

terminal CommentOpen '{--';
terminal CommentClose '--}';
terminal CommentBody /(\{\-([^\-]|\-+[^\}\-])*\-+\}|[^\-]|\-+[^\}\-])*/;

synthesized attribute markdown::String;

nonterminal DocComment with markdown;

concrete production documentationComments
top::DocComment ::= '{--' body::CommentBody '--}'
{
  top.markdown = body.lexeme;
}
