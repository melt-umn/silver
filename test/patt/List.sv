grammar patt;

function mynull
Boolean ::= s::[a]
{
  return case s of
           [] -> true
         | _::_ -> false
         end;
}

equalityTest ( mynull([]), true, Boolean, pat_tests ) ;
equalityTest ( mynull([1]), false, Boolean, pat_tests ) ;

function myhead
a ::= s::[a]
{
  return case s of
           h::t -> h
         | _ -> error("List is empty!")
         end;
}

equalityTest ( myhead([1]), 1, Integer, pat_tests ) ;
equalityTest ( myhead([false]), false, Boolean, pat_tests ) ;

function mytail
[a] ::= s::[a]
{
  return case s of
           h::t -> t
         | _ -> error("List is empty!")
         end;
}

equalityTest ( mytail([1]), [], [Integer], pat_tests ) ;
equalityTest ( mytail([false,true]), [true], [Boolean], pat_tests ) ;

function mysafedoubletail
[a] ::= s::[a]
{
  return case s of
           _::_::t -> t
         | _::_ -> []
         | _ -> []
         end;
}

equalityTest ( mysafedoubletail([]), [], [Integer], pat_tests ) ;
equalityTest ( mysafedoubletail([1]), [], [Integer], pat_tests ) ;
equalityTest ( mysafedoubletail([1,2]), [], [Integer], pat_tests ) ;
equalityTest ( mysafedoubletail([1,2,3]), [3], [Integer], pat_tests ) ;

