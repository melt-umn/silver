grammar silver:analysis:warnings:defs;

import silver:modification:autocopyattr only isAutocopy;
import silver:modification:collection;
import silver:definition:flow:driver only ProductionGraph, FlowType, flowVertexEq, prod, inhDepsForSyn, findProductionGraph, expandGraph, onlyLhsInh;
import silver:util:raw:treeset as set;

synthesized attribute warnMissingInh :: Boolean occurs on CmdArgs;

aspect production endCmdArgs
top::CmdArgs ::= l::[String]
{
  top.warnMissingInh = false;
}
abstract production warnMissingInhFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.warnMissingInh = true;
  forwards to rest;
}
aspect function parseArgs
Either<String  Decorated CmdArgs> ::= args::[String]
{
  flags <- [pair("--warn-missing-inh", flag(warnMissingInhFlag))];
}


-- A giant pile of helper functions, to kick things off...

function isEquationMissing
Boolean ::= f::([FlowDef] ::= String)  attr::String
{
  return null(f(attr));
}

{--
 - False if 'attr' occurs on 'lhsNt' and is an autocopy attribute,
 - true otherwise.  Used in conjunction with 'filter' to get
 - remove "missing equations" that are actually implicit autocopies.
 -}
function ignoreIfAutoCopyOnLhs
Boolean ::= lhsNt::String  env::Decorated Env  attr::String
{
  return !(isAutocopy(attr, env) && !null(getOccursDcl(attr, lhsNt, env)));
}

-- TODO: this should probably not be a thing I have to write here
function isAutocopy
Boolean ::= attr::String  e::Decorated Env
{
  return case getAttrDclAll(attr, e) of
  | at :: _ -> at.isAutocopy
  | _ -> false
  end;
}
-- TODO: why is this a thing I have to write here. Sheesh. FIX THIS.
function isInherited
Boolean ::= a::String  e::Decorated Env
{
  return case getAttrDclAll(a, e) of
  | at :: _ -> at.isInherited
  | _ -> false
  end;
}

function isLhsInh
Boolean ::= v::FlowVertex
{
  return case v of
  | lhsInhVertex(a) -> true
  | _ -> false
  end;
}

{--
 - Given a name of a child, return whether it has an undecorated
 - nonterminal type. False if nonsensicle.
 -}
function sigNotAReference
Boolean ::= sigName::String  e::Decorated Env
{
  local d :: [DclInfo] = getValueDcl(sigName, e);
  
  -- TODO BUG: it's actually possible for this to to fail to lookup
  -- due to aspects renaming the sig name!!  We're conservative here and return true if that happens
  -- but this could lead to spurious errors.
  
  return if null(d) then true else head(d).typerep.isDecorable;
}

{--
 - Used as a stop-gap measure to ensure equations exist.
 - Given a needed equation (represented by FlowVertex 'v'),
 - ensure such an equation exists, accounting for:
 -  1. Defaults
 -  2. Forwards
 -  3. Autocopy
 -  4. Reference accesses
 - 
 - This gives rise to 'missing transitive dependency' errors.
 - The reason this exists is to handle 'taking a reference'
 - actions needing to ensure equations were actually provided for
 - things we reference.
 -
 - @param v  A value we need an equation for.
 - @param l  Where to report an error, if it's missing
 - @param prodName  The full name of the production we're in
 - @param prodNt  The nonterminal is production belongs to
 - @param flowEnv  The local flow environment
 - @param realEnv  The local real environment
 - @returns  Errors for missing equations
 -}
