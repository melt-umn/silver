grammar silver:compiler:extension:scopegraphs2;

--

synthesized attribute names::[String] occurs on LabelNames;
synthesized attribute fsInhs::FlowSpecInhs occurs on LabelNames;

production labelNamesOne
top::LabelNames ::= lab::String
{
  top.names = [lab];
  top.fsInhs = oneFlowSpecInhs(flowSpecInh(qNameAttrOccur(qName(lab))));
}

production labelNamesCons
top::LabelNames ::= lab::String ns::LabelNames
{
  top.names = lab::ns.names;
  top.fsInhs = consFlowSpecInhs(
    flowSpecInh(qNameAttrOccur(qName(lab))), ',', ns.fsInhs
  );
}