grammar silver:compiler:analysis:warnings:flow;

import silver:compiler:modification:list;

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
  flags <- [
    flagSpec(name="--warn-missing-inh", paramString=nothing(),
      help="warn about any of several MWDA violations involving demanding inhs",
      flagParser=flag(warnMissingInhFlag))];
}

--------------------------------------------------------------------------------

-- In this file:

-- CHECK 1: Exceeds flowtype
--   - Examine overall dependencies of an equation, and see if they use LHS inh
--     that are not permissible, given the equation's flow type.
--   - Accomplished by explicit calculations in each production.

-- CHECK 1b: Reference set exceeded checks
--   - Direct accesses from references need to be checked, they don't emit dependencies otherwise
--   - Attribute sections need special checking, too
--   - Pattern matching can create dependencies on references, too

-- CHECK 2: Effective Completeness
--   - Ensure each inherited attribute that's used actually has an equation
--     in existence.
--   - Consists of calls to `checkAllEqDeps`
--   - Pattern variable accesses can induced *remote* inherited equation checks

--------------------------------------------------------------------------------

{--
 - Given a name of a child, return whether it has a normal decorated nonterminal
 - type (covered by the more specific checks on accesses from references) or a
 - unique decorated nonterminal type decorated with the attr.
 - True if nonsensicle.
 -}
function sigAttrViaReference
Boolean ::= sigName::String  attrName::String  ns::NamedSignature  e::Env
{
  local ty :: Type = findNamedSigElemType(sigName, ns.inputElements);
  return !isDecorable(ty, e) || contains(attrName, getMinRefSet(ty, e));
}

{--
 - Given a name of a local, return whether it has a normal decorated nonterminal
 - type (covered by the more specific checks on accesses from references) or a
 - unique decorated nonterminal type decorated with the attr.
 - True if nonsensicle.
 -}
function localAttrViaReference
Boolean ::= sigName::String  attrName::String  e::Env
{
  local d :: [ValueDclInfo] = getValueDcl(sigName, e);
  local ty :: Type = head(d).typeScheme.typerep;

  return null(d) || !isDecorable(ty, e) || contains(attrName, getMinRefSet(ty, e));
}

{--
 - Used as a stop-gap measure to ensure equations exist.
 - Given a needed equation (represented by FlowVertex 'v'),
 - ensure such an equation exists, accounting for:
 -  1. Defaults
 -  2. Forwards
 -  3. Reference accesses
 - 
 - This gives rise to 'missing transitive dependency' errors.
 - The reason this exists is to handle 'taking a reference'
 - actions needing to ensure equations were actually provided for
 - things we reference.
 -
 - @param v  A value we need an equation for.
 - @param config  Command-line arguments, that affect error reporting
 - @param l  Where to report an error, if it's missing
 - @param prodName  The full name of the production we're in
 - @param prodNt  The nonterminal this production belongs to. (For functions, a dummy value is ok)
 - @param flowEnv  The local flow environment
 - @param realEnv  The local real environment
 - @returns  Errors for missing equations
 -}
function checkEqDeps
[Message] ::= v::FlowVertex  config::Decorated CmdArgs  prodName::String  flowEnv::FlowEnv  realEnv::Env  anonResolve::[Pair<String  Location>]
{
  -- We're concerned with missing inherited equations on RHS, LOCAL, and ANON. (Implicitly, FORWARD.)
  
  local prodDcl :: [ValueDclInfo] = getValueDcl(prodName, realEnv);
  local ns :: NamedSignature =
    case prodDcl of
    | d :: _ -> d.namedSignature
    | [] -> bogusNamedSignature()
    end;

  return case v of
  -- A dependency on an LHS.INH is a flow issue: these equations do not exist
  -- locally, so we cannot check them.
  | lhsInhVertex(_) -> []
  -- A dependency on an LHS.SYN can be checked locally, but we do not do so here.
  -- All productions must have all SYN equations, so those errors are raised elsewhere.
  | lhsSynVertex(attrName) -> []
  -- A dependency on an RHS.ATTR. SYN are always present, so we only care about INH here.
  -- Filter missing equations for RHS that are references or supplied through another decoration site.
  | rhsInhVertex(sigName, attrName) ->
      if !null(lookupInh(prodName, sigName, attrName, flowEnv))
      || sigAttrViaReference(sigName, attrName, ns, realEnv)
      || !null(lookupRefDecSite(prodName, sigName, flowEnv))
      || case splitTransAttrInh(attrName) of
         | just((transAttr, _)) -> !null(lookupTransRefDecSite(prodName, sigName, transAttr, flowEnv))
         | nothing() -> false
         end
      then []
      else [mwdaWrnAmbientOrigin(config, "Equation has transitive dependency on child " ++ sigName ++ "'s inherited attribute for " ++ attrName ++ " but this equation appears to be missing.")]
  | rhsSynVertex(sigName, attrName) -> []
  -- A dependency on a LOCAL. Technically, local equations may not exist!
  -- But let's just assume they do, since `local name :: type = expr;` is the prefered syntax.
  | localEqVertex(fName) -> []
  -- A dependency on a LOCAL.ATTR. SYN always exist again, so we only care about INH here.
  -- Ignore the FORWARD (a special case of LOCAL), which always has both SYN/INH.
  -- And again ignore references and additional decoration sites.
  | localInhVertex(fName, attrName) -> 
      if !null(lookupLocalInh(prodName, fName, attrName, flowEnv))
      || fName == "forward"
      || localAttrViaReference(fName, attrName, realEnv)
      || !null(lookupLocalRefDecSite(fName, flowEnv))
      || case splitTransAttrInh(attrName) of
         | just((transAttr, _)) -> !null(lookupLocalTransRefDecSite(fName, transAttr, flowEnv))
         | nothing() -> isForwardProdAttr(fName, realEnv)  -- Inh on trans are not copied with forwarding
         end
      then []
      else [mwdaWrnAmbientOrigin(config, "Equation has transitive dependency on local " ++ fName ++ "'s inherited attribute for " ++ attrName ++ " but this equation appears to be missing.")]
  | localSynVertex(fName, attrName) -> []
  -- A dependency on a ANON. This do always exist (`decorate expr with..` always has expr.)
  | anonEqVertex(fName) -> []
  -- A dependency on ANON.ATTR. Again, SYN are safe. We need to check only for INH.
  -- If the equation is missing, then we again filter down to just those equations
  -- missing within THIS overall equation.
  -- i.e. `top.syn1 = ... missing ...; top.syn2 = top.syn1;` should only raise
  -- the missing in the first equation.
  | anonInhVertex(fName, attrName) ->
      if !null(lookupLocalInh(prodName, fName, attrName, flowEnv))
      then []
      else let
        anonl :: Maybe<Location> = lookup(fName, anonResolve)
      in if anonl.isJust
        then [mwdaWrn(config, anonl.fromJust, "Decoration requires inherited attribute for " ++ attrName ++ ".")]
        else [] -- If it's not in the list, then it's a transitive dep from a DIFFERENT equation (and thus reported there)
      end
  | anonSynVertex(fName, attrName) -> []
  -- A dependency on a projected equation in another production.
  -- Again, SYN are safe. We need to check only for INH.
  | subtermInhVertex(parent, termProdName, sigName, attrName) ->
      if !remoteProdMissingInhEq(termProdName, sigName, attrName, flowEnv)
      then []
      else [mwdaWrnAmbientOrigin(config, s"Equation has transitive dependencies on a missing remote equation.\n\tRemote production: ${termProdName}\n\tChild: ${sigName}\n\tMissing inherited equations for: ${attrName}")]
  | subtermSynVertex(parent, termProdName, sigName, attrName) -> []
  end;
}
fun checkAllEqDeps
[Message] ::= v::[FlowVertex]  config::Decorated CmdArgs  prodName::String  flowEnv::FlowEnv  realEnv::Env  anonResolve::[Pair<String  Location>] =
  flatMap(checkEqDeps(_, config, prodName, flowEnv, realEnv, anonResolve), v);

