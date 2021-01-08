grammar silver:compiler:extension:patternmatching;


--Gathering productions which do not forward
monoid attribute nonforwardingProductions::[DclInfo] with [], ++;
attribute nonforwardingProductions occurs on AGDcl, AGDcls;
propagate nonforwardingProductions on AGDcl, AGDcls excluding productionDcl;

--Pairs of fully-qualified nonterminal name and (fully-qualified production names and arities)
autocopy attribute requiredProductionPatterns::[Pair<String [Pair<String Integer>]>];
attribute requiredProductionPatterns occurs on AGDcl, AGDcls, ProductionBody, ProductionStmts, ProductionStmt, Expr, Exprs, AppExprs, AnnoAppExprs;


aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  {-
    The "unique significant expression" for a production is the
    forward.  If it is null, this is a non-forwarding production.
  -}
  local doesForward::Boolean = ! null(body.uniqueSignificantExpression);

  top.nonforwardingProductions :=
      if doesForward
      then []
      else [prodDef(top.grammarName, id.location, namedSig, false).dcl];
}


aspect production root
top::Root ::= gdcl::GrammarDcl ms::ModuleStmts ims::ImportStmts ags::AGDcls
{
  ags.requiredProductionPatterns = groupNonforwardingProductions(ags.nonforwardingProductions);
}


function groupNonforwardingProductions
[Pair<String [Pair<String Integer>]>] ::= prods::[DclInfo]
{
  local getTypeName::(String ::= DclInfo) =
        \ d::DclInfo ->
          case d.typeScheme.typerep of
          | functionType(nonterminalType(name, _, _), _, _) -> name
          | _ -> error("Should never get here")
          end;
  local getTypeArgNumber::(Integer ::= DclInfo) =
        \ d::DclInfo ->
          case d.typeScheme.typerep of
          | functionType(_, args, _) -> length(args)
          | _ -> error("Should never get here")
          end;
  local eqFun::(Boolean ::= DclInfo DclInfo) =
        \ d1::DclInfo d2::DclInfo -> getTypeName(d1) == getTypeName(d2);
  local groups::[[DclInfo]] = groupBy(eqFun, prods);
  return map(\ dlst::[DclInfo] -> pair(getTypeName(head(dlst)), map(\ d::DclInfo -> pair(d.fullName, getTypeArgNumber(d)), dlst)), groups);
}

