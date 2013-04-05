grammar silver:util:raw:treeset;

-- One should always import this via 'import silver:util:raw:treeset as ...'
-- The names are too general otherwise.

type Set<a> foreign;

{--
 - Returns a new, empty, set using the specified comparator.
 -}
function empty
Set<a> ::= comparator::(Integer ::= a a)
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawTreeSet.empty(%comparator%)";
}

-- an 'insert' function is deliberating omitted due to its inefficiency, but there's add:

{--
 - Adds a list of elements to a set.
 -}
function add
Set<a> ::= lst::[a] set::Set<a>
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawTreeSet.addList(%lst%, (java.util.TreeSet<Object>)%set%)";
}

{--
 - Converts a set back to a list, in sorted order.
 -}
function toList
[a] ::= set::Set<a>
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawTreeSet.toList((java.util.TreeSet<Object>)%set%)";
}

{--
 - Computes the union of the two sets.
 -}
function union
Set<a> ::= l::Set<a> r::Set<a>
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawTreeSet.union((java.util.TreeSet<Object>)%l%,(java.util.TreeSet<Object>)%r%)";
}

{--
 - Computes the intersection of the two sets.
 -}
function intersect
Set<a> ::= l::Set<a> r::Set<a>
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawTreeSet.intersect((java.util.TreeSet<Object>)%l%,(java.util.TreeSet<Object>)%r%)";
}

{--
 - Computes the difference of the two sets. (l - r)
 -}
function difference
Set<a> ::= l::Set<a> r::Set<a>
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawTreeSet.difference((java.util.TreeSet<Object>)%l%,(java.util.TreeSet<Object>)%r%)";
}

{--
 - Determines if the element e is in the set.
 -}
function contains
Boolean ::= e::a set::Set<a>
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawTreeSet.contains(%e%,(java.util.TreeSet<Object>)%set%)";
}

{--
 - Determines if all of the elements in e are in the set.
 -}
function containsAll
Boolean ::= e::[a] set::Set<a>
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawTreeSet.containsAll(%e%,(java.util.TreeSet<Object>)%set%)";
}

{--
 - Determines if l is a subset of r.
 -}
function subset
Boolean ::= l::Set<a> r::Set<a>
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawTreeSet.subset((java.util.TreeSet<Object>)%l%,(java.util.TreeSet<Object>)%r%)";
}

{--
 - Determines if the sets are equal.
 -}
function equals
Boolean ::= l::Set<a> r::Set<a>
{
  return subset(l,r) && subset(r,l);
}


