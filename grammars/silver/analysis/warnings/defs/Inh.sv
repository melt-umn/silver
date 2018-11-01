grammar silver:analysis:warnings:defs;

import silver:modification:autocopyattr only isAutocopy;
import silver:modification:collection;
import silver:modification:primitivepattern;

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



{--
 - This is a glorified lambda function, to help look for equations.
 - Literally, we're just checking for null here.
 -
 - @param f  The lookup function for the appropriate type of equation
 -           e.g. `lookupInh(prod, rhs, _, env)`
 - @param attr  The attribute to look up.
 -}
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
-- The real fix is for our vertexes to remember whether they are syn/inh.
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
  | lhsInhVertex(_) -> true
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
 - @param prodNt  The nonterminal is production belongs to. (For functions, a dummy value is ok)
 - @param flowEnv  The local flow environment
 - @param realEnv  The local real environment
 - @returns  Errors for missing equations
 -}
function checkEqDeps
[Message] ::= v::FlowVertex  l::Location  prodName::String  prodNt::String  flowEnv::Decorated FlowEnv  realEnv::Decorated Env  anonResolve::[Pair<String  Location>]
{
  -- We're concerned with missing inherited equations on RHS, LOCAL, and ANON. (Implicitly, FORWARD.)
  
  return case v of
  -- A dependency on an LHS.INH is a flow issue: these equations do not exist
  -- locally, so we cannot check them.
  | lhsInhVertex(_) -> []
  -- A dependency on an LHS.SYN can be checked locally, but we do not do so here.
  -- All productions must have all SYN equations, so those errors are raised elsewhere.
  | lhsSynVertex(attrName) -> []
  -- A dependency on an RHS.ATTR. SYN are always present, so we only care about INH here.
  -- Filter missing equations for autocopy or for RHS that are references.
  | rhsVertex(sigName, attrName) ->
      if isInherited(attrName, realEnv)
      then if !null(lookupInh(prodName, sigName, attrName, flowEnv))
           || !ignoreIfAutoCopyOnLhs(prodNt, realEnv, attrName)
           || !sigNotAReference(sigName, realEnv)
           then []
           else [wrn(l, "Equation has transitive dependency on child " ++ sigName ++ "'s inherited attribute for " ++ attrName ++ " but this equation appears to be missing.")]
      else []
  -- A dependency on a LOCAL. Technically, local equations may not exist!
  -- But let's just assume they do, since `local name :: type = expr;` is the prefered syntax.
  | localEqVertex(fName) -> []
  -- A dependency on a LOCAL.ATTR. SYN always exist again, so we only care about INH here.
  -- Ignore the FORWARD (a special case of LOCAL), which always has both SYN/INH.
  -- And again ignore references. Autocopy isn't relevant to locals, though.
  | localVertex(fName, attrName) -> 
      if isInherited(attrName, realEnv)
      then if !null(lookupLocalInh(prodName, fName, attrName, flowEnv))
           || fName == "forward"
           || !sigNotAReference(fName, realEnv)
           then []
           else [wrn(l, "Equation has transitive dependency on local " ++ fName ++ "'s inherited attribute for " ++ attrName ++ " but this equation appears to be missing.")]
      else []
  -- A dependency on a ANON. This do always exist (`decorate expr with..` always has expr.)
  | anonEqVertex(fName) -> []
  -- A dependency on ANON.ATTR. Again, SYN are safe. We need to check only for INH.
  -- If the equation is missing, then we again filter down to just those equations
  -- missing within THIS overall equation.
  -- i.e. `top.syn1 = ... missing ...; top.syn2 = top.syn1;` should only raise
  -- the missing in the first equation.
  | anonVertex(fName, attrName) ->
      if isInherited(attrName, realEnv)
      then if !null(lookupLocalInh(prodName, fName, attrName, flowEnv))
           then []
           else let
             anonl :: Maybe<Location> = lookupBy(stringEq, fName, anonResolve)
           in if anonl.isJust 
              then [wrn(anonl.fromJust, "Decoration requires inherited attribute for " ++ attrName ++ ".")]
              else [] -- If it's not in the list, then it's a transitive dep from a DIFFERENT equation (and thus reported there)
           end
      else []
  end;
}
function checkAllEqDeps
[Message] ::= v::[FlowVertex]  l::Location  prodName::String  prodNt::String  flowEnv::Decorated FlowEnv  realEnv::Decorated Env  anonResolve::[Pair<String  Location>]
{
  return flatMap(checkEqDeps(_, l, prodName, prodNt, flowEnv, realEnv, anonResolve), v);
}


