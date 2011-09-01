grammar silver:modification:autocopyattr:convenience;

import silver:modification:autocopyattr;
import silver:extension:convenience;
import silver:definition:core;
import silver:definition:concrete_syntax;
import silver:definition:type:syntax;
import silver:definition:type;

concrete production attributeDclAutoMultiple
top::AGDcl ::= 'autocopy' 'attribute' a::Name botl::BracketedOptTypeList '::' te::Type 'occurs' 'on' qs::QNames ';'
{
  top.pp = "autocopy attribute " ++ a.name ++ botl.pp ++ " :: " ++ te.pp ++ " occurs on " ++ qs.pp ++ ";" ;
  forwards to agDclAppend(attributeDclAuto($1, $2, a, botl, $5, te, $10),
                          makeOccursDclsHelp($1.line, $1.column, qNameWithTL(qNameId(a), botl), qs.qnames));
}

