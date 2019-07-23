nonterminal CompletionParams with jsonValue, textDocumentId, position, completionContext;

synthesized attribute textDocumentId :: TextDocumentIdentifier;
synthesized attribute completionContext :: Maybe<CompletionContext>;

abstract production completionParams
top::CompletionParams::=
  textDocument::TextDocumentIdentifier position::Position context::Maybe<CompletionContext>
{
  top.textDocumentId = textDocument;
  top.position = position;
  top.completionContext = context;
  top.jsonValue =
    addKeyValuePairToJSONObject("textDocument", textDocument.jsonValue, 
    addKeyValuePairToJSONObject("position", position.jsonValue, 
    maybeAddKeyValuePairToJSONObject("context", mapMaybe((.jsonValue), context), 
    jsonObject([]))));
}

function parseCompletionParams
Either<ResponseError CompletionParams> ::= val::JSONValue
{
  local textDocument :: Maybe<Either<ResponseError TextDocumentIdentifier>>
    = mapMaybe(parseTextDocumentIdentifier, getValueWithKey("textDocument", val));
  local position :: Maybe<Either<ResponseError Position>>
    = mapMaybe(parsePosition, getValueWithKey("position", val));
  local context :: Maybe<Either<ResponseError CompletionContext>>
    = mapMaybe(parseCompletionContext, getValueWithKey("context", val));

  local err :: Maybe<ResponseError> = 
    parseCompletionParamsError(textDocument, position, context);

  return
  if err.isJust
  then left(err.fromJust)
  else right(completionParams(textDocument.fromJust.fromRight, position.fromJust.fromRight, rightMaybe(context)));
}

function parseCompletionParamsError
Maybe<ResponseError> ::= 
  textDocument::Maybe<Either<ResponseError TextDocumentIdentifier>> position::Maybe<Either<ResponseError Position>> context::Maybe<Either<ResponseError CompletionContext>>
{
  return
  if !textDocument.isJust|| textDocument.fromJust.isLeft then just(lspInvalidParams("textDocument not set or invalid on CompletionParams"))
  else if !position.isJust|| position.fromJust.isLeft then just(lspInvalidParams("position not set or invalid on CompletionParams"))
  else if context.isJust && context.fromJust.isLeft then just(context.fromJust.fromLeft)
  else nothing();
}

function completionParamsToJson
JSONValue ::= val::CompletionParams
{
  return val.jsonValue;
}
