grammar silver:definition:flow:env;

import silver:util only contains;
import silver:definition:type:syntax;
import silver:driver:util only isExportedBy;

aspect production attributionDcl
top::AGDcl ::= 'attribute' at::QName attl::BracketedOptTypeExprs 'occurs' 'on' nt::QName nttl::BracketedOptTypeExprs ';'
{
  local isHost :: Boolean = isExportedBy(top.grammarName, [nt.lookupType.dcl.sourceGrammar], top.compiledGrammars);
  local isSyn :: Boolean = at.lookupAttribute.dcl.isSynthesized;

  -- Rule: non-host synthesized attributes' flow type must be a super set of that for the forward.
  top.flowDefs = 
    if !at.lookupAttribute.found || !nt.lookupType.found || isHost || !isSyn then 
      []
    else
      [extSynFlowDef(nt.lookupType.fullName, at.lookupAttribute.fullName)];
}

