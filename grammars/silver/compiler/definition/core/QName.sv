grammar silver:compiler:definition:core;

{--
 - Qualified names of the form 'a:b:c:d...'
 -}
nonterminal QName with config, name, location, grammarName, env, unparse, qNameType, baseNameLoc;
{--
 - Qualified names where the LAST name has an upper case first letter.
 -}
nonterminal QNameType with config, name, location, grammarName, env, unparse, baseNameLoc;

flowtype decorate {env} on QName, QNameType;

{--
 - The list of declarations resulting from looking up this QName
 -}
synthesized attribute dcls<a> :: [a];

synthesized attribute qNameType::QNameType;
synthesized attribute baseNameLoc::Location;

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
  top.unparse = id.unparse;
  top.qNameType = qNameTypeId(terminal(IdUpper_t, id.name, id.location), location=id.location);
  top.baseNameLoc = id.location;
  
  top.lookupValue = customLookup("value", getValueDcl(top.name, top.env), top.name, top.location);
  top.lookupType = customLookup("type", getTypeDcl(top.name, top.env), top.name, top.location);
  top.lookupAttribute = customLookup("attribute", getAttrDcl(top.name, top.env), top.name, top.location);
}

concrete production qNameCons
top::QName ::= id::Name ':' qn::QName
{
  top.name = id.name ++ ":" ++ qn.name;
  top.unparse = id.unparse ++ ":" ++ qn.unparse;
  top.qNameType = qNameTypeCons(id, ':', qn.qNameType, location=top.location);
  top.baseNameLoc = qn.baseNameLoc;
  
  top.lookupValue = customLookup("value", getValueDcl(top.name, top.env), top.name, top.location);
  top.lookupType = customLookup("type", getTypeDcl(top.name, top.env), top.name, top.location);
  top.lookupAttribute = customLookup("attribute", getAttrDcl(top.name, top.env), top.name, top.location);
} action {
  insert semantic token IdGrammarName_t at id.location;
}

abstract production qNameError
top::QName ::= msg::[Message]
{
  top.name = "err";
  top.unparse = "<err>";
  top.qNameType = qNameTypeId(terminal(IdUpper_t, "Err", top.location), location=top.location);
  top.baseNameLoc = top.location;
  
  top.lookupValue = errorLookup(msg);
  top.lookupType = errorLookup(msg);
  top.lookupAttribute = errorLookup(msg);
}

data nonterminal QNameLookup<a> with fullName, typeScheme, errors, dcls<a>, dcl<a>, found;

synthesized attribute lookupValue :: QNameLookup<ValueDclInfo> occurs on QName;
synthesized attribute lookupType :: QNameLookup<TypeDclInfo> occurs on QName;
synthesized attribute lookupAttribute :: QNameLookup<AttributeDclInfo> occurs on QName;

flowtype QName = lookupValue {env}, lookupType {env}, lookupAttribute {env};

abstract production customLookup
attribute fullName {} occurs on a,
attribute typeScheme {} occurs on a,
annotation sourceLocation occurs on a =>
top::QNameLookup<a> ::= kindOfLookup::String dcls::[a] name::String l::Location 
{
  top.dcls = dcls;
  top.found = !null(top.dcls); -- currently accurate
  top.dcl =
    if top.found then head(top.dcls)
    else error("INTERNAL ERROR: Accessing dcl of " ++ kindOfLookup ++ " " ++ name ++ " at " ++ l.unparse);
  
  top.fullName = if top.found then top.dcl.fullName else "undeclared:value:" ++ name;
  
  top.typeScheme = if top.found then top.dcl.typeScheme else monoType(errorType());
  
  top.errors := 
    (if top.found then []
     else [err(l, "Undeclared " ++ kindOfLookup ++ " '" ++ name ++ "'.")]) ++
    (if length(top.dcls) <= 1 then []
     else [err(l, "Ambiguous reference to " ++ kindOfLookup ++ " '" ++ name ++ "'. Possibilities are:\n" ++ printPossibilities(top.dcls))]);
}

abstract production errorLookup
top::QNameLookup<a> ::= msg::[Message]
{
  top.dcls = [];
  top.found = true;
  top.dcl = error("dcl demanded from errorLookup");
  top.fullName = "err";
  top.typeScheme = monoType(errorType());
  top.errors := msg;
}

function printPossibilities
attribute fullName {} occurs on a,
annotation sourceLocation occurs on a =>
String ::= lst::[a]
{
  return implode("\n", map(dclinfo2possibility, lst));
}
function dclinfo2possibility
attribute fullName {} occurs on a,
annotation sourceLocation occurs on a =>
String ::= dcl::a
{
  -- TODO: perhaps some way of including types, when they are relevant (attributes, values)
  return "\t" ++ dcl.fullName ++ " (" ++ dcl.sourceLocation.filename ++ ":" ++ toString(dcl.sourceLocation.line) ++ ")";
}


---- Right now, this is only used for types and type classes:
attribute lookupType occurs on QNameType;

concrete production qNameTypeId
top::QNameType ::= id::IdUpper_t
{
  top.name = id.lexeme;
  top.unparse = id.lexeme;
  top.baseNameLoc = id.location;
  
  top.lookupType = customLookup("type", getTypeDcl(top.name, top.env), top.name, top.location);
}

concrete production qNameTypeCons
top::QNameType ::= id::Name ':' qn::QNameType
{
  top.name = id.name ++ ":" ++ qn.name;
  top.baseNameLoc = qn.baseNameLoc;
  top.unparse = id.unparse ++ ":" ++ qn.unparse;
  
  top.lookupType = customLookup("type", getTypeDcl(top.name, top.env), top.name, top.location);
} action {
  insert semantic token IdGrammarName_t at id.location;
}

