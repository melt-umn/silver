grammar silver:definition:env;

import silver:util only contains;

--fullNameToShort  String ::= s::String
--defaultEnvItem   Decorate EnvItem ::= di::DclInfo
--renamedEnvItem   Decorate EnvItem ::= newname::String di::DclInfo
--mapGetDcls       [DclInfo] ::= i::[Decorated EnvItem]
--mapDefaultWrapDcls [Decorated EnvItem] ::= i::[DclInfo]
--mapFullnameDcls  [Decorated EnvItem] ::= i::[DclInfo]


synthesized attribute itemName :: String;
synthesized attribute dcl :: DclInfo;
synthesized attribute envContribs :: [Pair<String DclInfo>];

nonterminal EnvItem with itemName, dcl, envContribs;

function fullNameToShort
String ::= s::String
{
  -- Works just fine, even when lastIndexOf returns -1
  return substring(lastIndexOf(":", s) + 1, length(s), s);
}

function defaultEnvItem
EnvItem ::= di::DclInfo
{
  return renamedEnvItem(fullNameToShort(di.fullName), di);
}
abstract production renamedEnvItem
ei::EnvItem ::= newname::String di::DclInfo
{
  ei.itemName = newname;
  ei.dcl = di;
  ei.envContribs = if newname != di.fullName
                   then [pair(newname, di), pair(di.fullName, di)]
                   else [pair(newname, di)];
}
abstract production fullNameEnvItem
ei::EnvItem ::= di::DclInfo
{
  ei.itemName = di.fullName;
  ei.dcl = di;
  ei.envContribs = [pair(di.fullName, di)];
}
abstract production onlyRenamedEnvItem
ei::EnvItem ::= newname::String di::DclInfo
{
  ei.itemName = newname;
  ei.dcl = di;
  ei.envContribs = [pair(newname, di)];
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
  local attribute result :: Maybe<String>;
  result = lookupBy(stringEq, ei.itemName, renames);
  
  return if !result.isJust then ei
         -- this should clobber any 'onlyrenamed' but those shouldn't appear in imports, where this is used.
         else renamedEnvItem(result.fromJust, ei.dcl);
}


