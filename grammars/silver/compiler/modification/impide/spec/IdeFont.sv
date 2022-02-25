grammar silver:compiler:modification:impide:spec;

-- TODO: er, this should probably be moved to :cstast. It's not used here!
nonterminal Font with compareTo, isEqual, getTextAttribute, pluginXmlSpec;

synthesized attribute getTextAttribute :: String;
synthesized attribute pluginXmlSpec :: String;

abstract production font
top::Font ::= color::Color isBold::Boolean isItalic::Boolean
{
  propagate compareTo, isEqual;
  top.getTextAttribute =
    s"""TextAttributeProvider.getAttribute(display, ${toString(color.r)}, ${toString(color.g)}, ${toString(color.b)}, ${toString(isBold)}, ${toString(isItalic)})""";
  top.pluginXmlSpec = 
    s"""r="${toString(color.r)}" g="${toString(color.g)}" b="${toString(color.b)}" bold="${toString(isBold)}" italic="${toString(isItalic)}" """;
}

nonterminal Color with compareTo, isEqual, r, g, b;

synthesized attribute r :: Integer;
synthesized attribute g :: Integer;
synthesized attribute b :: Integer;

abstract production makeColor
top::Color ::= r::Integer g::Integer b::Integer
{
  propagate compareTo, isEqual;
  top.r = r;
  top.g = g;
  top.b = b;
}


