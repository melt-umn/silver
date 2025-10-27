grammar silver:util:treeset;

@@{-
   - One should always import this via 'import silver:util:treemap as ...'
   - The names are too general otherwise.
   -}

type Set<a> foreign = "java.util.TreeSet<Object>";

@{--
 - Returns a new, empty, set using Ord for comparison.
 -}
fun empty Ord a => Set<a> ::= = emptyWith(compare);

@{--
 - Returns a new, empty, set using the specified comparator.
 -}
function emptyWith
Set<a> ::= comparator::(Integer ::= a a)
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawTreeSet.empty(%comparator%)";
}

@@{- @warning An 'insert' function is deliberating omitted due to its inefficiency, but there's add: -}

@{--
 - Adds a list of elements to a set.
 -}
function add
Set<a> ::= lst::[a] set::Set<a>
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawTreeSet.addList(%lst%, %set%)";
}

@{--
 - Converts a list to a set.
 -}
fun fromList Ord a => Set<a> ::= lst::[a] = add(lst, empty());

@{--
 - Converts a set back to a list, in sorted order.
 -}
function toList
[a] ::= set::Set<a>
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawTreeSet.toList(%set%)";
}

@{--
 - Computes the union of the two sets.
 -}
function union
Set<a> ::= l::Set<a> r::Set<a>
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawTreeSet.union(%l%,%r%)";
}

@{--
 - Computes the intersection of the two sets.
 -}
function intersect
Set<a> ::= l::Set<a> r::Set<a>
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawTreeSet.intersect(%l%,%r%)";
}

@{--
 - Computes the difference of the two sets. (l - r)
 -}
function difference
Set<a> ::= l::Set<a> r::Set<a>
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawTreeSet.difference(%l%,%r%)";
}

@{--
 - Determines if the element e is in the set.
 -}
function contains
Boolean ::= e::a set::Set<a>
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawTreeSet.contains(%e%,%set%)";
}

@{--
 - Determines if all of the elements in e are in the set.
 -}
function containsAll
Boolean ::= e::[a] set::Set<a>
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawTreeSet.containsAll(%e%,%set%)";
}

@{--
 - Determines if l is a subset of r.
 -}
function subset
Boolean ::= l::Set<a> r::Set<a>
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawTreeSet.subset(%l%,%r%)";
}

@{--
 - Determines if a set is empty.
 -}
function isEmpty
Boolean ::= s::Set<a>
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawTreeSet.isEmpty(%s%)";
}

@{--
 - Determines the size of a set.
 -}
function size
Integer ::= s::Set<a>
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawTreeSet.size(%s%)";
}

@{--
 - Removes elements from a set, whenever 'f' return false.
 -}
function filter
Set<a> ::= f::(Boolean ::= a)  s::Set<a>
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawTreeSet.filter(%f%, %s%)";
}

@{--
 - Remove all elements from the set (returns set - lst)
 -}
function removeAll
Set<a> ::= lst::[a] set::Set<a>
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawTreeSet.removeAll(%lst%, %set%)";
}

instance Eq Set<a> {
  eq = \ l::Set<a> r::Set<a> -> subset(l,r) && subset(r,l);
}

instance Semigroup Set<a> {
  append = union;
}

instance Ord a => Monoid Set<a> {
  mempty = empty();
}
