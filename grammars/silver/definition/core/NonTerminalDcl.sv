grammar silver:definition:core;

concrete production nonterminalDcl
top::AGDcl ::= 'nonterminal' id::Name botl::BracketedOptTypeList ';'
{
  production attribute extraDcls :: AGDcl with appendAGDcl;
  extraDcls := defaultNonterminalDcl(id, botl);

  forwards to extraDcls;
}

abstract production defaultNonterminalDcl
top::AGDcl ::= id::Name botl::BracketedOptTypeList
{
  top.pp = "nonterminal " ++ id.pp ++ botl.pp ++ ";";
  top.location = id.location;

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ id.name;
  
  production attribute tl :: Decorated TypeList;
  tl = botl.typelist;

  {- Going over this carefully:
    * tl consists only of type variables, since we include tl.errorsTyVar below.
    * we add all type variables (t.lexicalTypeVariables) to the environment FOR TL ONLY
    * we construct the type of 'id' as a nonterminalTypeExp
    * we get tl.freeVariables (which must be IN ORDER) and list them as the bound variables of this declaration.
  -}
  
  top.defs = addNtDcl(top.grammarName, id.location, fName, tl.freeVariables, nonterminalTypeExp(fName, tl.types), emptyDefs());

  -- Include normal binding errors.
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
       then [err(id.location, "Types must be capitalized. Invalid nonterminal name " ++ id.name)]
       else [];

  forwards to defaultAGDcl();
}

