grammar silver:translation:java:concrete_syntax:copper;

import silver:definition:concrete_syntax;
import silver:definition:core;
import silver:definition:env;
import silver:util;

aspect production unparseRootSpec
top::RootSpecUnparse ::= r::Decorated RootSpec{
  unparses <- mapDGs(r.disambiguationGroupDcls);
  unparses <- mapPAs(r.parserAttrDcls);
}

function mapDGs
[String] ::= lst::[Decorated DisambiguationGroupSpec]
{
  return if null(lst) then [] else ["disambiguate [[" ++ folds(", ",quoteStrings(head(lst).groupMembers)) ++ "], \"" ++ escapeString(head(lst).actionCode) ++ "\"]"] ++ mapDGs(tail(lst));
}

function mapPAs
[String] ::= lst::[Decorated ParserAttrSpec]
{
  return if null(lst) then [] else ["parse_attr [" ++ quoteString(head(lst).name) ++ ", " ++ head(lst).typerep.unparse ++ ", \"" ++ escapeString(head(lst).actionCode) ++ "\"]"] ++ mapPAs(tail(lst));
}

function escapeString
String ::= s::String
{
  return substitute("\\\"", "\"", s);
}

function unescapeString
String ::= s::String
{
  return substitute("\"", "\\\"", s);
}
