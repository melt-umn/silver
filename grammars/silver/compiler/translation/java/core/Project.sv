grammar silver:compiler:translation:java:core;

imports silver:compiler:translation:java:type;

imports silver:compiler:definition:core;
imports silver:compiler:definition:type:syntax;

imports silver:compiler:definition:env;
imports silver:compiler:definition:type;
imports silver:compiler:definition:flow:env;

imports silver:compiler:analysis:uniqueness;
imports silver:compiler:analysis:typechecking:core only finalType;

fun makeName String ::= str::String = substitute(":", ".", str);
fun makeIdName String ::= str::String = substitute(":", "_", str);

fun makeProdName String ::= s::String = substituteLast(".", ".P", makeName(s));

fun makeNTName String ::= s::String = substituteLast(".", ".N", makeName(s));

fun makeAnnoName String ::= s::String = substituteLast(".", ".A", makeName(s));

fun makeTerminalName String ::= s::String = substituteLast(".", ".T", makeName(s));

fun makeParserName String ::= s::String = "Parser_" ++ makeIdName(s);

fun makeClassName String ::= s::String = substituteLast(".", ".C", makeName(s));

fun makeInstanceName String ::= g::String s::String t::Type =
  substituteLast(".", ".I", makeName(g ++ ":" ++ substitute(":", "_", s))) ++ "_" ++ transTypeName(t);

function substituteLast
String ::= r::String s::String str::String
{
  local attribute i::Integer;
  i = lastIndexOf(r, str);
  
  return if i == -1 then str
         else substring(0,i,str) ++ s ++ substring(i+length(r), length(str), str);
}

