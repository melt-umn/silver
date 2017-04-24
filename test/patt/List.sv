grammar patt;

-- NOTE: LEAVE THIS HERE. Don't change the line it's on.
wrongCode "7" { -- ensure the line 7 appears.
global nope55 :: Integer =
  case 3 of
  [] -> 1
  end;
} -- TODO: We could use a "errors do NOT contain" directive here to be less fragile. no -1 line numbers!
-- OKAY now you can change anything below without such awful concerns

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

