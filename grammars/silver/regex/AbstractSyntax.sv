grammar silver:regex;

imports silver:rewrite;
imports silver:langutil;
imports silver:langutil:pp;

implicit synthesized attribute classpp::Maybe<Document>;

nonterminal Regex with pp, classpp, isEqualTo, isEqual;

propagate isEqual, isEqualTo on Regex;

aspect default production
top::Regex ::=
{
  implicit top.classpp =;
}

abstract production char
top::Regex ::= c::Integer
{
  production char::String = charsToString([c]);
  top.pp = text(char);
  top.classpp = text(char);
}

abstract production wildChar
top::Regex ::=
{
  top.pp = pp".";
}

abstract production charRange
top::Regex ::= l::Integer u::Integer
{
  production lChar::String = charsToString([l]);
  production uChar::String = charsToString([u]);
  top.pp = pp"[${text(lChar)}-${text(uChar)}]";
  top.classpp = pp"${text(lChar)}-${text(uChar)}";
}

abstract production negChars
top::Regex ::= r::Regex
{
  top.pp =
    case top.classpp of
    | just(cpp) -> pp"[^${cpp}]"
    | nothing() -> pp"^(${r.pp})" -- Not possible to represent with syntax
    end;
}

abstract production empty
top::Regex ::=
{
  top.pp = pp"[]";
  top.classpp = pp"";
}

abstract production epsilon
top::Regex ::=
{
  top.pp = pp"";
}

abstract production alt
top::Regex ::= r1::Regex r2::Regex
{
  top.pp =
    case top.classpp of
    | just(cpp) -> pp"[${cpp}]"
    | nothing() -> pp"(${r1.pp})|(${r2.pp})"
    end;
  top.classpp = cat(r1.classpp, r2.classpp);
}

abstract production seq
top::Regex ::= r1::Regex r2::Regex
{
  top.pp = cat(parens(r1.pp), parens(r2.pp));
}

abstract production star
top::Regex ::= r::Regex
{
  top.pp = pp"(${r.pp})*";
}

abstract production plus
top::Regex ::= r::Regex
{
  top.pp = pp"(${r.pp})+";
  forwards to seq(r, star(r));
}

abstract production opt
top::Regex ::= r::Regex
{
  top.pp = pp"(${r.pp})?";
  forwards to alt(star(r), epsilon());
}
