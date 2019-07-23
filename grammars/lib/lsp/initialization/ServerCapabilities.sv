nonterminal ServerCapabilities with jsonValue, serverTextDocumentSyncCapabilities, serverHoverSupport, serverCompletionCapabilities, serverSignatureHelpCapabilities, serverGoToDefinitionSupport, serverGoToTypeDefinitionSupport, serverGoToImplementationSupport, serverFindReferencesSupport, serverDocumentHighlightSupport, serverWorkspaceSymbolSupport, serverCodeActionSupport, serverCodeLensCapabilities, serverFormattingSupport, serverRangeFormattingSupport, serverOnTypeFormattingCapabilities, serverRenameSupport, serverDocumentLinkCapabilities, serverFoldingRangeSupport, serverGoToDeclarationSupport, serverExecuteCommandCapabilities, serverWorkspaceCapabilities;

synthesized attribute serverTextDocumentSyncCapabilities :: Maybe<TextDocumentSyncOptions>;
synthesized attribute serverHoverSupport :: Maybe<Boolean>;
synthesized attribute serverCompletionCapabilities :: Maybe<CompletionOptions>;
synthesized attribute serverSignatureHelpCapabilities :: Maybe<SignatureHelpOptions>;
synthesized attribute serverGoToDefinitionSupport :: Maybe<Boolean>;
synthesized attribute serverGoToTypeDefinitionSupport :: Maybe<Boolean>;
synthesized attribute serverGoToImplementationSupport :: Maybe<Boolean>;
synthesized attribute serverFindReferencesSupport :: Maybe<Boolean>;
synthesized attribute serverDocumentHighlightSupport :: Maybe<Boolean>;
synthesized attribute serverWorkspaceSymbolSupport :: Maybe<Boolean>;
synthesized attribute serverCodeActionSupport :: Maybe<Boolean>;
synthesized attribute serverCodeLensCapabilities :: Maybe<CodeLensOptions>;
synthesized attribute serverFormattingSupport :: Maybe<Boolean>;
synthesized attribute serverRangeFormattingSupport :: Maybe<Boolean>;
synthesized attribute serverOnTypeFormattingCapabilities :: Maybe<DocumentOnTypeFormattingOptions>;
synthesized attribute serverRenameSupport :: Maybe<Boolean>;
synthesized attribute serverDocumentLinkCapabilities :: Maybe<DocumentLinkOptions>;
synthesized attribute serverFoldingRangeSupport :: Maybe<Boolean>;
synthesized attribute serverGoToDeclarationSupport :: Maybe<Boolean>;
synthesized attribute serverExecuteCommandCapabilities :: Maybe<ExecuteCommandOptions>;
synthesized attribute serverWorkspaceCapabilities :: Maybe<ServerWorkspaceCapabilities>;

