grammar silver:translation:java:concrete_syntax;
import silver:translation:java:core;
import silver:definition:concrete_syntax;
import silver:definition:core;

aspect production terminalDclDefault
top::AGDcl ::= t::TerminalKeywordModifier id::Name r::RegExpr tm::TerminalModifiers
{
  top.javaClasses = [];
  top.setupInh := "";
  top.initProd := "";
  top.initValues := "";
  top.postInit := "";
}

