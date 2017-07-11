grammar silver:definition:core;

nonterminal Name with config, grammarName, location, pp, name;

{--
 - An identifier's (possibly qualified) name.
 -}
synthesized attribute name :: String;

concrete production nameIdLower
top::Name ::= id::IdLower_t
{
  top.name = id.lexeme;
  top.pp = id.lexeme;
}
concrete production nameIdUpper
top::Name ::= id::IdUpper_t
{
  top.name = id.lexeme;
  top.pp = id.lexeme;
}

function name
Name ::= n::String l::Location
{
  return nameIdLower(terminal(IdLower_t, n, l), location=l);
}

