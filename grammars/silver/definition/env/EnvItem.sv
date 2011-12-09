grammar silver:definition:env;

import silver:definition:type only Substitution;
import silver:util only contains;

--fullNameToShort  String ::= s::String
--defaultEnvItem   Decorate EnvItem ::= di::Decorated DclInfo
--renamedEnvItem   Decorate EnvItem ::= newname::String di::Decorated DclInfo
--mapGetDcls       [Decorated DclInfo] ::= i::[Decorated EnvItem]
--mapDefaultWrapDcls [Decorated EnvItem] ::= i::[Decorated DclInfo]
--mapFullnameDcls  [Decorated EnvItem] ::= i::[Decorated DclInfo]
--sortEnvItems     [Decorated EnvItem] ::= eis::[Decorated EnvItem]


synthesized attribute itemName :: String;
synthesized attribute dcl :: Decorated DclInfo;

nonterminal EnvItem with itemName, dcl;

function fullNameToShort
String ::= s::String
{
  -- Works just fine, even when lastIndexOf returns -1
  return substring(lastIndexOf(":", s) + 1, length(s), s);
}

function defaultEnvItem
Decorated EnvItem ::= di::Decorated DclInfo
{
  return decorate i_envItem(fullNameToShort(di.fullName), di) with {};
}

function renamedEnvItem
Decorated EnvItem ::= newname::String di::Decorated DclInfo
{
  return decorate i_envItem(newname, di) with {};
}

function fullNameEnvItem
Decorated EnvItem ::= di::Decorated DclInfo
{
  return decorate i_envItem(di.fullName, di) with {};
}

abstract production i_envItem
top::EnvItem ::= short::String di::Decorated DclInfo
{
  top.itemName = short;
  top.dcl = di;
}

function mapGetDcls
[Decorated DclInfo] ::= i::[Decorated EnvItem]
{
  return if null(i) then []
         else head(i).dcl :: mapGetDcls(tail(i));
}

function mapFullnameDcls
[Decorated EnvItem] ::= i::[Decorated DclInfo]
{
  return if null(i) then []
         else fullNameEnvItem(head(i)) :: mapFullnameDcls(tail(i));
}

function mapDefaultWrapDcls
[Decorated EnvItem] ::= i::[Decorated DclInfo]
{
  return if null(i) then []
         else defaultEnvItem(head(i)) :: mapDefaultWrapDcls(tail(i));
}


function filterEnvItemsExclude
[Decorated EnvItem] ::= items::[Decorated EnvItem] exclude::[String]
{
  return if null(items) then []
         else if contains(head(items).itemName, exclude)
              then filterEnvItemsExclude(tail(items), exclude)
              else head(items) :: filterEnvItemsExclude(tail(items), exclude);
}

function filterEnvItemsInclude
[Decorated EnvItem] ::= items::[Decorated EnvItem] include::[String]
{
  return if null(items) then []
         else if contains(head(items).itemName, include)
              then head(items) :: filterEnvItemsInclude(tail(items), include)
              else filterEnvItemsInclude(tail(items), include);
}

function mapPrependEnvItems
[Decorated EnvItem] ::= items::[Decorated EnvItem] prefi::String
{
  return if null(items) then []
         else renamedEnvItem(prefi ++ head(items).itemName, head(items).dcl) :: mapPrependEnvItems(tail(items), prefi);
}

function mapRenameEnvItems
[Decorated EnvItem] ::= items::[Decorated EnvItem] renames::[Pair<String String>]
{
  local attribute result :: Maybe<String>;
  result = lookupBy(stringEq, head(items).itemName, renames);
  
  return if null(items) then []
         else if !result.isJust
              then head(items) :: mapRenameEnvItems(tail(items), renames)
              else renamedEnvItem(result.fromJust, head(items).dcl) :: mapRenameEnvItems(tail(items), renames);
}

-- Sort function
function sortEnvItems
[Decorated EnvItem] ::= eis::[Decorated EnvItem]
{
  return sortBy(envItemLTE, eis);
}
function groupEnvItems
[[Decorated EnvItem]] ::= eis::[Decorated EnvItem]
{
  return groupBy(envItemEQ, eis);
}
function envItemLTE
Boolean ::= e1::Decorated EnvItem e2::Decorated EnvItem
{
  return e1.itemName <= e2.itemName;
}
function envItemEQ
Boolean ::= e1::Decorated EnvItem e2::Decorated EnvItem
{
  return e1.itemName == e2.itemName;
}

-- Substitutions

function performSubstitutionEnvItem
[Decorated EnvItem] ::= e::[Decorated EnvItem] s::Substitution
{
  return if null(e) then []
         else decorate i_envItem(head(e).itemName, performSubstitutionDclInfo(head(e).dcl, s)) with {}
              :: performSubstitutionEnvItem(tail(e), s);
}