function checkEqDeps
[Message] ::= v::FlowVertex  l::Location  prodName::String  prodNt::String  flowEnv::Decorated FlowEnv  realEnv::Decorated Env  anonResolve::[Pair<String  Location>]
{
  -- We focus only on things that are "our problem."  Within a production,
  -- we can only check: syn on LHS, inhs on RHS and locals.
  -- LHS is someone else's job.
  -- Inhs on Decorated types are someone else's job.
  -- Inhs on forward is automatic.
  
  return case v of 
  | lhsInhVertex(_) -> [] -- not our problem
  | lhsSynVertex(attrName) -> [] -- actually, this should be an error elsewhere, so don't sweat it here
  | rhsVertex(sigName, attrName) ->
      if isInherited(attrName, realEnv)
      then if !null(lookupInh(prodName, sigName, attrName, flowEnv)) -- no equation
           || !ignoreIfAutoCopyOnLhs(prodNt, realEnv, attrName) -- not autocopy (and on lhs)
           || !sigNotAReference(sigName, realEnv) -- not Decorated type (which wouldn't be our problem)
           then []
           else [wrn(l, "Equation has transitive dependency on child " ++ sigName ++ "'s inherited attribute for " ++ attrName ++ " but this equation appears to be missing.")]
      else [] -- not our problem
  | localEqVertex(fName) -> [] -- Technically there's something to check here but let's ignore the brokenness of local declarations
  | localVertex(fName, attrName) -> 
      if isInherited(attrName, realEnv)
      then if !null(lookupLocalInh(prodName, fName, attrName, flowEnv)) -- no equation
           || fName == "forward" -- not forward (automatic)
           || !sigNotAReference(fName, realEnv) -- not Decorated type (which wouldn't be our problem)
           then []
           else [wrn(l, "Equation has transitive dependency on local " ++ fName ++ "'s inherited attribute for " ++ attrName ++ " but this equation appears to be missing.")]
      else [] -- not our problem
  | anonEqVertex(fName) -> [] -- so these syntactically must be there, nothing to check the existence of
  | anonVertex(fName, attrName) -> 
    --[] -- these DO need to be checked, but we defer to decorate checking for it themselves!
      if isInherited(attrName, realEnv)
      then if !null(lookupLocalInh(prodName, fName, attrName, flowEnv)) -- no equation
           then []
           else let
             anonl :: Maybe<Location> = lookupBy(stringEq, fName, anonResolve)
           in if anonl.isJust 
              then [wrn(anonl.fromJust, "Decoration requires inherited attribute for " ++ attrName ++ ".")]
              else [] -- If it's not in the list, then it's a transitive dep from a DIFFERENT equation (and thus reported there)
           end
      else [] -- not our problem
  end;
}
function checkAllEqDeps
[Message] ::= v::[FlowVertex]  l::Location  prodName::String  prodNt::String  flowEnv::Decorated FlowEnv  realEnv::Decorated Env  anonResolve::[Pair<String  Location>]
{
  return foldr(append, [], map(checkEqDeps(_, l, prodName, prodNt, flowEnv, realEnv, anonResolve), v));
}


--------------------------------------------------------------------------------


{- Step 1: Let's go ensure that all EQUATIONS dependencies all exist, and their flow types are satisfied. -}


aspect production synthesizedAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;
  local myGraphs :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  local transitiveDeps :: [FlowVertex] =
    expandGraph(e.flowDeps, findProductionGraph(top.signature.fullName, myGraphs));
  
  local lhsInhDeps :: set:Set<String> = onlyLhsInh(transitiveDeps);
  local lhsInhExceedsFlowType :: [String] = set:toList(set:difference(lhsInhDeps, inhDepsForSyn(attr.attrDcl.fullName, top.signature.outputElement.typerep.typeName, myFlow)));

  top.errors <-
    if null(dl.errors ++ attr.errors)
    && (top.config.warnAll || top.config.warnMissingInh)
    then checkAllEqDeps(transitiveDeps, top.location, top.signature.fullName, top.signature.outputElement.typerep.typeName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs)) ++
      if null(lhsInhExceedsFlowType) then []
      else [wrn(top.location, "Synthesized equation " ++ attr.pp ++ " exceeds flow type with dependencies on " ++ implode(", ", lhsInhExceedsFlowType))]
    else [];
}

aspect production inheritedAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  -- oh no again!
  --local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;
  local myGraphs :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  local transitiveDeps :: [FlowVertex] = 
    if top.blockContext.hasFullSignature
    then expandGraph(e.flowDeps, findProductionGraph(top.signature.fullName, myGraphs))
    else e.flowDeps; -- patch for functions lacking a graph
  
  -- TODO: if LHS is forward, we have to check that we aren't exceeding flow type!! (BUG)
  
  -- check transitive deps only. Nothing to be done for flow types
  top.errors <-
    if (top.config.warnAll || top.config.warnMissingInh)
    then checkAllEqDeps(transitiveDeps, top.location, top.signature.fullName, top.signature.outputElement.typerep.typeName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs))
    else [];
}

