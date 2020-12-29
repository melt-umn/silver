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

function functorInc
f<Integer> ::= fmapI::(f<Integer> ::= (Integer ::= Integer) f<Integer>) xs::f<Integer>
{
  return fmapI(\ x::Integer -> x + 1, xs);
}

equalityTest(hackUnparse(functorInc(map, [1, 2, 3])), "[2, 3, 4]", String, silver_tests);
equalityTest(hackUnparse(functorInc(mapMaybe, just(42))), "core:just(43)", String, silver_tests);
