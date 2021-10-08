grammar silver:compiler:definition:env;

{--
 - An entry in the environment.
 -}
nonterminal EnvItem<a> with itemName, dcl<a>, envContribs<a>;

synthesized attribute itemName :: String;
synthesized attribute dcl<a> :: a;
synthesized attribute envContribs<a> :: [Pair<String a>];

{--
 - Rare case: use of `import _ with _ as _` or `import _ as _` to rename.
 - Common case: `grammar:full:name` aka `name`. See `defaultEnvItem`.
 -}
abstract production renamedEnvItem
attribute fullName {} occurs on a =>
ei::EnvItem<a> ::= newname::String di::a
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
attribute fullName {} occurs on a =>
ei::EnvItem<a> ::= di::a
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
ei::EnvItem<a> ::= newname::String di::a
{
  ei.itemName = newname;
  ei.dcl = di;
  ei.envContribs = [pair(newname, di)];
}

{--
 - The common case, normal shortnames.
 -}
function defaultEnvItem
attribute fullName {} occurs on a =>
EnvItem<a> ::= di::a
{
  return renamedEnvItem(fullNameToShort(di.fullName), di);
}
function fullNameToShort
String ::= s::String
{
  -- Works just fine, even when lastIndexOf returns -1
  return substring(lastIndexOf(":", s) + 1, length(s), s);
}



global mapGetDcls :: ([a] ::= [EnvItem<a>]) = map((.dcl), _);
global mapFullnameDcls :: attribute fullName {} occurs on a => ([EnvItem<a>] ::= [a]) =
  map(fullNameEnvItem, _);
global mapDefaultWrapDcls :: attribute fullName {} occurs on a => ([EnvItem<a>] ::= [a]) =
  map(defaultEnvItem, _);

function envItemExclude
Boolean ::= ei::EnvItem<a>  exclude::[String]
{
  return !contains(ei.itemName, exclude);
}
function envItemInclude
Boolean ::= ei::EnvItem<a>  include::[String]
{
  return contains(ei.itemName, include);
}
function envItemPrepend
attribute fullName {} occurs on a =>
EnvItem<a> ::= ei::EnvItem<a>  pfx::String
{
  -- This clobbers 'onlyRenamed' but that's okay because this is only used
  -- by imports, where that doesn't appear.
  return renamedEnvItem(pfx ++ ei.itemName, ei.dcl);
}
function envItemApplyRenaming
attribute fullName {} occurs on a =>
EnvItem<a> ::= ei::EnvItem<a>  renames::[Pair<String String>]
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
EnvItem<DclInfo> ::= di::DclInfo
{
  -- loooking up the full name of the nonterminal it creates will resolve this prodDcl
  return onlyRenamedEnvItem(di.namedSignature.outputElement.typerep.typeName, di);
}

