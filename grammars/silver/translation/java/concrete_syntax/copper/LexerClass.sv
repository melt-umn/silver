grammar silver:translation:java:concrete_syntax:copper;

import silver:translation:java:core;

import silver:definition:core;
import silver:definition:env;
import silver:definition:concrete_syntax;

import silver:analysis:typechecking:core;

terminal Lexer_kwd 'lexer' lexer classes {KEYWORD};

concrete production lexerClassDclConcrete
top::AGDcl ::= 'lexer' 'class' id::Name ';'
{
  forwards to lexerClassDclFull(id, termPrecListNull(), termPrecListNull());
}

concrete production lexerClassDclSubmits
top::AGDcl ::= 'lexer' 'class' id::Name 'submits' 'to' t::TermPrecList ';'
{
  forwards to lexerClassDclFull(id, t, termPrecListNull());
}

concrete production lexerClassDclDominates
top::AGDcl ::= 'lexer' 'class' id::Name 'dominates' t::TermPrecList ';'
{
  forwards to lexerClassDclFull(id, termPrecListNull(), t);
}

concrete production lexerClassDclBoth1
top::AGDcl ::= 'lexer' 'class' id::Name 'dominates' t1::TermPrecList 'submits' 'to' t2::TermPrecList ';'
{
  forwards to lexerClassDclFull(id, t1, t2);
}

concrete production lexerClassDclBoth2
top::AGDcl ::= 'lexer' 'class' id::Name 'submits' 'to' t1::TermPrecList 'dominates' t2::TermPrecList ';'
{
  forwards to lexerClassDclFull(id, t2, t1);
}

abstract production lexerClassDclFull
top::AGDcl ::= id::Name subs::TermPrecList doms::TermPrecList
{
  top.pp = "lexer class " ++ id.name ++ ";";
  top.location = id.location;

  top.moduleNames = [];

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ id.name;

  top.defs = addLexerClassDcl(top.grammarName, id.location, fName, subs.precTermList, doms.precTermList,
             emptyDefs());

  top.errors <- if length(getLexerClassDcl(fName, top.env)) > 1
                then [err(top.location, "Lexer class '" ++ fName ++ "' is already bound.")]
                else [];	

  top.errors := subs.errors ++ doms.errors;

--from definition:concrete_syntax
  top.parserDcls = [];
  top.nonTerminalDcls = [];
  top.terminalDcls = [];			   
  top.ruleDcls = [];

--from translation:core
  top.javaClasses = [];
  top.setupInh := "";
  top.initProd := "";
  top.initValues := "";
  top.postInit := "";

  top.typeErrors := [];
}


