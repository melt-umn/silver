grammar silver:compiler:definition:flow:env;

import silver:compiler:definition:flow:syntax;

aspect production inhSetConstBaseDecl
top::AGDcl ::= 'inhset' id::Name ':=' _ inhs::FlowSpecInhs '}' ';'
{
  top.flowDefs <- [inhSetBaseFlowDef(fName, inhs.inhList)];
}

aspect production inhSetConstContribDecl
top::AGDcl ::= 'inhset' q::QNameType '<-' _ inhs::FlowSpecInhs '}' ';'
{
  top.flowDefs <- [inhSetContribFlowDef(q.lookupType.fullName, inhs.inhList)];
}
