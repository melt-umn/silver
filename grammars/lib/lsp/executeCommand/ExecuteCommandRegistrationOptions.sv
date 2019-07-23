nonterminal ExecuteCommandRegistrationOptions with jsonValue, serverCommands;

synthesized attribute serverCommands :: [String];

abstract production executeCommandRegistrationOptions
top::ExecuteCommandRegistrationOptions::=
  commands::[String]
{
  top.serverCommands = commands;
  top.jsonValue =
    addKeyValuePairToJSONObject("commands", jsonArray(map(jsonString, commands)), 
    jsonObject([]));
}

function parseExecuteCommandRegistrationOptions
Either<ResponseError ExecuteCommandRegistrationOptions> ::= val::JSONValue
{
  local commands :: Maybe<[String]>
    = mapMaybe(mapJsonArray(jsonToString, _), getValueWithKey("commands", val));

  local err :: Maybe<ResponseError> = 
    parseExecuteCommandRegistrationOptionsError(commands);

  return
  if err.isJust
  then left(err.fromJust)
  else right(executeCommandRegistrationOptions(commands.fromJust));
}

function parseExecuteCommandRegistrationOptionsError
Maybe<ResponseError> ::= 
  commands::Maybe<[String]>
{
  return
  if !commands.isJust then just(lspInvalidParams("commands not set or invalid on ExecuteCommandRegistrationOptions"))
  else nothing();
}

function executeCommandRegistrationOptionsToJson
JSONValue ::= val::ExecuteCommandRegistrationOptions
{
  return val.jsonValue;
}
