grammar silver:util:raw:treemap;

-- One should always import this via 'import silver:util:raw:treemap as ...'
-- The names are too general otherwise.

type Map<a b> foreign;

{--
 - Returns a new, empty, multimap using the specified comparator.
 -}
function empty
Map<a b> ::= comparator::(Integer ::= a a)
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawTreeMap.empty(%comparator%)";
}

-- an 'insert' function is deliberating omitted due to its inefficiency, but there's add:

{--
 - Adds a list of elements to a map.
 -}
function add
Map<a b> ::= lst::[Pair<a b>] mp::Map<a b>
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawTreeMap.addList(%lst%, (java.util.TreeMap<Object,common.ConsCell>)%mp%)";
}

{--
 - Looks up an element from the map, empty list if not contained.
 -}
function lookup
[b] ::= key::a mp::Map<a b>
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawTreeMap.lookup(%key%, (java.util.TreeMap<Object,common.ConsCell>)%mp%)";
}

{--
 - Converts a multimap back to a list of pairs, in sorted order by key.
 -}
function toList
[Pair<a b>] ::= mp::Map<a b>
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawTreeMap.toList((java.util.TreeMap<Object, common.ConsCell>)%mp%)";
}

