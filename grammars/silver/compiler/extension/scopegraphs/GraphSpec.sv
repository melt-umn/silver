grammar silver:compiler:extension:scopegraphs;

--------------------------
-- Scope graph labels spec

production graphSpec
top::AGDcl ::=
  --sgId::Maybe<String> -- no named SGs
  qns::LabelNames
  labsId::String
{
  -- 'graph definition' consisting of an identifier and constituent labels
  top.scopeGraphDefs := [
    scopeGraphDef(defaultEnvItem(
      graphDcl(top.grammarName, "_Scope_Default", qns.names)))
  ];

  forwards to appendAGDcl(
    Silver_AGDcl {
      type $Name{name(labsId)} = $TypeExpr{inhSetTypeExpr(terminal(InhSetLCurly_t, "{"), qns.fsInhs, '}')};
    },
    labelsAGDcls(labsId, qns.names)
  );
}

--------------
-- Label names

synthesized attribute names::[String] occurs on LabelNames;
synthesized attribute fsInhs::FlowSpecInhs occurs on LabelNames;

production labelNamesOne
top::LabelNames ::= lab::String
{ top.names = [lab];
  top.fsInhs = oneFlowSpecInhs(flowSpecInh(qNameAttrOccur(qName(lab)))); }

production labelNamesCons
top::LabelNames ::= lab::String ns::LabelNames
{ top.names = lab::ns.names;
  top.fsInhs = consFlowSpecInhs(
                flowSpecInh(qNameAttrOccur(qName(lab))), ',',
                ns.fsInhs); }
