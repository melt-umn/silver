grammar silver:compiler:definition:flow:env;

import silver:compiler:definition:type:syntax;
import silver:compiler:definition:type;
import silver:compiler:analysis:typechecking:core;

-- TODO: Extension/modification flow stuff should maybe be moved into these grammars
import silver:compiler:modification:copper;
import silver:compiler:modification:primitivepattern;
import silver:compiler:extension:patternmatching only Arrow_kwd, Vbar_kwd; -- TODO remove
import silver:compiler:modification:let_fix;
import silver:compiler:modification:lambda_fn;
import silver:compiler:modification:concisefunctions;

import silver:compiler:driver:util only isExportedBy;

{--
 - Direct (potential) dependencies this expression has on nodes in the production flow graph.
 -}
monoid attribute flowDeps :: [FlowVertex];

{--
 - Determines whether this expression corresponds to a node in the flow graph, and how
 - to treat it specially if so.
 - 
 - Do not forget we also need to address its equation (but vertexType takes care of that too)
 -
 - e.g. new(decorate rhs.a with {}) needs to emit 'rhs.a' still, even while
 -      ignoring the decorate vertex. That happens when we refer to the anonEq.
 -
 - When this is nothing(), the expression does not have a corresponding vertex in the
 - production graph and so much be treated as any value.
 -}
synthesized attribute flowVertexInfo :: Maybe<VertexType>;

{--
 - Where this expression is known to ultimately get decorated, if at all.
 - The supplied inherited attributes correspond to the inherited vertices for this vertex type.
 -}
inherited attribute decSiteVertexInfo :: Maybe<VertexType>;

{--
 - Is this expression unconditionally decorated.
 - This determines whether we can rely on projected inherited equations being present.
 - Can be false when decSiteVertexInfo is just(...) - for example:
 -
 - local foo::Expr = if cond then @e else bar;
 - foo.env = top.env;
 -
 - we know that e's decoration site vertex is foo, and e.env can depend on top.env,
 - but we can't gurantee that e.env is defined in case cond is false.
 -}
inherited attribute alwaysDecorated :: Boolean;

-- flowDefs because expressions (decorate, patterns) can now generate stitchpoints
attribute flowDeps, flowDefs, flowEnv occurs on Expr, ExprInhs, ExprInh, Exprs, AppExprs, AppExpr, AnnoAppExprs, AnnoExpr;
attribute flowVertexInfo occurs on Expr;

propagate flowDeps on Expr, ExprInhs, ExprInh, Exprs, AppExprs, AppExpr, AnnoAppExprs, AnnoExpr
  excluding
    childReference, lhsReference, localReference, forwardReference, forwardAccess,
    synDecoratedAccessHandler, inhDecoratedAccessHandler, transDecoratedAccessHandler,
    decorateExprWith, letp, lexicalLocalReference, matchPrimitiveReal;
propagate flowDefs, flowEnv on Expr, ExprInhs, ExprInh, Exprs, AppExprs, AppExpr, AnnoAppExprs, AnnoExpr;

attribute decSiteVertexInfo, alwaysDecorated occurs on Expr, AppExprs, AppExpr;
propagate decSiteVertexInfo, alwaysDecorated on AppExprs;

aspect default production
top::Expr ::=
{
  -- We go with using default here because
  -- (a) it's safe. vertexInfo is for being less conservative and more precise.
  -- (b) only a few productions actually provide it.
  top.flowVertexInfo = nothing();
}