--------------------------------------------------------------------------------


{- Step 1: We check two things:
   1. That all the dependencies of each equation have equations (checkAllEqDeps)
   2. That flow types are not exceeded, if it's a thing with a flowtype.
 -}

aspect production synthesizedAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;

  local transitiveDeps :: [FlowVertex] =
    expandGraph(e.flowDeps, top.frame.prodFlowGraph.fromJust);
  
  local lhsInhDeps :: set:Set<String> = onlyLhsInh(transitiveDeps);
  local lhsInhExceedsFlowType :: [String] = set:toList(set:difference(lhsInhDeps, inhDepsForSyn(attr.attrDcl.fullName, top.frame.lhsNtName, myFlow)));

  top.errors <-
    if dl.found && attr.found
    && (top.config.warnAll || top.config.warnMissingInh)
    && top.frame.prodFlowGraph.isJust -- Default synthesized equations have no production graph to use
                          -- TODO: shit. is anything looking at default synthesized equations to make sure
                          -- their flow types aren't messed up?
    then checkAllEqDeps(transitiveDeps, top.location, top.frame.fullName, top.frame.lhsNtName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs)) ++
      if null(lhsInhExceedsFlowType) then []
      else [wrn(top.location, "Synthesized equation " ++ attr.name ++ " exceeds flow type with dependencies on " ++ implode(", ", lhsInhExceedsFlowType))]
    else [];
}

aspect production inheritedAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  local transitiveDeps :: [FlowVertex] = 
    expandGraph(e.flowDeps, top.frame.prodFlowGraph.fromJust);
  
  -- TODO: if LHS is forward, we have to check that we aren't exceeding flow type!! (BUG)
  
  -- check transitive deps only. Nothing to check for flow types
  top.errors <-
    if (top.config.warnAll || top.config.warnMissingInh) && top.frame.prodFlowGraph.isJust
    then checkAllEqDeps(transitiveDeps, top.location, top.frame.fullName, top.frame.lhsNtName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs))
    else [];
}

----- WARNING TODO BEGIN MASSIVE COPY & PASTE SESSION
aspect production synBaseColAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;

  local transitiveDeps :: [FlowVertex] =
    expandGraph(e.flowDeps, top.frame.prodFlowGraph.fromJust);
  
  local lhsInhDeps :: set:Set<String> = onlyLhsInh(transitiveDeps);
  local lhsInhExceedsFlowType :: [String] = set:toList(set:difference(lhsInhDeps, inhDepsForSyn(attr.attrDcl.fullName, top.frame.lhsNtName, myFlow)));

  top.errors <-
    if dl.found && attr.found
    && (top.config.warnAll || top.config.warnMissingInh)
    && top.frame.prodFlowGraph.isJust
    then checkAllEqDeps(transitiveDeps, top.location, top.frame.fullName, top.frame.lhsNtName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs)) ++
      if null(lhsInhExceedsFlowType) then []
      else [wrn(top.location, "Synthesized equation " ++ attr.name ++ " exceeds flow type with dependencies on " ++ implode(", ", lhsInhExceedsFlowType))]
    else [];
}
aspect production synAppendColAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;

  local transitiveDeps :: [FlowVertex] =
    expandGraph(e.flowDeps, top.frame.prodFlowGraph.fromJust);
  
  local lhsInhDeps :: set:Set<String> = onlyLhsInh(transitiveDeps);
  local lhsInhExceedsFlowType :: [String] = set:toList(set:difference(lhsInhDeps, inhDepsForSyn(attr.attrDcl.fullName, top.frame.lhsNtName, myFlow)));

  top.errors <-
    if dl.found && attr.found
    && (top.config.warnAll || top.config.warnMissingInh)
    && top.frame.prodFlowGraph.isJust
    then checkAllEqDeps(transitiveDeps, top.location, top.frame.fullName, top.frame.lhsNtName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs)) ++
      if null(lhsInhExceedsFlowType) then []
      else [wrn(top.location, "Synthesized equation " ++ attr.name ++ " exceeds flow type with dependencies on " ++ implode(", ", lhsInhExceedsFlowType))]
    else [];
}
aspect production inhBaseColAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  local transitiveDeps :: [FlowVertex] =
    expandGraph(e.flowDeps, top.frame.prodFlowGraph.fromJust);
  
  -- check transitive deps only. Nothing to be done for flow types
  top.errors <-
    if (top.config.warnAll || top.config.warnMissingInh) && top.frame.prodFlowGraph.isJust
    then checkAllEqDeps(transitiveDeps, top.location, top.frame.fullName, top.frame.lhsNtName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs))
    else [];
}
aspect production inhAppendColAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  local transitiveDeps :: [FlowVertex] = 
    expandGraph(e.flowDeps, top.frame.prodFlowGraph.fromJust);
  
  -- check transitive deps only. Nothing to be done for flow types
  top.errors <-
    if (top.config.warnAll || top.config.warnMissingInh) && top.frame.prodFlowGraph.isJust
    then checkAllEqDeps(transitiveDeps, top.location, top.frame.fullName, top.frame.lhsNtName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs))
    else [];
}
------ END AWFUL COPY & PASTE SESSION

