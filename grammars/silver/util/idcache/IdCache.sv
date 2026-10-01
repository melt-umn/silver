grammar silver:util:idcache;

@@{-
 - A utility for mapping strings to unique integer ids.
 -}

type IdCache foreign = "java.util.HashMap<String,Integer>";

@{--
 - Returns a new, empty, id cache.
 -}
function empty
IdCache ::=
{
  return error("NYI");
} foreign {
  "java" : return "new java.util.HashMap<String,Integer>()";
}

@{--
 - Lookup a key from the id cache, generating a new id if not present.
 -}
function lookup
Integer ::= key::String cache::IdCache
{
  return error("NYI");
} foreign {
  "java" : return "%cache%.computeIfAbsent(%key%.toString(), k -> %cache%.size())";
}
