function matches
Boolean ::= regex::String str::String
{
  return error("Not Yet Implemented: matches");
} foreign {
  "java" : return "Boolean.valueOf(%str%.toString().matches(%regex%.toString()))";
}
