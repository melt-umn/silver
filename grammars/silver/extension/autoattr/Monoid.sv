grammar silver:extension:autoattr;

concrete production monoidAttributeDcl
top::AGDcl ::= 'monoid' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr 'with' e::Expr ',' q::NameOrBOperator ';'
{
  top.unparse = "monoid attribute " ++ a.unparse ++ tl.unparse ++ " :: " ++ te.unparse ++ " with " ++ e.unparse ++ ", " ++ q.unparse ++ ";";

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  tl.initialEnv = top.env;
  tl.env = tl.envBindingTyVars;
  te.env = tl.envBindingTyVars;
  
  q.operatorForType = te.typerep;
  
  -- TODO: We want to define our own defs here but can't forward to defsAGDcl because collections define different translation.
  -- Not sure about the best way to refactor this.
  top.defs :=
    [attrDef(defaultEnvItem(monoidDcl(top.grammarName, a.location, fName, tl.freeVariables, te.typerep, e, q.operation)))];

  top.errors <- e.errors;
  
  top.errors <-
    if length(getAttrDclAll(fName, top.env)) > 1
    then [err(a.location, "Attribute '" ++ fName ++ "' is already bound.")]
    else [];
  
  local errCheck1 :: TypeCheck = check(e.typerep, te.typerep);
  top.errors <-
    if errCheck1.typeerror
    then [err(e.location, "Monoid attribute " ++ fName ++ " of type " ++ errCheck1.rightpp ++ " has empty value specified with type " ++ errCheck1.leftpp)]
    else [];

  e.downSubst = emptySubst();
  errCheck1.downSubst = e.upSubst;

  errCheck1.finalSubst = errCheck1.upSubst;
  e.finalSubst = errCheck1.upSubst;
  
  forwards to
    collectionAttributeDclSyn(
      'synthesized', 'attribute', a, tl, '::', te, 'with', q, ';',
      location=top.location);
}

synthesized attribute appendProd :: (Expr ::= Expr Expr Location) occurs on Operation;

aspect production functionOperation
top::Operation ::= s::String
{
  top.appendProd = \ e1::Expr e2::Expr l::Location -> mkStrFunctionInvocation(l, s, [e1, e2]);
}
aspect production productionOperation
top::Operation ::= s::String
{
  top.appendProd = \ e1::Expr e2::Expr l::Location -> mkStrFunctionInvocation(l, s, [e1, e2]);
}
aspect production plusPlusOperationString
top::Operation ::= 
{
  top.appendProd = plusPlus(_, '++', _, location=_);
}
aspect production plusPlusOperationList
top::Operation ::= 
{
  top.appendProd = plusPlus(_, '++', _, location=_);
}
aspect production borOperation
top::Operation ::= 
{
  top.appendProd = or(_, '||', _, location=_);
}
aspect production bandOperation
top::Operation ::= 
{
  top.appendProd = and(_, '&&', _, location=_);
}

{--
 - Propagate a monoid attribute on the enclosing production
 - @param attr  The name of the attribute to propagate
 -}
abstract production propagateMonoid
top::ProductionStmt ::= attr::Decorated QName
{
  top.unparse = s"propagate ${attr.unparse};";
  
  -- No explicit errors, for now.  The only conceivable issue is the attribute not
  -- occuring on the LHS but this should be caught by the forward errors.  
  
  local attrFullName::String = attr.lookupAttribute.dcl.fullName;
  local inputsWithAttr::[NamedSignatureElement] =
    filter(
      \ input::NamedSignatureElement ->
        input.typerep.isDecorable &&
        !null(getOccursDcl(attrFullName, input.typerep.typeName, top.env)),
      top.frame.signature.inputElements);
  local res :: Expr = 
    if null(inputsWithAttr)
    then attr.lookupAttribute.dcl.emptyVal
    else
      foldr1(
        attr.lookupAttribute.dcl.operation.appendProd(_, _, top.location),
        map(
          \ i::NamedSignatureElement ->
            access(
              baseExpr(qName(top.location, i.elementName), location=top.location),
              '.',
              qNameAttrOccur(new(attr), location=top.location),
              location=top.location),
          inputsWithAttr));

  -- Construct an attribute def and call with the generated arguments
  forwards to
    attrContainsBase(
      concreteDefLHS(qName(top.location, top.frame.signature.outputElement.elementName), location=top.location),
      '.',
      qNameAttrOccur(new(attr), location=top.location),
      ':=', res, ';', location=top.location);
}
