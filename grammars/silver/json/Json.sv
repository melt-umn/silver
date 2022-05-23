-- Should this be under the silver:... grammar tree?
-- If we do the refactor mentioned in #152, #175, this should probably end up
-- as silver:json, with proper concrete syntax and everything.
grammar silver:json;

@{- Json is the type of JSON values. -}
nonterminal Json with jsonString;

@{- Converts the JSON value to a string. -}
synthesized attribute jsonString :: String;

@{- JsonRepr represents conversion to and from JSON. -}
class JsonRepr a {
  toJson :: (Json ::= a);
  fromJson :: (Either<String a> ::= Json);
}

instance JsonRepr Json {
  toJson = id;
  fromJson = right;
}

instance JsonRepr Boolean {
  toJson = jsonBoolean;
  fromJson = \ j::Json ->
    case j of
    | jsonBoolean(x) -> right(x)
    | _ -> left(s"Expected boolean, got ${j.jsonString}")
    end;
}

instance JsonRepr Float {
  toJson = jsonFloat;
  fromJson = \ j::Json ->
    case j of
    | jsonFloat(x) -> right(x)
    | _ -> left(s"Expected float, got ${j.jsonString}")
    end;
}

instance JsonRepr Integer {
  toJson = jsonInteger;
  fromJson = \ j::Json ->
    case j of
    | jsonFloat(x) when x == toFloat(toInteger(x)) -> right(toInteger(x))
    | _ -> left(s"Expected integer, got ${j.jsonString}")
    end;
}

instance JsonRepr String {
  toJson = jsonString;
  fromJson = \ j::Json ->
    case j of
    | jsonString(x) -> right(x)
    | _ -> left(s"Expected string, got ${j.jsonString}")
    end;
}

instance JsonRepr a => JsonRepr [a] {
  toJson = \xs::[a] -> jsonArray(map(toJson, xs));
  fromJson = \ j::Json ->
    case j of
    | jsonArray(x) -> traverseA(fromJson, x)
    | _ -> left(s"Expected array, got ${j.jsonString}")
    end;
}

instance JsonRepr a => JsonRepr Maybe<a> {
  toJson = \mx::Maybe<a> ->
    case mx of
    | just(x) -> toJson(x)
    | nothing() -> jsonNull()
    end;
  fromJson = \ j::Json ->
    case j of
    | jsonNull() -> right(nothing())
    | _ -> map(just, fromJson(j))
    end;
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
