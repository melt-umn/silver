grammar silver:definition:core;

synthesized attribute msg :: String;
nonterminal Message with location, msg, pp;

function err
Decorated Message ::= l::Location s::String{
  return decorate i_err(l, s) with {};
}

abstract production i_err
top::Message ::= l::Location s::String{
  top.location = l;
  top.msg = s;
  top.pp = "[" ++ l.fileName ++ ", line: " ++ toString(l.line) ++ ", column: " ++ toString(l.column) ++ "] Error: " ++ s;
}

function wrn
Decorated Message ::= l::Location s::String{
  return decorate i_wrn(l, s) with {};
}

abstract production i_wrn
top::Message ::= l::Location s::String{
  top.location = l;
  top.msg = s;
  top.pp = "[" ++ l.fileName ++ ", line: " ++ toString(l.line) ++ ", column: " ++ toString(l.column) ++ "] Warning: " ++ s;
}

function foldMessages
String ::= es::[Decorated Message]{
  return if null(es) then "" else head(es).pp ++ "\n" ++ foldMessages(tail(es));
}
