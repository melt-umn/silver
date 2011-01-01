grammar silver:definition:concrete_syntax;

aspect production defaultNonterminalDcl
top::AGDcl ::= id::Name tl::TypeList
{
  top.nonTerminalDcls = [nonTerminalSpec(fName)];  
}


