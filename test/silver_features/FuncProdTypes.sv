nonterminal IntNT ;
synthesized attribute intValue :: Integer occurs on IntNT ;
abstract production intTestProd
i::IntNT ::= v::Integer
{
 i.intValue = v ;
}


global aList :: [IntNT] = map(intTestProd, [1, 2, 3]) ;

equalityTest( map( (.intValue), aList ), 
              [1, 2, 3] , [Integer], silver_tests );


nonterminal ListNT ;
abstract production intAdd
s::IntNT ::= l::IntNT r::IntNT
{
 s.intValue = l.intValue + r.intValue ;
{- Some broken bits to use in improving error messages on bad prod/func applications.
 local attribute t::IntNT ;
 t = intTestProd ( 3.1415 ) ;

 local attribute ts :: [ IntNT ] ;
 ts = map ( t, [1,2,3] ) ;

 local attribute i :: Integer ; 
 i = 1 + intTestProd ( 0 ) ; 
-}
}

equalityTest ( foldr (intAdd, intTestProd(0), aList).intValue, 6, Integer, silver_tests ) ;

-- Higher-kinded polymorphic functions and productions
function functorInc
f<Integer> ::= fmapI::(f<Integer> ::= (Integer ::= Integer) f<Integer>) xs::f<Integer>
{
  return fmapI(\ x::Integer -> x + 1, xs);
}

equalityTest(functorInc(map, [1, 2, 3]), [2, 3, 4], [Integer], silver_tests);
equalityTest(functorInc(mapMaybe, just(42)).fromJust, 43, Integer, silver_tests);

nonterminal FInts with intValue;
production fints
top::FInts ::= ffoldI::(Integer ::= (Integer ::= Integer Integer) Integer f<Integer>) xs::f<Integer>
{
  top.intValue = ffoldI(\ i1::Integer i2::Integer -> i1 + i2, 0, xs);
}

equalityTest(fints(foldr, [1, 2, 3]).intValue, 6, Integer, silver_tests);
global foldMaybeInt::(Integer ::= (Integer ::= Integer Integer) Integer Maybe<Integer>) =
  \ fn::(Integer ::= Integer Integer) i0::Integer mi::Maybe<Integer> -> case mi of just(i1) -> fn(i0, i1) | _ -> i0 end;
equalityTest(fints(foldMaybeInt, just(42)).intValue, 42, Integer, silver_tests);

-- Kind mismatch
wrongCode "f is not fully applied, it has kind arity 1" {
  function badKind
  f ::= f<a>
  { return error(""); }
}

wrongCode "Missing type argument cannot be followed by a provided argument" {
  type BadFunc = (_ ::= _ String);
}

wrongCode "Return type cannot be present when argument types are missing" {
  type BadFunc = (Integer ::= String _);
}

global idInt1::(Integer ::= Integer) = id;
global idInt2::(_ ::= Integer)<Integer> = id;
global idInt3::(_ ::= _)<Integer Integer> = id;
