nonterminal DocumentOnTypeFormattingParams with jsonValue, textDocumentId, position, typedCharacter, formattingOptions;

synthesized attribute typedCharacter :: String;

abstract production documentOnTypeFormattingParams
top::DocumentOnTypeFormattingParams::=
  textDocument::TextDocumentIdentifier position::Position ch::String options::FormattingOptions
{
  top.textDocumentId = textDocument;
  top.position = position;
  top.typedCharacter = ch;
  top.formattingOptions = options;
  top.jsonValue =
    addKeyValuePairToJSONObject("textDocument", textDocument.jsonValue, 
    addKeyValuePairToJSONObject("position", position.jsonValue, 
    addKeyValuePairToJSONObject("ch", jsonString(ch), 
    addKeyValuePairToJSONObject("options", options.jsonValue, 
    jsonObject([])))));
}

function parseDocumentOnTypeFormattingParams
Either<ResponseError DocumentOnTypeFormattingParams> ::= val::JSONValue
{
  local textDocument :: Maybe<Either<ResponseError TextDocumentIdentifier>>
    = mapMaybe(parseTextDocumentIdentifier, getValueWithKey("textDocument", val));
  local position :: Maybe<Either<ResponseError Position>>
    = mapMaybe(parsePosition, getValueWithKey("position", val));
  local ch :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("ch", val));
  local options :: Maybe<Either<ResponseError FormattingOptions>>
    = mapMaybe(parseFormattingOptions, getValueWithKey("options", val));

  local err :: Maybe<ResponseError> = 
    parseDocumentOnTypeFormattingParamsError(textDocument, position, ch, options);

  return
  if err.isJust
  then left(err.fromJust)
  else right(documentOnTypeFormattingParams(textDocument.fromJust.fromRight, position.fromJust.fromRight, ch.fromJust, options.fromJust.fromRight));
}

function parseDocumentOnTypeFormattingParamsError
Maybe<ResponseError> ::= 
  textDocument::Maybe<Either<ResponseError TextDocumentIdentifier>> position::Maybe<Either<ResponseError Position>> ch::Maybe<String> options::Maybe<Either<ResponseError FormattingOptions>>
{
  return
  if !textDocument.isJust|| textDocument.fromJust.isLeft then just(lspInvalidParams("textDocument not set or invalid on DocumentOnTypeFormattingParams"))
  else if !position.isJust|| position.fromJust.isLeft then just(lspInvalidParams("position not set or invalid on DocumentOnTypeFormattingParams"))
  else if !ch.isJust then just(lspInvalidParams("ch not set on DocumentOnTypeFormattingParams"))
  else if !options.isJust|| options.fromJust.isLeft then just(lspInvalidParams("options not set or invalid on DocumentOnTypeFormattingParams"))
  else nothing();
}

function documentOnTypeFormattingParamsToJson
JSONValue ::= val::DocumentOnTypeFormattingParams
{
  return val.jsonValue;
}
