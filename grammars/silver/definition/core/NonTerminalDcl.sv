grammar silver:definition:core;

autocopy attribute nonterminalName :: String;

concrete production nonterminalDcl
top::AGDcl ::= cl::ClosedOrNot 'nonterminal' id::Name tl::BracketedOptTypeExprs nm::NonterminalModifiers ';'
{
  top.unparse = "nonterminal " ++ id.unparse ++ tl.unparse ++ " " ++ nm.unparse ++ ";";

  production fName :: String = top.grammarName ++ ":" ++ id.name;
  nm.nonterminalName = fName;
  
  -- tl.freeVariables is our order list of the bound types for this nonterminal.
  top.defs := [cl.whichDcl(top.grammarName, id.location, fName, tl.freeVariables, nonterminalType(fName, tl.types))];
  -- TODO: It's probably reasonable to skip listing
  -- tl.freeVariables, and the Type. Assuming we have a proper ntDcl.
  -- And we should consider recording the exact concrete names used... might be nice documentation to use
  

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

-- This feels a bit hackish.
nonterminal ClosedOrNot with location, whichDcl;

synthesized attribute whichDcl :: (Def ::= String Location String [TyVar] Type);

concrete production openNt
top::ClosedOrNot ::=
{
  top.whichDcl = ntDef;
}

concrete production closedNt
top::ClosedOrNot ::= 'closed'
{
  top.whichDcl = closedNtDef;
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

