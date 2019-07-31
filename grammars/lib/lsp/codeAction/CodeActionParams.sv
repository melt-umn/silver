nonterminal CodeActionParams with jsonValue, documentId, range, codeActionContext;

synthesized attribute codeActionContext :: CodeActionContext;

abstract production codeActionParams
top::CodeActionParams::=
  textDocument::TextDocumentIdentifier range::Range context::CodeActionContext
{
  top.documentId = textDocument;
  top.range = range;
  top.codeActionContext = context;
  top.jsonValue =
    addKeyValuePairToJSONObject("textDocument", textDocument.jsonValue, 
    addKeyValuePairToJSONObject("range", range.jsonValue, 
    addKeyValuePairToJSONObject("context", context.jsonValue, 
    jsonObject([]))));
}

function parseCodeActionParams
Either<ResponseError CodeActionParams> ::= val::JSONValue
{
  local textDocument :: Maybe<Either<ResponseError TextDocumentIdentifier>>
    = mapMaybe(parseTextDocumentIdentifier, getValueWithKey("textDocument", val));
  local range :: Maybe<Either<ResponseError Range>>
    = mapMaybe(parseRange, getValueWithKey("range", val));
  local context :: Maybe<Either<ResponseError CodeActionContext>>
    = mapMaybe(parseCodeActionContext, getValueWithKey("context", val));

  local err :: Maybe<ResponseError> = 
    parseCodeActionParamsError(textDocument, range, context);

  return
  if err.isJust
  then left(err.fromJust)
  else right(codeActionParams(textDocument.fromJust.fromRight, range.fromJust.fromRight, context.fromJust.fromRight));
}

function parseCodeActionParamsError
Maybe<ResponseError> ::= 
  textDocument::Maybe<Either<ResponseError TextDocumentIdentifier>> range::Maybe<Either<ResponseError Range>> context::Maybe<Either<ResponseError CodeActionContext>>
{
  return
  if !textDocument.isJust|| textDocument.fromJust.isLeft then just(lspInvalidParams("textDocument not set or invalid on CodeActionParams"))
  else if !range.isJust|| range.fromJust.isLeft then just(lspInvalidParams("range not set or invalid on CodeActionParams"))
  else if !context.isJust|| context.fromJust.isLeft then just(lspInvalidParams("context not set or invalid on CodeActionParams"))
  else nothing();
}

function codeActionParamsToJson
JSONValue ::= val::CodeActionParams
{
  return val.jsonValue;
}
