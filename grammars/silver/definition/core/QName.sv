grammar silver:definition:core;

{--
 - Qualified names of the form 'a:b:c:d...'
 -}
nonterminal QName with config, name, location, grammarName, env, pp;
{--
 - Qualified names where the LAST name has an upper case first letter.
 -}
nonterminal QNameType with config, name, location, grammarName, env, pp;

{--
 - The list of declarations resulting from looking up this QName
 -}
synthesized attribute dcls :: [DclInfo];

-- TODO: for consistency, the order of these args should be flipped:
function qName
QName ::= l::Location s::String
{
  return qNameId(nameIdLower(terminal(IdLower_t, s, l), location=l), location=l);
}

concrete production qNameId
top::QName ::= id::Name
{
  top.name = id.name;
  top.pp = id.pp;
  
  top.lookupValue = decorate customLookup("value", getValueDcl(top.name, top.env), top.name, top.location) with {};
  top.lookupType = decorate customLookup("type", getTypeDcl(top.name, top.env), top.name, top.location) with {};
  top.lookupAttribute = decorate customLookup("attribute", getAttrDcl(top.name, top.env), top.name, top.location) with {};
}

concrete production qNameCons
top::QName ::= id::Name ':' qn::QName
{
  top.name = id.name ++ ":" ++ qn.name;
  top.pp = id.pp ++ ":" ++ qn.pp;
  
  top.lookupValue = decorate customLookup("value", getValueDcl(top.name, top.env), top.name, top.location) with {};
  top.lookupType = decorate customLookup("type", getTypeDcl(top.name, top.env), top.name, top.location) with {};
  top.lookupAttribute = decorate customLookup("attribute", getAttrDcl(top.name, top.env), top.name, top.location) with {};
}

abstract production qNameError
top::QName ::= msg::[Message]
{
  top.name = "err";
  top.pp = "<err>";
  
  top.lookupValue = decorate errorLookup(msg) with {};
  top.lookupType = decorate errorLookup(msg) with {};
  top.lookupAttribute = decorate errorLookup(msg) with {};
}

nonterminal QNameLookup with fullName, typerep, errors, dcls, dcl, dclBoundVars, found;

synthesized attribute lookupValue :: Decorated QNameLookup occurs on QName;
synthesized attribute lookupType :: Decorated QNameLookup occurs on QName;
synthesized attribute lookupAttribute :: Decorated QNameLookup occurs on QName;

abstract production customLookup
top::QNameLookup ::= kindOfLookup::String dcls::[DclInfo] name::String l::Location 
{
  top.dcls = dcls;
  top.found = !null(top.dcls); -- currently accurate
  top.dcl =
    if top.found then head(top.dcls)
    else error("INTERNAL ERROR: Accessing dcl of " ++ kindOfLookup ++ " " ++ name ++ " at " ++ l.unparse);
  
  top.fullName = if top.found then top.dcl.fullName else "undeclared:value:" ++ name;
  
  -- TODO: We could eliminate a lot of explicit calls to 'freshenCompletely' and make this more correct
  -- if we pushed into 'dcl' a different kind of Type, which recorded quantifiers.
  -- e.g. QuantifiedType. Then when we asked for .typerep of that, it always freshens.
  top.typerep = if top.found then top.dcl.typerep else errorType();
  top.dclBoundVars = if top.found then top.dcl.dclBoundVars else [];
  
  top.errors := 
    (if top.found then []
     else [err(l, "Undeclared " ++ kindOfLookup ++ " '" ++ name ++ "'.")]) ++
    (if length(top.dcls) <= 1 then []
     else [err(l, "Ambiguous reference to " ++ kindOfLookup ++ " '" ++ name ++ "'. Possibilities are:\n" ++ printPossibilities(top.dcls))]);
}

abstract production errorLookup
top::QNameLookup ::= msg::[Message]
{
  top.dcls = [];
  top.found = true;
  top.fullName = "err";
  top.typerep = errorType();
  top.dclBoundVars = [];
  top.errors := msg;
}

function printPossibilities
String ::= lst::[DclInfo]
{
  return implode("\n", map(dclinfo2possibility, lst));
}
function dclinfo2possibility
String ::= dcl::DclInfo
{
  -- TODO: perhaps some way of including types, when they are relevant (attributes, values)
  return "\t" ++ dcl.fullName ++ " (" ++ dcl.sourceLocation.filename ++ ":" ++ toString(dcl.sourceLocation.line) ++ ")";
}


