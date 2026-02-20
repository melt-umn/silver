grammar silver:util:idcache;

@@{-
 - A utility for mapping comparable objects to unique integer ids.
 -}

type IdCache<a> foreign = "java.util.TreeMap<Object,Integer>";

@{--
 - Returns a new, empty, id cache using Ord for comparison.
 -}
fun empty Ord a => IdCache<a> ::=  = emptyWith(compare);

@{--
 - Returns a new, empty, id cache using the specified comparator.
 -}
function emptyWith
IdCache<a> ::= comparator::(Integer ::= a a)
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawIdCache.empty(%comparator%)";
}

@{--
 - Lookup a key from the id cache, generating a new id if not present.
 -}
function lookup
Integer ::= key::a cache::IdCache<a>
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawIdCache.lookup(%key%, %cache%)";
}
