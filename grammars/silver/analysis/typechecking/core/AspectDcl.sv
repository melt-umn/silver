grammar silver:analysis:typechecking:core;

aspect production aspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody 
{
  local attribute realType :: TypeExp;
  realType = freshenTypeExp( id.lookupValue.typerep, id.lookupValue.typerep.freeVariables );
  
  local attribute aspectType :: TypeExp;
  aspectType = productionTypeExp(ns.outputElement.typerep, getTypesSignature(ns.inputElements));

  local attribute errCheck1 :: TypeCheck; errCheck1.downSubst = emptySubst();
  errCheck1 = check(realType, aspectType);
  top.errors <-
        if errCheck1.typeerror
        then [err(top.location, "Aspect for '" ++ id.name ++ "' does not have the right signature.\nExpected: "
                                ++ errCheck1.leftpp ++ "\nActual: "
                                ++ errCheck1.rightpp)]
        else [];	

  body.downSubst = emptySubst();
  body.finalSubst = body.upSubst;
}


aspect production aspectFunctionDcl
top::AGDcl ::= 'aspect' 'function' id::QName ns::AspectFunctionSignature body::ProductionBody 
{
  local attribute realType :: TypeExp;
  realType = freshenTypeExp( id.lookupValue.typerep, id.lookupValue.typerep.freeVariables );
  
  local attribute aspectType :: TypeExp;
  aspectType = functionTypeExp(ns.outputElement.typerep, getTypesSignature(ns.inputElements));

  local attribute errCheck1 :: TypeCheck; errCheck1.downSubst = emptySubst();
  errCheck1 = check(realType, aspectType);
  top.errors <-
        if errCheck1.typeerror
        then [err(top.location, "Aspect for '" ++ id.name ++ "' does not have the right signature.\nExpected: "
                                ++ errCheck1.leftpp ++ "\nActual: "
                                ++ errCheck1.rightpp)]
        else [];	

  body.downSubst = emptySubst();
  body.finalSubst = body.upSubst;
}
