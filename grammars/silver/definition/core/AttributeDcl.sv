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

  top.defs = addInhDcl(top.grammarName, a.location, fName, te.typerep, emptyDefs());
  
  top.errors <-
        if length(getAttrDcl(fName, top.env)) > 1
        then [err(top.location, "Attribute '" ++ fName ++ "' is already bound.")]
        else [];	

  top.errors := te.errors;
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

  top.defs = addSynDcl(top.grammarName, a.location, fName, te.typerep, emptyDefs());

  top.errors <-
        if length(getAttrDcl(fName, top.env)) > 1
        then [err(top.location, "Attribute '" ++ fName ++ "' is already bound.")]
        else [];	

  top.errors := te.errors;
  top.warnings := [];
}




