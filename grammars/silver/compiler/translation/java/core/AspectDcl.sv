grammar silver:compiler:translation:java:core;

aspect production aspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody
{
  top.setupInh := body.setupInh;
  top.initProd := s"\t\t//ASPECT PRODUCTION ${id.name} ${ns.unparse}\n${body.translation}";
  top.valueWeaving := body.valueWeaving;
}

aspect production aspectFunctionDcl
top::AGDcl ::= 'aspect' 'function' id::QName ns::AspectFunctionSignature body::ProductionBody
{
  top.setupInh := body.setupInh;  
  top.initProd := s"\t\t//ASPECT FUNCTION ${id.name} ${ns.unparse}\n${body.translation}";
  top.valueWeaving := body.valueWeaving;
}
