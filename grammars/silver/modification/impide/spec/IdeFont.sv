grammar silver:modification:impide:spec;

-- TODO: er, this should probably be moved to :cstast. It's not used here!
nonterminal Font with getTextAttribute;

synthesized attribute getTextAttribute :: String;

abstract production font
top::Font ::= color::Color isBold::Boolean isItalic::Boolean
{
  top.getTextAttribute =
    s"""TextAttributeProvider.getAttribute(display, ${toString(color.r)}, ${toString(color.g)}, ${toString(color.b)}, ${boolToString(isBold)}, ${boolToString(isItalic)})""";
}

-- really, this should be part of the standard toString. TODO
function boolToString
String ::= b::Boolean
{
  return if b then "true" else "false";
}


nonterminal Color with r, g, b;

synthesized attribute r :: Integer;
synthesized attribute g :: Integer;
synthesized attribute b :: Integer;

abstract production makeColor
top::Color ::= r::Integer g::Integer b::Integer
{
  top.r = r;
  top.g = g;
  top.b = b;
}


