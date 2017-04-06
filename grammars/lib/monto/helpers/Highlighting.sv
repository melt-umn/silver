grammar lib:monto:helpers;
import lib:json;
-- import silver:langutil;

function makeHighlightingCallback
(Json ::= String String) ::= parse::(ParseResult<a> ::= String String)
{
  return defaultHighlightingCallback(parse, _, _);
}

function defaultHighlightingCallback
Json ::= parse::(ParseResult<a> ::= String String) src::String fileName::String
{
  local result :: ParseResult<a> = parse(src, fileName);
  return jsonArray(if result.parseSuccess then
    map((.json), map((.token), result.parseTerminals))
  else
    []);
}

synthesized attribute token :: Token;
attribute token occurs on TerminalDescriptor;

aspect production terminalDescriptor
top::TerminalDescriptor ::= lexeme::String lexerClasses::[String] terminalGrammarName::String terminalName::String terminalLocation::Location
{
  top.token = token(
    terminalLocation.index,
    terminalLocation.endIndex - terminalLocation.index,
    error("TODO font"));
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

synthesized attribute fgColor :: Color;
synthesized attribute bgColor :: Color;
synthesized attribute family :: String;
synthesized attribute size :: Integer;
synthesized attribute style :: String;
synthesized attribute variant :: String;
synthesized attribute weight :: String;

abstract production font
top::Font ::= fgColor::Color bgColor::Color family::String size::Integer style::String variant::String weight::String
{
  top.fgColor = fgColor;
  top.bgColor = bgColor;
  top.family = family;
  top.size = size;
  top.style = style;
  top.variant = variant;
  top.weight = weight;
  top.json = jsonObject(
    [ pair("color", top.fgColor.json)
    , pair("bgcolor", top.bgColor.json)
    , pair("family", jsonString(top.family))
    , pair("size", jsonInteger(top.size))
    , pair("style", jsonString(top.style))
    , pair("variant", jsonString(top.variant))
    , pair("weight", jsonString(top.weight))
    ]);
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
