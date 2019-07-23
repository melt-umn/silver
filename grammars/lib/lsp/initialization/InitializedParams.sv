nonterminal InitializedParams with jsonValue;


abstract production initializedParams
top::InitializedParams::=
  
{
  
  top.jsonValue =
    jsonObject([]);
}

function parseInitializedParams
Either<ResponseError InitializedParams> ::= val::JSONValue
{

  local err :: Maybe<ResponseError> = 
    parseInitializedParamsError();

  return
  if err.isJust
  then left(err.fromJust)
  else right(initializedParams());
}

function parseInitializedParamsError
Maybe<ResponseError> ::= 
  
{
  return
  nothing();
}

function initializedParamsToJson
JSONValue ::= val::InitializedParams
{
  return val.jsonValue;
}
