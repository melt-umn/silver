grammar silver:compiler:extension:autoattr;

concrete production functorAttributeDcl
top::AGDcl ::= 'functor' 'attribute' a::Name ';'
{
  top.unparse = "functor attribute " ++ a.unparse ++ ";";
  top.moduleNames := [];

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;
  
  top.errors <-
    if length(getAttrDclAll(fName, top.env)) > 1
    then [err(a.location, "Attribute '" ++ fName ++ "' is already bound.")]
    else [];
  
  forwards to
    defsAGDcl(
      [attrDef(defaultEnvItem(functorDcl(fName, sourceGrammar=top.grammarName, sourceLocation=a.location)))],
      location=top.location);
}

abstract production functorAttributionDcl
top::AGDcl ::= at::PartiallyDecorated QName attl::BracketedOptTypeExprs nt::QName nttl::BracketedOptTypeExprs
{
  undecorates to attributionDcl('attribute', at, attl, 'occurs', 'on', nt, nttl, ';', location=top.location);
  top.unparse = "attribute " ++ at.unparse ++ attl.unparse ++ " occurs on " ++ nt.unparse ++ nttl.unparse ++ ";";
  top.moduleNames := [];

  propagate grammarName, env, flowEnv;
  
  forwards to
    defaultAttributionDcl(
      at,
      if length(attl.types) > 0
      then attl
      else
        botlSome(
          bTypeList(
            '<',
            typeListSingle(
              case nttl of
              | botlSome(tl) -> 
                appTypeExpr(
                  nominalTypeExpr(nt.qNameType, location=top.location),
                  tl, location=top.location)
              | botlNone() -> nominalTypeExpr(nt.qNameType, location=top.location)
              end,
              location=top.location),
            '>', location=top.location),
          location=top.location),
      nt, nttl,
      location=top.location);
}

{--
 - Propagate a functor attribute on the enclosing production
 - @param attr  The name of the attribute to propagate
 -}
abstract production propagateFunctor
top::ProductionStmt ::= attr::PartiallyDecorated QName
{
  undecorates to propagateOneAttr(attr, location=top.location);
  top.unparse = s"propagate ${attr.unparse};";
  
  -- No explicit errors, for now.  The only conceivable issue is the attribute not
  -- occuring on the LHS but this should be caught by the forward errors.  
  
  -- Generate the arguments for the constructor
  local inputs :: [Expr] = 
    map(makeArg(top.location, top.env, attr, _), top.frame.signature.inputElements);
  local annotations :: [Pair<String Expr>] = 
    map(
      makeAnnoArg(top.location, top.frame.signature.outputElement.elementName, _),
      top.frame.signature.namedInputElements);

  -- Construct an attribute def and call with the generated arguments
  forwards to
    attributeDef(
      concreteDefLHS(qName(top.location, top.frame.signature.outputElement.elementName), location=top.location),
      '.',
      qNameAttrOccur(new(attr), location=top.location),
      '=',
      mkFullFunctionInvocation(
        top.location,
        baseExpr(qName(top.location, top.frame.fullName), location=top.location),
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
  local validTypeHead :: Boolean = isDecorable(input.typerep, env) && !input.typerep.isPartiallyDecorated;
  
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
Pair<String Expr> ::= loc::Location baseName::String input::NamedSignatureElement
{
  -- TODO: This is a hacky way of getting the base name, not sure if correct
  -- trouble is the annotations are listed as fullnames, but have to be supplied as shortnames. weird.
  local annoName :: String = last(explode(":", input.elementName));

  return
    pair(annoName,
      access(
        baseExpr(qName(loc, baseName), location=loc), '.',
        qNameAttrOccur(qName(loc, annoName), location=loc),
        location=loc));
}
