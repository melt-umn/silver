nonterminal FoldingRangeParams with jsonValue, documentId;


abstract production foldingRangeParams
top::FoldingRangeParams::=
  textDocument::TextDocumentIdentifier
{
  top.documentId = textDocument;
  top.jsonValue =
    addKeyValuePairToJSONObject("textDocument", textDocument.jsonValue, 
    jsonObject([]));
}

function parseFoldingRangeParams
Either<ResponseError FoldingRangeParams> ::= val::JSONValue
{
  local textDocument :: Maybe<Either<ResponseError TextDocumentIdentifier>>
    = mapMaybe(parseTextDocumentIdentifier, getValueWithKey("textDocument", val));

  local err :: Maybe<ResponseError> = 
    parseFoldingRangeParamsError(textDocument);

  return
  if err.isJust
  then left(err.fromJust)
  else right(foldingRangeParams(textDocument.fromJust.fromRight));
}

function parseFoldingRangeParamsError
Maybe<ResponseError> ::= 
  textDocument::Maybe<Either<ResponseError TextDocumentIdentifier>>
{
  return
  if !textDocument.isJust|| textDocument.fromJust.isLeft then just(lspInvalidParams("textDocument not set or invalid on FoldingRangeParams"))
  else nothing();
}

function foldingRangeParamsToJson
JSONValue ::= val::FoldingRangeParams
{
  return val.jsonValue;
}
