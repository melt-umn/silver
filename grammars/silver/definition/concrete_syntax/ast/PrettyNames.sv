grammar silver:definition:concrete_syntax:ast;

function asPrettyName
Maybe<String> ::= r::Regex
{
  return mapMaybe(\x::String -> "'" ++ xmlEscapeString(x) ++ "'", r.asLiteral);
}

synthesized attribute asLiteral::Maybe<String> occurs on Regex, RegexSeq,
  RegexRepetition, RegexItem, RegexCharSet, RegexCharSetItem, RegexChar;

aspect production regexEpsilon
top::Regex ::=
{
  top.asLiteral = just("");
}

aspect production regexSeq
top::Regex ::= h::RegexSeq
{
  top.asLiteral = h.asLiteral;
}

aspect production regexChoice
top::Regex ::= _ _ _
{
  -- TODO: In theory, we could check if one side is empty, and then go to the
  -- other.
  top.asLiteral = nothing();
}


aspect production regexSeqSnoc
top::RegexSeq ::= h::RegexSeq t::RegexRepetition
{
  top.asLiteral = case pair(h.asLiteral, t.asLiteral) of
  | pair(just(h), just(t)) -> just(h ++ t)
  | _ -> nothing()
  end;
}

aspect production regexSeqOne
top::RegexSeq ::= t::RegexRepetition
{
  top.asLiteral = t.asLiteral;
}


aspect production regexKleene
top::RegexRepetition ::= _ _
{
  -- TODO: We could check if the repeated construct is zero-length and give out
  -- just("") if so.
  top.asLiteral = nothing();
}

aspect production regexPlus
top::RegexRepetition ::= _ _
{
  -- TODO: We could check if the repeated construct is zero-length and give out
  -- just("") if so.
  top.asLiteral = nothing();
}

aspect production regexOptional
top::RegexRepetition ::= _ _
{
  -- TODO: We could check if the repeated construct is zero-length and give out
  -- just("") if so.
  top.asLiteral = nothing();
}

aspect production regexOnce
top::RegexRepetition ::= i::RegexItem
{
  top.asLiteral = i.asLiteral;
}


aspect production regexCharItem
top::RegexItem ::= char::RegexChar
{
  top.asLiteral = char.asLiteral;
}

aspect production regexWildcard
top::RegexItem ::= _
{
  top.asLiteral = nothing();
}

aspect production regexSet
top::RegexItem ::= _ g::RegexCharSet _
{
  top.asLiteral = g.asLiteral;
}

aspect production regexSetInverted
top::RegexItem ::= _ _ g::RegexCharSet _
{
  top.asLiteral = nothing();
}

aspect production regexGroup
top::RegexItem ::= _ r::Regex _
{
  top.asLiteral = r.asLiteral;
}

aspect production regexCharSetSnoc
top::RegexCharSet ::= h::RegexCharSet t::RegexCharSetItem
{
  top.asLiteral = case pair(h.asLiteral, t.asLiteral) of
  | pair(just(h), just(t)) -> just(h ++ t)
  | _ -> nothing()
  end;
}

aspect production regexCharSetOne
top::RegexCharSet ::= t::RegexCharSetItem
{
  top.asLiteral = t.asLiteral;
}

aspect production regexSetChar
top::RegexCharSetItem ::= char::RegexChar
{
  top.asLiteral = char.asLiteral;
}

aspect production regexSetRange
top::RegexCharSetItem ::= l::RegexChar _ u::RegexChar
{
  -- TODO: In theory, we could actually take this apart.
  top.asLiteral = nothing();
}

aspect production regexChar
top::RegexChar ::= char::RegexChar_t
{
  top.asLiteral = just(char.lexeme);
}

aspect production regexEscapedChar
top::RegexChar ::= esc::EscapedChar_t
{
  -- TODO: Support this better.
  top.asLiteral = just(substring(1, 2, esc.lexeme));
}
