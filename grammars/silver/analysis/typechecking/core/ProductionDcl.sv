grammar silver:analysis:typechecking:core;

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  top.typeErrors := body.typeErrors;
  
  body.downSubst = emptySubst();
  body.finalSubst = body.upSubst;
}

