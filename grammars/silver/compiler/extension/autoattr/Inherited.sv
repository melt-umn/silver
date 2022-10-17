grammar silver:compiler:extension:autoattr;

abstract production propagateInh
top::ProductionStmt ::= attr::PartiallyDecorated QName
{
  -- undecorates to propagateOneAttr(attr, location=top.location);
  top.unparse = s"propagate ${attr.unparse};";
  
  local attrFullName::String = attr.lookupAttribute.dcl.fullName;
  local inputsWithAttr::[NamedSignatureElement] =
    filter(
      \ input::NamedSignatureElement ->
        isDecorable(input.typerep, top.env) &&
        !input.typerep.isPartiallyDecorated &&  -- Don't copy on partially decorated children
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
