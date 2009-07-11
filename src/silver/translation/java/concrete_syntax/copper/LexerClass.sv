grammar silver:translation:java:concrete_syntax:copper;

import silver:translation:java:core;

import silver:definition:core;
import silver:definition:env;
import silver:definition:concrete_syntax;

import silver:analysis:typechecking:core;

terminal Lexer_kwd 'lexer' lexer classes {KEYWORD};

concrete production lexerClassDcl
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

  production attribute fName_dashes :: String ; 
  fName_dashes = makeName(fName);

  top.defs = addLexerClassDcl(fName, subs.precTermList, doms.precTermList,
	     addFullNameDcl(id.name, fName, emptyDefs()));

  local attribute er1 :: [Decorated Message];
  er1 = if length(getFullNameDclOne(id.name, top.env)) > 1
       then [err(top.location, "Name '" ++ id.name ++ "' is already bound.")]
       else [];	

  local attribute er2 :: [Decorated Message];
  er2 = if length(getLexerClassDclOne(fName, top.env)) > 1
       then [err(top.location, "Lexer class '" ++ fName ++ "' is already bound.")]
       else [];	

  top.errors := er1 ++ er2 ++ subs.errors ++ doms.errors;

--from definition:concrete_syntax
  top.parserDcls = [];
  top.nonTerminalDcls = [];
  top.terminalDcls = [];			   
  top.ruleDcls = [];

--from translation:core
  top.javaClasses = [];
  top.setupInh = "";
  top.initProd = "";
  top.initAspect = "";

  top.typeErrors = [];
}