{--
 - Look up flow types, either from the flow environment (for a nonterminal) or the occurs-on contexts (for a type var).
 - @param syn  A synthesized attribute's full name
 - @param t  The type to look up this attribute on
 - @param flow  The flow type environment (NOTE: TODO: this is currently 'myFlow' or something, NOT top.flowEnv)
 - @param ns    The named signature of the enclosing production
 - @param env   The regular (type) environment
 - @return A set of inherited attributes (if the inh dependencies for the attribute are bounded) and a list of type variables of kind InhSet,
 - needed to compute this synthesized attribute on this type.
 -}
function inhDepsForSynOnType
(Maybe<set:Set<String>>, [TyVar]) ::= syn::String  t::Type  flow::EnvTree<FlowType>  ns::NamedSignature env::Env
{
  local contexts::Contexts = foldContexts(ns.contexts);
  contexts.env = env;

  return
    if t.isNonterminal || (t.isDecorated && t.decoratedType.isNonterminal)
    then (just(inhDepsForSyn(syn, t.typeName, flow)), [])
    else (
      map(set:fromList, lookup(syn, lookupAll(t.typeName, contexts.occursContextInhDeps))),
      concat(lookupAll(syn, lookupAll(t.typeName, contexts.occursContextInhSetDeps))));
}


--------------------------------------------------------------------------------


aspect production globalValueDclConcrete
top::AGDcl ::= 'global' id::Name '::' cl::ConstraintList '=>' t::TypeExpr '=' e::Expr ';'
{
  local transitiveDeps :: [FlowVertex] = expandGraph(e.flowDeps, e.frame.flowGraph);

  top.errors <-
    if top.config.warnMissingInh
    then checkAllEqDeps(transitiveDeps, top.config, fName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs))
    else [];
}

aspect production defaultConstraintClassBodyItem
top::ClassBodyItem ::= id::Name '::' cl::ConstraintList '=>' ty::TypeExpr '=' e::Expr ';'
{
  local transitiveDeps :: [FlowVertex] = expandGraph(e.flowDeps, e.frame.flowGraph);

  top.errors <-
    if top.config.warnMissingInh
    then checkAllEqDeps(transitiveDeps, top.config, fName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs))
    else [];
}

aspect production instanceBodyItem
top::InstanceBodyItem ::= id::QName '=' e::Expr ';'
{
  local transitiveDeps :: [FlowVertex] = expandGraph(e.flowDeps, e.frame.flowGraph);

  top.errors <-
    if top.config.warnMissingInh
    then checkAllEqDeps(transitiveDeps, top.config, id.lookupValue.fullName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs))
    else [];
}

aspect production synthesizedAttributeDef
top::ProductionStmt ::= dl::Decorated! DefLHS  attr::Decorated! QNameAttrOccur  e::Expr
{
  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;

  local transitiveDeps :: [FlowVertex] =
    expandGraph(e.flowDeps, top.frame.flowGraph);
  
  local lhsInhDeps :: set:Set<String> = onlyLhsInh(transitiveDeps);
  local lhsInhExceedsFlowType :: [String] = set:toList(set:difference(lhsInhDeps, inhDepsForSyn(attr.attrDcl.fullName, top.frame.lhsNtName, myFlow)));

  top.errors <-
    if dl.found && attr.found && top.config.warnMissingInh
    then checkAllEqDeps(transitiveDeps, top.config, top.frame.fullName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs)) ++
      if null(lhsInhExceedsFlowType) then []
      else [mwdaWrnFromOrigin(top, "Synthesized equation " ++ attr.name ++ " exceeds flow type with dependencies on " ++ implode(", ", lhsInhExceedsFlowType))]
    else [];
}

aspect production inheritedAttributeDef
top::ProductionStmt ::= dl::Decorated! DefLHS  attr::Decorated! QNameAttrOccur  e::Expr
{
  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;

  local transitiveDeps :: [FlowVertex] = 
    expandGraph(e.flowDeps, top.frame.flowGraph);
  
  local lhsInhDeps :: set:Set<String> = onlyLhsInh(transitiveDeps);

  -- problem = lhsinh deps - fwd flow type - this inh attribute
  local lhsInhExceedsForwardFlowType :: [String] = 
    set:toList(
      set:removeAll(
        [dl.inhAttrName],
        set:difference(
          lhsInhDeps,
          inhDepsForSyn("forward", top.frame.lhsNtName, myFlow))));

  local refDecSiteInhDepsLhsInh :: Maybe<set:Set<String>> =
    map(\ deps::[FlowVertex] -> onlyLhsInh(expandGraph(deps, top.frame.flowGraph)), dl.refDecSiteInhDeps);

  -- problem = lhsinh deps - inh deps on dec site
  local lhsInhExceedsRefDecSiteDeps :: [String] =
    case refDecSiteInhDepsLhsInh of
      -- A unique reference is taken that doesn't include this attribute,
      -- make sure we aren't introducing any hidden transitive dependencies.
    | just(deps) -> set:toList(set:difference(lhsInhDeps, deps))
    | _ -> []
    end;

  top.errors <-
    if top.config.warnMissingInh
    then checkAllEqDeps(transitiveDeps, top.config, top.frame.fullName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs))
    else [];
  top.errors <-
    if top.config.warnMissingInh && dl.name == "forward" && !null(lhsInhExceedsForwardFlowType)
    then [mwdaWrnFromOrigin(top, "Forward inherited equation exceeds flow type with dependencies on " ++ implode(", ", lhsInhExceedsForwardFlowType))]
    else [];
  top.errors <-
    case dl.lhsUniqueRefs of
    | u :: _ when top.config.warnMissingInh && !null(lhsInhExceedsRefDecSiteDeps) ->
      [mwdaWrnFromOrigin(top,
        s"Inherited override equation may exceed a flow type with hidden transitive dependencies on ${implode(", ", lhsInhExceedsRefDecSiteDeps)}; " ++
        s"${attr.attrDcl.fullName} on the reference taken at ${u.sourceGrammar}:${u.sourceLocation.unparse} may be expected to depend only on ${implode(", ", set:toList(refDecSiteInhDepsLhsInh.fromJust))}")]
    | _ -> []
    end;
}

