grammar silver:definition:concrete_syntax;

aspect production defaultNonterminalDcl
top::AGDcl ::=  id::Name
{
  top.parserDcls = [];
  top.nonTerminalDcls = [nonTerminalSpec(fName)];  
  top.terminalDcls = [];
  top.ruleDcls = [];
}


