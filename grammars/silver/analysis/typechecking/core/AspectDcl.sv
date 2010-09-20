grammar silver:analysis:typechecking:core;

aspect production aspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody 
{
  local attribute realType :: TypeExp;
  realType = freshenTypeExp( id.lookupValue.typerep, id.lookupValue.typerep.freeVariables );
  
  local attribute aspectType :: TypeExp;
  aspectType = productionTypeExp(ns.outputElement.typerep, getTypesSignature(ns.inputElements));

  local attribute sigSubst :: Substitution;
  sigSubst = unify(realType, aspectType);
  
  local attribute fusedTyVars :: [TyVar];
  fusedTyVars = aspectType.freeVariables ++ realType.freeVariables;
  -- TODO: this is imprecise. Should be AFTER sigSubst get freevariables.

  top.typeErrors <-
        if sigSubst.failure
        then [err(top.location, "Aspect for '" ++ id.name ++ "' does not have the right signature.\nExpected: "
                                ++ prettyTypeWith( performSubstitution(realType, sigSubst), fusedTyVars) ++ "\nActual: "
                                ++ prettyTypeWith( performSubstitution(aspectType, sigSubst), fusedTyVars))]
        else [];	

  top.typeErrors := body.typeErrors;
  
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

  local attribute sigSubst :: Substitution;
  sigSubst = unify(realType, aspectType);
  
  local attribute fusedTyVars :: [TyVar];
  fusedTyVars = aspectType.freeVariables ++ realType.freeVariables;
  -- TODO: this is imprecise. Should be AFTER sigSubst get freevariables.

  top.typeErrors <-
        if sigSubst.failure
        then [err(top.location, "Aspect for '" ++ id.name ++ "' does not have the right signature.\nExpected: "
                                ++ prettyTypeWith( performSubstitution(realType, sigSubst), fusedTyVars) ++ "\nActual: "
                                ++ prettyTypeWith( performSubstitution(aspectType, sigSubst), fusedTyVars))]
        else [];

  top.typeErrors := body.typeErrors;
  
  body.downSubst = emptySubst();
  body.finalSubst = body.upSubst;
}
