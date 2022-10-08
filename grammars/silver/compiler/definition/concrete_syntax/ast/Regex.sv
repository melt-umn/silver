grammar silver:compiler:definition:concrete_syntax:ast;

imports silver:core hiding empty, alt;
import silver:compiler:definition:concrete_syntax:copper as copper;

-- Translation of regex to Copper grammar beans.
synthesized attribute copperRegex::copper:Regex occurs on Regex;
synthesized attribute copperRegexAlts::[copper:Regex] occurs on Regex;
synthesized attribute copperRegexSeqs::[copper:Regex] occurs on Regex;
implicit synthesized attribute copperRegexCharSet::Maybe<copper:CharSet>;
attribute copperRegexCharSet occurs on Regex;

aspect default production
top::Regex ::=
{
  top.copperRegexAlts = [top.copperRegex];
  top.copperRegexSeqs = [top.copperRegex];
  implicit top.copperRegexCharSet =;
}

---------------------------------------------------------------------------------

aspect production char
top::Regex ::= _
{
  local charSet::copper:CharSet = copper:singleChar(char);
  top.copperRegex = copper:characterSetRegex(charSet);
  top.copperRegexCharSet = charSet;
}
aspect production wildChar
top::Regex ::=
{
  -- Copper has no direct representation of dot.
  -- Dot represents everything EXCEPT \n

  local charSet::copper:CharSet = copper:invertCharSet(copper:singleChar("\n"));
  top.copperRegex = copper:characterSetRegex(charSet);
  top.copperRegexCharSet = charSet;
}
aspect production charRange
top::Regex ::= _ _
{
  local charSet::copper:CharSet = copper:charRange(lChar, uChar);
  top.copperRegex = copper:characterSetRegex(charSet);
  top.copperRegexCharSet = charSet;
}
aspect production negChars
top::Regex ::= r::Regex 
{
  local charSet::copper:CharSet = copper:invertCharSet(r.copperRegexCharSet.fromJust);
  top.copperRegex = copper:characterSetRegex(charSet);
  top.copperRegexCharSet = charSet;
}

aspect production empty
top::Regex ::=
{
  top.copperRegex = copper:choiceRegex([]);
  top.copperRegexAlts = [];
}
aspect production epsilon
top::Regex ::=
{
  top.copperRegex = copper:emptyStringRegex();
  top.copperRegexSeqs = [];
}
aspect production alt
top::Regex ::= r1::Regex r2::Regex
{
  top.copperRegex =
    case top.copperRegexCharSet of
    | just(cs) -> copper:characterSetRegex(cs)
    | nothing() -> copper:choiceRegex(top.copperRegexAlts)
    end;
  top.copperRegexAlts =
    case top.copperRegexCharSet of
    | just(_) -> [top.copperRegex]
    | nothing() -> r1.copperRegexAlts ++ r2.copperRegexAlts
    end;
  top.copperRegexCharSet = copper:unionCharSets(r1.copperRegexCharSet, r2.copperRegexCharSet);
}
aspect production seq
top::Regex ::= r1::Regex r2::Regex
{
  top.copperRegex = copper:concatenationRegex(top.copperRegexSeqs);
  top.copperRegexSeqs = r1.copperRegexSeqs ++ r2.copperRegexSeqs;
}
aspect production star
top::Regex ::= r::Regex
{
  top.copperRegex = copper:kleeneStarRegex(r.copperRegex);
}
