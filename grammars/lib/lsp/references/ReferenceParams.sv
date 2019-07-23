nonterminal ReferenceParams with jsonValue, referenceContext, textDocumentId, position;

synthesized attribute referenceContext :: ReferenceContext;
synthesized attribute textDocumentId :: TextDocumentIdentifier;

abstract production referenceParams
top::ReferenceParams::=
  context::ReferenceContext textDocument::TextDocumentIdentifier position::Position
{
  top.referenceContext = context;
  top.textDocumentId = textDocument;
  top.position = position;
  top.jsonValue =
    addKeyValuePairToJSONObject("context", context.jsonValue, 
    addKeyValuePairToJSONObject("textDocument", textDocument.jsonValue, 
    addKeyValuePairToJSONObject("position", position.jsonValue, 
    jsonObject([]))));
}

function parseReferenceParams
Either<ResponseError ReferenceParams> ::= val::JSONValue
{
  local context :: Maybe<Either<ResponseError ReferenceContext>>
    = mapMaybe(parseReferenceContext, getValueWithKey("context", val));
  local textDocument :: Maybe<Either<ResponseError TextDocumentIdentifier>>
    = mapMaybe(parseTextDocumentIdentifier, getValueWithKey("textDocument", val));
  local position :: Maybe<Either<ResponseError Position>>
    = mapMaybe(parsePosition, getValueWithKey("position", val));

  local err :: Maybe<ResponseError> = 
    parseReferenceParamsError(context, textDocument, position);

  return
  if err.isJust
  then left(err.fromJust)
  else right(referenceParams(context.fromJust.fromRight, textDocument.fromJust.fromRight, position.fromJust.fromRight));
}

function parseReferenceParamsError
Maybe<ResponseError> ::= 
  context::Maybe<Either<ResponseError ReferenceContext>> textDocument::Maybe<Either<ResponseError TextDocumentIdentifier>> position::Maybe<Either<ResponseError Position>>
{
  return
  if !context.isJust|| context.fromJust.isLeft then just(lspInvalidParams("context not set or invalid on ReferenceParams"))
  else if !textDocument.isJust|| textDocument.fromJust.isLeft then just(lspInvalidParams("textDocument not set or invalid on ReferenceParams"))
  else if !position.isJust|| position.fromJust.isLeft then just(lspInvalidParams("position not set or invalid on ReferenceParams"))
  else nothing();
}

function referenceParamsToJson
JSONValue ::= val::ReferenceParams
{
  return val.jsonValue;
}
