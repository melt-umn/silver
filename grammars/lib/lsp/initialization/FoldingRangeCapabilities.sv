nonterminal FoldingRangeCapabilities with jsonValue, dynamicRegistration, recommendedMaxFoldingRangesPerDocument, supportsOnlyFullLineFolding;

synthesized attribute recommendedMaxFoldingRangesPerDocument :: Maybe<Integer>;
synthesized attribute supportsOnlyFullLineFolding :: Maybe<Boolean>;

abstract production foldingRangeCapabilities
top::FoldingRangeCapabilities::=
  dynamicRegistration::Maybe<Boolean> rangeLimit::Maybe<Integer> lineFoldingOnly::Maybe<Boolean>
{
  top.dynamicRegistration = dynamicRegistration;
  top.recommendedMaxFoldingRangesPerDocument = rangeLimit;
  top.supportsOnlyFullLineFolding = lineFoldingOnly;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("dynamicRegistration", mapMaybe(jsonBoolean, dynamicRegistration), 
    maybeAddKeyValuePairToJSONObject("rangeLimit", mapMaybe(jsonInteger, rangeLimit), 
    maybeAddKeyValuePairToJSONObject("lineFoldingOnly", mapMaybe(jsonBoolean, lineFoldingOnly), 
    jsonObject([]))));
}

function parseFoldingRangeCapabilities
Either<ResponseError FoldingRangeCapabilities> ::= val::JSONValue
{
  local dynamicRegistration :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("dynamicRegistration", val));
  local rangeLimit :: Maybe<Integer>
    = mapMaybe(jsonToInteger, getValueWithKey("rangeLimit", val));
  local lineFoldingOnly :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("lineFoldingOnly", val));

  local err :: Maybe<ResponseError> = 
    parseFoldingRangeCapabilitiesError();

  return
  if err.isJust
  then left(err.fromJust)
  else right(foldingRangeCapabilities(dynamicRegistration, rangeLimit, lineFoldingOnly));
}

function parseFoldingRangeCapabilitiesError
Maybe<ResponseError> ::= 
  
{
  return
  nothing();
}

function foldingRangeCapabilitiesToJson
JSONValue ::= val::FoldingRangeCapabilities
{
  return val.jsonValue;
}
