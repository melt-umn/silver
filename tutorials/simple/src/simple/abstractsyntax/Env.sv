grammar simple:abstractsyntax ;

{- This file defines a parametric environment that may be used to
   store bindings of names to some form of type representation for use
   in name analysis and type checking.  The nonterminal Env<a>
   maintains a set of bindings from strings to values of type a.  

   The type Env<a> and various functions for creating and examining
   environments are provided.
-}

-- TODO: This is not only a bad way of doing things, but we should be using
-- a library environment anyway.
nonterminal Env<a> ;

function addBinding
Env<a> ::= n::String v::a env::Env<a>
{
  return addBinding_p (n, v, env) ;
}

function appendEnv
Env<a> ::= e1::Env<a> e2::Env<a>
{
  return appendEnv_p(e1, e2) ;
}

function emptyEnv
Env<a> ::=
{
  return emptyEnv_p() ;
}

function lookup
Maybe<a> ::= n::String e::Env<a>
{
  return if   null(matches)
         then nothing()
         else just( head(matches) ) ;

  local attribute matches :: [a] ;
  matches = allMatches (n, e) ;
}

function allMatches
[a] ::= n::String e::Env<a>
{
  return allMatches_helper (n, e.bindings) ;
}

------------------------------------------------------------
-- Env implementation productions and functions.  Do not use.
------------------------------------------------------------
synthesized attribute bindings<a> :: [ Pair<String a> ] ;
attribute bindings<a> occurs on Env<a> ;

abstract production emptyEnv_p
e::Env<a> ::= 
{
  e.bindings = [ ] ;
}

abstract production addBinding_p
e::Env<a> ::= n::String v::a env::Env<a>
{
  e.bindings = cons( pair(n,v), env.bindings ) ;
}

abstract production appendEnv_p
e::Env<a> ::= e1::Env<a> e2::Env<a>
{
  e.bindings = e1.bindings ++ e2.bindings ;
}

function allMatches_helper
[a] ::= n::String ps::[Pair<String a>]
{
  return if   null(ps)
         then [ ]
         else thisMatch ++ allMatches_helper(n, tail(ps)) ;

  local attribute thisMatch :: [a] ;
  thisMatch = if   n == head(ps).fst
              then [ head(ps).snd ]
              else [ ] ;
}
