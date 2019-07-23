nonterminal CompletionItemCapabilities with jsonValue, snippetSupport, commitCharactersSupport, supportedDocumentationFormats, deprecatedSupport, preselectSupport;

synthesized attribute snippetSupport :: Maybe<Boolean>;
synthesized attribute commitCharactersSupport :: Maybe<Boolean>;
synthesized attribute supportedDocumentationFormats :: Maybe<[MarkupKind]>;
synthesized attribute deprecatedSupport :: Maybe<Boolean>;
synthesized attribute preselectSupport :: Maybe<Boolean>;

abstract production completionItemCapabilities
top::CompletionItemCapabilities::=
  snippetSupport::Maybe<Boolean> commitCharacters::Maybe<Boolean> documentationFormat::Maybe<[MarkupKind]> deprecatedSupport::Maybe<Boolean> preselectSupport::Maybe<Boolean>
{
  top.snippetSupport = snippetSupport;
  top.commitCharactersSupport = commitCharacters;
  top.supportedDocumentationFormats = documentationFormat;
  top.deprecatedSupport = deprecatedSupport;
  top.preselectSupport = preselectSupport;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("snippetSupport", mapMaybe(jsonBoolean, snippetSupport), 
    maybeAddKeyValuePairToJSONObject("commitCharacters", mapMaybe(jsonBoolean, commitCharacters), 
    maybeAddKeyValuePairToJSONObject("documentationFormat", mapMaybe(jsonArray, mapMaybeList((.jsonValue), documentationFormat)), 
    maybeAddKeyValuePairToJSONObject("deprecatedSupport", mapMaybe(jsonBoolean, deprecatedSupport), 
    maybeAddKeyValuePairToJSONObject("preselectSupport", mapMaybe(jsonBoolean, preselectSupport), 
    jsonObject([]))))));
}

function parseCompletionItemCapabilities
Either<ResponseError CompletionItemCapabilities> ::= val::JSONValue
{
  local snippetSupport :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("snippetSupport", val));
  local commitCharacters :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("commitCharacters", val));
  local documentationFormat :: Maybe<[Either<ResponseError MarkupKind>]>
    = mapMaybe(mapJsonArray(parseMarkupKind, _), getValueWithKey("documentationFormat", val));
  local deprecatedSupport :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("deprecatedSupport", val));
  local preselectSupport :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("preselectSupport", val));

  local err :: Maybe<ResponseError> = 
    parseCompletionItemCapabilitiesError(documentationFormat);

  return
  if err.isJust
  then left(err.fromJust)
  else right(completionItemCapabilities(snippetSupport, commitCharacters, allFromRightMaybe(documentationFormat), deprecatedSupport, preselectSupport));
}

function parseCompletionItemCapabilitiesError
Maybe<ResponseError> ::= 
  documentationFormat::Maybe<[Either<ResponseError MarkupKind>]>
{
  return
  if documentationFormat.isJust && anyLeft(documentationFormat.fromJust) then firstLeft(documentationFormat.fromJust)
  else nothing();
}

function completionItemCapabilitiesToJson
JSONValue ::= val::CompletionItemCapabilities
{
  return val.jsonValue;
}
