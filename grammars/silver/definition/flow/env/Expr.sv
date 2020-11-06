grammar silver:definition:flow:env;

import silver:definition:type:syntax;
import silver:definition:type;
import silver:modification:copper;
import silver:modification:primitivepattern;
import silver:extension:patternmatching only Arrow_kwd, Vbar_kwd; -- TODO remove
import silver:modification:let_fix;

{--
 - Direct (potential) dependencies this expression has on nodes in the production flow graph.
 -}
monoid attribute flowDeps :: [FlowVertex] with [], ++;
{--
 - Determines whether this expression corresponds to a node in the flow graph, and how
 - to treat it specially if so.
 -}
synthesized attribute flowVertexInfo :: ExprVertexInfo;

-- flowDefs because expressions (decorate, patterns) can now generate stitchpoints
attribute flowDeps, flowDefs, flowEnv occurs on Expr, ExprInhs, ExprInh, Exprs, AppExprs, AppExpr, AnnoAppExprs, AnnoExpr;
attribute flowVertexInfo occurs on Expr;

propagate flowDeps on Expr, ExprInhs, ExprInh, Exprs, AppExprs, AppExpr, AnnoAppExprs, AnnoExpr
  excluding
    childReference, lhsReference, localReference, forwardReference, forwardAccess, synDecoratedAccessHandler, inhDecoratedAccessHandler,
    decorateExprWith, newFunction, letp, lexicalLocalReference, matchPrimitiveReal;
propagate flowDefs on Expr, ExprInhs, ExprInh, Exprs, AppExprs, AppExpr, AnnoAppExprs, AnnoExpr;


function inhsForTakingRef
[String] ::= nt::String  flowEnv::Decorated FlowEnv
{
  return case getInhsForNtRef(nt, flowEnv) of
         | ntRefFlowDef(_, inhs) :: _ -> inhs
         | _ -> []
         end;
}

function depsForTakingRef
[FlowVertex] ::= f::VertexType  nt::String  flowEnv::Decorated FlowEnv
{
  return map(f.inhVertex, inhsForTakingRef(nt, flowEnv));  
}

aspect default production
top::Expr ::=
{
  -- We go with using default here because
  -- (a) it's safe. vertexInfo is for being less conservative and more precise.
  -- (b) only a few productions actually provide it.
  top.flowVertexInfo = noVertex();
}

aspect production childReference
top::Expr ::= q::Decorated QName
{
  -- Note that q should find the actual type written in the signature, and so
  -- isDecorable on that indeed tells us whether it's something autodecorated.
  top.flowDeps :=
    if q.lookupValue.typerep.isDecorable && !performSubstitution(top.typerep, top.finalSubst).isDecorable
    then depsForTakingRef(rhsVertexType(q.lookupValue.fullName), q.lookupValue.typerep.typeName, top.flowEnv)
    else [];
  top.flowVertexInfo = 
    if q.lookupValue.typerep.isDecorable && !performSubstitution(top.typerep, top.finalSubst).isDecorable
    then hasVertex(rhsVertexType(q.lookupValue.fullName))
    else noVertex();
}
aspect production lhsReference
top::Expr ::= q::Decorated QName
{
  -- Always a decorable type, so just check how it's being used:
  top.flowDeps :=
    if !performSubstitution(top.typerep, top.finalSubst).isDecorable
    then depsForTakingRef(lhsVertexType, q.lookupValue.typerep.typeName, top.flowEnv)
    else [];
  top.flowVertexInfo = 
    if !performSubstitution(top.typerep, top.finalSubst).isDecorable
    then hasVertex(lhsVertexType)
    else noVertex();
}
aspect production localReference
top::Expr ::= q::Decorated QName
{
  -- Again, q give the actual type written.
  top.flowDeps := [localEqVertex(q.lookupValue.fullName)] ++
    if q.lookupValue.typerep.isDecorable && !performSubstitution(top.typerep, top.finalSubst).isDecorable
    then depsForTakingRef(localVertexType(q.lookupValue.fullName), q.lookupValue.typerep.typeName, top.flowEnv)
    else [];
    
  top.flowVertexInfo =
    if q.lookupValue.typerep.isDecorable && !performSubstitution(top.typerep, top.finalSubst).isDecorable
    then hasVertex(localVertexType(q.lookupValue.fullName))
    else noVertex();
}
aspect production forwardReference
top::Expr ::= q::Decorated QName
{
  -- Again, always a decorable type.
  top.flowDeps := [forwardEqVertex()]++
    if !performSubstitution(top.typerep, top.finalSubst).isDecorable
    then depsForTakingRef(forwardVertexType, q.lookupValue.typerep.typeName, top.flowEnv)
    else [];
    
  top.flowVertexInfo =
    if !performSubstitution(top.typerep, top.finalSubst).isDecorable
    then hasVertex(forwardVertexType)
    else noVertex();
}

