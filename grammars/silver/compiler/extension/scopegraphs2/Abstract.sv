grammar silver:compiler:extension:scopegraphs2;

import silver:util:treemap as rtm;

global sgScopeName::String =
  "silver:compiler:extension:scopegraphs2:SGScope"
;

----------------------
-- `scope attribute <aName> occurs on <qNames>;`

abstract production scopeAttribute
top::AGDcl ::= aName::String qNames::QNames
{
  -- Names of inherited attributes corresponding to labels
  --local labelNames::[String] =
  --  map(\attr::String -> last(explode(":", attr)), 
  --      getInhAttrsOn(sgScopeName, top.env));
  local labelNames::[String] =
    case searchEnvTree("MyScope", top.sgEnv) of
    | [] -> []
    | h::_ -> h.labelSet
    end;

  -- Set of label inherited attributes to decorate scopes with
  nondecorated local labelSetTypeExpr::TypeExpr = inhSetTypeExpr (
    terminal(InhSetLCurly_t, "{"), 
    foldrLastElem(
      \labelName::String rest::FlowSpecInhs -> consFlowSpecInhs(labelFlowSpecInh(labelName), ',', rest),
      \lastLabelName::String -> oneFlowSpecInhs(labelFlowSpecInh(lastLabelName)),
      labelNames
    ),
    '}'
  );

  -- "Hidden" type of scopes
  nondecorated local scopeType::TypeExpr = Silver_TypeExpr {
    Decorated SGScope with $TypeExpr{labelSetTypeExpr}
  };

  ------------------------------------------------------------------------------
  -- Names of monoid attributes

  local monoidAttrNames::[String] = 
    map (\lab::String -> aName ++ "_" ++ lab, labelNames);

  ------------------------------------------------------------------------------
  -- occurs declarations for monoids on all qNames

  -- Monoid occurs dcl for ma on qn
  local oneNtOccurDcl::(AGDcl ::= QName String) = \qn::QName ma::String ->
    Silver_AGDcl {
      attribute $Name{name(ma)} occurs on $QName{qn};
    };

  -- Monoid occurs dcls for all monoid attrs on qn
  local oneNtOccursDcls::(AGDcl ::= QName) = \qn::QName ->
    foldrLastElem(
      \ma::String rest::AGDcl-> appendAGDcl(oneNtOccurDcl(qn, ma), rest),
      \ma::String -> oneNtOccurDcl(qn, ma),
      monoidAttrNames
    );

  nondecorated local allNtOccursDcls::AGDcl = 
    foldrLastElem(
      \qn::QName rest::AGDcl -> appendAGDcl(oneNtOccursDcls(qn), rest),
      \qn::QName -> oneNtOccursDcls(qn),
      map((.qnwtQN), qNames.qnames)
    );

  ------------------------------------------------------------------------------
  -- monnoid attribute declarations

  local monoidAttrDcl::(AGDcl ::= String) = \ma::String ->
    Silver_AGDcl {
      monoid attribute $Name{name(ma)}::[$TypeExpr{scopeType}];
    };

  nondecorated local monoidAttrDcls::AGDcl = foldrLastElem (
    \ma::String rest::AGDcl -> appendAGDcl(monoidAttrDcl(ma), rest),
    \ma::String -> monoidAttrDcl(ma),
    monoidAttrNames
  );

  ------------------------------------------------------------------------------
  -- aspect production for all qNames, default label monoids to []

  local monoidDefaultEq::(ProductionStmt ::= String) = \ma::String ->
     Silver_ProductionStmt{top.$QName{qName(ma)} := [];};

  nondecorated local monoidDefaultEqs::ProductionStmt = foldrLastElem (
    \ma::String rest::ProductionStmt -> productionStmtAppend(monoidDefaultEq(ma), rest),
    \ma::String -> monoidDefaultEq(ma),
    monoidAttrNames
  );

  local aspectOneNt::(AGDcl ::= QName) = \qn::QName ->
    Silver_AGDcl {
      aspect default production top::$TypeExpr{nominalTypeExpr(qn.qNameType)} ::= {
        $ProductionStmt{monoidDefaultEqs}
      }
    };

  nondecorated local aspectsAll::AGDcl = foldrLastElem(
    \qn::QName rest::AGDcl -> appendAGDcl(aspectOneNt(qn), rest),
    \qn::QName -> aspectOneNt(qn),
    map((.qnwtQN), qNames.qnames)
  );

  ------------------------------------------------------------------------------
  -- occurs for inh attr

  local inhOccDcl::(AGDcl ::= QName) = \qn::QName ->
    Silver_AGDcl{
      attribute $Name{name(aName)} occurs on $QName{qn};
    };

  nondecorated local inhOccDcls::AGDcl = foldrLastElem(
    \qn::QName rest::AGDcl -> appendAGDcl(inhOccDcl(qn), rest),
    \qn::QName -> inhOccDcl(qn),
    map((.qnwtQN), qNames.qnames)
  );

  ------------------------------------------------------------------------------

  local attrDcls::AGDcl = appendAGDcl(
    Silver_AGDcl{inherited attribute $Name{name(aName)}::$TypeExpr{scopeType};},
    appendAGDcl(
      inhOccDcls,
      appendAGDcl(
        monoidAttrDcls,
        appendAGDcl(
          allNtOccursDcls,
          aspectsAll
        )
      )
    )
  );

  top.defs := attrDcls.defs;
  top.scopeGraphDefs := [];

  forwards to @attrDcls;
}

