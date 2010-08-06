grammar silver:analysis:typechecking:core;
import silver:definition:core;

aspect production functionDcl
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody
{
  top.typeErrors := body.typeErrors;
}

