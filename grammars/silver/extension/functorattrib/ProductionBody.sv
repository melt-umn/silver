
{--
 - Propagates a list of functor attributes on the current production.  
 - Actual implementation in propagateOne
 -}
concrete production propagateAttrDclOld
top::ProductionStmt ::= 'propagate_functor' ns::NameList ';'
{
  top.unparse = s"propagate ${ns.unparse};";
  
  -- Forwards to productionStmtAppend of propagating the first element in ns
  -- and propagateAttrDcl containing the remaining names
  forwards to
    case ns of
    | nameListOne(n) -> 
        propagateOne(n, location=top.location)
    | nameListCons(n, _, rest) ->
        productionStmtAppend(
          propagateOne(n, location=top.location),
          propagateAttrDclOld($1, rest, $3, location=top.location),
          location=top.location)
    end;
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
Expr ::= loc::Location env::Decorated Env attrName::QName input::NamedSignatureElement
{
  attrName.env = env;
  
  local at::QName = qName(loc, input.elementName);
  at.env = env;
  
  -- Check if the attribute occurs on the first child
  local attrOccursOnHead :: Boolean = 
    !null(
      -- The occurs dcls on this nonterminal for
      flatMap(getOccursDcl(_, input.typerep.typeName, env),
          -- the full names of each candidate
          map((.fullName), attrName.lookupAttribute.dcls)));
  local validTypeHead :: Boolean = input.typerep.isDecorable;
  
  return
    if validTypeHead && attrOccursOnHead
    then access(
           baseExpr(at, location=loc), '.',
           qNameAttrOccur(attrName, location=loc),
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

-- In the future, this should maybe be dispatch for different types of attributes (e.g. monoid)
{--
 - Propagate a functor attribute on the enclosing production
 - @param attr  The name of the attribute to propagate
 -}
abstract production propagateOne
top::ProductionStmt ::= attr::QName
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
      qNameAttrOccur(attr, location=top.location),
      '=',
      mkFullFunctionInvocation(
        top.location,
        baseExpr(prodName, location=top.location),
        inputs,
        annotations),
      ';',
      location=top.location);
}