aspect production childReference
top::Expr ::= @q::QName
{
  -- Note that q should find the actual type written in the signature, and so
  -- isDecorable on that indeed tells us whether it's something autodecorated.
  production refSet::Maybe<[String]> = getMaxRefSet(top.finalType, top.env);
  production origRefSet::[String] = getMinRefSet(q.lookupValue.typeScheme.monoType, top.env);
  top.flowDeps :=
    if isDecorable(q.lookupValue.typeScheme.monoType, top.env) && top.finalType.isDecorated
    then map(rhsVertexType(q.lookupValue.fullName).inhVertex, removeAll(origRefSet, fromMaybe([], refSet)))
    else [];
  top.flowVertexInfo = 
    if isDecorable(q.lookupValue.typeScheme.monoType, top.env) && top.finalType.isDecorated
    then just(rhsVertexType(q.lookupValue.fullName))
    else nothing();
}
aspect production lhsReference
top::Expr ::= @q::QName
{
  -- Always a decorable type, so just check how it's being used:
  production refSet::Maybe<[String]> = getMaxRefSet(top.finalType, top.env);
  top.flowDeps :=
    if top.finalType.isDecorated
    then map(lhsVertexType.inhVertex, fromMaybe([], refSet))
    else [];
  top.flowVertexInfo = 
    if top.finalType.isDecorated
    then just(lhsVertexType)
    else nothing();
}
aspect production localReference
top::Expr ::= @q::QName
{
  -- Again, q give the actual type written.
  production refSet::Maybe<[String]> = getMaxRefSet(top.finalType, top.env);
  production origRefSet::[String] = getMinRefSet(q.lookupValue.typeScheme.monoType, top.env);
  top.flowDeps := [localEqVertex(q.lookupValue.fullName)] ++
    if isDecorable(q.lookupValue.typeScheme.monoType, top.env) && top.finalType.isDecorated
    then map(localVertexType(q.lookupValue.fullName).inhVertex, removeAll(origRefSet, fromMaybe([], refSet)))
    else [];
  top.flowVertexInfo =
    if isDecorable(q.lookupValue.typeScheme.monoType, top.env) && top.finalType.isDecorated
    then just(localVertexType(q.lookupValue.fullName))
    else nothing();
}
aspect production forwardReference
top::Expr ::= @q::QName
{
  -- Again, always a decorable type.
  production refSet::Maybe<[String]> = getMaxRefSet(top.finalType, top.env);
  top.flowDeps := [forwardEqVertex()]++
    if top.finalType.isDecorated
    then map(forwardVertexType.inhVertex, fromMaybe([], refSet))
    else [];
  top.flowVertexInfo =
    if top.finalType.isDecorated
    then just(forwardVertexType)
    else nothing();
}

-- The named signature of the applied production.
-- Note that we don't project functions at the moment, since we don't build function flow graphs during inference.
inherited attribute appProd::Maybe<NamedSignature> occurs on AppExprs, AppExpr;
propagate appProd on AppExprs;

aspect production application
top::Expr ::= e::Expr '(' es::AppExprs ',' anns::AnnoAppExprs ')'
{
  propagate flowEnv;
  e.alwaysDecorated = false;
}

aspect production errorApplication
top::Expr ::= @e::Expr @es::AppExprs @anns::AnnoAppExprs
{
  e.decSiteVertexInfo = nothing();
  es.decSiteVertexInfo = nothing();
  es.alwaysDecorated = false;
  es.appProd = nothing();
}

aspect production functionInvocation
top::Expr ::= @e::Expr @es::AppExprs @anns::AnnoAppExprs
{
  top.flowVertexInfo = top.decSiteVertexInfo;
  es.appProd =
    case e of
    | productionReference(q) -> just(q.lookupValue.dcl.namedSignature)
    | _ -> nothing()
    end;
  e.decSiteVertexInfo = top.decSiteVertexInfo;
  es.decSiteVertexInfo = top.decSiteVertexInfo;
  es.alwaysDecorated = top.alwaysDecorated;
}

aspect production partialApplication
top::Expr ::= @e::Expr @es::AppExprs @anns::AnnoAppExprs
{
  es.appProd =
    case e of
    | productionReference(q) -> just(q.lookupValue.dcl.namedSignature)
    | _ -> nothing()
    end;
  e.decSiteVertexInfo = nothing();
  es.decSiteVertexInfo = nothing();
  es.alwaysDecorated = false;
}

aspect production dispatchApplication
top::Expr ::= @e::Expr @es::AppExprs @anns::AnnoAppExprs
{
  top.flowVertexInfo = top.decSiteVertexInfo;
  es.appProd =
    case e, e.typerep of
    | productionReference(q), _ -> just(q.lookupValue.dcl.namedSignature)
    | _, dispatchType(ns) -> just(ns)
    | _, _ -> error("dispatchApplication: unexpected type")
    end;
  e.decSiteVertexInfo = top.decSiteVertexInfo;
  es.decSiteVertexInfo = top.decSiteVertexInfo;
  es.alwaysDecorated =
    case e of
    | productionReference(q) -> top.alwaysDecorated
    | _ -> false
    end;
}