aspect production forwardsTo
top::ProductionStmt ::= 'forwards' 'to' e::Expr ';'
{
  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;

  local transitiveDeps :: [FlowVertex] =
    expandGraph(e.flowDeps, top.frame.prodFlowGraph.fromJust);
  
  local lhsInhDeps :: set:Set<String> = onlyLhsInh(transitiveDeps);
  local lhsInhExceedsFlowType :: [String] = set:toList(set:difference(lhsInhDeps, inhDepsForSyn("forward", top.frame.lhsNtName, myFlow)));

  top.errors <-
    if (top.config.warnAll || top.config.warnMissingInh) && top.frame.prodFlowGraph.isJust
    then checkAllEqDeps(transitiveDeps, top.location, top.frame.fullName, top.frame.lhsNtName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs)) ++
         if null(lhsInhExceedsFlowType) then []
         else [wrn(top.location, "Forward equation exceeds flow type with dependencies on " ++ implode(", ", lhsInhExceedsFlowType))]
    else [];
}
aspect production forwardInh
top::ForwardInh ::= lhs::ForwardLHSExpr '=' e::Expr ';'
{
  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;

  local transitiveDeps :: [FlowVertex] =
    expandGraph(e.flowDeps, top.frame.prodFlowGraph.fromJust);
  
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
        inhDepsForSyn("forward", top.frame.lhsNtName, myFlow))));

  top.errors <-
    if (top.config.warnAll || top.config.warnMissingInh) && top.frame.prodFlowGraph.isJust
    then checkAllEqDeps(transitiveDeps, top.location, top.frame.fullName, top.frame.lhsNtName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs)) ++
         if null(lhsInhExceedsFlowType) then []
         else [wrn(top.location, "Forward inherited equation exceeds flow type with dependencies on " ++ implode(", ", lhsInhExceedsFlowType))]
    else [];
}

aspect production localValueDef
top::ProductionStmt ::= val::Decorated QName  e::Expr
{
  local transitiveDeps :: [FlowVertex] =
    expandGraph(e.flowDeps, top.frame.prodFlowGraph.fromJust);
  
  -- check transitive deps only. No worries about flow types.
  top.errors <-
    if (top.config.warnAll || top.config.warnMissingInh) && top.frame.prodFlowGraph.isJust
    then checkAllEqDeps(transitiveDeps, top.location, top.frame.fullName, top.frame.lhsNtName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs))
    else [];
}

aspect production returnDef
top::ProductionStmt ::= 'return' e::Expr ';'
{
  local transitiveDeps :: [FlowVertex] =
    expandGraph(e.flowDeps, top.frame.prodFlowGraph.fromJust);

  top.errors <-
    if (top.config.warnAll || top.config.warnMissingInh) && top.frame.prodFlowGraph.isJust
    then checkAllEqDeps(transitiveDeps, top.location, top.frame.fullName, top.frame.lhsNtName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs))
    else [];
}

aspect production appendCollectionValueDef
top::ProductionStmt ::= val::Decorated QName  e::Expr
{
  local productionFlowGraph :: ProductionGraph = top.frame.prodFlowGraph.fromJust;
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
    if (top.config.warnAll || top.config.warnMissingInh)
       -- We can ignore functions. We're checking LHS inhs here... functions don't have any!
    && top.frame.hasFullSignature
    && top.frame.prodFlowGraph.isJust
    then if null(lhsInhExceedsFlowType) then []
         else [wrn(top.location, "Local contribution (" ++ val.name ++ " <-) equation exceeds flow dependencies with: " ++ implode(", ", lhsInhExceedsFlowType))]
    else [];
}



--------------------------------------------------------------------------------

-- Step 1.5: Implicit equations due to forwards need their flow types checked!

-- The flow environment can give us the authoritative list of attributes to check.
-- These may be from `options` and so requires the flowenv.

