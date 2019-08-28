
function makeCompletionItemFromLabel
CompletionItem ::= label::String
{
  return completionItem(
    label, 
    nothing(), -- completion item kind : Maybe<String>
    nothing(), -- completion item details : Maybe<String>
    nothing(), -- completion item documentation : Maybe<String>
    nothing(), -- is deprecated : Maybe<Boolean>
    nothing(), -- should be preselected :: Maybe<Boolean>
    nothing(), -- text to sort by :: Maybe<String>
    nothing(), -- text to filter by :: Maybe<String.
    nothing(), -- text to insert :: Maybe<String>
    nothing(), -- format of the insert teext :: Maybe<InsertTextFormat>
    nothing(), -- a text edit that should be applied when selected :: Maybe<TextEdit>
    nothing(), -- additional text edits to apply :: Maybe<[TextEdit]>
    nothing(), -- characters to commit the completion on :: Maybe<[String]>
    nothing(), -- command to execute after completion :: Maybe<Command>
    nothing() -- data stored between completion request and resolve request :: Maybe<JSONValue>
    );
}

function makeCompletionItemFromLabelAndKind
CompletionItem ::= label::String kind::CompletionItemKind
{

  return completionItem(
    label, 
    just(kind), -- completion item kind : Maybe<String>
    nothing(), -- completion item details : Maybe<String>
    nothing(), -- completion item documentation : Maybe<String>
    nothing(), -- is deprecated : Maybe<Boolean>
    nothing(), -- should be preselected :: Maybe<Boolean>
    nothing(), -- text to sort by :: Maybe<String>
    nothing(), -- text to filter by :: Maybe<String.
    nothing(), -- text to insert :: Maybe<String>
    nothing(), -- format of the insert teext :: Maybe<InsertTextFormat>
    nothing(), -- a text edit that should be applied when selected :: Maybe<TextEdit>
    nothing(), -- additional text edits to apply :: Maybe<[TextEdit]>
    nothing(), -- characters to commit the completion on :: Maybe<[String]>
    nothing(), -- command to execute after completion :: Maybe<Command>
    nothing() -- data stored between completion request and resolve request :: Maybe<JSONValue>
    );
}