{--
 - Qualified name looked up CONTEXTUALLY
 -}
nonterminal QNameAttrOccur with config, name, location, grammarName, env, unparse, attrFor, errors, typerep, dcl<OccursDclInfo>, attrDcl, found, attrFound;

flowtype QNameAttrOccur = decorate {grammarName, config, env, attrFor}, dcl {grammarName, env, attrFor}, attrDcl {grammarName, env, attrFor};

{--
 - For QNameAttrOccur, the name of the LHS to look up this attribute on.
 - i.e. 
 -}
inherited attribute attrFor :: Type;
synthesized attribute attrDcl :: AttributeDclInfo;

{--
 - Whether lookup was successful. Better than `null(_.errors)` because errors may be suppressed
 -}
synthesized attribute found :: Boolean;

{--
 - Whether we found the *attribute*.  Sometimes we still want to know this even when the nonterminal was an error
 -}
synthesized attribute attrFound :: Boolean;

{--
 - Used like `x.<this>`.
 - @param  at       the name of an attribute
 - @inh    attrFor  the type this attribute should be on
 -}
concrete production qNameAttrOccur
top::QNameAttrOccur ::= at::QName
{
  top.name = at.name;
  top.unparse = at.unparse;
  propagate env;
  
  local attrs :: [AttributeDclInfo] =
    if top.attrFor.isError
    then at.lookupAttribute.dcls
    else getOccuringAttrDcl(top.attrFor.typeName, at.name, top.env);

  local dcls :: [OccursDclInfo] =
    case attrs of
    | attr :: _ -> getOccursDcl(attr.fullName, top.attrFor.typeName, top.env)
    | _ -> []
    end;
  
  -- This basically has to mirror the logic in errors below!
  top.found = at.lookupAttribute.found && !top.attrFor.isError && !null(dcls) && length(attrs) == 1;
  
  top.attrFound = at.lookupAttribute.found;
  
  top.errors :=
    -- If we fail to look up the attribute, just report that.
    if !at.lookupAttribute.found then
      at.lookupAttribute.errors
    -- If we're looking up an attribute on `errorType`, an error is already raised, don't create noise
    else if top.attrFor.isError then
      []
    -- If no attribute occurs on this type, raise that error
    else if null(dcls) then
      -- This is a heuristic error message for the situation where you have a type, but haven't imported
      -- the grammar declaring that type.
      (if lastIndexOf(":", top.attrFor.typeName) > 0 && null(getTypeDcl(top.attrFor.typeName, top.env)) then
         [err(at.location, "Attribute '" ++ at.name ++ "' does not occur on '" ++ prettyType(top.attrFor) ++ "'. Perhaps import '" ++ substring(0, lastIndexOf(":", top.attrFor.typeName), top.attrFor.typeName)  ++ "'?")]
       else
         [err(at.location, "Attribute '" ++ at.name ++ "' does not occur on '" ++ prettyType(top.attrFor) ++ "'. Looked at:\n" ++ printPossibilities(at.lookupAttribute.dcls))]
      )
    -- If more than one attribute on the same _short name_ occurs, raise ambiguity
    else if length(attrs) > 1 then
      [err(at.location, "Ambiguous reference to attribute occurring on '" ++ prettyType(top.attrFor) ++ "'. Possibilities are:\n" ++ printPossibilities(attrs))]
    -- If this same attribute has multiple occurences (must be due to orphaned occurs)
    else []; {-if length(dcls) > 1 then
      [err(at.location, "There are erroneously multiple attribute occurrences for '" ++ at.name ++ "'. Possibilities are:\n" ++ printPossibilities(dcls))]
    else [];-}
    -- TODO: This last bit is disabled because we have problems with importing grammars multiple times.
    -- TODO FIXME: enable this, and fix the grammar import issues!

  production resolvedDcl::OccursDclInfo = if top.found then head(dcls) else
    error("INTERNAL ERROR: Accessing dcl of occurrence " ++ at.name ++ " at " ++ top.grammarName ++ " " ++ top.location.unparse);
  resolvedDcl.givenNonterminalType = top.attrFor;
  production resolvedTypeScheme::PolyType = resolvedDcl.typeScheme;
  production requiredContexts::Contexts = foldContexts(resolvedTypeScheme.contexts);
  requiredContexts.env = top.env;
  
  top.typerep = if top.found then determineAttributeType(head(dcls), top.attrFor) else errorType();
  top.dcl = resolvedDcl;
  top.attrDcl = if top.found then head(attrs) else
    -- Workaround fix for proper error reporting - appairently there are some places where this is still demanded.
    if at.lookupAttribute.found then at.lookupAttribute.dcl else
    error("INTERNAL ERROR: Accessing dcl of attribute " ++ at.name ++ " at " ++ top.grammarName ++ " " ++ top.location.unparse);
}

{--
 - `at` is a list of attribute declarations
 - `occ` is a mapped list of occurrence declarations for the corresponding attribute
 - we return only those `at` which have a non-empty element in `occ`
 -}
function zipFilterDcls
[AttributeDclInfo] ::= at::[AttributeDclInfo]  occ::[[OccursDclInfo]]
{
  return if null(at) then []
  else if null(head(occ)) then zipFilterDcls(tail(at), tail(occ))
  else head(at) :: zipFilterDcls(tail(at), tail(occ));
}
