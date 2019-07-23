nonterminal WorkspaceEditCapabilities with jsonValue, documentChangeCapability, resourceOperationsSupported, failureHandlingStrategy;

synthesized attribute documentChangeCapability :: Maybe<Boolean>;
synthesized attribute resourceOperationsSupported :: Maybe<[ResourceOperationKind]>;
synthesized attribute failureHandlingStrategy :: Maybe<FailureHandlingKind>;

abstract production workspaceEditCapabilities
top::WorkspaceEditCapabilities::=
  documentChanges::Maybe<Boolean> resourceOperations::Maybe<[ResourceOperationKind]> failureHandling::Maybe<FailureHandlingKind>
{
  top.documentChangeCapability = documentChanges;
  top.resourceOperationsSupported = resourceOperations;
  top.failureHandlingStrategy = failureHandling;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("documentChanges", mapMaybe(jsonBoolean, documentChanges), 
    maybeAddKeyValuePairToJSONObject("resourceOperations", mapMaybe(jsonArray, mapMaybeList((.jsonValue), resourceOperations)), 
    maybeAddKeyValuePairToJSONObject("failureHandling", mapMaybe((.jsonValue), failureHandling), 
    jsonObject([]))));
}

function parseWorkspaceEditCapabilities
Either<ResponseError WorkspaceEditCapabilities> ::= val::JSONValue
{
  local documentChanges :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("documentChanges", val));
  local resourceOperations :: Maybe<[Either<ResponseError ResourceOperationKind>]>
    = mapMaybe(mapJsonArray(parseResourceOperationKind, _), getValueWithKey("resourceOperations", val));
  local failureHandling :: Maybe<Either<ResponseError FailureHandlingKind>>
    = mapMaybe(parseFailureHandlingKind, getValueWithKey("failureHandling", val));

  local err :: Maybe<ResponseError> = 
    parseWorkspaceEditCapabilitiesError(resourceOperations, failureHandling);

  return
  if err.isJust
  then left(err.fromJust)
  else right(workspaceEditCapabilities(documentChanges, allFromRightMaybe(resourceOperations), rightMaybe(failureHandling)));
}

function parseWorkspaceEditCapabilitiesError
Maybe<ResponseError> ::= 
  resourceOperations::Maybe<[Either<ResponseError ResourceOperationKind>]> failureHandling::Maybe<Either<ResponseError FailureHandlingKind>>
{
  return
  if resourceOperations.isJust && anyLeft(resourceOperations.fromJust) then firstLeft(resourceOperations.fromJust)
  else if failureHandling.isJust && failureHandling.fromJust.isLeft then just(failureHandling.fromJust.fromLeft)
  else nothing();
}

function workspaceEditCapabilitiesToJson
JSONValue ::= val::WorkspaceEditCapabilities
{
  return val.jsonValue;
}
