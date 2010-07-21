grammar silver:translation:java:core;
import silver:translation:java:env;
import silver:definition:core;
import silver:definition:env;

aspect production globalValueDclConcrete
top::AGDcl ::= 'global' id::Name '=' e::Expr ';'
{
  top.setupInh := "";
  top.initProd := "";
  -- TODO: something other than just name?
  top.initValues := "\tpublic static final " ++ e.typerep.transType ++ " " ++ id.name ++ " = " ++ e.translation ++ ";\n";
  top.postInit := "";

  top.javaClasses = [];
}
