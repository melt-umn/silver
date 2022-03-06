grammar silver:compiler:definition:core;

autocopy attribute nonterminalName :: String;

concrete production nonterminalDcl
top::AGDcl ::= quals::NTDeclQualifiers 'nonterminal' id::Name tl::BracketedOptTypeExprs nm::NonterminalModifiers ';'
{
  top.unparse = quals.unparse ++ "nonterminal " ++ id.unparse ++ tl.unparse ++ " " ++ nm.unparse ++ ";";

  production fName :: String = top.grammarName ++ ":" ++ id.name;
  nm.nonterminalName = fName;
  
  -- tl.freeVariables is our order list of the bound types for this nonterminal.
  top.defs := [ntDef(top.grammarName,
                    id.location,
                    fName,
                    map((.kindrep), tl.types),
                    quals.closed,
                    quals.tracked)];
  -- TODO: It's probably reasonable to skip listing
  -- tl.freeVariables, and the Type. Assuming we have a proper ntDcl.
  -- And we should consider recording the exact concrete names used... might be nice documentation to use
  
  top.errors <- quals.errors;

  -- Here we ensure that the type list contains only type *variables*
  top.errors <- tl.errorsTyVars;
  
  -- Here we bind those type variables.
  tl.initialEnv = top.env;
  tl.env = tl.envBindingTyVars;
  
  -- Redefinition check of the name
  top.errors <- 
    if length(getTypeDclAll(fName, top.env)) > 1 
    then [err(id.location, "Type '" ++ fName ++ "' is already bound.")]
    else [];

  top.errors <-
    if isLower(substring(0,1,id.name))
    then [err(id.location, "Types must be capitalized. Invalid nonterminal name " ++ id.name)]
    else [];
}

nonterminal NTDeclQualifiers with location, unparse, errors;

synthesized attribute closed :: Boolean occurs on NTDeclQualifiers;
synthesized attribute tracked :: Boolean occurs on NTDeclQualifiers;

concrete production nilNTQualifier
top::NTDeclQualifiers ::=
{
  -- This controls the default closed-ness and tracked-ness of nonterminals
  top.closed = false;
  top.tracked = false;

  top.errors := [];
  top.unparse = "";
}

concrete production closedNTQualifier
top::NTDeclQualifiers ::= 'closed' rest::NTDeclQualifiers
{
  top.closed = true;
  top.tracked = rest.tracked;

  top.errors := rest.errors;
  top.errors <- if rest.closed then [err(top.location, "Duplicate 'closed' qualifier")] else [];
  top.unparse = "closed " ++ rest.unparse;
}

concrete production trackedNTQualifier
top::NTDeclQualifiers ::= 'tracked' rest::NTDeclQualifiers
{
  top.closed = rest.closed;
  top.tracked = true;

  top.errors := rest.errors;
  top.errors <- if rest.tracked then [err(top.location, "Duplicate 'tracked' qualifier")] else [];
  top.unparse = "tracked " ++ rest.unparse;
}

nonterminal NonterminalModifiers with config, location, unparse, errors, env, nonterminalName; -- 0 or some
nonterminal NonterminalModifierList with config, location, unparse, errors, env, nonterminalName; -- 1 or more
closed nonterminal NonterminalModifier with config, location, unparse, errors, env, nonterminalName; -- 1

propagate errors on NonterminalModifiers, NonterminalModifierList, NonterminalModifier;

concrete production nonterminalModifiersNone
top::NonterminalModifiers ::=
{
  top.unparse = "";
}
concrete production nonterminalModifierSome
top::NonterminalModifiers ::= nm::NonterminalModifierList
{
  top.unparse = nm.unparse;
}

concrete production nonterminalModifierSingle
top::NonterminalModifierList ::= nm::NonterminalModifier
{
  top.unparse = nm.unparse;
}
concrete production nonterminalModifiersCons
top::NonterminalModifierList ::= h::NonterminalModifier ',' t::NonterminalModifierList
{
  top.unparse = h.unparse ++ ", " ++ t.unparse;
}

