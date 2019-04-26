function syntaxDclJsonName
String ::= d::Decorated SyntaxDcl
{
  return case d of
  | syntaxLexerClass(n, _) -> s""" "${n}" """
  | syntaxTerminal(n, _, _) -> s""" "${n}" """
  | syntaxNonterminal(n, _) -> s""" "${n.typeName}" """
  | syntaxProduction(ns, _) -> s""" "${ns.outputElement.typerep.typeName}" """
  | syntaxDisambiguationGroup(n, _, _, _) -> s""" "${n}" """
  end;
}

function addQuotes
String ::= s::String
{
  return s""" "${s}" """;
}
