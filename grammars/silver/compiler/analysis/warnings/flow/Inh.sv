grammar silver:compiler:analysis:warnings:flow;

import silver:compiler:extension:list;

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
 - False if 'attr' is an autocopy attribute, occurs on the LHS nonterminal,
 - and child 'sigName' is a nonterminal (not a type var with an occurs-on context);
 - true otherwise.  Used in conjunction with 'filter' to get
 - remove "missing equations" that are actually implicit autocopies.
 -}
function ignoreIfAutoCopyOnLhs
Boolean ::= sigName::String  ns::NamedSignature  env::Decorated Env  attr::String
{
  return !(isAutocopy(attr, env) && !null(getOccursDcl(attr, ns.outputElement.typerep.typeName, env)) &&
           -- Only ignore autocopies if the sig item is a nonterminal and not a type variable with an occurs-on context
           findNamedSigElemType(sigName, ns.inputElements).isNonterminal);
}

{--
 - Given a name of a child, return whether it has an undecorated
 - nonterminal type. False if nonsensicle.
 -}
function sigNotAReference
Boolean ::= sigName::String  ns::NamedSignature  e::Decorated Env
{
  return isDecorable(findNamedSigElemType(sigName, ns.inputElements), e);
}

{--
 - Given a name of a local, return whether it has an undecorated
 - nonterminal type. False if nonsensicle.
 -}
function localNotAReference
Boolean ::= sigName::String  e::Decorated Env
{
  local d :: [ValueDclInfo] = getValueDcl(sigName, e);

  return if null(d) then true else isDecorable(head(d).typeScheme.typerep, e);
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
 - @param config  Command-line arguments, that affect error reporting
 - @param l  Where to report an error, if it's missing
 - @param prodName  The full name of the production we're in
 - @param prodNt  The nonterminal this production belongs to. (For functions, a dummy value is ok)
 - @param flowEnv  The local flow environment
 - @param realEnv  The local real environment
 - @returns  Errors for missing equations
 -}
function checkEqDeps
[Message] ::= v::FlowVertex  config::Decorated CmdArgs  l::Location  prodName::String  flowEnv::FlowEnv  realEnv::Decorated Env  anonResolve::[Pair<String  Location>]
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
  -- Filter missing equations for autocopy or for RHS that are references.
  | rhsVertex(sigName, attrName) ->
      if isInherited(attrName, realEnv)
      then if !null(lookupInh(prodName, sigName, attrName, flowEnv))
           || !ignoreIfAutoCopyOnLhs(sigName, ns, realEnv, attrName)
           || !sigNotAReference(sigName, ns, realEnv)
           then []
           else [mwdaWrn(config, l, "Equation has transitive dependency on child " ++ sigName ++ "'s inherited attribute for " ++ attrName ++ " but this equation appears to be missing.")]
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
           || !localNotAReference(fName, realEnv)
           then []
           else [mwdaWrn(config, l, "Equation has transitive dependency on local " ++ fName ++ "'s inherited attribute for " ++ attrName ++ " but this equation appears to be missing.")]
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
             anonl :: Maybe<Location> = lookup(fName, anonResolve)
           in if anonl.isJust
              then [mwdaWrn(config, anonl.fromJust, "Decoration requires inherited attribute for " ++ attrName ++ ".")]
              else [] -- If it's not in the list, then it's a transitive dep from a DIFFERENT equation (and thus reported there)
           end
      else []
  end;
}
function checkAllEqDeps
[Message] ::= v::[FlowVertex]  config::Decorated CmdArgs  l::Location  prodName::String  flowEnv::FlowEnv  realEnv::Decorated Env  anonResolve::[Pair<String  Location>]
{
  return flatMap(checkEqDeps(_, config, l, prodName, flowEnv, realEnv, anonResolve), v);
}

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
(Maybe<set:Set<String>>, [TyVar]) ::= syn::String  t::Type  flow::EnvTree<FlowType>  ns::NamedSignature env::Decorated Env
{
  local contexts::Contexts = foldContexts(ns.contexts);
  contexts.env = env;
  
  local isList::Boolean =
    case t of
    | appType(listCtrType(), _) -> true
    | decoratedType(appType(listCtrType(), _), _) -> true
    | _ -> false
    end;

  return
    if t.isNonterminal || (t.isDecorated && t.decoratedType.isNonterminal)
    then (just(inhDepsForSyn(syn, t.typeName, flow)), [])
    -- TODO: Hack for lists, since these pretend not to be not be nonterminals yet use attributes in the nonspecialized implementation.
    -- These attributes (i_headList, i_tailList, i_nullList, i_lengthList) have no inherited dependencies.
    -- This can go away if we make lists a built-in/modification and scrap the unspecialized implementations.
    else if isList then (just(mempty), [])
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
    then checkAllEqDeps(transitiveDeps, top.config, top.location, fName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs))
    else [];
}

