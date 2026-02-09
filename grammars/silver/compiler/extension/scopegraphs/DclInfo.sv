grammar silver:compiler:extension:scopegraphs;

--

abstract production scopeInhDcl
top::AttributeDclInfo ::= fn::String ty::Type
{
  propagate compareKey;
  
  top.decoratedAccessHandler = inhDecoratedAccessHandler; -- is this right?
  top.undecoratedAccessHandler = error("scopeInhDcl.undecoratedAccessHandler todo");
  top.dataAccessHandler = error("scopeInhDcl.dataAccessHandler todo");
  top.attrDefDispatcher = scopeAttributeDef;
  top.attributionDispatcher = defaultAttributionDcl;

  top.fullName = fn;

  top.typeScheme = monoType(^ty);

  top.isInherited = true;
}

--

abstract production scopeAttributeDef implements AttributeDef
top::ProductionStmt ::= @dl::DefLHS @attr::QNameAttrOccur e::Expr
{
  top.unparse = "\t" ++ dl.unparse ++ "." ++ attr.unparse ++ " = " ++ e.unparse ++ ";";

  e.isRoot = true;

  -- Names of inherited attributes corresponding to labels
  local labelNames::[String] = getLabelNames(top.sgEnv);

  -- Given a label, compute edge contribusions from child dl
  local contributionsf::(ProductionStmt ::= String) =
    case e of
    | baseExpr(qn) -> 
        \lab::String ->
          Silver_ProductionStmt{$QName{qnScopeAttr(qn.name, lab)} <-
            $Expr{baseExpr(qName(dl.name))}.$QName{qnScopeAttr(attr.name, lab)};}
    | access(baseExpr(node), _, qn) -> 
        \lab::String ->
          let parAttr::QName = qnScopeAttr(qn.name, lab) in  
            productionStmtAppend(
              Silver_ProductionStmt{$QName{^node}.$QName{parAttr} := [];},
              Silver_ProductionStmt{$QName{^node}.$QName{parAttr} <- 
                                      $Expr{baseExpr(qName(dl.name))} .
                                      $QName{qnScopeAttr(attr.name, lab)};}
            )
          end
    | _ -> error("scopeAttributeDef.contributionsf")
    end;

  -- Contributions to edge local attrs
  nondecorated local contributions::ProductionStmt = 
    childContributions(contributionsf, dl.name, labelNames);

  forwards to productionStmtAppend(
    inheritedAttributeDef(dl, attr, ^e),
    contributions
  );

}
