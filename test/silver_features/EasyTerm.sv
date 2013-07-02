
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

-- This isn't easy term, exactly, but this was a bug related to terminal attribute access,
-- where type information was not properly propagated.
terminal Term 'term';

global tl :: [Term] = [terminal(Term, "hi"), terminal(Term, "hello")];

-- these should not result in java compiler errors:
global error23423 :: String = head(tl).lexeme;
global error23424 :: Location = head(tl).location;

