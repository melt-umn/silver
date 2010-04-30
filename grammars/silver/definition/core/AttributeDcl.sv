grammar silver:definition:core;
import silver:definition:env;

concrete production attributeDclInh
top::AGDcl ::= 'inherited' 'attribute' a::Name '::' te::Type ';'
{
  top.pp = "inherited attribute " ++ a.pp ++ " :: " ++ te.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  top.moduleNames = [];

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

{-

  production attribute dcl :: DclInfo;
  dcl = inhAttrDcl(top.grammarName, a.location, fName, te.typerep);
  
  top.defs = addAttributeDcl(dcl, emptyDefs());

-}

  top.defs = addAttributeDcl(fName, inhTypeRep(te.typerep),
	     addFullNameDcl(a.name, fName, emptyDefs()));

  local attribute er1 :: [Decorated Message];
  er1 = if length(getFullNameDclOne(a.name, top.env)) > 1
        then [err(top.location, "Name '" ++ a.pp ++ "' is already bound.")]
        else [];	

  local attribute er2 :: [Decorated Message];
  er2 = if length(getAttributeDclOne(fName, top.env)) > 1
        then [err(top.location, "Attribute '" ++ fName ++ "' is already bound.")]
        else [];	

  top.errors := er1 ++ er2 ++ te.errors;
  top.warnings := [];
}

concrete production attributeDclSyn
top::AGDcl ::= 'synthesized' 'attribute' a::Name '::' te::Type ';'
{
  top.pp = "synthesized attribute " ++ a.pp ++ " :: " ++ te.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  top.moduleNames = [];

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  top.defs = addAttributeDcl(fName, synTypeRep(te.typerep),
	     addFullNameDcl(a.name, fName, emptyDefs()));

  local attribute er1 :: [Decorated Message];
  er1 = if length(getFullNameDclOne(a.name, top.env)) > 1
        then [err(top.location, "Name '" ++ a.pp ++ "' is already bound.")]
        else [];

  local attribute er2 :: [Decorated Message];
  er2 = if length(getAttributeDclOne(fName, top.env)) > 1
        then [err(top.location, "Attribute '" ++ fName ++ "' is already bound.")]
        else [];	

  top.errors := er1 ++ er2 ++ te.errors;
  top.warnings := [];
}




