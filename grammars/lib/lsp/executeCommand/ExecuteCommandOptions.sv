nonterminal ExecuteCommandOptions with jsonValue, serverSupportedCommands;

synthesized attribute serverSupportedCommands :: [String];

abstract production executeCommandOptions
top::ExecuteCommandOptions::=
  commands::[String]
{
  top.serverSupportedCommands = commands;
  top.jsonValue =
    addKeyValuePairToJSONObject("commands", jsonArray(map(jsonString, commands)), 
    jsonObject([]));
}

function parseExecuteCommandOptions
Either<ResponseError ExecuteCommandOptions> ::= val::JSONValue
{
  local commands :: Maybe<[String]>
    = mapMaybe(mapJsonArray(jsonToString, _), getValueWithKey("commands", val));

  local err :: Maybe<ResponseError> = 
    parseExecuteCommandOptionsError(commands);

  return
  if err.isJust
  then left(err.fromJust)
  else right(executeCommandOptions(commands.fromJust));
}

function parseExecuteCommandOptionsError
Maybe<ResponseError> ::= 
  commands::Maybe<[String]>
{
  return
  if !commands.isJust then just(lspInvalidParams("commands not set or invalid on ExecuteCommandOptions"))
  else nothing();
}

function executeCommandOptionsToJson
JSONValue ::= val::ExecuteCommandOptions
{
  return val.jsonValue;
}
