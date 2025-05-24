grammar silver:compiler:extension:nanopass;


attribute includeTrans occurs on ProductionBody;
aspect includeTrans on top::ProductionBody of
| productionBody(_, stmts, _) -> \ tr::Decorated TransformStmts ->
  productionBody('{', foldl(productionStmtsSnoc, productionStmtsNil(), stmts.includeTransStmts(tr)), '}')
end;

monoid attribute includeTransStmts :: ([ProductionStmt] ::= Decorated TransformStmts)
  occurs on ProductionStmts, ProductionStmt;
propagate includeTransStmts on ProductionStmts;

aspect production productionStmtAppend
top::ProductionStmt ::= h::ProductionStmt t::ProductionStmt
{
  propagate includeTransStmts;
}
aspect default production
top::ProductionStmt ::=
{
  top.includeTransStmts := \ tr::Decorated TransformStmts ->
    if top.isIncluded(tr) then [top.includeTrans(tr)] else [];
}

attribute isIncluded occurs on ProductionStmt, DefLHS;
aspect isIncluded on top::ProductionStmt of
| attachNoteStmt(_, _, _) -> tt
| returnDef(_, _, _) -> tt
| localAttributeDcl(_, _, _, _, te, _) -> te.isIncluded
| productionAttributeDcl(_, _, _, _, te, _) -> te.isIncluded
| nondecLocalAttributeDcl(_, _, _, _, _, te, _) -> te.isIncluded
| nondecProductionAttributeDcl(_, _, _, _, _, te, _) -> te.isIncluded
| forwardProductionAttributeDcl(_, _, _, _, _) -> tt
| forwardsTo(_, _, _, _) -> tt
| forwardingWith(_, _, _, _, _, _) -> tt
| synthesizedAttributeDef(dl, attr, _) ->
  dl.isIncluded && isAttributeIncluded(_, attr.attrDcl.fullName)
| inheritedAttributeDef(dl, attr, _) ->
  dl.isIncluded && isAttributeIncluded(_, attr.attrDcl.fullName)
| localValueDef(q, _) -> q.lookupValue.typeScheme.monoType.isIncluded
-- collections
| collectionAttributeDclProd(_, _, _, _, te, _, _, _) -> te.isIncluded
| baseCollectionValueDef(q, _) -> q.lookupValue.typeScheme.monoType.isIncluded
| appendCollectionValueDef(q, _) -> q.lookupValue.typeScheme.monoType.isIncluded
| synBaseColAttributeDef(dl, attr, _) ->
  dl.isIncluded && isAttributeIncluded(_, attr.attrDcl.fullName)
| synAppendColAttributeDef(dl, attr, _) ->
  dl.isIncluded && isAttributeIncluded(_, attr.attrDcl.fullName)
| inhBaseColAttributeDef(dl, attr, _) ->
  dl.isIncluded && isAttributeIncluded(_, attr.attrDcl.fullName)
| inhAppendColAttributeDef(dl, attr, _) ->
  dl.isIncluded && isAttributeIncluded(_, attr.attrDcl.fullName)
| _ -> ff
end;
aspect isIncluded on top::DefLHS of
| childDefLHS(_) -> tt
| lhsDefLHS(_) -> tt
| localDefLHS(_) -> top.typerep.isIncluded
| forwardDefLHS(_) -> tt
| childTransAttrDefLHS(_, attr) -> isAttributeIncluded(_, attr.attrDcl.fullName)
| localTransAttrDefLHS(q, attr) ->
  q.lookupValue.typeScheme.monoType.isIncluded && isAttributeIncluded(_, attr.attrDcl.fullName)
| _ -> ff
end;

attribute includeTrans occurs on ProductionStmt, ForwardInhs, ForwardInh, ForwardLHSExpr, DefLHS, QNameAttrOccur;
propagate includeTrans on ProductionStmt, ForwardInhs, ForwardInh, ForwardLHSExpr, DefLHS, QNameAttrOccur
  excluding synthesizedAttributeDef, inheritedAttributeDef, errorValueDef, localValueDef;
aspect includeTrans on top::ProductionStmt of
| synthesizedAttributeDef(dl, attr, e) -> \ tr::Decorated TransformStmts ->
  attributeDef(dl.includeTrans(tr), '.', attr.includeTrans(tr), '=', e.includeTrans(tr), ';')
| inheritedAttributeDef(dl, attr, e) -> \ tr::Decorated TransformStmts ->
  attributeDef(dl.includeTrans(tr), '.', attr.includeTrans(tr), '=', e.includeTrans(tr), ';')
| errorValueDef(val, e) -> \ tr::Decorated TransformStmts -> error("should not be demanded")
| localValueDef(val, e) -> \ tr::Decorated TransformStmts ->
  valueEq(^val, '=', e.includeTrans(tr), ';')
-- TODO: collections
end;