aspect production annoExpr
top::AnnoExpr ::= qn::QName '=' e::AppExpr
{
  e.decSiteVertexInfo = nothing();
  e.appProd = nothing();
  e.alwaysDecorated = false;
}

aspect production presentAppExpr
top::AppExpr ::= e::Expr
{
  production sigName::String =
    case top.appProd of
    | just(ns) when top.appExprIndex < length(ns.inputNames) -> head(drop(top.appExprIndex, ns.inputNames))
    | _ -> "err"
    end;
  top.flowDefs <-
    case e.decSiteVertexInfo of
    | just(subtermVertexType(parent, prodName, sigName)) ->
      [subtermDecEq(top.frame.fullName, parent, prodName, sigName)]
    | _ -> []
    end;
  e.decSiteVertexInfo =
    case top.decSiteVertexInfo, top.appProd of
    | just(parent), just(ns) when isDecorable(e.finalType, top.env) ->
      just(subtermVertexType(parent, ns.fullName, sigName))
    | _, _ -> nothing()
    end;
  e.alwaysDecorated = top.alwaysDecorated && e.decSiteVertexInfo.isJust;

  production sigIsShared::Boolean =
    case top.appProd of
    | just(ns) ->
      top.appExprIndex < length(ns.inputNames) &&
      head(drop(top.appExprIndex, ns.inputElements)).elementShared
    | _ -> false
    end;
  top.flowDefs <-
    case top.appProd, e.flowVertexInfo of
    | just(ns), just(v) when sigIsShared ->
      [sigShareSite(top.frame.fullName, v, ns.fullName, sigName)]
    | _, _ -> []
    end;
}

aspect production noteAttachment
top::Expr ::= 'attachNote' note::Expr 'on' e::Expr 'end'
{
  note.decSiteVertexInfo = nothing();
  e.decSiteVertexInfo = top.decSiteVertexInfo;
  note.alwaysDecorated = false;
  e.alwaysDecorated = top.alwaysDecorated;
}

aspect production access
top::Expr ::= e::Expr '.' q::QNameAttrOccur
{
  propagate flowEnv;
  e.alwaysDecorated = false;
}

aspect production accessBouncer
top::Expr ::= e::Expr  @q::QNameAttrOccur target::Access
{
  propagate flowEnv;
  e.alwaysDecorated = false;
}

aspect production forwardAccess
top::Expr ::= e::Expr '.' 'forward'
{
  top.flowDeps := 
    case e.flowVertexInfo of
    | just(vertex) -> vertex.fwdVertex :: vertex.eqVertex
    | nothing() -> e.flowDeps
    end;
  e.decSiteVertexInfo = nothing();
  e.alwaysDecorated = false;
}


