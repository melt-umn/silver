grammar silver:translation:java:core;

aspect production aspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody
{
  top.setupInh := body.setupInh;
  top.initProd := "\t\t//ASPECT PRODUCTION " ++ id.name ++ " " ++ ns.pp ++ "\n" ++ body.translation;
}

aspect production aspectFunctionDcl
top::AGDcl ::= 'aspect' 'function' id::QName ns::AspectFunctionSignature body::ProductionBody
{
  top.setupInh := body.setupInh;  
  top.initProd := "\t\t//ASPECT FUNCTION " ++ id.name ++ " " ++ ns.pp ++ "\n" ++ body.translation;
}
