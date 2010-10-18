grammar silver:definition:env;

import silver:definition:type;

import silver:util;

--fullNameToShort  String ::= s::String
--defaultEnvItem   Decorate EnvItem ::= di::Decorated DclInfo
--renamedEnvItem   Decorate EnvItem ::= newname::String di::Decorated DclInfo
--mapGetDcls       [Decorated DclInfo] ::= i::[Decorated EnvItem]
--mapDefaultWrapDcls [Decorated EnvItem] ::= i::[Decorated DclInfo]
--mapFullnameDcls  [Decorated EnvItem] ::= i::[Decorated DclInfo]
--sortEnvItems     [Decorated EnvItem] ::= eis::[Decorated EnvItem]


nonterminal EnvItem with itemName, dcl;

synthesized attribute itemName :: String;
synthesized attribute dcl :: Decorated DclInfo;

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
[Decorated EnvItem] ::= items::[Decorated EnvItem] renames::[[String]]
{
  local attribute result :: [String];
  result = lookupRename(head(items).itemName, renames);
  
  return if null(items) then []
         else if null(result)
              then head(items) :: mapRenameEnvItems(tail(items), renames)
              else renamedEnvItem(head(result), head(items).dcl) :: mapRenameEnvItems(tail(items), renames);
}

function lookupRename
[String] ::= v::String lst::[[String]]
{
  return if null(lst) then []
         else if v == head(head(lst))
              then [head(tail(head(lst)))]
              else lookupRename(v, tail(lst));
}

-- Sort function
function sortEnvItems
[Decorated EnvItem] ::= eis::[Decorated EnvItem]
{
  return sortEnvItemsHelp(eis, length(eis));
}

function sortEnvItemsHelp
[Decorated EnvItem] ::= eis::[Decorated EnvItem] upTo::Integer
{
  return if upTo == 0 then []
         else if upTo == 1 then [head(eis)]
         else mergeEnvItems(front_half,back_half);

  local attribute front_half :: [Decorated EnvItem] ;
  front_half = sortEnvItemsHelp(eis, middle) ;

  local attribute back_half :: [Decorated EnvItem] ;
  back_half = sortEnvItemsHelp(dropEnvItems(middle, eis), upTo - middle) ;

  local attribute middle :: Integer ;
  middle = toInt(toFloat(upTo) / 2.0) ;
}

function mergeEnvItems
[Decorated EnvItem] ::= l1::[Decorated EnvItem] l2::[Decorated EnvItem]
{
  return if null(l1) 
         then l2

         else if null(l2) 
         then l1

         else if head(l1).itemName < head(l2).itemName
         then head(l1) :: mergeEnvItems(tail(l1),l2)

         else head(l2) :: mergeEnvItems(l1,tail(l2)) ;
}

function dropEnvItems
[Decorated EnvItem] ::= n::Integer l::[Decorated EnvItem]
{
  return if n <= 0 then l else dropEnvItems(n-1, tail(l));
}

-- Substitutions

function performSubstitutionEnvItem
[Decorated EnvItem] ::= e::[Decorated EnvItem] s::Substitution
{
  return if null(e) then []
         else decorate i_envItem(head(e).itemName, performSubstitutionDclInfo(head(e).dcl, s)) with {}
              :: performSubstitutionEnvItem(tail(e), s);
}


