grammar disamb;

exports host;

-- Incorrect
imports test:term_b;

-- Correct
--exports test:term_b;

terminal Disamb_t 'AA';

disambiguate Disamb_t, B_t {
    pluck Disamb_t;
}

parser extendedParser :: Root {
    host;
    disamb;
} 