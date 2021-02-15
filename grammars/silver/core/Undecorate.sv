grammar silver:core;

function new
a ::= x::Decorated a
{ return error("foreign function"); }
foreign {
  "java": return "%x%.undecorate()";
}
