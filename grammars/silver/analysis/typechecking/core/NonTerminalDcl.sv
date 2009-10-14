grammar silver:analysis:typechecking:core;
import silver:definition:core;

aspect production defaultNonterminalDcl
top::AGDcl ::= id::Name
{
  top.typeErrors = [];
}

aspect production closeNonterminalDcl
top::AGDcl ::= 'close' 'nonterminal' id::QName ';'
{
  top.typeErrors = [];
}
