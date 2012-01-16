grammar silver:definition:env;

synthesized attribute fileName :: String;
synthesized attribute line :: Integer;
synthesized attribute column :: Integer;

nonterminal Location with line, column, fileName, unparse;

abstract production loc
top::Location ::= f::String l::Integer c::Integer
{
  top.line = l;
  top.column = c;
  top.fileName = f;
--  top.pp = "[file: " ++ f ++ ", line: " ++ toString(l) ++ ", column: " ++ toString(c) ++ "]";
  top.unparse = "'" ++ f ++ "', " ++ toString(l) ++ ", " ++ toString(c);
}
{-
function loc
Decorated Location ::= f::String l::Integer c::Integer
{
  return decorate internal_loc(f, l, c) with {};
}
-}
