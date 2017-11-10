grammar silver:support:monto:products;

import silver:json;

abstract production highlightingProduct
top::ProductValue ::= tokens::[HighlightToken]
{
  forwards to productValue("highlighting", jsonArray(map((.json), tokens)));
}

nonterminal HighlightToken with json;

abstract production highlightToken
top::HighlightToken ::= startByte::Integer endByte::Integer color::Color
{
  top.json = jsonObject(obj);
  local obj :: [Pair<String Json>] =
    [ pair("start_byte", jsonInteger(startByte))
    , pair("end_byte", jsonInteger(endByte))
    , pair("color", color.json)
    ];
}

nonterminal Color with json;

abstract production commentColor
top::Color ::=
{
  top.json = jsonObject(
    [ pair("type", jsonString("token"))
    , pair("value", jsonString("comment"))
    ]);
}

abstract production functionColor
top::Color ::=
{
  top.json = jsonObject(
    [ pair("type", jsonString("token"))
    , pair("value", jsonString("function"))
    ]);
}

abstract production identifierColor
top::Color ::=
{
  top.json = jsonObject(
    [ pair("type", jsonString("token"))
    , pair("value", jsonString("identifier"))
    ]);
}

abstract production keywordColor
top::Color ::=
{
  top.json = jsonObject(
    [ pair("type", jsonString("token"))
    , pair("value", jsonString("keyword"))
    ]);
}

abstract production literalColor
top::Color ::=
{
  top.json = jsonObject(
    [ pair("type", jsonString("token"))
    , pair("value", jsonString("literal"))
    ]);
}

abstract production punctuationColor
top::Color ::=
{
  top.json = jsonObject(
    [ pair("type", jsonString("token"))
    , pair("value", jsonString("punctuation"))
    ]);
}

abstract production typeColor
top::Color ::=
{
  top.json = jsonObject(
    [ pair("type", jsonString("token"))
    , pair("value", jsonString("type"))
    ]);
}

abstract production paletteColor
top::Color ::= idx::Integer
{
  top.json = jsonObject(
    [ pair("type", jsonString("palette"))
    , pair("value", jsonInteger(idx))
    ]);
}
