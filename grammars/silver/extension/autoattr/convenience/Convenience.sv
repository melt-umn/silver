grammar silver:extension:autoattr:convenience;

import silver:extension:autoattr;
import silver:extension:convenience;
import silver:definition:core;
import silver:definition:concrete_syntax;
import silver:definition:type:syntax;
import silver:definition:type;
import silver:definition:env;

concrete production functorAttributeDclMultiple
top::AGDcl ::= 'functor' 'attribute' a::Name 'occurs' 'on' qs::QNames ';'
{
  top.unparse = "functor attribute " ++ a.name ++ " occurs on " ++ qs.unparse ++ ";";
  forwards to
    appendAGDcl(
      functorAttributeDcl($1, $2, a, $7, location=a.location),
      makeOccursDclsHelp($1.location, qNameWithTL(qNameId(a, location=a.location), botlNone(location=top.location)), qs.qnames),
      location=top.location);
}
