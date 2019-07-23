nonterminal Command with jsonValue, commandTitle, commandString, commandArguments;

synthesized attribute commandTitle :: String;
synthesized attribute commandString :: String;
synthesized attribute commandArguments :: Maybe<JSONValue>;

abstract production command
top::Command::=
  title::String command_::String arguments::Maybe<JSONValue>
{
  top.commandTitle = title;
  top.commandString = command_;
  top.commandArguments = arguments;
  top.jsonValue =
    addKeyValuePairToJSONObject("title", jsonString(title), 
    addKeyValuePairToJSONObject("command", jsonString(command_), 
    maybeAddKeyValuePairToJSONObject("arguments", arguments, 
    jsonObject([]))));
}

function parseCommand
Either<ResponseError Command> ::= val::JSONValue
{
  local title :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("title", val));
  local command_ :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("command", val));
  local arguments :: Maybe<JSONValue>
    = mapMaybe(identity, getValueWithKey("arguments", val));

  local err :: Maybe<ResponseError> = 
    parseCommandError(title, command_);

  return
  if err.isJust
  then left(err.fromJust)
  else right(command(title.fromJust, command_.fromJust, arguments));
}

function parseCommandError
Maybe<ResponseError> ::= 
  title::Maybe<String> command_::Maybe<String>
{
  return
  if !title.isJust then just(lspInvalidParams("title not set on Command"))
  else if !command_.isJust then just(lspInvalidParams("command not set on Command"))
  else nothing();
}

function commandToJson
JSONValue ::= val::Command
{
  return val.jsonValue;
}
