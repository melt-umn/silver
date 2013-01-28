grammar silver:definition:core;

nonterminal Name with config, grammarName, file, location, pp, name;

{--
 - An identifier's (possibly qualified) name.
 -}
synthesized attribute name :: String;

concrete production nameIdLower
top::Name ::= id::IdLower_t
{
  top.name = id.lexeme;
  top.pp = id.lexeme;
  top.location = loc(top.file, id.line, id.column);
}
concrete production nameIdUpper
top::Name ::= id::IdUpper_t
{
  top.name = id.lexeme;
  top.pp = id.lexeme;
  top.location = loc(top.file, id.line, id.column);
}

