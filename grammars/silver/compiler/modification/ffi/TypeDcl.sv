grammar silver:compiler:modification:ffi;

imports silver:compiler:definition:core;
imports silver:compiler:definition:env;
imports silver:compiler:definition:type;
imports silver:compiler:definition:type:syntax;
imports silver:compiler:definition:flow:env;

-- Yikes, this was a weird choice of syntax.
concrete production ffiTypeDclLegacy
top::AGDcl ::= 'type' id::Name tl::BracketedOptTypeExprs 'foreign' ';'
{
  local obj :: String_t = terminal(String_t, "\"Object\"");
  --forwards to ffiTypeDcl('foreign', 'type', id, tl, '=', obj, ';');
  forwards to ffiTypeDclUgly('type', id, tl, 'foreign', '=', obj, ';');
} action {
  insert semantic token IdTypeDcl_t at id.nameLoc;
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

  top.defs := [typeAliasDef(top.grammarName, id.nameLoc, fName, [], tl.freeVariables, foreignType(fName, transType, tl.types))];

  propagate grammarName, errors, flowDefs, flowEnv;
  top.errors <- tl.errorsTyVars;
  
  -- Put the variables listed on the rhs in the environment FOR TL ONLY, so they're all "declared"
  tl.initialEnv = top.env;
  tl.env = tl.envBindingTyVars;
  
  -- Redefinition check of the name
  top.errors <- 
    if length(getTypeDclAll(fName, top.env)) > 1 
    then [errFromOrigin(id, "Type '" ++ fName ++ "' is already bound.")]
    else [];

  top.errors <-
    if isLower(substring(0, 1, id.name))
    then [errFromOrigin(id, "Types must be capitalized. Invalid foreign type name " ++ id.name)]
    else [];
} action {
  insert semantic token IdTypeDcl_t at id.nameLoc;
}

