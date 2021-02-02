grammar silver:regex;

imports silver:core hiding empty, alt;
imports silver:rewrite;
imports silver:langutil;
imports silver:langutil:pp;

synthesized attribute altPP::Document;
synthesized attribute seqPP::Document;
synthesized attribute basePP::Document;
implicit synthesized attribute classPP::Maybe<Document>;

nonterminal Regex with pp, altPP, seqPP, basePP, classPP, isEqualTo, isEqual;

propagate isEqual, isEqualTo on Regex;

aspect default production
top::Regex ::=
{
  top.pp = top.altPP;
  top.altPP = top.seqPP;
  top.seqPP = top.basePP;
  top.basePP = parens(top.pp);
  implicit top.classPP =;
}

abstract production char
top::Regex ::= c::Integer
{
  production char::String = charsToString([c]);
  top.basePP = text(char);
  top.classPP = text(char);
}

abstract production wildChar
top::Regex ::=
{
  top.basePP = pp".";
}

abstract production charRange
top::Regex ::= l::Integer u::Integer
{
  production lChar::String = charsToString([l]);
  production uChar::String = charsToString([u]);
  top.basePP = pp"[${text(lChar)}-${text(uChar)}]";
  top.classPP = pp"${text(lChar)}-${text(uChar)}";
}

abstract production negChars
top::Regex ::= r::Regex
{
  top.basePP =
    case top.classPP of
    | just(cpp) -> pp"[^${cpp}]"
    | nothing() -> pp"(^${r.basePP})" -- Not possible to represent with syntax
    end;
}

abstract production empty
top::Regex ::=
{
  top.basePP = pp"[]";
  top.classPP = pp"";
}

abstract production epsilon
top::Regex ::=
{
  top.basePP = pp"";
}

abstract production alt
top::Regex ::= r1::Regex r2::Regex
{
  top.basePP =
    case top.classPP of
    | just(cpp) -> pp"[${cpp}]"
    | nothing() -> pp"(${r1.altPP}|${r2.altPP})"
    end;
  top.altPP =
    case top.classPP of
    | just(cpp) -> pp"[${cpp}]"
    | nothing() -> pp"${r1.altPP}|${r2.altPP}"
    end;
  top.classPP = cat(r1.classPP, r2.classPP);
}

abstract production seq
top::Regex ::= r1::Regex r2::Regex
{
  top.seqPP = cat(r1.seqPP, r2.seqPP);
}

abstract production star
top::Regex ::= r::Regex
{
  top.basePP = pp"${r.basePP}*";
}

abstract production plus
top::Regex ::= r::Regex
{
  top.pp = top.altPP;
  top.altPP = top.seqPP;
  top.seqPP = top.basePP;
  top.basePP = pp"${r.basePP}+";
  forwards to seq(r, star(r));
}

abstract production opt
top::Regex ::= r::Regex
{
  top.pp = top.altPP;
  top.altPP = top.seqPP;
  top.seqPP = top.basePP;
  top.basePP = pp"${r.basePP}?";
  forwards to alt(r, epsilon());
}

------------------------------------------------

{--
 - Returns a regex that matches a string literal.
 - (i.e. no interpretation of special characters.)
 -}
function regexLiteral
Regex ::= s::String
{
  return
    if s == "" then epsilon()
    else foldr1(seq, map(char, stringToChars(s)));
}
