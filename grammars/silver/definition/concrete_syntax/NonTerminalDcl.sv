grammar silver:definition:concrete_syntax;

aspect production defaultNonterminalDcl
top::AGDcl ::= id::Name botl::BracketedOptTypeList
{
  top.nonTerminalDcls = [nonTerminalSpec(fName)];  
}


