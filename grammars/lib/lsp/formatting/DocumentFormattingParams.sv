nonterminal DocumentFormattingParams with jsonValue, documentId, formattingOptions;


abstract production documentFormattingParams
top::DocumentFormattingParams::=
  textDocument::TextDocumentIdentifier options::FormattingOptions
{
  top.documentId = textDocument;
  top.formattingOptions = options;
  top.jsonValue =
    addKeyValuePairToJSONObject("textDocument", textDocument.jsonValue, 
    addKeyValuePairToJSONObject("options", options.jsonValue, 
    jsonObject([])));
}

function parseDocumentFormattingParams
Either<ResponseError DocumentFormattingParams> ::= val::JSONValue
{
  local textDocument :: Maybe<Either<ResponseError TextDocumentIdentifier>>
    = mapMaybe(parseTextDocumentIdentifier, getValueWithKey("textDocument", val));
  local options :: Maybe<Either<ResponseError FormattingOptions>>
    = mapMaybe(parseFormattingOptions, getValueWithKey("options", val));

  local err :: Maybe<ResponseError> = 
    parseDocumentFormattingParamsError(textDocument, options);

  return
  if err.isJust
  then left(err.fromJust)
  else right(documentFormattingParams(textDocument.fromJust.fromRight, options.fromJust.fromRight));
}

function parseDocumentFormattingParamsError
Maybe<ResponseError> ::= 
  textDocument::Maybe<Either<ResponseError TextDocumentIdentifier>> options::Maybe<Either<ResponseError FormattingOptions>>
{
  return
  if !textDocument.isJust|| textDocument.fromJust.isLeft then just(lspInvalidParams("textDocument not set or invalid on DocumentFormattingParams"))
  else if !options.isJust|| options.fromJust.isLeft then just(lspInvalidParams("options not set or invalid on DocumentFormattingParams"))
  else nothing();
}

function documentFormattingParamsToJson
JSONValue ::= val::DocumentFormattingParams
{
  return val.jsonValue;
}
