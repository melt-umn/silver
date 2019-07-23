grammar lib:lsp:json;

synthesized attribute jsonValue :: JSONValue;
synthesized attribute jsonValues :: [JSONValue];
synthesized attribute jsonKeyValuePair :: Pair<String JSONValue>;
synthesized attribute jsonKeyValuePairs :: [Pair<String JSONValue>];

nonterminal JSONValue with jsonString;

synthesized attribute jsonString :: String;

abstract production jsonArray
top::JSONValue ::= val::[JSONValue]
{
  top.jsonString = s"""[${implode(",", map((.jsonString), val))}]""";
}

abstract production jsonObject
top::JSONValue ::= val::[Pair<String JSONValue>]
{
  top.jsonString = s"""{${implode(",", map(jsonKeyValuePairToString, val))}}""";
}

abstract production jsonBoolean
top::JSONValue ::= val::Boolean
{
  top.jsonString = if val then "true" else "false";
}


abstract production jsonInteger
top::JSONValue ::= val::Integer
{
  top.jsonString = toString(val);
}

abstract production jsonNumber
top::JSONValue ::= val::Float
{
  top.jsonString = toString(val);
}

abstract production jsonString
top::JSONValue ::= val::String
{
  top.jsonString = "\"" ++ val ++ "\"";
}

abstract production jsonNull
top::JSONValue ::=
{
  top.jsonString = "null";
}
