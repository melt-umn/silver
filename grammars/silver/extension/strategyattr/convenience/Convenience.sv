grammar silver:extension:strategyattr:convenience;

import silver:extension:strategyattr;
import silver:extension:convenience;
import silver:definition:core;
import silver:definition:concrete_syntax;
import silver:definition:type:syntax;
import silver:definition:type;
import silver:definition:env;

concrete production strategyAttributeDclMultiple
top::AGDcl ::= 'strategy' 'attribute' a::Name '=' e::StrategyExpr_c 'occurs' 'on' qs::QNames ';'
{
  top.unparse = "strategy attribute " ++ a.name ++ " occurs on " ++ qs.unparse ++ ";";
  forwards to
    appendAGDcl(
      strategyAttributeDcl_c($1, $2, a, $4, e, $9, location=a.location),
      makeOccursDclsHelp($1.location, qNameWithTL(qNameId(a, location=a.location), botlNone(location=top.location)), qs.qnames),
      location=top.location);
}