---- Right now, this is only used for types:
attribute lookupType occurs on QNameType;

concrete production qNameTypeId
top::QNameType ::= id::IdUpper_t
{
  top.name = id.lexeme;
  top.pp = id.lexeme;
  
  top.lookupType = decorate customLookup("type", getTypeDcl(top.name, top.env), top.name, top.location) with {};
}

concrete production qNameTypeCons
top::QNameType ::= id::Name ':' qn::QNameType
{
  top.name = id.name ++ ":" ++ qn.name;
  top.pp = id.pp ++ ":" ++ qn.pp;
  
  top.lookupType = decorate customLookup("type", getTypeDcl(top.name, top.env), top.name, top.location) with {};
}

{--
 - Qualified name looked up CONTEXTUALLY
 -}
nonterminal QNameAttrOccur with config, name, location, grammarName, env, pp, attrFor, errors, typerep, dcl, attrDcl, found;

{--
 - For QNameAttrOccur, the name of the LHS to look up this attribute on.
 - i.e. 
 -}
inherited attribute attrFor :: Type;
synthesized attribute attrDcl :: DclInfo;

{--
 - Whether lookup was successful. Better than `null(_.errors)` because errors may be suppressed
 -}
synthesized attribute found :: Boolean;

concrete production qNameAttrOccur
top::QNameAttrOccur ::= at::QName
{
  top.name = at.name;
  top.pp = at.pp;
  
  -- Start with `at.lookupAttribute.dcls`
  -- Occurrence lookups for each attribute dcl on `top.attrFor`
  local narrowed :: [[DclInfo]] = 
    -- The occurs dcls on this nonterminal for
    map(getOccursDcl(_, top.attrFor.typeName, top.env),
      -- the full names of each candidate
      map((.fullName), at.lookupAttribute.dcls));
  -- TODO: BUG: this disambiguates, but doesn't find full-named that aren't in scope with short names!
  -- i.e. 'import somthing as prefixed;  something.a' won't find prefixed:a.

  -- Occurs dcls for `at` on `top.attrFor` (there should be only one)
  local dclsNarrowed :: [DclInfo] = concat(narrowed);
  
  -- Attribute dcls
  local attrsNarrowed :: [DclInfo] = zipFilterDcls(at.lookupAttribute.dcls, narrowed);
  
  -- This basically has to mirror the logic in errors below!
  top.found = 
    !(null(at.lookupAttribute.dcls) ||
      top.attrFor.isError ||
      null(dclsNarrowed) ||
      length(attrsNarrowed) != 1);
  
  top.errors :=
    if null(at.lookupAttribute.dcls) then
      at.lookupAttribute.errors
    else if top.attrFor.isError then
      -- suppress further errors
      []
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
  
  top.typerep = if top.found then determineAttributeType(head(dclsNarrowed), top.attrFor) else errorType();
  top.dcl = if top.found then head(dclsNarrowed) else
    error("INTERNAL ERROR: Accessing dcl of occurrence " ++ at.name ++ " at " ++ top.grammarName ++ " " ++ top.location.unparse);
  top.attrDcl = if top.found then head(attrsNarrowed) else
    error("INTERNAL ERROR: Accessing dcl of attribute " ++ at.name ++ " at " ++ top.grammarName ++ " " ++ top.location.unparse);
}

{--
 - `at` is a list of attribute declarations
 - `occ` is a mapped list of occurrence declarations for the corresponding attribute
 - we return only those `at` which have a non-empty element in `occ`
 -}
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
top::OccursCheck ::= at::Decorated QName  ntty::Type
{
  local occursCheck :: [DclInfo] =
    getOccursDcl(at.lookupAttribute.fullName, ntty.typeName, at.env); -- cheating to get env! :) Must be decorated!

  top.errors := if null(at.lookupAttribute.errors) && null(occursCheck) && !ntty.isError
                then [err(at.location, "Attribute '" ++ at.name ++ "' does not occur on '" ++ prettyType(ntty) ++ "'")]
                else [];
  top.typerep = if null(at.lookupAttribute.errors) && null(top.errors)
                then determineAttributeType(head(occursCheck), ntty)
                else errorType();
  top.dcl = head(occursCheck);
}


