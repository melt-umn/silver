grammar silver:compiler:extension:templating:syntax;

imports silver:compiler:definition:core;
imports silver:langutil:pp;

terminal TripleQuote /\"\"\"/ lexer classes {LITERAL};
terminal DoubleDollar '$$' lexer classes {LITERAL};
terminal QuoteWater /[^$\r\n\t\"\\]+/ lexer classes {LITERAL};
terminal SingleLineQuoteWater /([^$\r\n\t\"\\]|[\\][\"]|[\\][\\]|[\\]b|[\\]r|[\\]f|[\\]t)+/ lexer classes {LITERAL};
terminal LiteralNewline /(\n|\r\n)/ lexer classes {LITERAL};
terminal LiteralTab /\t/ lexer classes {LITERAL};
terminal LiteralQuote /\"/ lexer classes {LITERAL};
terminal LiteralBackslash /\\/ lexer classes {LITERAL};
terminal LiteralBackslashN /\\n/ lexer classes {LITERAL};

terminal OpenEscape '${';

{-- A string without the first triple quote, with escaped expressions within -}
nonterminal TemplateString with location;
{-- A single-line string without the first quote, with escaped expressions within -}
nonterminal SingleLineTemplateString with location;
{-- A list of alternating String/Exprs -}
nonterminal TemplateStringBody with location;
{-- A single-line list of alternating String/Exprs -}
nonterminal SingleLineTemplateStringBody with location;
{-- Either a String or an Expr -}
nonterminal TemplateStringBodyItem with location;
{-- Either a single-line String or an Expr -}
nonterminal SingleLineTemplateStringBodyItem with location;
{-- An escape -}
nonterminal NonWater with location;
{-- List that yields a string -}
nonterminal Water with location, waterString, waterDoc;
{-- List that yields a single-line string -}
nonterminal SingleLineWater with location, waterString, waterDoc;
{-- Components that yield a string -}
nonterminal WaterItem with location, waterString, waterDoc;
{-- Components that yield a single-line string -}
nonterminal SingleLineWaterItem with location, waterString, waterDoc;

{-- The string corresponding to the water -}
synthesized attribute waterString :: String;
{-- The Document corresponding to the water -}
synthesized attribute waterDoc :: Document;

concrete production templateString
top::TemplateString ::= b::TemplateStringBody TripleQuote
{
}

concrete production templateStringEmpty
top::TemplateString ::= TripleQuote
{
}

concrete production singleLineTemplateString
top::SingleLineTemplateString ::= b::SingleLineTemplateStringBody LiteralQuote
{
}

concrete production singleLineTemplateStringEmpty
top::SingleLineTemplateString ::= LiteralQuote
{
}

concrete production bodyCons
top::TemplateStringBody ::= h::TemplateStringBodyItem  t::TemplateStringBody
{
}

concrete production bodyOne
top::TemplateStringBody ::= h::TemplateStringBodyItem
{
}

concrete production bodyOneWater
top::TemplateStringBody ::= h::Water
{
}

concrete production singleLineBodyCons
top::SingleLineTemplateStringBody ::= h::SingleLineTemplateStringBodyItem  t::SingleLineTemplateStringBody
{
}

concrete production singleLineBodyOne
top::SingleLineTemplateStringBody ::= h::SingleLineTemplateStringBodyItem
{
}

concrete production singleLineBodyOneWater
top::SingleLineTemplateStringBody ::= h::SingleLineWater
{
}

concrete production itemWaterEscape
top::TemplateStringBodyItem ::= w::Water nw::NonWater
{
}

concrete production itemEscape
top::TemplateStringBodyItem ::= nw::NonWater
{
}

concrete production singleLineItemWaterEscape
top::SingleLineTemplateStringBodyItem ::= w::SingleLineWater nw::NonWater
{
}

concrete production singleLineItemEscape
top::SingleLineTemplateStringBodyItem ::= nw::NonWater
{
}

concrete production nonwater
top::NonWater ::= '${' e::Expr '}'
layout {BlockComments, Comments, WhiteSpace}
{
}

concrete production waterCons
top::Water ::= h::Water  t::WaterItem
{
  top.waterString = h.waterString ++ t.waterString;
  top.waterDoc = flatCat(h.waterDoc, t.waterDoc);
}

concrete production waterOne
top::Water ::= h::WaterItem
{
  top.waterString = h.waterString;
  top.waterDoc = h.waterDoc;
}

concrete production water
top::WaterItem ::= w::QuoteWater
{
  top.waterString = w.lexeme;
  top.waterDoc = text(w.lexeme);
}

concrete production waterDollar
top::WaterItem ::= '$$'
{
  top.waterString = "$";
  top.waterDoc = text("$");
}

concrete production waterBackSlash
top::WaterItem ::= LiteralBackslash
{
  -- The reason I decided to make backslashes not "work" is due to
  -- dealing with \"  Originally, this turned into \\" in the string
  -- because the quote got escaped... this of course, was disaster."
  top.waterString = "\\\\";
  top.waterDoc = text("\\");
}

concrete production waterNewline
top::WaterItem ::= LiteralNewline
{
  -- We always interpret newlines as just \n, even if the source file was \r\n.
  top.waterString = "\\n";
  top.waterDoc = realLine();
}

concrete production waterTab
top::WaterItem ::= LiteralTab
{
  top.waterString = "\\t";
  top.waterDoc = text("\t");
}

concrete production waterQuote
top::WaterItem ::= LiteralQuote
{
  top.waterString = "\\\"";
  top.waterDoc = text("\"");
}

concrete production singleLineWaterCons
top::SingleLineWater ::= h::SingleLineWater  t::SingleLineWaterItem
{
  top.waterString = h.waterString ++ t.waterString;
  top.waterDoc = flatCat(h.waterDoc, t.waterDoc);
}

concrete production singleLineWaterOne
top::SingleLineWater ::= h::SingleLineWaterItem
{
  top.waterString = h.waterString;
  top.waterDoc = h.waterDoc;
}

concrete production singleLineWater
top::SingleLineWaterItem ::= w::SingleLineQuoteWater
{
  top.waterString = w.lexeme;
  top.waterDoc = text(unescapeString(w.lexeme));  -- Need to interpret escape sequences besides \\
}

concrete production singleLineWaterDollar
top::SingleLineWaterItem ::= '$$'
{
  top.waterString = "$";
  top.waterDoc = text("$");
}

-- Seperated from singleLineWater since we don't want newlines in text()
concrete production singleLineWaterNewline
top::SingleLineWaterItem ::= LiteralBackslashN
{
  top.waterString = "\\n";
  top.waterDoc = realLine();
}

concrete production singleLineWaterBackSlash
top::SingleLineWaterItem ::= LiteralBackslash
{
  -- Same as waterBackSlash
  top.waterString = "\\\\";
  top.waterDoc = text("\\");
}

function flatCat
Document ::= d1::Document d2::Document
{
  return
    case d1, d2 of
    | text(s1), text(s2) -> text(s1 ++ s2)
    | _, _ -> cat(d1, d2)
    end;
}