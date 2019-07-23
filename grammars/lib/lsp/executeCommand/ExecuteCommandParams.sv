nonterminal ExecuteCommandParams with jsonValue, commandName, commandArguments;

synthesized attribute commandName :: String;

abstract production executeCommandParams
top::ExecuteCommandParams::=
  command_::String arguments::Maybe<JSONValue>
{
  top.commandName = command_;
  top.commandArguments = arguments;
  top.jsonValue =
    addKeyValuePairToJSONObject("command", jsonString(command_), 
    maybeAddKeyValuePairToJSONObject("arguments", arguments, 
    jsonObject([])));
}

function parseExecuteCommandParams
Either<ResponseError ExecuteCommandParams> ::= val::JSONValue
{
  local command_ :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("command", val));
  local arguments :: Maybe<JSONValue>
    = mapMaybe(identity, getValueWithKey("arguments", val));

  local err :: Maybe<ResponseError> = 
    parseExecuteCommandParamsError(command_);

  return
  if err.isJust
  then left(err.fromJust)
  else right(executeCommandParams(command_.fromJust, arguments));
}

function parseExecuteCommandParamsError
Maybe<ResponseError> ::= 
  command_::Maybe<String>
{
  return
  if !command_.isJust then just(lspInvalidParams("command not set on ExecuteCommandParams"))
  else nothing();
}

function executeCommandParamsToJson
JSONValue ::= val::ExecuteCommandParams
{
  return val.jsonValue;
}
