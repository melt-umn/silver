grammar silver:definition:core;

concrete production attributeDclInh
top::AGDcl ::= 'inherited' 'attribute' a::Name tl::BracketedOptTypeList '::' te::Type ';'
{
  top.pp = "inherited attribute " ++ a.pp ++ tl.pp ++ " :: " ++ te.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  top.defs = [inhDef(top.grammarName, a.location, fName, tl.freeVariables, te.typerep)];

  tl.initialEnv = top.env;
  tl.env = tl.envBindingTyVars;
  te.env = tl.envBindingTyVars;

  top.errors <-
    if length(getAttrDclAll(fName, top.env)) > 1
    then [err(top.location, "Attribute '" ++ fName ++ "' is already bound.")]
    else [];	

  top.errors := te.errors ++ tl.errors ++ tl.errorsTyVars;
}

concrete production attributeDclSyn
top::AGDcl ::= 'synthesized' 'attribute' a::Name tl::BracketedOptTypeList '::' te::Type ';'
{
  top.pp = "synthesized attribute " ++ a.pp ++ tl.pp ++ " :: " ++ te.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  top.defs = [synDef(top.grammarName, a.location, fName, tl.freeVariables, te.typerep)];

  tl.initialEnv = top.env;
  tl.env = tl.envBindingTyVars;
  te.env = tl.envBindingTyVars;
  
  top.errors <-
    if length(getAttrDclAll(fName, top.env)) > 1
    then [err(top.location, "Attribute '" ++ fName ++ "' is already bound.")]
    else [];	

  top.errors := te.errors ++ tl.errors ++ tl.errorsTyVars;
}

