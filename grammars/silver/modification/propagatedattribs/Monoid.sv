function makeMonoidExpr
Expr ::= loc::Location env::Decorated Env attrName::QName combine::(Expr ::= Expr Expr Location) nullVal::(Expr ::= Location) inputs::[NamedSignatureElement]
{
  attrName.env = env;
  
  local at::QName = qName(loc, head(inputs).elementName);
  at.env = env;
  
  -- Check if the attribute occurs on the first child
  local attrOccursOnHead :: Boolean = 
    !null(
      foldr(append, [], 
        -- The occurs dcls on this nonterminal for
        map(getOccursDcl(_, head(inputs).typerep.typeName, env),
          -- the full names of each candidate
          map((.fullName), attrName.lookupAttribute.dcls))));
  local validTypeHead :: Boolean = head(inputs).typerep.isDecorable;
  
  return
    case inputs of
      [] -> nullVal(loc)
    | [h] ->
        if validTypeHead && attrOccursOnHead
        then access(
               baseExpr(at, location=loc), '.',
               qNameAttrOccur(attrName, location=loc),
               location=loc)
        else nullVal(loc)
    | h :: rest -> 
        if validTypeHead && attrOccursOnHead
        then combine(
               access(
                 baseExpr(at, location=loc), '.',
                 qNameAttrOccur(attrName, location=loc),
                 location=loc),
               makeMonoidExpr(loc, env, attrName, combine, nullVal, rest),
               loc)
        else makeMonoidExpr(loc, env, attrName, combine, nullVal, rest)
    end;
}
{-
abstract production propagateMonoid
top::ProductionStmt ::= a::QName
{
  -- No explicit errors, for now.  The only conceivable issue is the attribute not
  -- occuring on the LHS but this should be caught by the forward errors.  
  
  -- Generate the arguments for the constructor
  local topName::QName = qName(top.location, top.signature.outputElement.elementName);
  local prodName::QName = qName(top.location, top.signature.fullName);
  prodName.grammarName = top.grammarName;
  prodName.config = top.config;
  prodName.env = top.env;
  local rhs::Expr = 
    makeMonoidExpr(top.location, top.env, a, top.signature.inputElements);

  -- Construct an attribute def and call with the generated arguments
  forwards to 
    attributeDef(
      concreteDefLHS(topName, location=top.location),
      '.',
      qNameAttrOccur(a, location=top.location),
      '=',
      rhs,
      ';',
      location=top.location);
}-}