abstract production serverCapabilities
top::ServerCapabilities::=
  textDocumentSync::Maybe<TextDocumentSyncOptions> hoverProvider::Maybe<Boolean> completionProvider::Maybe<CompletionOptions> signatureHelpProvider::Maybe<SignatureHelpOptions> definitionProvider::Maybe<Boolean> typeDefinitionProvider::Maybe<Boolean> implementationProvider::Maybe<Boolean> referencesProvider::Maybe<Boolean> documentHighlightProvider::Maybe<Boolean> workspaceSymbolProvider::Maybe<Boolean> codeActionProvider::Maybe<Boolean> codeLensProvider::Maybe<CodeLensOptions> documentFormattingProvider::Maybe<Boolean> documentRangeFormattingProvider::Maybe<Boolean> documentOnTypeFormattingProvider::Maybe<DocumentOnTypeFormattingOptions> renameProvider::Maybe<Boolean> documentLinkProvider::Maybe<DocumentLinkOptions> foldingRangeProvider::Maybe<Boolean> declarationProvider::Maybe<Boolean> executeCommandProvider::Maybe<ExecuteCommandOptions> workspace::Maybe<ServerWorkspaceCapabilities>
{
  top.serverTextDocumentSyncCapabilities = textDocumentSync;
  top.serverHoverSupport = hoverProvider;
  top.serverCompletionCapabilities = completionProvider;
  top.serverSignatureHelpCapabilities = signatureHelpProvider;
  top.serverGoToDefinitionSupport = definitionProvider;
  top.serverGoToTypeDefinitionSupport = typeDefinitionProvider;
  top.serverGoToImplementationSupport = implementationProvider;
  top.serverFindReferencesSupport = referencesProvider;
  top.serverDocumentHighlightSupport = documentHighlightProvider;
  top.serverWorkspaceSymbolSupport = workspaceSymbolProvider;
  top.serverCodeActionSupport = codeActionProvider;
  top.serverCodeLensCapabilities = codeLensProvider;
  top.serverFormattingSupport = documentFormattingProvider;
  top.serverRangeFormattingSupport = documentRangeFormattingProvider;
  top.serverOnTypeFormattingCapabilities = documentOnTypeFormattingProvider;
  top.serverRenameSupport = renameProvider;
  top.serverDocumentLinkCapabilities = documentLinkProvider;
  top.serverFoldingRangeSupport = foldingRangeProvider;
  top.serverGoToDeclarationSupport = declarationProvider;
  top.serverExecuteCommandCapabilities = executeCommandProvider;
  top.serverWorkspaceCapabilities = workspace;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("textDocumentSync", mapMaybe((.jsonValue), textDocumentSync), 
    maybeAddKeyValuePairToJSONObject("hoverProvider", mapMaybe(jsonBoolean, hoverProvider), 
    maybeAddKeyValuePairToJSONObject("completionProvider", mapMaybe((.jsonValue), completionProvider), 
    maybeAddKeyValuePairToJSONObject("signatureHelpProvider", mapMaybe((.jsonValue), signatureHelpProvider), 
    maybeAddKeyValuePairToJSONObject("definitionProvider", mapMaybe(jsonBoolean, definitionProvider), 
    maybeAddKeyValuePairToJSONObject("typeDefinitionProvider", mapMaybe(jsonBoolean, typeDefinitionProvider), 
    maybeAddKeyValuePairToJSONObject("implementationProvider", mapMaybe(jsonBoolean, implementationProvider), 
    maybeAddKeyValuePairToJSONObject("referencesProvider", mapMaybe(jsonBoolean, referencesProvider), 
    maybeAddKeyValuePairToJSONObject("documentHighlightProvider", mapMaybe(jsonBoolean, documentHighlightProvider), 
    maybeAddKeyValuePairToJSONObject("workspaceSymbolProvider", mapMaybe(jsonBoolean, workspaceSymbolProvider), 
    maybeAddKeyValuePairToJSONObject("codeActionProvider", mapMaybe(jsonBoolean, codeActionProvider), 
    maybeAddKeyValuePairToJSONObject("codeLensProvider", mapMaybe((.jsonValue), codeLensProvider), 
    maybeAddKeyValuePairToJSONObject("documentFormattingProvider", mapMaybe(jsonBoolean, documentFormattingProvider), 
    maybeAddKeyValuePairToJSONObject("documentRangeFormattingProvider", mapMaybe(jsonBoolean, documentRangeFormattingProvider), 
    maybeAddKeyValuePairToJSONObject("documentOnTypeFormattingProvider", mapMaybe((.jsonValue), documentOnTypeFormattingProvider), 
    maybeAddKeyValuePairToJSONObject("renameProvider", mapMaybe(jsonBoolean, renameProvider), 
    maybeAddKeyValuePairToJSONObject("documentLinkProvider", mapMaybe((.jsonValue), documentLinkProvider), 
    maybeAddKeyValuePairToJSONObject("foldingRangeProvider", mapMaybe(jsonBoolean, foldingRangeProvider), 
    maybeAddKeyValuePairToJSONObject("declarationProvider", mapMaybe(jsonBoolean, declarationProvider), 
    maybeAddKeyValuePairToJSONObject("executeCommandProvider", mapMaybe((.jsonValue), executeCommandProvider), 
    maybeAddKeyValuePairToJSONObject("workspace", mapMaybe((.jsonValue), workspace), 
    jsonObject([]))))))))))))))))))))));
}

