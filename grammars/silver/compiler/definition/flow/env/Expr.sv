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
 - Only dependencies of this expression that correspond to constructing the outer tree node.
 - In local/forward/trans attr equations, sub-tree nodes have their own equation vertices.
 -}
monoid attribute outerFlowDeps :: [FlowVertex];

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
 - but we can't guarantee that e.env is defined in case cond is false.
 -}
inherited attribute alwaysDecorated :: Boolean;

{--
 - The decSiteVertexInfo where this expression is directly applied.
 -}
inherited attribute appDecSiteVertexInfo :: Maybe<VertexType>;

{--
 - Mappings of lexical local (let/pattern var) bindings referenced in this expression,
 - to their decoration site vertices and whether they are always decorated.
 -}
monoid attribute lexicalLocalDecSites :: [(String, Maybe<VertexType>)];
monoid attribute lexicalLocalAlwaysDecorated :: [(String, Boolean)];
-- flowDefs because expressions (decorate, patterns) can now generate stitchpoints
attribute flowDeps, flowDefs, flowEnv, lexicalLocalDecSites, lexicalLocalAlwaysDecorated
  occurs on Expr, ExprInhs, ExprInh, Exprs, AppExprs, AppExpr, AnnoAppExprs, AnnoExpr;
attribute outerFlowDeps, flowVertexInfo occurs on Expr;

monoid attribute argOuterFlowDeps :: [[FlowVertex]] occurs on AppExprs, AppExpr;

flowtype Expr =
  flowVertexInfo {forward}, flowDeps {forward}, flowDefs {forward, alwaysDecorated},
  lexicalLocalDecSites {forward}, lexicalLocalAlwaysDecorated {forward, alwaysDecorated};

propagate flowDeps on Expr, ExprInhs, ExprInh, Exprs, AppExprs, AppExpr, AnnoAppExprs, AnnoExpr
  excluding
    forwardAccess, synDecoratedAccessHandler, inhDecoratedAccessHandler, transDecoratedAccessHandler,
    decorateExprWith, letp, lexicalLocalReference, matchPrimitiveReal;
propagate flowDefs, flowEnv, lexicalLocalDecSites, lexicalLocalAlwaysDecorated
  on Expr, ExprInhs, ExprInh, Exprs, AppExprs, AppExpr, AnnoAppExprs, AnnoExpr;

attribute decSiteVertexInfo, alwaysDecorated occurs on Expr, AppExprs, AppExpr;
propagate decSiteVertexInfo, alwaysDecorated, argOuterFlowDeps on AppExprs;

attribute appDecSiteVertexInfo occurs on Expr;

aspect default production
top::Expr ::=
{
  top.outerFlowDeps := top.flowDeps;
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
  top.flowDeps <-
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
  -- Always a nonterminal type, but check if it's decorated in case it's a data NT:
  production refSet::Maybe<[String]> = getMaxRefSet(top.finalType, top.env);
  top.flowDeps <-
    if top.finalType.isDecorated
    then map(lhsInhVertex, fromMaybe([], refSet))
    else [];
  top.flowVertexInfo = 
    if top.finalType.isDecorated
    then just(lhsVertexType())
    else nothing();
}
aspect production localReference
top::Expr ::= @q::QName
{
  -- Again, q give the actual type written.
  production refSet::Maybe<[String]> = getMaxRefSet(top.finalType, top.env);
  production origRefSet::[String] = getMinRefSet(q.lookupValue.typeScheme.monoType, top.env);
  top.flowDeps <- localEqVertex(q.lookupValue.fullName) ::
    if isDecorable(q.lookupValue.typeScheme.monoType, top.env) && top.finalType.isDecorated
    then map(localVertexType(q.lookupValue.fullName).inhVertex, removeAll(origRefSet, fromMaybe([], refSet)))
    else [];
  top.flowVertexInfo =
    if isDecorable(q.lookupValue.typeScheme.monoType, top.env) && top.finalType.isDecorated
    then just(localVertexType(q.lookupValue.fullName))
    else nothing();
}
aspect production nondecLocalReference
top::Expr ::= @q::QName
{
  -- Never decorated - just the equation vertex.
  top.flowDeps <- [localEqVertex(q.lookupValue.fullName)];
  top.flowVertexInfo = nothing();
  top.flowDefs <-
    case top.decSiteVertexInfo of
    | just(v) -> [holeEq(top.frame.fullName, top.typerep.typeName, top.typerep.isNonterminal, v, top.flowDeps)]
    | nothing() -> []
    end;
}
aspect production forwardReference
top::Expr ::= @q::QName
{
  -- Always a non-data nonterminal type.
  production refSet::Maybe<[String]> = getMaxRefSet(top.finalType, top.env);
  top.flowDeps <- lhsSynVertex("forward") :: map(forwardInhVertex, fromMaybe([], refSet));
  top.flowVertexInfo = just(forwardVertexType());
}
aspect production forwardParentReference
top::Expr ::= 'forwardParent'
{
  production refSet::Maybe<[String]> = getMaxRefSet(top.finalType, top.env);
  top.flowDeps <-
    if top.finalType.isDecorated
    then map(forwardParentVertexType().inhVertex, fromMaybe([], refSet))
    else [];
  top.flowVertexInfo =
    if top.finalType.isDecorated
    then just(forwardParentVertexType())
    else nothing();
}