aspect production errorAccessHandler
top::Expr ::= @e::Expr @q::QNameAttrOccur
{
  e.decSiteVertexInfo = nothing();
}
aspect production terminalAccessHandler
top::Expr ::= @e::Expr @q::QNameAttrOccur
{
  e.decSiteVertexInfo = nothing();
}
-- Note that below we IGNORE the flow deps of the lhs if we know what it is
-- this is because by default the lhs will have 'taking ref' flow deps (see above)
aspect production synDecoratedAccessHandler
top::Expr ::= @e::Expr @q::QNameAttrOccur
{
  top.flowDeps := 
    case e.flowVertexInfo of
    | just(vertex) -> vertex.synVertex(q.attrDcl.fullName) :: vertex.eqVertex
    | nothing() -> e.flowDeps
    end;
  e.decSiteVertexInfo = nothing();
}
aspect production inhDecoratedAccessHandler
top::Expr ::= @e::Expr @q::QNameAttrOccur
{
  top.flowDeps :=
    case e.flowVertexInfo of
    | just(vertex) -> vertex.inhVertex(q.attrDcl.fullName) :: vertex.eqVertex
    | nothing() -> e.flowDeps
    end;
  e.decSiteVertexInfo = nothing();
}
aspect production transDecoratedAccessHandler
top::Expr ::= @e::Expr @q::QNameAttrOccur
{
  production refSet::Maybe<[String]> = getMaxRefSet(top.finalType, top.env);
  top.flowVertexInfo = map(transAttrVertexType(_, q.attrDcl.fullName), e.flowVertexInfo);
  top.flowDeps := 
    case e.flowVertexInfo of
    | just(vertex) -> vertex.synVertex(q.attrDcl.fullName) :: vertex.eqVertex ++
      if top.finalType.isDecorated then map(vertex.inhVertex, fromMaybe([], refSet)) else []
    | nothing() -> e.flowDeps
    end;
  e.decSiteVertexInfo = nothing();
}
aspect production annoAccessHandler
top::Expr ::= @e::Expr @q::QNameAttrOccur
{
  e.decSiteVertexInfo = nothing();
}
aspect production synDataAccessHandler
top::Expr ::= @e::Expr @q::QNameAttrOccur
{
  -- No flow vertex, since there are never any inh deps

  e.decSiteVertexInfo = nothing();
}
aspect production inhUndecoratedAccessErrorHandler
top::Expr ::= @e::Expr @q::QNameAttrOccur
{
  e.decSiteVertexInfo = nothing();
}
aspect production transUndecoratedAccessErrorHandler
top::Expr ::= @e::Expr @q::QNameAttrOccur
{
  e.decSiteVertexInfo = nothing();
}
aspect production unknownDclAccessHandler
top::Expr ::= @e::Expr @q::QNameAttrOccur
{
  e.decSiteVertexInfo = nothing();
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
  inh.decorationVertex = "__decorate" ++ toString(genInt()) ++ ":line" ++ toString(getParsedOriginLocationOrFallback(top).line);

  -- Next, emit the "local equation" for this anonymous flow vertex.
  -- This means only the deps in 'e', see above conceptual transformation to see why.
  -- N.B. 'inh.flowDefs' will emit 'localInhEq's for this anonymous flow vertex.
  local eTy::Type = e.finalType;
  top.flowDefs <-
    [anonEq(top.frame.fullName, inh.decorationVertex, eTy.typeName, eTy.isNonterminal, getParsedOriginLocationOrFallback(top), e.flowDeps)];

  -- Now, we represent ourselves to anything that might use us specially
  -- as though we were a reference to this anonymous local
  top.flowVertexInfo = just(anonVertexType(inh.decorationVertex));
  e.decSiteVertexInfo = just(anonVertexType(inh.decorationVertex));
  -- The type of decorate ... with ... is a normal reference for now, so this should always be false, but that could change.
  e.alwaysDecorated = top.alwaysDecorated;

  -- Finally, our standard flow deps mimic those of a local: "taking a reference"
  -- This are of course ignored when treated specially.
  production refSet::Maybe<[String]> = getMaxRefSet(top.finalType, top.env);
  top.flowDeps := [anonEqVertex(inh.decorationVertex)] ++
    map(anonVertexType(inh.decorationVertex).inhVertex, fromMaybe([], refSet));

  -- If we have a type var with occurs-on contexts, add the specified syn -> inh deps for the new vertex
  top.flowDefs <- occursContextDeps(top.frame.signature, top.env, top.finalType, anonVertexType(inh.decorationVertex));
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
  e1.decSiteVertexInfo = nothing();
  e1.alwaysDecorated = false;
}

aspect production decorationSiteExpr
top::Expr ::= '@' e::Expr
{
  top.flowVertexInfo = e.flowVertexInfo;

  top.flowDefs <-
    case e.flowVertexInfo, top.decSiteVertexInfo of
    | just(ref), just(decSite) ->
      [refDecSiteEq(top.frame.fullName, e.typerep.typeName, ref, decSite, top.alwaysDecorated)]
    | _, _ -> []
    end;
}

