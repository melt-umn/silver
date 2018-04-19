grammar silver:extension:doc:core;

function nameToPath
String ::= name::String
{
  return nameToPathHelp(explode(":", name));
}

function nameToPathHelp
String ::= s::[String]
{
  return case s of
         | [] -> ""
         | x :: [] -> "#" ++ x
         | x1 :: x2 :: [] -> x1 ++ "#" ++ x2
         | x :: xs -> x ++ "/" ++ nameToPathHelp(xs)
         end;
}