function parseServerCapabilities
Either<ResponseError ServerCapabilities> ::= val::JSONValue
{
  local textDocumentSync :: Maybe<Either<ResponseError TextDocumentSyncOptions>>
    = mapMaybe(parseTextDocumentSyncOptions, getValueWithKey("textDocumentSync", val));
  local hoverProvider :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("hoverProvider", val));
  local completionProvider :: Maybe<Either<ResponseError CompletionOptions>>
    = mapMaybe(parseCompletionOptions, getValueWithKey("completionProvider", val));
  local signatureHelpProvider :: Maybe<Either<ResponseError SignatureHelpOptions>>
    = mapMaybe(parseSignatureHelpOptions, getValueWithKey("signatureHelpProvider", val));
  local definitionProvider :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("definitionProvider", val));
  local typeDefinitionProvider :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("typeDefinitionProvider", val));
  local implementationProvider :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("implementationProvider", val));
  local referencesProvider :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("referencesProvider", val));
  local documentHighlightProvider :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("documentHighlightProvider", val));
  local workspaceSymbolProvider :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("workspaceSymbolProvider", val));
  local codeActionProvider :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("codeActionProvider", val));
  local codeLensProvider :: Maybe<Either<ResponseError CodeLensOptions>>
    = mapMaybe(parseCodeLensOptions, getValueWithKey("codeLensProvider", val));
  local documentFormattingProvider :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("documentFormattingProvider", val));
  local documentRangeFormattingProvider :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("documentRangeFormattingProvider", val));
  local documentOnTypeFormattingProvider :: Maybe<Either<ResponseError DocumentOnTypeFormattingOptions>>
    = mapMaybe(parseDocumentOnTypeFormattingOptions, getValueWithKey("documentOnTypeFormattingProvider", val));
  local renameProvider :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("renameProvider", val));
  local documentLinkProvider :: Maybe<Either<ResponseError DocumentLinkOptions>>
    = mapMaybe(parseDocumentLinkOptions, getValueWithKey("documentLinkProvider", val));
  local foldingRangeProvider :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("foldingRangeProvider", val));
  local declarationProvider :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("declarationProvider", val));
  local executeCommandProvider :: Maybe<Either<ResponseError ExecuteCommandOptions>>
    = mapMaybe(parseExecuteCommandOptions, getValueWithKey("executeCommandProvider", val));
  local workspace :: Maybe<Either<ResponseError ServerWorkspaceCapabilities>>
    = mapMaybe(parseServerWorkspaceCapabilities, getValueWithKey("workspace", val));

  local err :: Maybe<ResponseError> = 
    parseServerCapabilitiesError(textDocumentSync, completionProvider, signatureHelpProvider, codeLensProvider, documentOnTypeFormattingProvider, documentLinkProvider, executeCommandProvider, workspace);

  return
  if err.isJust
  then left(err.fromJust)
  else right(serverCapabilities(rightMaybe(textDocumentSync), hoverProvider, rightMaybe(completionProvider), rightMaybe(signatureHelpProvider), definitionProvider, typeDefinitionProvider, implementationProvider, referencesProvider, documentHighlightProvider, workspaceSymbolProvider, codeActionProvider, rightMaybe(codeLensProvider), documentFormattingProvider, documentRangeFormattingProvider, rightMaybe(documentOnTypeFormattingProvider), renameProvider, rightMaybe(documentLinkProvider), foldingRangeProvider, declarationProvider, rightMaybe(executeCommandProvider), rightMaybe(workspace)));
}

function parseServerCapabilitiesError
Maybe<ResponseError> ::= 
  textDocumentSync::Maybe<Either<ResponseError TextDocumentSyncOptions>> completionProvider::Maybe<Either<ResponseError CompletionOptions>> signatureHelpProvider::Maybe<Either<ResponseError SignatureHelpOptions>> codeLensProvider::Maybe<Either<ResponseError CodeLensOptions>> documentOnTypeFormattingProvider::Maybe<Either<ResponseError DocumentOnTypeFormattingOptions>> documentLinkProvider::Maybe<Either<ResponseError DocumentLinkOptions>> executeCommandProvider::Maybe<Either<ResponseError ExecuteCommandOptions>> workspace::Maybe<Either<ResponseError ServerWorkspaceCapabilities>>
{
  return
  if textDocumentSync.isJust && textDocumentSync.fromJust.isLeft then just(textDocumentSync.fromJust.fromLeft)
  else if completionProvider.isJust && completionProvider.fromJust.isLeft then just(completionProvider.fromJust.fromLeft)
  else if signatureHelpProvider.isJust && signatureHelpProvider.fromJust.isLeft then just(signatureHelpProvider.fromJust.fromLeft)
  else if codeLensProvider.isJust && codeLensProvider.fromJust.isLeft then just(codeLensProvider.fromJust.fromLeft)
  else if documentOnTypeFormattingProvider.isJust && documentOnTypeFormattingProvider.fromJust.isLeft then just(documentOnTypeFormattingProvider.fromJust.fromLeft)
  else if documentLinkProvider.isJust && documentLinkProvider.fromJust.isLeft then just(documentLinkProvider.fromJust.fromLeft)
  else if executeCommandProvider.isJust && executeCommandProvider.fromJust.isLeft then just(executeCommandProvider.fromJust.fromLeft)
  else if workspace.isJust && workspace.fromJust.isLeft then just(workspace.fromJust.fromLeft)
  else nothing();
}

function serverCapabilitiesToJson
JSONValue ::= val::ServerCapabilities
{
  return val.jsonValue;
}