aspect production defaultConstraintClassBodyItem
top::ClassBodyItem ::= id::Name '::' cl::ConstraintList '=>' ty::TypeExpr '=' e::Expr ';'
{
  local transitiveDeps :: [FlowVertex] = expandGraph(e.flowDeps, e.frame.flowGraph);

  top.errors <-
    if top.config.warnMissingInh
    then checkAllEqDeps(transitiveDeps, top.config, top.location, fName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs))
    else [];
}

aspect production instanceBodyItem
top::InstanceBodyItem ::= id::QName '=' e::Expr ';'
{
  local transitiveDeps :: [FlowVertex] = expandGraph(e.flowDeps, e.frame.flowGraph);

  top.errors <-
    if top.config.warnMissingInh
    then checkAllEqDeps(transitiveDeps, top.config, top.location, id.lookupValue.fullName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs))
    else [];
}

aspect production synthesizedAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;

  local transitiveDeps :: [FlowVertex] =
    expandGraph(e.flowDeps, top.frame.flowGraph);
  
  local lhsInhDeps :: set:Set<String> = onlyLhsInh(transitiveDeps);
  local lhsInhExceedsFlowType :: [String] = set:toList(set:difference(lhsInhDeps, inhDepsForSyn(attr.attrDcl.fullName, top.frame.lhsNtName, myFlow)));

  top.errors <-
    if dl.found && attr.found && top.config.warnMissingInh
    then checkAllEqDeps(transitiveDeps, top.config, top.location, top.frame.fullName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs)) ++
      if null(lhsInhExceedsFlowType) then []
      else [mwdaWrn(top.config, top.location, "Synthesized equation " ++ attr.name ++ " exceeds flow type with dependencies on " ++ implode(", ", lhsInhExceedsFlowType))]
    else [];
}

aspect production inheritedAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  local transitiveDeps :: [FlowVertex] = 
    expandGraph(e.flowDeps, top.frame.flowGraph);
  
  -- TODO: if LHS is forward, we have to check that we aren't exceeding flow type!! (BUG)
  
  -- check transitive deps only. Nothing to check for flow types
  top.errors <-
    if top.config.warnMissingInh
    then checkAllEqDeps(transitiveDeps, top.config, top.location, top.frame.fullName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs))
    else [];
}

