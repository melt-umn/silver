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

wrongCode "aValidProduction has type" {
 nonterminal AValidNonterminal;
 abstract production aValidProduction
 t::AValidNonterminal ::= {}

 function funfoo
 String ::= t::mistakenly_a_type_variable
 {
  return case t of
         | aValidProduction() -> "hi"
         | _ -> "result"
         end; 
 }
}
