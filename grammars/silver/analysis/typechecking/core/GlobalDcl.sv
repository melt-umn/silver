grammar silver:analysis:typechecking:core;
import silver:definition:core;
import silver:definition:env;

aspect production globalValueDclConcrete
top::AGDcl ::= 'global' id::Name '::' t::Type '=' e::Expr ';'
{
  top.typeErrors := e.typeErrors;
  
  top.typeErrors <- if e.typerep.typeEquals(e.typerep, t.typerep).bValue
                    then []
                    else [err(top.location, "Declaration of global " ++ id.name ++ " with type " ++ t.pp ++ " has initialization expression with type " ++ e.typerep.typeName)];
}

