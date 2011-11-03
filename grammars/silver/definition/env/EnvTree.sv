grammar silver:definition:env;

-- searchEnvTree   [Decorated DclInfo] ::= search::String et::EnvTree
-- buildTree       EnvTree ::= eis::[Decorated EnvItem]
-- collapseEnvTree [Decorated DclInfo] ::= et::EnvTree

import silver:util:raw:treemap as rtm;
--import silver:util:fixedmap as fm;

type EnvTree = rtm:Map<String Decorated DclInfo>;

function searchEnvTree
[Decorated DclInfo] ::= search::String et::EnvTree
{
  return rtm:lookup(search, et);
}

function collapseEnvTree
[Decorated DclInfo] ::= et::EnvTree
{
  -- preserve only full name env items.
  return map(myGetSnd, filter(isSameKey, rtm:toList(et)));
}
function myGetSnd
b ::= p::Pair<a b>
{ return p.snd; }

function buildTree
EnvTree ::= eis::[Decorated EnvItem]
{
  return directBuildTree( explodeEnvItems(eis) );
}

function directBuildTree
EnvTree ::= eis::[Pair<String Decorated DclInfo>]
{
  return rtm:add(eis, rtm:empty(compareString));
--  return fm:create( eis );
}


-- Utility functions

function isSameKey
Boolean ::= p::Pair<String Decorated DclInfo>
{ return p.fst == p.snd.fullName; }

-- Take (shortName, fullName) and turns it into [(shortName, fullName), (fullName, fullName)]
-- So lookups see both.
function explodeEnvItems
[Pair<String Decorated DclInfo>] ::= eis::[Decorated EnvItem]
{
  local attribute h :: Decorated EnvItem;
  h = head(eis);
  
  return if null(eis) then [] else
         pair(h.dcl.fullName, h.dcl) ::
           (if h.itemName == h.dcl.fullName
            then explodeEnvItems(tail(eis))
            else pair(h.itemName, h.dcl) :: explodeEnvItems(tail(eis)));
}

