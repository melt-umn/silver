nonterminal ParameterInformation with jsonValue, parameterLabel, parameterDocumentation;

synthesized attribute parameterLabel :: String;
synthesized attribute parameterDocumentation :: Maybe<String>;

abstract production parameterInformation
top::ParameterInformation::=
  label::String documentation::Maybe<String>
{
  top.parameterLabel = label;
  top.parameterDocumentation = documentation;
  top.jsonValue =
    addKeyValuePairToJSONObject("label", jsonString(label), 
    maybeAddKeyValuePairToJSONObject("documentation", mapMaybe(jsonString, documentation), 
    jsonObject([])));
}

function parseParameterInformation
Either<ResponseError ParameterInformation> ::= val::JSONValue
{
  local label :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("label", val));
  local documentation :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("documentation", val));

  local err :: Maybe<ResponseError> = 
    parseParameterInformationError(label);

  return
  if err.isJust
  then left(err.fromJust)
  else right(parameterInformation(label.fromJust, documentation));
}

function parseParameterInformationError
Maybe<ResponseError> ::= 
  label::Maybe<String>
{
  return
  if !label.isJust then just(lspInvalidParams("label not set on ParameterInformation"))
  else nothing();
}

function parameterInformationToJson
JSONValue ::= val::ParameterInformation
{
  return val.jsonValue;
}
