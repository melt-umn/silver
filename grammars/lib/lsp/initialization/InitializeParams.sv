nonterminal InitializeParams with jsonValue, processId, projectRootPath, projectRootUri, clientCapabilities, projectTraceSetting, projectWorkspaceFolders;

synthesized attribute processId :: Integer;
synthesized attribute projectRootPath :: Maybe<String>;
synthesized attribute projectRootUri :: DocumentUri;
synthesized attribute clientCapabilities :: ClientCapabilities;
synthesized attribute projectTraceSetting :: Maybe<Trace>;
synthesized attribute projectWorkspaceFolders :: Maybe<[WorkspaceFolder]>;

abstract production initializeParams
top::InitializeParams::=
  processId::Integer rootPath::Maybe<String> rootUri::DocumentUri capabilities::ClientCapabilities trace::Maybe<Trace> workspaceFolders::Maybe<[WorkspaceFolder]>
{
  top.processId = processId;
  top.projectRootPath = rootPath;
  top.projectRootUri = rootUri;
  top.clientCapabilities = capabilities;
  top.projectTraceSetting = trace;
  top.projectWorkspaceFolders = workspaceFolders;
  top.jsonValue =
    addKeyValuePairToJSONObject("processId", jsonInteger(processId), 
    maybeAddKeyValuePairToJSONObject("rootPath", mapMaybe(jsonString, rootPath), 
    addKeyValuePairToJSONObject("rootUri", jsonString(rootUri), 
    addKeyValuePairToJSONObject("capabilities", capabilities.jsonValue, 
    maybeAddKeyValuePairToJSONObject("trace", mapMaybe((.jsonValue), trace), 
    maybeAddKeyValuePairToJSONObject("workspaceFolders", mapMaybe(jsonArray, mapMaybeList((.jsonValue), workspaceFolders)), 
    jsonObject([])))))));
}

function parseInitializeParams
Either<ResponseError InitializeParams> ::= val::JSONValue
{
  local processId :: Maybe<Integer>
    = mapMaybe(jsonToInteger, getValueWithKey("processId", val));
  local rootPath :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("rootPath", val));
  local rootUri :: Maybe<DocumentUri>
    = mapMaybe(jsonToString, getValueWithKey("rootUri", val));
  local capabilities :: Maybe<Either<ResponseError ClientCapabilities>>
    = mapMaybe(parseClientCapabilities, getValueWithKey("capabilities", val));
  local trace :: Maybe<Either<ResponseError Trace>>
    = mapMaybe(parseTrace, getValueWithKey("trace", val));
  local workspaceFolders :: Maybe<[Either<ResponseError WorkspaceFolder>]>
    = mapMaybe(mapJsonArray(parseWorkspaceFolder, _), getValueWithKey("workspaceFolders", val));

  local err :: Maybe<ResponseError> = 
    parseInitializeParamsError(processId, rootUri, capabilities, trace, workspaceFolders);

  return
  if err.isJust
  then left(err.fromJust)
  else right(initializeParams(processId.fromJust, rootPath, rootUri.fromJust, capabilities.fromJust.fromRight, rightMaybe(trace), allFromRightMaybe(workspaceFolders)));
}

function parseInitializeParamsError
Maybe<ResponseError> ::= 
  processId::Maybe<Integer> rootUri::Maybe<DocumentUri> capabilities::Maybe<Either<ResponseError ClientCapabilities>> trace::Maybe<Either<ResponseError Trace>> workspaceFolders::Maybe<[Either<ResponseError WorkspaceFolder>]>
{
  return
  if !processId.isJust then just(lspInvalidParams("processId not set on InitializeParams"))
  else if !rootUri.isJust then just(lspInvalidParams("rootUri not set on InitializeParams"))
  else if !capabilities.isJust|| capabilities.fromJust.isLeft then just(lspInvalidParams("capabilities not set or invalid on InitializeParams"))
  else if trace.isJust && trace.fromJust.isLeft then just(trace.fromJust.fromLeft)
  else if workspaceFolders.isJust && anyLeft(workspaceFolders.fromJust) then firstLeft(workspaceFolders.fromJust)
  else nothing();
}

function initializeParamsToJson
JSONValue ::= val::InitializeParams
{
  return val.jsonValue;
}
