grammar lib:monto:helpers;

import lib:json;

function makeHighlightingCallback
(Json ::= String String) ::= parse::(ParseResult<a> ::= String String) highlight::(Font ::= TerminalDescriptor)
{
  return defaultHighlightingCallback(parse, highlight, _, _);
}

function defaultHighlightingCallback
Json ::= parse::(ParseResult<a> ::= String String) highlight::(Font ::= TerminalDescriptor) src::String fileName::String
{
  local result :: ParseResult<a> = parse(src, fileName);
  return jsonArray(if result.parseSuccess then
    map((.json), map(terminalDescriptorToToken(highlight, _), result.parseTerminals))
  else
    []); -- TODO What should happen on failure?
}

function terminalDescriptorToToken
Token ::= highlight::(Font ::= TerminalDescriptor) td::TerminalDescriptor
{
  return token(
    td.terminalLocation.index,
    td.terminalLocation.endIndex - td.terminalLocation.index,
    highlight(td));
}

nonterminal Token with json, tokenOffset, tokenLength, font;

synthesized attribute tokenOffset :: Integer;
synthesized attribute tokenLength :: Integer;
synthesized attribute font :: Font;

abstract production token
top::Token ::= tokenOffset::Integer tokenLength::Integer font::Font
{
  top.tokenOffset = tokenOffset;
  top.tokenLength = tokenLength;
  top.font = font;
  top.json = jsonObject(
    [ pair("offset", jsonInteger(top.tokenOffset))
    , pair("length", jsonInteger(top.tokenLength))
    , pair("font", top.font.json)
    ]);
}

nonterminal Font with json, fgColor, bgColor, family, size, style, variant, weight;

synthesized attribute fgColor :: Maybe<Color>;
synthesized attribute bgColor :: Maybe<Color>;
synthesized attribute family :: Maybe<String>;
synthesized attribute size :: Maybe<Integer>;
synthesized attribute style :: Maybe<String>;
synthesized attribute variant :: Maybe<String>;
synthesized attribute weight :: Maybe<String>;

abstract production font
top::Font ::= fgColor::Maybe<Color> bgColor::Maybe<Color> family::Maybe<String> size::Maybe<Integer> style::Maybe<String> variant::Maybe<String> weight::Maybe<String>
{
  top.fgColor = fgColor;
  top.bgColor = bgColor;
  top.family = family;
  top.size = size;
  top.style = style;
  top.variant = variant;
  top.weight = weight;
  top.json = jsonObject(catMaybes(
    [ mapMaybe(\c::Color -> pair("color", c.json), top.fgColor)
    , mapMaybe(\c::Color -> pair("bgcolor", c.json), top.bgColor)
    , mapMaybe(\f::String -> pair("family", jsonString(f)), top.family)
    , mapMaybe(\s::Integer -> pair("size", jsonInteger(s)), top.size)
    , mapMaybe(\s::String -> pair("style", jsonString(s)), top.style)
    , mapMaybe(\v::String -> pair("variant", jsonString(v)), top.variant)
    , mapMaybe(\w::String -> pair("weight", jsonString(w)), top.weight)
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
