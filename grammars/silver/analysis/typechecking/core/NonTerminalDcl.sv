grammar silver:analysis:typechecking:core;

aspect production defaultNonterminalDcl
top::AGDcl ::= id::Name tl::TypeList 
{
  top.typeErrors := [];
}

