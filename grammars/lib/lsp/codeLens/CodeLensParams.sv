nonterminal CodeLensParams with jsonValue, textDocumentId;


abstract production codeLensParams
top::CodeLensParams::=
  textDocument::TextDocumentIdentifier
{
  top.textDocumentId = textDocument;
  top.jsonValue =
    addKeyValuePairToJSONObject("textDocument", textDocument.jsonValue, 
    jsonObject([]));
}

function parseCodeLensParams
Either<ResponseError CodeLensParams> ::= val::JSONValue
{
  local textDocument :: Maybe<Either<ResponseError TextDocumentIdentifier>>
    = mapMaybe(parseTextDocumentIdentifier, getValueWithKey("textDocument", val));

  local err :: Maybe<ResponseError> = 
    parseCodeLensParamsError(textDocument);

  return
  if err.isJust
  then left(err.fromJust)
  else right(codeLensParams(textDocument.fromJust.fromRight));
}

function parseCodeLensParamsError
Maybe<ResponseError> ::= 
  textDocument::Maybe<Either<ResponseError TextDocumentIdentifier>>
{
  return
  if !textDocument.isJust|| textDocument.fromJust.isLeft then just(lspInvalidParams("textDocument not set or invalid on CodeLensParams"))
  else nothing();
}

function codeLensParamsToJson
JSONValue ::= val::CodeLensParams
{
  return val.jsonValue;
}
