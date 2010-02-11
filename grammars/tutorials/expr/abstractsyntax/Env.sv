grammar tutorials:expr:abstractsyntax ;

import tutorials:expr:terminals ;

nonterminal Binding with ident, value ;
synthesized attribute ident :: Id_t ;

abstract production bind_value
b::Binding ::= id::Id_t v::Integer
{
 b.ident = id ;
 b.value = v ;
}

function lookup
EnvRes ::= n::String e::[ Binding ]
{
 return if null(e)
        then notfoundEnvRes()
        else if   n == head(e).ident.lexeme
             then foundEnvRes(head(e))
             else lookup(n,tail(e)) ;
}

nonterminal EnvRes with found, binding ;
synthesized attribute found :: Boolean ;
synthesized attribute binding :: Binding ;

abstract production foundEnvRes
r::EnvRes ::= b::Binding
{ 
 r.found = true ;
 r.binding = b ;
}

abstract production notfoundEnvRes
r::EnvRes ::= 
{ 
 r.found = false ;
 r.binding = error ("Accessing binding when variable not found.");
}
