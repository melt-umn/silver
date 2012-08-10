grammar silver:analysis:warnings:defs;

import silver:modification:autocopyattr only autocopyDcl;
import silver:definition:flow:driver only makeGraphEnv, expandGraph, flowTypeName;

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
aspect production run
top::RunUnit ::= iIn::IO args::[String]
{
  flags <- [pair("--warn-missing-inh", flag(warnMissingInhFlag))];
}


-- TOTALLY INCOMPLETE, but let's kick this off!

aspect production synDNTAccessDispatcher
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  -- TODO oh hell look at that
  local myFlow :: EnvTree<Pair<String String>> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).flowTypes;

  top.errors <- 
    if null(e.errors)
    && (top.config.warnAll || top.config.warnMissingInh)
    && top.blockContext.hasFullSignature -- TODO: only checking productions at the moment!!
    then
      case e of
      | childReference(lq) ->
          if lq.lookupValue.typerep.isDecorable
          then
            let inhs :: [String] = 
                  filter(
                    ignoreIfAutoCopyOnLhs(top.signature.outputElement.typerep.typeName, top.env, _),
                    filter(
                      isEquationMissing(
                        lookupInh(top.signature.fullName, lq.lookupValue.fullName, _, top.flowEnv),
                        _),
                      inhDepsForSyn(q.lookupAttribute.fullName, e.typerep.typeName, myFlow)))
             in if null(inhs) then []
                else [wrn(top.location, "Access of syn attribute " ++ q.pp ++ " on " ++ e.pp ++ " requires missing inherited attributes " ++ implode(", ", inhs) ++ " to be supplied")]
            end
          else checkRefAccess(
                 inhsForTakingRef(e.typerep.typeName, top.flowEnv),
                 inhDepsForSyn(q.lookupAttribute.fullName, e.typerep.typeName, myFlow),
                 top.location, q.pp)
      | lhsReference(lq) -> [] -- actually okay, only affects flow
      | localReference(lq) ->
          if lq.lookupValue.typerep.isDecorable
          then
            let inhs :: [String] = 
                  filter(
                    isEquationMissing(
                      lookupLocalInh(top.signature.fullName, lq.lookupValue.fullName, _, top.flowEnv),
                      _),
                    inhDepsForSyn(q.lookupAttribute.fullName, e.typerep.typeName, myFlow))
             in if null(inhs) then []
                else [wrn(top.location, "Access of syn attribute " ++ q.pp ++ " on " ++ e.pp ++ " requires missing inherited attributes " ++ implode(", ", inhs) ++ " to be supplied")]
            end
          else checkRefAccess(
                 inhsForTakingRef(e.typerep.typeName, top.flowEnv),
                 inhDepsForSyn(q.lookupAttribute.fullName, e.typerep.typeName, myFlow),
                 top.location, q.pp)
      | forwardReference(lq) -> [] -- actually okay, only affects flow
      | _ -> checkRefAccess(
                 inhsForTakingRef(e.typerep.typeName, top.flowEnv),
                 inhDepsForSyn(q.lookupAttribute.fullName, e.typerep.typeName, myFlow),
                 top.location, q.pp) -- TODO: need special case for 'decorate .... }. attr' others???
    end
    else [];
}

-- TODO: need inh checks here too~! or do we? I suppose to check any inh accesses on references. any other reason?


function checkRefAccess
[Message] ::= blessedSet::[String]  neededSet::[String]  l::Location  attrpp::String
{
  local diff::[String] = rem(neededSet, blessedSet);
  
  return if null(diff) then [] else [wrn(l, "Access of " ++ attrpp ++ " from reference requires inherited attributes not known to be supplied to references: " ++ implode(", ", diff))];
}


function inhDepsForSyn
[String] ::= syn::String  nt::String  flow::EnvTree<Pair<String String>>
{
  return lookupAllBy(stringEq, syn, searchEnvTree(nt, flow));
}

