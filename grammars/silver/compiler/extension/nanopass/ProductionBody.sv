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
| defaultLhsDefLHS(_) -> tt
| childTransAttrDefLHS(_, attr) -> isAttributeIncluded(_, attr.attrDcl.fullName)
| localTransAttrDefLHS(q, attr) ->
  q.lookupValue.typeScheme.monoType.isIncluded && isAttributeIncluded(_, attr.attrDcl.fullName)
| _ -> ff
end;

attribute includeTrans occurs on ProductionStmt, ForwardInhs, ForwardInh, ForwardLHSExpr, DefLHS, QNameAttrOccur;
propagate includeTrans on ProductionStmt, ForwardInhs, ForwardInh, ForwardLHSExpr
  excluding synthesizedAttributeDef, inheritedAttributeDef, errorValueDef, localValueDef,
  parserAttributeValueDef, termAttrValueValueDef,
  synBaseColAttributeDef, synAppendColAttributeDef, inhBaseColAttributeDef, inhAppendColAttributeDef;
aspect includeTrans on top::ProductionStmt of
| synthesizedAttributeDef(dl, attr, e) -> \ tr::Decorated TransformStmts ->
  attributeDef(dl.includeTrans(tr), '.', attr.includeTrans(tr), '=', e.includeTrans(tr), ';')
| inheritedAttributeDef(dl, attr, e) -> \ tr::Decorated TransformStmts ->
  attributeDef(dl.includeTrans(tr), '.', attr.includeTrans(tr), '=', e.includeTrans(tr), ';')
| errorValueDef(val, e) -> error("should not be demanded")
| localValueDef(val, e) -> \ tr::Decorated TransformStmts ->
  valueEq(^val, '=', e.includeTrans(tr), ';')
| parserAttributeValueDef(val, e) -> error("should not be demanded")
| termAttrValueValueDef(val, e) -> error("should not be demanded")
-- collections
| baseCollectionValueDef(q, e) -> \ tr::Decorated TransformStmts ->
  valContainsBase(^q, ':=', e.includeTrans(tr), ';')
| appendCollectionValueDef(q, e) -> \ tr::Decorated TransformStmts ->
  valContainsAppend(qName(unqualifyIfSameGrammar(top.grammarName, q.lookupValue.fullName)), '<-', e.includeTrans(tr), ';')
| synBaseColAttributeDef(dl, attr, e) -> \ tr::Decorated TransformStmts ->
  attrContainsBase(dl.includeTrans(tr), '.', attr.includeTrans(tr), ':=', e.includeTrans(tr), ';')
| synAppendColAttributeDef(dl, attr, e) -> \ tr::Decorated TransformStmts ->
  attrContainsAppend(dl.includeTrans(tr), '.', attr.includeTrans(tr), '<-', e.includeTrans(tr), ';')
| inhBaseColAttributeDef(dl, attr, e) -> \ tr::Decorated TransformStmts ->
  attrContainsBase(dl.includeTrans(tr), '.', attr.includeTrans(tr), ':=', e.includeTrans(tr), ';')
| inhAppendColAttributeDef(dl, attr, e) -> \ tr::Decorated TransformStmts ->
  attrContainsAppend(dl.includeTrans(tr), '.', attr.includeTrans(tr), '<-', e.includeTrans(tr), ';')
end;
aspect production collectionAttributeDclProd
top::ProductionStmt ::= 'production' 'attribute' a::Name '::' te::TypeExpr 'with' q::NameOrBOperator ';'
{ propagate includeTrans; }
aspect includeTrans on top::DefLHS of
| errorDefLHS(_) -> error("should not be demanded")
| childDefLHS(q) -> \ _ -> concreteDefLHS(^q)
| lhsDefLHS(q) -> \ _ -> concreteDefLHS(^q)
| localDefLHS(q) -> \ _ ->
  concreteDefLHS(qName(unqualifyIfSameGrammar(top.grammarName, q.lookupValue.fullName)))
| forwardDefLHS(q) -> \ _ -> concreteDefLHS(^q)
| defaultLhsDefLHS(q) -> \ _ -> concreteDefLHS(^q)
| parserAttributeDefLHS(q) -> error("should not be demanded")
| errorTransAttrDefLHS(q, attr) -> error("should not be demanded")
| childTransAttrDefLHS(q, attr) -> \ tr::Decorated TransformStmts ->
  transAttrDefLHS(^q, attr.includeTrans(tr))
| localTransAttrDefLHS(q, attr) -> \ tr::Decorated TransformStmts ->
  transAttrDefLHS(qName(unqualifyIfSameGrammar(top.grammarName, q.lookupValue.fullName)), attr.includeTrans(tr))
end;
aspect includeTrans on top::QNameAttrOccur of
| qNameAttrOccur(at) -> \ tr::Decorated TransformStmts ->
  if !top.attrFound || isAttributeIncluded(tr, top.attrDcl.fullName)
  then ^top
  else qNameAttrOccur(qName(top.attrDcl.fullName))
end;
