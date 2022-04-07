grammar silver:util:hashmap;

type HMap foreign = "java.util.HashMap<String,common.ConsCell>";


@{--
 - Returns a new, Hmap (String as keys, String List as values) filled in
 - with entries according to the csv string passed in.
 -}
function fromCSVString
HMap ::= csvStr::String
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawHashMap.fromCSVString(%csvStr%)";
}

function lookupList
[String] ::= map::HMap key::String
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawHashMap.lookupList(%map%,%key%)";
}
