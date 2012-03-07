
terminal Single_t 'single';


equalityTest( 'single'.lexeme, "single", String, silver_tests );

wrongCode "Could not find terminal declaration for 'double'" {
 global t :: Single_t = 'double';
}

wrongCode "Found ambiguous possibilities for 'single'" {
 -- It's now looking only in the closest environment. hmmm... expected behavior?
 terminal Dupe 'single';
 terminal Dupe2 'single';
 global t :: Single_t = 'single';
}
