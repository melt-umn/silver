grammar patt;

wrongCode "1 is an Integer but we're trying to match against String" {
 function funfoo
 String ::= {
  return case "a string" of
           1 -> ""
         end;
 }
}

-- gets the line number right
wrongCode "Wrong.sv:17" {
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

-- Pattern MUST NOT be a production #HACK2012
wrongCode "Production name can't be used in pattern" {
 nonterminal TotallyValidNonterminal;
 abstract production empty_TVN
 tvn::TotallyValidNonterminal ::= {}

 function funfoo
 String ::= tvn::TotallyValidNonterminal
 {
  return case just(tvn) of
         | just(empty_TVN) -> "Fail"
         end;
 }
}

-- Pattern MUST start with a lower case letter #HACK2012
wrongCode "Pattern variable names start with a lower case letter" {
 nonterminal CompletelyValidNonterminal;
 
 function funfoo
 String ::=
 {
  return case "a string" of
         | CapitalizedStuff -> "Fail"
         end;
 }
}
