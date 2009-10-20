grammar silver:translation:java:concrete_syntax:copper;

import silver:definition:concrete_syntax;
import silver:definition:core;
import silver:definition:env;
import silver:util;

aspect production unparseRootSpec
top::RootSpecUnparse ::= r::Decorated RootSpec{
  unparses <- mapDGs(r.disambiguationGroupDcls);
}

function mapDGs
[String] ::= lst::[Decorated DisambiguationGroupSpec]
{
  return if null(lst) then [] else ["disambiguate [[" ++ folds(", ",quoteStrings(head(lst).groupMembers)) ++ "], \"" ++ head(lst).actionCode ++ "\"]"] ++ mapDGs(tail(lst));
}
