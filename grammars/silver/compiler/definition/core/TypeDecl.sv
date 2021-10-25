grammar silver:compiler:definition:core;

concrete production typeAliasDecl
top::AGDcl ::= 'type' id::Name tl::BracketedOptTypeExprs '=' te::TypeExpr ';'
{
  top.unparse = "type " ++ id.unparse ++ tl.unparse ++ "=" ++ te.unparse ++ ";";

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ id.name;
  
  top.defs := [typeAliasDef(top.grammarName, id.location, fName, tl.freeVariables, te.typerep)];

  top.errors <- tl.errorsTyVars;
  
  tl.initialEnv = top.env;
  tl.env = tl.envBindingTyVars;
  te.env = tl.envBindingTyVars;
  
  -- Redefinition check of the name
  top.errors <- 
       if length(getTypeDclAll(fName, top.env)) > 1 
       then [err(id.location, "Type '" ++ fName ++ "' is already bound.")]
       else [];

  top.errors <-
       if isLower(substring(0,1,id.name))
       then [err(id.location, "Types must be capitalized. Invalid type alias name " ++ id.name)]
       else [];
}

