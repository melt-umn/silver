nonterminal DocumentRangeFormattingParams with jsonValue, documentId, range, formattingOptions;


abstract production documentRangeFormattingParams
top::DocumentRangeFormattingParams::=
  textDocument::TextDocumentIdentifier range::Range options::FormattingOptions
{
  top.documentId = textDocument;
  top.range = range;
  top.formattingOptions = options;
  top.jsonValue =
    addKeyValuePairToJSONObject("textDocument", textDocument.jsonValue, 
    addKeyValuePairToJSONObject("range", range.jsonValue, 
    addKeyValuePairToJSONObject("options", options.jsonValue, 
    jsonObject([]))));
}

function parseDocumentRangeFormattingParams
Either<ResponseError DocumentRangeFormattingParams> ::= val::JSONValue
{
  local textDocument :: Maybe<Either<ResponseError TextDocumentIdentifier>>
    = mapMaybe(parseTextDocumentIdentifier, getValueWithKey("textDocument", val));
  local range :: Maybe<Either<ResponseError Range>>
    = mapMaybe(parseRange, getValueWithKey("range", val));
  local options :: Maybe<Either<ResponseError FormattingOptions>>
    = mapMaybe(parseFormattingOptions, getValueWithKey("options", val));

  local err :: Maybe<ResponseError> = 
    parseDocumentRangeFormattingParamsError(textDocument, range, options);

  return
  if err.isJust
  then left(err.fromJust)
  else right(documentRangeFormattingParams(textDocument.fromJust.fromRight, range.fromJust.fromRight, options.fromJust.fromRight));
}

function parseDocumentRangeFormattingParamsError
Maybe<ResponseError> ::= 
  textDocument::Maybe<Either<ResponseError TextDocumentIdentifier>> range::Maybe<Either<ResponseError Range>> options::Maybe<Either<ResponseError FormattingOptions>>
{
  return
  if !textDocument.isJust|| textDocument.fromJust.isLeft then just(lspInvalidParams("textDocument not set or invalid on DocumentRangeFormattingParams"))
  else if !range.isJust|| range.fromJust.isLeft then just(lspInvalidParams("range not set or invalid on DocumentRangeFormattingParams"))
  else if !options.isJust|| options.fromJust.isLeft then just(lspInvalidParams("options not set or invalid on DocumentRangeFormattingParams"))
  else nothing();
}

function documentRangeFormattingParamsToJson
JSONValue ::= val::DocumentRangeFormattingParams
{
  return val.jsonValue;
}
