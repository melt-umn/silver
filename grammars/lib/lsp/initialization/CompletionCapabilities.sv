nonterminal CompletionCapabilities with jsonValue, dynamicRegistration, completionItemCapabilities, completionItemKindCapabilities, supportsCompletionContext;

synthesized attribute completionItemCapabilities :: Maybe<CompletionItemCapabilities>;
synthesized attribute completionItemKindCapabilities :: Maybe<CompletionItemKindCapabilities>;
synthesized attribute supportsCompletionContext :: Maybe<Boolean>;

abstract production completionCapabilities
top::CompletionCapabilities::=
  dynamicRegistration::Maybe<Boolean> completionItem::Maybe<CompletionItemCapabilities> completionItemKind::Maybe<CompletionItemKindCapabilities> contextSupport::Maybe<Boolean>
{
  top.dynamicRegistration = dynamicRegistration;
  top.completionItemCapabilities = completionItem;
  top.completionItemKindCapabilities = completionItemKind;
  top.supportsCompletionContext = contextSupport;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("dynamicRegistration", mapMaybe(jsonBoolean, dynamicRegistration), 
    maybeAddKeyValuePairToJSONObject("completionItem", mapMaybe((.jsonValue), completionItem), 
    maybeAddKeyValuePairToJSONObject("completionItemKind", mapMaybe((.jsonValue), completionItemKind), 
    maybeAddKeyValuePairToJSONObject("contextSupport", mapMaybe(jsonBoolean, contextSupport), 
    jsonObject([])))));
}

function parseCompletionCapabilities
Either<ResponseError CompletionCapabilities> ::= val::JSONValue
{
  local dynamicRegistration :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("dynamicRegistration", val));
  local completionItem :: Maybe<Either<ResponseError CompletionItemCapabilities>>
    = mapMaybe(parseCompletionItemCapabilities, getValueWithKey("completionItem", val));
  local completionItemKind :: Maybe<Either<ResponseError CompletionItemKindCapabilities>>
    = mapMaybe(parseCompletionItemKindCapabilities, getValueWithKey("completionItemKind", val));
  local contextSupport :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("contextSupport", val));

  local err :: Maybe<ResponseError> = 
    parseCompletionCapabilitiesError(completionItem, completionItemKind);

  return
  if err.isJust
  then left(err.fromJust)
  else right(completionCapabilities(dynamicRegistration, rightMaybe(completionItem), rightMaybe(completionItemKind), contextSupport));
}

function parseCompletionCapabilitiesError
Maybe<ResponseError> ::= 
  completionItem::Maybe<Either<ResponseError CompletionItemCapabilities>> completionItemKind::Maybe<Either<ResponseError CompletionItemKindCapabilities>>
{
  return
  if completionItem.isJust && completionItem.fromJust.isLeft then just(completionItem.fromJust.fromLeft)
  else if completionItemKind.isJust && completionItemKind.fromJust.isLeft then just(completionItemKind.fromJust.fromLeft)
  else nothing();
}

function completionCapabilitiesToJson
JSONValue ::= val::CompletionCapabilities
{
  return val.jsonValue;
}
