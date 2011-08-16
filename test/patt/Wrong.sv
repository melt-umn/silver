grammar patt;

wrongCode "1 is an Integer but we're trying to match against String" {
 function funfoo
 String ::= {
  return case "a string" of
           1 -> ""
         end;
 }
}

wrongCode "line: 16" {
 function funfoo
 String ::= {
  return case "a string" of
           1 -> ""
         end;
 }
}

wrongCode "1 is an Integer but we're trying to match against String" {
 function funfoo
 String ::= {
  return case "a string" of
         | "a" -> "2"
         | 1 -> ""
         end;
 }
}