-- The named signature of the applied production.
-- Note that we don't project functions at the moment, since we don't build function flow graphs during inference.
inherited attribute appProd::Maybe<NamedSignature> occurs on AppExprs, AppExpr;

-- The offset of the first supplied signature element, if the production implements a dispatch signature and has extra children.
inherited attribute appIndexOffset::Integer occurs on AppExprs, AppExpr;
propagate appProd, appIndexOffset on AppExprs;

aspect production application
top::Expr ::= e::Expr '(' es::AppExprs ',' anns::AnnoAppExprs ')'
{
  propagate flowEnv;
  e.appDecSiteVertexInfo = top.decSiteVertexInfo;
  e.decSiteVertexInfo = nothing();
  e.alwaysDecorated = false;

  es.appProd =
    case e, e.finalType of
    | productionReference(q), _ -> just(q.lookupValue.dcl.namedSignature)
    | _, dispatchType(ns) -> just(ns)
    | _, _ -> nothing()
    end;

  top.flowDefs <-
    case es.appProd, top.decSiteVertexInfo of
    | just(ns), just(v) ->
        [subtermDecEq(
          top.frame.fullName, v, ns.fullName,
          zip(ns.inputNames, es.argOuterFlowDeps))]
    | _, just(v) -> [holeEq(top.frame.fullName, top.finalType.typeName, top.finalType.isNonterminal, v, top.flowDeps)]
    | _, _ -> []
    end;
}

aspect production errorApplication
top::Expr ::= @e::Expr @es::AppExprs @anns::AnnoAppExprs
{
  es.decSiteVertexInfo = nothing();
  es.alwaysDecorated = false;
  es.appIndexOffset = 0;
}

aspect production functionInvocation
top::Expr ::= @e::Expr @es::AppExprs @anns::AnnoAppExprs
{
  top.outerFlowDeps :=
    case e of
    | productionReference(_) when top.decSiteVertexInfo.isJust -> anns.flowDeps
    | _ -> top.flowDeps
    end;

  es.appIndexOffset =
    case e of
    | productionReference(q) when q.lookupValue.dcl.implementedSignature matches just(dSig) ->
      if length(q.lookupValue.dcl.namedSignature.inputElements) > length(dSig.inputElements)
      then length(dSig.inputElements)
      else 0
    | _ -> 0
    end;
  es.decSiteVertexInfo = top.decSiteVertexInfo;
  es.alwaysDecorated = top.alwaysDecorated;
}

aspect production partialApplication
top::Expr ::= @e::Expr @es::AppExprs @anns::AnnoAppExprs
{
  es.appIndexOffset =
    case e of
    | productionReference(q) when q.lookupValue.dcl.implementedSignature matches just(dSig) ->
      if length(q.lookupValue.dcl.namedSignature.inputElements) > length(dSig.inputElements)
      then length(dSig.inputElements)
      else 0
    | _ -> 0
    end;
  es.decSiteVertexInfo = nothing();
  es.alwaysDecorated = false;
}

