nonterminal TextDocumentClientCapabilities with jsonValue, documentSyncCapabilities, completionCapabilities, hoverCapabilities, signatureHelpCapabilities, findReferenceCapabilities, documentHighlightCapabilities, documentSymbolCapabilities, formattingCapabilities, rangeFormattingCapabilities, onTypeFormattingCapabilities, gotoDeclarationCapabilities, gotoDefinitionCapabilities, gotoTypeDefinitionCapabilities, gotoImplementationCapabilities, codeActionCapabilities, codeLensCapabilities, documentLinkCapabilities, colorProviderCapabilities, renameCapabilities, publishDiagnosticsCapabilities, foldingRangeCapabilities;

synthesized attribute documentSyncCapabilities :: Maybe<DocumentSynchronizationCapabilities>;
synthesized attribute completionCapabilities :: Maybe<CompletionCapabilities>;
synthesized attribute hoverCapabilities :: Maybe<HoverCapabilities>;
synthesized attribute signatureHelpCapabilities :: Maybe<SignatureHelpCapabilities>;
synthesized attribute findReferenceCapabilities :: Maybe<FindReferencesCapabilities>;
synthesized attribute documentHighlightCapabilities :: Maybe<DocumentHighlightCapabilities>;
synthesized attribute documentSymbolCapabilities :: Maybe<DocumentSymbolCapabilities>;
synthesized attribute formattingCapabilities :: Maybe<FormattingCapabilities>;
synthesized attribute rangeFormattingCapabilities :: Maybe<RangeFormattingCapabilities>;
synthesized attribute onTypeFormattingCapabilities :: Maybe<OnTypeFormattingCapabilities>;
synthesized attribute gotoDeclarationCapabilities :: Maybe<GoToDeclarationCapabilities>;
synthesized attribute gotoDefinitionCapabilities :: Maybe<GoToDefinitionCapabilities>;
synthesized attribute gotoTypeDefinitionCapabilities :: Maybe<GoToTypeDefinitionCapabilities>;
synthesized attribute gotoImplementationCapabilities :: Maybe<GoToImplementationCapabilities>;
synthesized attribute codeActionCapabilities :: Maybe<CodeActionCapabilities>;
synthesized attribute codeLensCapabilities :: Maybe<CodeLensCapabilities>;
synthesized attribute documentLinkCapabilities :: Maybe<DocumentLinkCapabilities>;
synthesized attribute colorProviderCapabilities :: Maybe<ColorProviderCapabilities>;
synthesized attribute renameCapabilities :: Maybe<RenameCapabilities>;
synthesized attribute publishDiagnosticsCapabilities :: Maybe<PublishDiagnosticsCapabilities>;
synthesized attribute foldingRangeCapabilities :: Maybe<FoldingRangeCapabilities>;

