grammar silver:compiler:definition:core;

tracked nonterminal ProductionImplements with config, grammarName, env, unparse, errors, implementsSig;
tracked nonterminal ProductionSignature with config, grammarName, env, unparse, errors, defs, constraintDefs, occursDefs, namedSignature, signatureName, implementedSig<NamedSignature>;
tracked nonterminal ProductionLHS with config, grammarName, env, unparse, errors, defs, outputElement, implementedSig<NamedSignatureElement>;
tracked nonterminal ProductionRHS with config, grammarName, env, unparse, errors, defs, inputElements, elementCount, implementedSig<NamedSignatureElements>;
tracked nonterminal ProductionRHSElem with config, grammarName, env, unparse, errors, defs, inputElements, deterministicCount, implementedSig<NamedSignatureElement>;

flowtype forward {env, signatureName} on ProductionSignature;
flowtype forward {env} on ProductionLHS, ProductionRHS;
flowtype forward {deterministicCount, env} on ProductionRHSElem;

flowtype decorate {forward, grammarName, flowEnv, implementedSig} on ProductionSignature, ProductionLHS, ProductionRHS, ProductionRHSElem;

propagate config, grammarName, errors on
  ProductionImplements, ProductionSignature, ProductionLHS, ProductionRHS, ProductionRHSElem;
propagate env on ProductionImplements;
propagate env, defs on ProductionRHS;

synthesized attribute implementsSig::Maybe<NamedSignature>;
inherited attribute implementedSig<a>::Maybe<a>;

{--
 - Used to help give names to children, when names are omitted.
 -}
inherited attribute deterministicCount :: Integer;
synthesized attribute elementCount::Integer;

{--
 - Given to signature syntax, so as to construct a named signature representation.
 -}
inherited attribute signatureName :: String;

{--
 - Defs from the constraint list are passed seperately from the rest of the signature defs,
 - to avoid an infinite recursion.
 -}
synthesized attribute constraintDefs::[Def];

{--
 - The signature names of the production body currently being parsed, for use in reporting semantic tokens.
 -}
parser attribute sigNames::[String] action { sigNames = []; };

concrete production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name d::ProductionImplements ns::ProductionSignature body::ProductionBody
{
  top.unparse = "abstract production " ++ id.unparse ++ " " ++ d.unparse ++ "\n" ++ ns.unparse ++ "\n" ++ body.unparse; 

  production fName :: String = top.grammarName ++ ":" ++ id.name;
  production namedSig :: NamedSignature = ns.namedSignature;

  top.defs := prodDef(top.grammarName, id.nameLoc, namedSig, map((.fullName), d.implementsSig), length(body.forwardExpr) > 0) ::
    if null(body.productionAttributes) then []
    else [prodOccursDef(top.grammarName, id.nameLoc, namedSig, body.productionAttributes)];

  -- Other productions on the same nonterminal with the same name:
  local sameNTProds::[ValueDclInfo] = filter(
    \ v::ValueDclInfo ->
      case v of
      | prodDcl(ns, _, _) -> ns.outputElement.typerep == namedSig.outputElement.typerep
      | _ -> false
      end,
    getValueDclAll(id.name, top.env));
  top.errors <-
    if length(getValueDclAll(fName, top.env)) > 1
    then [errFromOrigin(id, "Value '" ++ fName ++ "' is already bound.")]
    else if length(sameNTProds) > 1
    then [errFromOrigin(top, "Production " ++ id.name ++ " shares a name with another production from an imported grammar. Either this production is meant to be an aspect, or you should use 'import ... with " ++ id.name ++ " as ...' to change the other production's apparent name.")]
    else [];
  
  top.errors <-
    if length(body.forwardExpr) > 1
    then [errFromOrigin(top, "Production '" ++ id.name ++ "' has more than one forward declaration.")]
    else [];
  
  top.errors <-
    if length(body.undecorateExpr) > 1
    then [errFromOrigin(top, "Production '" ++ id.name ++ "' has more than one undecorate declaration.")]
    else [];

  top.errors <-
    if isLower(substring(0,1,id.name)) then []
    else [wrnFromOrigin(id, s"(future) ${id.name}: productions may be required to begin with a lower-case letter.")];

  production attribute sigDefs :: [Def] with ++;
  sigDefs := ns.defs;

  d.env = top.env;

  ns.signatureName = fName;
  ns.env = newScopeEnv(sigDefs, top.env);
  ns.implementedSig = d.implementsSig;

  local attribute prodAtts :: [Def];
  prodAtts = defsFromPADcls(getProdAttrs(fName, top.env), namedSig);

  body.env = occursEnv(ns.occursDefs, newScopeEnv(body.defs ++ sigDefs ++ ns.constraintDefs ++ prodAtts, top.env));
  body.frame = productionContext(namedSig, myFlowGraph, sourceGrammar=top.grammarName); -- graph from flow:env
} action {
  insert semantic token IdFnProdDcl_t at id.nameLoc;
  sigNames = [];
}

