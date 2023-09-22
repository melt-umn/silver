grammar silver:compiler:extension:strategyattr:convenience;

import silver:compiler:extension:strategyattr;
import silver:compiler:extension:convenience;
import silver:compiler:definition:core;
import silver:compiler:definition:concrete_syntax;
import silver:compiler:definition:type:syntax;
import silver:compiler:definition:type;
import silver:compiler:definition:env;

concrete production partialStrategyAttributeDclMultiple
top::AGDcl ::= 'partial' 'strategy' 'attribute' a::Name '=' e::StrategyExpr_c 'occurs' 'on' qs::QNames ';'
{
  top.unparse = "partial strategy attribute " ++ a.name ++ " occurs on " ++ qs.unparse ++ ";";
  forwards to
    appendAGDcl(
      partialStrategyAttributeDcl($1, $2, $3, a, $5, e, $10),
      makeOccursDclsHelp($1.location, qNameWithTL(qNameId(a), botlNone()), qs.qnames));
}

concrete production totalStrategyAttributeDclMultiple
top::AGDcl ::= 'strategy' 'attribute' a::Name '=' e::StrategyExpr_c 'occurs' 'on' qs::QNames ';'
{
  top.unparse = "strategy attribute " ++ a.name ++ " occurs on " ++ qs.unparse ++ ";";
  forwards to
    appendAGDcl(
      totalStrategyAttributeDcl($1, $2, a, $4, e, $9),
      makeOccursDclsHelp($1.location, qNameWithTL(qNameId(a), botlNone()), qs.qnames));
}
