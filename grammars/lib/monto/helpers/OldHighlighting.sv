grammar lib:monto:helpers;

import lib:json;

nonterminal Font with json, fgColor, bgColor, fontFamily, fontSize, fontStyle, fontVariant, fontWeight;

synthesized attribute fgColor :: Maybe<Color>;
synthesized attribute bgColor :: Maybe<Color>;
synthesized attribute fontFamily :: Maybe<String>;
synthesized attribute fontSize :: Maybe<Integer>;
synthesized attribute fontStyle :: Maybe<String>;
synthesized attribute fontVariant :: Maybe<String>;
synthesized attribute fontWeight :: Maybe<String>;

abstract production font
top::Font ::= fgColor::Maybe<Color> bgColor::Maybe<Color> fontFamily::Maybe<String> fontSize::Maybe<Integer> fontStyle::Maybe<String> fontVariant::Maybe<String> fontWeight::Maybe<String>
{
  top.fgColor = fgColor;
  top.bgColor = bgColor;
  top.fontFamily = fontFamily;
  top.fontSize = fontSize;
  top.fontStyle = fontStyle;
  top.fontVariant = fontVariant;
  top.fontWeight = fontWeight;
  top.json = jsonObject(catMaybes(
    [ mapMaybe(\c::Color -> pair("color", c.json), top.fgColor)
    , mapMaybe(\c::Color -> pair("bgcolor", c.json), top.bgColor)
    , mapMaybe(\f::String -> pair("family", jsonString(f)), top.fontFamily)
    , mapMaybe(\s::Integer -> pair("size", jsonInteger(s)), top.fontSize)
    , mapMaybe(\s::String -> pair("style", jsonString(s)), top.fontStyle)
    , mapMaybe(\v::String -> pair("variant", jsonString(v)), top.fontVariant)
    , mapMaybe(\w::String -> pair("weight", jsonString(w)), top.fontWeight)
    ]));
}

nonterminal Color with json;

-- Red, Green, and Blue *seem to* be 0-255. This isn't documented, though.
abstract production color
top::Color ::= red::Integer green::Integer blue::Integer
{
  top.json = jsonObject(
    [ pair("red", jsonInteger(red))
    , pair("green", jsonInteger(green))
    , pair("blue", jsonInteger(blue))
    ]);
}
