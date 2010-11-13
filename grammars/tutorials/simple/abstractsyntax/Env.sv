grammar tutorials:simple:abstractsyntax ;

nonterminal Env<a> ;

function addBinding
Env<a> ::= name::String v::a env::Env<a>
{ return addBinding_p (name, v, env) ;
}

function appendEnv
Env<a> ::= e1::Env<a> e2::Env<a>
{ return appendEnv_p(e1, e2) ;
}

function emptyEnv
Env<a> ::=
{ return emptyEnv_p() ;
}

function lookup
Maybe<a> ::= name::String e::Env<a>
{ return if   null(matches)
         then nothing()
         else just( head(matches) ) ;

  local attribute matches :: [a] ;
  matches = allMatches (name, e) ;
}

function allMatches
[a] ::= name::String e::Env<a>
{ return allMatches_helper (name, e.bindings) ;
}


-- Env implementation productions.  Do not use.
synthesized attribute bindings<a> :: [ Pair<String a> ] ;
attribute bindings<a> occurs on Env<a> ;

abstract production emptyEnv_p
e::Env<a> ::= 
{ e.bindings = [ ] ; }

abstract production addBinding_p
e::Env<a> ::= name::String v::a env::Env<a>
{ e.bindings = cons( pair(name,v), env.bindings ) ;
}

abstract production appendEnv_p
e::Env<a> ::= e1::Env<a> e2::Env<a>
{ e.bindings = e1.bindings ++ e2.bindings ;
}

function allMatches_helper
[a] ::= name::String ps::[Pair<String a>]
{ return if   null(ps)
         then [ ]
         else thisMatch ++ allMatches_helper(name, tail(ps)) ;
  local attribute thisMatch :: [a] ;
  thisMatch = if   name == head(ps).fst
              then [ head(ps).snd ]
              else [ ] ;
}