----- WARNING TODO BEGIN MASSIVE COPY & PASTE SESSION
aspect production synBaseColAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;
  local myGraphs :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  local transitiveDeps :: [FlowVertex] =
    expandGraph(e.flowDeps, findProductionGraph(top.signature.fullName, myGraphs));
  
  local lhsInhDeps :: set:Set<String> = onlyLhsInh(transitiveDeps);
  local lhsInhExceedsFlowType :: [String] = set:toList(set:difference(lhsInhDeps, inhDepsForSyn(attr.attrDcl.fullName, top.signature.outputElement.typerep.typeName, myFlow)));

  top.errors <-
    if null(dl.errors ++ attr.errors)
    && (top.config.warnAll || top.config.warnMissingInh)
    then checkAllEqDeps(transitiveDeps, top.location, top.signature.fullName, top.signature.outputElement.typerep.typeName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs)) ++
      if null(lhsInhExceedsFlowType) then []
      else [wrn(top.location, "Synthesized equation " ++ attr.pp ++ " exceeds flow type with dependencies on " ++ implode(", ", lhsInhExceedsFlowType))]
    else [];
}
aspect production synAppendColAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;
  local myGraphs :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  local transitiveDeps :: [FlowVertex] =
    expandGraph(e.flowDeps, findProductionGraph(top.signature.fullName, myGraphs));
  
  local lhsInhDeps :: set:Set<String> = onlyLhsInh(transitiveDeps);
  local lhsInhExceedsFlowType :: [String] = set:toList(set:difference(lhsInhDeps, inhDepsForSyn(attr.attrDcl.fullName, top.signature.outputElement.typerep.typeName, myFlow)));

  top.errors <-
    if null(dl.errors ++ attr.errors)
    && (top.config.warnAll || top.config.warnMissingInh)
    then checkAllEqDeps(transitiveDeps, top.location, top.signature.fullName, top.signature.outputElement.typerep.typeName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs)) ++
      if null(lhsInhExceedsFlowType) then []
      else [wrn(top.location, "Synthesized equation " ++ attr.pp ++ " exceeds flow type with dependencies on " ++ implode(", ", lhsInhExceedsFlowType))]
    else [];
}
aspect production inhBaseColAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  -- oh no again!
  --local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;
  local myGraphs :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  local transitiveDeps :: [FlowVertex] = 
    if top.blockContext.hasFullSignature
    then expandGraph(e.flowDeps, findProductionGraph(top.signature.fullName, myGraphs))
    else e.flowDeps; -- patch for functions lacking a graph
  
  -- check transitive deps only. Nothing to be done for flow types
  top.errors <-
    if (top.config.warnAll || top.config.warnMissingInh)
    then checkAllEqDeps(transitiveDeps, top.location, top.signature.fullName, top.signature.outputElement.typerep.typeName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs))
    else [];
}
aspect production inhAppendColAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  -- oh no again!
  --local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;
  local myGraphs :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  local transitiveDeps :: [FlowVertex] = 
    if top.blockContext.hasFullSignature
    then expandGraph(e.flowDeps, findProductionGraph(top.signature.fullName, myGraphs))
    else e.flowDeps; -- patch for functions lacking a graph
  
  -- check transitive deps only. Nothing to be done for flow types
  top.errors <-
    if (top.config.warnAll || top.config.warnMissingInh)
    then checkAllEqDeps(transitiveDeps, top.location, top.signature.fullName, top.signature.outputElement.typerep.typeName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs))
    else [];
}
------ END AWFUL COPY & PASTE SESSION

aspect production forwardsTo
top::ProductionStmt ::= 'forwards' 'to' e::Expr ';'
{
  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;
  local myGraphs :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  local transitiveDeps :: [FlowVertex] = expandGraph(e.flowDeps, findProductionGraph(top.signature.fullName, myGraphs));
  
  local lhsInhDeps :: set:Set<String> = onlyLhsInh(transitiveDeps);
  local lhsInhExceedsFlowType :: [String] = set:toList(set:difference(lhsInhDeps, inhDepsForSyn("forward", top.signature.outputElement.typerep.typeName, myFlow)));

  top.errors <-
    if (top.config.warnAll || top.config.warnMissingInh)
    then checkAllEqDeps(transitiveDeps, top.location, top.signature.fullName, top.signature.outputElement.typerep.typeName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs)) ++
         if null(lhsInhExceedsFlowType) then []
         else [wrn(top.location, "Forward equation exceeds flow type with dependencies on " ++ implode(", ", lhsInhExceedsFlowType))]
    else [];
}
aspect production forwardInh
top::ForwardInh ::= lhs::ForwardLHSExpr '=' e::Expr ';'
{
  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;
  local myGraphs :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  local transitiveDeps :: [FlowVertex] = expandGraph(e.flowDeps, findProductionGraph(top.signature.fullName, myGraphs));
  
  local lhsInhDeps :: set:Set<String> = onlyLhsInh(transitiveDeps);
  -- problem = lhsinh deps - fwd flow type - this inh attribute
  local lhsInhExceedsFlowType :: [String] = 
    set:toList(
      set:removeAll(
        [case lhs of
         | forwardLhsExpr(q) -> q.attrDcl.fullName
         end],
      set:difference(
        lhsInhDeps,
        inhDepsForSyn("forward", top.signature.outputElement.typerep.typeName, myFlow))));

  top.errors <-
    if (top.config.warnAll || top.config.warnMissingInh)
    then checkAllEqDeps(transitiveDeps, top.location, top.signature.fullName, top.signature.outputElement.typerep.typeName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs)) ++
         if null(lhsInhExceedsFlowType) then []
         else [wrn(top.location, "Forward inherited equation exceeds flow type with dependencies on " ++ implode(", ", lhsInhExceedsFlowType))]
    else [];
}

