grammar silver:compiler:extension:scopegraphs;

--

-- compute names of scope graph labels by env lookup
fun getLabelNames [String] ::= env::EnvTree<ScopeLabelDclInfo> =
  case searchEnvTree("MyScope", env) of
  | [] -> []
  | h::_ -> h.labelSet
  end
;

fun childContributions ProductionStmt ::= contribf::(ProductionStmt ::= String) child::String labels::[String] =
  foldrLastElem(
    \lab::String rest::ProductionStmt -> productionStmtAppend(contribf(lab), rest),
    \lab::String -> contribf(lab),
    labels
  )
;

--

fun labelFlowSpecInh FlowSpecInh ::= labelName::String =
  flowSpecInh(qNameAttrOccur(qName(labelName)));

fun mkLabelAGDcl AGDcl ::= scopeType::TypeExpr inhName::String =
  appendAGDcl(
    Silver_AGDcl{inherited attribute $Name{name(inhName)}::[$TypeExpr{scopeType}];},
    Silver_AGDcl{attribute $QName{qName(inhName)} occurs on SGScope;}
  );

fun inhAttrInit ProductionStmt ::= scopeName::String inhName::String =
  Silver_ProductionStmt{
    $QName{qName(scopeName)}.$QName{qName(inhName)} = $QName{qName(scopeName ++ "_" ++ inhName)};
  };

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

--

fun inhAttrDefinition ProductionStmt ::= s::String label::String =
  Silver_ProductionStmt{
    $QName{qName(s)}.$QName{qName(label)} = 
      $QName{qnScopeAttr(s, label)}
    ;
  }
;

fun localAttrDclDef ProductionStmt ::= s::String label::String =
  productionStmtAppend(
    Silver_ProductionStmt {
      production attribute $Name{name(s ++ "_" ++ label)}::[MyScope] with ++;
    },
    Silver_ProductionStmt {
      $QName{qnScopeAttr(s, label)} := [];
    }
  );

--

fun qnScopeAttr QName ::= s::String l::String = qName(s ++ "_" ++ l); 