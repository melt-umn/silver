nonterminal ReferenceContext with jsonValue, includeReferenceToDeclaration;

synthesized attribute includeReferenceToDeclaration :: Boolean;

abstract production referenceContext
top::ReferenceContext::=
  includeDeclaration::Boolean
{
  top.includeReferenceToDeclaration = includeDeclaration;
  top.jsonValue =
    addKeyValuePairToJSONObject("includeDeclaration", jsonBoolean(includeDeclaration), 
    jsonObject([]));
}

function parseReferenceContext
Either<ResponseError ReferenceContext> ::= val::JSONValue
{
  local includeDeclaration :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("includeDeclaration", val));

  local err :: Maybe<ResponseError> = 
    parseReferenceContextError(includeDeclaration);

  return
  if err.isJust
  then left(err.fromJust)
  else right(referenceContext(includeDeclaration.fromJust));
}

function parseReferenceContextError
Maybe<ResponseError> ::= 
  includeDeclaration::Maybe<Boolean>
{
  return
  if !includeDeclaration.isJust then just(lspInvalidParams("includeDeclaration not set on ReferenceContext"))
  else nothing();
}

function referenceContextToJson
JSONValue ::= val::ReferenceContext
{
  return val.jsonValue;
}
