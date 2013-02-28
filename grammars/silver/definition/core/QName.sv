grammar silver:definition:core;

{--
 - Qualified names of the form 'a:b:c:d...'
 -}
nonterminal QName with config, name, location, grammarName, file, env, pp;
{--
 - Qualified names where the LAST name has an upper case first letter.
 -}
nonterminal QNameType with config, name, location, grammarName, file, env, pp;

{--
 - The list of declarations resulting from looking up this QName
 -}
synthesized attribute dcls :: [DclInfo];

function qName
QName ::= l::Location s::String
{
  return qNameId(nameIdLower(terminal(IdLower_t, s, l.line, l.column)));
}

concrete production qNameId
top::QName ::= id::Name
{
  top.name = id.name;
  top.pp = id.pp;
  top.location = id.location;
  
  top.lookupValue = decorate customLookup("value", getValueDcl(top.name, top.env), top.name, top.location) with {};
  top.lookupType = decorate customLookup("type", getTypeDcl(top.name, top.env), top.name, top.location) with {};
  top.lookupAttribute = decorate customLookup("attribute", getAttrDcl(top.name, top.env), top.name, top.location) with {};
}

concrete production qNameCons
top::QName ::= id::Name ':' qn::QName
{
  top.name = id.name ++ ":" ++ qn.name;
  top.pp = id.pp ++ ":" ++ qn.pp;
  top.location = $2.location;
  
  top.lookupValue = decorate customLookup("value", getValueDcl(top.name, top.env), top.name, top.location) with {};
  top.lookupType = decorate customLookup("type", getTypeDcl(top.name, top.env), top.name, top.location) with {};
  top.lookupAttribute = decorate customLookup("attribute", getAttrDcl(top.name, top.env), top.name, top.location) with {};
}

nonterminal QNameLookup with fullName, typerep, errors, dcls, dcl, dclBoundVars;

synthesized attribute lookupValue :: Decorated QNameLookup occurs on QName;
synthesized attribute lookupType :: Decorated QNameLookup occurs on QName;
synthesized attribute lookupAttribute :: Decorated QNameLookup occurs on QName;

abstract production customLookup
top::QNameLookup ::= kindOfLookup::String dcls::[DclInfo] name::String l::Location 
{
  top.dcls = dcls;
  top.dcl = if null(top.dcls) then error("INTERNAL ERROR: Accessing dcl of " ++ kindOfLookup ++ " " ++ name ++ " at " ++ l.unparse)
            else head(top.dcls);
  
  top.fullName = if null(top.dcls) then "undeclared:value:" ++ name
                 else top.dcl.fullName;
  
  top.typerep = if null(top.dcls) then errorType()  else top.dcl.typerep;
  top.dclBoundVars = if null(top.dcls) then []      else top.dcl.dclBoundVars;
  
  top.errors := (if null(top.dcls)
                  then [err(l, "Undeclared " ++ kindOfLookup ++ " '" ++ name ++ "'.")]
                  else [])
             ++ (if length(top.dcls) > 1
                  then [err(l, "Ambiguous reference to " ++ kindOfLookup ++ " '" ++ name ++ "'. Possibilities are:\n" ++ printPossibilities(top.dcls))] 
                  else []);
}

function printPossibilities
String ::= lst::[DclInfo]
{
  local attribute dcl :: DclInfo;
  dcl = head(lst);
  
  -- TODO: perhaps some way of including types, when they are relevant (attributes, values)
  return if null(lst) then ""
         else ("\t" ++ dcl.fullName ++ " (" ++ dcl.sourceLocation.filename ++ ":" ++ toString(dcl.sourceLocation.line) ++ ")\n")
              ++ printPossibilities(tail(lst));
}


---- Right now, this is only used for types:
attribute lookupType occurs on QNameType;

concrete production qNameTypeId
top::QNameType ::= id::IdUpper_t
{
  top.name = id.lexeme;
  top.pp = id.lexeme;
  top.location = $1.location;
  
  top.lookupType = decorate customLookup("type", getTypeDcl(top.name, top.env), top.name, top.location) with {};
}

concrete production qNameTypeCons
top::QNameType ::= id::Name ':' qn::QNameType
{
  top.name = id.name ++ ":" ++ qn.name;
  top.pp = id.pp ++ ":" ++ qn.pp;
  top.location = $2.location;
  
  top.lookupType = decorate customLookup("type", getTypeDcl(top.name, top.env), top.name, top.location) with {};
}

