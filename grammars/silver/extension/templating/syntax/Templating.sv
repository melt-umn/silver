grammar silver:extension:templating:syntax;

import silver:definition:core only Expr, RCurly_t;

terminal TripleQuote /\"\"\"/;
terminal OpenEscape '${';
terminal DoubleDollar '$$';
terminal QuoteWater /[^$\n\t\"]+/;
terminal LiteralNewline /\n/;
terminal LiteralTab /\t/;
terminal LiteralQuote /\"/;

{-- A string, with escaped expressions within -}
nonterminal TemplateString with location;
{-- A list of alternating String/Exprs -}
nonterminal TemplateStringBody with location;
{-- Either a String or an Expr -}
nonterminal TemplateStringBodyItem with location;
{-- List that yields a string -}
nonterminal Water with location, waterString;
{-- Components that yield a string -}
nonterminal WaterItem with location, waterString;

{-- The string corresponding to the water -}
synthesized attribute waterString :: String;

concrete production templateString
top::TemplateString ::= TripleQuote b::TemplateStringBody TripleQuote
layout {}
{
}

concrete production templateStringEmpty
top::TemplateString ::= TripleQuote TripleQuote
layout {}
{
}

concrete production bodyCons
top::TemplateStringBody ::= h::TemplateStringBodyItem  t::TemplateStringBody
layout {}
{
}

concrete production bodyOne
top::TemplateStringBody ::= h::TemplateStringBodyItem
layout {}
{
}

concrete production bodyOneWater
top::TemplateStringBody ::= h::Water
layout {}
{
}

concrete production itemWaterEscape
top::TemplateStringBodyItem ::= w::Water '${' e::Expr '}'
layout {}
{
}

concrete production itemEscape
top::TemplateStringBodyItem ::= '${' e::Expr '}'
layout {}
{
}

concrete production waterCons
top::Water ::= h::Water  t::WaterItem
layout {}
{
  top.waterString = h.waterString ++ t.waterString;
}

concrete production waterOne
top::Water ::= h::WaterItem
layout {}
{
  top.waterString = h.waterString;
}

{- We don't bother to try to capture escaped literals (\n for example)
 - because they *should* be interpreted, and thus, passed untouched to
 - java. TODO: wait, should they??
 -}

concrete production water
top::WaterItem ::= w::QuoteWater
layout {}
{
  top.waterString = w.lexeme;
}

concrete production waterDollar
top::WaterItem ::= '$$'
layout {}
{
  top.waterString = "$";
}

concrete production waterNewline
top::WaterItem ::= LiteralNewline
layout {}
{
  top.waterString = "\\n";
}

concrete production waterTab
top::WaterItem ::= LiteralTab
layout {}
{
  top.waterString = "\\t";
}

concrete production waterQuote
top::WaterItem ::= LiteralQuote
layout {}
{
  top.waterString = "\\\"";
}


