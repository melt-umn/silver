grammar silver:extension:autoattr;

concrete production propagateAttrList
top::ProductionStmt ::= 'propagate' ns::NameList ';'
{
  top.unparse = s"propagate ${ns.unparse};";
  
  -- Forwards to productionStmtAppend of propagating the first element in ns
  -- and propagateAttrDcl containing the remaining names
  forwards to
    case ns of
    | nameListOne(n) -> propagateOneAttr(n, location=top.location)
    | nameListCons(n, _, rest) ->
      productionStmtAppend(
        propagateOneAttr(n, location=top.location),
        propagateAttrList($1, rest, $3, location=top.location),
        location=top.location)
    end;
}

abstract production propagateOneAttr
top::ProductionStmt ::= attr::QName
{
  -- Ugh, workaround for circular dependency
  top.defs = [];
  top.productionAttributes = [];
  forwards to
    if !attr.lookupAttribute.found
    then errorProductionStmt(attr.lookupAttribute.errors, location=top.location)
    else attr.lookupAttribute.dcl.propagateDispatcher(attr, top.location);
}

abstract production propagateError
top::ProductionStmt ::= attr::Decorated QName
{
  forwards to
    errorProductionStmt(
      [err(attr.location, s"Attribute ${attr.name} cannot be propagated")],
      location=top.location);
}
