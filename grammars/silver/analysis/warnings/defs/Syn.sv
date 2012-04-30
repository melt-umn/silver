grammar silver:analysis:warnings:defs;

synthesized attribute warnMissingSyn :: Boolean occurs on CmdArgs;

aspect production endCmdArgs
top::CmdArgs ::= l::[String]
{
  top.warnMissingSyn = false;
}
abstract production warnMissingSynFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.warnMissingSyn = true;
  forwards to rest;
}
aspect production run
top::RunUnit ::= iIn::IO args::[String]
{
  flags <- [pair("--warn-missing-syn", flag(warnMissingSynFlag))];
}

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  -- stop if this production forwards
  -- Lookup all attribute that occurs on our LHS (filter to SYN!)
  -- Ensure there exists an equation for each on this production
  
  local attrs :: [Decorated DclInfo] = 
    filter(isOccursSynthesized(_, top.env),
      getAttrsOn(namedSig.outputElement.typerep.typeName, top.env));

  top.errors <-
    if null(body.errors ++ ns.errors{-TODO-})
    && (top.config.warnAll || top.config.warnMissingSyn)
    && null(body.uniqueSignificantExpression) -- no forward
    then raiseMissingAttrs(top.location, fName, attrs, top.flowEnv)
    else [];
}

function isOccursSynthesized
Boolean ::= occs::Decorated DclInfo  e::Decorated Env
{
  return case getAttrDcl(occs.attrOccurring, e) of
  | synDcl(_,_,_,_,_) :: _ -> true
  | _ -> false
  end;
}

function raiseMissingAttrs
[Message] ::= l::Location  fName::String  attrs::[Decorated DclInfo]  e::Decorated FlowEnv
{
  return if null(attrs) then []
  else (
       if null(lookupDef(head(attrs).fullName, head(attrs).attrOccurring, e)) -- no default eq!
       then
         case lookupSyn(fName, head(attrs).attrOccurring, e) of
         | eq :: _ -> []
         | [] -> [wrn(l, "production " ++ fName ++ " lacks synthesized equation for " ++ head(attrs).attrOccurring)]
         end
       else []) ++ raiseMissingAttrs(l, fName, tail(attrs), e);
}

aspect production attributionDcl
top::AGDcl ::= 'attribute' at::QName attl::BracketedOptTypeList 'occurs' 'on' nt::QName nttl::BracketedOptTypeList ';'
{
  -- ensure we're looking at a syn attribute
  -- Lookup all productions for this nonterminal
  -- ensure an equation exists for each production or the production forwards
  
  local prods :: [Decorated DclInfo] = getProdsOn(nt.lookupType.typerep.typeName, top.env);

  top.errors <-
    if null(nt.lookupType.errors ++ at.lookupAttribute.errors)
    && (top.config.warnAll || top.config.warnMissingSyn)
    && (case at.lookupAttribute.dcl of synDcl(_,_,_,_,_) -> true | _ -> false end) -- TODO: we really need a better way to do this
    && null(lookupDef(nt.lookupType.fullName, at.lookupAttribute.fullName, top.flowEnv)) -- no default eq!
    then raiseMissingProds(top.location, at.lookupAttribute.fullName, prods, top.flowEnv)
    else [];
}

function raiseMissingProds
[Message] ::= l::Location  fName::String  prods::[Decorated DclInfo]  e::Decorated FlowEnv
{
  return if null(prods) then []
  else case lookupSyn(head(prods).fullName, fName, e),  lookupFwd(head(prods).fullName, e) of
       | _ :: _, _ -> [] -- eq present
       | [], _ :: _ -> [] -- prod forwards
       | [], [] -> [wrn(l, "attribute "  ++ fName ++ " missing equation for production " ++ head(prods).fullName)]
       end ++ raiseMissingProds(l, fName, tail(prods), e);

}

