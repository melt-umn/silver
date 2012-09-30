grammar simple:abstractsyntax;

import silver:util:raw:treemap as tm;

{- This is slightly overkill for simple, however it's an ideal way to set up the
   environment for larger projects and more realistic languages. -}

nonterminal Env;
nonterminal Defs;
closed nonterminal Def;

-- Environment manipulation functions

function emptyEnv
Decorated Env ::=
{
  return decorate emptyEnv_i() with {};
}
function addEnv
Decorated Env ::= d::[Def]  e::Decorated Env
{
  return decorate addEnv_i(d, e) with {};
}

-- Environment representation productions

abstract production emptyEnv_i
top::Env ::=
{
}
abstract production addEnv_i
top::Env ::= dlist::[Def]  e::Decorated Env
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

aspect production emptyEnv_i
top::Env ::=
{
  top.values = tm:empty(compareString);
}
aspect production addEnv_i
top::Env ::= dlist::[Def]  e::Decorated Env
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
  top.valueContribs = [pair(n, t)];
}

function lookupValue
Maybe<Decorated TypeExpr> ::= n::String  e::Decorated Env
{
  return adaptMaybe(tm:lookup(n, e.values));
}
function lookupValueAll
[Decorated TypeExpr] ::= n::String  e::Decorated Env
{
  return tm:lookup(n, e.values);
}

  
-- Helper function

function adaptMaybe
Maybe<a> ::= l::[a]
{ return if null(l) then nothing() else just(head(l)); }


