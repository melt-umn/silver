grammar silver:compiler:extension:scopegraphs;

global sgScopeName::String =
  "silver:compiler:extension:scopegraphs:SGScope"
;

----------------------
-- `scope attribute <aName> occurs on <qNames>;`

abstract production scopeAttribute
top::AGDcl ::= a::QName qNames::QNames
{
  propagate env, moduleNames;

  top.unparse = "scope attribute " ++ a.unparse ++ " occurs on " ++ qNames.unparse;

  local aName::String = a.name;

  -- Names of inherited attributes corresponding to labels
  local labelNames::[String] = getLabelNames(top.sgEnv);

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
  local scopeType::TypeExpr = Silver_TypeExpr {
    Decorated SGScope with $TypeExpr{labelSetTypeExpr}
  };
  scopeType.env = top.env;
  scopeType.flowEnv = top.flowEnv;
  scopeType.grammarName = top.grammarName;

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
      monoid attribute $Name{name(ma)}::[$TypeExpr{^scopeType}] with [], ++;
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

  local attrDcls::AGDcl = --appendAGDcl(
    --Silver_AGDcl{inherited attribute $Name{name(aName)}::$TypeExpr{^scopeType};},
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
  --)
  ;

  top.defs := attrDef(
                defaultEnvItem(
                  scopeInhDcl(
                    aName,
                    scopeType.typerep,
                    sourceGrammar=top.grammarName,
                    sourceLocation=a.nameLoc
                  )
                )
              )
              ::attrDcls.defs;
  top.scopeGraphDefs := [];

  forwards to @attrDcls;

}

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
    nonterminalType("silver:compiler:extension:scopegraphs:SGScope", [], false, false),
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
{ top.unparse = "mkscope " ++ a.unparse ++ ";";
  forwards to
    scopeAssertionBoth(^a, 
      Silver_ProductionStmt{production attribute $Name{^a}::SGScope = scopeNoDatum();}
    ); 
}


-- `mkscope s1 -> "name" |-> data`
-- todo: better syntax
abstract production scopeAssertionDatum
top::ProductionStmt ::= a::Name name::Expr e::Expr
{ top.unparse = "mkscope " ++ a.unparse ++ " -> " ++ name.unparse ++ " |-> " ++ e.unparse ++ ";";
  forwards to 
    scopeAssertionBoth(^a, 
      Silver_ProductionStmt{production attribute $Name{^a}::SGScope = scopeDatum($Expr{^name}, $Expr{^e});}
    ); 
}


-- Combined
abstract production scopeAssertionBoth
top::ProductionStmt ::= a::Name scopeProdAttr::ProductionStmt
{
  propagate frame, grammarName;

  top.unparse = error("scopeAssertionBoth.unparse");

  -- Names of inherited attributes corresponding to labels
  local labelNames::[String] = getLabelNames(top.sgEnv);
  
  -- Generating `scope.inh := [];` for every inherited label attribute `inh`
  nondecorated local inhAttrDefs::ProductionStmt = 
    foldrLastElem(
      \inh::String rest::ProductionStmt -> productionStmtAppend(inhAttrInit(a.name, inh), rest),
      \inh::String -> inhAttrInit(a.name, inh),
      labelNames
    )
  ;

  nondecorated local localAttrDclsDefs::ProductionStmt =
    foldrLastElem(
      \inh::String rest::ProductionStmt -> productionStmtAppend(localAttrDclDef(a.name, inh), rest),
      \inh::String -> localAttrDclDef(a.name, inh),
      labelNames
    )
  ;

  forwards to
    productionStmtAppend(
      -- production attr, inh defs
      productionStmtAppend(^scopeProdAttr, inhAttrDefs),
      -- local dcl, base defs
      localAttrDclsDefs
    )
  ;
}
