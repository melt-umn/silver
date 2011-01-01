grammar silver:definition:core;

concrete production attributeDclInhEmpty
top::AGDcl ::= 'inherited' 'attribute' a::Name '::' te::Type ';'
{
  forwards to attributeDclInh($1,$2,a,'<',typeListNone(),'>', $4, te, $6);
}

concrete production attributeDclInh
top::AGDcl ::= 'inherited' 'attribute' a::Name '<' tl::TypeList '>' '::' te::Type ';'
{
  top.pp = "inherited attribute " ++ a.pp ++ " :: " ++ te.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  top.defs = addInhDcl(top.grammarName, a.location, fName, tl.freeVariables, te.typerep, emptyDefs());

--------
  tl.env = newScopeEnv( addNewLexicalTyVars(top.grammarName, top.location, tl.lexicalTypeVariables),
                        top.env);
  te.env = tl.env;
  top.errors <- if containsDuplicates(tl.lexicalTypeVariables)
                then [err(top.location, "Duplicate type variable names listed")]
                else [];
  top.errors <- tl.errorsTyVars;
--------
  
  top.errors <-
        if length(getAttrDcl(fName, top.env)) > 1
        then [err(top.location, "Attribute '" ++ fName ++ "' is already bound.")]
        else [];	

  top.errors := te.errors ++ tl.errors;

  forwards to agDclDefault();
}

concrete production attributeDclSynEmpty
top::AGDcl ::= 'synthesized' 'attribute' a::Name '::' te::Type ';'
{
  forwards to attributeDclSyn($1,$2,a,'<',typeListNone(),'>', $4, te, $6);
}

concrete production attributeDclSyn
top::AGDcl ::= 'synthesized' 'attribute' a::Name '<' tl::TypeList '>' '::' te::Type ';'
{
  top.pp = "synthesized attribute " ++ a.pp ++ " :: " ++ te.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  top.defs = addSynDcl(top.grammarName, a.location, fName, tl.freeVariables, te.typerep, emptyDefs());

--------
  tl.env = newScopeEnv( addNewLexicalTyVars(top.grammarName, top.location, tl.lexicalTypeVariables),
                        top.env);
  te.env = tl.env;
  top.errors <- if containsDuplicates(tl.lexicalTypeVariables)
                then [err(top.location, "Duplicate type variable names listed")]
                else [];
  top.errors <- tl.errorsTyVars;
--------
  
  top.errors <-
        if length(getAttrDcl(fName, top.env)) > 1
        then [err(top.location, "Attribute '" ++ fName ++ "' is already bound.")]
        else [];	

  top.errors := te.errors ++ tl.errors;
  top.warnings := [];

  forwards to agDclDefault();
}

