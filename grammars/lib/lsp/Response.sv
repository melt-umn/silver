grammar lib:lsp;

nonterminal ResponseMessage with jsonValue;
nonterminal ResponseError with jsonValue, responseErrorCode, responseErrorMessage;

synthesized attribute responseErrorCode :: Integer;
synthesized attribute responseErrorMessage :: String;

abstract production errorResponseRaw
top::ResponseMessage ::= id::JSONValue err::JSONValue
{
  top.jsonValue = addKeyValuePairToJSONObject("id", id, 
                  addKeyValuePairToJSONObject("error", err,
                  jsonObject([])));
}

abstract production errorResponse
top::ResponseMessage ::= id::JSONValue err::ResponseError
{
  top.jsonValue = addKeyValuePairToJSONObject("id", id, 
                  addKeyValuePairToJSONObject("error", err.jsonValue,
                  jsonObject([])));
}

abstract production resultResponse
top::ResponseMessage ::= id::JSONValue result::JSONValue
{
  top.jsonValue = addKeyValuePairToJSONObject("id", id, 
                  addKeyValuePairToJSONObject("result", result,
                  jsonObject([])));
}

abstract production lspParseError
top::ResponseError ::= msg::String
{
  top.responseErrorCode = -32700;
  top.responseErrorMessage = msg;
  top.jsonValue = constructResponseErrorJSONValue(top.responseErrorCode, msg);
}

abstract production lspInvalidRequest
top::ResponseError ::= msg::String
{
  top.responseErrorCode = -32600;
  top.responseErrorMessage = msg;
  top.jsonValue = constructResponseErrorJSONValue(top.responseErrorCode, msg);
}

abstract production lspMethodNotFound
top::ResponseError ::= msg::String
{
  top.responseErrorCode = -32601;
  top.responseErrorMessage = msg;
  top.jsonValue = constructResponseErrorJSONValue(top.responseErrorCode, msg);
}

abstract production lspInvalidParams
top::ResponseError ::= msg::String
{
  top.responseErrorCode = -32602;
  top.responseErrorMessage = msg;
  top.jsonValue = constructResponseErrorJSONValue(top.responseErrorCode, msg);
}

abstract production lspInternalError
top::ResponseError ::= msg::String
{
  top.responseErrorCode = -32603;
  top.responseErrorMessage = msg;
  top.jsonValue = constructResponseErrorJSONValue(top.responseErrorCode, msg);
}

abstract production lspServerErrorStart
top::ResponseError ::= msg::String
{
  top.responseErrorCode = -32099;
  top.responseErrorMessage = msg;
  top.jsonValue = constructResponseErrorJSONValue(top.responseErrorCode, msg);
}

abstract production lspServerErrorEnd
top::ResponseError ::= msg::String
{
  top.responseErrorCode = -32000;
  top.responseErrorMessage = msg;
  top.jsonValue = constructResponseErrorJSONValue(top.responseErrorCode, msg);
}

abstract production lspServerNotInitialized
top::ResponseError ::= msg::String
{
  top.responseErrorCode = -32002;
  top.responseErrorMessage = msg;
  top.jsonValue = constructResponseErrorJSONValue(top.responseErrorCode, msg);
}

abstract production lspUnknownError
top::ResponseError ::= msg::String
{
  top.responseErrorCode = -32001;
  top.responseErrorMessage = msg;
  top.jsonValue = constructResponseErrorJSONValue(top.responseErrorCode, msg);
}

abstract production lspRequestCancelled
top::ResponseError ::= msg::String
{
  top.responseErrorCode = -32800;
  top.responseErrorMessage = msg;
  top.jsonValue = constructResponseErrorJSONValue(top.responseErrorCode, msg);
}

abstract production lspContentModified
top::ResponseError ::= msg::String
{
  top.responseErrorCode = -32801;
  top.responseErrorMessage = msg;
  top.jsonValue = constructResponseErrorJSONValue(top.responseErrorCode, msg);
}

function constructResponseErrorJSONValue
JSONValue ::= code::Integer msg::String
{
  return 
  addKeyValuePairToJSONObject("code", jsonInteger(code), 
  addKeyValuePairToJSONObject("message", jsonString(msg),
  jsonObject([])));
}
