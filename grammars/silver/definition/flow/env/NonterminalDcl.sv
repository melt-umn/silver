grammar silver:definition:flow:env;

import silver:definition:type:syntax only BracketedOptTypeList;
import silver:analysis:warnings:defs only isOccursSynthesized;

aspect production nonterminalDcl
top::AGDcl ::= cl::ClosedOrNot 'nonterminal' id::Name botl::BracketedOptTypeList ';'
{
  local attrs :: Pair<[DclInfo] [DclInfo]> = partition(isOccursSynthesized(_, top.env), getAttrsOn(fName, top.env));
  local inhs :: [String] = map((.attrOccurring), attrs.snd);

  top.flowDefs = [ntRefFlowDef(fName, inhs)];
}

