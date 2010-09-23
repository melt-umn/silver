grammar silver:definition:concrete_syntax;

aspect production attributeDclInh
top::AGDcl ::= 'inherited' 'attribute' a::Name '<' tl::TypeList '>' '::' te::Type ';'
{
  top.parserDcls = [];
  top.nonTerminalDcls = [];
  top.terminalDcls = [];
  top.ruleDcls = [];
}


aspect production attributeDclSyn
top::AGDcl ::= 'synthesized' 'attribute' a::Name '<' tl::TypeList '>' '::' te::Type ';'
{
  top.parserDcls = [];
  top.nonTerminalDcls = [];
  top.terminalDcls = [];
  top.ruleDcls = [];
}