abstract production textDocumentClientCapabilities
top::TextDocumentClientCapabilities::=
  synchronization::Maybe<DocumentSynchronizationCapabilities> completion::Maybe<CompletionCapabilities> hover::Maybe<HoverCapabilities> signatureHelp::Maybe<SignatureHelpCapabilities> references::Maybe<FindReferencesCapabilities> documentHighlight::Maybe<DocumentHighlightCapabilities> documentSymbol::Maybe<DocumentSymbolCapabilities> formatting::Maybe<FormattingCapabilities> rangeFormatting::Maybe<RangeFormattingCapabilities> onTypeFormatting::Maybe<OnTypeFormattingCapabilities> declaration::Maybe<GoToDeclarationCapabilities> definition::Maybe<GoToDefinitionCapabilities> typeDefinition::Maybe<GoToTypeDefinitionCapabilities> implementation::Maybe<GoToImplementationCapabilities> codeAction::Maybe<CodeActionCapabilities> codeLens::Maybe<CodeLensCapabilities> documentLink::Maybe<DocumentLinkCapabilities> colorProvider::Maybe<ColorProviderCapabilities> rename::Maybe<RenameCapabilities> publishDiagnostics::Maybe<PublishDiagnosticsCapabilities> foldingRange::Maybe<FoldingRangeCapabilities>
{
  top.documentSyncCapabilities = synchronization;
  top.completionCapabilities = completion;
  top.hoverCapabilities = hover;
  top.signatureHelpCapabilities = signatureHelp;
  top.findReferenceCapabilities = references;
  top.documentHighlightCapabilities = documentHighlight;
  top.documentSymbolCapabilities = documentSymbol;
  top.formattingCapabilities = formatting;
  top.rangeFormattingCapabilities = rangeFormatting;
  top.onTypeFormattingCapabilities = onTypeFormatting;
  top.gotoDeclarationCapabilities = declaration;
  top.gotoDefinitionCapabilities = definition;
  top.gotoTypeDefinitionCapabilities = typeDefinition;
  top.gotoImplementationCapabilities = implementation;
  top.codeActionCapabilities = codeAction;
  top.codeLensCapabilities = codeLens;
  top.documentLinkCapabilities = documentLink;
  top.colorProviderCapabilities = colorProvider;
  top.renameCapabilities = rename;
  top.publishDiagnosticsCapabilities = publishDiagnostics;
  top.foldingRangeCapabilities = foldingRange;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("synchronization", mapMaybe((.jsonValue), synchronization), 
    maybeAddKeyValuePairToJSONObject("completion", mapMaybe((.jsonValue), completion), 
    maybeAddKeyValuePairToJSONObject("hover", mapMaybe((.jsonValue), hover), 
    maybeAddKeyValuePairToJSONObject("signatureHelp", mapMaybe((.jsonValue), signatureHelp), 
    maybeAddKeyValuePairToJSONObject("references", mapMaybe((.jsonValue), references), 
    maybeAddKeyValuePairToJSONObject("documentHighlight", mapMaybe((.jsonValue), documentHighlight), 
    maybeAddKeyValuePairToJSONObject("documentSymbol", mapMaybe((.jsonValue), documentSymbol), 
    maybeAddKeyValuePairToJSONObject("formatting", mapMaybe((.jsonValue), formatting), 
    maybeAddKeyValuePairToJSONObject("rangeFormatting", mapMaybe((.jsonValue), rangeFormatting), 
    maybeAddKeyValuePairToJSONObject("onTypeFormatting", mapMaybe((.jsonValue), onTypeFormatting), 
    maybeAddKeyValuePairToJSONObject("declaration", mapMaybe((.jsonValue), declaration), 
    maybeAddKeyValuePairToJSONObject("definition", mapMaybe((.jsonValue), definition), 
    maybeAddKeyValuePairToJSONObject("typeDefinition", mapMaybe((.jsonValue), typeDefinition), 
    maybeAddKeyValuePairToJSONObject("implementation", mapMaybe((.jsonValue), implementation), 
    maybeAddKeyValuePairToJSONObject("codeAction", mapMaybe((.jsonValue), codeAction), 
    maybeAddKeyValuePairToJSONObject("codeLens", mapMaybe((.jsonValue), codeLens), 
    maybeAddKeyValuePairToJSONObject("documentLink", mapMaybe((.jsonValue), documentLink), 
    maybeAddKeyValuePairToJSONObject("colorProvider", mapMaybe((.jsonValue), colorProvider), 
    maybeAddKeyValuePairToJSONObject("rename", mapMaybe((.jsonValue), rename), 
    maybeAddKeyValuePairToJSONObject("publishDiagnostics", mapMaybe((.jsonValue), publishDiagnostics), 
    maybeAddKeyValuePairToJSONObject("foldingRange", mapMaybe((.jsonValue), foldingRange), 
    jsonObject([]))))))))))))))))))))));
}

