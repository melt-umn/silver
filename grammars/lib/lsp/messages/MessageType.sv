nonterminal MessageType with jsonValue, messageType;

synthesized attribute messageType :: Integer;

abstract production messageTypeError
top::MessageType ::=
{
  top.messageType = 1;
  top.jsonValue = jsonInteger(1);
}

abstract production messageTypeWarning
top::MessageType ::=
{
  top.messageType = 2;
  top.jsonValue = jsonInteger(2);
}

abstract production messageTypeInfo
top::MessageType ::=
{
  top.messageType = 3;
  top.jsonValue = jsonInteger(3);
}

abstract production messageTypeLog
top::MessageType ::=
{
  top.messageType = 4;
  top.jsonValue = jsonInteger(4);
}

function parseMessageType
Either<ResponseError MessageType> ::= val::JSONValue
{
  return case val of
  | jsonInteger(intVal) -> makeMessageType(intVal)
  | _ -> left(lspInvalidParams("MessageType not of type Integer"))
  end;
}

function makeMessageType
Either<ResponseError MessageType> ::= val::Integer
{
  return
  if val == 1 then right(messageTypeError())
  else if val == 2 then right(messageTypeWarning())
  else if val == 3 then right(messageTypeInfo())
  else if val == 4 then right(messageTypeLog())
  else left(lspInvalidParams("Invalid MessageType value"));
}
