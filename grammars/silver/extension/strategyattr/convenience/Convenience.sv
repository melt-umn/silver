grammar silver:extension:strategyattr:convenience;

import silver:extension:strategyattr;
import silver:extension:convenience;
import silver:definition:core;
import silver:definition:concrete_syntax;
import silver:definition:type:syntax;
import silver:definition:type;
import silver:definition:env;

concrete production partialStrategyAttributeDclMultiple
top::AGDcl ::= 'partial' 'strategy' 'attribute' a::Name '=' e::StrategyExpr_c 'occurs' 'on' qs::QNames ';'
{
  top.unparse = "partial strategy attribute " ++ a.name ++ " occurs on " ++ qs.unparse ++ ";";
  forwards to
    appendAGDcl(
      partialStrategyAttributeDcl($1, $2, $3, a, $5, e, $10, location=a.location),
      makeOccursDclsHelp($1.location, qNameWithTL(qNameId(a, location=a.location), botlNone(location=top.location)), qs.qnames),
      location=top.location);
}

concrete production totalStrategyAttributeDclMultiple
top::AGDcl ::= 'strategy' 'attribute' a::Name '=' e::StrategyExpr_c 'occurs' 'on' qs::QNames ';'
{
  top.unparse = "strategy attribute " ++ a.name ++ " occurs on " ++ qs.unparse ++ ";";
  forwards to
    appendAGDcl(
      totalStrategyAttributeDcl($1, $2, a, $4, e, $9, location=a.location),
      makeOccursDclsHelp($1.location, qNameWithTL(qNameId(a, location=a.location), botlNone(location=top.location)), qs.qnames),
      location=top.location);
}