aspect production curriedDispatchApplication
top::Expr ::= @e::Expr @es::AppExprs @anns::AnnoAppExprs
{
  es.appIndexOffset = 0;
  es.decSiteVertexInfo = top.decSiteVertexInfo;
  es.alwaysDecorated = top.alwaysDecorated;

  -- We override these attributes in what we forward to, as a special case to
  -- be more precise about what production is being applied.
  dispatchArgs.appProd = es.appProd;
  dispatchArgs.appIndexOffset = 0;
  dispatchArgs.decSiteVertexInfo = top.decSiteVertexInfo;
  dispatchArgs.alwaysDecorated = top.alwaysDecorated;
  extraArgs.appProd = es.appProd;
  extraArgs.appIndexOffset = dispatchArgs.appExprSize;
  extraArgs.decSiteVertexInfo = top.decSiteVertexInfo;
  extraArgs.alwaysDecorated = top.alwaysDecorated;
}

aspect production dispatchApplication
top::Expr ::= @e::Expr @es::AppExprs @anns::AnnoAppExprs
{
  top.outerFlowDeps := e.flowDeps ++ anns.flowDeps;
  es.appIndexOffset = 0;
  es.decSiteVertexInfo = top.decSiteVertexInfo;
  es.alwaysDecorated = top.alwaysDecorated;
}

aspect production annoUpdatePositionalErrorApplication
top::Expr ::= @e::Expr @es::AppExprs @anns::AnnoAppExprs
{
  es.decSiteVertexInfo = nothing();
  es.alwaysDecorated = false;
  es.appIndexOffset = 0;
}

aspect production annoUpdateInvocation
top::Expr ::= @e::Expr @es::AppExprs @anns::AnnoAppExprs
{
  es.decSiteVertexInfo = nothing();
  es.alwaysDecorated = false;
  es.appIndexOffset = 0;
}

aspect production annoUpdatePartialApplication
top::Expr ::= @e::Expr @es::AppExprs @anns::AnnoAppExprs
{
  es.decSiteVertexInfo = nothing();
  es.alwaysDecorated = false;
  es.appIndexOffset = 0;
}

aspect production annoExpr
top::AnnoExpr ::= qn::QName '=' e::AppExpr
{
  e.decSiteVertexInfo = nothing();
  e.appProd = nothing();
  e.appIndexOffset = 0;
  e.alwaysDecorated = false;
}

aspect production missingAppExpr
top::AppExpr ::= _
{
  top.argOuterFlowDeps := [];
}

