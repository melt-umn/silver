grammar silver:core;

@{-
  - Undecorate a reference.
  -
  - @param x  The reference to undecorate.
  - @return A new, undecorated term coresponding to x.
  -}
function new
a ::= x::Decorated a with i
{ return error("foreign function"); }
foreign {
  "java": return "common.OriginsUtil.duplicatePoly(%x%.undecorate(), originCtx)";
}

@{-
  - Type-safe cast of one decorated type into one with a smaller reference set.
  -
  - @param x  The reference to cast.
  - @return x, with a type that has a different reference set.
  -}
function castRef
i1 subset i2 => Decorated a with i1 ::= x::Decorated a with i2
{ return error("foreign function"); }
foreign {
  "java": return "%x%";
}

@{-
  - Given a decorated tree, return the underlying term that was decorated to create it,
  - without copying or applying any transformations.
  - This is unsafe as it constitutes a hole in the uniqueness analysis, and should not
  - be used outside of debugging and internally within Silver in the implementation of
  - annotation accesses; use new if you want to undecorate a term.
  -
  - @param x  The reference to undecorate.
  - @return The term that was decorated to produce x.
  -}
function getTermThatWasDecorated
a ::= x::Decorated a with i
{ return error("foreign function"); }
foreign {
  "java": return "%x%.getNode()";
}
