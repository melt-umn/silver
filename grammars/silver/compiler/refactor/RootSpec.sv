grammar silver:compiler:refactor;

import silver:compiler:driver:util;

monoid attribute transformedFiles::[(String, Root)] occurs on RootSpec, Grammar;
propagate transformedFiles on RootSpec, Grammar;

aspect production consGrammar
top::Grammar ::= h::Root  t::Grammar
{
  top.transformedFiles <- [
    (getParsedOriginLocation(h).fromJust.filename,
     rewriteWith(topDown(try(h.transforms)), new(h)).fromJust)
  ];
}
