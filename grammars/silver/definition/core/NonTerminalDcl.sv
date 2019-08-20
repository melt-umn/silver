grammar silver:definition:core;

concrete production nonterminalDcl
top::AGDcl ::= quals::NTDeclQualifiers 'nonterminal' id::Name tl::BracketedOptTypeExprs ';'
{
  top.unparse = "nonterminal^ " ++ id.unparse ++ tl.unparse ++ ";";

  local l :: Location = top.location;

  local originLinkTE :: TypeExpr = nominalTypeExpr(qNameTypeId(terminal(IdUpper_t, "OriginLink", l),
    location=l), botlNone(location=l), location=l);
  local ntTE :: TypeExpr = nominalTypeExpr(qNameTypeId(terminal(IdUpper_t, id.name, l),
    location=l), tl, location=l);

  local nextLinkNothingImpl :: Expr = Silver_Expr{nothing()};
  local nextLinkSomethingImpl :: Expr = Silver_Expr{just(n.origininfo)};
  local hasOrigins :: Boolean = findNamedSigElem("silver:modification:origintracking:childruntime:origininfo",
      annotationsForNonterminal(nonterminalType(top.grammarName ++ ":" ++ id.name, tl.types), top.env), 0) != -1;
  local selectedImpl :: Expr = if hasOrigins then nextLinkSomethingImpl else nextLinkNothingImpl;
  local implStmt :: ProductionStmt = attributeDef(concreteDefLHS(qName(l, "top"), location=l),
    '.', qNameAttrOccur(qName(l, "nextOrigin"), location=l), '=', nextLinkSomethingImpl, ';', location=l);

  local newdcl :: AGDcl = productionDcl('abstract', 'production', name("originLink" ++ id.name, l),
    productionSignature(productionLHS(name("top", l), '::', originLinkTE, location=l), '::=',
      productionRHSCons(productionRHSElem(name("n", l), '::', ntTE, location=l), productionRHSNil(location=l),
        location=l), location=l), productionBody('{',
          productionStmtsNil(location=l), '}', location=l), location=l);

  forwards to appendAGDcl(
    noWrapperNonterminalDcl(quals, 'nonterminal', id, tl, ';', location=l),
    newdcl, location=l);
}

abstract production noWrapperNonterminalDcl
top::AGDcl ::= quals::NTDeclQualifiers 'nonterminal' id::Name tl::BracketedOptTypeExprs ';'
{
  top.unparse = "nonterminal " ++ id.unparse ++ tl.unparse ++ ";";

  production fName :: String = top.grammarName ++ ":" ++ id.name;
  
  -- tl.freeVariables is our order list of the bound types for this nonterminal.
  top.defs = [ntDef(top.grammarName,
                    id.location,
                    fName,
                    tl.freeVariables,
                    nonterminalType(fName, tl.types),
                    quals.closed,
                    quals.tracked)];
  -- TODO: It's probably reasonable to skip listing
  -- tl.freeVariables, and the Type. Assuming we have a proper ntDcl.
  -- And we should consider recording the exact concrete names used... might be nice documentation to use
  

  -- Here we ensure that the type list contains only type *variables*
  top.errors := tl.errors ++ tl.errorsTyVars ++ quals.errors;
  
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

nonterminal NTDeclQualifiers with location, errors;

synthesized attribute closed :: Boolean occurs on NTDeclQualifiers;
synthesized attribute tracked :: Boolean occurs on NTDeclQualifiers;

concrete production nilNTQualifier
top::NTDeclQualifiers ::=
{
  -- This controls the default closed-ness and tracked-ness of nonterminals
  top.closed = false;
  top.tracked = false;

  top.errors := [];
}

concrete production closedNTQualifier
top::NTDeclQualifiers ::= 'closed' rest::NTDeclQualifiers
{
  top.closed = true;
  top.tracked = rest.tracked;

  top.errors := rest.errors;
  top.errors <- if rest.closed then [err(top.location, "Duplicate 'closed' qualifier")] else [];
}

concrete production trackedNTQualifier
top::NTDeclQualifiers ::= 'tracked' rest::NTDeclQualifiers
{
  top.closed = rest.closed;
  top.tracked = true;

  top.errors := rest.errors;
  top.errors <- if rest.tracked then [err(top.location, "Duplicate 'tracked' qualifier")] else [];
}