-- Only attributes exported(/optioned) from host can have flow types more restricted
-- than the forward flow type, and those are the only ones that need checking here.

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;
  local myGraphs :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;
  
  local transitiveDeps :: [FlowVertex] = expandGraph([forwardEqVertex()], findProductionGraph(fName, myGraphs));
  local fwdFlowDeps :: set:Set<String> = onlyLhsInh(transitiveDeps);

  local lhsNt :: String = namedSig.outputElement.typerep.typeName;
  local hostSyns :: [String] = getHostSynsFor(lhsNt, top.flowEnv);

  top.errors <-
    if null(body.errors ++ ns.errors)
    && (top.config.warnAll || top.config.warnMissingInh)
    -- Must be a forwarding production:
    && !null(body.uniqueSignificantExpression)
    then flatMap(raiseImplicitFwdEqFlowTypesForProd(top.location, lhsNt, fName, _, top.flowEnv, fwdFlowDeps, myFlow), hostSyns)
    else [];
}
function raiseImplicitFwdEqFlowTypesForProd
[Message] ::= l::Location  lhsNt::String  prod::String  attr::String  e::Decorated FlowEnv  fwdFlowDeps::set:Set<String>  myFlow::EnvTree<FlowType>
{
  -- The flow type for `attr` on `lhsNt`
  local depsForThisAttr :: set:Set<String> = inhDepsForSyn(attr, lhsNt, myFlow);
  -- Actual forwards equation deps not in the flow type for `attr`
  local diff :: [String] = set:toList(set:difference(fwdFlowDeps, depsForThisAttr));

  return case lookupSyn(prod, attr, e) of
  | eq :: _ -> []
  | [] ->
      if null(diff) then []
      else 
      [wrn(l, s"In production ${prod}, the implicit copy equation for ${attr} (due to forwarding) would exceed the attribute's flow type because the production forward equation depends on ${implode(", ", diff)}")]
  end;
}

-- General TODO: we should probably find another way of generating errors,
-- so that we can eliminate these silly checks...
-- Perhaps put "namespaces" in errors? (Check from [Message] to ErrorSpace with multiple [Message]?)
-- Then we could 1. Issue normal errors; If none, 2. Issue syn-completeness errors; If none, 3. Issue inh-completeness errors


-- TODO: we check implicit forward equations above, but what about implicit equations from default equations!? TODO

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
          else [wrn(top.location, "Access of " ++ q.name ++ " from reference requires inherited attributes not known to be supplied to references: " ++ implode(", ", diff))]
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
                    ignoreIfAutoCopyOnLhs(top.frame.lhsNtName, top.env, _),
                    filter(
                      isEquationMissing(
                        lookupInh(top.frame.fullName, lq.lookupValue.fullName, _, top.flowEnv),
                        _),
                      set:toList(inhDepsForSyn(q.attrDcl.fullName, eTypeName, myFlow))))
             in if null(inhs) then []
                else [wrn(top.location, "Access of syn attribute " ++ q.name ++ " on " ++ e.unparse ++ " requires missing inherited attributes " ++ implode(", ", inhs) ++ " to be supplied")]
            end
          else []
      | localReference(lq) ->
          if lq.lookupValue.typerep.isDecorable
          then
            let inhs :: [String] = 
                  filter(
                    isEquationMissing(
                      lookupLocalInh(top.frame.fullName, lq.lookupValue.fullName, _, top.flowEnv),
                      _),
                    set:toList(inhDepsForSyn(q.attrDcl.fullName, eTypeName, myFlow)))
             in if null(inhs) then []
                else [wrn(top.location, "Access of syn attribute " ++ q.name ++ " on " ++ e.unparse ++ " requires missing inherited attributes " ++ implode(", ", inhs) ++ " to be supplied")]
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
          else [wrn(top.location, "Access of inherited attribute " ++ q.name ++ " from a reference is not permitted, as references are not known to be decorated with this attribute.")]
      end
    else [];      
}

aspect production decorateExprWith
top::Expr ::= 'decorate' e::Expr 'with' '{' inh::ExprInhs '}'
{
  -- Do nothing. Everything gets taken care of with anonResolve and checkEqDeps at the top
}

-- TODO: pattern variable accesses. WAIT. is this taken care of? this might be a stale TODO

