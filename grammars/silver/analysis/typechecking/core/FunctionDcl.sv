grammar silver:analysis:typechecking:core;

aspect production functionDcl
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody
{
  top.typeErrors := body.typeErrors;
  
  body.downSubst = emptySubst();
  body.finalSubst = body.upSubst;
}

