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
          else [] -- TODO: type is 'decorated blah'
      | lhsReference(lq) -> [] -- actually okay, only affects flow
      | localReference(lq) ->
          if lq.lookupValue.typerep.isDecorable
          then
            let inhs :: [String] = filter(isEquationMissing(lookupLocalInh(top.signature.fullName, lq.lookupValue.fullName, _, top.flowEnv), _), inhDepsForSyn(q.lookupAttribute.fullName, e.typerep.typeName, myFlow))
             in if null(inhs) then []
                else [wrn(top.location, "Access of syn attribute " ++ q.pp ++ " on " ++ e.pp ++ " requires missing inherited attributes " ++ implode(", ", inhs) ++ " to be supplied")]
            end
          else [] -- TODO: reference
      | forwardReference(lq) -> [] -- actually okay, only affects flow
      | _ -> []
    end
    else [];
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
  local transitiveDeps :: [FlowVertex] = expandGraph(immediateDeps, productionFlowGraph);
  
  local lhsInhDeps :: [String] = map((.flowTypeName), filter(isLhsInh(_, top.env), transitiveDeps));
  local lhsInhExceedsFlowType :: [String] = rem(lhsInhDeps, inhDepsForSyn(attr.lookupAttribute.fullName, top.signature.outputElement.typerep.typeName, myFlow));

  top.errors <-
    if null(occursCheck.errors ++ attr.lookupAttribute.errors)
    && (top.config.warnAll || top.config.warnMissingInh)
    && !null(lhsInhExceedsFlowType)
    then [wrn(top.location, "Synthesized equation exceeds flow type with dependencies on " ++ implode(", ", lhsInhExceedsFlowType))]
    else [];
}

function isLhsInh
Boolean ::= v::FlowVertex  e::Decorated Env
{
  return case v of
  | lhsVertex(a) -> 
      case getAttrDcl(a, e) of
      | inhDcl(_,_,_,_,_) :: _ -> true
      | _ -> false
      end
  | _ -> false
  end;
}


-- TODO: locals and forwards



