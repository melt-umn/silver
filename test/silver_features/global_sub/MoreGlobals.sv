
import silver_features;
import silver:testing;
import lib:extcore;

global globalint1 :: Integer = 1 + 2 + 3;

-- reference to s_f's globals.
global globalbool2 :: Boolean = globalbool1 && globalbool1;

equalityTest ( globalbool1, false, Boolean, silver_tests ) ;

