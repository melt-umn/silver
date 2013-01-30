grammar silver:definition:concrete_syntax:ast;

-- From TerminalModifiers
--synthesized attribute dominatesXML :: String;
--synthesized attribute submitsXML :: String;


{--
 - Modifiers for lexer classes.
 -}
nonterminal SyntaxLexerClassModifiers with cstEnv, cstErrors, dominatesXML, submitsXML, unparses;

abstract production consLexerClassMod
top::SyntaxLexerClassModifiers ::= h::SyntaxLexerClassModifier  t::SyntaxLexerClassModifiers
{
  top.cstErrors := h.cstErrors ++ t.cstErrors;
  top.dominatesXML = h.dominatesXML ++ t.dominatesXML;
  top.submitsXML = h.submitsXML ++ t.submitsXML;
  top.unparses = h.unparses ++ t.unparses;
}

abstract production nilLexerClassMod
top::SyntaxLexerClassModifiers ::= 
{
  top.cstErrors := [];
  top.dominatesXML = "";
  top.submitsXML = "";
  top.unparses = [];
}



{--
 - Modifiers for lexer classes.
 -}
nonterminal SyntaxLexerClassModifier with cstEnv, cstErrors, dominatesXML, submitsXML, unparses;

{- We default ALL attributes, so we can focus only on those that are interesting in each case... -}
aspect default production
top::SyntaxLexerClassModifier ::=
{
  --top.cstErrors := [];
  top.dominatesXML = "";
  top.submitsXML = "";
  --top.unparses -- don't default unparses
}

{--
 - The submits list for the lexer class. Either lexer classes or terminals.
 -}
abstract production lexerClassSubmits
top::SyntaxLexerClassModifier ::= sub::[String]
{
  production subRefs :: [[Decorated SyntaxDcl]] = lookupStrings(sub, top.cstEnv);

  top.cstErrors := []; -- TODO error checking!
  top.submitsXML = implode("", map(xmlCopperRef, map(head, subRefs)));
  top.unparses = ["sub(" ++ unparseStrings(sub) ++ ")"];
}
{--
 - The dominates list for the lexer class. Either lexer classes or terminals.
 -}
abstract production lexerClassDominates
top::SyntaxLexerClassModifier ::= dom::[String]
{
  production domRefs :: [[Decorated SyntaxDcl]] = lookupStrings(dom, top.cstEnv);

  top.cstErrors := []; -- TODO error checking!
  top.dominatesXML = implode("", map(xmlCopperRef, map(head, domRefs)));
  top.unparses = ["dom(" ++ unparseStrings(dom) ++ ")"];
}

