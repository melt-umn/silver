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
  - Undecorate a partially-decorated reference.
  -
  - @param x  The reference to undecorate.
  - @return A new, undecorated term coresponding to x.
  -}
function newPartial
a ::= x::PartiallyDecorated a with i
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
