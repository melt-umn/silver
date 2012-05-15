grammar silver:definition:env;

import silver:definition:type only Substitution;
import silver:util only contains;

--fullNameToShort  String ::= s::String
--defaultEnvItem   Decorate EnvItem ::= di::DclInfo
--renamedEnvItem   Decorate EnvItem ::= newname::String di::DclInfo
--mapGetDcls       [DclInfo] ::= i::[Decorated EnvItem]
--mapDefaultWrapDcls [Decorated EnvItem] ::= i::[DclInfo]
--mapFullnameDcls  [Decorated EnvItem] ::= i::[DclInfo]
--sortEnvItems     [Decorated EnvItem] ::= eis::[Decorated EnvItem]


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


function filterEnvItemsExclude
[EnvItem] ::= items::[EnvItem] exclude::[String]
{
  return if null(items) then []
         else if contains(head(items).itemName, exclude)
              then filterEnvItemsExclude(tail(items), exclude)
              else head(items) :: filterEnvItemsExclude(tail(items), exclude);
}

function filterEnvItemsInclude
[EnvItem] ::= items::[EnvItem] include::[String]
{
  return if null(items) then []
         else if contains(head(items).itemName, include)
              then head(items) :: filterEnvItemsInclude(tail(items), include)
              else filterEnvItemsInclude(tail(items), include);
}

function mapPrependEnvItems
[EnvItem] ::= items::[EnvItem] prefi::String
{
  return if null(items) then []
         -- this should clobber any 'onlyrenamed' but those shouldn't appear in imports, where this is used.
         else renamedEnvItem(prefi ++ head(items).itemName, head(items).dcl) :: mapPrependEnvItems(tail(items), prefi);
}

function mapRenameEnvItems
[EnvItem] ::= items::[EnvItem] renames::[Pair<String String>]
{
  local attribute result :: Maybe<String>;
  result = lookupBy(stringEq, head(items).itemName, renames);
  
  return if null(items) then []
         else if !result.isJust
              then head(items) :: mapRenameEnvItems(tail(items), renames)
              -- this should clobber any 'onlyrenamed' but those shouldn't appear in imports, where this is used.
              else renamedEnvItem(result.fromJust, head(items).dcl) :: mapRenameEnvItems(tail(items), renames);
}

-- Sort function
function sortEnvItems
[EnvItem] ::= eis::[EnvItem]
{
  return sortBy(envItemLTE, eis);
}
function groupEnvItems
[[EnvItem]] ::= eis::[EnvItem]
{
  return groupBy(envItemEQ, eis);
}
function envItemLTE
Boolean ::= e1::EnvItem e2::EnvItem
{
  return e1.itemName <= e2.itemName;
}
function envItemEQ
Boolean ::= e1::EnvItem e2::EnvItem
{
  return e1.itemName == e2.itemName;
}

-- Substitutions

function performSubstitutionEnvItem
[EnvItem] ::= e::[EnvItem] s::Substitution
{
  return if null(e) then []
         -- this should clobber any 'onlyrenamed' but those shouldn't appear in production attributes, where this is used.
         else renamedEnvItem(head(e).itemName, performSubstitutionDclInfo(head(e).dcl, s))
              :: performSubstitutionEnvItem(tail(e), s);
}


