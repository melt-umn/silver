grammar silver:modification:autocopyattr:convenience;

import silver:modification:autocopyattr;
import silver:extension:convenience;
import silver:definition:core;
import silver:definition:concrete_syntax;
import silver:definition:type:syntax;
import silver:definition:type;

concrete production attributeDclAutoMultipleNoTL
top::AGDcl ::= 'autocopy' 'attribute' a::Name '::' te::Type 'occurs' 'on' qs::QNames ';'
{
  top.pp = "autocopy attribute " ++ a.name ++ " :: " ++ te.pp ++ " occurs on " ++ qs.pp ++ ";" ;
  forwards to agDclAppend(attributeDclAutoEmpty($1, $2, a, $4, te, $9),
                          makeOccursDclsHelp($1.line, $1.column, qNameWithoutTL(qNameId(a)), qs.qnames));
}
concrete production attributeDclAutoMultipleWithTL
top::AGDcl ::= 'autocopy' 'attribute' a::Name '<' tl::TypeList '>' '::' te::Type 'occurs' 'on' qs::QNames ';'
{
  top.pp = "autocopy attribute " ++ a.name ++ " :: " ++ te.pp ++ " occurs on " ++ qs.pp ++ ";" ;
  forwards to agDclAppend(attributeDclAuto($1, $2, a, $4, tl, $6, $7, te, $12),
                          makeOccursDclsHelp($1.line, $1.column, qNameWithTL(qNameId(a), $4, tl, $6), qs.qnames));
}

