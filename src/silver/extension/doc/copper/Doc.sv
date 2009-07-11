grammar silver:extension:doc:copper;
export silver:extension:doc:copper;

import silver:extension:doc;

import silver:definition:core;
import silver:translation:java:concrete_syntax:copper;


aspect production lexerClassDcl
top::AGDcl ::= 'lexer' 'class' id::Name ';'
{
  top.documentation = [];
}