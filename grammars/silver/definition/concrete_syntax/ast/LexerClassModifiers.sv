grammar silver:definition:concrete_syntax:ast;

-- From TerminalModifiers
--synthesized attribute dominatesXML :: String;
--synthesized attribute submitsXML :: String;


{--
 - Modifiers for lexer classes.
 -}
nonterminal SyntaxLexerClassModifiers with cstEnv, cstErrors, dominatesXML, submitsXML;

abstract production consLexerClassMod
top::SyntaxLexerClassModifiers ::= h::SyntaxLexerClassModifier  t::SyntaxLexerClassModifiers
{
  top.cstErrors := h.cstErrors ++ t.cstErrors;
  top.dominatesXML = h.dominatesXML ++ t.dominatesXML;
  top.submitsXML = h.submitsXML ++ t.submitsXML;
}

abstract production nilLexerClassMod
top::SyntaxLexerClassModifiers ::= 
{
  top.cstErrors := [];
  top.dominatesXML = "";
  top.submitsXML = "";
}



{--
 - Modifiers for lexer classes.
 -}
nonterminal SyntaxLexerClassModifier with cstEnv, cstErrors, dominatesXML, submitsXML;

{- We default ALL attributes, so we can focus only on those that are interesting in each case... -}
aspect default production
top::SyntaxLexerClassModifier ::=
{
  --top.cstErrors := [];
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

