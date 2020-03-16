grammar silver:extension:autoattr;

concrete production propagateOnNTListDcl
top::AGDcl ::= 'propagate' attrs::NameList 'on' nts::NameList ';'
{
  top.unparse = s"propagate ${attrs.unparse} on ${nts.unparse};";
  
  forwards to
    case nts of
    | nameListOne(n) -> propagateOnOneNTDcl(attrs, n, location=n.location)
    | nameListCons(n, _, rest) ->
      appendAGDcl(
        propagateOnOneNTDcl(attrs, n, location=n.location),
        propagateOnNTListDcl($1, attrs, $3, rest, $5, location=top.location),
        location=top.location)
    end;
}

-- Note that this only propagates on all *known* non-forwarding productions.
-- Usually all non-forwarding productions are exported by the NT, but that isn't
-- always the case (see options and closed nonterminals.)
-- For such productions the attribute must still be explicitly propagated.
abstract production propagateOnOneNTDcl
top::AGDcl ::= attrs::NameList nt::QName
{
  top.unparse = s"propagate ${attrs.unparse} on ${nt.unparse};";
  
  -- Ugh, workaround for circular dependency
  top.defs := [];
  top.occursDefs := [];
  top.moduleNames := [];
  
  local nonForwardingProds::[DclInfo] =
    filter(\ d::DclInfo -> !d.hasForward, getKnownProds(nt.lookupType.fullName, top.env));
  local dcl::AGDcl =
    foldr(
      appendAGDcl(_, _, location=top.location), emptyAGDcl(location=top.location),
      map(
        \ d::DclInfo ->
          aspectProductionDcl(
            'aspect', 'production', qName(top.location, d.fullName),
            aspectProductionSignature(
              aspectProductionLHSFull(
                name(d.namedSignature.outputElement.elementName, top.location),
                d.namedSignature.outputElement.typerep,
                location=top.location),
              '::=',
              foldr(
                aspectRHSElemCons(_, _, location=top.location),
                aspectRHSElemNil(location=top.location),
                map(
                  \ ie::NamedSignatureElement ->
                    aspectRHSElemFull(
                      name(ie.elementName, top.location),
                      ie.typerep,
                      location=top.location),
                  d.namedSignature.inputElements)),
              location=top.location),
            productionBody(
              '{',
              productionStmtsSnoc(
                productionStmtsNil(location=top.location),
                propagateAttrList('propagate', attrs, ';', location=top.location),
                location=top.location),
              '}',
              location=top.location),
            location=top.location),
        nonForwardingProds));
   
  forwards to
    if !null(nt.lookupType.errors)
    then errorAGDcl(nt.lookupType.errors, location=top.location)
    else dcl;
}

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
  top.unparse = s"propagate ${attr.unparse};";
  
  -- Ugh, workaround for circular dependency
  top.defs := [];
  top.productionAttributes := [];
  forwards to
    if !null(attr.lookupAttribute.errors)
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