-- All unique references to the LHS's decoration site
synthesized attribute lhsUniqueRefs::[UniqueRefSite] occurs on DefLHS;

-- Minimum flow deps of this inherited attribute on any unique references to this decoration site
synthesized attribute refDecSiteInhDeps::Maybe<[FlowVertex]> occurs on DefLHS;

flowtype DefLHS = lhsUniqueRefs {grammarName, config, frame, env, flowEnv}, refDecSiteInhDeps {grammarName, config, frame, env, flowEnv, defLHSattr};

aspect default production
top::DefLHS ::=
{
  top.lhsUniqueRefs = [];
  top.refDecSiteInhDeps = nothing();
}
aspect production childDefLHS
top::DefLHS ::= q::Decorated! QName
{
  top.lhsUniqueRefs = lookupUniqueRefs(top.frame.fullName, q.lookupValue.fullName, top.flowEnv);
  top.refDecSiteInhDeps =
    case top.lhsUniqueRefs of
    | u :: _ when !contains(top.inhAttrName, u.refSet) -> just(
        u.refFlowDeps ++
        map(
          \ v::VertexType -> v.inhVertex(top.inhAttrName),
          lookupRefPossibleDecSites(top.frame.fullName, q.lookupValue.fullName, top.flowEnv)))
    | _ -> nothing()
    end;
}
aspect production localDefLHS
top::DefLHS ::= q::Decorated! QName
{
  top.lhsUniqueRefs = lookupLocalUniqueRefs(q.lookupValue.fullName, top.flowEnv);
  top.refDecSiteInhDeps =
    case top.lhsUniqueRefs of
    | u :: _ when !contains(top.inhAttrName, u.refSet) -> just(
        u.refFlowDeps ++
        map(
          \ v::VertexType -> v.inhVertex(top.inhAttrName),
          lookupLocalRefPossibleDecSites(q.lookupValue.fullName, top.flowEnv)))
    | _ -> nothing()
    end;
}
aspect production childTransAttrDefLHS
top::DefLHS ::= q::Decorated! QName  attr::Decorated! QNameAttrOccur
{
  local childUniqueRefs::[UniqueRefSite] =
    lookupUniqueRefs(top.frame.fullName, q.lookupValue.fullName, top.flowEnv);
  local childTransAttrUniqueRefs::[UniqueRefSite] =
    lookupTransUniqueRefs(top.frame.fullName, q.lookupValue.fullName, attr.attrDcl.fullName, top.flowEnv);
  top.lhsUniqueRefs = if !null(childUniqueRefs) then childUniqueRefs else childTransAttrUniqueRefs;
  top.refDecSiteInhDeps =
    case childUniqueRefs, childTransAttrUniqueRefs of
    | u :: _, _ when !contains(top.inhAttrName, u.refSet) -> just(
        u.refFlowDeps ++
        map(
          \ v::VertexType -> v.inhVertex(top.inhAttrName),
          lookupRefPossibleDecSites(top.frame.fullName, q.lookupValue.fullName, top.flowEnv)))
    | _, u :: _ when !contains(top.defLHSattr.attrDcl.fullName, u.refSet) -> just(
        u.refFlowDeps ++
        map(
          \ v::VertexType -> v.inhVertex(top.defLHSattr.attrDcl.fullName),
          lookupTransRefPossibleDecSites(top.frame.fullName, q.lookupValue.fullName, attr.attrDcl.fullName, top.flowEnv)))
    | _, _ -> nothing()
    end;
}
aspect production localTransAttrDefLHS
top::DefLHS ::= q::Decorated! QName  attr::Decorated! QNameAttrOccur
{
  local localUniqueRefs::[UniqueRefSite] =
    lookupUniqueRefs(top.frame.fullName, q.lookupValue.fullName, top.flowEnv);
  local localTransAttrUniqueRefs::[UniqueRefSite] =
    lookupTransUniqueRefs(top.frame.fullName, q.lookupValue.fullName, attr.attrDcl.fullName, top.flowEnv);
  top.lhsUniqueRefs = if !null(localUniqueRefs) then localUniqueRefs else localTransAttrUniqueRefs;
  top.refDecSiteInhDeps =
    case localUniqueRefs, localTransAttrUniqueRefs of
    | u :: _, _ when !contains(top.inhAttrName, u.refSet) -> just(
        u.refFlowDeps ++
        map(
          \ v::VertexType -> v.inhVertex(top.inhAttrName),
          lookupLocalRefPossibleDecSites(q.lookupValue.fullName, top.flowEnv)))
    | _, u :: _ when !contains(top.defLHSattr.attrDcl.fullName, u.refSet) -> just(
        u.refFlowDeps ++
        map(
          \ v::VertexType -> v.inhVertex(top.defLHSattr.attrDcl.fullName),
          lookupLocalTransRefPossibleDecSites(q.lookupValue.fullName, attr.attrDcl.fullName, top.flowEnv)))
    | _, _ -> nothing()
    end;
}

