grammar silver:compiler:extension:scopegraphs2;

---------------------------------------------
-- `scope graph labels { lex, var, imp, mod }`

abstract production labelsSpecAbs
top::AGDcl ::= labelNames::[String]
{
  local labelFlowSpecInh::(FlowSpecInh ::= String) = \labelName::String ->
    flowSpecInh(qNameAttrOccur(qName(labelName)));

  nondecorated local labelSetTypeExpr::TypeExpr = inhSetTypeExpr (
    terminal(InhSetLCurly_t, "{"), 
    foldrLastElem(
      \labelName::String rest::FlowSpecInhs -> consFlowSpecInhs(labelFlowSpecInh(labelName), ',', rest),
      \lastLabelName::String -> oneFlowSpecInhs(labelFlowSpecInh(lastLabelName)),
      labelNames
    ),
    '}'
  );

  nondecorated local scopeTypeExpr::TypeExpr = Silver_TypeExpr {
    Decorated SGScope with $TypeExpr{labelSetTypeExpr}
  };

  nondecorated local inhTypeExpr::TypeExpr = Silver_TypeExpr {
    [$TypeExpr{scopeTypeExpr}]
  };

  local mkLabelAGDcl::(AGDcl ::= String) = \labelName::String ->
    appendAGDcl(
      Silver_AGDcl{inherited attribute $Name{name(labelName)}::$TypeExpr{inhTypeExpr};},
      Silver_AGDcl{attribute $QName{qName(labelName)} occurs on SGScope;}
    );

  forwards to
    appendAGDcl(
      Silver_AGDcl {
        type $Name{name("Scope")} = $TypeExpr{scopeTypeExpr};
      },
      foldrLastElem(
        \labelName::String rest::AGDcl -> appendAGDcl(mkLabelAGDcl(labelName), rest),
        \lastLabelName::String -> mkLabelAGDcl(lastLabelName),
        labelNames
      )
    );
}

---------------
-- `mkscope s1`

abstract production scopeAssertionNoDatum
top::ProductionStmt ::= a::Name
{
  propagate config, grammarName, compiledGrammars, env, flowEnv;

  -- inh types:

  -- seems to be a cycle whenever the forward depends on this??
  -- but this isn't true because the production attr for this scope depends on it...
  -- cycle occurs when trying to make the local collection attributes for edges..
  local labelNames::[String] =
    map(\attr::String -> last(explode(":", attr)), 
        getInhAttrsOn("silver:compiler:extension:scopegraphs2:SGScope", top.env));

  local labelFlowSpecInh::(FlowSpecInh ::= String) = \labelName::String ->
    flowSpecInh(qNameAttrOccur(qName(labelName)));

  nondecorated local labelSetTypeExpr::TypeExpr = inhSetTypeExpr (
    terminal(InhSetLCurly_t, "{"), 
    foldrLastElem(
      \labelName::String rest::FlowSpecInhs -> consFlowSpecInhs(labelFlowSpecInh(labelName), ',', rest),
      \lastLabelName::String -> oneFlowSpecInhs(labelFlowSpecInh(lastLabelName)),
      labelNames
    ),
    '}'
  );

  -- local collection attributes:

  local prodAttrName::(String ::= String) = \labelName::String -> 
    "local_" ++ a.name ++ "_" ++ labelName
  ;

  nondecorated local prodAttrLabelDcl::(ProductionStmt ::= String) = \labelName::String ->
    Silver_ProductionStmt{
      production attribute $Name{name(prodAttrName(labelName))}::[Scope] with ++;
    }
  ;

  nondecorated local prodAttrLabelInit::(ProductionStmt ::= String) = \labelName::String ->
    Silver_ProductionStmt{
      $QName{qName(prodAttrName(labelName))} := [];
    }
  ;

  nondecorated local prodAttrLabelBoth::(ProductionStmt ::= String) = \labelName::String ->
    productionStmtAppend(
      prodAttrLabelDcl(labelName),
      prodAttrLabelInit(labelName)
    )
  ;

  nondecorated local prodAttrsAll::ProductionStmt = foldrLastElem(
    \labelName::String rest::ProductionStmt -> productionStmtAppend(prodAttrLabelBoth(labelName), rest),
    \lastLabelName::String -> prodAttrLabelBoth(lastLabelName),
    labelNames
  );

  -- inh exprs:

  nondecorated local exprInhLabel::(ExprInh ::= String) = \labelName::String ->
    exprInh(exprLhsExpr(qNameAttrOccur(qName(labelName))), '=', baseExpr(qName(prodAttrName(labelName))), ';')
  ;

  nondecorated local labelExprInhs::ExprInhs = foldrLastElem(
    \labelName::String rest::ExprInhs -> exprInhsCons(exprInhLabel(labelName), rest),
    \lastLabelName::String -> exprInhsOne(exprInhLabel(lastLabelName)),
    labelNames
  );

  -- forward:

  forwards to productionStmtAppend( 
    Silver_ProductionStmt {
      production attribute $Name{^a}::Scope = 
       decorate scope() with {$ExprInhs{labelExprInhs}};
    },
    prodAttrsAll
  );

  {-
  -- no cycle:
  forwards to Silver_ProductionStmt {
    production attribute $Name{^a}::Decorated SGScope with $TypeExpr{labelSetTypeExpr} = 
      decorate scope() with {$ExprInhs{labelExprInhs}};
  };
  -}

  -- added these in an attempt to break the cycle
  top.defs := [];
  top.forwardExpr := [];
  -- defining `top.productionAttributes` using `labelNames` to make prod attrs for the local edge lists also results in cycle
  -- but defining `top.productionAttributes` as empty breaks the cycle - although gives errors in programs
  -- top.productionAttributes := [];

}