-----------------------
-- attribute occurences



------------------------------------------------
-- `scope MyScope labels { lex, var, imp, mod }`

abstract production labelsSpecAbs
top::AGDcl ::= alias::String labelNames::[String]
{
  -- Set of label inherited attributes to decorate scopes with
  nondecorated local labelSetTypeExpr::TypeExpr = inhSetTypeExpr (
    terminal(InhSetLCurly_t, "{"), 
    foldrLastElem(
      \labelName::String rest::FlowSpecInhs -> consFlowSpecInhs(labelFlowSpecInh(labelName), ',', rest),
      \lastLabelName::String -> oneFlowSpecInhs(labelFlowSpecInh(lastLabelName)),
      labelNames
    ),
    '}'
  );

  nondecorated local scopeTypeDec::Type = decoratedType(
    nonterminalType("silver:compiler:extension:scopegraphs2:SGScope", [], false, false),
    inhSetType(labelNames)
  );

  -- "Hidden" type of scopes
  nondecorated local scopeTypeExpr::TypeExpr = Silver_TypeExpr {
    Decorated SGScope with $TypeExpr{labelSetTypeExpr}
  };

  -- Attribute and occurs declarations for each label name
  nondecorated local labelAGDcls::AGDcl =
    foldrLastElem(
      \labelName::String rest::AGDcl -> appendAGDcl(mkLabelAGDcl(scopeTypeExpr, labelName), rest),
      \lastLabelName::String -> mkLabelAGDcl(scopeTypeExpr, lastLabelName),
      labelNames
    );

  

  -- Label productions for all label names
  nondecorated local labelProds::AGDcl = 
    foldrLastElem(
      \labelName::String rest::AGDcl -> appendAGDcl(labelProd(labelSetTypeExpr, labelName), rest),
      \lastLabelName::String -> labelProd(labelSetTypeExpr, lastLabelName),
      labelNames
    );

  forwards to
    appendAGDcl(
      Silver_AGDcl {type $Name{name(alias)} = $TypeExpr{scopeTypeExpr};},
      appendAGDcl(labelAGDcls, labelProds)
    )
  ;

  top.scopeGraphDefs := [
    scopeLabelsDef(
      defaultEnvItem(
        labelSetDcl(alias, labelNames)
      )
    )
  ];

}

----------------
-- mkscope

-- `mkscope s1`
abstract production scopeAssertionNoDatum
top::ProductionStmt ::= a::Name
{ forwards to scopeAssertionBoth(^a, nothing()); }

-- `mkscope s1 -> "name" : data`
abstract production scopeAssertionDatum
top::ProductionStmt ::= a::Name name::Expr e::Expr
{ forwards to scopeAssertionBoth(^a, just((^name, ^e))); }

-- Combined
abstract production scopeAssertionBoth
top::ProductionStmt ::= a::Name data::Maybe<(Expr, Expr)>
{
  -- Names of inherited attributes corresponding to labels
  --local labelNames::[String] =
  --  map(\attr::String -> last(explode(":", attr)), 
  --      getInhAttrsOn(sgScopeName, top.env));
  local labelNames::[String] =
    case searchEnvTree("MyScope", top.sgEnv) of
    | [] -> []
    | h::_ -> h.labelSet
    end;

  -- Production attribute for the scope asserted
  local scopeProdAttr::ProductionStmt = 
    if data.isJust
    then
      Silver_ProductionStmt {
        production attribute $Name{^a}::SGScope = 
          scopeDatum(
            $Expr{data.fromJust.1},
            $Expr{data.fromJust.2}
          );
      }
    else
      Silver_ProductionStmt {
        production attribute $Name{^a}::SGScope = scope();
      };

  -- Generating `scope.inh := [];` for every inherited label attribute `inh`
  nondecorated local baseAttrEqs::ProductionStmt = 
    foldrLastElem(
      \inh::String rest::ProductionStmt -> productionStmtAppend(inhAttrInit(a.name, inh), rest),
      \inh::String -> inhAttrInit(a.name, inh),
      labelNames
    )
  ;

  -- Need both of these to avoid cycle
  top.productionAttributes := scopeProdAttr.productionAttributes;
  top.defs := [];

  forwards to
    productionStmtAppend(@scopeProdAttr, baseAttrEqs)
  ;
}

---------------------
-- `s1 -[ lex ]-> s2`

