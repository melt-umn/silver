{--
 - Propagates a list of functor attributes on the current production.  
 - Actual implementation in propagateOne
 -}
concrete production propagateAttrDcl
top::ProductionStmt ::= 'propagate' ns::NameList ';'
{
  top.pp = s"propagate ${ns.pp};";
  
  -- Forwards to productionStmtAppend of propagating the first element in ns
  -- and propagateAttrDcl containing the remaining names
  forwards to
    case ns of
      nameListOne(n) -> 
        propagateOne(n, location=top.location)
    | nameListCons(n, _, rest) ->
        productionStmtAppend(
          -- Location is n.location so errors are for only the correct attribute
          propagateOne(n, location=n.location),
          propagateAttrDcl($1, rest, $3, location=top.location),
          location=top.location)
    end;
}

abstract production propagateOne
top::ProductionStmt ::= a::QName
{
  top.pp = s"propagate ${a.pp};";
  
  local lookupRes::Decorated QNameLookup = a.lookupAttribute;
  local dcl::DclInfo = lookupRes.dcl;
  
  forwards to propagateFunctor(a, location=top.location); {-
    if !null(lookupRes.errors)
    then errorProductionStmt(lookupRes.errors, location=top.location)
    else dcl.propagateProd(a, top.location);-}
}

abstract production propagateError
top::ProductionStmt ::= a::QName
{
  forwards to errorProductionStmt([err(a.location, a.name ++ " cannot be propagated")], location=top.location);
}