-- Still need these equations since propagate ignores decorated references
aspect production functionInvocation
top::Expr ::= e::Decorated Expr es::Decorated AppExprs annos::Decorated AnnoAppExprs
{
  top.flowDeps <- e.flowDeps ++ es.flowDeps ++ annos.flowDeps;
  top.flowDefs <- e.flowDefs ++ es.flowDefs ++ annos.flowDefs;
}
aspect production partialApplication
top::Expr ::= e::Decorated Expr es::Decorated AppExprs annos::Decorated AnnoAppExprs
{
  top.flowDeps <- e.flowDeps ++ es.flowDeps ++ annos.flowDeps;
  top.flowDefs <- e.flowDefs ++ es.flowDefs ++ annos.flowDefs;
}

aspect production forwardAccess
top::Expr ::= e::Expr '.' 'forward'
{
  top.flowDeps := 
    case e.flowVertexInfo of
    | hasVertex(vertex) -> vertex.fwdVertex :: vertex.eqVertex
    | noVertex() -> e.flowDeps
    end;
}

aspect production errorAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.flowDefs <- e.flowDefs;
}
-- Note that below we IGNORE the flow deps of the lhs if we know what it is
-- this is because by default the lhs will have 'taking ref' flow deps (see above)
aspect production synDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.flowDeps := 
    case e.flowVertexInfo of
    | hasVertex(vertex) -> vertex.synVertex(q.attrDcl.fullName) :: vertex.eqVertex
    | noVertex() -> e.flowDeps
    end;
  top.flowDefs <- e.flowDefs;
}
aspect production inhDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.flowDeps :=
    case e.flowVertexInfo of
    | hasVertex(vertex) -> vertex.inhVertex(q.attrDcl.fullName) :: vertex.eqVertex
    | noVertex() -> e.flowDeps
    end;
  top.flowDefs <- e.flowDefs;
}
aspect production errorDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.flowDeps <- []; -- errors, who cares?
  top.flowDefs <- e.flowDefs;
}
aspect production terminalAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.flowDeps <- e.flowDeps;
  top.flowDefs <- e.flowDefs;
}
aspect production annoAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.flowDeps <- e.flowDeps;
  top.flowDefs <- e.flowDefs;
}


aspect production decorateExprWith
top::Expr ::= 'decorate' e::Expr 'with' '{' inh::ExprInhs '}'
{
  -- The general theory:
  -- ... some expr ... decorate EXP1 with { ... inhs ... } ...
  -- is equivalent to:
  -- local ANON :: EXP1.typerep = EXP1;
  -- ANON.inhN = inhNexp; -- etc...
  -- an the expr is now ... ANON ...
  
  -- We don't actually do this transform, of course, but that's what we're representing
  -- this as to the flow analysis, and justifies all the choices below:

  -- First, generate our "anonymous" flow vertex name:
  inh.decorationVertex = "__decorate" ++ toString(genInt()) ++ ":line" ++ toString(top.location.line);

  -- Next, emit the "local equation" for this anonymous flow vertex.
  -- This means only the deps in 'e', see above conceptual transformation to see why.
  -- N.B. 'inh.flowDefs' will emit 'localInhEq's for this anonymous flow vertex.
  top.flowDefs <-
    [anonEq(top.frame.fullName, inh.decorationVertex, performSubstitution(e.typerep, top.finalSubst).typeName, top.location, e.flowDeps)];

  -- Now, we represent ourselves to anything that might use us specially
  -- as though we were a reference to this anonymous local
  top.flowVertexInfo = hasVertex(anonVertexType(inh.decorationVertex));

  -- Finally, our standard flow deps mimic those of a local: "taking a reference"
  -- This are of course ignored when treated specially.
  top.flowDeps := [anonEqVertex(inh.decorationVertex)] ++
    depsForTakingRef(anonVertexType(inh.decorationVertex), performSubstitution(e.typerep, top.finalSubst).typeName, top.flowEnv);
}

autocopy attribute decorationVertex :: String occurs on ExprInhs, ExprInh;

aspect production exprInh
top::ExprInh ::= lhs::ExprLHSExpr '=' e1::Expr ';'
{
  top.flowDefs <-
    if !null(lhs.errors) then [] else
    case lhs of
    | exprLhsExpr(q) -> [anonInhEq(top.frame.fullName, top.decorationVertex, q.attrDcl.fullName, e1.flowDeps)]
    end;
    
}

aspect production errorPlusPlus
top::Expr ::= e1::Decorated Expr e2::Decorated Expr
{
  top.flowDeps <- []; -- error, so who cares?
  top.flowDefs <- e1.flowDefs ++ e2.flowDefs;
}
aspect production stringPlusPlus
top::Expr ::= e1::Decorated Expr e2::Decorated Expr
{
  top.flowDeps <- e1.flowDeps ++ e2.flowDeps;
  top.flowDefs <- e1.flowDefs ++ e2.flowDefs;
}

