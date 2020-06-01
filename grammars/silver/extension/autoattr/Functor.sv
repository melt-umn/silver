grammar silver:extension:autoattr;

concrete production functorAttributeDcl
top::AGDcl ::= 'functor' 'attribute' a::Name ';'
{
  top.unparse = "functor attribute " ++ a.unparse ++ ";";

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;
  
  top.errors <-
    if length(getAttrDclAll(fName, top.env)) > 1
    then [err(a.location, "Attribute '" ++ fName ++ "' is already bound.")]
    else [];
  
  forwards to
    defsAGDcl(
      [attrDef(defaultEnvItem(functorDcl(top.grammarName, a.location, fName, freshTyVar())))],
      location=top.location);
}

abstract production functorAttributionDcl
top::AGDcl ::= at::Decorated QName attl::BracketedOptTypeExprs nt::QName nttl::BracketedOptTypeExprs
{
  forwards to
    defaultAttributionDcl(
      at,
      if length(attl.types) > 0
      then attl
      else
        botlSome(
          '<',
          typeListSingle(
            nominalTypeExpr(nt.qNameType, nttl, location=top.location),
            location=top.location),
          '>', location=top.location),
      nt, nttl,
      location=top.location);
}

{--
 - Propagate a functor attribute on the enclosing production
 - @param attr  The name of the attribute to propagate
 -}
abstract production propagateFunctor
top::ProductionStmt ::= attr::Decorated QName
{
  -- No explicit errors, for now.  The only conceivable issue is the attribute not
  -- occuring on the LHS but this should be caught by the forward errors.  
  
  -- Generate the arguments for the constructor
  local topName::QName = qName(top.location, top.frame.signature.outputElement.elementName);
  local prodName::QName = qName(top.location, top.frame.fullName);
  prodName.grammarName = top.grammarName;
  prodName.config = top.config;
  prodName.env = top.env;

  local inputs :: [Expr] = 
    map(makeArg(top.location, top.env, attr, _), top.frame.signature.inputElements);
  local annotations :: [Pair<String Expr>] = 
    map(makeAnnoArg(top.location, topName, _), top.frame.signature.namedInputElements);

  -- Construct an attribute def and call with the generated arguments
  forwards to 
    attributeDef(
      concreteDefLHS(topName, location=top.location),
      '.',
      qNameAttrOccur(new(attr), location=top.location),
      '=',
      mkFullFunctionInvocation(
        top.location,
        baseExpr(prodName, location=top.location),
        inputs,
        annotations),
      ';',
      location=top.location);
}

{--
 - Generates the expression we should use for an argument
 - @param loc      The parent location to use in construction
 - @param env      The environment
 - @param attrName The name of the attribute being propagated
 - @param input    The NamedSignatureElement being propagated
 - @return Either this the child, or accessing `attrName` on the child
 -}
function makeArg
Expr ::= loc::Location env::Decorated Env attrName::Decorated QName input::NamedSignatureElement
{
  local at::QName = qName(loc, input.elementName);
  at.env = env;
  
  -- Check if the attribute occurs on the first child
  local attrOccursOnHead :: Boolean =
    !null(getOccursDcl(attrName.lookupAttribute.dcl.fullName, input.typerep.typeName, env));
  local validTypeHead :: Boolean = input.typerep.isDecorable;
  
  return
    if validTypeHead && attrOccursOnHead
    then access(
           baseExpr(at, location=loc), '.',
           qNameAttrOccur(new(attrName), location=loc),
           location=loc)
    else baseExpr(at, location=loc);
}

{--
 - Generates the list of AnnoExprs used in calling the constructor
 - @param loc      The parent location to use in construction
 - @param baseName The name of the parent from the signature
 - @param input   The NamedSignatureElement for an annotation
 - @return A list of AnnoExprs to be used to build the named arguments
 -}
function makeAnnoArg
Pair<String Expr> ::= loc::Location baseName::QName input::NamedSignatureElement
{
  -- TODO: This is a hacky way of getting the base name, not sure if correct
  -- trouble is the annotations are listed as fullnames, but have to be supplied as shortnames. weird.
  local annoName :: String = last(explode(":", input.elementName));

  return
    pair(annoName,
      access(
        baseExpr(baseName, location=loc), '.',
        qNameAttrOccur(qName(loc, annoName), location=loc),
        location=loc));
}