aspect production presentAppExpr
top::AppExpr ::= e::Expr
{
  top.argOuterFlowDeps := [e.outerFlowDeps];
  production sigIndex::Integer = top.appExprIndex + top.appIndexOffset;
  production sigName::String =  -- Name of the corresponding child of the production/dispatch being applied
    case top.appProd of
    | just(ns) when sigIndex < length(ns.inputNames) -> head(drop(sigIndex, ns.inputNames))
    | _ -> "err"
    end;

  -- Capture the equation dependencies for non-decorable children.
  -- Additional optimization: if the expression is a "boring" tree literal with no flow
  -- dependencies or sharing, just treat it as a hole.
  -- Using the flow type is less precise than a tile stitch point, but this
  -- avoids a massive blowup in the size of the flow graph when constructing
  -- large trees.
  top.flowDefs <-
    case top.decSiteVertexInfo, top.appProd of
    | just(parent), just(ns) when
        !sigIsShared &&
        (!isDecorable(top.appExprTyperep, top.env) ||
         (null(e.flowDeps) && null(e.sharedRefs))) ->
      [holeEq(
        top.frame.fullName, top.appExprTyperep.typeName, false,
        subtermVertexType(parent, ns.fullName, sigName),
        e.flowDeps)]
    | _, _ -> []
    end;
  e.decSiteVertexInfo =
    case top.decSiteVertexInfo, top.appProd of
    | just(parent), just(ns) when
        !sigIsShared &&
        isDecorable(top.appExprTyperep, top.env) &&
        !(null(e.flowDeps) && null(e.sharedRefs)) ->
      just(subtermVertexType(parent, ns.fullName, sigName))
    | _, _ -> nothing()
    end;
  e.alwaysDecorated = top.alwaysDecorated && e.decSiteVertexInfo.isJust;
  e.appDecSiteVertexInfo = nothing();

  production inputSigIsShared::Boolean =
    case e.flowVertexInfo of
    | just(rhsVertexType(sigName))
        when getValueDcl(sigName, top.env) matches dcl :: _ -> dcl.isShared
    | _ -> false
    end;
  production sigIsShared::Boolean =
    case top.appProd of
    | just(ns) ->
      sigIndex < length(ns.inputNames) && head(drop(sigIndex, ns.inputElements)).elementShared
    | _ -> false
    end;
  production sigDecSite::Maybe<VertexType> =
    case top.decSiteVertexInfo, top.appProd of
    | just(parent), just(ns) when sigIsShared ->
      just(subtermVertexType(parent, ns.fullName, sigName))
    | _, _ -> nothing()
    end;
  production isForwardParam::Boolean =
    -- Don't try to share if someone uses a signature sharing prod somewhere invalid.
    case top.decSiteVertexInfo of
    | just(forwardVertexType()) -> true
    | just(localVertexType(fName)) when isForwardProdAttr(top.frame.fullName, fName, top.flowEnv) -> true
    | _ -> false
    end;
  top.flowDefs <-
    case sigDecSite, top.appProd, e.flowVertexInfo of
    | just(decSite), just(ns), just(v) ->
      refDecSiteEq(top.frame.fullName, e.finalType.typeName, v, decSite, top.alwaysDecorated) ::
      if inputSigIsShared then []
      else [sigShareSite(ns.fullName, e.finalType.typeName, sigName, top.frame.fullName, v)]
    | _, _, _ -> []
    end;
}

aspect production noteAttachment
top::Expr ::= 'attachNote' note::Expr 'on' e::Expr 'end'
{
  top.outerFlowDeps := note.flowDeps ++ e.outerFlowDeps;
  note.decSiteVertexInfo = nothing();
  e.decSiteVertexInfo = top.decSiteVertexInfo;
  note.alwaysDecorated = false;
  e.alwaysDecorated = top.alwaysDecorated;
  note.appDecSiteVertexInfo = nothing();
  e.appDecSiteVertexInfo = top.appDecSiteVertexInfo;
}

aspect production access
top::Expr ::= e::Expr '.' q::QNameAttrOccur
{
  propagate flowEnv;
  e.alwaysDecorated = false;
  e.decSiteVertexInfo = nothing();
  e.appDecSiteVertexInfo = nothing();
}

aspect production accessBouncer
top::Expr ::= e::Expr  @q::QNameAttrOccur target::Access
{
  propagate flowEnv;
  e.alwaysDecorated = false;
  e.decSiteVertexInfo = nothing();
  e.appDecSiteVertexInfo = nothing();
}

