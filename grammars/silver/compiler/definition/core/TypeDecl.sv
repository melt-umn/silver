grammar silver:compiler:definition:core;

import silver:compiler:definition:flow:syntax;

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

concrete production inhSetConstBaseDecl
top::AGDcl ::= 'inhset' id::Name ':=' InhSetLCurly_t inhs::FlowSpecInhs '}' ';'
{
  top.unparse = s"inhset ${id.unparse} := {${inhs.unparse}};";

  production attribute fName :: String = top.grammarName ++ ":" ++ id.name;
  
  top.defs := [inhSetConstDef(top.grammarName, id.location, fName)];

  inhs.onNt = errorType();
  
  -- Redefinition check of the name
  top.errors <- 
       if length(getTypeDclAll(fName, top.env)) > 1 
       then [err(id.location, "Type '" ++ fName ++ "' is already bound.")]
       else [];

  top.errors <-
       if isLower(substring(0,1,id.name))
       then [err(id.location, "Types must be capitalized. Invalid inherited attribute set type constant name " ++ id.name)]
       else [];
}

concrete production inhSetConstContribDecl
top::AGDcl ::= 'inhset' q::QNameType '<-' InhSetLCurly_t inhs::FlowSpecInhs '}' ';'
{
  top.unparse = s"inhset ${q.unparse} <- {${inhs.unparse}};";

  inhs.onNt = errorType();

  top.errors <- q.lookupType.errors;
  top.errors <-
    if !q.lookupType.found || q.lookupType.dcl.isInhSetConst
    then []
    else [err(top.location, q.name ++ " is not an inherited attribute set type constant.")];
}
