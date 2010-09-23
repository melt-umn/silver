grammar silver:definition:concrete_syntax;

aspect production attributionDcl
top::AGDcl ::= 'attribute' a::QName '<' tlat::TypeList '>' 'occurs' 'on' nt::QName '<' tlnt::TypeList '>' ';'
{
  top.parserDcls = [];
  top.nonTerminalDcls = [];
  top.terminalDcls = [];
  top.ruleDcls = [];
}
