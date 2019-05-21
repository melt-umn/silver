grammar silver:extension:treesitter;

import silver:definition:regex;

synthesized attribute stringLiteral :: Boolean;
attribute stringLiteral occurs on Regex, RegexSeq, RegexRepetition, RegexItem;

aspect production regexEpsilon
top::Regex ::=
{
  top.stringLiteral = true;
}

aspect production regexSeq
top::Regex ::= h::RegexSeq
{
  top.stringLiteral = h.stringLiteral;
}

aspect production regexChoice
top::Regex ::= h::RegexSeq '|' t::Regex
{
  top.stringLiteral = false;
}


{--
 - A sequence of regular expressions.
 -}

aspect production regexSeqSnoc
top::RegexSeq ::= h::RegexSeq t::RegexRepetition
{
  top.stringLiteral = h.stringLiteral && t.stringLiteral;
}

aspect production regexSeqOne
top::RegexSeq ::= t::RegexRepetition
{
  top.stringLiteral = t.stringLiteral;
}


{--
 - A RegexItem with an optional repetition operator (*+?)
 -}

aspect production regexKleene
top::RegexRepetition ::= i::RegexItem '*'
{
  top.stringLiteral = false;
}

aspect production regexPlus
top::RegexRepetition ::= i::RegexItem '+'
{
  top.stringLiteral = false;
}

aspect production regexOptional
top::RegexRepetition ::= i::RegexItem '?'
{
  top.stringLiteral = false;
}

aspect production regexOnce
top::RegexRepetition ::= i::RegexItem
{
  top.stringLiteral = i.stringLiteral;
}


{--
 - A single matched entity (char, wildcard, set, group)
 -}
-- characters or sequences/sets

aspect production regexCharItem
top::RegexItem ::= char::RegexChar
{
  top.stringLiteral = true;
}

aspect production regexWildcard
top::RegexItem ::= '.'
{
  top.stringLiteral = false;
}

aspect production regexSet
top::RegexItem ::= '[' g::RegexCharSet ']'
{
  top.stringLiteral = false;
}

aspect production regexSetInverted
top::RegexItem ::= '[' '^' g::RegexCharSet ']'
{
  top.stringLiteral = false;
}

aspect production regexGroup
top::RegexItem ::= '(' r::Regex ')'
{
  top.stringLiteral = false;
}