aspect production forwardAccess
top::Expr ::= e::Expr '.' 'forward'
{
  top.flowDeps := 
    case e.flowVertexInfo of
    | just(vertex) -> vertex.fwdVertex :: vertex.eqVertex
    | nothing() -> e.flowDeps
    end;
  top.flowDefs <-
    case top.decSiteVertexInfo of
    | just(v) -> [holeEq(top.frame.fullName, top.typerep.typeName, top.typerep.isNonterminal, v, top.flowDeps)]
    | nothing() -> []
    end;
  e.decSiteVertexInfo = nothing();
  e.alwaysDecorated = false;
  e.appDecSiteVertexInfo = nothing();
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
  top.flowDefs <-
    case top.decSiteVertexInfo of
    | just(v) -> [holeEq(top.frame.fullName, top.typerep.typeName, top.typerep.isNonterminal, v, top.flowDeps)]
    | nothing() -> []
    end;
}
aspect production inhDecoratedAccessHandler
top::Expr ::= @e::Expr @q::QNameAttrOccur
{
  top.flowDeps :=
    case e.flowVertexInfo of
    | just(vertex) -> vertex.inhVertex(q.attrDcl.fullName) :: vertex.eqVertex
    | nothing() -> e.flowDeps
    end;
  top.flowDefs <-
    case top.decSiteVertexInfo of
    | just(v) -> [holeEq(top.frame.fullName, top.typerep.typeName, top.typerep.isNonterminal, v, top.flowDeps)]
    | nothing() -> []
    end;
}
aspect production transDecoratedAccessHandler
top::Expr ::= @e::Expr @q::QNameAttrOccur
{
  production refSet::Maybe<[String]> = getMaxRefSet(top.finalType, top.env);
  top.flowVertexInfo = map(transAttrVertexType(_, q.attrDcl.fullName), e.flowVertexInfo);
  top.flowDeps := 
    case e.flowVertexInfo of
    | just(vertex) -> vertex.synVertex(q.attrDcl.fullName) :: vertex.eqVertex ++
      map(transAttrVertexType(vertex, q.attrDcl.fullName).inhVertex, fromMaybe([], refSet))
    | nothing() -> e.flowDeps
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
  inh.decorationVertex = "__decorate" ++ toString(genInt()) ++ ":line" ++ toString(getParsedOriginLocationOrFallback(top).line);

  -- Next, emit the "local equation" for this anonymous flow vertex.
  -- This means only the deps in 'e', see above conceptual transformation to see why.
  -- N.B. 'inh.flowDefs' will emit 'localInhEq's for this anonymous flow vertex.
  local eTy::Type = e.finalType;
  top.flowDefs <-
    [anonEq(top.frame.fullName, inh.decorationVertex, getParsedOriginLocationOrFallback(top), e.flowDeps)];

  -- Now, we represent ourselves to anything that might use us specially
  -- as though we were a reference to this anonymous local
  top.flowVertexInfo = just(anonVertexType(inh.decorationVertex));
  e.decSiteVertexInfo = just(anonVertexType(inh.decorationVertex));
  -- The type of decorate ... with ... is a normal reference for now, so this should always be false, but that could change.
  e.alwaysDecorated = top.alwaysDecorated;
  e.appDecSiteVertexInfo = nothing();

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
  e1.appDecSiteVertexInfo = nothing();
}

aspect production decorationSiteExpr
top::Expr ::= '@' e::Expr
{
  top.flowVertexInfo = e.flowVertexInfo;
  e.decSiteVertexInfo = nothing();
  e.alwaysDecorated = false;
  e.appDecSiteVertexInfo = nothing();

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
  top.outerFlowDeps := e1.flowDeps ++ e2.outerFlowDeps ++ e3.outerFlowDeps;
  e1.decSiteVertexInfo = nothing();
  e2.decSiteVertexInfo = top.decSiteVertexInfo;
  e3.decSiteVertexInfo = top.decSiteVertexInfo;
  e1.alwaysDecorated = false;
  e2.alwaysDecorated = false;
  e3.alwaysDecorated = false;
  e1.appDecSiteVertexInfo = nothing();
  e2.appDecSiteVertexInfo = nothing();
  e3.appDecSiteVertexInfo = nothing();
}

aspect production terminalConstructor
top::Expr ::= 'terminal' '(' t::TypeExpr ',' es::Expr ',' el::Expr ')'
{
  es.decSiteVertexInfo = nothing();
  el.decSiteVertexInfo = nothing();
  es.alwaysDecorated = false;
  el.alwaysDecorated = false;
  es.appDecSiteVertexInfo = nothing();
  el.appDecSiteVertexInfo = nothing();
}

aspect production exprsSingle
top::Exprs ::= e::Expr
{
  e.decSiteVertexInfo = nothing();
  e.alwaysDecorated = false;
  e.appDecSiteVertexInfo = nothing();
}
aspect production exprsCons
top::Exprs ::= e1::Expr ',' e2::Exprs
{
  e1.decSiteVertexInfo = nothing();
  e1.alwaysDecorated = false;
  e1.appDecSiteVertexInfo = nothing();
}

aspect production lambdap
top::Expr ::= params::LambdaRHS e::Expr
{
  e.decSiteVertexInfo = nothing();
  e.alwaysDecorated = false;
  e.appDecSiteVertexInfo = nothing();
}

