--grammar silver:definition:concrete_syntax;
import silver:definition:core;
import silver:definition:env;

attribute parserDcls occurs on RootSpec;
attribute nonTerminalDcls occurs on RootSpec;
attribute terminalDcls occurs on RootSpec;
attribute ruleDcls occurs on RootSpec;

aspect production unparseRootSpec
top::RootSpecUnparse ::= r::Decorated RootSpec{
  unparses <- ["terminals [" ++ foldTerminals(r.terminalDcls) ++ "]"];
  unparses <- ["nonterminals [" ++ foldNonTerminals(r.nonTerminalDcls) ++ "]"];
  unparses <- ["rules [" ++ foldRules(r.ruleDcls) ++ "]"];
}


function foldTerminals
String::= l::[Decorated TerminalSpec]{
  return if null(l) then "" else (head(l).unparse ++ (if null(tail(l)) then "" else ", " ++ foldTerminals(tail(l))));
}


function foldNonTerminals
String::= l::[Decorated NonTerminalSpec]{
  return if null(l) then "" else (head(l).unparse ++ (if null(tail(l)) then "" else ", " ++ foldNonTerminals(tail(l))));
}


function foldRules
String::= l::[Decorated RuleSpec]{
  return if null(l) then "" else (head(l).unparse ++ (if null(tail(l)) then "" else ", " ++ foldRules(tail(l))));
}

aspect production i_emptyRootSpec
top::RootSpec ::= 
{
  top.parserDcls = [];
  top.terminalDcls = [];
  top.nonTerminalDcls = [];
  top.ruleDcls = [];
}

aspect production i_rootSpecRoot
top::RootSpec ::=  c1::Decorated Root{

  top.parserDcls = c1.parserDcls;
  top.terminalDcls = c1.terminalDcls;
  top.nonTerminalDcls = c1.nonTerminalDcls;
  top.ruleDcls = c1.ruleDcls;
}

aspect production i_appendRootSpec
top::RootSpec ::= c1::Decorated RootSpec c2::Decorated RootSpec
{
  top.parserDcls = c1.parserDcls ++ c2.parserDcls;
  top.nonTerminalDcls = c1.nonTerminalDcls ++ c2.nonTerminalDcls;
  top.terminalDcls = c1.terminalDcls ++ c2.terminalDcls;
  top.ruleDcls = c1.ruleDcls ++ c2.ruleDcls;
}

