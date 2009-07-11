grammar silver:analysis:typechecking:core;
import silver:definition:core;

aspect production attributeDclInh
top::AGDcl ::= 'inherited' 'attribute' a::Name '::' te::Type ';'
{
  top.typeErrors = [];
}


aspect production attributeDclSyn
top::AGDcl ::= 'synthesized' 'attribute' a::Name '::' te::Type ';'
{
  top.typeErrors = [];
}