function isEquationMissing
Boolean ::= f::([FlowDef] ::= String)  attr::String
{
  return null(f(attr));
}

function ignoreIfAutoCopyOnLhs
Boolean ::= lhsNt::String  env::Decorated Env  attr::String
{
  return !(isAutocopy(attr, env) && !null(getOccursDcl(attr, lhsNt, env)));
}


function isAutocopy
Boolean ::= attr::String  e::Decorated Env
{
  return case getAttrDclAll(attr, e) of
  | autocopyDcl(_,_,_,_,_) :: _ -> true
  | _ -> false
  end;
}


aspect production synthesizedAttributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::Decorated QName '=' e::Expr
{
  -- TODO oh no again!
  local myFlow :: EnvTree<Pair<String String>> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).flowTypes;
  local myGraphs :: [Pair<String [Pair<FlowVertex FlowVertex>]>] = head(searchEnvTree(top.grammarName, top.compiledGrammars)).prodFlowGraphs;

  local immediateDeps :: [FlowVertex] = e.flowDeps;
  local productionFlowGraph :: EnvTree<FlowVertex> = directBuildTree(map(makeGraphEnv, fromMaybe([], lookupBy(stringEq, top.signature.fullName, myGraphs))));
  local transitiveDeps :: [FlowVertex] = nubBy(equalFlowVertex, expandGraph(immediateDeps, productionFlowGraph));
  
  local lhsInhDeps :: [String] = map((.flowTypeName), filter(isLhsInh(_, top.env), transitiveDeps));
  local lhsInhExceedsFlowType :: [String] = rem(lhsInhDeps, inhDepsForSyn(attr.lookupAttribute.fullName, top.signature.outputElement.typerep.typeName, myFlow));

  top.errors <-
    if null(occursCheck.errors ++ attr.lookupAttribute.errors)
    && (top.config.warnAll || top.config.warnMissingInh)
    then foldr(append, [], map(checkEqDeps(_, top.location, top.signature.fullName, top.signature.outputElement.typerep.typeName, top.flowEnv, top.env), transitiveDeps)) ++
         if null(lhsInhExceedsFlowType) then []
         else [wrn(top.location, "Synthesized equation exceeds flow type with dependencies on " ++ implode(", ", lhsInhExceedsFlowType))]
    else [];
}

function isLhsInh
Boolean ::= v::FlowVertex  e::Decorated Env
{
  return case v of
  | lhsVertex(a) -> isInherited(a, e)
  | _ -> false
  end;
}

function isInherited
Boolean ::= a::String  e::Decorated Env
{
  return case getAttrDcl(a, e) of
  | inhDcl(_,_,_,_,_) :: _ -> true
  | _ -> false
  end;
}

function sigNotAReference
Boolean ::= sigName::String  e::Decorated Env
{
  local d :: [DclInfo] = getValueDcl(sigName, e);
  
  return if null(d) then false else head(d).typerep.isDecorable;
}

function checkEqDeps
[Message] ::= v::FlowVertex  l::Location  prodName::String  prodNt::String  flowEnv::Decorated FlowEnv  realEnv::Decorated Env
{
  return case v of 
  | lhsVertex(attrName) ->
      if isInherited(attrName, realEnv)
      then [] -- Do nothing. This just affects flow types.
      else if !null(lookupSyn(prodName, attrName, flowEnv)) -- no equation
           || !null(lookupDef(prodNt, attrName, flowEnv)) -- no default
           || !null(lookupFwd(prodName, flowEnv)) -- no forward
           then []
           else [wrn(l, "Equation has transitive dependency on this production's synthesized equation for " ++ attrName ++ " but this equation appears to be missing.")]
  | rhsVertex(sigName, attrName) ->
      if isInherited(attrName, realEnv)
      then if !null(lookupInh(prodName, sigName, attrName, flowEnv)) -- no equation
           || !ignoreIfAutoCopyOnLhs(prodNt, realEnv, attrName) -- no autocopy
           || !sigNotAReference(sigName, realEnv) -- not Decorated type
           then []
           else [wrn(l, "Equation has transitive dependency on child " ++ sigName ++ "'s inherited attribute for " ++ attrName ++ " but this equation appears to be missing.")]
      else [] -- Do nothing. This just affects inh dependencies on this rhs via flow types.
  | localEqVertex(fName) -> [] -- Anything to do here? I am uncertain.
  | localVertex(fName, attrName) -> 
      if isInherited(attrName, realEnv)
      then if !null(lookupLocalInh(prodName, fName, attrName, flowEnv)) -- no equation
           || fName == "forward" -- not forward
           || !sigNotAReference(fName, realEnv) -- not Decorated type
           then []
           else [wrn(l, "Equation has transitive dependency on local " ++ fName ++ "'s inherited attribute for " ++ attrName ++ " but this equation appears to be missing.")]
      else [] -- Do nothing. This again just affects inh dependencies via flow types.
  end;
}


