grammar silver:definition:core;

import silver:definition:env only loc;

concrete production nameId
top::Name ::= id::Id_t
{
  top.name = id.lexeme;
  top.pp = id.lexeme;
  top.location = loc(top.file, id.line, id.column);
}

concrete production nameIdTick
top::NameTick ::= id::IdTick_t
{
  top.pp = id.lexeme;
  top.location = loc(top.file, id.line, id.column);
  top.name = substring(0, length(id.lexeme) -1, id.lexeme);
}

concrete production nameIdTickTick
top::NameTickTick ::= id::IdTickTick_t
{
  top.pp = id.lexeme;
  top.location = loc(top.file, id.line, id.column);
  top.name = substring(0, length(id.lexeme) -2, id.lexeme);
}
