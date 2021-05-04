grammar silver:core;

function new
a ::= x::Decorated a with i
{ return error("foreign function"); }
foreign {
  "java": return "common.OriginsUtil.duplicatePoly(%x%.undecorate(), originCtx)";
}

function partialUndecorate
i1 subset i2 => Decorated a with i1 ::= x::Decorated a with i2
{ return error("foreign function"); }
foreign {
  "java": return "%x%";
}