concrete production productionImplementsSome
top::ProductionImplements ::= 'implements' id::QName
{
  top.unparse = "implements " ++ id.unparse;
  top.implementsSig =
    if id.lookupType.found
    then just(id.lookupType.dcl.dispatchSignature)
    else nothing();

  top.errors <- id.lookupType.errors;
  top.errors <- if !id.lookupType.found then [] else
    case id.lookupType.dcl of
    | dispatchDcl(_) -> []
    | _ -> [errFromOrigin(id, "Type '" ++ id.unparse ++ "' is not a dispatch signature type.")]
    end;
}

concrete production productionImplementsNone
top::ProductionImplements ::=
{
  top.unparse = "";
  top.implementsSig = nothing();
}

concrete production productionSignature
top::ProductionSignature ::= cl::ConstraintList '=>' lhs::ProductionLHS '::=' rhs::ProductionRHS
{
  top.unparse = s"${cl.unparse} => ${lhs.unparse} ::= ${rhs.unparse}";
  
  cl.env = top.env;
  cl.constraintPos = signaturePos(top.namedSignature, sourceGrammar=top.grammarName);
  lhs.env = top.env;
  rhs.env = occursEnv(cl.occursDefs, top.env);
  lhs.implementedSig = map((.outputElement), top.implementedSig);
  rhs.implementedSig = map(compose(foldNamedSignatureElements, (.inputElements)), top.implementedSig);

  top.defs := lhs.defs ++ rhs.defs;
  top.constraintDefs = cl.defs;
  top.occursDefs := cl.occursDefs;
  top.namedSignature =
    namedSignature(
      top.signatureName,
      foldContexts(cl.contexts),
      foldNamedSignatureElements(rhs.inputElements),
      lhs.outputElement,
      foldNamedSignatureElements(annotationsForNonterminal(lhs.outputElement.typerep, top.env)));
} action {
  sigNames = foldNamedSignatureElements(lhs.outputElement :: rhs.inputElements).elementNames;
}

concrete production productionSignatureNoCL
top::ProductionSignature ::= lhs::ProductionLHS '::=' rhs::ProductionRHS
{
  top.unparse = s"${lhs.unparse} ::= ${rhs.unparse}";
  
  forwards to productionSignature(nilConstraint(), '=>', lhs, $2, rhs);
} action {
  sigNames = foldNamedSignatureElements(lhs.outputElement :: rhs.inputElements).elementNames;
}

concrete production productionLHS
top::ProductionLHS ::= id::Name '::' t::TypeExpr
{
  top.unparse = id.unparse ++ "::" ++ t.unparse;
  propagate env;

  top.outputElement = namedSignatureElement(id.name, t.typerep, false);

  top.defs := [lhsDef(top.grammarName, id.nameLoc, id.name, t.typerep)];

  top.errors <-
    if length(getValueDclInScope(id.name, top.env)) > 1
    then [errFromOrigin(id, "Value '" ++ id.name ++ "' is already bound.")]
    else [];
} action {
  insert semantic token IdSigNameDcl_t at id.nameLoc;
}

concrete production productionRHSNil
top::ProductionRHS ::=
{
  top.unparse = "";

  top.inputElements = [];
  top.elementCount = 0;
}

concrete production productionRHSCons
top::ProductionRHS ::= h::ProductionRHSElem t::ProductionRHS
{
  top.unparse = h.unparse ++ " " ++ t.unparse;

  top.inputElements = h.inputElements ++ t.inputElements;
  top.elementCount = 1 + t.elementCount;
  h.deterministicCount = t.elementCount;

  h.implementedSig =
    case top.implementedSig of just(consNamedSignatureElement(h, _)) -> just(h) | _ -> nothing() end;
  t.implementedSig =
    case top.implementedSig of just(consNamedSignatureElement(_, t)) -> just(t) | _ -> nothing() end;
}

concrete production productionRHSElem
top::ProductionRHSElem ::= id::Name '::' t::TypeExpr
{
  top.unparse = id.unparse ++ "::" ++ t.unparse;
  propagate env;

  local shared::Boolean =
    case top.implementedSig of
    | just(e) -> e.elementShared
    | nothing() -> false
    end;
  top.inputElements = [namedSignatureElement(id.name, t.typerep, shared)];

  top.defs := [childDef(top.grammarName, id.nameLoc, id.name, t.typerep)];

  top.errors <-
    if length(getValueDclInScope(id.name, top.env)) > 1 
    then [errFromOrigin(id, "Value '" ++ id.name ++ "' is already bound.")]
    else [];
} action {
  insert semantic token IdSigNameDcl_t at id.nameLoc;
}

concrete production productionRHSElemType
top::ProductionRHSElem ::= t::TypeExpr
{
  top.unparse = t.unparse;

  forwards to productionRHSElem(name("_G_" ++ toString(top.deterministicCount)), '::', t);
}