abstract production edgeAssertionLocal
top::ProductionStmt ::= src::QName lab::String tgt::Expr
{
  propagate config, grammarName, compiledGrammars, env, flowEnv;

  -- Names of inherited attributes corresponding to labels
  --local labelNames::[String] =
  --  map(\attr::String -> last(explode(":", attr)), 
  --      getInhAttrsOn(sgScopeName, top.env));
  local labelNames::[String] =
    case searchEnvTree("MyScope", top.sgEnv) of
    | [] -> []
    | h::_ -> h.labelSet
    end;

  -- Expected decorated type of scopes
  local scopeType::Type = decoratedType(
    nonterminalType(sgScopeName, [], false, false), inhSetType(labelNames)
  );

  -- Type of left hand side of edge assertion
  local srcDecTy::Decorated Type with {boundVariables} =
    decorate src.lookupValue.dcl.refDispatcher(src).typerep
    with { boundVariables = []; };

  -- Type of right hand side of edge assertion
  local tgtDecTy::Decorated Type with {boundVariables} =
    decorate tgt.typerep
    with { boundVariables = []; };

  {-top.errors :=
    -- Check `lab` is in `labelNames`
    if !contains(lab, labelNames)
    then [errFromOrigin(top, "Label " ++ lab ++ " not in edge label set {" ++ implode(", ", labelNames) ++ "}")]
    -- Check `src` is of type `Decorated SGScope with <set>`
    else if !isDecScope(labelNames, ^srcDecTy)
    then [errFromOrigin(top, "Left side of Scope edge assertion has type " ++ 
                             srcDecTy.typepp ++ " but must have type " ++ 
                             scopeType.typepp)]
    -- Check `tgt` is of type `Decorated SGScope with <set>`
    else if !isDecScope(labelNames, ^tgtDecTy)
    then [errFromOrigin(top, "Right side of Scope edge assertion has type " ++ 
                             tgtDecTy.typepp ++ " but must have type " ++ 
                             scopeType.typepp)]
    else tgt.errors;-}

  -- LHS for inh attr
  local lhs::DefLHS = concreteDefLHS(^src);
  lhs.env = top.env;
  lhs.frame = top.frame;
  lhs.grammarName = top.grammarName;

  -- Edge inh attr being defined
  local attrName::QNameAttrOccur = qNameAttrOccur(qName(lab));
  attrName.attrFor = ^srcDecTy;--^scopeType;
  attrName.env = top.env;
  attrName.grammarName = top.grammarName;

  forwards to
    inhAppendColAttributeDef(lhs, attrName, Silver_Expr{[$Expr{@tgt}]})
  ;
}

-----------------
-- util functions

fun labelFlowSpecInh FlowSpecInh ::= labelName::String =
  flowSpecInh(qNameAttrOccur(qName(labelName)));

fun mkLabelAGDcl AGDcl ::= scopeType::TypeExpr inhName::String =
  appendAGDcl(
    Silver_AGDcl{inherited attribute $Name{name(inhName)}::[$TypeExpr{scopeType}] with ++;},
    Silver_AGDcl{attribute $QName{qName(inhName)} occurs on SGScope;}
  );

fun inhAttrInit ProductionStmt ::= scopeName::String inhName::String =
  attrContainsBase(
    concreteDefLHS(qName(scopeName)), '.', qNameAttrOccur(qName(inhName)),
    ':=', Silver_Expr{[]}, ';'
  );

fun isDecScope Boolean ::= labs::[String] ty::Type =
  case ty of
  | decoratedType(nt, dt) ->
      !unify(^nt, nonterminalType(sgScopeName, [], false, false)).failure &&
      !unify(^dt, inhSetType(labs)).failure
  | _ -> false
  end
;

fun labelProd AGDcl ::= inhsTypeExpr::TypeExpr lab::String =
  Silver_AGDcl{
    production $Name{name("label_" ++ lab)}
    top::Label<$TypeExpr{inhsTypeExpr}> ::= 
    {
      top.name = $Expr{stringConst(terminal(String_t, "\"" ++ lab ++ "\""))};
      top.demand = \s::Decorated SGScope with $TypeExpr{inhsTypeExpr} -> 
        s.$QName{qName(lab)};
      forwards to label();
    }
  }
;

-----------
-- inh attr

abstract production scopeForChild
top::ProductionStmt ::= n::Name d::DefLHS a::QNameAttrOccur
{
  -- Names of inherited attributes corresponding to labels
  local labelNames::[String] =
    case searchEnvTree("MyScope", top.sgEnv) of
    | [] -> []
    | h::_ -> h.labelSet
    end;

  -- monoid contribs
  nondecorated local monoidContribs::ProductionStmt =
    foldrLastElem(
      \lab::String rest::ProductionStmt -> 
        productionStmtAppend(
          Silver_ProductionStmt{ 
            $QName{qName(n.name)}.$QName{qName(lab)} <- $Expr{access(baseExpr(qName(d.name)), '.', qNameAttrOccur(qName(a.name ++ "_" ++ lab)))};
          },
          rest
        ),
      \lab::String -> 
        Silver_ProductionStmt{ 
          $QName{qName(n.name)}.$QName{qName(lab)} <- $Expr{access(baseExpr(qName(d.name)), '.', qNameAttrOccur(qName(a.name ++ "_" ++ lab)))}; 
        },
      labelNames
    )
  ;

  forwards to productionStmtAppend(
    attributeDef(^d, '.', ^a, '=', baseExpr(qName(n.name)), ';'),
    monoidContribs
  );
}