----- WARNING TODO BEGIN MASSIVE COPY & PASTE SESSION
aspect production synBaseColAttributeDef
top::ProductionStmt ::= dl::Decorated! DefLHS  attr::Decorated! QNameAttrOccur  e::Expr
{
  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;

  local transitiveDeps :: [FlowVertex] =
    expandGraph(e.flowDeps, top.frame.flowGraph);
  
  local lhsInhDeps :: set:Set<String> = onlyLhsInh(transitiveDeps);
  local lhsInhExceedsFlowType :: [String] = set:toList(set:difference(lhsInhDeps, inhDepsForSyn(attr.attrDcl.fullName, top.frame.lhsNtName, myFlow)));

  top.errors <-
    if dl.found && attr.found && top.config.warnMissingInh
    then checkAllEqDeps(transitiveDeps, top.config, top.frame.fullName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs)) ++
      if null(lhsInhExceedsFlowType) then []
      else [mwdaWrnFromOrigin(top, "Synthesized equation " ++ attr.name ++ " exceeds flow type with dependencies on " ++ implode(", ", lhsInhExceedsFlowType))]
    else [];
}
aspect production synAppendColAttributeDef
top::ProductionStmt ::= dl::Decorated! DefLHS  attr::Decorated! QNameAttrOccur  e::Expr
{
  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;

  local transitiveDeps :: [FlowVertex] =
    expandGraph(e.flowDeps, top.frame.flowGraph);
  
  local lhsInhDeps :: set:Set<String> = onlyLhsInh(transitiveDeps);
  local lhsInhExceedsFlowType :: [String] = set:toList(set:difference(lhsInhDeps, inhDepsForSyn(attr.attrDcl.fullName, top.frame.lhsNtName, myFlow)));

  top.errors <-
    if dl.found && attr.found && top.config.warnMissingInh
    then checkAllEqDeps(transitiveDeps, top.config, top.frame.fullName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs)) ++
      if null(lhsInhExceedsFlowType) then []
      else [mwdaWrnFromOrigin(top, "Synthesized equation " ++ attr.name ++ " exceeds flow type with dependencies on " ++ implode(", ", lhsInhExceedsFlowType))]
    else [];
}
aspect production inhBaseColAttributeDef
top::ProductionStmt ::= dl::Decorated! DefLHS  attr::Decorated! QNameAttrOccur  e::Expr
{
  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;

  local transitiveDeps :: [FlowVertex] = 
    expandGraph(e.flowDeps, top.frame.flowGraph);
  
  local lhsInhDeps :: set:Set<String> = onlyLhsInh(transitiveDeps);
  -- problem = lhsinh deps - fwd flow type - this inh attribute
  local lhsInhExceedsForwardFlowType :: [String] = 
    set:toList(
      set:removeAll(
        [dl.inhAttrName],
        set:difference(
          lhsInhDeps,
          inhDepsForSyn("forward", top.frame.lhsNtName, myFlow))));

  top.errors <-
    if top.config.warnMissingInh
    then checkAllEqDeps(transitiveDeps, top.config, top.frame.fullName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs)) ++
         if dl.name != "forward" || null(lhsInhExceedsForwardFlowType) then []
         else [mwdaWrnFromOrigin(top, "Forward inherited equation exceeds flow type with dependencies on " ++ implode(", ", lhsInhExceedsForwardFlowType))]
    else [];
}
aspect production inhAppendColAttributeDef
top::ProductionStmt ::= dl::Decorated! DefLHS  attr::Decorated! QNameAttrOccur  e::Expr
{
  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;

  local transitiveDeps :: [FlowVertex] = 
    expandGraph(e.flowDeps, top.frame.flowGraph);
  
  local lhsInhDeps :: set:Set<String> = onlyLhsInh(transitiveDeps);
  -- problem = lhsinh deps - fwd flow type - this inh attribute
  local lhsInhExceedsForwardFlowType :: [String] = 
    set:toList(
      set:removeAll(
        [dl.inhAttrName],
        set:difference(
          lhsInhDeps,
          inhDepsForSyn("forward", top.frame.lhsNtName, myFlow))));

  top.errors <-
    if top.config.warnMissingInh
    then checkAllEqDeps(transitiveDeps, top.config, top.frame.fullName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs)) ++
         if dl.name != "forward" || null(lhsInhExceedsForwardFlowType) then []
         else [mwdaWrnFromOrigin(top, "Forward inherited equation exceeds flow type with dependencies on " ++ implode(", ", lhsInhExceedsForwardFlowType))]
    else [];
}
------ END AWFUL COPY & PASTE SESSION

aspect production forwardsTo
top::ProductionStmt ::= 'forwards' 'to' e::Expr ';'
{
  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;

  local transitiveDeps :: [FlowVertex] =
    expandGraph(e.flowDeps, top.frame.flowGraph);
  
  local lhsInhDeps :: set:Set<String> = onlyLhsInh(transitiveDeps);
  local lhsInhExceedsFlowType :: [String] = set:toList(set:difference(lhsInhDeps, inhDepsForSyn("forward", top.frame.lhsNtName, myFlow)));

  top.errors <-
    if top.config.warnMissingInh
    then checkAllEqDeps(transitiveDeps, top.config, top.frame.fullName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs)) ++
         if null(lhsInhExceedsFlowType) then []
         else [mwdaWrnFromOrigin(top, "Forward equation exceeds flow type with dependencies on " ++ implode(", ", lhsInhExceedsFlowType))]
    else [];
}
aspect production forwardInh
top::ForwardInh ::= lhs::ForwardLHSExpr '=' e::Expr ';'
{
  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;

  local transitiveDeps :: [FlowVertex] =
    expandGraph(e.flowDeps, top.frame.flowGraph);
  
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
    if top.config.warnMissingInh
    then checkAllEqDeps(transitiveDeps, top.config, top.frame.fullName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs)) ++
         if null(lhsInhExceedsFlowType) then []
         else [mwdaWrnFromOrigin(top, "Forward inherited equation exceeds flow type with dependencies on " ++ implode(", ", lhsInhExceedsFlowType))]
    else [];
}

aspect production undecoratesTo
top::ProductionStmt ::= 'undecorates' 'to' e::Expr ';'
{
  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;

  local transitiveDeps :: [FlowVertex] =
    expandGraph(e.flowDeps, top.frame.flowGraph);
  
  local lhsInhDeps :: [String] = set:toList(onlyLhsInh(transitiveDeps));

  top.errors <-
    if top.config.warnMissingInh
    then checkAllEqDeps(transitiveDeps, top.config, top.frame.fullName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs)) ++
         if null(lhsInhDeps) then []
         else [mwdaWrnFromOrigin(top, "Undecorates equation has dependencies on " ++ implode(", ", lhsInhDeps))]
    else [];
}

aspect production localValueDef
top::ProductionStmt ::= val::Decorated! QName  e::Expr
{
  local transitiveDeps :: [FlowVertex] =
    expandGraph(e.flowDeps, top.frame.flowGraph);
  
  -- check transitive deps only. No worries about flow types.
  top.errors <-
    if top.config.warnMissingInh
    then checkAllEqDeps(transitiveDeps, top.config, top.frame.fullName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs))
    else [];
}