aspect production ifThenElse
top::Expr ::= 'if' e1::Expr 'then' e2::Expr 'else' e3::Expr
{
  e1.decSiteVertexInfo = nothing();
  e2.decSiteVertexInfo = top.decSiteVertexInfo;
  e3.decSiteVertexInfo = top.decSiteVertexInfo;
  e1.alwaysDecorated = false;
  e2.alwaysDecorated = false;
  e3.alwaysDecorated = false;
}

aspect production terminalConstructor
top::Expr ::= 'terminal' '(' t::TypeExpr ',' es::Expr ',' el::Expr ')'
{
  es.decSiteVertexInfo = nothing();
  el.decSiteVertexInfo = nothing();
  es.alwaysDecorated = false;
  el.alwaysDecorated = false;
}

aspect production exprsSingle
top::Exprs ::= e::Expr
{
  e.decSiteVertexInfo = nothing();
  e.alwaysDecorated = false;
}
aspect production exprsCons
top::Exprs ::= e1::Expr ',' e2::Exprs
{
  e1.decSiteVertexInfo = nothing();
  e1.alwaysDecorated = false;
}

aspect production lambdap
top::Expr ::= params::LambdaRHS e::Expr
{
  e.decSiteVertexInfo = nothing();
  e.alwaysDecorated = false;
}

-- FROM LET TODO
attribute flowDefs, flowEnv occurs on AssignExpr;
propagate flowDefs, flowEnv on AssignExpr;

aspect production letp
top::Expr ::= la::AssignExpr  e::Expr
{
  top.flowDeps := e.flowDeps;
  top.flowVertexInfo = e.flowVertexInfo;
  e.decSiteVertexInfo = top.decSiteVertexInfo;
  e.alwaysDecorated = top.alwaysDecorated;
}

aspect production assignExpr
top::AssignExpr ::= id::Name '::' t::TypeExpr '=' e::Expr
{
  e.decSiteVertexInfo = nothing();
  e.alwaysDecorated = false;
}

aspect production lexicalLocalReference
top::Expr ::= @q::QName  fi::Maybe<VertexType>  fd::[FlowVertex]
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
    | just(vertex) ->
        if performSubstitution(q.lookupValue.typeScheme.monoType, top.finalSubst).isDecorated &&
           !top.finalType.isDecorated
        then vertex.eqVertex -- we're a `t` emulating `new(t)`
        else fd -- we're passing along our vertex-ness to the outer expression
    | nothing() -> fd -- we're actually being used as a ref-set-taking decorated var
    end;
  top.flowVertexInfo = fi;
}


-- FROM PATTERN TODO
attribute flowDeps, flowDefs, flowEnv, decSiteVertexInfo, alwaysDecorated, scrutineeVertexType occurs on PrimPatterns, PrimPattern;
propagate flowDeps, flowDefs, flowEnv, decSiteVertexInfo, alwaysDecorated, scrutineeVertexType on PrimPatterns, PrimPattern;

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
  local anonName :: String = "__scrutinee" ++ toString(genInt()) ++ ":line" ++ toString(getParsedOriginLocationOrFallback(e).line);

  pr.scrutineeVertexType =
    case e.flowVertexInfo of
    | just(vertex) -> vertex
    | nothing() -> anonVertexType(anonName)
    end;

  -- Let's make sure for decorated types, we only demand what's necessary for forward
  -- evaluation.
  top.flowDeps := pr.flowDeps ++ f.flowDeps ++
    (pr.scrutineeVertexType.fwdVertex :: pr.scrutineeVertexType.eqVertex);

  local eTy::Type = e.finalType;
  top.flowDefs <-
    case e.flowVertexInfo of
    | just(vertex) -> []
    | nothing() -> [anonEq(top.frame.fullName, anonName, eTy.typeName, eTy.isNonterminal, getParsedOriginLocationOrFallback(top), e.flowDeps)]
    end;
  -- We want to use anonEq here because that introduces the nonterminal stitch point for our vertex.

  e.decSiteVertexInfo = nothing();
  pr.decSiteVertexInfo = top.decSiteVertexInfo;
  f.decSiteVertexInfo = top.decSiteVertexInfo;
  e.alwaysDecorated = false;
  pr.alwaysDecorated = false;
  f.alwaysDecorated = false;
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