aspect production attributeSection
top::Expr ::= '(' '.' q::QName ')'
{
  -- oh no again
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;

  -- We need to check that the flow sets are acceptable to what we're doing
  -- undecorated accesses: flow type for attribute has to be empty
  -- decorated accesses: FT has to be subset of refset
  local acceptable :: [String] =
    if inputType.isDecorated then inhsForTakingRef(inputType.typeName, top.flowEnv) else [];

  top.errors <- 
    if q.lookupAttribute.found
    && (top.config.warnAll || top.config.warnMissingInh)
    then
      let inhs :: [String] = 
            filter(
              \ x::String -> !contains(x, acceptable),
              set:toList(inhDepsForSyn(q.lookupAttribute.fullName, inputType.typeName, myFlow)))
       in if null(inhs) then []
          else [wrn(top.location, s"Attribute section (.${q.name}) requires attributes not known to be on '${prettyType(inputType)}': ${implode(", ", inhs)}")]
      end
    else [];
}


{--
 - For pattern matching, we have an obligation to check:
 - 1. If we invented an anon vertex type for the scrutinee, then it's a sink, and
 -    we need to check that nothing more than the ref set was depended upon.
 -}
aspect production matchPrimitiveReal
top::Expr ::= e::Expr t::TypeExpr pr::PrimPatterns f::Expr
{
  -- slightly awkward way to recover the name and whether/not it was invented
  local sinkVertexName :: Maybe<String> =
    case e.flowVertexInfo, pr.scrutineeVertexType of
    | noVertex(), anonVertexType(n) -> just(n)
    | _, _ -> nothing()
    end;

  -- TODO bug: expressions in globals don't have flow graphs. That's a mistake.
  -- We need to add edges due to flow types and 'flowDefs' from subexpressions
  -- to accurately raise errors. We need to fix this by ensuring ALL contexts get
  -- flow graphs built for them.

  -- These should be the only ones that can reference our anon sink
  local transitiveDeps :: [FlowVertex] =
    if top.frame.prodFlowGraph.isJust
    then expandGraph(top.flowDeps, top.frame.prodFlowGraph.fromJust)
    else top.flowDeps;
  
  pr.receivedDeps = transitiveDeps;

  -- just the deps on inhs of our sink
  local inhDeps :: [String] =
    toAnonInhs(transitiveDeps, sinkVertexName.fromJust, top.env);

  -- Subtract the ref set from our deps
  local diff :: [String] =
    set:toList(set:removeAll(
      inhsForTakingRef(e.typerep.typeName, top.flowEnv),
      set:add(inhDeps, set:empty(compareString))));

  top.errors <-
    if null(e.errors)
    && (top.config.warnAll || top.config.warnMissingInh)
    && sinkVertexName.isJust
    && !null(diff)
    then [wrn(e.location, "Pattern match on reference has transitive dependencies on " ++ implode(", ", diff) ++ " which are not known to be supplied to references")]
    else [];

}

function toAnonInhs
[String] ::= v::[FlowVertex]  vertex::String  env::Decorated Env
{
  return
    case v of
    | anonVertex(n, inh) :: tl ->
        if vertex == n && isInherited(inh, env)
        then inh :: toAnonInhs(tl, vertex, env)
        else toAnonInhs(tl, vertex, env)
    | _ :: tl -> toAnonInhs(tl, vertex, env)
    | [] -> []
    end;
}

autocopy attribute receivedDeps :: [FlowVertex] occurs on VarBinders, VarBinder, PrimPatterns, PrimPattern;

aspect production varVarBinder
top::VarBinder ::= n::Name
{
  -- fName is our invented vertex name for the pattern variable
  local requiredInhs :: [String] =
    toAnonInhs(top.receivedDeps, fName, top.env);

  -- Check for equation's existence:
  -- Prod: top.matchingAgainst.fromJust.fullName
  -- Child: top.bindingName
  -- Inh: each of requiredInhs
  local missingInhs :: [String] =
    filter(remoteProdMissingEq(top.matchingAgainst.fromJust, top.bindingName, _, top.env, top.flowEnv), requiredInhs);

  top.errors <-
    if (top.config.warnAll || top.config.warnMissingInh)
    && top.bindingType.isDecorable
    && top.matchingAgainst.isJust
    && !null(missingInhs)
    then [wrn(top.location, s"Pattern variable '${n.name}' has transitive dependencies with missing remote equations.\n\tRemote production: ${top.matchingAgainst.fromJust.fullName}\n\tChild: ${top.bindingName}\n\tMissing inherited equations for: ${implode(", ", missingInhs)}")]
    else [];
}

function remoteProdMissingEq
Boolean ::= prod::DclInfo  sigName::String  attrName::String  realEnv::Decorated Env  flowEnv::Decorated FlowEnv
{
  return
    null(lookupInh(prod.fullName, sigName, attrName, flowEnv)) && -- no equation
    ignoreIfAutoCopyOnLhs(prod.namedSignature.outputElement.typerep.typeName, realEnv, attrName); -- not autocopy (and on lhs)
}



