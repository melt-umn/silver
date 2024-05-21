grammar silver:compiler:analysis:typechecking:core;

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name d::ProductionImplements ns::ProductionSignature body::ProductionBody
{
  body.downSubst = emptySubst();

  top.errors <-
    case d.implementsSig of
    | just(sig) when length(sig.inputElements) > length(ns.namedSignature.inputElements) ->
      [errFromOrigin(top, "Production signature missing items from implemented dispatch signature.")]
    | _ -> []
    end;
}

aspect production productionLHS
top::ProductionLHS ::= id::Name '::' t::TypeExpr
{
  top.errors <- t.errorsKindStar;
  
  local checkNT::TypeCheck = checkNonterminal(top.env, false, t.typerep);
  checkNT.downSubst = emptySubst();
  checkNT.finalSubst = emptySubst();
  
  local checkImplementedSig::TypeCheck = check(t.typerep, top.implementedSig.fromJust.elementDclType);
  checkImplementedSig.downSubst = emptySubst();
  checkImplementedSig.finalSubst = emptySubst();

  top.errors <-
    if checkNT.typeerror
    then [errFromOrigin(top, "Production LHS type must be a nonterminal.  Instead it is of type " ++ checkNT.leftpp)]
    else [];
  
  top.errors <-
    if top.implementedSig.isJust && checkImplementedSig.typeerror
    then [errFromOrigin(top, "Production LHS type does not match the type from the implemented dispatch signature, " ++ checkImplementedSig.rightpp ++ ".  Instead it is of type " ++ checkImplementedSig.leftpp)]
    else [];
}

aspect production productionRHSElem
top::ProductionRHSElem ::= ms::MaybeShared id::Name '::' t::TypeExpr
{
  top.errors <- t.errorsKindStar;
  
  local checkImplementedSig::TypeCheck = check(t.typerep, top.implementedSig.fromJust.elementDclType);
  checkImplementedSig.downSubst = emptySubst();
  checkImplementedSig.finalSubst = emptySubst();
  
  top.errors <-
    case top.implementedSig of
    | just(e) when e.elementShared != ms.isShared ->
      [errFromOrigin(top, s"Child ${id.name} does not match the sharedness from the implemented dispatch signature.")]
    | _ -> []
    end;
  top.errors <-
    if top.implementedSig.isJust && checkImplementedSig.typeerror
    then [errFromOrigin(top, "Child " ++ id.name ++ " does not match the type from the implemented dispatch signature " ++ checkImplementedSig.rightpp ++ ".  Instead it is of type " ++ checkImplementedSig.leftpp)]
    else [];
}