aspect production localValueDef
top::ProductionStmt ::= val::Decorated QName  e::Expr
{
  -- oh no again!
  --local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;
  local myGraphs :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  local transitiveDeps :: [FlowVertex] = 
    if top.blockContext.hasFullSignature
    then expandGraph(e.flowDeps, findProductionGraph(top.signature.fullName, myGraphs))
    else e.flowDeps; -- patch for functions lacking a graph
  
  -- check transitive deps only. No worries about flow types.
  top.errors <-
    if (top.config.warnAll || top.config.warnMissingInh)
    then checkAllEqDeps(transitiveDeps, top.location, top.signature.fullName, top.signature.outputElement.typerep.typeName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs))
    else [];
}

aspect production returnDef
top::ProductionStmt ::= 'return' e::Expr ';'
{
  -- TODO: lacking a graph, we're going to just do this on immediate deps directly.
  -- This still captures the really necessary case of 'take reference' equations needed

  -- without graphs for functions, we don't get any transitive dependencies.
  -- this means rhs.syn doesn't emit deps on rhs.inh at all. nor for a localEq, any of its deps

  -- Note: "::nolhs" is the nonterminal name of the lhs. This *should* only be used by
  -- checkEqDeps for default equations and autocopy info, so giving a bogus value here
  -- should be correct as those are not relevant to functions.
  top.errors <-
    if (top.config.warnAll || top.config.warnMissingInh)
    then checkAllEqDeps(e.flowDeps, top.location, top.signature.fullName, "::nolhs", top.flowEnv, top.env, collectAnonOrigin(e.flowDefs))
    else [];
-- TODO: bug: we don't have graphs for functions, so we have a problem with the above
-- implementation needing those graphs.
}

aspect production appendCollectionValueDef
top::ProductionStmt ::= val::Decorated QName  e::Expr
{
  -- oh no again!
  --local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;
  local myGraphs :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  local productionFlowGraph :: ProductionGraph = findProductionGraph(top.signature.fullName, myGraphs);
  local transitiveDeps :: [FlowVertex] = expandGraph(e.flowDeps, productionFlowGraph);
  
  local originalEqDeps :: [FlowVertex] = 
    expandGraph([localEqVertex(val.lookupValue.fullName)], productionFlowGraph);
  
  local lhsInhDeps :: set:Set<String> = onlyLhsInh(transitiveDeps);
  
  local originalEqLhsInhDeps :: set:Set<String> = onlyLhsInh(originalEqDeps);
  
  local lhsInhExceedsFlowType :: [String] = set:toList(set:difference(lhsInhDeps, originalEqLhsInhDeps));

  -- For most collection append operators, the checking is already done by the thing they forward to.
  -- Local collections are a special case though: typically they're always considered "authoritative"
  -- and thus flow types don't need checking (unlike syn defs), but for contributions to locals we do
  -- need to do a check!
  top.errors <-
    if (top.config.warnAll || top.config.warnMissingInh) &&
       -- We can ignore functions. We're checking LHS inhs here... functions don't have any!
       top.blockContext.hasFullSignature
    then if null(lhsInhExceedsFlowType) then []
         else [wrn(top.location, "Local contribution (<-) equation exceeds flow dependencies with: " ++ implode(", ", lhsInhExceedsFlowType))]
    else [];
}



--------------------------------------------------------------------------------

-- Step 1.5: implicit equations due to forwards need their flow types checked!
-- We could get rid of these, if we generated the copy equations, as then this
-- would be checked on those copy equations.

