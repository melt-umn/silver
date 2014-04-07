grammar silver:analysis:typechecking:core;

aspect production functionDcl
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody
{
  body.downSubst = emptySubst();
}

