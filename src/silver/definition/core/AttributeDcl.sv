grammar silver:definition:core;
import silver:definition:env;

concrete production attributeDclInh
top::AGDcl ::= 'inherited' 'attribute' a::Name '::' te::Type ';'
{
  top.pp = "inherited attribute " ++ a.pp ++ " :: " ++ te.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  top.moduleNames = [::String];

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  top.defs = addAttributeDcl(fName, te.typerep, 
             addInheritedDcl(fName, 
	     addFullNameDcl(a.name, fName,  emptyDefs())));

  local attribute er1 :: [Decorated Message];
  er1 = if length(getFullNameDclOne(a.name, top.env)) > 1
        then [err(top.location, "Name '" ++ a.pp ++ "' is already bound.")]
        else [::Decorated Message];	

  local attribute er2 :: [Decorated Message];
  er2 = if length(getAttributeDclOne(fName, top.env)) > 1
        then [err(top.location, "Attribute '" ++ fName ++ "' is already bound.")]
        else [::Decorated Message];	

  top.errors := er1 ++ er2 ++ te.errors;
  top.warnings := [::Decorated Message];
}

concrete production attributeDclSyn
top::AGDcl ::= 'synthesized' 'attribute' a::Name '::' te::Type ';'
{
  top.pp = "synthesized attribute " ++ a.pp ++ " :: " ++ te.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  top.moduleNames = [::String];

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  top.defs = addAttributeDcl(fName, te.typerep, 
             addSynthesizedDcl(fName, 
	     addFullNameDcl(a.name, fName,  emptyDefs())));

  local attribute er1 :: [Decorated Message];
  er1 = if length(getFullNameDclOne(a.name, top.env)) > 1
        then [err(top.location, "Name '" ++ a.pp ++ "' is already bound.")]
        else [::Decorated Message];

  local attribute er2 :: [Decorated Message];
  er2 = if length(getAttributeDclOne(fName, top.env)) > 1
        then [err(top.location, "Attribute '" ++ fName ++ "' is already bound.")]
        else [::Decorated Message];	

  top.errors := er1 ++ er2 ++ te.errors;
  top.warnings := [::Decorated Message];
}




