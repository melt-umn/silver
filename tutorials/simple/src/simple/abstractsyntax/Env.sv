grammar simple:abstractsyntax;

import silver:util:treemap as tm;

{- This is slightly overkill for simple, however it's an ideal way to set up the
   environment for larger projects and more realistic languages. -}

data nonterminal Env;
data nonterminal Defs;
closed data nonterminal Def;

-- Environment representation productions

abstract production emptyEnv
top::Env ::=
{
}
abstract production addEnv
top::Env ::= dlist::[Def]  e::Env
{
  production d::Defs = foldr(consDefs, nilDefs(), dlist);
}

-- Definition list productions

abstract production nilDefs
top::Defs ::=
{
}

abstract production consDefs
top::Defs ::= h::Def  t::Defs
{
}

-- Individual definitions

aspect default production
top::Def ::=
{
}


-------------

{- We add this attribute the the environment separately from the above, so that
   it can serve as a model for how extensions can add new namespaces (or other
   information) to the environment. -}

synthesized attribute values :: tm:Map<String Decorated TypeExpr> occurs on Env;
synthesized attribute valueContribs :: [Pair<String Decorated TypeExpr>] occurs on Defs, Def;

aspect production emptyEnv
top::Env ::=
{
  top.values = tm:empty();
}
aspect production addEnv
top::Env ::= dlist::[Def]  e::Env
{
  top.values = tm:add(d.valueContribs, e.values);
}

aspect production nilDefs
top::Defs ::=
{
  top.valueContribs = [];
}
aspect production consDefs
top::Defs ::= h::Def  t::Defs
{
  top.valueContribs = h.valueContribs ++ t.valueContribs;
}

aspect default production
top::Def ::=
{
  top.valueContribs = [];
}
abstract production valueDef
top::Def ::= n::String  t::Decorated TypeExpr
{
  top.valueContribs = [(n, t)];
}

function lookupValue
Maybe<Decorated TypeExpr> ::= n::String  e::Env
{
  return adaptMaybe(tm:lookup(n, e.values));
}
function lookupValueAll
[Decorated TypeExpr] ::= n::String  e::Env
{
  return tm:lookup(n, e.values);
}

  
-- Helper function

function adaptMaybe
Maybe<a> ::= l::[a]
{ return if null(l) then nothing() else just(head(l)); }


