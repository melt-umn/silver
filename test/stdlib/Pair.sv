

equalityTest ( ("A", 1).fst, "A",
               String, core_tests ) ;
equalityTest ( ("A", 1).snd, 1,
               Integer, core_tests ) ;


global pairtester :: [Pair<String Integer>] =
 [("1", 1),
  ("2", 2),
  ("3", 3),
  ("99", 99),
  ("4", 4),
  ("4", 3),
  ("3", 2)];
  

equalityTest ( lookup("A", pairtester).isJust, false,
               Boolean, core_tests ) ;
equalityTest ( lookup("1", pairtester).isJust, true,
               Boolean, core_tests ) ;
equalityTest ( lookup("2", pairtester).fromJust, 2,
               Integer, core_tests ) ;
equalityTest ( lookup("1", pairtester).fromJust, 1,
               Integer, core_tests ) ;
equalityTest ( lookup("99", pairtester).fromJust, 99,
               Integer, core_tests ) ;
equalityTest ( lookup("4", pairtester).fromJust, 4,
               Integer, core_tests ) ;

equalityTest(lookupAll("1", pairtester), [1], [Integer], core_tests);
equalityTest(lookupAll("2", pairtester), [2], [Integer], core_tests);
equalityTest(lookupAll("4", pairtester), [4,3], [Integer], core_tests);
equalityTest(lookupAll("3", pairtester), [3,2], [Integer], core_tests);
equalityTest(lookupAll("A", pairtester), [], [Integer], core_tests);

equalityTest(unzipPairs(pairtester).fst, ["1", "2", "3", "99", "4", "4", "3"], [String], core_tests);
equalityTest(unzipPairs(pairtester).snd, [1, 2, 3, 99, 4, 3, 2], [Integer], core_tests);

