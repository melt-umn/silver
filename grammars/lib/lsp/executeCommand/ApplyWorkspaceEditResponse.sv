nonterminal ApplyWorkspaceEditResponse with jsonValue, editApplied, editFailureReason;

synthesized attribute editApplied :: Boolean;
synthesized attribute editFailureReason :: Maybe<String>;

abstract production applyWorkspaceEditResponse
top::ApplyWorkspaceEditResponse::=
  applied::Boolean failureReason::Maybe<String>
{
  top.editApplied = applied;
  top.editFailureReason = failureReason;
  top.jsonValue =
    addKeyValuePairToJSONObject("applied", jsonBoolean(applied), 
    maybeAddKeyValuePairToJSONObject("failureReason", mapMaybe(jsonString, failureReason), 
    jsonObject([])));
}

function parseApplyWorkspaceEditResponse
Either<ResponseError ApplyWorkspaceEditResponse> ::= val::JSONValue
{
  local applied :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("applied", val));
  local failureReason :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("failureReason", val));

  local err :: Maybe<ResponseError> = 
    parseApplyWorkspaceEditResponseError(applied);

  return
  if err.isJust
  then left(err.fromJust)
  else right(applyWorkspaceEditResponse(applied.fromJust, failureReason));
}

function parseApplyWorkspaceEditResponseError
Maybe<ResponseError> ::= 
  applied::Maybe<Boolean>
{
  return
  if !applied.isJust then just(lspInvalidParams("applied not set on ApplyWorkspaceEditResponse"))
  else nothing();
}

function applyWorkspaceEditResponseToJson
JSONValue ::= val::ApplyWorkspaceEditResponse
{
  return val.jsonValue;
}
