nonterminal SymbolInformation with jsonValue, symbolName, typeOfSymbol, isDeprecated, symbolLocation, containingSymbol;

synthesized attribute isDeprecated :: Maybe<Boolean>;
synthesized attribute symbolLocation :: Location;
synthesized attribute containingSymbol :: Maybe<String>;

abstract production symbolInformation
top::SymbolInformation::=
  name::String kind::SymbolKind deprecated::Maybe<Boolean> location::Location containerName::Maybe<String>
{
  top.symbolName = name;
  top.typeOfSymbol = kind;
  top.isDeprecated = deprecated;
  top.symbolLocation = location;
  top.containingSymbol = containerName;
  top.jsonValue =
    addKeyValuePairToJSONObject("name", jsonString(name), 
    addKeyValuePairToJSONObject("kind", kind.jsonValue, 
    maybeAddKeyValuePairToJSONObject("deprecated", mapMaybe(jsonBoolean, deprecated), 
    addKeyValuePairToJSONObject("location", location.jsonValue, 
    maybeAddKeyValuePairToJSONObject("containerName", mapMaybe(jsonString, containerName), 
    jsonObject([]))))));
}

function parseSymbolInformation
Either<ResponseError SymbolInformation> ::= val::JSONValue
{
  local name :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("name", val));
  local kind :: Maybe<Either<ResponseError SymbolKind>>
    = mapMaybe(parseSymbolKind, getValueWithKey("kind", val));
  local deprecated :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("deprecated", val));
  local location :: Maybe<Either<ResponseError Location>>
    = mapMaybe(parseLocation, getValueWithKey("location", val));
  local containerName :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("containerName", val));

  local err :: Maybe<ResponseError> = 
    parseSymbolInformationError(name, kind, location);

  return
  if err.isJust
  then left(err.fromJust)
  else right(symbolInformation(name.fromJust, kind.fromJust.fromRight, deprecated, location.fromJust.fromRight, containerName));
}

function parseSymbolInformationError
Maybe<ResponseError> ::= 
  name::Maybe<String> kind::Maybe<Either<ResponseError SymbolKind>> location::Maybe<Either<ResponseError Location>>
{
  return
  if !name.isJust then just(lspInvalidParams("name not set on SymbolInformation"))
  else if !kind.isJust|| kind.fromJust.isLeft then just(lspInvalidParams("kind not set or invalid on SymbolInformation"))
  else if !location.isJust|| location.fromJust.isLeft then just(lspInvalidParams("location not set or invalid on SymbolInformation"))
  else nothing();
}

function symbolInformationToJson
JSONValue ::= val::SymbolInformation
{
  return val.jsonValue;
}
