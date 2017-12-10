grammar silver:analysis:typechecking:core;

aspect production mkProductionDcl
top::AGDcl ::= id::Name ns::ProductionSignature body::ProductionBody isAbstract::Boolean
{
  body.downSubst = emptySubst();
}

