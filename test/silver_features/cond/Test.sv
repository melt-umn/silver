grammar silver_features:cond;

import silver_features:cond:a; -- triggers c with b
import silver_features:cond:b;

import silver_features;
import silver:testing;
import lib:extcore;


-- should get :c.

-- directly reference the c
equalityTest(silver_features:cond:c:aVal, 1, Integer, silver_tests);

-- d is exported by c. ensure we have it.
equalityTest(silver_features:cond:d:bVal, 5, Integer, silver_tests);

-- e gets trigger by d. ensure we have it.
equalityTest(silver_features:cond:e:cVal, 9, Integer, silver_tests);

-- Yeah, this long chain is needed.
-- Tricky one to ensure we catch: triggered c exports d.
-- Make sure we include the exports!
-- Won't be caught just by checking for d, because
-- the imports only check if the trigger is present in the set, not the result
-- so it's e that shows the bug, if we
-- "do all exports, then do all cond builds, forgetting about exports"

