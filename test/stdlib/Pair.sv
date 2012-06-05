

equalityTest ( pair("A", 1).fst, "A",
               String, core_tests ) ;
equalityTest ( pair("A", 1).snd, 1,
               Integer, core_tests ) ;


global pairtester :: [Pair<String Integer>] =
 [pair("1", 1),
  pair("2", 2),
  pair("3", 3),
  pair("99", 99),
  pair("4", 4),
  pair("4", 3),
  pair("3", 2)];
  

equalityTest ( lookupBy(stringEq, "A", pairtester).isJust, false,
               Boolean, core_tests ) ;
equalityTest ( lookupBy(stringEq, "1", pairtester).isJust, true,
               Boolean, core_tests ) ;
equalityTest ( lookupBy(stringEq, "2", pairtester).fromJust, 2,
               Integer, core_tests ) ;
equalityTest ( lookupBy(stringEq, "1", pairtester).fromJust, 1,
               Integer, core_tests ) ;
equalityTest ( lookupBy(stringEq, "99", pairtester).fromJust, 99,
               Integer, core_tests ) ;
equalityTest ( lookupBy(stringEq, "4", pairtester).fromJust, 4,
               Integer, core_tests ) ;

equalityTest(lookupAllBy(stringEq, "1", pairtester), [1], [Integer], core_tests);
equalityTest(lookupAllBy(stringEq, "2", pairtester), [2], [Integer], core_tests);
equalityTest(lookupAllBy(stringEq, "4", pairtester), [4,3], [Integer], core_tests);
equalityTest(lookupAllBy(stringEq, "3", pairtester), [3,2], [Integer], core_tests);
equalityTest(lookupAllBy(stringEq, "A", pairtester), [], [Integer], core_tests);

