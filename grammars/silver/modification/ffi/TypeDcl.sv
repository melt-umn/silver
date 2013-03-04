grammar silver:modification:ffi;

imports silver:definition:core;
imports silver:definition:env;
imports silver:definition:type;
imports silver:definition:type:syntax;
imports silver:util;

imports silver:modification:typedecl;

-- TODO: this should provide some sort of translation type
-- but right now, we don't. Phooey.

concrete production ffiTypeDcl
top::AGDcl ::= 'type' id::Name tl::BracketedOptTypeList 'foreign' ';'
{
  top.pp = "type " ++ id.pp ++ tl.pp ++ " foreign ;";
  
  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ id.name;

  top.defs = [typeAliasDef(top.grammarName, id.location, fName, tl.freeVariables, foreignTypeExp(fName, tl.types))];

  top.errors := tl.errors ++ tl.errorsTyVars;
  
  -- Put the variables listed on the rhs in the environment FOR TL ONLY, so they're all "declared"
  tl.initialEnv = top.env;
  tl.env = tl.envBindingTyVars;
  
  -- Redefinition check of the name
  top.errors <- 
    if length(getTypeDclAll(fName, top.env)) > 1 
    then [err(id.location, "Type '" ++ fName ++ "' is already bound.")]
    else [];

  top.errors <-
    if isLower(substring(0, 1, id.name))
    then [err(id.location, "Types must be capitalized. Invalid foreign type name " ++ id.name)]
    else [];
}

