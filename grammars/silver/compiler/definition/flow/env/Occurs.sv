grammar silver:compiler:definition:flow:env;

import silver:util only contains;
import silver:compiler:definition:type:syntax;
import silver:compiler:driver:util only isExportedBy;

aspect production attributionDcl
top::AGDcl ::= 'attribute' at::QName attl::BracketedOptTypeExprs 'occurs' 'on' nt::QName nttl::BracketedOptTypeExprs ';'
{
  local isHost :: Boolean = isExportedBy(top.grammarName, [nt.lookupType.dcl.sourceGrammar], top.compiledGrammars);
  local isSyn :: Boolean = at.lookupAttribute.dcl.isSynthesized;

  -- We must be able to identify host-language synthesized attributes from the flow environment
  top.flowDefs <-
    if !at.lookupAttribute.found || !nt.lookupType.found || !isHost || !isSyn then 
      []
    else
      [hostSynFlowDef(nt.lookupType.fullName, at.lookupAttribute.fullName)];
}

