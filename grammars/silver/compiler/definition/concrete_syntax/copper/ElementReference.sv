grammar silver:compiler:definition:concrete_syntax:copper;

-- edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.CopperElementReference
type ElementReference foreign;

-- We don't bother passing Silver Locations through, since all the relevant
-- checks Copper does should already have been performed.
function elementReference
ElementReference ::= grammarName::String  name::String
{
  return error("copper FFI function");
} foreign {
  "java" : return "common.CopperUtil.makeElementReference(%grammarName%.toString(), %name%.toString())";
}
