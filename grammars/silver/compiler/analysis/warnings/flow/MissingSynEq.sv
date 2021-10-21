grammar silver:compiler:analysis:warnings:flow;

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
aspect function parseArgs
Either<String  Decorated CmdArgs> ::= args::[String]
{
  flags <- [("--warn-missing-syn", flag(warnMissingSynFlag))];
}

{- This is the primary check for missing synthesized equations.
 - In theory, this is the only check necessary to spot them all.
 -}
aspect production attributionDcl
top::AGDcl ::= 'attribute' at::QName attl::BracketedOptTypeExprs 'occurs' 'on' nt::QName nttl::BracketedOptTypeExprs ';'
{
  -- All non-forwarding productions for this nonterminal:
  local nfprods :: [String] = getNonforwardingProds(nt.lookupType.typeScheme.typeName, top.flowEnv);

  -- The check we're writing in this aspect can find all instances of missing
  -- synthesized equations, but in the interest of improved error messages, we
  -- will omit raising an error if we know it can be raised by `productionDcl`
  -- below instead. That's closer to where the fix should be applied.
  local suppress_this_error :: Boolean =
    -- This could be done using `isExportedBy` but I'm lazy and this is by far the common case.
    nt.lookupType.dcl.sourceGrammar == top.grammarName; 

  top.errors <-
    if nt.lookupType.found && at.lookupAttribute.found
    && (top.config.warnAll || top.config.warnMissingSyn || top.config.runMwda)
    -- We only care about synthesized attributes:
    && at.lookupAttribute.dcl.isSynthesized
    -- This error message won't be redundant:
    && !suppress_this_error
    -- And we can ignore any attribute that has a default equation:
    && null(lookupDef(nt.lookupType.fullName, at.lookupAttribute.fullName, top.flowEnv))
    -- Otherwise, examine them all:
    then flatMap(raiseMissingProds(top.location, at.lookupAttribute.fullName, _, top.flowEnv, top.config.runMwda), nfprods)
    else [];
}

{--
 - Examine a non-forwarding production `prod` to see if it is missing an equation
 - for the synthesized attribute `attr`.
 -
 - @param l      Location to raise the error message (of the attribute occurence)
 - @param attr   Full name of a synthesized attribute
 - @param prod   Full name of non-forwarding production to examine
 - @param e      The local flow environment
 - @returns      An error message from the attribute occurrence's perspective, if any
 -}
function raiseMissingProds
[Message] ::= l::Location  attr::String  prod::String  e::Decorated FlowEnv runMwda::Boolean
{
  -- Because the location is of the attribute occurrence, deliberately use the attribute's shortname
  local shortName :: String = substring(lastIndexOf(":", attr) + 1, length(attr), attr);

  return case lookupSyn(prod, attr, e) of
  | _ :: _ -> [] -- equation exists
  | [] -> [mwdaWrn(l, "attribute "  ++ shortName ++ " missing equation for production " ++ prod, runMwda)]
  end;

}

{- In the interest of improving error messages, we additionally check from production
 - declarations, to all locally known synthesized attributes.
 - This check is not necessary to catch additional mistakes, merely to move the error
 - closer to the location where a fix would be requird.
 -}
aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  -- All locally known synthesized attributes. This does not need to be exhaustive,
  -- because this error message is a courtesy, not the basis of the analysis.
  local attrs :: [OccursDclInfo] = 
    filter(isOccursSynthesized(_, top.env),
      getAttrsOn(namedSig.outputElement.typerep.typeName, top.env));

  top.errors <-
    if null(body.errors ++ ns.errors)
    && (top.config.warnAll || top.config.warnMissingSyn || top.config.runMwda)
    -- Forwarding productions do no have missing synthesized equations:
    && null(body.uniqueSignificantExpression)
    -- Otherwise, examine them all:
    then flatMap(raiseMissingAttrs(top.location, fName, _, top.flowEnv, top.config.runMwda), attrs)
    else [];
}

{--
 - Examine a non-forwarding production `prod` to see if it is missing an equation
 - for the synthesized attribute `attr`.
 -
 - @param l      Location to raise the error message (of the production)
 - @param attr   DclInfo of a synthesized attribute occurrence
 - @param prod   Full name of the non-forwarding production in question
 - @param e      The local flow environment
 - @returns      An error message from the production's perspective, if any
 -}
function raiseMissingAttrs
[Message] ::= l::Location  prod::String  attr::OccursDclInfo  e::Decorated FlowEnv runMwda::Boolean
{
  -- Because the location is of the production, deliberately use the production's shortname
  local shortName :: String = substring(lastIndexOf(":", prod) + 1, length(prod), prod);

  local lacks_default_equation :: Boolean = 
    null(lookupDef(attr.fullName, attr.attrOccurring, e));

  local missing_explicit_equation :: Boolean =
    case lookupSyn(prod, attr.attrOccurring, e) of
    | eq :: _ -> false
    | [] -> true
    end;
 
  return if lacks_default_equation && missing_explicit_equation
  then [mwdaWrn(l, "production " ++ shortName ++ " lacks synthesized equation for " ++ attr.attrOccurring, runMwda)]
  else [];
}

