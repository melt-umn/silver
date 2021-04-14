grammar silver:compiler:extension:autoattr;

concrete production propagateOnNTListExcludingDcl_c
top::AGDcl ::= 'propagate' attrs::NameList 'on' nts::NameList 'excluding' ps::ProdNameList ';'
{
  top.unparse = s"propagate ${attrs.unparse} on ${nts.unparse} excluding ${ps.unparse};";
  
  top.errors <- ps.errors;
  forwards to propagateOnNTListDcl(attrs, nts, ps, location=top.location);
}

concrete production propagateOnNTListDcl_c
top::AGDcl ::= 'propagate' attrs::NameList 'on' nts::NameList ';'
{
  top.unparse = s"propagate ${attrs.unparse} on ${nts.unparse};";
  
  forwards to
    propagateOnNTListDcl(attrs, nts, prodNameListNil(location=top.location), location=top.location);
}

abstract production propagateOnNTListDcl
top::AGDcl ::= attrs::NameList nts::NameList ps::ProdNameList
{
  top.unparse = s"propagate ${attrs.unparse} on ${nts.unparse} excluding ${ps.unparse};";
  
  forwards to
    case nts of
    | nameListOne(n) -> propagateOnOneNTDcl(attrs, n, ps, location=n.location)
    | nameListCons(n, _, rest) ->
      appendAGDcl(
        propagateOnOneNTDcl(attrs, n, ps, location=n.location),
        propagateOnNTListDcl(attrs, rest, ps, location=top.location),
        location=top.location)
    end;
}

-- Note that this only propagates on all *known* non-forwarding productions.
-- Usually all non-forwarding productions are exported by the NT, but that isn't
-- always the case (see options and closed nonterminals.)
-- For such productions the attribute must still be explicitly propagated.
abstract production propagateOnOneNTDcl
top::AGDcl ::= attrs::NameList nt::QName ps::ProdNameList
{
  top.unparse = s"propagate ${attrs.unparse} on ${nt.unparse} excluding ${ps.unparse};";
  
  -- Ugh, workaround for circular dependency
  top.defs := [];
  top.occursDefs := [];
  top.moduleNames := [];
  top.specDefs := [];
  
  local includedProds::[DclInfo] =
    filter(
      \ d::DclInfo -> !d.hasForward && !contains(d.fullName, ps.names),
      getKnownProds(nt.lookupType.fullName, top.env));
  local dcl::AGDcl =
    foldr(
      appendAGDcl(_, _, location=top.location), emptyAGDcl(location=top.location),
      map(propagateAspectDcl(_, attrs, location=nt.location), includedProds));
  
  forwards to
    if !null(nt.lookupType.errors)
    then errorAGDcl(nt.lookupType.errors, location=top.location)
    else dcl;
}

abstract production propagateAspectDcl
top::AGDcl ::= d::DclInfo attrs::NameList
{
  top.errors :=
    if null(forward.errors)
    then []
    else [nested(top.location, s"In propagate of ${attrs.unparse} for production ${d.fullName}:", forward.errors)];
  
  forwards to
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
                freshenType(ie.typerep, ie.typerep.freeVariables),
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
      location=top.location);
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
        propagateOneAttr(n, location=n.location),
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


-- Need a seperate nonterminal since this can be empty and needs env to check errors
nonterminal ProdNameList with config, grammarName, env, location, unparse, names, errors;
propagate errors on ProdNameList;

abstract production prodNameListNil
top::ProdNameList ::=
{
  top.unparse = "";
  top.names = [];
}

concrete production prodNameListOne
top::ProdNameList ::= n::QName
{
  top.unparse = n.unparse;
  top.names = [n.lookupValue.fullName];
  top.errors <- n.lookupValue.errors;
  top.errors <-
    if n.lookupValue.found
    then
      case n.lookupValue.dcl of
      | prodDcl(_, _) -> []
      | _ -> [err(n.location, n.name ++ " is not a production")]
      end
    else [];
}

concrete production prodNameListCons
top::ProdNameList ::= h::QName ',' t::ProdNameList
{
  top.unparse = h.unparse ++ ", " ++ t.unparse;
  top.names = [h.lookupValue.fullName] ++ t.names;
  top.errors <- h.lookupValue.errors;
  top.errors <-
    if h.lookupValue.found
    then
      case h.lookupValue.dcl of
      | prodDcl(_, _) -> []
      | _ -> [err(h.location, h.name ++ " is not a production")]
      end
    else [];
}
