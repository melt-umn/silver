{-
concrete production attributeDclSyn
top::AGDcl ::= 'functor' 'attribute' a::Name ';'
{
  top.pp = "functor attribute " ++ a.pp ++ ";";

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  top.defs = [synDef(top.grammarName, a.location, fName, tl.freeVariables, te.typerep)];

  tl.initialEnv = top.env;
  tl.env = tl.envBindingTyVars;
  te.env = tl.envBindingTyVars;
  
  top.errors <-
    if length(getAttrDclAll(fName, top.env)) > 1
    then [err(a.location, "Attribute '" ++ fName ++ "' is already bound.")]
    else [];	

  top.errors := te.errors ++ tl.errors ++ tl.errorsTyVars;
}
-}