function parseTextDocumentClientCapabilities
Either<ResponseError TextDocumentClientCapabilities> ::= val::JSONValue
{
  local synchronization :: Maybe<Either<ResponseError DocumentSynchronizationCapabilities>>
    = mapMaybe(parseDocumentSynchronizationCapabilities, getValueWithKey("synchronization", val));
  local completion :: Maybe<Either<ResponseError CompletionCapabilities>>
    = mapMaybe(parseCompletionCapabilities, getValueWithKey("completion", val));
  local hover :: Maybe<Either<ResponseError HoverCapabilities>>
    = mapMaybe(parseHoverCapabilities, getValueWithKey("hover", val));
  local signatureHelp :: Maybe<Either<ResponseError SignatureHelpCapabilities>>
    = mapMaybe(parseSignatureHelpCapabilities, getValueWithKey("signatureHelp", val));
  local references :: Maybe<Either<ResponseError FindReferencesCapabilities>>
    = mapMaybe(parseFindReferencesCapabilities, getValueWithKey("references", val));
  local documentHighlight :: Maybe<Either<ResponseError DocumentHighlightCapabilities>>
    = mapMaybe(parseDocumentHighlightCapabilities, getValueWithKey("documentHighlight", val));
  local documentSymbol :: Maybe<Either<ResponseError DocumentSymbolCapabilities>>
    = mapMaybe(parseDocumentSymbolCapabilities, getValueWithKey("documentSymbol", val));
  local formatting :: Maybe<Either<ResponseError FormattingCapabilities>>
    = mapMaybe(parseFormattingCapabilities, getValueWithKey("formatting", val));
  local rangeFormatting :: Maybe<Either<ResponseError RangeFormattingCapabilities>>
    = mapMaybe(parseRangeFormattingCapabilities, getValueWithKey("rangeFormatting", val));
  local onTypeFormatting :: Maybe<Either<ResponseError OnTypeFormattingCapabilities>>
    = mapMaybe(parseOnTypeFormattingCapabilities, getValueWithKey("onTypeFormatting", val));
  local declaration :: Maybe<Either<ResponseError GoToDeclarationCapabilities>>
    = mapMaybe(parseGoToDeclarationCapabilities, getValueWithKey("declaration", val));
  local definition :: Maybe<Either<ResponseError GoToDefinitionCapabilities>>
    = mapMaybe(parseGoToDefinitionCapabilities, getValueWithKey("definition", val));
  local typeDefinition :: Maybe<Either<ResponseError GoToTypeDefinitionCapabilities>>
    = mapMaybe(parseGoToTypeDefinitionCapabilities, getValueWithKey("typeDefinition", val));
  local implementation :: Maybe<Either<ResponseError GoToImplementationCapabilities>>
    = mapMaybe(parseGoToImplementationCapabilities, getValueWithKey("implementation", val));
  local codeAction :: Maybe<Either<ResponseError CodeActionCapabilities>>
    = mapMaybe(parseCodeActionCapabilities, getValueWithKey("codeAction", val));
  local codeLens :: Maybe<Either<ResponseError CodeLensCapabilities>>
    = mapMaybe(parseCodeLensCapabilities, getValueWithKey("codeLens", val));
  local documentLink :: Maybe<Either<ResponseError DocumentLinkCapabilities>>
    = mapMaybe(parseDocumentLinkCapabilities, getValueWithKey("documentLink", val));
  local colorProvider :: Maybe<Either<ResponseError ColorProviderCapabilities>>
    = mapMaybe(parseColorProviderCapabilities, getValueWithKey("colorProvider", val));
  local rename :: Maybe<Either<ResponseError RenameCapabilities>>
    = mapMaybe(parseRenameCapabilities, getValueWithKey("rename", val));
  local publishDiagnostics :: Maybe<Either<ResponseError PublishDiagnosticsCapabilities>>
    = mapMaybe(parsePublishDiagnosticsCapabilities, getValueWithKey("publishDiagnostics", val));
  local foldingRange :: Maybe<Either<ResponseError FoldingRangeCapabilities>>
    = mapMaybe(parseFoldingRangeCapabilities, getValueWithKey("foldingRange", val));

  local err :: Maybe<ResponseError> = 
    parseTextDocumentClientCapabilitiesError(synchronization, completion, hover, signatureHelp, references, documentHighlight, documentSymbol, formatting, rangeFormatting, onTypeFormatting, declaration, definition, typeDefinition, implementation, codeAction, codeLens, documentLink, colorProvider, rename, publishDiagnostics, foldingRange);

  return
  if err.isJust
  then left(err.fromJust)
  else right(textDocumentClientCapabilities(rightMaybe(synchronization), rightMaybe(completion), rightMaybe(hover), rightMaybe(signatureHelp), rightMaybe(references), rightMaybe(documentHighlight), rightMaybe(documentSymbol), rightMaybe(formatting), rightMaybe(rangeFormatting), rightMaybe(onTypeFormatting), rightMaybe(declaration), rightMaybe(definition), rightMaybe(typeDefinition), rightMaybe(implementation), rightMaybe(codeAction), rightMaybe(codeLens), rightMaybe(documentLink), rightMaybe(colorProvider), rightMaybe(rename), rightMaybe(publishDiagnostics), rightMaybe(foldingRange)));
}

