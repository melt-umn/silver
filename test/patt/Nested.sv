grammar patt;

-- Fixing #495 to support arbitrary nesting of patterns
function nestedlists
String ::= alist :: [[Integer]]
{
 return case alist of
        | (head::firsttail)::finaltail -> "NotEmpty/NotEmpty"
        | _::finaltail -> "Empty/NotEmpty"
        | [] -> "Empty" 
        end;
}

function nestedints
Integer ::= i ::Integer
{
 return case i of
        | 0 -> 10
        | (1) -> 11
        | ((2)) -> 12
        | _ -> 0
        end;
}

nonterminal Nest;
production nest_empty
n::Nest ::= 
{ }

production nest_int
n::Nest ::= i::Integer
{ }

production nest_double
n::Nest ::= n1::Nest n2::Nest
{ }

function nestedprods
String ::= n::Nest
{
  return case n of
         | (nest_empty()) -> "nest_empty"
         | ((nest_int((2)))) -> "nest_int_2"
         | (nest_int((_))) -> "nest_int"
         | ((nest_double((nest_empty()),(nest_int((_)))))) -> "nest_double_specific"
         | (((nest_double(((_)),_)))) -> "nest_double_general"
         end;
}

equalityTest ( nestedlists( [ [1,2] , [3,4] ] ), "NotEmpty/NotEmpty", String, pat_tests ) ;
equalityTest ( nestedlists( [ [] , [3,4] ] ), "Empty/NotEmpty", String, pat_tests ) ;
equalityTest ( nestedlists( [ ] ), "Empty", String, pat_tests ) ;

equalityTest ( nestedints( 0 ), 10, Integer, pat_tests ) ;
equalityTest ( nestedints( 1 ), 11, Integer, pat_tests ) ;
equalityTest ( nestedints( 2 ), 12, Integer, pat_tests ) ;
equalityTest ( nestedints( 3 ),  0, Integer, pat_tests ) ;

equalityTest ( nestedprods( nest_empty() ), "nest_empty", String, pat_tests ) ;
equalityTest ( nestedprods( nest_int(2) ), "nest_int_2", String, pat_tests ) ;
equalityTest ( nestedprods( nest_double( nest_empty(), nest_int(2)) ), "nest_double_specific", String, pat_tests ) ;
equalityTest ( nestedprods( nest_double( nest_int(3), nest_empty()) ), "nest_double_general", String, pat_tests ) ;