----- WARNING TODO BEGIN MASSIVE COPY & PASTE SESSION
aspect production synBaseColAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;

  local transitiveDeps :: [FlowVertex] =
    expandGraph(e.flowDeps, top.frame.flowGraph);
  
  local lhsInhDeps :: set:Set<String> = onlyLhsInh(transitiveDeps);
  local lhsInhExceedsFlowType :: [String] = set:toList(set:difference(lhsInhDeps, inhDepsForSyn(attr.attrDcl.fullName, top.frame.lhsNtName, myFlow)));

  top.errors <-
    if dl.found && attr.found && top.config.warnMissingInh
    then checkAllEqDeps(transitiveDeps, top.config, top.location, top.frame.fullName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs)) ++
      if null(lhsInhExceedsFlowType) then []
      else [mwdaWrn(top.config, top.location, "Synthesized equation " ++ attr.name ++ " exceeds flow type with dependencies on " ++ implode(", ", lhsInhExceedsFlowType))]
    else [];
}
aspect production synAppendColAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;

  local transitiveDeps :: [FlowVertex] =
    expandGraph(e.flowDeps, top.frame.flowGraph);
  
  local lhsInhDeps :: set:Set<String> = onlyLhsInh(transitiveDeps);
  local lhsInhExceedsFlowType :: [String] = set:toList(set:difference(lhsInhDeps, inhDepsForSyn(attr.attrDcl.fullName, top.frame.lhsNtName, myFlow)));

  top.errors <-
    if dl.found && attr.found && top.config.warnMissingInh
    then checkAllEqDeps(transitiveDeps, top.config, top.location, top.frame.fullName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs)) ++
      if null(lhsInhExceedsFlowType) then []
      else [mwdaWrn(top.config, top.location, "Synthesized equation " ++ attr.name ++ " exceeds flow type with dependencies on " ++ implode(", ", lhsInhExceedsFlowType))]
    else [];
}
aspect production inhBaseColAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  local transitiveDeps :: [FlowVertex] =
    expandGraph(e.flowDeps, top.frame.flowGraph);
  
  -- check transitive deps only. Nothing to be done for flow types
  top.errors <-
    if top.config.warnMissingInh
    then checkAllEqDeps(transitiveDeps, top.config, top.location, top.frame.fullName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs))
    else [];
}
aspect production inhAppendColAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  local transitiveDeps :: [FlowVertex] = 
    expandGraph(e.flowDeps, top.frame.flowGraph);
  
  -- check transitive deps only. Nothing to be done for flow types
  top.errors <-
    if top.config.warnMissingInh
    then checkAllEqDeps(transitiveDeps, top.config, top.location, top.frame.fullName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs))
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
    then checkAllEqDeps(transitiveDeps, top.config, top.location, top.frame.fullName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs)) ++
         if null(lhsInhExceedsFlowType) then []
         else [mwdaWrn(top.config, top.location, "Forward equation exceeds flow type with dependencies on " ++ implode(", ", lhsInhExceedsFlowType))]
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
    then checkAllEqDeps(transitiveDeps, top.config, top.location, top.frame.fullName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs)) ++
         if null(lhsInhExceedsFlowType) then []
         else [mwdaWrn(top.config, top.location, "Forward inherited equation exceeds flow type with dependencies on " ++ implode(", ", lhsInhExceedsFlowType))]
    else [];
}

aspect production localValueDef
top::ProductionStmt ::= val::Decorated QName  e::Expr
{
  local transitiveDeps :: [FlowVertex] =
    expandGraph(e.flowDeps, top.frame.flowGraph);
  
  -- check transitive deps only. No worries about flow types.
  top.errors <-
    if top.config.warnMissingInh
    then checkAllEqDeps(transitiveDeps, top.config, top.location, top.frame.fullName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs))
    else [];
}

aspect production returnDef
top::ProductionStmt ::= 'return' e::Expr ';'
{
  local transitiveDeps :: [FlowVertex] =
    expandGraph(e.flowDeps, top.frame.flowGraph);

  top.errors <-
    if top.config.warnMissingInh
    then checkAllEqDeps(transitiveDeps, top.config, top.location, top.frame.fullName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs))
    else [];
}

aspect production attachNoteStmt
top::ProductionStmt ::= 'attachNote' e::Expr ';'
{
  local transitiveDeps :: [FlowVertex] =
    expandGraph(e.flowDeps, top.frame.flowGraph);

  top.errors <-
    if top.config.warnMissingInh
    then checkAllEqDeps(transitiveDeps, top.config, top.location, top.frame.fullName, top.flowEnv, top.env, collectAnonOrigin(e.flowDefs))
    else [];
}

