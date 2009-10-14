grammar silver:definition:concrete_syntax;
import silver:definition:core;

synthesized attribute terminalDcls :: [Decorated TerminalSpec];
synthesized attribute nonTerminalDcls :: [Decorated NonTerminalSpec];
synthesized attribute ruleDcls :: [Decorated RuleSpec];
synthesized attribute parserDcls :: [Decorated ParserSpec];

attribute parserDcls occurs on Root, AGDcls, AGDcl;
attribute terminalDcls occurs on Root, AGDcls, AGDcl;
attribute nonTerminalDcls occurs on Root, AGDcls, AGDcl;
attribute ruleDcls occurs on Root, AGDcls, AGDcl;

aspect production root
top::Root ::= gdcl::GrammarDcl ms::ModuleStmts ims::ImportStmts ags::AGDcls
{
  top.terminalDcls = ags.terminalDcls;
  top.nonTerminalDcls = ags.nonTerminalDcls;
  top.ruleDcls = ags.ruleDcls;
  top.parserDcls = ags.parserDcls;
}

aspect production agDclNone
top::AGDcl ::=
{
  top.parserDcls = [];
  top.nonTerminalDcls = [];
  top.terminalDcls = [];
  top.ruleDcls = [];
}

aspect production agDclsOne
top::AGDcls ::= ag::AGDcl
{
  top.parserDcls = ag.parserDcls;
  top.nonTerminalDcls = ag.nonTerminalDcls;
  top.terminalDcls = ag.terminalDcls;
  top.ruleDcls = ag.ruleDcls ;
}

aspect production agDclsCons
top::AGDcls ::= h::AGDcl t::AGDcls
{
  top.parserDcls = h.parserDcls ++ t.parserDcls;
  top.nonTerminalDcls = h.nonTerminalDcls ++ t.nonTerminalDcls;
  top.terminalDcls = h.terminalDcls ++ t.terminalDcls;
  top.ruleDcls = h.ruleDcls ++ t.ruleDcls;
}

aspect production agDclsAppend
top::AGDcls ::= ag1::AGDcls ag2::AGDcls
{
  top.parserDcls = ag1.parserDcls ++ ag2.parserDcls;
  top.nonTerminalDcls = ag1.nonTerminalDcls ++ ag2.nonTerminalDcls;
  top.terminalDcls = ag1.terminalDcls ++ ag2.terminalDcls;
  top.ruleDcls = ag1.ruleDcls ++ ag2.ruleDcls;
}

aspect production agDclAppend
top::AGDcl ::= ag1::AGDcl ag2::AGDcl
{
  top.parserDcls = ag1.parserDcls ++ ag2.parserDcls;
  top.nonTerminalDcls = ag1.nonTerminalDcls ++ ag2.nonTerminalDcls;
  top.terminalDcls = ag1.terminalDcls ++ ag2.terminalDcls;
  top.ruleDcls = ag1.ruleDcls ++ ag2.ruleDcls;
}