-- TODO: locals and forwards equations do not exceed their flow types
-- AND that all transitive deps are satisfied for them!!

aspect production forwardsTo
top::ProductionStmt ::= 'forwards' 'to' e::Expr ';'
{
  -- TODO oh no again!
  local myFlow :: EnvTree<Pair<String String>> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).flowTypes;
  local myGraphs :: [Pair<String [Pair<FlowVertex FlowVertex>]>] = head(searchEnvTree(top.grammarName, top.compiledGrammars)).prodFlowGraphs;

  local immediateDeps :: [FlowVertex] = e.flowDeps;
  local productionFlowGraph :: EnvTree<FlowVertex> = directBuildTree(map(makeGraphEnv, fromMaybe([], lookupBy(stringEq, top.signature.fullName, myGraphs))));
  local transitiveDeps :: [FlowVertex] = nubBy(equalFlowVertex, expandGraph(immediateDeps, productionFlowGraph));
  
  local lhsInhDeps :: [String] = map((.flowTypeName), filter(isLhsInh(_, top.env), transitiveDeps));
  local lhsInhExceedsFlowType :: [String] = rem(lhsInhDeps, inhDepsForSyn("forward", top.signature.outputElement.typerep.typeName, myFlow));

  top.errors <-
    if (top.config.warnAll || top.config.warnMissingInh)
    then foldr(append, [], map(checkEqDeps(_, top.location, top.signature.fullName, top.signature.outputElement.typerep.typeName, top.flowEnv, top.env), transitiveDeps)) ++
         if null(lhsInhExceedsFlowType) then []
         else [wrn(top.location, "Forward equation exceeds flow type with dependencies on " ++ implode(", ", lhsInhExceedsFlowType))]
    else [];
}

aspect production forwardsToWith
top::ProductionStmt ::= 'forwards' 'to' e::Expr 'with' '{' inh::ForwardInhs '}' ';'
{ -- TODO WHY IS THIS A SEPARATE PRODUCTION!?
}



aspect production decorateExprWith
top::Expr ::= 'decorate' e::Expr 'with' '{' inh::ExprInhs '}'
{
  -- TODO: we are being WAY overly conservative here and requiring 'decorate' expressions
  -- to ALWAYS provide the full blessed set.
  -- We could do better by detecting those situations where we immediate access
  -- a synthesized attribute, and only requiring the flow there....
  -- Alternatively, by introducing a full "decoration site" notion...
  local blessedSet :: [String] = inhsForTakingRef(e.typerep.typeName, top.flowEnv);
  local diff :: [String] = rem(blessedSet, inh.suppliedInhs);

  top.errors <- 
    if null(e.errors)
    && (top.config.warnAll || top.config.warnMissingInh)
    && top.blockContext.hasFullSignature -- TODO: only checking productions at the moment!!
    then
      if null(diff) then []
      else [wrn(top.location, "Decoration producing a reference does not supply " ++ implode(", ", diff))]
    else [];
}

