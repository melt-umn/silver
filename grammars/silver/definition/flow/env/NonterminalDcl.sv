grammar silver:definition:flow:env;

import silver:definition:type:syntax only BracketedOptTypeList;

aspect production nonterminalDcl
top::AGDcl ::= cl::ClosedOrNot 'nonterminal' id::Name tl::BracketedOptTypeList ';'
{
  -- TODO: although this works, because all we really care about is having a *consistent* set of
  -- attributes that we deem the "blessed reference set" this behavior is slightly bogus.
  -- We should probably only list inhs *exported* by this grammar, rather than inhs imported
  -- by this grammar, as this does now. Otherwise we end up "missing" attributes that
  -- aren't in scope...
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
