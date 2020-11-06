grammar silver:modification:ffi;

imports silver:definition:core;
imports silver:definition:env;
imports silver:definition:type;
imports silver:definition:type:syntax;
imports silver:util;

imports silver:modification:typedecl;

-- Yikes, this was a weird choice of syntax.
concrete production ffiTypeDclLegacy
top::AGDcl ::= 'type' id::Name tl::BracketedOptTypeExprs 'foreign' ';'
{
  local obj :: String_t = terminal(String_t, "\"Object\"");
  --forwards to ffiTypeDcl('foreign', 'type', id, tl, '=', obj, ';', location=top.location);
  forwards to ffiTypeDclUgly('type', id, tl, 'foreign', '=', obj, ';', location=top.location);
}


{-------------------------------------------------------------------------------

Well, there were shift/reduce conflicts. I opened issue #310.

-- TODO: Right now we don't permit parameterized types in foreign types.
-- We should add support for this... later.
-- (e.g. right now we can do "IOToken" but not "ConsCell<%elem%>" or the like.)

concrete production ffiTypeDcl
top::AGDcl ::= 'foreign' 'type' id::Name tl::BracketedOptTypeExprs '=' trans::String_t ';'
-------------------------------------------------------------------------------}

concrete production ffiTypeDclUgly
top::AGDcl ::= 'type' id::Name tl::BracketedOptTypeExprs 'foreign' '=' trans::String_t ';'
{
  top.unparse = s"foreign type ${id.unparse}${tl.unparse} = ${trans.lexeme};";
  
  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ id.name;
  
  -- Strip quotes
  local transType :: String = substring(1, length(trans.lexeme) - 1, trans.lexeme);

  top.defs := [typeAliasDef(top.grammarName, id.location, fName, tl.freeVariables, foreignType(fName, transType, tl.types))];

  propagate errors, flowDefs;
  top.errors <- tl.errorsTyVars;
  
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