{--
 - Qualified name looked up CONTEXTUALLY
 -}
nonterminal QNameAttrOccur with config, name, location, grammarName, file, env, pp, attrFor, errors, typerep, dcl, attrDcl;

{--
 - For QNameAttrOccur, the name of the LHS to look up this attribute on.
 - i.e. 
 -}
inherited attribute attrFor :: TypeExp;
synthesized attribute attrDcl :: DclInfo;

concrete production qNameAttrOccur
top::QNameAttrOccur ::= at::QName
{
  top.name = at.name;
  top.pp = at.pp;
  top.location = at.location;
  
  -- Occurs dcls
  local narrowed :: [[DclInfo]] = 
    -- The occurs dcls on this nonterminal for
    map(getOccursDcl(_, top.attrFor.typeName, top.env),
      -- the full names of each candidate
      map((.fullName), at.lookupAttribute.dcls));

  -- Occurs dcls
  local dclsNarrowed :: [DclInfo] = foldr(append, [], narrowed);
  
  -- Attribute dcls
  local attrsNarrowed :: [DclInfo] = zipFilterDcls(at.lookupAttribute.dcls, narrowed);
    
  top.errors :=
    if null(at.lookupAttribute.dcls) then
      at.lookupAttribute.errors
    else if null(dclsNarrowed) then 
      -- Note we're using the short name of the attribute... there may be more than one attribute Dcl
      -- But, none of them occur so this error quite suffices!
      [err(at.location, "Attribute '" ++ at.name ++ "' does not occur on '" ++ prettyType(top.attrFor) ++ "'. Looked at:\n" ++ printPossibilities(at.lookupAttribute.dcls))]
    else if length(attrsNarrowed) > 1 then
      -- Here we've found multiple attributes that occur here!
      [err(at.location, "Ambiguous reference to attribute occurring on '" ++ at.name ++ "'. Possibilities are:\n" ++ printPossibilities(attrsNarrowed))]
    else []; {-if length(dclsNarrowed) > 1 then
      -- This should be essentially impossible! (Requires "orphaned occurs" which are currently allowed...)
      [err(at.location, "There are erroneously multiple attribute occurrences for '" ++ at.name ++ "'. Possibilities are:\n" ++ printPossibilities(dclsNarrowed))]
    else [];-}
    -- TODO: This last bit is disabled because we have problems with importing grammars multiple times.
    -- TODO FIXME: enable this, and fix the grammar import issues!
  
  top.typerep = if null(top.errors) then determineAttributeType(head(dclsNarrowed), top.attrFor) else errorType();
  top.dcl = if null(top.errors) then head(dclsNarrowed) else
    error("INTERNAL ERROR: Accessing dcl of occurrence " ++ at.name ++ " at " ++ top.location.unparse);
  top.attrDcl = if length(attrsNarrowed) == 1 then head(attrsNarrowed) else
    error("INTERNAL ERROR: Accessing dcl of attribute " ++ at.name ++ " at " ++ top.location.unparse);
}

function zipFilterDcls
[DclInfo] ::= at::[DclInfo]  occ::[[DclInfo]]
{
  return if null(at) then []
  else if null(head(occ)) then zipFilterDcls(tail(at), tail(occ))
  else head(at) :: zipFilterDcls(tail(at), tail(occ));
}


-- TODO THIS SHOULD BE OBSOLETED BY THE ABOVE
nonterminal OccursCheck with errors, typerep, dcl;

-- Doc note: be sure you've included at.errors, as well as this production's errors!
abstract production occursCheckQName
top::OccursCheck ::= at::Decorated QName  ntty::TypeExp
{
  local occursCheck :: [DclInfo] =
    getOccursDcl(at.lookupAttribute.fullName, ntty.typeName, at.env); -- cheating to get env! :) Must be decorated!

  top.errors := if null(at.lookupAttribute.errors) && null(occursCheck)
                then [err(at.location, "Attribute '" ++ at.name ++ "' does not occur on '" ++ prettyType(ntty) ++ "'")]
                else [];
  top.typerep = if null(at.lookupAttribute.errors) && null(top.errors)
                then determineAttributeType(head(occursCheck), ntty)
                else errorType();
  top.dcl = head(occursCheck);
}


