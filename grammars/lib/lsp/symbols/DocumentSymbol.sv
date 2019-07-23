nonterminal DocumentSymbol with jsonValue, symbolName, symbolDetails, typeOfSymbol, isDeprecated, range, selectionRange, symbolChildren;

synthesized attribute symbolName :: String;
synthesized attribute symbolDetails :: Maybe<String>;
synthesized attribute typeOfSymbol :: SymbolKind;
synthesized attribute selectionRange :: Range;
synthesized attribute symbolChildren :: Maybe<[DocumentSymbol]>;

abstract production documentSymbol
top::DocumentSymbol::=
  name::String detail::Maybe<String> kind::SymbolKind deprecated::Maybe<Boolean> range::Range selectionRange::Range children::Maybe<[DocumentSymbol]>
{
  top.symbolName = name;
  top.symbolDetails = detail;
  top.typeOfSymbol = kind;
  top.isDeprecated = deprecated;
  top.range = range;
  top.selectionRange = selectionRange;
  top.symbolChildren = children;
  top.jsonValue =
    addKeyValuePairToJSONObject("name", jsonString(name), 
    maybeAddKeyValuePairToJSONObject("detail", mapMaybe(jsonString, detail), 
    addKeyValuePairToJSONObject("kind", kind.jsonValue, 
    maybeAddKeyValuePairToJSONObject("deprecated", mapMaybe(jsonBoolean, deprecated), 
    addKeyValuePairToJSONObject("range", range.jsonValue, 
    addKeyValuePairToJSONObject("selectionRange", selectionRange.jsonValue, 
    maybeAddKeyValuePairToJSONObject("children", mapMaybe(jsonArray, mapMaybeList((.jsonValue), children)), 
    jsonObject([]))))))));
}

function parseDocumentSymbol
Either<ResponseError DocumentSymbol> ::= val::JSONValue
{
  local name :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("name", val));
  local detail :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("detail", val));
  local kind :: Maybe<Either<ResponseError SymbolKind>>
    = mapMaybe(parseSymbolKind, getValueWithKey("kind", val));
  local deprecated :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("deprecated", val));
  local range :: Maybe<Either<ResponseError Range>>
    = mapMaybe(parseRange, getValueWithKey("range", val));
  local selectionRange :: Maybe<Either<ResponseError Range>>
    = mapMaybe(parseRange, getValueWithKey("selectionRange", val));
  local children :: Maybe<[Either<ResponseError DocumentSymbol>]>
    = mapMaybe(mapJsonArray(parseDocumentSymbol, _), getValueWithKey("children", val));

  local err :: Maybe<ResponseError> = 
    parseDocumentSymbolError(name, kind, range, selectionRange, children);

  return
  if err.isJust
  then left(err.fromJust)
  else right(documentSymbol(name.fromJust, detail, kind.fromJust.fromRight, deprecated, range.fromJust.fromRight, selectionRange.fromJust.fromRight, allFromRightMaybe(children)));
}

function parseDocumentSymbolError
Maybe<ResponseError> ::= 
  name::Maybe<String> kind::Maybe<Either<ResponseError SymbolKind>> range::Maybe<Either<ResponseError Range>> selectionRange::Maybe<Either<ResponseError Range>> children::Maybe<[Either<ResponseError DocumentSymbol>]>
{
  return
  if !name.isJust then just(lspInvalidParams("name not set on DocumentSymbol"))
  else if !kind.isJust|| kind.fromJust.isLeft then just(lspInvalidParams("kind not set or invalid on DocumentSymbol"))
  else if !range.isJust|| range.fromJust.isLeft then just(lspInvalidParams("range not set or invalid on DocumentSymbol"))
  else if !selectionRange.isJust|| selectionRange.fromJust.isLeft then just(lspInvalidParams("selectionRange not set or invalid on DocumentSymbol"))
  else if children.isJust && anyLeft(children.fromJust) then firstLeft(children.fromJust)
  else nothing();
}

function documentSymbolToJson
JSONValue ::= val::DocumentSymbol
{
  return val.jsonValue;
}
