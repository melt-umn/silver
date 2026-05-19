grammar silver:compiler:extension:scopegraphs;

--

global globScopeTy::Type = nonterminalType(
  "silver:compiler:extension:scopegraphs:Scope", [], false, false
);

fun decScopeTy Type ::= labs::[String] =
  decoratedType(
    globScopeTy,
    inhSetType(labs)
  )
;

fun scopeTypeExpr TypeExpr ::= sg::String =
  Silver_TypeExpr{
    Decorated Scope with $TypeExpr{
      nominalTypeExpr(qNameTypeId(terminal(IdUpper_t, sg)))
    }
  };

fun qnScopeAttr QName ::= s::String l::String = qName(s ++ "_" ++ l);
fun nScopeAttr Name ::= s::String l::String = name(s ++ "_" ++ l);

---------
-- Env.sv

fun lookupGraphDcl [ScopeGraphDclInfo] ::= sgfn::String sgEnv::SGEnv =
  searchEnvTree(sgfn, sgEnv.scopeGraphsTree);

fun lookupGraphDclOpt Maybe<ScopeGraphDclInfo> ::= sgfn::String sgEnv::SGEnv =
  let res::[ScopeGraphDclInfo] = lookupGraphDcl(sgfn, sgEnv) in
  if null(res) then nothing() else just(head(res)) end;

-------------
-- DclInfo.sv

fun appendToQName QName ::= orig::QName extra::String =
  case orig of
  | qNameId(id) -> qNameId(name(id.name ++ extra))
  | qNameCons(id, _, qn) -> qNameCons(^id, ':', appendToQName(^qn, extra))
  | qNameError(_) -> error("appendToQName")
  end;

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
  appendAGDcl(
    Silver_AGDcl {
      production $Name{name("label_" ++ lab)}
      top::Label<$TypeExpr{nominalTypeExpr(qNameTypeId(terminal(IdUpper_t, sg)))}> ::=
      {
        top.name = $Expr{stringConst(terminal(String_t, "\"" ++ lab ++ "\""))};
        top.demand = \s::$TypeExpr{scopeTypeExpr(sg)} -> s.$QName{qName(lab)};
      }
    },
    let te::TypeExpr = Silver_TypeExpr { Decorated Scope with $TypeExpr{nominalTypeExpr(qNameTypeId(terminal(IdUpper_t, sg)))} } in
    let labsTe::TypeExpr = nominalTypeExpr(qNameTypeId(terminal(IdUpper_t, sg))) in
      Silver_AGDcl {
        fun $Name{name("regexLabelFun_" ++ lab)} ([ResPair<$TypeExpr{labsTe}>] ::= ResPair<$TypeExpr{labsTe}>) ::=  =
          \p::ResPair<$TypeExpr{labsTe}> -> 
            map(
              \sf::$TypeExpr{te} -> 
                (sf, $Expr{stringConst(terminal(String_t, "\"" ++ lab ++ "\""))}::p.2),
              p.1.$QName{qName(lab)}
            );
      }
    end end
  );

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