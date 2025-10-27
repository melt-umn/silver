grammar silver:compiler:extension:autoattr;

abstract production propagateInh implements Propagate
top::ProductionStmt ::= includeShared::Boolean @attr::QName
{
  top.unparse = s"propagate ${if includeShared then "@" else ""}${attr.unparse};";
  
  local attrFullName::String = attr.lookupAttribute.dcl.fullName;
  local inputsWithAttr::[NamedSignatureElement] =
    filter(
      \ input::NamedSignatureElement ->
        isDecorable(input.elementDclType, top.env) &&
        !null(getOccursDcl(attrFullName, input.typerep.typeName, top.env)) &&
        (includeShared || !input.elementShared),
      top.frame.signature.inputElements);

  forwards to
    foldr(
      productionStmtAppend(_, _),
      emptyProductionStmt(),
      map(
        \ ie::NamedSignatureElement ->
          attributeDef(
            concreteDefLHS(qName(ie.elementName)), '.',
            qNameAttrOccur(^attr),
            '=',
            access(
              baseExpr(qName(top.frame.signature.outputElement.elementName)), '.',
              qNameAttrOccur(^attr)),
            ';'),
        inputsWithAttr));
}
