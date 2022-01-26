grammar silver:compiler:definition:concrete_syntax:copper;

-- edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.CopperElementReference
type ElementReference foreign;

function elementReference
ElementReference ::= location::Location  grammarName::String  name::String
{
  return error("copper FFI function");
} foreign {
  "java" : return "common.CopperUtil.makeElementReference(%location%, %grammarName%.toString(), %name%.toString())";
}
