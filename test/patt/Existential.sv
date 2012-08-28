grammar patt;

nonterminal Existential;

abstract production existentialprod
top::Existential ::= arg::a  fun::(String ::= a)
{
}

function applyExist
String ::= v::Existential
{
  return case v of
           existentialprod(arg, fun) -> fun(arg)
         end;
}

equalityTest ( applyExist(existentialprod(1, toStringFromInteger)), "1", String, pat_tests ) ;
equalityTest ( applyExist(existentialprod(2, toStringFromInteger)), "2", String, pat_tests ) ;
equalityTest ( applyExist(existentialprod(1.0, toStringFromFloat)), "1.0", String, pat_tests ) ;

-- Make sure this raises an error instead of the existential type being unified
wrongCode "expected a but argument is of type String" {
 function applyExistBadType
 String ::= v::Existential
 {
  return case v of
           existentialprod(arg, fun) -> fun("hahahaha")
         end;
 }
}
