grammar silver:compiler:definition:flow:env;

import silver:compiler:definition:type;
import silver:compiler:definition:type:syntax;
import silver:compiler:driver:util only isExportedBy;

-- Needed for specializing inh deps in syn occurs-on contexts
attribute flowEnv occurs on Contexts, Context;
propagate flowEnv on Contexts, Context;

aspect production defaultAttributionDcl
top::AGDcl ::= at::Decorated! QName  attl::Decorated! BracketedOptTypeExprs with {}  nt::Decorated! QName with {}  nttl::Decorated! BracketedOptTypeExprs with {}
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

