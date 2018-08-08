grammar silver:modification:autocopyattr:convenience;

import silver:modification:autocopyattr;
import silver:extension:convenience;
import silver:definition:core;
import silver:definition:concrete_syntax;
import silver:definition:type:syntax;
import silver:definition:type;
import silver:definition:env;

concrete production attributeDclAutoMultiple
top::AGDcl ::= 'autocopy' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr 'occurs' 'on' qs::QNames ';'
{
  top.unparse = "autocopy attribute " ++ a.name ++ tl.unparse ++ " :: " ++ te.unparse ++ " occurs on " ++ qs.unparse ++ ";" ;
  forwards to appendAGDcl(attributeDclAuto($1, $2, a, tl, $5, te, $10, location=a.location),
                          makeOccursDclsHelp($1.location, qNameWithTL(qNameId(a, location=a.location), tl), qs.qnames), location=top.location);
}

