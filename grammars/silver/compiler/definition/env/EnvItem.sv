grammar silver:compiler:definition:env;

{--
 - An entry in the environment.
 -}
nonterminal EnvItem with itemName, dcl, envContribs;

synthesized attribute itemName :: String;
synthesized attribute dcl :: DclInfo;
synthesized attribute envContribs :: [Pair<String DclInfo>];

{--
 - Rare case: use of `import _ with _ as _` or `import _ as _` to rename.
 - Common case: `grammar:full:name` aka `name`. See `defaultEnvItem`.
 -}
abstract production renamedEnvItem
ei::EnvItem ::= newname::String di::DclInfo
{
  ei.itemName = newname;
  ei.dcl = di;
  ei.envContribs =
    if newname != di.fullName
    then [pair(newname, di), pair(di.fullName, di)]
    else [pair(newname, di)];
}
{--
 - Entries at fullname ONLY.
 - Used for occurrences & production attributes, which are looked up
 - by the full nonterminal name (or production name) only, and a shortname is nonsense.
 -}
abstract production fullNameEnvItem
ei::EnvItem ::= di::DclInfo
{
  ei.itemName = di.fullName;
  ei.dcl = di;
  ei.envContribs = [pair(di.fullName, di)];
}
{--
 - Used for aspect local variables. The LHS and children have a full name
 - like `newname` and in the aspect we can rename it anything we want.
 - We should *not* see `newname` in the environment in those cases.
 -}
abstract production onlyRenamedEnvItem
ei::EnvItem ::= newname::String di::DclInfo
{
  ei.itemName = newname;
  ei.dcl = di;
  ei.envContribs = [pair(newname, di)];
}

{--
 - The common case, normal shortnames.
 -}
function defaultEnvItem
EnvItem ::= di::DclInfo
{
  return renamedEnvItem(fullNameToShort(di.fullName), di);
}
function fullNameToShort
String ::= s::String
{
  -- Works just fine, even when lastIndexOf returns -1
  return substring(lastIndexOf(":", s) + 1, length(s), s);
}



function mapGetDcls
[DclInfo] ::= i::[EnvItem]
{
  return map((.dcl), i);
}

function mapFullnameDcls
[EnvItem] ::= i::[DclInfo]
{
  return map(fullNameEnvItem, i);
}

function mapDefaultWrapDcls
[EnvItem] ::= i::[DclInfo]
{
  return map(defaultEnvItem, i);
}

function envItemExclude
Boolean ::= ei::EnvItem  exclude::[String]
{
  return !contains(ei.itemName, exclude);
}
function envItemInclude
Boolean ::= ei::EnvItem  include::[String]
{
  return contains(ei.itemName, include);
}
function envItemPrepend
EnvItem ::= ei::EnvItem  pfx::String
{
  -- This clobbers 'onlyRenamed' but that's okay because this is only used
  -- by imports, where that doesn't appear.
  return renamedEnvItem(pfx ++ ei.itemName, ei.dcl);
}
function envItemApplyRenaming
EnvItem ::= ei::EnvItem  renames::[Pair<String String>]
{
  local result :: Maybe<String> = lookup(ei.itemName, renames);
  
  return if !result.isJust then ei
         -- this would clobber any 'onlyrenamed' but those shouldn't appear in imports, where this is used.
         else renamedEnvItem(result.fromJust, ei.dcl);
}


{--
 - Maps a production's DclInfo into an EnvItem named for the nonterminal it constructs.
 -}
function envItemNTFromProdDcl
EnvItem ::= di::DclInfo
{
  -- loooking up the full name of the nonterminal it creates will resolve this prodDcl
  return onlyRenamedEnvItem(di.namedSignature.outputElement.typerep.typeName, di);
}

