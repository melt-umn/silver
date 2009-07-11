grammar silver:translation:java:core;
import silver:definition:core;

aspect production defaultNonterminalDcl
top::AGDcl ::= id::Name
{
  top.javaClasses = [];
  top.setupInh = "";
  top.initProd = "";
  top.initAspect = "";
}

aspect production closeNonterminalDcl
top::AGDcl ::= 'close' 'nonterminal' q::QName ';'
{
  top.javaClasses = [];
  top.setupInh = "";
  top.initProd = "";
  top.initAspect = "";
}

