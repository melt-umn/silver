grammar silver:compiler:extension:attrsection;

imports silver:compiler:definition:core;
imports silver:compiler:definition:type:syntax;
imports silver:compiler:definition:type;
imports silver:compiler:definition:env;
imports silver:compiler:modification:lambda_fn;

concrete production attributeSection
top::Expr ::= '(' '.' q::QNameAttrOccur ')'
{
  top.unparse = s"(.${q.unparse})";

  local attrType::Type =
    -- Try to determine the type of the type of the attribute if it can be looked up unambiguously.
    -- Note that this will have fresh type variables filled in if the attribute is polymorphic.
    case q of
    | qNameAttrOccur(at) when at.lookupAttribute.dcls matches [dcl] -> dcl.typeScheme.typerep
    | _ -> freshType()
    end;
  top.typerep = appTypes(functionType(1, []), [freshType(), attrType]);
  top.upSubst = top.downSubst;

  local finalTy::Type = performSubstitution(top.typerep, top.finalSubst);
  local inputTy::Type = head(finalTy.inputTypes);
  local outputTy::Type = finalTy.outputType;
  top.errors :=
    if !null(outputTy.freeFlexibleVars)
    then [err(top.location, s"The attribute section ${top.unparse} has an ambiguous inferred output type ${prettyType(outputTy)}, where ${implode(", ", map(findAbbrevFor(_, outputTy.freeVariables), outputTy.freeFlexibleVars))} ${if length(outputTy.freeFlexibleVars) > 1 then "are" else "is"} unspecialized")]
    else forward.errors;

  local checkOutputType::TypeCheck = check(outputTy, forward.typerep.outputType);
  checkOutputType.downSubst = forward.upSubst;
  checkOutputType.finalSubst = composeSubst(forward.upSubst, top.finalSubst);
  top.errors <-
    if checkOutputType.typeerror
    then [err(top.location, s"Attribute section ${top.unparse} has inferred output type ${checkOutputType.leftpp} that does not match the attribute's type ${checkOutputType.rightpp}")]
    else [];

  forwards to
    lambdap(
      productionRHSCons(
        productionRHSElem(name("x", top.location), '::', typerepTypeExpr(inputTy, location=top.location), location=top.location),
        productionRHSNil(location=top.location),
        location=top.location),
      access(baseExpr(qName(top.location, "x"), location=top.location), '.', q, location=top.location),
      location=top.location);
}
