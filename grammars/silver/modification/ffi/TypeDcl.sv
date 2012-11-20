grammar silver:modification:ffi;

imports silver:definition:core;
imports silver:definition:env;
imports silver:definition:type;
imports silver:definition:type:syntax;
imports silver:util;

imports silver:modification:typedecl;

-- TODO: this should provide some sort of translation type
-- but right now, we don't. Phooey.

concrete production ffiTypeDcl
top::AGDcl ::= 'type' id::Name botl::BracketedOptTypeList 'foreign' ';' -- '{' ffidefs::FFIDefs '}'
{
  top.pp = "type " ++ id.pp ++ botl.pp ++ " foreign;"; -- "{\n" ++ ffidefs.pp ++ "}";
  top.location = loc(top.file, $1.line, $1.column);
  
  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ id.name;

  production attribute tl :: Decorated TypeList;
  tl = botl.typelist;

  top.defs = [typeAliasDef(top.grammarName, id.location, fName, tl.freeVariables, foreignTypeExp(fName, tl.types))];

  top.errors := tl.errors;
  
  -- Put the variables listed on the rhs in the environment FOR TL ONLY, so they're all "declared"
  botl.env = newScopeEnv( addNewLexicalTyVars(top.grammarName, top.location, tl.lexicalTypeVariables),
                        top.env);
  top.errors <- if containsDuplicates(tl.lexicalTypeVariables)
                then [err(top.location, "Duplicate type variable names listed")]
                else [];
  
  -- Make sure only type variables show up in the tl
  top.errors <- tl.errorsTyVars;
  
  -- Redefinition check of the name
  top.errors <- 
       if length(getTypeDclAll(fName, top.env)) > 1 
       then [err(top.location, "Type '" ++ fName ++ "' is already bound.")]
       else [];

  top.errors <-
       if isLower(substring(0,1,id.name))
       then [err(id.location, "Types must be capitalized. Invalid foreign type name " ++ id.name)]
       else [];
}

