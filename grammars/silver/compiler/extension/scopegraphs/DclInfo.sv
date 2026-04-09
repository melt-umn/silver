grammar silver:compiler:extension:scopegraphs;

------------------------------------------
-- Scope attribute environment declaration

abstract production scopeInhDcl
top::AttributeDclInfo ::= fn::String ty::Type sglabs::[String]
{
  propagate compareKey;
  
  top.propagateDispatcher = propagateError; -- todo
  top.decoratedAccessHandler = inhDecoratedAccessHandler;
  top.undecoratedAccessHandler = error("scopeInhDcl.undecoratedAccessHandler todo");
  top.dataAccessHandler = error("scopeInhDcl.dataAccessHandler todo");
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
  e.downSubst = top.downSubst;

  local labs::[String] =
    let res::AttributeDclInfo = attrQn.lookupAttribute.dcl in
      case res of
      | scopeInhDcl(_, _, labs) -> labs
      | _ -> error("Impossible(?) silver:compiler:extension:scopegraphs:scopeAttributeAttributionDcl:labs")
      end
    end;

  forwards to productionStmtAppend(
    inheritedAttributeDef(dl, attr, ^e),
    productionStmtAppend(
      edgeContributions(^dl, ^attr, e, labs),
      undecContributions(^dl, ^attr, e)
    )
  );
}

-- todo: revisit cases here. need something more intricate and/or error message generation if patterns not matched
fun edgeContributions ProductionStmt ::= dl::DefLHS attr::QNameAttrOccur e::Decorated Expr labs::[String] =
  let edgeContrib::(ProductionStmt ::= String) = \lab::String ->
    case e of
    | baseExpr(qn) ->
      Silver_ProductionStmt {
        $QName{^qn}.$QName{qName(lab)} <- $QName{qName(dl.name)}.$QName{qName(attr.name ++ "_" ++ lab)};
      }
    | access(baseExpr(qn1), _, qNameAttrOccur(qn2)) ->
      Silver_ProductionStmt {
        $QName{^qn1}.$QName{qName(qn2.name ++ "_" ++ lab)} <- $QName{qName(dl.name)}.$QName{qName(attr.name ++ "_" ++ lab)};
      }
    | _ ->
      error("Impossible(?) silver:compiler:extension:scopegraphs:edgeContributions")
    end
  in
    foldrLastElem(
      \lab::String acc::ProductionStmt -> productionStmtAppend(edgeContrib(lab), acc),
      \lab::String -> edgeContrib(lab),
      labs
    )
  end;

-- todo: revisit cases here. need something more intricate and/or error message generation if patterns not matched
fun undecContributions ProductionStmt ::= dl::DefLHS attr::QNameAttrOccur e::Decorated Expr =
  case e of
  | baseExpr(qn) ->
    Silver_ProductionStmt {
      $QName{qName(qn.name ++ "_undec")} <- $QName{qName(dl.name)}.$QName{qName(attr.name ++ "_undec")};
    }
  | access(baseExpr(qn1), _, qNameAttrOccur(qn2)) ->
    Silver_ProductionStmt {
      $QName{^qn1}.$QName{qName(qn2.name ++ "_undec")} <- $QName{qName(dl.name)}.$QName{qName(attr.name ++ "_undec")};
    }
  | _ ->
    error("Impossible(?) silver:compiler:extension:scopegraphs:undecContributions")
  end;

--------------------
-- Scope attribution

-- todo: reduce number of times we loop over label list
abstract production scopeAttributeAttributionDcl implements AttributionDcl
top::AGDcl ::= at::QName attl::BracketedOptTypeExprs nt::QName nttl::BracketedOptTypeExprs
{
  at.env = top.env;

  local labs::[String] =
    let res::AttributeDclInfo = at.lookupAttribute.dcl in
      case res of
      | scopeInhDcl(_, _, labs) -> labs
      | _ -> error("Impossible(?) silver:compiler:extension:scopegraphs:scopeAttributeAttributionDcl:labs")
      end
    end;

  forwards to appendAGDcl(
    defaultAttributionDcl(^at, botlNone(), ^nt, ^nttl),
    appendAGDcl(
      edgeSynsOccurDcls(^at, ^nt, ^nttl, labs),
      aspectBaseDefinitions(^nt, at.name, labs)
    )
  );
}

fun edgeSynsOccurDcls AGDcl ::= at::QName nt::QName nttl::BracketedOptTypeExprs labs::[String] =
  let occDcl::(AGDcl ::= String) = \lab::String ->
    attributionDcl(
      'attribute', appendToQName(at, "_" ++ lab), botlNone(),
      'occurs', 'on', nt, nttl, ';'
    )
  in
    foldrLastElem(
      \lab::String acc::AGDcl -> appendAGDcl(occDcl(lab), acc),
      \lab::String -> occDcl(lab),
      "undec"::labs
    )
  end;

fun appendToQName QName ::= orig::QName extra::String =
  case orig of
  | qNameId(id) -> qNameId(name(id.name ++ extra))
  | qNameCons(id, _, qn) -> qNameCons(^id, ':', appendToQName(^qn, extra))
  | qNameError(_) -> error("Impossible(?) silver:compiler:extension:scopegraphs:appendToQName")
  end;

fun aspectBaseDefinitions AGDcl ::= nt::QName s::String labs::[String] =
  let baseDef::(ProductionStmt ::= String) = \lab::String ->
    Silver_ProductionStmt {
      top.$QName{qnScopeAttr(s, lab)} := [];
    }
  in
    Silver_AGDcl {
      aspect default production top::$TypeExpr{nominalTypeExpr(nt.qNameType)} ::=
      {
        $ProductionStmt{
          foldrLastElem(
            \lab::String acc::ProductionStmt -> productionStmtAppend(baseDef(lab), acc),
            \lab::String -> baseDef(lab),
            "undec"::labs
          )
        }
      }
    }
  end;