grammar silver:regex;

synthesized attribute nullable::Boolean occurs on Regex;

inherited attribute wrt::Integer occurs on Regex; -- Char
synthesized attribute deriv::Regex occurs on Regex;

-- Simplification using strategy attributes
strategy attribute simpl =
  innermost(
    rule on Regex of
    | seq(empty(), r) -> empty()
    | seq(r, empty()) -> empty()
    | seq(epsilon(), r) -> ^r
    | seq(r, epsilon()) -> ^r
    | alt(empty(), r) -> ^r
    | alt(r, empty()) -> ^r
    | alt(epsilon(), r) when r.nullable -> ^r
    | alt(r, epsilon()) when r.nullable -> ^r
    | alt(r1, r2) when ^r1 == ^r2 -> ^r1
    | star(empty()) -> epsilon()
    | star(epsilon()) -> epsilon()
    end)
  occurs on Regex;

strategy attribute simplDeriv = deriv <* simpl occurs on Regex;

function matchStep
Regex ::= r::Regex c::Integer
{
  r.wrt = c;
  return r.simplDeriv;
}

fun matches Boolean ::= r::Regex s::String = foldl(matchStep, r, stringToChars(s)).nullable;

propagate simpl, simplDeriv on Regex;

aspect production empty
top::Regex ::=
{
  top.nullable = false;
  top.deriv = empty();
}

aspect production epsilon
top::Regex ::=
{
  top.nullable = true;
  top.deriv = empty();
}

aspect production char
top::Regex ::= c::Integer
{
  top.nullable = false;
  top.deriv = if c == top.wrt then epsilon() else empty();
}

global newlineChar::Integer = head(stringToChars("\n"));
aspect production wildChar
top::Regex ::=
{
  top.nullable = false;
  top.deriv = if top.wrt != newlineChar then epsilon() else empty();
}

aspect production charRange
top::Regex ::= l::Integer u::Integer
{
  top.nullable = false;
  top.deriv = if l <= top.wrt && top.wrt <= u then epsilon() else empty();
}

aspect production negChars
top::Regex ::= r::Regex
{
  top.nullable = false;
  top.deriv = if r.deriv.nullable then empty() else epsilon();
  r.wrt = top.wrt;
}

aspect production seq
top::Regex ::= r1::Regex r2::Regex
{
  top.nullable = r1.nullable && r2.nullable;
  top.deriv =
    alt(
      seq(r1.deriv, ^r2),
      if r1.nullable then r2.deriv else empty());
  r1.wrt = top.wrt;
  r2.wrt = top.wrt;
}

aspect production alt
top::Regex ::= r1::Regex r2::Regex
{
  top.nullable = r1.nullable || r2.nullable;
  top.deriv = alt(r1.deriv, r2.deriv);
  r1.wrt = top.wrt;
  r2.wrt = top.wrt;
}

aspect production star
top::Regex ::= r::Regex
{
  top.nullable = true;
  top.deriv = seq(r.deriv, ^top);
  r.wrt = top.wrt;
}

