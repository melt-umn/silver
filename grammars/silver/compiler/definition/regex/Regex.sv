grammar silver:compiler:definition:regex;

synthesized attribute regString :: String;

lexer class Operator;
lexer class Escape;

terminal Plus_t          '+' lexer classes { Operator };
terminal Kleene_t        '*' lexer classes { Operator };
terminal Optional_t      '?' lexer classes { Operator };
terminal Choice_t        '|' lexer classes { Operator };
terminal Range_t         '-' lexer classes { Operator };
terminal RegexNot_t      '^' lexer classes { Operator };
terminal RegexLBrack_t   '[' lexer classes { Operator };
terminal RegexRBrack_t   ']' lexer classes { Operator };
terminal RegexLParen_t   '(' lexer classes { Operator };
terminal RegexRParen_t   ')' lexer classes { Operator };
terminal RegexWildcard_t '.' lexer classes { Operator };
terminal RegexChar_t     /./ lexer classes { Escape };
terminal EscapedChar_t /\\./ lexer classes { Escape };

-- Disambiguate these, rather than using lexical precedence,
-- so we can avoid superfluous escapes (e.g. /--.*/).
-- This is the behavior of most regex libraries.
disambiguate RegexChar_t, Plus_t { pluck Plus_t; }
disambiguate RegexChar_t, Kleene_t { pluck Kleene_t; }
disambiguate RegexChar_t, Optional_t { pluck Optional_t; }
disambiguate RegexChar_t, Choice_t { pluck Choice_t; }
disambiguate RegexChar_t, Range_t { pluck Range_t; }
disambiguate RegexChar_t, RegexNot_t { pluck RegexNot_t; }
disambiguate RegexChar_t, RegexLBrack_t { pluck RegexLBrack_t; }
disambiguate RegexChar_t, RegexRBrack_t { pluck RegexRBrack_t; }
disambiguate RegexChar_t, RegexLParen_t { pluck RegexLParen_t; }
disambiguate RegexChar_t, RegexRParen_t { pluck RegexRParen_t; }
disambiguate RegexChar_t, RegexWildcard_t { pluck RegexWildcard_t; }

-- TODO: It might be wise to someday do a CST/AST split on this.


{--
 - A basic regular expression.
 -
 - At lowest precedence, a regex consists of a series of choices (a|b|c)
 -}
nonterminal Regex with regString;

concrete production regexEpsilon
top::Regex ::=
{
  top.regString = "";
}

concrete production regexSeq
top::Regex ::= h::RegexSeq
{
  top.regString = h.regString;
}

concrete production regexChoice
top::Regex ::= h::RegexSeq '|' t::Regex
{
  top.regString = h.regString ++ "|" ++ t.regString;
}


{--
 - A sequence of regular expressions.
 -}
nonterminal RegexSeq with regString;

concrete production regexSeqSnoc
top::RegexSeq ::= h::RegexSeq t::RegexRepetition
{
  top.regString = h.regString ++ t.regString;
}

concrete production regexSeqOne
top::RegexSeq ::= t::RegexRepetition
{
  top.regString = t.regString;
}


{--
 - A RegexItem with an optional repetition operator (*+?)
 -}
nonterminal RegexRepetition with regString;

concrete production regexKleene
top::RegexRepetition ::= i::RegexItem '*'
{
  top.regString = i.regString ++ "*";
}

concrete production regexPlus
top::RegexRepetition ::= i::RegexItem '+'
{
  top.regString = i.regString ++ "+";
}

concrete production regexOptional
top::RegexRepetition ::= i::RegexItem '?'
{
  top.regString = i.regString ++ "?";
}

concrete production regexOnce
top::RegexRepetition ::= i::RegexItem
{
  top.regString = i.regString;
}


{--
 - A single matched entity (char, wildcard, set, group)
 -}
nonterminal RegexItem with regString;      -- characters or sequences/sets

concrete production regexCharItem
top::RegexItem ::= char::RegexChar
{
  top.regString = char.regString;
}

concrete production regexWildcard
top::RegexItem ::= '.'
{
  top.regString = ".";
}

concrete production regexSet
top::RegexItem ::= '[' g::RegexCharSet ']'
{
  top.regString = "[" ++ g.regString ++ "]";
}

concrete production regexSetInverted
top::RegexItem ::= '[' '^' g::RegexCharSet ']'
{
  top.regString = "[^" ++ g.regString ++ "]";
}

concrete production regexGroup
top::RegexItem ::= '(' r::Regex ')'
{
  top.regString = "(" ++ r.regString ++ ")";
}


{--
 - A list of options or ranges within a regexSet.
 -}
nonterminal RegexCharSet with regString;

concrete production regexCharSetSnoc
top::RegexCharSet ::= h::RegexCharSet  t::RegexCharSetItem
{
  top.regString = h.regString ++ t.regString;
}

concrete production regexCharSetOne
top::RegexCharSet ::= t::RegexCharSetItem
{
  top.regString = t.regString;
}


{--
 - An option or range within a regexSet.
 -}
nonterminal RegexCharSetItem with regString;

concrete production regexSetChar
top::RegexCharSetItem ::= char::RegexChar
{
  top.regString = char.regString;
}

concrete production regexSetRange
top::RegexCharSetItem ::= l::RegexChar '-' u::RegexChar
{
  top.regString = l.regString ++ "-" ++ u.regString;
}


{--
 - A character, escaped or otherwise.
 -}
nonterminal RegexChar with regString;

concrete production regexChar
top::RegexChar ::= char::RegexChar_t
{
  top.regString = char.lexeme;
}

concrete production regexEscapedChar
top::RegexChar ::= esc::EscapedChar_t
{
  top.regString = esc.lexeme;
}


---- Helper functions


{--
 - Concatenates two regular expressions.
 -}
function regexConcatenate
Regex ::= l::Regex  r::Regex
{
  return regexSeq(concatRegexItems([regexGroup('(', l, ')'), regexGroup('(', r, ')')]));
}

{--
 - Concatenates a list of RegexItems, must be non-empty.
 -}
function concatRegexItems
RegexSeq ::= l::[RegexItem]
{
  return foldl(regexSeqSnoc, regexSeqOne(regexOnce(head(l))), map(regexOnce, tail(l)));
}

{--
 - Converts a character to a RegexItem, escaping if necessary.
 -}
function regexCharToItem
RegexItem ::= ch::String
{
  return regexCharItem(
    if isAlpha(ch) || isDigit(ch)
    then regexChar(terminal(RegexChar_t, ch))
    else regexEscapedChar(terminal(EscapedChar_t, "\\" ++ ch)));
}

{--
 - Returns a regex that matches a string literal.
 - (i.e. no interpretation of special characters.)
 -}
function regexLiteral
Regex ::= s::String
{
  return if length(s) == 0
  then regexEpsilon()
  else regexSeq(concatRegexItems(map(regexCharToItem, explode("", s))));
}


