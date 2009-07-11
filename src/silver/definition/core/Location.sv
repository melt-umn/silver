grammar silver:definition:core;

abstract production internal_loc
top::Location ::= f::String l::Integer c::Integer {
  top.line = l;
  top.column = c;
  top.fileName = f; 
  top.pp = "[file: " ++ f ++ ", line: " ++ toString(l) ++ ", column: " ++ toString(c) ++ "]";
}

function loc
Decorated Location ::= f::String l::Integer c::Integer {
  return decorate internal_loc(f, l, c) with {};
}