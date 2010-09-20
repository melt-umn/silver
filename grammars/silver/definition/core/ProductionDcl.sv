grammar silver:definition:core;

concrete production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  top.pp = "abstract production " ++ id.pp ++ "\n" ++ ns.pp ++ "\n" ++ body.pp; 
  top.location = loc(top.file, $1.line, $1.column);

  top.moduleNames = [];

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ id.name;

  production attribute namedSig :: Decorated NamedSignature;
  namedSig = namedSignatureDcl(fName, ns.inputElements, ns.outputElement);

  top.defs = addProdDcl(top.grammarName, id.location, namedSig,
               body.productionAttributes);

  local attribute er2 :: [Decorated Message];
  er2 = if length(getValueDclAll(fName, top.env)) > 1
        then [err(top.location, "Value '" ++ fName ++ "' is already bound.")]

        -- TODO: Narrow this down to just a list of productions before deciding to error.
        else if length(getValueDcl(id.name, top.env)) > 1 && null(er2)
        then [err(top.location, "Production " ++ id.pp ++ " shares a name with another production from an imported grammar. Either this production is meant to be an aspect, or you should use 'import ... with " ++ id.pp ++ " as ...' to change the other production's apparent name.")]
        else [];
  
  top.errors := er2 ++ ns.errors ++ body.errors;
  top.warnings := [];

  production attribute sigDefs :: Defs with appendDefs;
  sigDefs := ns.defs;

  ns.env = newScopeEnv(sigDefs, top.env);

  local attribute prodAtts :: Defs;
  prodAtts = valueDefsFromDcls(getProdAttrs(fName, top.env));

  body.env = newScopeEnv(appendDefs(body.defs, sigDefs), newScopeEnv(prodAtts, top.env));
  body.signature = namedSig;
}

concrete production productionSignatureEmptyRHS
top::ProductionSignature ::= lhs::ProductionLHS '::='
{
  top.pp = lhs.pp ++ " ::= ";
  top.location = loc(top.file, $2.line, $2.column);
  
  top.defs = lhs.defs;
  top.errors := lhs.errors;
  top.warnings := [];

  top.inputElements = [];
  top.outputElement = lhs.outputElement;
}

concrete production productionSignature
top::ProductionSignature ::= lhs::ProductionLHS '::=' rhs::ProductionRHS 
{
  top.pp = lhs.pp ++ " ::= " ++ rhs.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.defs = appendDefs(lhs.defs, rhs.defs);
  top.errors := lhs.errors ++ rhs.errors;
  top.warnings := [];

  top.inputElements = rhs.inputElements;
  top.outputElement = lhs.outputElement;
}

concrete production productionLHS
top::ProductionLHS ::= id::Name '::' t::Type
{
  top.pp = id.pp ++ "::" ++ t.pp;
  top.location = loc(top.file, $2.line, $2.column);

  production attribute fName :: String;
  fName = id.name;

  top.outputElement = namedSignatureElement(id.name, t.typerep);

  -- TODO: think about this. lhs doesn't really have an fName.
  top.defs = addLhsDcl(top.grammarName, t.location, fName, t.typerep, emptyDefs());

  top.errors <-
       if length(getValueDclAll(fName, top.env)) > 1
       then [err(top.location, "Value '" ++ fName ++ "' is already bound.")]
       else [];	

  top.errors := t.errors;
  top.warnings := [];
}

concrete production productionRHSSingle
top::ProductionRHS ::= rhs::ProductionRHSElem
{
  top.pp = rhs.pp;
  top.location = rhs.location;

  top.defs = rhs.defs;
  top.errors := rhs.errors;
  top.warnings := [];

  top.inputElements = rhs.inputElements;
  rhs.deterministicCount = 0;
}

concrete production productionRHSCons
top::ProductionRHS ::= h::ProductionRHSElem t::ProductionRHS
{
  top.pp = h.pp ++ " " ++ t.pp;
  top.location = h.location;

  top.defs = appendDefs(h.defs, t.defs);
  top.errors := h.errors ++ t.errors;
  top.warnings := [];

  top.inputElements = h.inputElements ++ t.inputElements;
  h.deterministicCount = length(t.inputElements);
}

-- used to avoid using gen int when not given an explicit name
inherited attribute deterministicCount :: Integer occurs on ProductionRHSElem;

concrete production productionRHSElem
top::ProductionRHSElem ::= id::Name '::' t::Type
{
  top.pp = id.pp ++ "::" ++ t.pp;
  top.location = loc(top.file, $2.line, $2.column);

  production attribute fName :: String;
  fName = id.name;

  top.inputElements = [namedSignatureElement(id.name, t.typerep)];

  -- TODO: think about this. child doesn't really have an fName.
  top.defs = addChildDcl(top.grammarName, t.location, fName, t.typerep, emptyDefs());

  top.errors <-
       if length(getValueDclAll(fName, top.env)) > 1
       then [err(top.location, "Value '" ++ fName ++ "' is already bound.")]
       else [];	

  top.errors := t.errors;
  top.warnings := [];
}

concrete production productionRHSElemType
top::ProductionRHSElem ::= t::Type
{
  top.pp = t.pp;
  top.location = t.location;

  forwards to productionRHSElem(nameId(terminal(Id_t, "_G_" ++ toString(top.deterministicCount))), terminal(HasType_t, "::="), t);
}
