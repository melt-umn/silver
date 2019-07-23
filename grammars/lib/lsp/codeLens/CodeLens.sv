nonterminal CodeLens with jsonValue, range, commandRepresented, dataForCodeLensResolve;

synthesized attribute commandRepresented :: Maybe<Command>;
synthesized attribute dataForCodeLensResolve :: Maybe<JSONValue>;

abstract production codeLens
top::CodeLens::=
  range::Range command_::Maybe<Command> data::Maybe<JSONValue>
{
  top.range = range;
  top.commandRepresented = command_;
  top.dataForCodeLensResolve = data;
  top.jsonValue =
    addKeyValuePairToJSONObject("range", range.jsonValue, 
    maybeAddKeyValuePairToJSONObject("command", mapMaybe((.jsonValue), command_), 
    maybeAddKeyValuePairToJSONObject("data", data, 
    jsonObject([]))));
}

function parseCodeLens
Either<ResponseError CodeLens> ::= val::JSONValue
{
  local range :: Maybe<Either<ResponseError Range>>
    = mapMaybe(parseRange, getValueWithKey("range", val));
  local command_ :: Maybe<Either<ResponseError Command>>
    = mapMaybe(parseCommand, getValueWithKey("command", val));
  local data :: Maybe<JSONValue>
    = mapMaybe(identity, getValueWithKey("data", val));

  local err :: Maybe<ResponseError> = 
    parseCodeLensError(range, command_);

  return
  if err.isJust
  then left(err.fromJust)
  else right(codeLens(range.fromJust.fromRight, rightMaybe(command_), data));
}

function parseCodeLensError
Maybe<ResponseError> ::= 
  range::Maybe<Either<ResponseError Range>> command_::Maybe<Either<ResponseError Command>>
{
  return
  if !range.isJust|| range.fromJust.isLeft then just(lspInvalidParams("range not set or invalid on CodeLens"))
  else if command_.isJust && command_.fromJust.isLeft then just(command_.fromJust.fromLeft)
  else nothing();
}

function codeLensToJson
JSONValue ::= val::CodeLens
{
  return val.jsonValue;
}
