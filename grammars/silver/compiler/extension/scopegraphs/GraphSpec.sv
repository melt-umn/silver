grammar silver:compiler:extension:scopegraphs;

--

production graphSpec
top::AGDcl ::= sgId::Maybe<String> qns::LabelNames labsId::String
{
  -- alias of the set of inherited attributes corresponding to graph label set
  nondecorated local alias::AGDcl = Silver_AGDcl {
    type $Name{name(labsId)} =
      $TypeExpr{inhSetTypeExpr(terminal(InhSetLCurly_t, "{"), qns.fsInhs, '}')};
  };

  forwards to appendAGDcl(
    alias,
    labelsAGDcls(labsId, qns.names)
  );

  local sgName::String = fromMaybe("_Scope_Default", sgId);

  -- 'graph definition' consisting of an identifier and constituent labels
  top.scopeGraphDefs := [scopeGraphDef(defaultEnvItem(graphDcl(sgName, qns.names)))];
}
