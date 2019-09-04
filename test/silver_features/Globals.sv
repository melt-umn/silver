

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

abstract production Tfoo
t::Tglob ::=
{
  t.strGlob = globstring1;
}

global unT :: Tglob = Tfoo();

equalityTest ( unT.strGlob, "Hi", String, silver_tests ) ;

global deT :: Decorated Tglob = decorate unT with {};

equalityTest ( deT.strGlob, "Hi", String, silver_tests ) ;

wrongCode "initialization expression with type" {
  global badT :: Decorated Tglob = Tfoo();
}

wrongCode "does not have the right signature." { -- TODO: this error message should be improved!!
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
