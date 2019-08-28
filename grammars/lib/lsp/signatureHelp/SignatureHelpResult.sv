nonterminal SignatureHelpResult with jsonValue;

abstract production nullSignatureHelpResult
top::SignatureHelpResult ::=
{
  top.jsonValue = jsonNull();
}

abstract production signatureHelpResultSignatureHelp
top::SignatureHelpResult ::= result::SignatureHelp
{
  top.jsonValue = result.jsonValue;
}

function signatureHelpResultToJson
JSONValue ::= val::SignatureHelpResult
{
  return val.jsonValue;
}
