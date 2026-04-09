grammar silver:compiler:extension:scopegraphs;

--

fun mkLabelInhs AGDcl ::= sg::String labs::[String] =
  foldr(
    \lab::String acc::AGDcl ->
      appendAGDcl(
        Silver_AGDcl{
          inherited attribute
            $Name{name(lab)}::[$TypeExpr{scopeTypeExpr(sg)}]
          occurs on Scope;
        },
        acc
      ),
    emptyAGDcl(),
    labs
  );

fun scopeTypeExpr TypeExpr ::= sg::String =
  Silver_TypeExpr{
    Decorated Scope with
      $TypeExpr{nominalTypeExpr(qNameTypeId(terminal(IdUpper_t, sg)))}
  };

fun labelProdName
Name ::= lab::String =
  name("label_" ++ lab);

fun qnScopeAttr QName ::= s::String l::String = 
  qName(s ++ "_" ++ l);

fun nScopeAttr Name ::= s::String l::String =
  name(s ++ "_" ++ l);


-------------
-- DclInfo.sv

-- todo: revisit cases here. need something more intricate and/or error message generation if patterns not matched
fun edgeContributions ProductionStmt ::= dl::DefLHS attr::QNameAttrOccur
                                         e::Decorated Expr labs::[String] =
  let edgeContrib::(ProductionStmt ::= String) = \lab::String ->
    case e of
    | baseExpr(qn) ->
      Silver_ProductionStmt {
        $QName{^qn}.$QName{qName(lab)}
          <- $QName{qName(dl.name)}.$QName{qName(attr.name ++ "_" ++ lab)};
      }
    | access(baseExpr(qn1), _, qNameAttrOccur(qn2)) ->
      Silver_ProductionStmt {
        $QName{^qn1}.$QName{qName(qn2.name ++ "_" ++ lab)}
          <- $QName{qName(dl.name)}.$QName{qName(attr.name ++ "_" ++ lab)};
      }
    | _ ->
      error("edgeContributions")
    end
  in
    foldrLastElem(
      \lab::String acc::ProductionStmt -> productionStmtAppend(edgeContrib(lab), acc),
      \lab::String -> edgeContrib(lab),
      labs
    )
  end;

-- todo: revisit cases here. need something more intricate and/or error message generation if patterns not matched
fun undecContributions ProductionStmt ::= dl::DefLHS attr::QNameAttrOccur
                                          e::Decorated Expr =
  case e of
  | baseExpr(qn) ->
    Silver_ProductionStmt {
      $QName{qName(qn.name ++ "_undec")}
        <- $QName{qName(dl.name)}.$QName{qName(attr.name ++ "_undec")};
    }
  | access(baseExpr(qn1), _, qNameAttrOccur(qn2)) ->
    Silver_ProductionStmt {
      $QName{^qn1}.$QName{qName(qn2.name ++ "_undec")}
        <- $QName{qName(dl.name)}.$QName{qName(attr.name ++ "_undec")};
    }
  | _ ->
    error("undecContributions")
  end;

--

fun edgeSynsOccurDcls AGDcl ::= at::QName nt::QName nttl::BracketedOptTypeExprs
                                labs::[String] =
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
  | qNameError(_) -> error("appendToQName")
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


---------
-- Env.sv

fun lookupGraphDcl [ScopeGraphDclInfo] ::= sgfn::String sgEnv::SGEnv =
  searchEnvTree(sgfn, sgEnv.scopeGraphsTree)
;


---------------
-- GraphSpec.sv

-- Generate inherited attribute and production declarations for each label
fun labelsAGDcls AGDcl ::= sg::String labs::[String] =
  let labelAGDcls::(AGDcl ::= String) = 
    \lab::String -> appendAGDcl(labelInh(sg, lab), labelProd(sg, lab))
  in
    if null(labs)
    then error("Scope graph label list cannot be empty!")
    else
      foldrLastElem(
        \lab::String acc::AGDcl -> appendAGDcl(labelAGDcls(lab), acc),
        \lab::String -> labelAGDcls(lab),
        labs
      )
  end;

-- Generate a Label production declaration for a given label
fun labelProd AGDcl ::= sg::String lab::String =
  Silver_AGDcl {
    production $Name{labelProdName(lab)}
    top::Label<$TypeExpr{nominalTypeExpr(qNameTypeId(terminal(IdUpper_t, sg)))}> ::=
    {
      top.name = $Expr{stringConst(terminal(String_t, "\"" ++ lab ++ "\""))};
      top.demand = \s::$TypeExpr{scopeTypeExpr(sg)} -> s.$QName{qName(lab)};
    }
  };

-- Generate an inherited attribute declaration for a given label
fun labelInh AGDcl ::= sg::String lab::String =
  Silver_AGDcl {
    inherited attribute $Name{name(lab)}::[$TypeExpr{scopeTypeExpr(sg)}] with ++
    occurs on Scope;
  };


-------------
-- MkScope.sv

-- inh attr base exprs

fun mkScopeBaseInhs ProductionStmt ::= s::String labs::[String] =
  let baseEq::(ProductionStmt ::= String) = \lab::String ->
    Silver_ProductionStmt {
      $QName{qName(s)}.$QName{qName(lab)} := [];
    }
  in
    foldrLastElem(
      \lab::String acc::ProductionStmt -> productionStmtAppend(baseEq(lab), acc),
      \lab::String -> baseEq(lab),
      labs
    )
  end;


--------------------
-- ScopeAttribute.sv

fun scopeInh AGDcl ::= sg::String attr::String =
  Silver_AGDcl {
    inherited attribute $Name{name(attr)}::$TypeExpr{scopeTypeExpr(sg)};
  };

fun scopeSyns AGDcl ::= sg::String attr::String labs::[String] =
  let
    oneSyn::(AGDcl ::= String) = \lab::String ->
      Silver_AGDcl {
        synthesized attribute 
          $Name{nScopeAttr(attr, lab)}::[$TypeExpr{scopeTypeExpr(sg)}]
        with ++;
      }
  in
    foldrLastElem(
      \lab::String acc::AGDcl -> appendAGDcl(oneSyn(lab), acc),
      \lab::String -> oneSyn(lab),
      labs
    )
  end;

fun inhScopeType Type ::= grammarName::String labs::[String] =
  decoratedType(
    nonterminalType(
      "silver:compiler:extension:scopegraphs:Scope",
      [], false, false
    ),
    inhSetType(map(\lab::String ->grammarName ++ ":" ++ lab, labs))
  )
;

fun undecScopeAttrDcl AGDcl ::= attr::String =
  Silver_AGDcl {
    synthesized attribute
      $Name{nScopeAttr(attr, "undec")}::[Scope]
    with ++;
  };