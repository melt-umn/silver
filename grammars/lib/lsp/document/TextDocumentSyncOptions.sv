nonterminal TextDocumentSyncOptions with jsonValue, sendOpenCloseNotifications, syncKind, sendWillSaveNotifications, sendWillSaveWaitUntilRequests, saveOptions;

synthesized attribute sendOpenCloseNotifications :: Maybe<Boolean>;
synthesized attribute syncKind :: Maybe<TextDocumentSyncKind>;
synthesized attribute sendWillSaveNotifications :: Maybe<Boolean>;
synthesized attribute sendWillSaveWaitUntilRequests :: Maybe<Boolean>;
synthesized attribute saveOptions :: Maybe<SaveOptions>;

abstract production textDocumentSyncOptions
top::TextDocumentSyncOptions::=
  openClose::Maybe<Boolean> change::Maybe<TextDocumentSyncKind> willSave::Maybe<Boolean> willSaveWaitUntil::Maybe<Boolean> save::Maybe<SaveOptions>
{
  top.sendOpenCloseNotifications = openClose;
  top.syncKind = change;
  top.sendWillSaveNotifications = willSave;
  top.sendWillSaveWaitUntilRequests = willSaveWaitUntil;
  top.saveOptions = save;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("openClose", mapMaybe(jsonBoolean, openClose), 
    maybeAddKeyValuePairToJSONObject("change", mapMaybe((.jsonValue), change), 
    maybeAddKeyValuePairToJSONObject("willSave", mapMaybe(jsonBoolean, willSave), 
    maybeAddKeyValuePairToJSONObject("willSaveWaitUntil", mapMaybe(jsonBoolean, willSaveWaitUntil), 
    maybeAddKeyValuePairToJSONObject("save", mapMaybe((.jsonValue), save), 
    jsonObject([]))))));
}

function parseTextDocumentSyncOptions
Either<ResponseError TextDocumentSyncOptions> ::= val::JSONValue
{
  local openClose :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("openClose", val));
  local change :: Maybe<Either<ResponseError TextDocumentSyncKind>>
    = mapMaybe(parseTextDocumentSyncKind, getValueWithKey("change", val));
  local willSave :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("willSave", val));
  local willSaveWaitUntil :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("willSaveWaitUntil", val));
  local save :: Maybe<Either<ResponseError SaveOptions>>
    = mapMaybe(parseSaveOptions, getValueWithKey("save", val));

  local err :: Maybe<ResponseError> = 
    parseTextDocumentSyncOptionsError(change, save);

  return
  if err.isJust
  then left(err.fromJust)
  else right(textDocumentSyncOptions(openClose, rightMaybe(change), willSave, willSaveWaitUntil, rightMaybe(save)));
}

function parseTextDocumentSyncOptionsError
Maybe<ResponseError> ::= 
  change::Maybe<Either<ResponseError TextDocumentSyncKind>> save::Maybe<Either<ResponseError SaveOptions>>
{
  return
  if change.isJust && change.fromJust.isLeft then just(change.fromJust.fromLeft)
  else if save.isJust && save.fromJust.isLeft then just(save.fromJust.fromLeft)
  else nothing();
}

function textDocumentSyncOptionsToJson
JSONValue ::= val::TextDocumentSyncOptions
{
  return val.jsonValue;
}