aspect production returnDef
top::ProductionStmt ::= 'return' e::Expr ';'
{
  local transitiveDeps :: [FlowVertex] =
    expandGraph(e.flowDeps, top.frame.flowGraph);

  top.errors <-
    if top.config.warnMissingInh
    then checkAllEqDeps(transitiveDeps, top.config, top.frame.fullName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs))
    else [];
}

aspect production attachNoteStmt
top::ProductionStmt ::= 'attachNote' e::Expr ';'
{
  local transitiveDeps :: [FlowVertex] =
    expandGraph(e.flowDeps, top.frame.flowGraph);

  top.errors <-
    if top.config.warnMissingInh
    then checkAllEqDeps(transitiveDeps, top.config, top.frame.fullName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs))
    else [];
}

-- Skipping `baseCollectionValueDef`: it forwards to `localValueDef`
-- Partially skipping `appendCollectionValueDef`: it likewise forwards
-- But we do have a special "exceeds check" to do here:
aspect production appendCollectionValueDef
top::ProductionStmt ::= val::Decorated! QName  e::Expr
{
  local productionFlowGraph :: ProductionGraph = top.frame.flowGraph;
  local transitiveDeps :: [FlowVertex] = expandGraph(e.flowDeps, productionFlowGraph);
  
  local originalEqDeps :: [FlowVertex] = 
    expandGraph([localEqVertex(val.lookupValue.fullName)], productionFlowGraph);
  
  local lhsInhDeps :: set:Set<String> = onlyLhsInh(transitiveDeps);
  
  local originalEqLhsInhDeps :: set:Set<String> = onlyLhsInh(originalEqDeps);
  
  local lhsInhExceedsFlowType :: [String] = set:toList(set:difference(lhsInhDeps, originalEqLhsInhDeps));

  top.errors <-
    if top.config.warnMissingInh
       -- We can ignore functions. We're checking LHS inhs here... functions don't have any!
    && top.frame.hasFullSignature
    then if null(lhsInhExceedsFlowType) then []
         else [mwdaWrnFromOrigin(top, "Local contribution (" ++ val.name ++ " <-) equation exceeds flow dependencies with: " ++ implode(", ", lhsInhExceedsFlowType))]
    else [];
}


--------------------------------------------------------------------------------


{-
Step 2: Let's go check on expressions. This has two purposes:
1. Better error messages for missing equations than the "transitive dependency" ones.
   But technically, unneeded and transititve dependencies are covering this.
2. We have to ensure that each individual access from a reference fits within the inferred reference set.
   Additionally we must check that wherever we take a reference, the required reference set is bounded.
   This is not covered by any other checks.
-}

aspect production childReference
top::Expr ::= q::Decorated! QName
{
    top.errors <-
    if top.config.warnMissingInh
    && isDecorable(q.lookupValue.typeScheme.typerep, top.env)
    then if refSet.isJust then []
         else [mwdaWrnFromOrigin(top, s"Cannot take a reference of type ${prettyType(top.finalType)}, as the reference set is not bounded.")]
    else [];
}
aspect production lhsReference
top::Expr ::= q::Decorated! QName
{
    top.errors <-
    if top.config.warnMissingInh
    then if refSet.isJust then []
         else [mwdaWrnFromOrigin(top, s"Cannot take a reference of type ${prettyType(top.finalType)}, as the reference set is not bounded.")]
    else [];
}
aspect production localReference
top::Expr ::= q::Decorated! QName
{
    top.errors <-
    if top.config.warnMissingInh
    && isDecorable(q.lookupValue.typeScheme.typerep, top.env)
    then if refSet.isJust then []
         else [mwdaWrnFromOrigin(top, s"Cannot take a reference of type ${prettyType(top.finalType)}, as the reference set is not bounded.")]
    else [];
}
aspect production forwardReference
top::Expr ::= q::Decorated! QName
{
    top.errors <-
    if top.config.warnMissingInh
    then if refSet.isJust then []
         else [mwdaWrnFromOrigin(top, s"Cannot take a reference of type ${prettyType(top.finalType)}, as the reference set is not bounded.")]
    else [];
}

aspect production forwardAccess
top::Expr ::= e::Expr '.' 'forward'
{
  -- TODO?
}

aspect production synDecoratedAccessHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  -- oh no again
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;

  local deps :: (Maybe<set:Set<String>>, [TyVar]) =
    inhDepsForSynOnType(q.attrDcl.fullName, e.finalType, myFlow, top.frame.signature, top.env);
  local inhDeps :: set:Set<String> = fromMaybe(set:empty(), deps.1);  -- Need to check that we have bounded inh deps, i.e. deps.1 == just(...)

-- This aspect is in two parts. First: we *must* check that any accesses
-- on a unknown decorated tree are in the ref-set.
  local acceptable :: ([String], [TyVar]) =
    case e.finalType of
    | decoratedType(_, i) -> getMinInhSetMembers([], i, top.env)
    | _ -> ([], [])
    end;
  local diff :: [String] =
    set:toList(set:removeAll(acceptable.1,  -- blessed inhs for a reference
      inhDeps)); -- needed inhs
  
  -- CASE 1: References. This check is necessary and won't be caught elsewhere.
  top.errors <- 
    if null(e.errors)
    && top.config.warnMissingInh
    then
      case e.flowVertexInfo of
      -- We don't track dependencies on inh sets transitively, so we need to check that the inh deps are bounded here;
      -- an access with unbounded inh deps only ever makes sense on a reference. 
      | just(_) ->
          if deps.1.isJust then []
          else [mwdaWrnFromOrigin(top, "Access of " ++ q.name ++ " from " ++ prettyType(e.finalType) ++ " requires an unbounded set of inherited attributes")]
      -- without a vertex, we're accessing from a reference, and so...
      | nothing() ->
          if any(map(contains(_, deps.2), acceptable.2)) then []  -- The deps are supplied as a common InhSet var
          -- We didn't find the deps as an InhSet var
          else if null(diff)
            then if deps.fst.isJust then []  -- We have a bound on the inh deps, and they are all present
            -- We don't have a bound on the inh deps, flag the unsatisfied InhSet deps
            else if null(acceptable.2)
            then [mwdaWrnFromOrigin(top, "Access of " ++ q.name ++ " from " ++ prettyType(e.finalType) ++ " requires an unbounded set of inherited attributes")]
            else [mwdaWrnFromOrigin(top, "Access of " ++ q.name ++ " from reference of type " ++ prettyType(e.finalType) ++ " requires one of the following sets of inherited attributes not known to be supplied to this reference: " ++ implode(", ", map(findAbbrevFor(_, top.frame.signature.freeVariables), deps.snd)))]
          -- We didn't find the inh deps
          else [mwdaWrnFromOrigin(top, "Access of " ++ q.name ++ " from reference of type " ++ prettyType(e.finalType) ++ " requires inherited attributes not known to be supplied to this reference: " ++ implode(", ", diff))]
      end
    else [];

