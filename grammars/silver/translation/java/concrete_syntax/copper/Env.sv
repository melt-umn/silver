grammar silver:translation:java:concrete_syntax:copper;

import silver:definition:env;
import silver:util;

synthesized attribute isLexerClassDeclaration :: Boolean;
attribute isLexerClassDeclaration occurs on EnvItem;
attribute submitsTo occurs on EnvItem;
attribute termDominates occurs on EnvItem;

function lexerClassEnvItem
Decorated EnvItem ::= n::String s::[String] d::[String]
{
  return decorate i_lexerClassEnvItem(n, s, d) with {};
}

abstract production i_lexerClassEnvItem
top::EnvItem ::= n::String s::[String] d::[String]
{
  top.unparse = "lexer_class('" ++ n ++ "', [" ++ (if null(s) then "" else "'" ++ folds("','", s) ++ "'") ++ "], [" ++ (if null(d) then "" else "'" ++ folds("','", d) ++ "'") ++ "])";

  top.submitsTo = s;
  top.termDominates = d;
  -- required to be defined.
  top.itemName = n;

  top.isLexerClassDeclaration = true;

  forwards to i_defaultEnvItem();
}

function getLexerClassDclOne
[Decorated EnvItem] ::= search::String e::Decorated Env
{
  return searchDclsOne(search, e.restTree);
}

function getLexerClassDcl
[Decorated EnvItem] ::= search::String e::Decorated Env
{
  return searchDcls(search, e.restTree);
}

function addLexerClassDcl
Decorated Defs ::= n::String s::[String] d::[String] e::Decorated Defs
{
  return consDefs(lexerClassEnvItem(n, s, d), e);
}

aspect production i_defaultEnvItem
top::EnvItem ::= 
{
  top.isLexerClassDeclaration = false;
}
