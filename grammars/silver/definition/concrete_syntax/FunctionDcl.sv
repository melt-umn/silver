grammar silver:definition:concrete_syntax;

aspect production functionDcl
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody 
{
  top.parserDcls = [];
  top.nonTerminalDcls = [];
  top.terminalDcls = [];
  top.ruleDcls = [];
}