----------------

  -- CASE 2: More specific errors for things already caught by `checkAllEqDeps`.
  -- Equation has transitive dep on `i`, but here we can say where this dependency
  -- originated: from a syn access.
  top.errors <- 
    if null(e.errors) && top.config.warnMissingInh
    then
      case e of
      | childReference(lq) ->
          if isDecorable(lq.lookupValue.typeScheme.typerep, top.env) &&
             null(lookupRefDecSite(top.frame.fullName, lq.lookupValue.fullName, top.flowEnv))  -- Decoration site projection, covered by checkAllEqDeps
          then
            let inhs :: [String] =
                filter(\ attr::String ->
                  case splitTransAttrInh(attr) of
                  -- If the dep is for an inh on a trans attribute, check for a decoration site projection for the trans attribute
                  | just((transAttr, _)) -> null(lookupTransRefDecSite(top.frame.fullName, lq.lookupValue.fullName, transAttr, top.flowEnv))
                  | _ -> true
                  end,
                  filter(
                    isEquationMissing(
                      lookupInh(top.frame.fullName, lq.lookupValue.fullName, _, top.flowEnv),
                      _),
                    removeAll(getMinRefSet(lq.lookupValue.typeScheme.typerep, top.env),
                      set:toList(inhDeps))))
             in if null(inhs) then []
                else [mwdaWrnFromOrigin(top, "Access of syn attribute " ++ q.name ++ " on " ++ e.unparse ++ " requires missing inherited attributes " ++ implode(", ", inhs) ++ " to be supplied")]
            end
          else []
      | localReference(lq) ->
          if isDecorable(lq.lookupValue.typeScheme.typerep, top.env) &&
             null(lookupLocalRefDecSite(lq.lookupValue.fullName, top.flowEnv))  -- Decoration site projection, covered by checkAllEqDeps
          then
            let inhs :: [String] = 
                filter(\ attr::String ->
                  case splitTransAttrInh(attr) of
                  -- If the dep is for an inh on a trans attribute, check for a decoration site projection for the trans attribute
                  | just((transAttr, _)) -> null(lookupLocalTransRefDecSite(lq.lookupValue.fullName, transAttr, top.flowEnv))
                  -- If the dep is for a normal inh attribute, ignore if the local is a forward production attribute
                  | nothing() -> !lq.lookupValue.dcl.hasForward
                  end,
                  filter(
                    isEquationMissing(
                      lookupLocalInh(top.frame.fullName, lq.lookupValue.fullName, _, top.flowEnv),
                      _),
                    removeAll(getMinRefSet(lq.lookupValue.typeScheme.typerep, top.env),
                      set:toList(inhDeps))))
             in if null(inhs) then []
                else [mwdaWrnFromOrigin(top, "Access of syn attribute " ++ q.name ++ " on " ++ e.unparse ++ " requires missing inherited attributes " ++ implode(", ", inhs) ++ " to be supplied")]
            end
          else []
      | _ -> []
    end
    else [];
}

aspect production inhDecoratedAccessHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  -- In this case, ONLY check for references.
  -- The transitive deps error will be less difficult to figure out when there's
  -- an explicit access to the attributes.
  top.errors <- 
    if null(e.errors) && top.config.warnMissingInh
    then
      case e.flowVertexInfo of
      | just(_) -> [] -- no check to make, as it was done transitively
      -- without a vertex, we're accessing from a reference, and so...
      | nothing() ->
          if contains(q.attrDcl.fullName, getMinRefSet(e.finalType, top.env))
          then []
          else [mwdaWrnFromOrigin(top, "Access of inherited attribute " ++ q.name ++ " on reference of type " ++ prettyType(e.finalType) ++ " is not permitted")]
      end
    else [];
}

aspect production transDecoratedAccessHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  -- oh no again
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;

  local deps :: (Maybe<set:Set<String>>, [TyVar]) =
    inhDepsForSynOnType(q.attrDcl.fullName, e.finalType, myFlow, top.frame.signature, top.env);
  local inhDeps :: set:Set<String> =
    -- Inh deps for computing this syn attribute
    fromMaybe(set:empty(), deps.1) ++  -- Need to check that we have bounded inh deps, i.e. deps.1 == just(...)
    -- When taking a reference to this translation attribute access, we depend on the ref set inhs on e.
    set:fromList(map(\ inh::String -> s"${q.attrDcl.fullName}.${inh}", fromMaybe([], refSet)));

  -- Need to check that the reference set is bounded when taking a reference, as with locals/children/etc.
  top.errors <-
    if top.config.warnMissingInh
    then if refSet.isJust then []
         else [mwdaWrnFromOrigin(top, s"Cannot take a reference of type ${prettyType(e.finalType)}, as the reference set is not bounded.")]
    else [];

  -- TODO: check that reference set is only inhs?

  -- This logic exactly mirrors synDecoratedAccessHandler, except with inhDeps containing extra inh dependencies from taking a reference.

-- This aspect is in two parts. First: we *must* check that any accesses
-- on a unknown decorated tree are in the ref-set.
  local acceptable :: ([String], [TyVar]) =
    case e.finalType of
    | decoratedType(_, i) -> getMinInhSetMembers([], i, top.env)
    | _ -> ([], [])
    end;
  local diff :: [String] =
    set:toList(set:removeAll(acceptable.1,  -- blessed inhs for a reference
      inhDeps)); -- needed inhs
  
  -- CASE 1: References. This check is necessary and won't be caught elsewhere.
  top.errors <- 
    if null(e.errors)
    && top.config.warnMissingInh
    then
      case e.flowVertexInfo of
      -- We don't track dependencies on inh sets transitively, so we need to check that the inh deps are bounded here;
      -- an access with unbounded inh deps only ever makes sense on a reference. 
      | just(_) ->
          if deps.1.isJust then []
          else [mwdaWrnFromOrigin(top, "Access of " ++ q.name ++ " from " ++ prettyType(e.finalType) ++ " requires an unbounded set of inherited attributes")]
      -- without a vertex, we're accessing from a reference, and so...
      | nothing() ->
          if any(map(contains(_, deps.2), acceptable.2)) then []  -- The deps are supplied as a common InhSet var
          -- We didn't find the deps as an InhSet var
          else if null(diff)
            then if deps.fst.isJust then []  -- We have a bound on the inh deps, and they are all present
            -- We don't have a bound on the inh deps, flag the unsatisfied InhSet deps
            else if null(acceptable.2)
            then [mwdaWrnFromOrigin(top, "Access of " ++ q.name ++ " from " ++ prettyType(e.finalType) ++ " requires an unbounded set of inherited attributes")]
            else [mwdaWrnFromOrigin(top, "Access of " ++ q.name ++ " from reference of type " ++ prettyType(e.finalType) ++ " requires one of the following sets of inherited attributes not known to be supplied to this reference: " ++ implode(", ", map(findAbbrevFor(_, top.frame.signature.freeVariables), deps.snd)))]
          -- We didn't find the inh deps
          else [mwdaWrnFromOrigin(top, "Access of " ++ q.name ++ " from reference of type " ++ prettyType(e.finalType) ++ " requires inherited attributes not known to be supplied to this reference: " ++ implode(", ", diff))]
      end
    else [];

