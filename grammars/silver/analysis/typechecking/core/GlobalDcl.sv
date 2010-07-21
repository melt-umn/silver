grammar silver:analysis:typechecking:core;
import silver:definition:core;

aspect production globalValueDclConcrete
top::AGDcl ::= 'global' id::Name '=' e::Expr ';'
{
  top.typeErrors = e.typeErrors;
}

