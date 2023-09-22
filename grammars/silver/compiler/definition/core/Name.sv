grammar silver:compiler:definition:core;

tracked nonterminal Name with config, grammarName, nameLoc, unparse, name;

{--
 - An identifier's (possibly qualified) name.
 -}
synthesized attribute name :: String;

concrete production nameIdLower
top::Name ::= id::IdLower_t
{
  top.name = id.lexeme;
  top.unparse = id.lexeme;
  top.nameLoc = id.location;
}
concrete production nameIdUpper
top::Name ::= id::IdUpper_t
{
  top.name = id.lexeme;
  top.unparse = id.lexeme;
  top.nameLoc = id.location;
}

function name
Name ::= n::String
{
  local loc::Location = getParsedOriginLocationOrFallback(ambientOrigin());
  return nameIdLower(terminal(IdLower_t, n, loc));
}

