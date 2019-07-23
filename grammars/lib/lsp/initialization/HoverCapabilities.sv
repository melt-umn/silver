nonterminal HoverCapabilities with jsonValue, dynamicRegistration, supportedContentFormats;

synthesized attribute supportedContentFormats :: Maybe<[MarkupKind]>;

abstract production hoverCapabilities
top::HoverCapabilities::=
  dynamicRegistration::Maybe<Boolean> contentFormat::Maybe<[MarkupKind]>
{
  top.dynamicRegistration = dynamicRegistration;
  top.supportedContentFormats = contentFormat;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("dynamicRegistration", mapMaybe(jsonBoolean, dynamicRegistration), 
    maybeAddKeyValuePairToJSONObject("contentFormat", mapMaybe(jsonArray, mapMaybeList((.jsonValue), contentFormat)), 
    jsonObject([])));
}

function parseHoverCapabilities
Either<ResponseError HoverCapabilities> ::= val::JSONValue
{
  local dynamicRegistration :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("dynamicRegistration", val));
  local contentFormat :: Maybe<[Either<ResponseError MarkupKind>]>
    = mapMaybe(mapJsonArray(parseMarkupKind, _), getValueWithKey("contentFormat", val));

  local err :: Maybe<ResponseError> = 
    parseHoverCapabilitiesError(contentFormat);

  return
  if err.isJust
  then left(err.fromJust)
  else right(hoverCapabilities(dynamicRegistration, allFromRightMaybe(contentFormat)));
}

function parseHoverCapabilitiesError
Maybe<ResponseError> ::= 
  contentFormat::Maybe<[Either<ResponseError MarkupKind>]>
{
  return
  if contentFormat.isJust && anyLeft(contentFormat.fromJust) then firstLeft(contentFormat.fromJust)
  else nothing();
}

function hoverCapabilitiesToJson
JSONValue ::= val::HoverCapabilities
{
  return val.jsonValue;
}
