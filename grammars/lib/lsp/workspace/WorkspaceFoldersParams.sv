nonterminal WorkspaceFoldersParams with jsonValue;


abstract production workspaceFoldersParams
top::WorkspaceFoldersParams::=
  
{
  
  top.jsonValue =
    jsonObject([]);
}

function parseWorkspaceFoldersParams
Either<ResponseError WorkspaceFoldersParams> ::= val::JSONValue
{

  local err :: Maybe<ResponseError> = 
    parseWorkspaceFoldersParamsError();

  return
  if err.isJust
  then left(err.fromJust)
  else right(workspaceFoldersParams());
}

function parseWorkspaceFoldersParamsError
Maybe<ResponseError> ::= 
  
{
  return
  nothing();
}

function workspaceFoldersParamsToJson
JSONValue ::= val::WorkspaceFoldersParams
{
  return val.jsonValue;
}
