grammar silver:analysis:typechecking:core;
import silver:definition:core;
import silver:definition:env;
import core;

aspect production aspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody 
{
  local attribute sigOkay :: Boolean;
  sigOkay = (genListEquals(getTypesSignature(realSig.inputElements), getTypesSignature(ns.inputElements)))
	 && (realSig.outputElement.typerep.typeEquals(realSig.outputElement.typerep, ns.outputElement.typerep).bValue);

  local attribute er2 :: [Decorated Message];
  er2 = if !sigOkay
        then [err(top.location, "Aspect for '" ++ id.name ++ "' does not have the right signature.")]
        else [];	

  top.typeErrors = er2 ++ body.typeErrors;
}


aspect production aspectFunctionDcl
top::AGDcl ::= 'aspect' 'function' id::QName ns::AspectFunctionSignature body::ProductionBody 
{
  local attribute sigOkay :: Boolean;
  sigOkay = (genListEquals(getTypesSignature(realSig.inputElements), getTypesSignature(ns.inputElements)))
	 && (realSig.outputElement.typerep.typeEquals(realSig.outputElement.typerep, ns.outputElement.typerep).bValue);

  local attribute er2 :: [Decorated Message];
  er2 = if !sigOkay
        then [err(top.location, "Aspect for '" ++ id.name ++ "' does not have the right signature.")]
        else [];	

  top.typeErrors = er2 ++ body.typeErrors;
}
