grammar silver:compiler:modification:autocopyattr:convenience;

import silver:compiler:modification:autocopyattr;
import silver:compiler:extension:convenience;
import silver:compiler:definition:core;
import silver:compiler:definition:concrete_syntax;
import silver:compiler:definition:type:syntax;
import silver:compiler:definition:type;
import silver:compiler:definition:env;

concrete production attributeDclAutoMultiple
top::AGDcl ::= 'autocopy' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr 'occurs' 'on' qs::QNames ';'
{
  top.unparse = "autocopy attribute " ++ a.name ++ tl.unparse ++ " :: " ++ te.unparse ++ " occurs on " ++ qs.unparse ++ ";" ;
  forwards to appendAGDcl(attributeDclAuto($1, $2, a, tl, $5, te, $10, location=a.location),
                          makeOccursDclsHelp($1.location, qNameWithTL(qNameId(a, location=a.location), tl, location=top.location), qs.qnames), location=top.location);
}