-- Side note: the "authoritative source" for these checks / errors
-- should be the production, unless the attribute isn't known to the production.
-- Later, carefully think about how to formulate "isn't known to the production"
-- so we know when to do that check. TODO

aspect production attributionDcl
top::AGDcl ::= 'attribute' at::QName attl::BracketedOptTypeList 'occurs' 'on' nt::QName nttl::BracketedOptTypeList ';'
{
  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;
  local myGraphs :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;
  
  local depsForThisAttr :: set:Set<String> = inhDepsForSyn(at.lookupAttribute.fullName, nt.lookupType.fullName, myFlow);

  top.errors <-
    if null(nt.lookupType.errors ++ at.lookupAttribute.errors)
    && (top.config.warnAll || top.config.warnMissingInh)
    && at.lookupAttribute.dcl.isSynthesized
    then raiseImplicitFwdEqFlowTypesForAttr(top.location, at.lookupAttribute.fullName, prods, top.flowEnv, depsForThisAttr, myGraphs)
    else [];
}
function raiseImplicitFwdEqFlowTypesForAttr
[Message] ::= l::Location  attr::String  prods::[FlowDef]  e::Decorated FlowEnv  depsForThisAttr::set:Set<String> myGraphs::EnvTree<ProductionGraph>
{
  local headProdName :: String = case head(prods) of prodFlowDef(_, p) -> p end;
  local transitiveDeps :: [FlowVertex] = expandGraph([forwardEqVertex()], findProductionGraph(headProdName, myGraphs));
  local thisFlowDeps :: set:Set<String> = onlyLhsInh(transitiveDeps);
  local diff :: [String] = set:toList(set:difference(thisFlowDeps, depsForThisAttr));

  return if null(prods) then []
  else case lookupSyn(headProdName, attr, e),  lookupFwd(headProdName, e) of
       | _ :: _, _ -> [] -- eq present, checked elsewhere
       -- if no equation and DOES forward, do the error check (no worries about defaults!)
       | [], fwdFD :: _ -> if null(diff) then [] else [wrn(l, "Implicit forward copy equation for attribute " ++ attr ++ " on production " ++ headProdName ++ " exceeds the flow type for this attribute because the forward additionally depends on " ++ implode(", ", diff))]
       | [], [] -> [] -- different error situation (or non-error for defaults)
       end ++ raiseImplicitFwdEqFlowTypesForAttr(l, attr, tail(prods), e, depsForThisAttr, myGraphs);
}
aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;
  local myGraphs :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;
  
  local transitiveDeps :: [FlowVertex] = expandGraph([forwardEqVertex()], findProductionGraph(fName, myGraphs));
  local fwdFlowDeps :: set:Set<String> = onlyLhsInh(transitiveDeps);

  top.errors <-
    if null(body.errors ++ ns.errors{-TODO-})
    && (top.config.warnAll || top.config.warnMissingInh)
    && !null(body.uniqueSignificantExpression) -- don't bother checking if this production doesn't forward
    then raiseImplicitFwdEqFlowTypesForProd(top.location, fName, attrs, top.flowEnv, fwdFlowDeps, myFlow)
    else [];
}
function raiseImplicitFwdEqFlowTypesForProd
[Message] ::= l::Location  prod::String  attrs::[DclInfo]  e::Decorated FlowEnv  fwdFlowDeps::set:Set<String>  myFlow::EnvTree<FlowType>
{
  local depsForThisAttr :: set:Set<String> = inhDepsForSyn(head(attrs).attrOccurring, head(attrs).fullName, myFlow);
  local diff :: [String] = set:toList(set:difference(fwdFlowDeps, depsForThisAttr));

  return if null(attrs) then []
  else case lookupSyn(prod, head(attrs).attrOccurring, e) of
       | eq :: _ -> []
       | [] -> if null(diff) then [] else [wrn(l, "Implicit forward copy equation for attribute " ++ head(attrs).attrOccurring ++ " in production " ++ prod ++ " exceeds flow type because the forward depends on " ++ implode(", ", diff))]
       end ++ raiseImplicitFwdEqFlowTypesForProd(l, prod, tail(attrs), e, fwdFlowDeps, myFlow);
}

-- General TODO: we should probably find another way of generating errors,
-- so that we can eliminate these silly checks...
-- Perhaps put "namespaces" in errors? (Check from [Message] to ErrorSpace with multiple [Message]?)
-- Then we could 1. Issue normal errors; If none, 2. Issue syn-completeness errors; If none, 3. Issue inh-completeness errors


