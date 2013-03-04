grammar silver:modification:autocopyattr;

import silver:util;

terminal AutoCopy_kwd 'autocopy' lexer classes {KEYWORD};

concrete production attributeDclAuto
top::AGDcl ::= 'autocopy' 'attribute' a::Name tl::BracketedOptTypeList '::' te::Type ';'
{
  top.pp = "autocopy attribute " ++ a.pp ++ tl.pp ++ " :: " ++ te.pp ++ ";";

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  top.defs = [autocopyDef(top.grammarName, a.location, fName, tl.freeVariables, te.typerep)];

  tl.initialEnv = top.env;
  tl.env = tl.envBindingTyVars;
  te.env = tl.envBindingTyVars;

  top.errors := tl.errors ++ te.errors ++ tl.errorsTyVars;
  
  top.errors <-
        if length(getAttrDclAll(fName, top.env)) > 1
        then [err(a.location, "Attribute '" ++ fName ++ "' is already bound.")]
        else [];
  
  -- AUTOCOPY IS UNSOUND OTHERWISE
  -- We don't know just from the "occurs on" bit whether the types are the same and its safe to autocopy, so...
  top.errors <-
        if !null(tl.types)
        then [err(tl.location, "Autocopy attributes cannot be parameterized by type variables!")]
        else [];

  forwards to attributeDclInh('inherited', $2, a, tl, $5, te, $7, location=top.location);
}

