grammar silver:definition:flow:env;

import silver:definition:type:syntax only BracketedOptTypeList;
import silver:driver:util only isExportedBy;

aspect production nonterminalDcl
top::AGDcl ::= cl::ClosedOrNot 'nonterminal' id::Name tl::BracketedOptTypeList ';'
{
  local inferredInhs :: [String] =
    flatMap(
      filterOccursForReferences(_, top.env, isExportedBy(_, [top.grammarName], top.compiledGrammars)),
      getAttrsOn(fName, top.env));
  
  local specInhs :: Maybe<[String]> =
    lookupBy(stringEq, "decorate", getFlowTypeSpecFor(fName, top.flowEnv));

  -- Notice the circularity: flowDefs uses flowEnv. Works fine because only
  -- the (lazy) parameter of ntRefFlowDef isn't computable until later.

  top.flowDefs = [ntRefFlowDef(fName, fromMaybe(inferredInhs, specInhs))];
}

-- If it is inherited and exported by this grammar (according to authority)
function filterOccursForReferences
[String] ::= occ::DclInfo  e::Decorated Env  authority::(Boolean ::= String)
{
  return case getAttrDcl(occ.attrOccurring, e) of
         | at :: _ ->
             if at.isInherited && authority(occ.sourceGrammar)
             then [occ.attrOccurring]
             else []
         | _ -> []
         end; 
}
