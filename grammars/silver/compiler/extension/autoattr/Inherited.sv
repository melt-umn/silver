grammar silver:compiler:extension:autoattr;

abstract production propagateInh
top::ProductionStmt ::= attr::Decorated! QName
{
  undecorates to propagateOneAttr(attr, location=top.location);
  top.unparse = s"propagate ${attr.unparse};";
  
  local attrFullName::String = attr.lookupAttribute.dcl.fullName;
  local inputsWithAttr::[NamedSignatureElement] =
    filter(
      \ input::NamedSignatureElement ->
        isDecorable(input.typerep, top.env) &&
        -- Only propagate for unique decorated children that don't have the attribute
        case getMaxRefSet(input.typerep, top.env) of
        | just(inhs) -> !contains(attrFullName, inhs)
        | nothing() -> false
        end &&
        !null(getOccursDcl(attrFullName, input.typerep.typeName, top.env)),
      top.frame.signature.inputElements);
  forwards to
    foldr(
      productionStmtAppend(_, _, location=top.location),
      errorProductionStmt([], location=top.location), -- No emptyProductionStmt?
      map(
        \ ie::NamedSignatureElement ->
          attributeDef(
            concreteDefLHS(qName(top.location, ie.elementName), location=top.location), '.',
            qNameAttrOccur(new(attr), location=top.location),
            '=',
            access(
              baseExpr(qName(top.location, top.frame.signature.outputElement.elementName), location=top.location), '.',
              qNameAttrOccur(new(attr), location=top.location),
              location=top.location),
            ';',
            location=top.location),
        inputsWithAttr));
}
