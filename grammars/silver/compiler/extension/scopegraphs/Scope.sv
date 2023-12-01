grammar silver:compiler:extension:scopegraphs;


nonterminal Scope with var_edges, lex_edges, datum;

synthesized attribute var_edges :: [Scope];
synthesized attribute lex_edges :: [Scope];
synthesized attribute datum :: Maybe<Datum>;

abstract production mkScopeFull
top::Scope ::= 
  datum::Maybe<Datum>
  var_edges :: [Scope]
  lex_edges :: [Scope]
{
  top.var_edges = var_edges;
  top.lex_edges = lex_edges;
  top.datum = datum;
}


nonterminal Datum;

abstract production datumScope
top::Datum ::= str::String
{}

abstract production datumType
top::Datum ::= str::String t::Type
{}