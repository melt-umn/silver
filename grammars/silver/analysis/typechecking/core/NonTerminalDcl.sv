grammar silver:analysis:typechecking:core;
import silver:definition:core;

aspect production defaultNonterminalDcl
top::AGDcl ::= id::Name
{
  top.typeErrors := [];
}

