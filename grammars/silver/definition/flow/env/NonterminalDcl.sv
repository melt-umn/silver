grammar silver:definition:flow:env;

import silver:definition:type:syntax only BracketedOptTypeExprs;
import silver:driver:util only isStrictlyExportedBy;

aspect production nonterminalDcl
top::AGDcl ::= cl::ClosedOrNot 'nonterminal' id::Name tl::BracketedOptTypeExprs nm::NonterminalModifiers ';'
{
  -- Normally the flow analysis consider options to be the same as exports.
  -- Here, to avoid creating a hard dependency on options, we ignore options when
  -- deciding the include things in the *inferred* ref set. (Thus, isStrictlyExportedBy.)
  local inferredInhs :: [String] =
    flatMap(
      filterOccursForReferences(_, top.env, isStrictlyExportedBy(_, [top.grammarName], top.compiledGrammars)),
      getAttrsOn(fName, top.env));
  
  local specInhs :: Maybe<[String]> =
    lookupBy(stringEq, "decorate", getFlowTypeSpecFor(fName, top.flowEnv));

  -- Notice the circularity: flowDefs uses flowEnv. Works fine because only
  -- the (lazy) parameter of ntRefFlowDef isn't computable until later.

  top.flowDefs <- [ntRefFlowDef(fName, fromMaybe(inferredInhs, specInhs))];
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
