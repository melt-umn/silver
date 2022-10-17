grammar silver:compiler:definition:flow:env;

import silver:compiler:definition:type:syntax;
import silver:compiler:definition:type;
import silver:compiler:modification:copper;
import silver:compiler:modification:primitivepattern;
import silver:compiler:extension:patternmatching only Arrow_kwd, Vbar_kwd; -- TODO remove
import silver:compiler:modification:let_fix;

import silver:compiler:driver:util only isExportedBy;

{--
 - Direct (potential) dependencies this expression has on nodes in the production flow graph.
 -}
monoid attribute flowDeps :: [FlowVertex];
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
    decorateExprWith, letp, lexicalLocalReference, matchPrimitiveReal;
propagate flowDefs, flowEnv on
  Expr, ExprInhs, ExprInh, Exprs, AppExprs, AppExpr, AnnoAppExprs, AnnoExpr;

aspect default production
top::Expr ::=
{
  -- We go with using default here because
  -- (a) it's safe. vertexInfo is for being less conservative and more precise.
  -- (b) only a few productions actually provide it.
  top.flowVertexInfo = noVertex();
}

aspect production childReference
top::Expr ::= q::PartiallyDecorated QName
{
  -- Note that q should find the actual type written in the signature, and so
  -- isDecorable on that indeed tells us whether it's something autodecorated.
  local finalTy::Type = performSubstitution(top.typerep, top.finalSubst);
  production refSet::Maybe<[String]> = getMaxRefSet(finalTy, top.env);
  production origRefSet::[String] = getMinRefSet(q.lookupValue.typeScheme.monoType, top.env);
  top.flowDeps :=
    if isDecorable(q.lookupValue.typeScheme.monoType, top.env) && finalTy.isDecorated
    then map(rhsVertexType(q.lookupValue.fullName).inhVertex, removeAll(origRefSet, fromMaybe([], refSet)))
    else [];
  top.flowVertexInfo = 
    if isDecorable(q.lookupValue.typeScheme.monoType, top.env) && finalTy.isDecorated
    then hasVertex(rhsVertexType(q.lookupValue.fullName))
    else noVertex();

  top.flowDefs <-
    case finalTy, refSet of
    | partiallyDecoratedType(_, _), just(inhs)
      when isExportedBy(top.grammarName, [q.lookupValue.dcl.sourceGrammar], top.compiledGrammars) ->
      [partialRef(top.frame.fullName, q.lookupValue.fullName, top.grammarName, q.location, inhs)]
    | _, _ -> []
    end;
}
aspect production lhsReference
top::Expr ::= q::PartiallyDecorated QName
{
  -- Always a decorable type, so just check how it's being used:
  local finalTy::Type = performSubstitution(top.typerep, top.finalSubst);
  production refSet::Maybe<[String]> = getMaxRefSet(finalTy, top.env);
  top.flowDeps :=
    if finalTy.isDecorated
    then map(lhsVertexType.inhVertex, fromMaybe([], refSet))
    else [];
  top.flowVertexInfo = 
    if finalTy.isDecorated
    then hasVertex(lhsVertexType)
    else noVertex();
}
aspect production localReference
top::Expr ::= q::PartiallyDecorated QName
{
  -- Again, q give the actual type written.
  local finalTy::Type = performSubstitution(top.typerep, top.finalSubst);
  production refSet::Maybe<[String]> = getMaxRefSet(finalTy, top.env);
  production origRefSet::[String] = getMinRefSet(q.lookupValue.typeScheme.monoType, top.env);
  top.flowDeps := [localEqVertex(q.lookupValue.fullName)] ++
    if isDecorable(q.lookupValue.typeScheme.monoType, top.env) && finalTy.isDecorated
    then map(rhsVertexType(q.lookupValue.fullName).inhVertex, removeAll(origRefSet, fromMaybe([], refSet)))
    else [];
    
  top.flowVertexInfo =
    if isDecorable(q.lookupValue.typeScheme.monoType, top.env) && finalTy.isDecorated
    then hasVertex(localVertexType(q.lookupValue.fullName))
    else noVertex();

  top.flowDefs <-
    case finalTy, refSet of
    | partiallyDecoratedType(_, _), just(inhs)
      when isExportedBy(top.grammarName, [q.lookupValue.dcl.sourceGrammar], top.compiledGrammars) ->
      [partialRef(top.frame.fullName, q.lookupValue.fullName, top.grammarName, q.location, inhs)]
    | _, _ -> []
    end;
}
aspect production forwardReference
top::Expr ::= q::PartiallyDecorated QName
{
  -- Again, always a decorable type.
  local finalTy::Type = performSubstitution(top.typerep, top.finalSubst);
  production refSet::Maybe<[String]> = getMaxRefSet(finalTy, top.env);
  top.flowDeps := [forwardEqVertex()]++
    if finalTy.isDecorated
    then map(forwardVertexType.inhVertex, fromMaybe([], refSet))
    else [];
    
  top.flowVertexInfo =
    if finalTy.isDecorated
    then hasVertex(forwardVertexType)
    else noVertex();
}

