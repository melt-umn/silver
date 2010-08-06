grammar silver:analysis:typechecking:core;
import silver:definition:core;
import silver:definition:env;

aspect production aspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody 
{
  local attribute sigOkay :: Boolean;
  sigOkay = (genListEquals(getTypesSignature(realSig.inputElements), getTypesSignature(ns.inputElements)))
	 && (realSig.outputElement.typerep.typeEquals(realSig.outputElement.typerep, ns.outputElement.typerep).bValue);

  top.typeErrors <-
        if !sigOkay
        then [err(top.location, "Aspect for '" ++ id.name ++ "' does not have the right signature.")]
        else [];	

  top.typeErrors := body.typeErrors;
}


aspect production aspectFunctionDcl
top::AGDcl ::= 'aspect' 'function' id::QName ns::AspectFunctionSignature body::ProductionBody 
{
  local attribute sigOkay :: Boolean;
  sigOkay = (genListEquals(getTypesSignature(realSig.inputElements), getTypesSignature(ns.inputElements)))
	 && (realSig.outputElement.typerep.typeEquals(realSig.outputElement.typerep, ns.outputElement.typerep).bValue);

  top.typeErrors <-
        if !sigOkay
        then [err(top.location, "Aspect for '" ++ id.name ++ "' does not have the right signature.")]
        else [];	

  top.typeErrors := body.typeErrors;
}
