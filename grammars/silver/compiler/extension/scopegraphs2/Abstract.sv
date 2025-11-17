grammar silver:compiler:extension:scopegraphs2;

global sgScopeName::String =
  "silver:compiler:extension:scopegraphs2:SGScope"
;

------------------------------------------------
-- `scope MyScope labels { lex, var, imp, mod }`

abstract production labelsSpecAbs
top::AGDcl ::= alias::String labelNames::[String]
{
  -- Set of label inherited attributes to decorate scopes with
  nondecorated local labelSetTypeExpr::TypeExpr = inhSetTypeExpr (
    terminal(InhSetLCurly_t, "{"), 
    foldrLastElem(
      \labelName::String rest::FlowSpecInhs -> consFlowSpecInhs(mkLabelFlowSpecInh(labelName), ',', rest),
      \lastLabelName::String -> oneFlowSpecInhs(mkLabelFlowSpecInh(lastLabelName)),
      labelNames
    ),
    '}'
  );

  -- "Hidden" type of scopes
  nondecorated local scopeType::TypeExpr = Silver_TypeExpr {
    Decorated SGScope with $TypeExpr{labelSetTypeExpr}
  };

  -- Attribute and occurs declarations for each label name
  nondecorated local labelAGDcls::AGDcl =
    foldrLastElem(
      \labelName::String rest::AGDcl -> appendAGDcl(mkLabelAGDcl(scopeType, labelName), rest),
      \lastLabelName::String -> mkLabelAGDcl(scopeType, lastLabelName),
      labelNames
    );

  -- Label productions for all label names
  nondecorated local labelProds::AGDcl = 
    foldrLastElem(
      \labelName::String rest::AGDcl -> appendAGDcl(mkLabelProd(labelSetTypeExpr, labelName), rest),
      \lastLabelName::String -> mkLabelProd(labelSetTypeExpr, lastLabelName),
      labelNames
    )
  ;

  local labelProdNameToApp::(Expr ::= String) = \label::String -> 
    applicationEmpty(
      baseExpr(qName("label_" ++ label)), '(', ')'
    )
  ;

  nondecorated local labelProdNameExprs::Exprs = foldrLastElem (
    \labelName::String rest::Exprs -> exprsCons(labelProdNameToApp(labelName), ',', rest),
    \lastLabelName::String -> exprsSingle(labelProdNameToApp(lastLabelName)),
    labelNames
  );

  -- `global allLabs::[Label<<set>>] = [ label_lex(), label_var(), ...]`
  nondecorated local labelProdGlobalList::AGDcl = Silver_AGDcl {
    global allLabs::[Label<$TypeExpr{labelSetTypeExpr}>] =
      $Expr{fullList('[', labelProdNameExprs, ']')};
  };

  forwards to
    appendAGDcl(
      Silver_AGDcl {type $Name{name(alias)} = $TypeExpr{scopeType};},
      appendAGDcl(
        labelAGDcls,
        appendAGDcl(
          labelProds,
          labelProdGlobalList
        )
      )
    )
  ;
}

----------------
-- `mkscope s1;`

abstract production scopeAssertionNoDatum
top::ProductionStmt ::= a::Name
{ forwards to scopeAssertionBoth(^a, nothing()); }

abstract production scopeAssertionDatum
top::ProductionStmt ::= a::Name name::String_t e::Expr
{ forwards to scopeAssertionBoth(^a, just((name, ^e))); }

abstract production scopeAssertionBoth
top::ProductionStmt ::= a::Name data::Maybe<(String_t, Expr)>
{
  -- Names of inherited attributes corresponding to labels
  local labelNames::[String] =
    map(\attr::String -> last(explode(":", attr)), 
        getInhAttrsOn(sgScopeName, top.env));

  -- Production attribute for the scope asserted
  local scopeProdAttr::ProductionStmt = 
    if data.isJust
    then
      Silver_ProductionStmt {
        production attribute $Name{^a}::SGScope = 
          scopeDatum(
            $Expr{stringConst(data.fromJust.1)},
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
      \inh::String rest::ProductionStmt -> productionStmtAppend(mkInhAttrInit(a.name, inh), rest),
      \inh::String -> mkInhAttrInit(a.name, inh),
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
  local labelNames::[String] =
    map(\attr::String -> last(explode(":", attr)), 
        getInhAttrsOn(sgScopeName, top.env));

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

  top.errors :=
    -- check `lab` is in `labelNames`
    if !contains(lab, labelNames)
    then [errFromOrigin(top, "Label " ++ lab ++ " not in edge label set {" ++ implode(", ", labelNames) ++ "}")]
    -- check `src` is of type `Decorated SGScope with <set>`
    else if !isDecScope(labelNames, ^srcDecTy)
    then [errFromOrigin(top, "Left side of Scope edge assertion has type " ++ 
                             srcDecTy.typepp ++ " but must have type " ++ 
                             scopeType.typepp)]
    -- check `tgt` is of type `Decorated SGScope with <set>`
    else if !isDecScope(labelNames, ^tgtDecTy)
    then [errFromOrigin(top, "Right side of Scope edge assertion has type " ++ 
                             tgtDecTy.typepp ++ " but must have type '" ++ 
                             scopeType.typepp)]
    else tgt.errors;

  -- LHS for inh attr
  local lhs::DefLHS = concreteDefLHS(^src);
  lhs.env = top.env;
  lhs.frame = top.frame;
  lhs.grammarName = top.grammarName;

  -- Edge inh attr being defined
  local attrName::QNameAttrOccur = qNameAttrOccur(qName(lab));
  attrName.attrFor = ^scopeType;
  attrName.env = top.env;
  attrName.grammarName = top.grammarName;

  forwards to
    inhAppendColAttributeDef(lhs, attrName, Silver_Expr{[$Expr{@tgt}]})
  ;
}

-----------------
-- util functions

fun mkLabelFlowSpecInh FlowSpecInh ::= labelName::String =
  flowSpecInh(qNameAttrOccur(qName(labelName)));

fun mkLabelAGDcl AGDcl ::= scopeType::TypeExpr inhName::String =
  appendAGDcl(
    Silver_AGDcl{inherited attribute $Name{name(inhName)}::[$TypeExpr{scopeType}] with ++;},
    Silver_AGDcl{attribute $QName{qName(inhName)} occurs on SGScope;}
  );

fun mkInhAttrInit ProductionStmt ::= scopeName::String inhName::String =
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

fun mkLabelProd AGDcl ::= inhsTypeExpr::TypeExpr lab::String =
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