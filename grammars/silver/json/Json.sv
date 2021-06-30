-- Should this be under the silver:... grammar tree?
-- If we do the refactor mentioned in #152, #175, this should probably end up
-- as silver:json, with proper concrete syntax and everything.
grammar silver:json;

@{- Json is the type of JSON values. -}
nonterminal Json with jsonString;

@{- Converts the JSON value to a string. -}
synthesized attribute jsonString :: String;

@{- ToJson represents conversion to JSON. -}
class ToJson a {
  toJson :: (Json ::= a);
}

instance ToJson Json {
  toJson = id;
}

instance ToJson Boolean {
  toJson = jsonBoolean;
}

instance ToJson Float {
  toJson = jsonFloat;
}

instance ToJson Integer {
  toJson = jsonInteger;
}

instance ToJson String {
  toJson = jsonString;
}

instance ToJson a => ToJson [a] {
  toJson = \xs::[a] -> jsonArray(map(toJson, xs));
}

abstract production jsonString
top::Json ::= str::String
{
  local escapeChar :: (String ::= Integer) =
    \ch::Integer -> case ch of
    |  0 -> "\\u0000"
    |  1 -> "\\u0001"
    |  2 -> "\\u0002"
    |  3 -> "\\u0003"
    |  4 -> "\\u0004"
    |  5 -> "\\u0005"
    |  6 -> "\\u0006"
    |  7 -> "\\u0007"
    |  8 -> "\\b"
    |  9 -> "\\t"
    | 10 -> "\\n"
    | 11 -> "\\u000b"
    | 12 -> "\\f"
    | 13 -> "\\r"
    | 14 -> "\\u000e"
    | 15 -> "\\u000f"
    | 16 -> "\\u0010"
    | 17 -> "\\u0011"
    | 18 -> "\\u0012"
    | 19 -> "\\u0013"
    | 20 -> "\\u0014"
    | 21 -> "\\u0015"
    | 22 -> "\\u0016"
    | 23 -> "\\u0017"
    | 24 -> "\\u0018"
    | 25 -> "\\u0019"
    | 26 -> "\\u001a"
    | 27 -> "\\u001b"
    | 28 -> "\\u001c"
    | 29 -> "\\u001d"
    | 30 -> "\\u001e"
    | 31 -> "\\u001f"
    | 34 -> "\\\""
    | 92 -> "\\\\"
    | ch -> charsToString([ch])
    end;
  top.jsonString = "\"" ++ implode("", map(escapeChar, stringToChars(str))) ++ "\"";
}

function jsonInteger
Json ::= i::Integer
{
  return jsonFloat(toFloat(i));
}

abstract production jsonFloat
top::Json ::= float::Float
{
  top.jsonString = if float == toFloat(toInteger(float))
                   then toString(toInteger(float))
                   else toString(float);
}

abstract production jsonArray
top::Json ::= vals::[Json]
{
  local strs :: [String] = map(\j::Json -> j.jsonString, vals);
  top.jsonString = "[" ++ implode(",", strs) ++ "]";
}

abstract production jsonObject
top::Json ::= vals::[(String, Json)]
{
  local strs :: [String] = map(
    \p::(String, Json) -> jsonString(p.fst).jsonString ++ ":" ++ p.snd.jsonString,
    vals);
  top.jsonString = "{" ++ implode(",", strs) ++ "}";
}

abstract production jsonBoolean
top::Json ::= boolean::Boolean
{
  top.jsonString = toString(boolean);
}

abstract production jsonNull
top::Json ::=
{
  top.jsonString = "null";
}
