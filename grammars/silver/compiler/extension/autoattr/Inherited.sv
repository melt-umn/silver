grammar silver:compiler:extension:autoattr;

abstract production propagateInh implements Propagate
top::ProductionStmt ::= @attr::QName
{
  top.unparse = s"propagate ${attr.unparse};";
  
  local attrFullName::String = attr.lookupAttribute.dcl.fullName;
  local inputsWithAttr::[NamedSignatureElement] =
    filter(
      \ input::NamedSignatureElement ->
        ((isDecorable(input.typerep, top.env) &&
          -- Only propagate for unique decorated children that don't have the attribute
          case getMaxRefSet(input.typerep, top.env) of
          | just(inhs) -> !contains(attrFullName, inhs)
          | nothing() -> false
          end) ||
         -- TODO: Check that the attribute is not already defined.
         -- This may require consulting the flowEnv, but shouldn't require inferring flow types.
         input.elementShared) &&
        !null(getOccursDcl(attrFullName, input.typerep.typeName, top.env)),
      top.frame.signature.inputElements);
  forwards to
    foldr(
      productionStmtAppend(_, _),
      errorProductionStmt([]), -- No emptyProductionStmt?
      map(
        \ ie::NamedSignatureElement ->
          attributeDef(
            concreteDefLHS(qName(ie.elementName)), '.',
            qNameAttrOccur(new(attr)),
            '=',
            access(
              baseExpr(qName(top.frame.signature.outputElement.elementName)), '.',
              qNameAttrOccur(new(attr))),
            ';'),
        inputsWithAttr));
}
