grammar silver:compiler:definition:concrete_syntax:ast;

function asPrettyName
Maybe<String> ::= r::Regex
{
  return map(\x::String -> "'" ++ xmlEscapeString(x) ++ "'", r.asLiteral);
}

implicit synthesized attribute asLiteral::Maybe<String>;
attribute asLiteral occurs on Regex;

aspect default production
top::Regex ::=
{
  implicit top.asLiteral =; 
}

aspect production char
top::Regex ::= _
{
  top.asLiteral = char;
}

aspect production seq
top::Regex ::= r1::Regex r2::Regex
{
  top.asLiteral = r1.asLiteral ++ r2.asLiteral;
}
