grammar silver:definition:flow:env;

import silver:definition:type:syntax only BracketedOptTypeList;

aspect production nonterminalDcl
top::AGDcl ::= cl::ClosedOrNot 'nonterminal' id::Name tl::BracketedOptTypeList ';'
{
  local inhs :: [String] =
    map((.attrOccurring),
      filter(isOccursInherited(_, top.env),
        getAttrsOn(fName, top.env)));

  top.flowDefs = [ntRefFlowDef(fName, inhs)];
}

function isOccursInherited
Boolean ::= occs::DclInfo  e::Decorated Env
{
  return case getAttrDcl(occs.attrOccurring, e) of
         | at :: _ -> at.isInherited
         | _ -> false
         end;
}