-- Skipping `baseCollectionValueDef`: it forwards to `localValueDef`
-- Partially skipping `appendCollectionValueDef`: it likewise forwards
-- But we do have a special "exceeds check" to do here:
aspect production appendCollectionValueDef
top::ProductionStmt ::= val::Decorated QName  e::Expr
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
         else [mwdaWrn(top.config, top.location, "Local contribution (" ++ val.name ++ " <-) equation exceeds flow dependencies with: " ++ implode(", ", lhsInhExceedsFlowType))]
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
top::Expr ::= q::Decorated QName
{
  local finalTy::Type = performSubstitution(top.typerep, top.finalSubst);
  top.errors <-
    if top.config.warnMissingInh
    && isDecorable(q.lookupValue.typeScheme.typerep, top.env)
    then if refSet.isJust then []
         else [mwdaWrn(top.config, top.location, s"Cannot take a reference of type ${prettyType(finalTy)}, as the reference set is not bounded.")]
    else [];
}
aspect production lhsReference
top::Expr ::= q::Decorated QName
{
  local finalTy::Type = performSubstitution(top.typerep, top.finalSubst);
  top.errors <-
    if top.config.warnMissingInh
    then if refSet.isJust then []
         else [mwdaWrn(top.config, top.location, s"Cannot take a reference of type ${prettyType(finalTy)}, as the reference set is not bounded.")]
    else [];
}
aspect production localReference
top::Expr ::= q::Decorated QName
{
  local finalTy::Type = performSubstitution(top.typerep, top.finalSubst);
  top.errors <-
    if top.config.warnMissingInh
    && isDecorable(q.lookupValue.typeScheme.typerep, top.env)
    then if refSet.isJust then []
         else [mwdaWrn(top.config, top.location, s"Cannot take a reference of type ${prettyType(finalTy)}, as the reference set is not bounded.")]
    else [];
}
aspect production forwardReference
top::Expr ::= q::Decorated QName
{
  local finalTy::Type = performSubstitution(top.typerep, top.finalSubst);
  top.errors <-
    if top.config.warnMissingInh
    then if refSet.isJust then []
         else [mwdaWrn(top.config, top.location, s"Cannot take a reference of type ${prettyType(finalTy)}, as the reference set is not bounded.")]
    else [];
}

aspect production forwardAccess
top::Expr ::= e::Expr '.' 'forward'
{
  -- TODO?
}

aspect production synDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  -- oh no again
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;

  local finalTy :: Type = performSubstitution(e.typerep, e.upSubst);
  local deps :: (Maybe<set:Set<String>>, [TyVar]) =
    inhDepsForSynOnType(q.attrDcl.fullName, finalTy, myFlow, top.frame.signature, top.env);
  local inhDeps :: set:Set<String> = fromMaybe(set:empty(), deps.1);  -- Need to check that we have bounded inh deps, i.e. deps.1 == just(...)

-- This aspect is in two parts. First: we *must* check that any accesses
-- on a unknown decorated tree are in the ref-set.
  local acceptable :: ([String], [TyVar]) =
    case finalTy of
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
      | hasVertex(_) ->
          if deps.1.isJust then []
          else [mwdaWrn(top.config, top.location, "Access of " ++ q.name ++ " from " ++ prettyType(finalTy) ++ " requires an unbounded set of inherited attributes")]
      -- without a vertex, we're accessing from a reference, and so...
      | noVertex() ->
          if any(map(contains(_, deps.2), acceptable.2)) then []  -- The deps are supplied as a common InhSet var
          -- We didn't find the deps as an InhSet var
          else if null(diff)
            then if deps.fst.isJust then []  -- We have a bound on the inh deps, and they are all present
            -- We don't have a bound on the inh deps, flag the unsatisfied InhSet deps
            else if null(acceptable.2)
            then [mwdaWrn(top.config, top.location, "Access of " ++ q.name ++ " from " ++ prettyType(finalTy) ++ " requires an unbounded set of inherited attributes")]
            else [mwdaWrn(top.config, top.location, "Access of " ++ q.name ++ " from reference of type " ++ prettyType(finalTy) ++ " requires one of the following sets of inherited attributes not known to be supplied to this reference: " ++ implode(", ", map(findAbbrevFor(_, top.frame.signature.freeVariables), deps.snd)))]
          -- We didn't find the inh deps
          else [mwdaWrn(top.config, top.location, "Access of " ++ q.name ++ " from reference of type " ++ prettyType(finalTy) ++ " requires inherited attributes not known to be supplied to this reference: " ++ implode(", ", diff))]
      end
    else [];

