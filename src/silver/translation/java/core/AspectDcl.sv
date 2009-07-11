grammar silver:translation:java:core;
import silver:definition:core;


aspect production aspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody{

  top.javaClasses = [];
  top.setupInh = body.setupInh;  
  top.initProd = "";
  top.initAspect = "		//ASPECT PRODUCTION " ++ id.name ++ " " ++ ns.pp ++ "\n" ++ body.translation;
}

aspect production aspectFunctionDcl
top::AGDcl ::= 'aspect' 'function' id::QName ns::AspectFunctionSignature body::ProductionBody{

  top.javaClasses = [];
  top.setupInh = body.setupInh;  
  top.initProd = "";
  top.initAspect = "		//ASPECT FUNCTION " ++ id.name ++ " " ++ ns.pp ++ "\n" ++ body.translation;
}
