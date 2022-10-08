grammar silver:regex:concrete_syntax;

imports silver:langutil;
imports silver:langutil:lsp as lsp;
imports silver:regex as abs;

lexer class Operator extends lsp:Regexp;
lexer class Escape extends lsp:Regexp;

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

{--
 - A basic regular expression.
 -
 - At lowest precedence, a regex consists of a series of choices (a|b|c)
 -}
nonterminal Regex with unparse, ast<abs:Regex>;

concrete production regexEpsilon
top::Regex ::=
{
  top.unparse = "";
  abstract abs:epsilon;
}

concrete production regexSeq
top::Regex ::= h::RegexSeq
{
  top.unparse = h.unparse;
  top.ast = h.ast;
}

concrete production regexChoice
top::Regex ::= h::RegexSeq '|' t::Regex
{
  top.unparse = h.unparse ++ "|" ++ t.unparse;
  abstract abs:alt;
}


{--
 - A sequence of regular expressions.
 -}
nonterminal RegexSeq with unparse, ast<abs:Regex>;

concrete production regexSeqSnoc
top::RegexSeq ::= h::RegexSeq t::RegexRepetition
{
  top.unparse = h.unparse ++ t.unparse;
  abstract abs:seq;
}

concrete production regexSeqOne
top::RegexSeq ::= t::RegexRepetition
{
  top.unparse = t.unparse;
  top.ast = t.ast;
}


{--
 - A RegexItem with an optional repetition operator (*+?)
 -}
nonterminal RegexRepetition with unparse, ast<abs:Regex>;

concrete production regexKleene
top::RegexRepetition ::= i::RegexItem '*'
{
  top.unparse = i.unparse ++ "*";
  abstract abs:star;
}

concrete production regexPlus
top::RegexRepetition ::= i::RegexItem '+'
{
  top.unparse = i.unparse ++ "+";
  abstract abs:plus;
}

concrete production regexOptional
top::RegexRepetition ::= i::RegexItem '?'
{
  top.unparse = i.unparse ++ "?";
  abstract abs:opt;
}

concrete production regexOnce
top::RegexRepetition ::= i::RegexItem
{
  top.unparse = i.unparse;
  top.ast = i.ast;
}


{--
 - A single matched entity (char, wildcard, set, group)
 -}
nonterminal RegexItem with unparse, ast<abs:Regex>;      -- characters or sequences/sets

concrete production regexCharItem
top::RegexItem ::= char::RegexChar
{
  top.unparse = char.unparse;
  abstract abs:char;
}

concrete production regexWildcard
top::RegexItem ::= '.'
{
  top.unparse = ".";
  abstract abs:wildChar;
}

concrete production regexSet
top::RegexItem ::= '[' g::RegexCharSet ']'
{
  top.unparse = "[" ++ g.unparse ++ "]";
  top.ast = g.ast;
}

concrete production regexSetInverted
top::RegexItem ::= '[' '^' g::RegexCharSet ']'
{
  top.unparse = "[^" ++ g.unparse ++ "]";
  abstract abs:negChars;
}

concrete production regexGroup
top::RegexItem ::= '(' r::Regex ')'
{
  top.unparse = "(" ++ r.unparse ++ ")";
  top.ast = r.ast;
}


{--
 - A list of options or ranges within a regexSet.
 -}
nonterminal RegexCharSet with unparse, ast<abs:Regex>;

concrete production regexCharSetSnoc
top::RegexCharSet ::= h::RegexCharSet  t::RegexCharSetItem
{
  top.unparse = h.unparse ++ t.unparse;
  abstract abs:alt;
}

concrete production regexCharSetOne
top::RegexCharSet ::= t::RegexCharSetItem
{
  top.unparse = t.unparse;
  top.ast = t.ast;
}


{--
 - An option or range within a regexSet.
 -}
nonterminal RegexCharSetItem with unparse, ast<abs:Regex>;

concrete production regexSetChar
top::RegexCharSetItem ::= char::RegexChar
{
  top.unparse = char.unparse;
  abstract abs:char;
}

concrete production regexSetRange
top::RegexCharSetItem ::= l::RegexChar '-' u::RegexChar
{
  top.unparse = l.unparse ++ "-" ++ u.unparse;
  abstract abs:charRange;
}


{--
 - A character, escaped or otherwise.
 -}
nonterminal RegexChar with unparse, ast<Integer>;

concrete production regexChar
top::RegexChar ::= char::RegexChar_t
{
  top.unparse = char.lexeme;
  top.ast = head(stringToChars(char.lexeme));
}

concrete production regexEscapedChar
top::RegexChar ::= esc::EscapedChar_t
{
  top.unparse = esc.lexeme;
  top.ast = head(stringToChars(unescapeString(esc.lexeme)));
}
