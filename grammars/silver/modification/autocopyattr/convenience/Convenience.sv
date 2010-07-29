grammar silver:modification:autocopyattr:convenience;

import silver:modification:autocopyattr;
import silver:extension:convenience;
import silver:definition:core;
import silver:definition:concrete_syntax;

concrete production attributeDclAutoMultiple1
top::AGDcl ::= 'autocopy' 'attribute' a::Names2 '::' te::Type 'occurs' 'on' qs::QNames ';'
{
  top.pp = "autocopy attribute " ++ a.pp ++ " :: " ++ te.pp ++ " occurs on " ++ qs.pp ++ ";" ;
  forwards to agDclAppend(makeAutoDcls($1.line, $1.column, te, a.ids), makeOccursDcls($1.line, $1.column, qualifyNames(a.ids), qs.qnames));
}

concrete production attributeDclAutoMultiple2
top::AGDcl ::= 'autocopy' 'attribute' a::Name '::' te::Type 'occurs' 'on' qs::QNames ';'
{
  top.pp = "autocopy attribute " ++ a.name ++ " :: " ++ te.pp ++ " occurs on " ++ qs.pp ++ ";" ;
  forwards to agDclAppend(makeAutoDcls($1.line, $1.column, te, [a]), makeOccursDcls($1.line, $1.column, qualifyNames([a]), qs.qnames));
}

function makeAutoDcls
AGDcl ::= l::Integer c::Integer t::Type atts::[Name]
{
  return if null(atts)
	 then agDclNone()
	 else agDclAppend(autocopyAttributeDcl(terminal(AutoCopy_kwd, "autocopy", l, c),
				       terminal(Attribute_kwd, "attribute", l, c),
			      	       head(atts),
			      	       terminal(HasType_t, "::", l, c),
			               t,
			               terminal(Semi_t, ";", l, c)),
		       makeAutoDcls(l, c, t, tail(atts)));
}
