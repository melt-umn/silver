grammar silver:definition:core;

synthesized attribute msg :: String;
nonterminal Message with location, msg, pp;


function err
Message ::= l::Location s::String{
  return  i_err(l, s);-- with {};
}

abstract production i_err
top::Message ::= l::Location s::String{
  top.location = l;
  top.msg = s;
  top.pp = "[" ++ l.fileName ++ ", line: " ++ toString(l.line) ++ ", column: " ++ toString(l.column) ++ "] Error: " ++ s;
}

function wrn
Message ::= l::Location s::String{
  return  i_wrn(l, s);-- with {};
}

abstract production i_wrn
top::Message ::= l::Location s::String{
  top.location = l;
  top.msg = s;
  top.pp = "[" ++ l.fileName ++ ", line: " ++ toString(l.line) ++ ", column: " ++ toString(l.column) ++ "] Warning: " ++ s;
}

function foldMessages
String ::= es::[Message]{
  return if null(es) then "" else head(es).pp ++ "\n" ++ foldMessages(tail(es));
}
{-
abstract production err
top::Message ::= l::Location m::String
{
  top.unparse = l.unparse ++ ": error: " ++ m;
  top.location = l;
}-}
