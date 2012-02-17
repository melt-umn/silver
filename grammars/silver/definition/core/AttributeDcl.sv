grammar silver:definition:core;

concrete production attributeDclInh
top::AGDcl ::= 'inherited' 'attribute' a::Name botl::BracketedOptTypeList '::' te::Type ';'
{
  top.pp = "inherited attribute " ++ a.pp ++ botl.pp ++ " :: " ++ te.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  production attribute tl :: Decorated TypeList;
  tl = botl.typelist;

  top.defs = addInhDcl(top.grammarName, a.location, fName, tl.freeVariables, te.typerep, emptyDefs());

--------
  botl.env = newScopeEnv( addNewLexicalTyVars(top.grammarName, top.location, tl.lexicalTypeVariables),
                        top.env);
  te.env = tl.env;
  top.errors <- if containsDuplicates(tl.lexicalTypeVariables)
                then [err(top.location, "Duplicate type variable names listed")]
                else [];
  top.errors <- tl.errorsTyVars;
--------
  
  top.errors <-
        if length(getAttrDclAll(fName, top.env)) > 1
        then [err(top.location, "Attribute '" ++ fName ++ "' is already bound.")]
        else [];	

  top.errors := te.errors ++ tl.errors;

  forwards to defaultAGDcl();
}

concrete production attributeDclSyn
top::AGDcl ::= 'synthesized' 'attribute' a::Name botl::BracketedOptTypeList '::' te::Type ';'
{
  top.pp = "synthesized attribute " ++ a.pp ++ botl.pp ++ " :: " ++ te.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  production attribute tl :: Decorated TypeList;
  tl = botl.typelist;

  top.defs = addSynDcl(top.grammarName, a.location, fName, tl.freeVariables, te.typerep, emptyDefs());

--------
  botl.env = newScopeEnv( addNewLexicalTyVars(top.grammarName, top.location, tl.lexicalTypeVariables),
                        top.env);
  te.env = tl.env;
  top.errors <- if containsDuplicates(tl.lexicalTypeVariables)
                then [err(top.location, "Duplicate type variable names listed")]
                else [];
  top.errors <- tl.errorsTyVars;
--------
  
  top.errors <-
        if length(getAttrDclAll(fName, top.env)) > 1
        then [err(top.location, "Attribute '" ++ fName ++ "' is already bound.")]
        else [];	

  top.errors := te.errors ++ tl.errors;
  top.warnings := [];

  forwards to defaultAGDcl();
}

