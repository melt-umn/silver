grammar silver:extension:doc:core;

aspect production globalValueDclConcrete
top::AGDcl ::= 'global' id::Name '::' t::Type '=' e::Expr ';'
{
}

