grammar silver_features:cond;

import silver_features:cond:a; -- triggers c with b
import silver_features:cond:b;

import silver_features;
import silver:testing;
import lib:extcore;


-- should get :c.

-- directly reference the c
equalityTest(silver_features:cond:c:aVal, 1, Integer, silver_tests);
