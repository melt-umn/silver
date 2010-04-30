grammar silver:definition:core;

import silver:definition:env;

concrete production qNameId
top::QName ::= id::Name
{
  top.name = id.name;
  top.pp = id.pp;
  top.location = id.location;
  
  top.lookupValue = decorate valueLookup(top) with { env = top.env; };
  top.lookupType = decorate typeLookup(top) with { env = top.env; };
  top.lookupAttribute = decorate attributeLookup(top) with { env = top.env; };
}

concrete production qNameCons
top::QName ::= id::Name ':' qn::QName
{
  top.name = id.name ++ ":" ++ qn.name;
  top.pp = id.pp ++ ":" ++ qn.pp;
  top.location = loc(top.file, $2.line, $2.column);
  
  top.lookupValue = decorate valueLookup(top) with { env = top.env; };
  top.lookupType = decorate typeLookup(top) with { env = top.env; };
  top.lookupAttribute = decorate attributeLookup(top) with { env = top.env; };
}

synthesized attribute lookupValue :: Decorated QNameLookup occurs on QName;
synthesized attribute lookupType :: Decorated QNameLookup occurs on QName;
synthesized attribute lookupAttribute :: Decorated QNameLookup occurs on QName;

nonterminal QNameLookup with fullName, typerep, errors, env, envItems;

abstract production typeLookup
top::QNameLookup ::= q::Decorated QName
{
  production attribute fNames :: [Decorated EnvItem];
  fNames = getFullNameDcl(q.name, top.env);
  
  top.fullName = if null(fNames) then q.name else head(fNames).fullName;
  
  top.envItems = getTypeDcl(top.fullName, top.env);
  
  top.typerep = if null(top.envItems) then topTypeRep() else head(top.envItems).typerep;
  
  -- null fNames is NOT an error.
  -- null top.envItems IS an error.
  -- length(fNames) > 1 will be an error when we do lookups properly! TODO
  
  top.errors := if null(top.envItems) then [err(q.location, "Undeclared type '" ++ q.name ++ "'.")] else [];
}

abstract production valueLookup
top::QNameLookup ::= q::Decorated QName
{
  production attribute fNames :: [Decorated EnvItem];
  fNames = getFullNameDcl(q.name, top.env);
  
  top.fullName = if null(fNames) then q.name else head(fNames).fullName;
  
  top.envItems = getValueDcl(top.fullName, top.env);
  
  top.typerep = if null(top.envItems) then topTypeRep() else head(top.envItems).typerep;
  
  -- null fNames is NOT an error.
  -- null top.envItems IS an error.
  -- length(fNames) > 1 will be an error when we do lookups properly! TODO
  
  top.errors := if null(top.envItems) then [err(q.location, "Undeclared value '" ++ q.name ++ "'.")] else [];
}

abstract production attributeLookup
top::QNameLookup ::= q::Decorated QName
{
  production attribute fNames :: [Decorated EnvItem];
  fNames = getFullNameDcl(q.name, top.env);
  
  top.fullName = if null(fNames) then q.name else head(fNames).fullName;
  
  top.envItems = getAttributeDcl(top.fullName, top.env);
  
  top.typerep = if null(top.envItems) then topTypeRep() else head(top.envItems).typerep;
  
  -- null fNames is NOT an error.
  -- null top.envItems IS an error.
  -- length(fNames) > 1 will be an error when we do lookups properly! TODO
  
  top.errors := if null(top.envItems) then [err(q.location, "Undeclared attribute '" ++ q.name ++ "'.")] else [];
}