aspect production application
top::Expr ::= e::Expr '(' es::AppExprs ',' anns::AnnoAppExprs ')'
{
  propagate flowEnv;
}

aspect production access
top::Expr ::= e::Expr '.' q::QNameAttrOccur
{
  propagate flowEnv;
}

aspect production accessBouncer
top::Expr ::= target::(Expr ::= PartiallyDecorated Expr  PartiallyDecorated QNameAttrOccur  Location) e::Expr  q::PartiallyDecorated QNameAttrOccur
{
  propagate flowEnv;
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

-- Note that below we IGNORE the flow deps of the lhs if we know what it is
-- this is because by default the lhs will have 'taking ref' flow deps (see above)
aspect production synDecoratedAccessHandler
top::Expr ::= e::PartiallyDecorated Expr  q::PartiallyDecorated QNameAttrOccur
{
  top.flowDeps := 
    case e.flowVertexInfo of
    | hasVertex(vertex) -> vertex.synVertex(q.attrDcl.fullName) :: vertex.eqVertex
    | noVertex() -> e.flowDeps
    end;
}
aspect production inhDecoratedAccessHandler
top::Expr ::= e::PartiallyDecorated Expr  q::PartiallyDecorated QNameAttrOccur
{
  top.flowDeps :=
    case e.flowVertexInfo of
    | hasVertex(vertex) -> vertex.inhVertex(q.attrDcl.fullName) :: vertex.eqVertex
    | noVertex() -> e.flowDeps
    end;
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
  local eTy::Type = performSubstitution(e.typerep, top.finalSubst);
  top.flowDefs <-
    [anonEq(top.frame.fullName, inh.decorationVertex, eTy.typeName, eTy.isNonterminal, top.location, e.flowDeps)];

  -- Now, we represent ourselves to anything that might use us specially
  -- as though we were a reference to this anonymous local
  top.flowVertexInfo = hasVertex(anonVertexType(inh.decorationVertex));

  -- Finally, our standard flow deps mimic those of a local: "taking a reference"
  -- This are of course ignored when treated specially.
  local finalTy::Type = performSubstitution(top.typerep, top.finalSubst);
  production refSet::Maybe<[String]> = getMaxRefSet(finalTy, top.env);
  top.flowDeps := [anonEqVertex(inh.decorationVertex)] ++
    map(anonVertexType(inh.decorationVertex).inhVertex, fromMaybe([], refSet));

  -- If we have a type var with occurs-on contexts, add the specified syn -> inh deps for the new vertex
  top.flowDefs <- occursContextDeps(top.frame.signature, top.env, finalTy, anonVertexType(inh.decorationVertex));
}

inherited attribute decorationVertex :: String occurs on ExprInhs, ExprInh;
propagate decorationVertex on ExprInhs, ExprInh;

aspect production exprInh
top::ExprInh ::= lhs::ExprLHSExpr '=' e1::Expr ';'
{
  top.flowDefs <-
    if !null(lhs.errors) then [] else
    case lhs of
    | exprLhsExpr(q) -> [anonInhEq(top.frame.fullName, top.decorationVertex, q.attrDcl.fullName, e1.flowDeps)]
    end;
    
}

aspect production exprRef
top::Expr ::= e::PartiallyDecorated Expr
{
  top.flowVertexInfo = e.flowVertexInfo;
}

aspect production decHereExpr
top::Expr ::= e::PartiallyDecorated Expr with {}
{
  top.flowDeps <- e.flowDeps;
  top.flowVertexInfo = e.flowVertexInfo;
  top.flowDefs <- e.flowDefs;
  
  e.flowEnv = top.flowEnv;
}

-- FROM LET TODO
attribute flowDefs, flowEnv occurs on AssignExpr;
propagate flowDefs, flowEnv on AssignExpr;

aspect production letp
top::Expr ::= la::AssignExpr  e::Expr
{
  top.flowDeps := e.flowDeps;
}

aspect production lexicalLocalReference
top::Expr ::= q::PartiallyDecorated QName  fi::ExprVertexInfo  fd::[FlowVertex]
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
        if performSubstitution(q.lookupValue.typeScheme.monoType, top.finalSubst).isDecorated &&
           !performSubstitution(top.typerep, top.finalSubst).isDecorated
        then vertex.eqVertex -- we're a `t` emulating `new(t)`
        else fd -- we're passing along our vertex-ness to the outer expression
    | noVertex() -> fd -- we're actually being used as a ref-set-taking decorated var
    end;
  top.flowVertexInfo = fi;
}


-- FROM PATTERN TODO
attribute flowDeps, flowDefs, flowEnv, scrutineeVertexType occurs on PrimPatterns, PrimPattern;
propagate flowDeps, flowDefs, flowEnv, scrutineeVertexType on PrimPatterns, PrimPattern;

inherited attribute scrutineeVertexType :: VertexType;

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

  local eTy::Type = performSubstitution(e.typerep, top.finalSubst);
  top.flowDefs <-
    case e.flowVertexInfo of
    | hasVertex(vertex) -> []
    | noVertex() -> [anonEq(top.frame.fullName, anonName, eTy.typeName, eTy.isNonterminal, top.location, e.flowDeps)]
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
