grammar silver:definition:core;

nonterminal ProductionSignature with config, grammarName, file, env, location, pp, errors, defs, namedSignature, signatureName;
nonterminal ProductionLHS with config, grammarName, file, env, location, pp, errors, defs, outputElement;
nonterminal ProductionRHS with config, grammarName, file, env, location, pp, errors, defs, inputElements;
nonterminal ProductionRHSElem with config, grammarName, file, env, location, pp, errors, defs, inputElements, deterministicCount;

{--
 - Used to help give names to children, when names are omitted.
 -}
inherited attribute deterministicCount :: Integer;

{--
 - Given to signature syntax, so as to construct a named signature representation.
 -}
inherited attribute signatureName :: String;

concrete production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  top.pp = "abstract production " ++ id.pp ++ "\n" ++ ns.pp ++ "\n" ++ body.pp; 
  top.location = loc(top.file, $1.line, $1.column);

  production fName :: String = top.grammarName ++ ":" ++ id.name;
  production namedSig :: NamedSignature = ns.namedSignature;

  top.defs = prodDef(top.grammarName, id.location, namedSig) ::
    if null(body.productionAttributes) then []
    else [prodOccursDef(top.grammarName, id.location, namedSig, body.productionAttributes)];

  top.errors <-
        if length(getValueDclAll(fName, top.env)) > 1
        then [err(top.location, "Value '" ++ fName ++ "' is already bound.")]

        -- TODO: Narrow this down to just a list of productions before deciding to error.
        else if length(getValueDclAll(id.name, top.env)) > 1
        then [err(top.location, "Production " ++ id.pp ++ " shares a name with another production from an imported grammar. Either this production is meant to be an aspect, or you should use 'import ... with " ++ id.pp ++ " as ...' to change the other production's apparent name.")]
        else [];
  
  top.errors <-
        if length(body.uniqueSignificantExpression) > 1
        then [err(top.location, "Production '" ++ id.name ++ "' has more than one forward declaration.")]
        else [];

  top.errors := ns.errors ++ body.errors;

  production attribute sigDefs :: [Def] with ++;
  sigDefs := ns.defs;

  ns.signatureName = fName;
  ns.env = newScopeEnv(sigDefs, top.env);

  local attribute prodAtts :: [Def];
  prodAtts = defsFromPADcls(getProdAttrs(fName, top.env), namedSig);

  body.env = newScopeEnv(body.defs ++ sigDefs, newScopeEnv(prodAtts, top.env));
  body.signature = namedSig;
  body.blockContext = productionContext();
}

concrete production productionSignature
top::ProductionSignature ::= lhs::ProductionLHS '::=' rhs::ProductionRHS 
{
  top.pp = lhs.pp ++ " ::= " ++ rhs.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.defs = lhs.defs ++ rhs.defs;
  top.errors := lhs.errors ++ rhs.errors;

  top.namedSignature = namedSignature(top.signatureName, rhs.inputElements, lhs.outputElement);
}

concrete production productionLHS
top::ProductionLHS ::= id::Name '::' t::Type
{
  top.pp = id.pp ++ "::" ++ t.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.outputElement = namedSignatureElement(id.name, t.typerep);

  top.defs = [lhsDef(top.grammarName, t.location, id.name, t.typerep)];

  top.errors <-
    if length(getValueDclInScope(id.name, top.env)) > 1
    then [err(top.location, "Value '" ++ id.name ++ "' is already bound.")]
    else [];	

  top.errors := t.errors;
}

concrete production productionRHSNil
top::ProductionRHS ::=
{
  top.pp = "";
  top.location = loc(top.file,-1,-1);

  top.defs = [];
  top.errors := [];

  top.inputElements = [];
}

concrete production productionRHSCons
top::ProductionRHS ::= h::ProductionRHSElem t::ProductionRHS
{
  top.pp = h.pp ++ " " ++ t.pp;
  top.location = h.location;

  top.defs = h.defs ++ t.defs;
  top.errors := h.errors ++ t.errors;

  top.inputElements = h.inputElements ++ t.inputElements;
  h.deterministicCount = length(t.inputElements);
}

concrete production productionRHSElem
top::ProductionRHSElem ::= id::Name '::' t::Type
{
  top.pp = id.pp ++ "::" ++ t.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.inputElements = [namedSignatureElement(id.name, t.typerep)];

  top.defs = [childDef(top.grammarName, t.location, id.name, t.typerep)];

  top.errors <-
    if length(getValueDclInScope(id.name, top.env)) > 1 
    then [err(top.location, "Value '" ++ id.name ++ "' is already bound.")]
    else [];	

  top.errors := t.errors;
}

concrete production productionRHSElemType
top::ProductionRHSElem ::= t::Type
{
  top.pp = t.pp;
  top.location = t.location;

  forwards to productionRHSElem(nameIdLower(terminal(IdLower_t, "_G_" ++ toString(top.deterministicCount))), terminal(ColonColon_t, "::"), t);
}

