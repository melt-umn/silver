grammar silver:definition:core;

concrete production globalValueDclConcrete
top::AGDcl ::= 'global' id::Name '::' t::Type '=' e::Expr ';'
{
  top.pp = "global " ++ id.pp ++ " = " ++ e.pp ++ "\n"; 
  top.location = loc(top.file, $1.line, $1.column);

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ id.name;

  top.defs = addGlobalValueDcl(top.grammarName, id.location, fName, t.typerep, emptyDefs());

  top.errors <-
        if length(getValueDclAll(fName, top.env)) > 1
        then [err(top.location, "Value '" ++ fName ++ "' is already bound.")]
        else [];

  top.errors := t.errors ++ e.errors;
  e.blockContext = globalExprContext();
  e.signature = decorate bogusNamedSignature() with {};
}
