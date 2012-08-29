grammar silver:definition:flow:env;

import silver:util only contains;
import silver:definition:type:syntax;
import silver:driver only computeDependencies;

aspect production attributionDcl
top::AGDcl ::= 'attribute' at::QName attl::BracketedOptTypeList 'occurs' 'on' nt::QName nttl::BracketedOptTypeList ';'
{
  local isHost :: Boolean = contains(top.grammarName, computeDependencies([nt.lookupType.dcl.sourceGrammar], top.compiledGrammars));
  local isSyn :: Boolean = case at.lookupAttribute.dcls of synDcl(_,_,_,_,_) :: _ -> true | _ -> false end;

  -- Rule: non-host synthesized attributes' flow type must be a super set of that for the forward.
  top.flowDefs = if isHost || !isSyn then [] else [nonHostSynDef(at.lookupAttribute.fullName, nt.lookupType.fullName)];
}