----------------

  -- CASE 2: More specific errors for things already caught by `checkAllEqDeps`.
  -- Equation has transitive dep on `i`, but here we can say where this dependency
  -- originated: from a syn access.
  top.errors <- 
    if null(e.errors) && top.config.warnMissingInh
    then
      case e of
      | childReference(lq) ->
          if isDecorable(lq.lookupValue.typeScheme.typerep, top.env) &&
             null(lookupRefDecSite(top.frame.fullName, lq.lookupValue.fullName, top.flowEnv))  -- Decoration site projection, covered by checkAllEqDeps
          then
            let inhs :: [String] =
                filter(\ attr::String ->
                  case splitTransAttrInh(attr) of
                  -- If the dep is for an inh on a trans attribute, check for a decoration site projection for the trans attribute
                  | just((transAttr, _)) -> null(lookupTransRefDecSite(top.frame.fullName, lq.lookupValue.fullName, transAttr, top.flowEnv))
                  | _ -> true
                  end,
                  filter(
                    isEquationMissing(
                      lookupInh(top.frame.fullName, lq.lookupValue.fullName, _, top.flowEnv),
                      _),
                    removeAll(getMinRefSet(lq.lookupValue.typeScheme.typerep, top.env),
                      set:toList(inhDeps))))
             in if null(inhs) then []
                else [mwdaWrnFromOrigin(top, "Access of syn attribute " ++ q.name ++ " on " ++ e.unparse ++ " requires missing inherited attributes " ++ implode(", ", inhs) ++ " to be supplied")]
            end
          else []
      | localReference(lq) ->
          if isDecorable(lq.lookupValue.typeScheme.typerep, top.env) &&
             null(lookupLocalRefDecSite(lq.lookupValue.fullName, top.flowEnv))  -- Decoration site projection, covered by checkAllEqDeps
          then
            let inhs :: [String] = 
                filter(\ attr::String ->
                  case splitTransAttrInh(attr) of
                  -- If the dep is for an inh on a trans attribute, check for a decoration site projection for the trans attribute
                  | just((transAttr, _)) -> null(lookupLocalTransRefDecSite(lq.lookupValue.fullName, transAttr, top.flowEnv))
                  -- If the dep is for a normal inh attribute, ignore if the local is a forward production attribute
                  | nothing() -> !lq.lookupValue.dcl.hasForward
                  end,
                  filter(
                    isEquationMissing(
                      lookupLocalInh(top.frame.fullName, lq.lookupValue.fullName, _, top.flowEnv),
                      _),
                    removeAll(getMinRefSet(lq.lookupValue.typeScheme.typerep, top.env),
                      set:toList(inhDeps))))
             in if null(inhs) then []
                else [mwdaWrnFromOrigin(top, "Access of syn attribute " ++ q.name ++ " on " ++ e.unparse ++ " requires missing inherited attributes " ++ implode(", ", inhs) ++ " to be supplied")]
            end
          else []
      | _ -> []
    end
    else [];
}

aspect production decorateExprWith
top::Expr ::= 'decorate' e::Expr 'with' '{' inh::ExprInhs '}'
{
  -- Do nothing. Everything gets taken care of with anonResolve and checkEqDeps at the top-level of the equation
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
    | nothing(), anonVertexType(n) -> just(n)
    | _, _ -> nothing()
    end;

  -- These should be the only ones that can reference our anon sink
  local transitiveDeps :: [FlowVertex] =
    expandGraph(top.flowDeps, top.frame.flowGraph);
  
  pr.receivedDeps = transitiveDeps;

  -- just the deps on inhs of our sink
  local inhDeps :: [String] = toAnonInhs(transitiveDeps, sinkVertexName.fromJust);

  -- Subtract the ref set from our deps
  local diff :: [String] =
    set:toList(set:removeAll(getMinRefSet(scrutineeType, top.env), set:add(inhDeps, set:empty())));

  top.errors <-
    if null(e.errors)
    && top.config.warnMissingInh
    && sinkVertexName.isJust
    && !null(diff)
    then [mwdaWrnFromOrigin(e, "Pattern match on reference of type " ++ prettyType(scrutineeType) ++ " has transitive dependencies on " ++ implode(", ", diff))]
    else [];

}

fun toAnonInhs [String] ::= vs::[FlowVertex]  vertex::String =
  filterMap(\ v::FlowVertex ->
    case v of
    | anonInhVertex(n, inh) when n == vertex -> just(inh)
    | _ -> nothing()
    end, vs);

inherited attribute receivedDeps :: [FlowVertex] occurs on VarBinders, VarBinder, PrimPatterns, PrimPattern;
propagate receivedDeps on VarBinders, VarBinder, PrimPatterns, PrimPattern;

aspect production varVarBinder
top::VarBinder ::= n::Name
{
  -- Check that we're not taking an unbounded reference
  top.errors <-
    if top.config.warnMissingInh
    && isDecorable(top.bindingType, top.env)
    then if refSet.isJust then []
         else [mwdaWrnFromOrigin(top, s"Cannot take a reference of type ${prettyType(finalTy)}, as the reference set is not bounded.")]
    else [];

  -- fName is our invented vertex name for the pattern variable
  local requiredInhs :: [String] = toAnonInhs(top.receivedDeps, fName);

  -- Check for equation's existence:
  -- Prod: top.matchingAgainst.fromJust.fullName
  -- Child: top.bindingName
  -- Inh: each of requiredInhs
  local missingInhs :: [String] =
    filter(remoteProdMissingInhEq(top.matchingAgainst.fromJust.fullName, top.bindingName, _, top.flowEnv),
      removeAll(getMinRefSet(top.bindingType, top.env), requiredInhs));

  top.errors <-
    if top.config.warnMissingInh
    && isDecorable(top.bindingType, top.env)
    && top.matchingAgainst.isJust
    && !null(missingInhs)
    then [mwdaWrnFromOrigin(top, s"Pattern variable '${n.name}' has transitive dependencies with missing remote equations.\n\tRemote production: ${top.matchingAgainst.fromJust.fullName}\n\tChild: ${top.bindingName}\n\tMissing inherited equations for: ${implode(", ", missingInhs)}")]
    else [];
}

