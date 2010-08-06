grammar silver:analysis:typechecking:core;
import silver:definition:core;

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  top.typeErrors := body.typeErrors;
}

