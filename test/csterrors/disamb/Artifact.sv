grammar disamb;

exports host;

-- Incorrect
imports test:term_b;

-- Correct
--exports test:term_b;

terminal Disamb_t 'AA';

-- This disambiguate clause 
-- causes a cstError because this grammar does 
-- not have access to B_t, but does import B_t
disambiguate Disamb_t, B_t {
    pluck Disamb_t;
}

parser extendedParser :: Root {
    host;
    disamb;
} 