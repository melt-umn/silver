import silver:langutil:pp;

{--
 - Propagates a list of functor attributes on the current production.  
 - Actual implementation in propagateOne
 -}
concrete production propagateAttrDcl
top::ProductionStmt ::= 'propagate' ns::NameList ';'
{
  top.pp = s"propagate ${ns.pp};";
  
  -- Forwards to productionStmtAppend of propagating the first element in ns
  -- and propagateAttrDcl containing the remaining names
  forwards to
    case ns of
      nameListOne(n) -> 
        propagateOne(n, location=top.location)
    | nameListCons(n, _, rest) ->
        productionStmtAppend(
          propagateOne(n, location=top.location),
          propagateAttrDcl($1, rest, $3, location=top.location),
          location=top.location)
    end;
}

{--
 - Generates the list of AppExprs used in calling the constructor
 - @param loc      The parent location to use in construction
 - @param env      The environment
 - @param attrName The name of the attribute being propagated
 - @param inputs   The NamedSignatureElements being propagated
 - @return A list of AppExprs to be used to build the arguments
 -}
function makeArgs
[AppExpr] ::= loc::Location env::Decorated Env attrName::QName inputs::[NamedSignatureElement]
{
  attrName.env = env;
  
  local at::QName = qName(loc, head(inputs).elementName);
  at.env = env;
  
  -- Check if the attribute occurs on the first child
  local attrOccursOnHead :: Boolean = 
    null(
      foldr(append, [], 
        -- The occurs dcls on this nonterminal for
        map(getOccursDcl(_, head(inputs).typerep.typeName, env),
          -- the full names of each candidate
          map((.fullName), attrName.lookupAttribute.dcls))));
  
  return
    case inputs of
      [] -> []
    | h :: rest -> 
        (if attrOccursOnHead
         then presentAppExpr(baseExpr(at, location=loc), location=loc)
         else presentAppExpr(
                access(
                  baseExpr(at, location=loc), '.',
                  qNameAttrOccur(attrName, location=loc),
                  location=loc),
                location=loc)) :: makeArgs(loc, env, attrName, rest)
    end;
}

{--
 - Generates the list of AnnoExprs used in calling the constructor
 - @param loc      The parent location to use in construction
 - @param baseName The name of the parent from the signature
 - @param inputs   The NamedSignatureElements for the annotations
 - @return A list of AnnoExprs to be used to build the named arguments
 -}
function makeAnnoArgs
[AnnoExpr] ::= loc::Location baseName::QName inputs::[NamedSignatureElement]
{
  -- TODO: This is a hacky way of getting the base name, not sure if correct
  local annoName::QName = qName(loc, last(explode(":", head(inputs).elementName)));

  return
    case inputs of
      [] -> []
    | h :: rest -> 
      annoExpr(
        annoName,
        '=',
        presentAppExpr(
          access(
            baseExpr(baseName, location=loc), '.',
              qNameAttrOccur(annoName, location=loc),
              location=loc),
            location=loc),
          location=loc) :: makeAnnoArgs(loc, baseName, rest)
    end;
}

-- In the future, this should maybe be dispatch for different types of attributes (e.g. monoid)
{--
 - Propagate a functor attribute on the enclosing production
 - @param a  The name of the attribute to propagate
 -}
abstract production propagateOne
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
  local inputs::AppExprs = 
    foldl(snocAppExprs(_, ',', _, location=top.location),
          emptyAppExprs(location=top.location),
          makeArgs(top.location, top.env, a, top.signature.inputElements));
  local annotations::AnnoAppExprs = 
    foldl(snocAnnoAppExprs(_, ',', _, location=top.location),
          emptyAnnoAppExprs(location=top.location),
          makeAnnoArgs(top.location, topName, top.signature.namedInputElements));

  -- Construct an attribute def and call with the generated arguments
  forwards to 
    attributeDef(
      concreteDefLHS(topName, location=top.location),
      '.',
      qNameAttrOccur(a, location=top.location),
      '=',
      application(
        baseExpr(prodName, location=top.location),
        '(', inputs, ',', annotations, ')',
        location=top.location),
      ';',
      location=top.location);
}
