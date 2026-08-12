grammar silver:compiler:extension:agtix;

--

terminal Scope_t 'scope' lexer classes {KEYWORD};
terminal Graph_t 'graph' lexer classes {KEYWORD};

terminal Edge_t 'edge' lexer classes {KEYWORD};
terminal EdgeL_t '-[';
terminal EdgeR_t ']->';

synthesized attribute labels::[String];

--

concrete production scopeGraphDcl
top::AGDcl ::= 'scope' 'graph' '{' spec::AgtixSpecs '}'
{
  forwards to
    foldr(
      \label::String acc::AGDcl ->
        appendAGDcl(Silver_AGDcl { inherited attribute $Name{name(label)}::Boolean; }, acc),
      emptyAGDcl(),
      spec.labels
    );
}

--

nonterminal AgtixSpecs with location, labels;

concrete production consEdgeSpec
top::AgtixSpecs ::= l::AgtixSpec r::AgtixSpecs
{
  top.labels = l.labels ++ r.labels;
}

concrete production nilEdgeSpec
top::AgtixSpecs ::=
{
  top.labels = [];
}

--

nonterminal AgtixSpec with location, labels;

concrete production edgeSpec
top::AgtixSpec ::= 'edge' '-[' l::IdLower_t ']->' ';'
{
  top.labels = [l.lexeme];
}

concrete production concScopeSpec
top::AgtixSpec ::= 'scope' '(' ')' '=>' id::IdLower_t md::MaybeData
{

}

--

nonterminal MaybeData with location;

concrete production noData
top::MaybeData ::=
{}

concrete production hasData
top::MaybeData ::= '->'
{}
