nonterminal CompletionItem with jsonValue, completionLabel, completionKind, completionDetail, completionDocumentation, itemDeprecated, preselectedItem, textToSortBy, textToFilterBy, textToInsert, insertionFormat, editToApply, extraEditsToApply, completionCommitCharacters, commandToExecuteAfterCompletion, dataForResolve;

synthesized attribute completionLabel :: String;
synthesized attribute completionKind :: Maybe<CompletionItemKind>;
synthesized attribute completionDetail :: Maybe<String>;
synthesized attribute completionDocumentation :: Maybe<String>;
synthesized attribute itemDeprecated :: Maybe<Boolean>;
synthesized attribute preselectedItem :: Maybe<Boolean>;
synthesized attribute textToSortBy :: Maybe<String>;
synthesized attribute textToFilterBy :: Maybe<String>;
synthesized attribute textToInsert :: Maybe<String>;
synthesized attribute insertionFormat :: Maybe<InsertTextFormat>;
synthesized attribute editToApply :: Maybe<TextEdit>;
synthesized attribute extraEditsToApply :: Maybe<[TextEdit]>;
synthesized attribute completionCommitCharacters :: Maybe<[String]>;
synthesized attribute commandToExecuteAfterCompletion :: Maybe<Command>;
synthesized attribute dataForResolve :: Maybe<JSONValue>;

abstract production completionItem
top::CompletionItem::=
  label::String kind::Maybe<CompletionItemKind> detail::Maybe<String> documentation::Maybe<String> deprecated::Maybe<Boolean> preselect::Maybe<Boolean> sortText::Maybe<String> filterText::Maybe<String> insertText::Maybe<String> insertTextFormat::Maybe<InsertTextFormat> textEdit::Maybe<TextEdit> additionalTextEdits::Maybe<[TextEdit]> commitCharacters::Maybe<[String]> command_::Maybe<Command> data::Maybe<JSONValue>
{
  top.completionLabel = label;
  top.completionKind = kind;
  top.completionDetail = detail;
  top.completionDocumentation = documentation;
  top.itemDeprecated = deprecated;
  top.preselectedItem = preselect;
  top.textToSortBy = sortText;
  top.textToFilterBy = filterText;
  top.textToInsert = insertText;
  top.insertionFormat = insertTextFormat;
  top.editToApply = textEdit;
  top.extraEditsToApply = additionalTextEdits;
  top.completionCommitCharacters = commitCharacters;
  top.commandToExecuteAfterCompletion = command_;
  top.dataForResolve = data;
  top.jsonValue =
    addKeyValuePairToJSONObject("label", jsonString(label), 
    maybeAddKeyValuePairToJSONObject("kind", mapMaybe((.jsonValue), kind), 
    maybeAddKeyValuePairToJSONObject("detail", mapMaybe(jsonString, detail), 
    maybeAddKeyValuePairToJSONObject("documentation", mapMaybe(jsonString, documentation), 
    maybeAddKeyValuePairToJSONObject("deprecated", mapMaybe(jsonBoolean, deprecated), 
    maybeAddKeyValuePairToJSONObject("preselect", mapMaybe(jsonBoolean, preselect), 
    maybeAddKeyValuePairToJSONObject("sortText", mapMaybe(jsonString, sortText), 
    maybeAddKeyValuePairToJSONObject("filterText", mapMaybe(jsonString, filterText), 
    maybeAddKeyValuePairToJSONObject("insertText", mapMaybe(jsonString, insertText), 
    maybeAddKeyValuePairToJSONObject("insertTextFormat", mapMaybe((.jsonValue), insertTextFormat), 
    maybeAddKeyValuePairToJSONObject("textEdit", mapMaybe((.jsonValue), textEdit), 
    maybeAddKeyValuePairToJSONObject("additionalTextEdits", mapMaybe(jsonArray, mapMaybeList((.jsonValue), additionalTextEdits)), 
    maybeAddKeyValuePairToJSONObject("commitCharacters", mapMaybe(jsonArray, mapMaybeList(jsonString, commitCharacters)), 
    maybeAddKeyValuePairToJSONObject("command", mapMaybe((.jsonValue), command_), 
    maybeAddKeyValuePairToJSONObject("data", data, 
    jsonObject([]))))))))))))))));
}

function parseCompletionItem
Either<ResponseError CompletionItem> ::= val::JSONValue
{
  local label :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("label", val));
  local kind :: Maybe<Either<ResponseError CompletionItemKind>>
    = mapMaybe(parseCompletionItemKind, getValueWithKey("kind", val));
  local detail :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("detail", val));
  local documentation :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("documentation", val));
  local deprecated :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("deprecated", val));
  local preselect :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("preselect", val));
  local sortText :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("sortText", val));
  local filterText :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("filterText", val));
  local insertText :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("insertText", val));
  local insertTextFormat :: Maybe<Either<ResponseError InsertTextFormat>>
    = mapMaybe(parseInsertTextFormat, getValueWithKey("insertTextFormat", val));
  local textEdit :: Maybe<Either<ResponseError TextEdit>>
    = mapMaybe(parseTextEdit, getValueWithKey("textEdit", val));
  local additionalTextEdits :: Maybe<[Either<ResponseError TextEdit>]>
    = mapMaybe(mapJsonArray(parseTextEdit, _), getValueWithKey("additionalTextEdits", val));
  local commitCharacters :: Maybe<[String]>
    = mapMaybe(mapJsonArray(jsonToString, _), getValueWithKey("commitCharacters", val));
  local command_ :: Maybe<Either<ResponseError Command>>
    = mapMaybe(parseCommand, getValueWithKey("command", val));
  local data :: Maybe<JSONValue>
    = mapMaybe(identity, getValueWithKey("data", val));

  local err :: Maybe<ResponseError> = 
    parseCompletionItemError(label, kind, insertTextFormat, textEdit, additionalTextEdits, command_);

  return
  if err.isJust
  then left(err.fromJust)
  else right(completionItem(label.fromJust, rightMaybe(kind), detail, documentation, deprecated, preselect, sortText, filterText, insertText, rightMaybe(insertTextFormat), rightMaybe(textEdit), allFromRightMaybe(additionalTextEdits), commitCharacters, rightMaybe(command_), data));
}

function parseCompletionItemError
Maybe<ResponseError> ::= 
  label::Maybe<String> kind::Maybe<Either<ResponseError CompletionItemKind>> insertTextFormat::Maybe<Either<ResponseError InsertTextFormat>> textEdit::Maybe<Either<ResponseError TextEdit>> additionalTextEdits::Maybe<[Either<ResponseError TextEdit>]> command_::Maybe<Either<ResponseError Command>>
{
  return
  if !label.isJust then just(lspInvalidParams("label not set on CompletionItem"))
  else if kind.isJust && kind.fromJust.isLeft then just(kind.fromJust.fromLeft)
  else if insertTextFormat.isJust && insertTextFormat.fromJust.isLeft then just(insertTextFormat.fromJust.fromLeft)
  else if textEdit.isJust && textEdit.fromJust.isLeft then just(textEdit.fromJust.fromLeft)
  else if additionalTextEdits.isJust && anyLeft(additionalTextEdits.fromJust) then firstLeft(additionalTextEdits.fromJust)
  else if command_.isJust && command_.fromJust.isLeft then just(command_.fromJust.fromLeft)
  else nothing();
}

function completionItemToJson
JSONValue ::= val::CompletionItem
{
  return val.jsonValue;
}
