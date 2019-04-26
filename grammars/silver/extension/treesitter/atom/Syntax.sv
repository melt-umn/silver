grammar silver:extension:treesitter:atom;

synthesized attribute jsonAtom :: String;
attribute jsonAtom occurs on SyntaxDcl, SyntaxTerminalModifiers, SyntaxProductionModifiers, SyntaxLexerClassModifiers, SyntaxTerminalModifier, SyntaxProductionModifier, SyntaxLexerClassModifier;

synthesized attribute jsonTerminalList :: [String] occurs on Syntax;
synthesized attribute jsonNonterminalList :: [String] occurs on Syntax;
synthesized attribute jsonProductionList :: [String] occurs on Syntax;
synthesized attribute jsonLexerClassList :: [String] occurs on Syntax;
synthesized attribute jsonDisambiguationGroupList :: [String] occurs on Syntax;

aspect production nilSyntax
top::Syntax ::=
{
  top.jsonTerminalList = [];
  top.jsonNonterminalList = [];
  top.jsonProductionList = [];
  top.jsonLexerClassList = [];
  top.jsonDisambiguationGroupList = [];
}

aspect production consSyntax
top::Syntax ::= s1::SyntaxDcl s2::Syntax
{
  top.jsonTerminalList =
    if isTerminal(s1) then
      s1.jsonAtom :: s2.jsonTerminalList
    else
      s2.jsonTerminalList;

  top.jsonNonterminalList =
    if isNonTerminal(s1) then
      s1.jsonAtom :: s2.jsonNonterminalList
    else
      s2.jsonNonterminalList;

  top.jsonProductionList =
    if isProduction(s1) then
      s1.jsonAtom :: s2.jsonProductionList
    else
      s2.jsonProductionList;

  top.jsonLexerClassList =
    if isLexerClass(s1) then
      s1.jsonAtom :: s2.jsonLexerClassList
    else
      s2.jsonLexerClassList;

  top.jsonDisambiguationGroupList =
    if isSyntaxDisambiguationGroup(s1) then
      s1.jsonAtom :: s2.jsonDisambiguationGroupList
    else
      s2.jsonDisambiguationGroupList;
}

aspect production syntaxNonterminal
top::SyntaxDcl ::= t::Type subdcls::Syntax
{
  top.jsonAtom = s"""
  {
    "name": "${t.typeName}",
    "productions": [
      ${implode(",\n    ", subdcls.jsonProductionList)}
    ]
  }""";
}

aspect production syntaxTerminal
top::SyntaxDcl ::= n::String regex::Regex modifiers::SyntaxTerminalModifiers
{
  top.jsonAtom = s"""
  {
    "name": "${n}",
    "regex": "/${jsonify(regex.regString)}/",
    "modifiers": {${modifiers.jsonAtom}}
  }""";
}

aspect production syntaxProduction
top::SyntaxDcl ::= ns::NamedSignature modifiers::SyntaxProductionModifiers
{
  top.jsonAtom = s"""
  {
    "input_elements": [ ${implode(",", map(addQuotes, map(productionElementToString, ns.inputElements)))} ],
    "output_element": "${ns.outputElement.typerep.typeName}",
    "modifiers": {${modifiers.jsonAtom}}
  }""";
}

aspect production syntaxLexerClass
top::SyntaxDcl ::= n::String modifiers::SyntaxLexerClassModifiers
{
  top.jsonAtom = s"""
  {
    "name": "${n}",
    "modifiers": {${modifiers.jsonAtom}}
  }
  """;
}

aspect production syntaxDisambiguationGroup
top::SyntaxDcl ::= n::String terms::[String] applicableToSubsets::Boolean acode::String
{
  top.jsonAtom = s"""
  {
    "name": "${n}",
    "terminals_involved": [
      ${implode(",", map(addQuotes, terms))}
    ],
    "applicableToSubsets": "${toString(applicableToSubsets)}",
    "acode": "${jsonify(acode)}"
  }""";
}
