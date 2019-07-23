nonterminal ColorPresentation with jsonValue, colorPresentationLabel, editToApply, additionalEditsToApply;

synthesized attribute colorPresentationLabel :: String;
synthesized attribute editToApply :: Maybe<TextEdit>;
synthesized attribute additionalEditsToApply :: Maybe<[TextEdit]>;

abstract production colorPresentation
top::ColorPresentation::=
  label::String textEdit::Maybe<TextEdit> additionalTextEdits::Maybe<[TextEdit]>
{
  top.colorPresentationLabel = label;
  top.editToApply = textEdit;
  top.additionalEditsToApply = additionalTextEdits;
  top.jsonValue =
    addKeyValuePairToJSONObject("label", jsonString(label), 
    maybeAddKeyValuePairToJSONObject("textEdit", mapMaybe((.jsonValue), textEdit), 
    maybeAddKeyValuePairToJSONObject("additionalTextEdits", mapMaybe(jsonArray, mapMaybeList((.jsonValue), additionalTextEdits)), 
    jsonObject([]))));
}

function parseColorPresentation
Either<ResponseError ColorPresentation> ::= val::JSONValue
{
  local label :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("label", val));
  local textEdit :: Maybe<Either<ResponseError TextEdit>>
    = mapMaybe(parseTextEdit, getValueWithKey("textEdit", val));
  local additionalTextEdits :: Maybe<[Either<ResponseError TextEdit>]>
    = mapMaybe(mapJsonArray(parseTextEdit, _), getValueWithKey("additionalTextEdits", val));

  local err :: Maybe<ResponseError> = 
    parseColorPresentationError(label, textEdit, additionalTextEdits);

  return
  if err.isJust
  then left(err.fromJust)
  else right(colorPresentation(label.fromJust, rightMaybe(textEdit), allFromRightMaybe(additionalTextEdits)));
}

function parseColorPresentationError
Maybe<ResponseError> ::= 
  label::Maybe<String> textEdit::Maybe<Either<ResponseError TextEdit>> additionalTextEdits::Maybe<[Either<ResponseError TextEdit>]>
{
  return
  if !label.isJust then just(lspInvalidParams("label not set on ColorPresentation"))
  else if textEdit.isJust && textEdit.fromJust.isLeft then just(textEdit.fromJust.fromLeft)
  else if additionalTextEdits.isJust && anyLeft(additionalTextEdits.fromJust) then firstLeft(additionalTextEdits.fromJust)
  else nothing();
}

function colorPresentationToJson
JSONValue ::= val::ColorPresentation
{
  return val.jsonValue;
}
