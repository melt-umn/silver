grammar silver:modification:copper;

nonterminal ParserAttrSpec with name, typerep, actionCode;

function parserAttrSpec
Decorated ParserAttrSpec ::= n::String t::TypeExp a::String
{
  return decorate i_parserAttrSpec(n, t, a) with {};
}

abstract production i_parserAttrSpec
top::ParserAttrSpec ::= n::String t::TypeExp a::String
{
  top.name = n;
  top.typerep = t;
  top.actionCode = a;
}