----------------

  -- CASE 2: More specific errors for things already caught by `checkAllEqDeps`.
  -- Equation has transition dep on `i`, but here we can say where this dependency
  -- originated: from an syn acces.
  top.errors <- 
    if null(e.errors) && top.config.warnMissingInh
    then
      case e of
      | childReference(lq) ->
          if isDecorable(lq.lookupValue.typeScheme.typerep, top.env)
          then
            let inhs :: [String] = 
                  -- N.B. we're filtering out autocopies here
                  filter(
                    ignoreIfAutoCopyOnLhs(lq.name, top.frame.signature, top.env, _),
                    filter(
                      isEquationMissing(
                        lookupInh(top.frame.fullName, lq.lookupValue.fullName, _, top.flowEnv),
                        _),
                      set:toList(inhDeps)))
             in if null(inhs) then []
                else [mwdaWrn(top.config, top.location, "Access of syn attribute " ++ q.name ++ " on " ++ e.unparse ++ " requires missing inherited attributes " ++ implode(", ", inhs) ++ " to be supplied")]
            end
          else []
      | localReference(lq) ->
          if isDecorable(lq.lookupValue.typeScheme.typerep, top.env)
          then
            let inhs :: [String] = 
                  filter(
                    isEquationMissing(
                      lookupLocalInh(top.frame.fullName, lq.lookupValue.fullName, _, top.flowEnv),
                      _),
                    set:toList(inhDeps))
             in if null(inhs) then []
                else [mwdaWrn(top.config, top.location, "Access of syn attribute " ++ q.name ++ " on " ++ e.unparse ++ " requires missing inherited attributes " ++ implode(", ", inhs) ++ " to be supplied")]
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
  local finalTy::Type = performSubstitution(e.typerep, e.upSubst);
  top.errors <- 
    if null(e.errors) && top.config.warnMissingInh
    then
      case e.flowVertexInfo of
      | hasVertex(_) -> [] -- no check to make, as it was done transitively
      -- without a vertex, we're accessing from a reference, and so...
      | noVertex() ->
          if contains(q.attrDcl.fullName, getMinRefSet(finalTy, top.env))
          then []
          else [mwdaWrn(top.config, top.location, "Access of inherited attribute " ++ q.name ++ " on reference of type " ++ prettyType(finalTy) ++ " is not permitted")]
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
    | noVertex(), anonVertexType(n) -> just(n)
    | _, _ -> nothing()
    end;

  -- These should be the only ones that can reference our anon sink
  local transitiveDeps :: [FlowVertex] =
    expandGraph(top.flowDeps, top.frame.flowGraph);
  
  pr.receivedDeps = transitiveDeps;

  -- just the deps on inhs of our sink
  local inhDeps :: [String] =
    toAnonInhs(transitiveDeps, sinkVertexName.fromJust, top.env);

  -- Subtract the ref set from our deps
  local diff :: [String] =
    set:toList(set:removeAll(getMinRefSet(e.typerep, top.env), set:add(inhDeps, set:empty())));

  top.errors <-
    if null(e.errors)
    && top.config.warnMissingInh
    && sinkVertexName.isJust
    && !null(diff)
    then [mwdaWrn(top.config, e.location, "Pattern match on reference of type " ++ prettyType(e.typerep) ++ " has transitive dependencies on " ++ implode(", ", diff))]
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
         else [mwdaWrn(top.config, top.location, s"Cannot take a reference of type ${prettyType(finalTy)}, as the reference set is not bounded.")]
    else [];

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
    if top.config.warnMissingInh
    && isDecorable(top.bindingType, top.env)
    && top.matchingAgainst.isJust
    && !null(missingInhs)
    then [mwdaWrn(top.config, top.location, s"Pattern variable '${n.name}' has transitive dependencies with missing remote equations.\n\tRemote production: ${top.matchingAgainst.fromJust.fullName}\n\tChild: ${top.bindingName}\n\tMissing inherited equations for: ${implode(", ", missingInhs)}")]
    else [];
}

function remoteProdMissingEq
Boolean ::= prod::ValueDclInfo  sigName::String  attrName::String  realEnv::Decorated Env  flowEnv::FlowEnv
{
  return
    null(lookupInh(prod.fullName, sigName, attrName, flowEnv)) && -- no equation
    ignoreIfAutoCopyOnLhs(sigName, prod.namedSignature, realEnv, attrName); -- not autocopy (and on lhs)
}

--------------------------------------------------------------------------------

-- TODO: There are a few final places where we need to `checkEqDeps` for the sake of `anonVertex`s

-- global declarations, action blocks (production, terminal, disam, etc)

-- But we don't create flowEnv information for these locations so they can't be checked... oops
-- (e.g. `checkEqDeps` wants a production fName to look things up about.)


