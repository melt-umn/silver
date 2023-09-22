grammar silver:compiler:definition:core;

concrete production attributeDclInh
top::AGDcl ::= 'inherited' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.unparse = "inherited attribute " ++ a.unparse ++ tl.unparse ++ " :: " ++ te.unparse ++ ";";

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  top.defs := [inhDef(top.grammarName, a.nameLoc, fName, tl.freeVariables, te.typerep)];

  tl.initialEnv = top.env;
  tl.env = tl.envBindingTyVars;
  te.env = tl.envBindingTyVars;

  top.errors <-
    if length(getAttrDclAll(fName, top.env)) > 1
    then [errFromOrigin(a, "Attribute '" ++ fName ++ "' is already bound.")]
    else [];

  top.errors <- tl.errorsTyVars;
}

concrete production attributeDclSyn
top::AGDcl ::= 'synthesized' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.unparse = "synthesized attribute " ++ a.unparse ++ tl.unparse ++ " :: " ++ te.unparse ++ ";";

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  top.defs := [synDef(top.grammarName, a.nameLoc, fName, tl.freeVariables, te.typerep)];

  tl.initialEnv = top.env;
  tl.env = tl.envBindingTyVars;
  te.env = tl.envBindingTyVars;
  
  top.errors <-
    if length(getAttrDclAll(fName, top.env)) > 1
    then [errFromOrigin(a, "Attribute '" ++ fName ++ "' is already bound.")]
    else [];

  top.errors <- tl.errorsTyVars;
}

concrete production attributeDclTrans
top::AGDcl ::= 'translation' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.unparse = "translation attribute " ++ a.unparse ++ tl.unparse ++ " :: " ++ te.unparse ++ ";";

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  top.defs := [transDef(top.grammarName, a.nameLoc, fName, tl.freeVariables, te.typerep)];

  tl.initialEnv = top.env;
  tl.env = tl.envBindingTyVars;
  te.env = tl.envBindingTyVars;
  
  top.errors <-
    if length(getAttrDclAll(fName, top.env)) > 1
    then [errFromOrigin(a, "Attribute '" ++ fName ++ "' is already bound.")]
    else [];

  top.errors <- tl.errorsTyVars;
}

