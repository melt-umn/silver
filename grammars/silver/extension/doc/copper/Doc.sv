grammar silver:extension:doc:copper;

import silver:extension:doc;

import silver:definition:core;
import silver:translation:java:concrete_syntax:copper;


aspect production lexerClassDclFull
top::AGDcl ::= id::Name subs::TermPrecList doms::TermPrecList
{
  top.documentation = [];
}

aspect production disambiguationGroupDcl
top::AGDcl ::= 'disambiguate' terms::TermPrecList acode::ActionCode_c
{
  top.documentation = [];
}

