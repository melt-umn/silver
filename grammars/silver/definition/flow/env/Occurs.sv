grammar silver:definition:flow:env;

import silver:util only contains;
import silver:definition:type:syntax;
import silver:driver:util only computeDependencies;

aspect production attributionDcl
top::AGDcl ::= 'attribute' at::QName attl::BracketedOptTypeList 'occurs' 'on' nt::QName nttl::BracketedOptTypeList ';'
{
  local isHost :: Boolean = contains(top.grammarName, computeDependencies([nt.lookupType.dcl.sourceGrammar], top.compiledGrammars));
  local isSyn :: Boolean = at.lookupAttribute.dcl.isSynthesized;

  -- Rule: non-host synthesized attributes' flow type must be a super set of that for the forward.
  top.flowDefs = 
    if !null(at.lookupAttribute.errors ++ nt.lookupType.errors) || isHost || !isSyn then 
      []
    else
      [nonHostSynDef(at.lookupAttribute.fullName, nt.lookupType.fullName)];
}

