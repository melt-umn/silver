grammar silver:definition:core;

import silver:definition:env;

concrete production globalValueDclConcrete
top::AGDcl ::= 'global' id::Name '=' e::Expr ';'
{
  top.pp = "global " ++ id.pp ++ " = " ++ e.pp ++ "\n"; 
  top.location = loc(top.file, $1.line, $1.column);

  top.moduleNames = [];

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ id.name;

  top.defs = addGlobalValueDcl(top.grammarName, id.location, fName, e.typerep, emptyDefs());

  local attribute er2 :: [Decorated Message];
  er2 = if length(getValueDclAll(fName, top.env)) > 1
        then [err(top.location, "Value '" ++ fName ++ "' is already bound.")]
        else [];

  top.errors := er2 ++ e.errors;
  top.warnings := [];
}
