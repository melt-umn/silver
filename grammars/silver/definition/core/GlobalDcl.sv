grammar silver:definition:core;

concrete production globalValueDclConcrete
top::AGDcl ::= 'global' id::Name '::' t::Type '=' e::Expr ';'
{
  top.pp = "global " ++ id.pp ++ " :: " ++ t.pp ++ " = " ++ e.pp ++ "\n"; 

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ id.name;

  top.defs = [globalDef(top.grammarName, id.location, fName, t.typerep)];

  top.errors <-
        if length(getValueDclAll(fName, top.env)) > 1
        then [err(id.location, "Value '" ++ fName ++ "' is already bound.")]
        else [];

  top.errors := t.errors ++ e.errors;
  e.blockContext = globalExprContext();
  e.signature = bogusNamedSignature();
}