aspect production exprRef
top::Expr ::= e::Decorated Expr
{
  -- This production is somewhat special, for example, error is := []
  -- That's because the errors should have already been appeared wherever it's anchored.
  
  -- But, here we DO pass flowDeps through because this affects wherever this expression
  -- is used, not just where it appears.
  
  -- So definitely don't consider making this []!
  
  top.flowDeps <- e.flowDeps;
  top.flowVertexInfo = e.flowVertexInfo;
  top.flowDefs <- e.flowDefs; -- I guess? I haven't thought about this exactly.
  -- i.e. whether this has already been included. shouldn't hurt to do so though.
}


-- builtins

aspect production stringLength
top::Expr ::= e::Decorated Expr
{
  top.flowDeps <- e.flowDeps;
  top.flowDefs <- e.flowDefs;
}
aspect production errorLength
top::Expr ::= e::Decorated Expr
{
  top.flowDeps <- e.flowDeps;
  top.flowDefs <- e.flowDefs;
}

aspect production newFunction
top::Expr ::= 'new' '(' e1::Expr ')'
{
  -- Emit nothing except the keepDeps, for a vertex node
  top.flowDeps :=
    case e1.flowVertexInfo of
    | hasVertex(vertex) -> vertex.eqVertex
    | noVertex() -> e1.flowDeps
    end;
}


-- FROM LET TODO
attribute flowDefs, flowEnv occurs on AssignExpr;
propagate flowDefs on AssignExpr;

aspect production letp
top::Expr ::= la::AssignExpr  e::Expr
{
  top.flowDeps := e.flowDeps;
}

aspect production lexicalLocalReference
top::Expr ::= q::Decorated QName  fi::ExprVertexInfo  fd::[FlowVertex]
{
  -- Because of the auto-undecorate behavior, we need to check for the case
  -- where `t` should be equivalent to `new(t)` and report accoringly.
  
  -- If we:
  -- 1. Have a flow vertex
  -- 2. Are a decorated type
  -- 3. Used as undecorated type
  -- Then: Suppress `fd` and report just `fi.eq`

  top.flowDeps := 
    case fi of
    | hasVertex(vertex) ->
        if performSubstitution(q.lookupValue.typerep, top.finalSubst).isDecorated &&
           !performSubstitution(top.typerep, top.finalSubst).isDecorated
        then vertex.eqVertex -- we're a `t` emulating `new(t)`
        else fd -- we're passing along our vertex-ness to the outer expression
    | noVertex() -> fd -- we're actually being used as a ref-set-taking decorated var
    end;
  top.flowVertexInfo = fi;
}


-- FROM PATTERN TODO
attribute flowDeps, flowDefs, flowEnv, scrutineeVertexType occurs on PrimPatterns, PrimPattern;
propagate flowDeps, flowDefs on PrimPatterns, PrimPattern;

autocopy attribute scrutineeVertexType :: VertexType;

aspect production matchPrimitiveReal
top::Expr ::= e::Expr t::TypeExpr pr::PrimPatterns f::Expr
{
  -- If we take e.flowDeps ++ f.flowDeps, look them all up in the production
  -- graph, and take the union, then filter down to just those on our anon vertex
  -- we can discover what's needed, and use that to raise errors.
  
  -- We do have to do the lookups, though: we can't just use those Deps directly.
  -- consider 'case e of prod(x) -> decorate x.syn with ...'
  -- that introduces the use of 'x.syn' in a flowDef, and then emits the anonEq in flowDep
  -- so we DO need to be transitive. Unfortunately.
  
  -- hack note: there's a test that depends on this name starting with __scrutinee. grep for it if you have to change this
  local anonName :: String = "__scrutinee" ++ toString(genInt()) ++ ":line" ++ toString(e.location.line);

  pr.scrutineeVertexType =
    case e.flowVertexInfo of
    | hasVertex(vertex) -> vertex
    | noVertex() -> anonVertexType(anonName)
    end;

  -- Let's make sure for decorated types, we only demand what's necessary for forward
  -- evaluation.
  top.flowDeps := pr.flowDeps ++ f.flowDeps ++
    (pr.scrutineeVertexType.fwdVertex :: pr.scrutineeVertexType.eqVertex);

  top.flowDefs <-
    case e.flowVertexInfo of
    | hasVertex(vertex) -> []
    | noVertex() -> [anonEq(top.frame.fullName, anonName, performSubstitution(e.typerep, top.finalSubst).typeName, top.location, e.flowDeps)]
    end;
  -- We want to use anonEq here because that introduces the nonterminal stitch point for our vertex.
}

aspect production prodPatternNormal
top::PrimPattern ::= qn::Decorated QName  ns::VarBinders  e::Expr
{
  top.flowDefs <-
    [patternRuleEq(top.frame.fullName, qn.lookupValue.fullName, top.scrutineeVertexType, ns.flowProjections)];
}
aspect production prodPatternGadt
top::PrimPattern ::= qn::Decorated QName  ns::VarBinders  e::Expr
{
  top.flowDefs <-
    [patternRuleEq(top.frame.fullName, qn.lookupValue.fullName, top.scrutineeVertexType, ns.flowProjections)];
}