function parseTextDocumentClientCapabilitiesError
Maybe<ResponseError> ::= 
  synchronization::Maybe<Either<ResponseError DocumentSynchronizationCapabilities>> completion::Maybe<Either<ResponseError CompletionCapabilities>> hover::Maybe<Either<ResponseError HoverCapabilities>> signatureHelp::Maybe<Either<ResponseError SignatureHelpCapabilities>> references::Maybe<Either<ResponseError FindReferencesCapabilities>> documentHighlight::Maybe<Either<ResponseError DocumentHighlightCapabilities>> documentSymbol::Maybe<Either<ResponseError DocumentSymbolCapabilities>> formatting::Maybe<Either<ResponseError FormattingCapabilities>> rangeFormatting::Maybe<Either<ResponseError RangeFormattingCapabilities>> onTypeFormatting::Maybe<Either<ResponseError OnTypeFormattingCapabilities>> declaration::Maybe<Either<ResponseError GoToDeclarationCapabilities>> definition::Maybe<Either<ResponseError GoToDefinitionCapabilities>> typeDefinition::Maybe<Either<ResponseError GoToTypeDefinitionCapabilities>> implementation::Maybe<Either<ResponseError GoToImplementationCapabilities>> codeAction::Maybe<Either<ResponseError CodeActionCapabilities>> codeLens::Maybe<Either<ResponseError CodeLensCapabilities>> documentLink::Maybe<Either<ResponseError DocumentLinkCapabilities>> colorProvider::Maybe<Either<ResponseError ColorProviderCapabilities>> rename::Maybe<Either<ResponseError RenameCapabilities>> publishDiagnostics::Maybe<Either<ResponseError PublishDiagnosticsCapabilities>> foldingRange::Maybe<Either<ResponseError FoldingRangeCapabilities>>
{
  return
  if synchronization.isJust && synchronization.fromJust.isLeft then just(synchronization.fromJust.fromLeft)
  else if completion.isJust && completion.fromJust.isLeft then just(completion.fromJust.fromLeft)
  else if hover.isJust && hover.fromJust.isLeft then just(hover.fromJust.fromLeft)
  else if signatureHelp.isJust && signatureHelp.fromJust.isLeft then just(signatureHelp.fromJust.fromLeft)
  else if references.isJust && references.fromJust.isLeft then just(references.fromJust.fromLeft)
  else if documentHighlight.isJust && documentHighlight.fromJust.isLeft then just(documentHighlight.fromJust.fromLeft)
  else if documentSymbol.isJust && documentSymbol.fromJust.isLeft then just(documentSymbol.fromJust.fromLeft)
  else if formatting.isJust && formatting.fromJust.isLeft then just(formatting.fromJust.fromLeft)
  else if rangeFormatting.isJust && rangeFormatting.fromJust.isLeft then just(rangeFormatting.fromJust.fromLeft)
  else if onTypeFormatting.isJust && onTypeFormatting.fromJust.isLeft then just(onTypeFormatting.fromJust.fromLeft)
  else if declaration.isJust && declaration.fromJust.isLeft then just(declaration.fromJust.fromLeft)
  else if definition.isJust && definition.fromJust.isLeft then just(definition.fromJust.fromLeft)
  else if typeDefinition.isJust && typeDefinition.fromJust.isLeft then just(typeDefinition.fromJust.fromLeft)
  else if implementation.isJust && implementation.fromJust.isLeft then just(implementation.fromJust.fromLeft)
  else if codeAction.isJust && codeAction.fromJust.isLeft then just(codeAction.fromJust.fromLeft)
  else if codeLens.isJust && codeLens.fromJust.isLeft then just(codeLens.fromJust.fromLeft)
  else if documentLink.isJust && documentLink.fromJust.isLeft then just(documentLink.fromJust.fromLeft)
  else if colorProvider.isJust && colorProvider.fromJust.isLeft then just(colorProvider.fromJust.fromLeft)
  else if rename.isJust && rename.fromJust.isLeft then just(rename.fromJust.fromLeft)
  else if publishDiagnostics.isJust && publishDiagnostics.fromJust.isLeft then just(publishDiagnostics.fromJust.fromLeft)
  else if foldingRange.isJust && foldingRange.fromJust.isLeft then just(foldingRange.fromJust.fromLeft)
  else nothing();
}

function textDocumentClientCapabilitiesToJson
JSONValue ::= val::TextDocumentClientCapabilities
{
  return val.jsonValue;
}
