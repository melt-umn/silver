grammar silver:compiler:analysis:typechecking:core;

attribute upSubst, downSubst, finalSubst occurs on AspectProductionSignature, AspectProductionLHS, AspectRHS, AspectRHSElem, AspectFunctionSignature, AspectFunctionLHS;
propagate finalSubst on AspectProductionSignature, AspectProductionLHS, AspectRHS, AspectRHSElem, AspectFunctionSignature, AspectFunctionLHS;

aspect production aspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody 
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = ns.finalSubst;

  errCheck1 = check(realSig.typeScheme.typerep, namedSig.typeScheme.typerep);
  top.errors <-
    if errCheck1.typeerror
    then [errFromOrigin(top, "Aspect for '" ++ id.name ++ "' does not have the right signature.\nExpected: "
                            ++ errCheck1.leftpp ++ "\nActual: " ++ errCheck1.rightpp)]
    else
    -- dcl is potentially not found, accessing it can crash.
    -- so check on dcls for this.
      case id.lookupValue.dcls of
      | prodDcl (_, _, _) :: _ -> []
      | funDcl  (_) :: _ -> [errFromOrigin(top, "Production aspect for '" ++ id.name ++ "' should be a 'function' aspect instead.")]
      | _ -> [errFromOrigin(id, id.name ++ " is not a production.")]
      end;

  ns.downSubst = emptySubst();
  thread downSubst, upSubst on ns, errCheck1, body;
  
  ns.finalSubst = errCheck1.upSubst;
}


aspect production aspectFunctionDcl
top::AGDcl ::= 'aspect' 'function' id::QName ns::AspectFunctionSignature body::ProductionBody 
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = ns.finalSubst;

  errCheck1 = check(realSig.typeScheme.typerep, namedSig.typeScheme.typerep);
  top.errors <-
    if errCheck1.typeerror
    then [errFromOrigin(top, "Aspect for '" ++ id.name ++ "' does not have the right signature.\nExpected: "
                            ++ errCheck1.leftpp ++ "\nActual: " ++ errCheck1.rightpp)]
    else
    -- must be on dcls because lookup may have failed.
      case id.lookupValue.dcls of
      | funDcl (_) :: _ -> []
      | prodDcl  (_, _, _) :: _ -> [errFromOrigin(top, "Function aspect for '" ++ id.name ++ "' should be a 'production' aspect instead.")]
      | _ -> [errFromOrigin(id, id.name ++ " is not a function.")]
      end;

  ns.downSubst = emptySubst();
  thread downSubst, upSubst on ns, errCheck1, body;
  
  ns.finalSubst = errCheck1.upSubst;
}

--

aspect production aspectProductionSignature
top::AspectProductionSignature ::= lhs::AspectProductionLHS '::=' rhs::AspectRHS
{
  propagate downSubst, upSubst;
}

aspect production aspectProductionLHSFull
top::AspectProductionLHS ::= id::Name t::Type
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  thread downSubst, upSubst on top, errCheck1, top;
  
  errCheck1 = check(rType, t);
  top.errors <-
        if errCheck1.typeerror
        then [errFromOrigin(top, "Type incorrect in aspect signature. Expected: " ++ errCheck1.leftpp ++ "  Got: " ++ errCheck1.rightpp)]
        else [];
}

aspect production aspectRHSElemNil
top::AspectRHS ::= 
{
  propagate downSubst, upSubst;
}

aspect production aspectRHSElemCons
top::AspectRHS ::= h::AspectRHSElem t::AspectRHS
{
  propagate downSubst, upSubst;
}

aspect production aspectRHSElemFull
top::AspectRHSElem ::= id::Name t::Type
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  thread downSubst, upSubst on top, errCheck1, top;
  
  errCheck1 = check(rType, t);
  top.errors <-
        if errCheck1.typeerror
        then [errFromOrigin(top, "Type incorrect in aspect signature. Expected: " ++ errCheck1.leftpp ++ "  Got: " ++ errCheck1.rightpp)]
        else [];
}

aspect production aspectFunctionSignature
top::AspectFunctionSignature ::= lhs::AspectFunctionLHS '::=' rhs::AspectRHS 
{
  propagate downSubst, upSubst;
}

aspect production functionLHSType
top::AspectFunctionLHS ::= t::TypeExpr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  thread downSubst, upSubst on top, errCheck1, top;
  
  errCheck1 = check(rType, t.typerep);
  top.errors <-
        if errCheck1.typeerror
        then [errFromOrigin(top, "Type incorrect in aspect signature. Expected: " ++ errCheck1.leftpp ++ "  Got: " ++ errCheck1.rightpp)]
        else [];
}

