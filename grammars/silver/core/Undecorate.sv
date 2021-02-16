grammar silver:core;

function new
a ::= x::Decorated a with i
{ return error("foreign function"); }
foreign {
  "java": return "%x%.undecorate()";
}
