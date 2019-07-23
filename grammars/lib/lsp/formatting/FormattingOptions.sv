nonterminal FormattingOptions with jsonValue, numOfSpacesInTab, preferSpacesOverTabs;

synthesized attribute numOfSpacesInTab :: Integer;
synthesized attribute preferSpacesOverTabs :: Boolean;

abstract production formattingOptions
top::FormattingOptions::=
  tabSize::Integer insertSpaces::Boolean
{
  top.numOfSpacesInTab = tabSize;
  top.preferSpacesOverTabs = insertSpaces;
  top.jsonValue =
    addKeyValuePairToJSONObject("tabSize", jsonInteger(tabSize), 
    addKeyValuePairToJSONObject("insertSpaces", jsonBoolean(insertSpaces), 
    jsonObject([])));
}

function parseFormattingOptions
Either<ResponseError FormattingOptions> ::= val::JSONValue
{
  local tabSize :: Maybe<Integer>
    = mapMaybe(jsonToInteger, getValueWithKey("tabSize", val));
  local insertSpaces :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("insertSpaces", val));

  local err :: Maybe<ResponseError> = 
    parseFormattingOptionsError(tabSize, insertSpaces);

  return
  if err.isJust
  then left(err.fromJust)
  else right(formattingOptions(tabSize.fromJust, insertSpaces.fromJust));
}

function parseFormattingOptionsError
Maybe<ResponseError> ::= 
  tabSize::Maybe<Integer> insertSpaces::Maybe<Boolean>
{
  return
  if !tabSize.isJust then just(lspInvalidParams("tabSize not set on FormattingOptions"))
  else if !insertSpaces.isJust then just(lspInvalidParams("insertSpaces not set on FormattingOptions"))
  else nothing();
}

function formattingOptionsToJson
JSONValue ::= val::FormattingOptions
{
  return val.jsonValue;
}
