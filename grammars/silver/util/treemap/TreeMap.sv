grammar silver:util:treemap;

@@{-
   - One should always import this via 'import silver:util:treemap as ...'
   - The names are too general otherwise.
   -}

type Map<a b> foreign = "java.util.TreeMap<Object,common.ConsCell>";

@{--
 - Returns a new, empty, multimap using Ord for comparison.
 -}
function empty
Ord a => Map<a b> ::=
{
  return emptyWith(compare);
}

@{--
 - Returns a new, empty, multimap using the specified comparator.
 -}
function emptyWith
Map<a b> ::= comparator::(Integer ::= a a)
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawTreeMap.empty(%comparator%)";
}

@@{- @warning An 'insert' function is deliberating omitted due to its inefficiency, but there's add: -}

@{--
 - Adds a list of elements to a map.
 -}
function add
Map<a b> ::= lst::[Pair<a b>] mp::Map<a b>
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawTreeMap.addList(%lst%, %mp%)";
}

@{--
 - Returns a list of keys that are present in the map, in sorted order.
 -}
function keys
[a] ::= mp::Map<a b>
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawTreeMap.keys(%mp%)";
}

@{--
 - Looks up an element from the map, empty list if not contained.
 -}
function lookup
[b] ::= key::a mp::Map<a b>
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawTreeMap.lookup(%key%, %mp%)";
}


-- @{--
--  - Returns a new, multimap (String as keys, String List as values) filled in
--  - with entries according to the csv string passed in.
--  -}
-- function fromCSVString
-- Map<String String> ::= csvStr::String comparator::(Integer ::= a a)
-- {
--   return error("NYI");
-- } foreign {
--   "java" : return "common.rawlib.RawTreeMap.fromCSVString(%csvStr%, %comparator%)";
-- }

@{--
 - Converts a multimap back to a list of pairs, in sorted order by key.
 -}
function toList
[Pair<a b>] ::= mp::Map<a b>
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawTreeMap.toList(%mp%)";
}

@{--
 - Updates the value returned by a key
 -}
function update
Map<a b> ::= key::a  value::[b]  mp::Map<a b>
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawTreeMap.update(%key%, %value%, %mp%)";
}

