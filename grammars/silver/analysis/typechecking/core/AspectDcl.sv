grammar silver:analysis:typechecking:core;

attribute upSubst, downSubst, finalSubst occurs on AspectProductionSignature, AspectProductionLHS, AspectRHS, AspectRHSElem, AspectFunctionSignature, AspectFunctionLHS;

aspect production aspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody 
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = ns.finalSubst;

  errCheck1 = check(realSig.typeScheme.typerep, namedSig.typeScheme.typerep);
  top.errors <-
    if errCheck1.typeerror
    then [err(top.location, "Aspect for '" ++ id.name ++ "' does not have the right signature.\nExpected: "
                            ++ errCheck1.leftpp ++ "\nActual: " ++ errCheck1.rightpp)]
    else
    -- dcl is potentially not found, accessing it can crash.
    -- so check on dcls for this.
      case id.lookupValue.dcls of
      | prodDcl (_, _) :: _ -> []
      | funDcl  (_) :: _ -> [err(top.location, "Production aspect for '" ++ id.name ++ "' should be a 'function' aspect instead.")]
      | _ -> [err(id.location, id.name ++ " is not a production.")]
      end;

  ns.downSubst = emptySubst();
  errCheck1.downSubst = ns.upSubst;
  body.downSubst = errCheck1.upSubst;
  
  ns.finalSubst = errCheck1.upSubst;
}


aspect production aspectFunctionDcl
top::AGDcl ::= 'aspect' 'function' id::QName ns::AspectFunctionSignature body::ProductionBody 
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = ns.finalSubst;

  errCheck1 = check(realSig.typeScheme.typerep, namedSig.typeScheme.typerep);
  top.errors <-
    if errCheck1.typeerror
    then [err(top.location, "Aspect for '" ++ id.name ++ "' does not have the right signature.\nExpected: "
                            ++ errCheck1.leftpp ++ "\nActual: " ++ errCheck1.rightpp)]
    else
    -- must be on dcls because lookup may have failed.
      case id.lookupValue.dcls of
      | funDcl (_) :: _ -> []
      | prodDcl  (_, _) :: _ -> [err(top.location, "Function aspect for '" ++ id.name ++ "' should be a 'production' aspect instead.")]
      | _ -> [err(id.location, id.name ++ " is not a function.")]
      end;

  ns.downSubst = emptySubst();
  errCheck1.downSubst = ns.upSubst;
  body.downSubst = errCheck1.upSubst;
  
  ns.finalSubst = errCheck1.upSubst;
}

--

aspect production aspectProductionSignature
top::AspectProductionSignature ::= lhs::AspectProductionLHS '::=' rhs::AspectRHS
{
  lhs.downSubst = top.downSubst;
  rhs.downSubst = lhs.upSubst;
  top.upSubst = rhs.upSubst;
}

aspect production aspectProductionLHSFull
top::AspectProductionLHS ::= id::Name t::Type
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  errCheck1.downSubst = top.downSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(rType, t);
  top.errors <-
        if errCheck1.typeerror
        then [err(top.location, "Type incorrect in aspect signature. Expected: " ++ errCheck1.leftpp ++ "  Got: " ++ errCheck1.rightpp)]
        else [];
}

aspect production aspectRHSElemNil
top::AspectRHS ::= 
{
  top.upSubst = top.downSubst;
}

aspect production aspectRHSElemCons
top::AspectRHS ::= h::AspectRHSElem t::AspectRHS
{
  h.downSubst = top.downSubst;
  t.downSubst = h.upSubst;
  top.upSubst = t.upSubst;
}

aspect production aspectRHSElemFull
top::AspectRHSElem ::= id::Name t::Type
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  errCheck1.downSubst = top.downSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(rType, t);
  top.errors <-
        if errCheck1.typeerror
        then [err(top.location, "Type incorrect in aspect signature. Expected: " ++ errCheck1.leftpp ++ "  Got: " ++ errCheck1.rightpp)]
        else [];
}

aspect production aspectFunctionSignature
top::AspectFunctionSignature ::= lhs::AspectFunctionLHS '::=' rhs::AspectRHS 
{
  lhs.downSubst = top.downSubst;
  rhs.downSubst = lhs.upSubst;
  top.upSubst = rhs.upSubst;
}

aspect production functionLHSType
top::AspectFunctionLHS ::= t::TypeExpr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  errCheck1.downSubst = top.downSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(rType, t.typerep);
  top.errors <-
        if errCheck1.typeerror
        then [err(top.location, "Type incorrect in aspect signature. Expected: " ++ errCheck1.leftpp ++ "  Got: " ++ errCheck1.rightpp)]
        else [];
}