-- Is this there an equation for this inh attr on any decoration site for this child?
fun remoteProdMissingInhEq
Boolean ::= prodName::String  sigName::String  attrName::String  flowEnv::FlowEnv = !any(unzipWith(
    vertexHasInhEq(_, _, attrName, flowEnv),
    lookupAllDecSites(prodName, rhsVertexType(sigName), flowEnv)));

-- Find all decoration sites productions/vertices for this vertex
fun lookupAllDecSites [(String, VertexType)] ::= prodName::String  vt::VertexType  flowEnv::FlowEnv =
  (prodName, vt) ::
  case vt of
  | lhsVertexType_real() -> []
  | rhsVertexType(sigName) ->
    flatMap(lookupAllDecSites(prodName, _, flowEnv), lookupRefDecSite(prodName, sigName, flowEnv))
  | localVertexType(fName) ->
    flatMap(lookupAllDecSites(prodName, _, flowEnv), lookupLocalRefDecSite(fName, flowEnv))
  | transAttrVertexType(rhsVertexType(sigName), transAttr) ->
    flatMap(lookupAllDecSites(prodName, _, flowEnv), lookupTransRefDecSite(prodName, sigName, transAttr, flowEnv))
  | transAttrVertexType(localVertexType(fName), transAttr) ->
    flatMap(lookupAllDecSites(prodName, _, flowEnv), lookupLocalTransRefDecSite(fName, transAttr, flowEnv))
  | transAttrVertexType(_, _) -> []
  | anonVertexType(fName) -> []
  | forwardVertexType_real() -> []
  | subtermVertexType(_, remoteProdName, sigName) ->
    lookupAllDecSites(remoteProdName, rhsVertexType(sigName), flowEnv)
  end;

fun vertexHasInhEq Boolean ::= prodName::String  vt::VertexType  attrName::String  flowEnv::FlowEnv =
  case vt of
  | rhsVertexType(sigName) -> !null(lookupInh(prodName, sigName, attrName, flowEnv))
  | localVertexType(fName) -> !null(lookupLocalInh(prodName, fName, attrName, flowEnv))
  | transAttrVertexType(rhsVertexType(sigName), transAttr) ->
    !null(lookupInh(prodName, sigName, s"${transAttr}.${attrName}", flowEnv))
  | transAttrVertexType(localVertexType(fName), transAttr) ->
    !null(lookupLocalInh(prodName, fName, s"${transAttr}.${attrName}", flowEnv))
  | transAttrVertexType(_, _) -> false
  | anonVertexType(fName) -> !null(lookupLocalInh(prodName, fName, attrName, flowEnv))
  | subtermVertexType(_, remoteProdName, sigName) ->
    vertexHasInhEq(remoteProdName, rhsVertexType(sigName), attrName, flowEnv)
  -- This is a tricky case since we don't know what decorated this prod.
  -- checkEqDeps can count on missing LHS inh eqs being caught as flow issues elsewhere,
  -- but here we are remotely looking for equations that might not be the direct dependency of
  -- anything in the prod flow graph.
  | lhsVertexType_real() -> false  -- Shouldn't ever be directly needed, since the LHS is never the dec site for another vertex.
  | forwardVertexType_real() -> false  -- Same as LHS, but we can check this if e.g. forwarding to a child.
  end;

-- In places where we solve a synthesized attribute occurs-on context,
-- check that the actual deps for the attribute do not exceed the one specified for the context.
aspect production synOccursContext
top::Context ::= attr::String args::[Type] atty::Type inhs::Type ntty::Type
{
  -- oh no again
  production myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;

  -- The logic here mirrors the reference case in synDecoratedAccessHandler
  local deps :: (Maybe<set:Set<String>>, [TyVar]) =
    inhDepsForSynOnType(attr, ntty, myFlow, top.frame.signature, top.env);
  local inhDeps :: set:Set<String> = fromMaybe(set:empty(), deps.1);  -- Need to check that we have bounded inh deps, i.e. deps.1 == just(...)

  local acceptable :: ([String], [TyVar]) = getMinInhSetMembers([], inhs, top.env);
  local diff :: [String] = set:toList(set:removeAll(acceptable.1, inhDeps));

  top.contextErrors <-
    if top.config.warnMissingInh
    && null(ntty.freeFlexibleVars) && null(inhs.freeFlexibleVars)
    && !null(top.resolvedOccurs)
    then
      if any(map(contains(_, deps.2), acceptable.2)) then []  -- The deps are supplied as a common InhSet var
      -- We didn't find the deps as an InhSet var
      else if null(diff)
        then if deps.1.isJust then []  -- We have a bound on the inh deps, and they are all present
        -- We don't have a bound on the inh deps, flag the unsatisfied InhSet deps
        else if null(acceptable.2)
        then [mwdaWrn(top.config, top.contextLoc, s"The instance for ${prettyContext(top)} (arising from ${top.contextSource}) depends on an unbounded set of inherited attributes")]
        else [mwdaWrn(top.config, top.contextLoc, s"The instance for ${prettyContext(top)} (arising from ${top.contextSource}) exceeds the flow type constraint with dependencies on one of the following sets of inherited attributes: " ++ implode(", ", map(findAbbrevFor(_, top.frame.signature.freeVariables), deps.2)))]
      -- We didn't find the inh deps
      else [mwdaWrn(top.config, top.contextLoc, s"The instance for ${prettyContext(top)} (arising from ${top.contextSource}) has a flow type exceeding the constraint with dependencies on " ++ implode(", ", diff))]
   else [];
}

--------------------------------------------------------------------------------

-- TODO: There are a few final places where we need to `checkEqDeps` for the sake of `anonVertex`s

-- action blocks (production, terminal, disam, etc)

-- But we don't create flowEnv information for these locations so they can't be checked... oops
-- (e.g. `checkEqDeps` wants a production fName to look things up about.)


