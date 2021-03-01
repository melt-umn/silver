

import silver_features:global_sub;

-- basics!
global globstring1 :: String = "Hi";

equalityTest ( globstring1, "Hi", String, silver_tests ) ;

-- function call
global globstring2 :: String = substring(2,4,"Heyooo");

equalityTest ( globstring2, "yo", String, silver_tests ) ;

-- ref to sub's global
global globint2 :: Integer = 11 + globalint1;

equalityTest ( globalint1, 6, Integer, silver_tests ) ;
equalityTest ( globint2, 17, Integer, silver_tests ) ;

-- refed by sub
global globalbool1 :: Boolean = false;

-- ref to sub
equalityTest ( globalbool2, false, Boolean, silver_tests ) ;

--some decoratedness tests
nonterminal Tglob with strGlob;
synthesized attribute strGlob :: String;

abstract production tfoo
t::Tglob ::=
{
  t.strGlob = globstring1;
}

global unT :: Tglob = tfoo();

equalityTest ( unT.strGlob, "Hi", String, silver_tests ) ;

global deT :: Decorated Tglob = decorate unT with {};

equalityTest ( deT.strGlob, "Hi", String, silver_tests ) ;

wrongCode "initialization expression with type" {
  global badT :: Decorated Tglob = tfoo();
}

wrongCode "is not a production" {
  aspect production unT
  t::Tglob ::=
  {
  }
}

-- 'Let's function in a global. Used to rely on signatures...
global letTest :: Integer = let x :: Integer = 5 in x end;

wrongCode "[String] has initialization expression with type [Integer]" {
  -- Check that error messages are generated properly
  global globIntList :: [Integer] = [1,2];
  global globStrList :: [String] = tail(globIntList); -- thread type state properly to detect Str!=Int
}

-- Testing polymorphism
global myMap::([b] ::= (b ::= a) [a]) = map;
global globLst::[Integer] = myMap((\x::Integer -> x + 3), [1,2,3,4,5]);
equalityTest (head(globLst), 4, Integer, silver_tests );

function thirdOfFour
c ::= tuple::(a,b,c,d)
{
  return tuple.3;
}

global getThird::(c ::= (a,b,c,d)) = thirdOfFour;
equalityTest(getThird((1, 2, "three", 4)), "three", String, silver_tests);

-- With constraints
global myEq::Eq a => (Boolean ::= a a) = eq;
equalityTest (myEq(1, 2), false, Boolean, silver_tests);

function strComp
Boolean ::= a::String b::String
{
  return if a == b then true else false;
}

global elem::Eq a => (Boolean ::= (Boolean ::= a a) a [a]) = containsBy;
equalityTest(elem(strComp, "ABC", ["A", "B", "C"]), false, Boolean, silver_tests);
equalityTest(elem(strComp, "B", ["A", "B", "C"]), true, Boolean, silver_tests);