-- FROM LET TODO
attribute flowDefs, flowEnv occurs on AssignExpr;
propagate flowDefs, flowEnv on AssignExpr;

inherited attribute bodyDecSites :: [(String, Maybe<VertexType>)] occurs on AssignExpr;
inherited attribute bodyAlwaysDecorated :: [(String, Boolean)] occurs on AssignExpr;
propagate bodyDecSites, bodyAlwaysDecorated on AssignExpr;

aspect production letp
top::Expr ::= la::AssignExpr  e::Expr
{
  top.flowDeps := e.flowDeps;
  top.flowVertexInfo = e.flowVertexInfo;
  la.bodyDecSites = e.lexicalLocalDecSites;
  la.bodyAlwaysDecorated = e.lexicalLocalAlwaysDecorated;
  e.decSiteVertexInfo = top.decSiteVertexInfo;
  e.alwaysDecorated = top.alwaysDecorated;
  e.appDecSiteVertexInfo = nothing();
}

aspect production assignExpr
top::AssignExpr ::= id::Name '::' t::TypeExpr '=' e::Expr
{
  e.decSiteVertexInfo =
    case nub(lookupAll(fName, top.bodyDecSites)) of
    | [v] -> v
    | _ -> nothing()
    end;
  e.alwaysDecorated = lookupAll(fName, top.bodyAlwaysDecorated) == [true];
  e.appDecSiteVertexInfo = nothing();
}

aspect production lexicalLocalReference
top::Expr ::= @q::QName  fi::Maybe<VertexType>  fd::[FlowVertex]  _
{
  top.flowDeps := fd;
  top.flowVertexInfo = fi;

  top.lexicalLocalDecSites <- [(q.lookupValue.fullName, top.decSiteVertexInfo)];
  top.lexicalLocalAlwaysDecorated <- [(q.lookupValue.fullName, top.alwaysDecorated)];
}


-- FROM PATTERN TODO
attribute flowDeps, outerFlowDeps, flowDefs, flowEnv, decSiteVertexInfo, alwaysDecorated, appDecSiteVertexInfo, scrutineeVertexType
  occurs on PrimPatterns, PrimPattern;
propagate flowDeps, outerFlowDeps, flowDefs, flowEnv, decSiteVertexInfo, alwaysDecorated, appDecSiteVertexInfo, scrutineeVertexType
  on PrimPatterns, PrimPattern;

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
  top.outerFlowDeps := pr.outerFlowDeps ++ f.outerFlowDeps ++
    (pr.scrutineeVertexType.fwdVertex :: pr.scrutineeVertexType.eqVertex);

  local eTy::Type = e.finalType;
  top.flowDefs <-
    case e.flowVertexInfo of
    | just(vertex) -> []
    | nothing() ->
      -- Add the dependencies and nonterminal stitch point for the anon vertex we created:
      [anonEq(top.frame.fullName, anonName, getParsedOriginLocationOrFallback(top), e.flowDeps),
       holeEq(top.frame.fullName, eTy.typeName, eTy.isNonterminal, anonVertexType(anonName), [])]
    end;

  e.decSiteVertexInfo = nothing();
  pr.decSiteVertexInfo = top.decSiteVertexInfo;
  f.decSiteVertexInfo = top.decSiteVertexInfo;
  e.alwaysDecorated = false;
  pr.alwaysDecorated = false;
  f.alwaysDecorated = false;
  e.appDecSiteVertexInfo = nothing();
  pr.appDecSiteVertexInfo = nothing();
  f.appDecSiteVertexInfo = nothing();
}

aspect production prodPattern
top::PrimPattern ::= qn::QName '(' ns::VarBinders ')' _ e::Expr
{
  propagate flowDeps, outerFlowDeps, flowDefs, flowEnv, decSiteVertexInfo, alwaysDecorated, scrutineeVertexType;
  top.flowDefs <-
    [patternRuleEq(top.frame.fullName, qn.lookupValue.fullName, top.scrutineeVertexType, ns.flowProjections)];
  e.appDecSiteVertexInfo = nothing();
}
