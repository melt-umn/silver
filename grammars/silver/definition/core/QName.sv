grammar silver:definition:core;

import silver:definition:env;

concrete production qNameId
top::QName ::= id::Name
{
  top.name = id.name;
  top.pp = id.pp;
  top.location = id.location;
  
  top.lookupValue = decorate customLookup("value", getValueDcl, top) with { env = top.env; };
  top.lookupType = decorate customLookup("type", getTypeDcl, top) with { env = top.env; };
  top.lookupAttribute = decorate customLookup("attribute", getAttributeDcl, top) with { env = top.env; };

  top.lookupFunction = decorate customLookup("function", getFunctionDcl, top) with { env = top.env; };
  top.lookupProduction = decorate customLookup("production", getProductionDcl, top) with { env = top.env; };
}

concrete production qNameCons
top::QName ::= id::Name ':' qn::QName
{
  top.name = id.name ++ ":" ++ qn.name;
  top.pp = id.pp ++ ":" ++ qn.pp;
  top.location = loc(top.file, $2.line, $2.column);
  
  top.lookupValue = decorate customLookup("value", getValueDcl, top) with { env = top.env; };
  top.lookupType = decorate customLookup("type", getTypeDcl, top) with { env = top.env; };
  top.lookupAttribute = decorate customLookup("attribute", getAttributeDcl, top) with { env = top.env; };

  top.lookupFunction = decorate customLookup("function", getFunctionDcl, top) with { env = top.env; };
  top.lookupProduction = decorate customLookup("production", getProductionDcl, top) with { env = top.env; };
}

synthesized attribute lookupValue :: Decorated QNameLookup occurs on QName;
synthesized attribute lookupType :: Decorated QNameLookup occurs on QName;
synthesized attribute lookupAttribute :: Decorated QNameLookup occurs on QName;

-- TODO: these are to be removed and replaced by 'value' upon environment changes being finished
synthesized attribute lookupFunction :: Decorated QNameLookup occurs on QName;
synthesized attribute lookupProduction :: Decorated QNameLookup occurs on QName;


nonterminal QNameLookup with fullName, typerep, errors, env, envItems;

abstract production customLookup
top::QNameLookup ::= lookupName::String lookupFunc::Function([Decorated EnvItem] ::= String Decorated Env) q::Decorated QName 
{
  production attribute fNames :: [Decorated EnvItem];
  fNames = getFullNameDclNearest(q.name, top.env);
  
  top.fullName = if null(fNames) then q.name else head(fNames).fullName;
  
  top.envItems = lookupFunc(top.fullName, top.env);
  
  top.typerep = if null(top.envItems) then topTypeRep() else head(top.envItems).typerep;
  
  -- null fNames is NOT an error.
  -- null top.envItems IS an error.
  -- length(fNames) > 1 will be an error when we do lookups properly! TODO
  
  top.errors := (if null(top.envItems) then [err(q.location, "Undeclared " ++ lookupName ++ " '" ++ q.name ++ "'.")] else [])
             ++ (if length(fNames) > 1 then [err(q.location, "Ambiguous reference to " ++ lookupName ++ " '" ++ q.name ++ "'. Possibilities are:\n" ++ printEnvItems(fNames))] else []);
}

