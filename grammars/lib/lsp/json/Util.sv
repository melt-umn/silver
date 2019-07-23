function parseJson
Either<String JSONValue> ::= val::String
{
  local concreteRoot :: ParseResult<JSONText> = jsonParser(val, "");
  return
  case concreteRoot of
  | parseSucceeded(cstRoot, _) -> right(cstRoot.jsonValue)
  | parseFailed(errval, _) -> left(errval.parseErrors)
  end;
}

function jsonKeyValuePairToString
String ::= val::Pair<String JSONValue>
{
  return
  s""""${fst(val)}":${(snd(val).jsonString)}""";
}

function getValueWithKey
Maybe<JSONValue> ::= key::String obj::JSONValue
{
  return 
  case obj of
  | jsonObject(keyVals) -> lookupBy(stringEq, key, keyVals)
  | _ -> nothing()
  end;
}

function addKeyValuePairToJSONObject
JSONValue ::= key::String val::JSONValue obj::JSONValue
{
  return
  case obj of 
  | jsonObject(keyVals) -> jsonObject(pair(key, val) :: keyVals)
  | _ -> error("Can't add key value pair to JSON value that is not an object")
  end;
}

function maybeAddKeyValuePairToJSONObject
JSONValue ::= key::String val::Maybe<JSONValue> obj::JSONValue
{
  return 
  case val of
  | nothing() -> obj
  | just(valu) -> addKeyValuePairToJSONObject(key, valu, obj)
  end;
}

function jsonToInteger
Integer ::= val::JSONValue
{
  return 
  case val of
  | jsonInteger(val) -> val
  | _ -> error("Can't convert non-integer JSON value to integer")
  end;
}

function jsonToString
String ::= val::JSONValue
{
  return
  case val of
  | jsonString(val) -> val
  | _ -> error("Can't covert non-string JSON value to string")
  end;
}

function jsonToBoolean
Boolean ::= val::JSONValue
{
  return
  case val of
  | jsonBoolean(val) -> val
  | _ -> error("Can't covert non-boolean JSON value to boolean")
  end;
}

function jsonToFloat
Float ::= val::JSONValue
{
  return
  case val of
  | jsonNumber(val) -> val
  | _ -> error("Can't covert non-float JSON value to float")
  end;
}

function mapJsonArray
[a] ::= func::(a ::= JSONValue) arr::JSONValue
{
  return
  case arr of
  | jsonArray(vals) -> map(func, vals)
  | _ -> error("Can't map JSON value that is not an array")
  end;
}
