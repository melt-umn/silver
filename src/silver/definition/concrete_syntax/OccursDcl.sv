--grammar silver:definition:concrete_syntax;
import silver:definition:core;

aspect production attributionDcl
top::AGDcl ::= 'attribute' a::QName 'occurs' 'on' nt::QName ';'
{
  top.parserDcls = [];
  top.nonTerminalDcls = [];
  top.terminalDcls = [];
  top.ruleDcls = [];
}
