grammar silver:definition:concrete_syntax:ast;

-- From TerminalModifiers
--synthesized attribute dominatesXML :: String;
--synthesized attribute submitsXML :: String;

autocopy attribute className :: String;

{--
 - Modifiers for lexer classes.
 -}
nonterminal SyntaxLexerClassModifiers with cstEnv, cstErrors, className, classTerminals, xmlCopper, dominatesXML, submitsXML;

abstract production consLexerClassMod
top::SyntaxLexerClassModifiers ::= h::SyntaxLexerClassModifier  t::SyntaxLexerClassModifiers
{
  top.cstErrors := h.cstErrors ++ t.cstErrors;
  top.xmlCopper = h.xmlCopper ++ t.xmlCopper;
  top.dominatesXML = h.dominatesXML ++ t.dominatesXML;
  top.submitsXML = h.submitsXML ++ t.submitsXML;
}

abstract production nilLexerClassMod
top::SyntaxLexerClassModifiers ::= 
{
  top.cstErrors := [];
  top.xmlCopper = "";
  top.dominatesXML = "";
  top.submitsXML = "";
}



{--
 - Modifiers for lexer classes.
 -}
nonterminal SyntaxLexerClassModifier with cstEnv, cstErrors, className, classTerminals, xmlCopper, dominatesXML, submitsXML;

{- We default ALL attributes, so we can focus only on those that are interesting in each case... -}
aspect default production
top::SyntaxLexerClassModifier ::=
{
  --top.cstErrors := [];
  top.xmlCopper = "";
  top.dominatesXML = "";
  top.submitsXML = "";
}

{--
 - The submits list for the lexer class. Either lexer classes or terminals.
 -}
abstract production lexerClassSubmits
top::SyntaxLexerClassModifier ::= sub::[String]
{
  production subRefs :: [[Decorated SyntaxDcl]] = lookupStrings(sub, top.cstEnv);

  top.cstErrors := flatMap(\ a::Pair<String [Decorated SyntaxDcl]> ->
                     if !null(a.snd) then []
                     else ["Terminal / Lexer Class " ++ a.fst ++ " was referenced but " ++
                           "this grammar was not included in this parser. (Referenced from submit clause for lexer class)"], --TODO: come up with a way to reference a given lexer class (line numbers would be great)
                   zipWith(pair, sub, subRefs)); 
  top.submitsXML = implode("", map(xmlCopperRef, map(head, subRefs)));
}
{--
 - The dominates list for the lexer class. Either lexer classes or terminals.
 -}
abstract production lexerClassDominates
top::SyntaxLexerClassModifier ::= dom::[String]
{
  production domRefs :: [[Decorated SyntaxDcl]] = lookupStrings(dom, top.cstEnv);

  top.cstErrors := flatMap(\ a::Pair<String [Decorated SyntaxDcl]> ->
                     if !null(a.snd) then []
                     else ["Terminal / Lexer Class " ++ a.fst ++ " was referenced but " ++
                           "this grammar was not included in this parser. (Referenced from dominates clause for lexer class)"],
                   zipWith(pair, dom, domRefs));
  top.dominatesXML = implode("", map(xmlCopperRef, map(head, domRefs)));
}

{--
 - A disambiguation function that should be created for the members of a lexer class.
 -}
abstract production lexerClassDisambiguate
top::SyntaxLexerClassModifier ::= acode::String
{
  production terms :: [String] = searchEnvTree(top.className, top.classTerminals);
  local trefs::[[Decorated SyntaxDcl]] = lookupStrings(terms, top.cstEnv);

  top.cstErrors := []; -- TODO: Check for duplicate disambiguation for a lexer class
  top.xmlCopper = s"""
  <DisambiguationFunction id="disambiguate_${makeCopperName(top.className)}" applicableToSubsets="true">
    <Members>${implode("", map(xmlCopperRef, map(head, trefs)))}</Members>
    <Code><![CDATA[
${acode}
    ]]></Code>
  </DisambiguationFunction>
""";
}
