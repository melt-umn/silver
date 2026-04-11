grammar silver:compiler:extension:scopegraphs;

------------------------------------------
-- Scope attribute environment declaration

abstract production scopeInhDcl
top::AttributeDclInfo ::= fn::String ty::Type sglabs::[String]
{
  propagate compareKey;
  
  top.propagateDispatcher = propagateInh;
  top.decoratedAccessHandler = inhDecoratedAccessHandler;
  top.undecoratedAccessHandler = accessBounceDecorate (inhDecoratedAccessHandler);
  top.dataAccessHandler = inhUndecoratedAccessErrorHandler;

  top.attrDefDispatcher = scopeAttributeDef;
  top.attributionDispatcher = scopeAttributeAttributionDcl;

  top.fullName = fn;
  top.typeScheme = monoType(^ty);
  top.isInherited = true;
}

-----------------------------
-- Scope attribute definition

-- todo: reduce number of times we loop over label list
abstract production scopeAttributeDef implements AttributeDef
top::ProductionStmt ::= @dl::DefLHS @attr::QNameAttrOccur e::Expr
{
  local errCheck :: TypeCheck = check(attr.typerep, e.typerep);
  errCheck.finalSubst = top.finalSubst;
  thread downSubst, upSubst on top, e, errCheck, top;

  local attrQn::QName = case attr of qNameAttrOccur(qn) -> ^qn end;
  attrQn.env = top.env;

  e.env = top.env;
  e.isRoot = true;
  e.flowEnv = top.flowEnv;
  e.appDecSiteVertexInfo = nothing();
  e.decSiteVertexInfo = nothing();
  e.alwaysDecorated = true;
  e.config = top.config;
  e.compiledGrammars = top.compiledGrammars;
  e.originRules = [];
  e.grammarName = top.grammarName;
  e.frame = top.frame;
  e.finalSubst = top.finalSubst;
  --e.downSubst = top.downSubst;

  local labs::[String] =
    let res::AttributeDclInfo = attrQn.lookupAttribute.dcl in
      case res of
      | scopeInhDcl(_, _, labs) -> labs
      | _ -> error("scopeAttributeAttributionDcl.labs")
      end
    end;

  local maybeAttrContribs::Maybe<ProductionStmt> = contributions(^dl, ^attr, e, labs);
  nondecorated local attrContribs::ProductionStmt = fromMaybe(emptyProductionStmt(), maybeAttrContribs);

  local inhDef::ProductionStmt = inheritedAttributeDef(dl, attr, ^e);

  forwards to productionStmtAppend(@inhDef, attrContribs);

  top.errors := inhDef.errors;
  top.errors <-
    if !maybeAttrContribs.isJust
    then [errFromOrigin(top,
            "Scope attribute " ++ attr.name ++
            " must be defined as a locally asserted scope, or scope attribute on " ++
            top.frame.signature.outputElement.elementName)]
    else [];
}

-- todo: generate appropriate type error message if expr is not a Decorated Scope
fun contributions Maybe<ProductionStmt> ::=
  dl::DefLHS attr::QNameAttrOccur e::Decorated Expr labs::[String] =
  let asEdgeContribBaseExpr::(ProductionStmt ::= QName String) = \qn::QName lab::String ->
    Silver_ProductionStmt {
      $QName{qn}.$QName{qName(lab)} <-
        $QName{qName(dl.name)}.$QName{qName(attr.name ++ "_" ++ lab)}; }
  in
  let asUndecContribBaseExpr::(ProductionStmt ::= QName) = \qn::QName ->
    Silver_ProductionStmt {
      $QName{qName(qn.name ++ "_undec")} <-
        $QName{qName(dl.name)}.$QName{qName(attr.name ++ "_undec")}; }
  in
    case e of
    | baseExpr(qn) ->
        let edgeContribs::[ProductionStmt] =
              map(asEdgeContribBaseExpr(^qn, _), labs) in
        let undecContrib::ProductionStmt = 
              asUndecContribBaseExpr(^qn)
        in
          just(foldr(productionStmtAppend(_, _), emptyProductionStmt(),
                    undecContrib::edgeContribs))
        end end
    | access(baseExpr(qn1), _, qNameAttrOccur(qn2)) ->
        let allContribs::[ProductionStmt] =
          map(\i::String -> Silver_ProductionStmt {
                $QName{^qn1}.$QName{qName(qn2.name ++ "_" ++ i)} <-
                  $QName{qName(dl.name)}.$QName{qName(attr.name ++ "_" ++ i)};},
              "undec"::labs)
        in
          just(foldr(productionStmtAppend(_, _), emptyProductionStmt(),
                    allContribs))
        end
    | _ -> nothing()
    end 
  end end;

--------------------
-- Scope attribution

-- todo: reduce number of times we loop over label list
abstract production scopeAttributeAttributionDcl implements AttributionDcl
top::AGDcl ::= at::QName attl::BracketedOptTypeExprs nt::QName
               nttl::BracketedOptTypeExprs
{
  at.env = top.env;

  local labs::[String] =
    let res::AttributeDclInfo = at.lookupAttribute.dcl in
      case res of
      | scopeInhDcl(_, _, labs) -> labs
      | _ -> error("scopeAttributeAttributionDcl.labs")
      end
    end;

  forwards to appendAGDcl(
    defaultAttributionDcl(^at, botlNone(), ^nt, ^nttl),
    edgeOccDclsBaseDefs(^at, ^nt, ^nttl, labs)
  );
}

fun edgeOccDclsBaseDefs AGDcl ::= at::QName nt::QName nttl::BracketedOptTypeExprs
                                  labs::[String] =
  let baseDef::(ProductionStmt ::= String) = \lab::String ->
    Silver_ProductionStmt { top.$QName{qnScopeAttr(at.name, lab)} := []; }
  in
  let occDcl::(AGDcl ::= String) = \lab::String ->
    attributionDcl('attribute', appendToQName(at, "_" ++ lab), botlNone(),
                   'occurs', 'on', nt, nttl, ';')
  in
  let occsBases::(AGDcl, ProductionStmt) = 
    foldrLastElem(
      \lab::String acc::(AGDcl, ProductionStmt) ->
        (appendAGDcl(occDcl(lab), acc.1), productionStmtAppend(baseDef(lab), acc.2)),
      \lab::String -> 
        (occDcl(lab), baseDef(lab)),
      "undec"::labs
    )
  in
    appendAGDcl(
      occsBases.1,
      Silver_AGDcl{
        aspect default production top::$TypeExpr{nominalTypeExpr(nt.qNameType)} ::=
        { $ProductionStmt{occsBases.2} }
      }
    )
  end end end;

----------------------------------
-- Scope graph definition (labels)

synthesized attribute labels::[String];
synthesized attribute labelsFn::[String];

nonterminal ScopeGraphDclInfo with fullName, isEqual, compareTo, labels, labelsFn;

abstract production graphDcl
top::ScopeGraphDclInfo ::= grammarName::String name::String labs::[String]
{
  top.fullName = {-grammarName ++ ":" ++-} name;
  top.isEqual = ^top.compareTo == ^top;
  top.labels = labs;
  top.labelsFn = map(\l::String -> grammarName ++ ":" ++ l, labs);
}

instance Eq ScopeGraphDclInfo {
  eq = \l::ScopeGraphDclInfo r::ScopeGraphDclInfo -> l.fullName == r.fullName;
}
