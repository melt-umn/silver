grammar silver:regex;

-- Generate a random string matching a regex
implicit synthesized attribute genArbMatch::RandomGen<String>;

-- The desired probability of generating an additional repeat from a Kleene *
restricted inherited attribute starProb::Float;

-- The total number of alternatives up to (altCountIn) and including (altCount) this regex
-- in this chain/tree of alts.
-- Invariant: any Regex that is not the direct child of an alt should have altCountIn == 0. 
restricted inherited attribute altCountIn::Integer;
restricted synthesized attribute altCount::Integer;

-- All the "leaf" regexes of some chain/tree of alts
type GenInhs = {starProb, altCountIn};
restricted synthesized attribute altOptions::[Decorated Regex with GenInhs];

attribute genArbMatch, starProb, altCountIn, altCount, altOptions occurs on Regex;
-- propagate starProb on Regex;  -- TODO: Make propagate work with restricted attributes

aspect default production
top::Regex ::=
{
  top.altCount = top.altCountIn + 1;
  top.altOptions = [top];
}

aspect production empty
top::Regex ::=
{
  top.genArbMatch = error("Can't generate a match for empty regex");
  top.altCount = top.altCountIn;
  top.altOptions = [];
}

aspect production epsilon
top::Regex ::=
{
  top.genArbMatch = "";
}

aspect production char
top::Regex ::= c::Integer
{
  top.genArbMatch = char;
}

aspect production wildChar
top::Regex ::=
{
  -- Generate any ASCII char, excluding \x00 and \n
  top.genArbMatch =
    let i::Integer = randomRange(1, 126)
    in charsToString([if i < newlineChar then i else i + 1])
    end;
  top.altCount = top.altCountIn + 126;
}

aspect production charRange
top::Regex ::= l::Integer u::Integer
{
  top.genArbMatch = charsToString([randomRange(l, u)]);
  top.altCount = top.altCountIn + u - l + 1;
}

global asciiChars::[String] = map(\ c::Integer -> charsToString([c]), range(1, 128));
aspect production negChars
top::Regex ::= r::Regex
{
  -- Generate any ASCII char that doesn't match r
  production validAsciiChars::[String] = filter(\ c::String -> !(c =~ r), asciiChars);
  top.genArbMatch = head(drop(randomRange(0, length(validAsciiChars) - 1), validAsciiChars));
  top.altCount = top.altCountIn + length(validAsciiChars);
  r.altCountIn = 0;
}

aspect production seq
top::Regex ::= r1::Regex r2::Regex
{
  top.genArbMatch = r1.genArbMatch ++ r2.genArbMatch;

  r1.starProb = top.starProb;
  r2.starProb = top.starProb;
  r1.altCountIn = 0;
  r2.altCountIn = 0;
}

aspect production alt
top::Regex ::= r1::Regex r2::Regex
{
  -- Choose some "leaf" regex in this chain of alts, weighted by the number of alternatives in each leaf.
  top.genArbMatch =
    -- Invariant: this should only be computed at the *top* of a chain/tree of alts,
    -- i.e. when top.altCountIn == 0.
    if top.altCountIn != 0 then error("genArbMatch on alt when top.altCount != 0") else
    let i::Integer = randomRange(0, top.altCount - 1)
    in head(dropWhile(\ r::Decorated Regex with GenInhs -> r.altCount < i, top.altOptions)).genArbMatch
    end;

  thread altCountIn, altCount on top, r1, r2, top;
  top.altOptions = r1.altOptions ++ r2.altOptions;

  r1.starProb = top.starProb;
  r2.starProb = top.starProb;
}

aspect production star
top::Regex ::= r::Regex
{
  top.genArbMatch =
    if random < top.starProb
    then r.genArbMatch ++ top.genArbMatch
    else "";

  r.starProb = top.starProb;
  r.altCountIn = 0;
}
