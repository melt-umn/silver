grammar xrobots:abstractsyntax;


function mk_error
[String] ::= s::String r::Integer c::Integer
{
 return ["Error: (line: " ++ toString(r) ++
	 ", column: " ++ toString(c) ++ "): " ++ s ++ "\n"];
}

function print_errors
String ::= sl ::[String]
{
 return
  if null(sl)
  then ""
  else head(sl) ++ "\n";
}