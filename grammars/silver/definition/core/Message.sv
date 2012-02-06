grammar silver:definition:core;

{--
 - Representation of diagnostic outputs of the compiler.
 - e.g. errors, warnings, etc.
 -}
nonterminal Message with location, msg, pp;

synthesized attribute msg :: String;

abstract production err
top::Message ::= l::Location s::String
{
  top.location = l;
  top.msg = s;
  top.pp = "[" ++ l.fileName ++ ", line: " ++ toString(l.line) ++ ", column: " ++ toString(l.column) ++ "] Error: " ++ s;
}

abstract production wrn
top::Message ::= l::Location s::String
{
  top.location = l;
  top.msg = s;
  top.pp = "[" ++ l.fileName ++ ", line: " ++ toString(l.line) ++ ", column: " ++ toString(l.column) ++ "] Warning: " ++ s;
}

function foldMessages
String ::= es::[Message]{
  return if null(es) then "" else head(es).pp ++ "\n" ++ foldMessages(tail(es));
}

