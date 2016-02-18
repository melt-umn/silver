

{--
 - Propagates a list of functor attributes on the current production.  
 - Actual implementation in propagateOne
 -}
concrete production propagateAttrDcl
top::ProductionStmt ::= 'propagate' 'functor' ns::NameList ';'
{
  --top.errors <- [err(top.location, forward.pp)];
  forwards to
    case ns of
      nameListOne(n) -> 
        propagateOne(n, location=top.location)
    | nameListCons(n, _, rest) ->
        productionStmtAppend(
          propagateOne(n, location=top.location),
          propagateAttrDcl($1, $2, rest, $4, location=top.location),
          location=top.location)
    end;
}


{--
 - Generates the list of AppExprs used in calling the constructor
 -}
function makeArgs
[AppExpr] ::= loc::Location env::Decorated Env attrName::QName inputs::[NamedSignatureElement]
{
  attrName.env = env;
  
  local at::QName = qName(loc, head(inputs).elementName);
  at.env = env;
  
  -- Occurs dcls
  local narrowed :: [[DclInfo]] = 
    -- The occurs dcls on this nonterminal for
    map(getOccursDcl(_, head(inputs).typerep.typeName, env),
      -- the full names of each candidate
      map((.fullName), attrName.lookupAttribute.dcls));

  -- Occurs dcls
  local dclsNarrowed :: [DclInfo] = foldr(append, [], narrowed);
  
  return
    case inputs of
      [] -> []
    | h :: rest -> 
        if null(dclsNarrowed)
        then presentAppExpr(baseExpr(at, location=loc), location=loc) ::
             makeArgs(loc, env, attrName, rest)
        else presentAppExpr(
               access(
                 baseExpr(at, location=loc), '.',
                 qNameAttrOccur(attrName, location=loc),
                 location=loc),
               location=loc) :: makeArgs(loc, env, attrName, rest)
    end;
}

-- In the future, this should maybe be dispatch for different types of attributes (e.g. monoid)
{--
 -
 -}
abstract production propagateOne
top::ProductionStmt ::= a::QName
{
  -- No explicit errors, for now.  The only conceivable issue is the attribute not
  -- occuring on the LHS but this should be caught by the forward errors.  
  
  local topName::QName = qName(top.location, top.signature.outputElement.elementName);
  local prodName::QName = qName(top.location, top.signature.fullName);
  prodName.grammarName = top.grammarName;
  prodName.config = top.config;
  prodName.env = top.env;
  local inputs::AppExprs = 
    foldl(snocAppExprs(_, ',', _, location=top.location),
          emptyAppExprs(location=top.location),
          makeArgs(top.location, top.env, a, top.signature.inputElements));

  forwards to 
    attributeDef(
      concreteDefLHS(topName, location=top.location),
      '.',
      qNameAttrOccur(a, location=top.location),
      '=',
      applicationExpr(
        functionReference(prodName, location=top.location),
        '(', inputs, ')',
        location=top.location),
      ';',
      location=top.location);
}
