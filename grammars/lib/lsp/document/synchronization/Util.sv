
import silver:util:raw:treemap as rtm;

function modifyTextForContentChange
String ::= s::String change::TextDocumentContentChangeEvent
{
  return
  if change.changedRange.isJust
  then error("Not Yet Implemented")
  else change.newText; -- no range so the new text is for the entire document
}
