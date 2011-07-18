

import silver_features:global_sub;


global globstring1 :: String = "Hi";

equalityTest ( globstring1, "Hi", String, silver_tests ) ;

global globstring2 :: String = substring(2,4,"Heyooo");

equalityTest ( globstring2, "yo", String, silver_tests ) ;

-- ref to sub's global
global globint2 :: Integer = 11 + globalint1;

equalityTest ( globalint1, 6, Integer, silver_tests ) ;
equalityTest ( globint2, 17, Integer, silver_tests ) ;

-- refed by sub
global globalbool1 :: Boolean = false;

equalityTest ( globalbool2, false, Boolean, silver_tests ) ;

-- infinite lists are broken. see bug #1426
--global infiniteOnes :: [Integer] = 1 :: infiniteOnes;

--equalityTest ( take(5,infiniteOnes), [1,1,1,1,1], [Integer], silver_tests ) ;

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