--------------------------------------------------------------------------------


{-
Step 2: Let's go check on expressions. This has two purposes:
1. Better error messages for missing equations than the "transitive dependency" ones.
   But technically, unneeded and transititve dependencies are covering this.
2. We have to ensure that each individual access from a reference fits within the blessed set.
   This is not covered by any other checks.
-}


aspect production forwardAccess
top::Expr ::= e::Expr '.' 'forward'
{
  -- TODO?
}

aspect production synDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
-- This aspect is in two parts. First: we *must* check that any accesses
-- on a unknown decorated tree are in the ref-set.

  -- oh no again
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;

  local eTypeName :: String = performSubstitution(e.typerep, e.upSubst).typeName;
  local diff :: [String] =
    set:toList(set:removeAll(
      inhsForTakingRef(eTypeName, top.flowEnv),  -- blessed inhs for a reference
      inhDepsForSyn(q.attrDcl.fullName, eTypeName, myFlow))); -- needed inhs
  
  top.errors <- 
    if null(e.errors)
    && (top.config.warnAll || top.config.warnMissingInh)
    then
      case e.flowVertexInfo of
      | hasVertex(_) -> [] -- no check to make, as it was done transitively
      -- without a vertex, we're accessing from a reference, and so...
      | noVertex() ->
          if null(diff) then []
          else [wrn(top.location, "Access of " ++ q.pp ++ " from reference requires inherited attributes not known to be supplied to references: " ++ implode(", ", diff))]
      end
    else [];

----------------
-- Okay, so as part 2, we attempt to give better error messages, explaining *where*
-- some needed dependencies came from. 

  top.errors <- 
    if null(e.errors)
    && (top.config.warnAll || top.config.warnMissingInh)
    then
      case e of
      | childReference(lq) ->
          if lq.lookupValue.typerep.isDecorable
          then
            let inhs :: [String] = 
                  -- N.B. we're filtering out autocopies here
                  filter(
                    ignoreIfAutoCopyOnLhs(top.signature.outputElement.typerep.typeName, top.env, _),
                    filter(
                      isEquationMissing(
                        lookupInh(top.signature.fullName, lq.lookupValue.fullName, _, top.flowEnv),
                        _),
                      set:toList(inhDepsForSyn(q.attrDcl.fullName, eTypeName, myFlow))))
             in if null(inhs) then []
                else [wrn(top.location, "Access of syn attribute " ++ q.pp ++ " on " ++ e.pp ++ " requires missing inherited attributes " ++ implode(", ", inhs) ++ " to be supplied")]
            end
          else []
      | localReference(lq) ->
          if lq.lookupValue.typerep.isDecorable
          then
            let inhs :: [String] = 
                  filter(
                    isEquationMissing(
                      lookupLocalInh(top.signature.fullName, lq.lookupValue.fullName, _, top.flowEnv),
                      _),
                    set:toList(inhDepsForSyn(q.attrDcl.fullName, eTypeName, myFlow)))
             in if null(inhs) then []
                else [wrn(top.location, "Access of syn attribute " ++ q.pp ++ " on " ++ e.pp ++ " requires missing inherited attributes " ++ implode(", ", inhs) ++ " to be supplied")]
            end
          else []
      | _ -> []
    end
    else [];
}

aspect production inhDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  -- In this case, ONLY check for references.
  -- The transitive deps error will be less difficult to figure out when there's
  -- an explicit access to the attributes.
  top.errors <- 
    if null(e.errors)
    && (top.config.warnAll || top.config.warnMissingInh)
    then
      case e.flowVertexInfo of
      | hasVertex(_) -> [] -- no check to make, as it was done transitively
      -- without a vertex, we're accessing from a reference, and so...
      | noVertex() ->
          if contains(q.attrDcl.fullName, inhsForTakingRef(performSubstitution(e.typerep, e.upSubst).typeName, top.flowEnv))
          then []
          else [wrn(top.location, "Access of inherited attribute " ++ q.pp ++ " from a reference is not permitted, as references are not known to be decorated with this attribute.")]
      end
    else [];      
}

aspect production decorateExprWith
top::Expr ::= 'decorate' e::Expr 'with' '{' inh::ExprInhs '}'
{
  -- Do nothing. Everything gets taken care of with anonResolve and checkEqDeps at the top
}

-- TODO: pattern variable accesses.

