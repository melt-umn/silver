
function referenceParamsToTextDocumentPositionParams
TextDocumentPositionParams ::= params::ReferenceParams
{
  return textDocumentPositionParams(params.documentId, params.position);
}
