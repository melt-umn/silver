grammar silver:util:random;

@{-
Types for which random values can be generated.

This class imposes no restrictions on the range or distribution of random values,
as there may be instances for types with no notion of ordering/equality.
-}
class Random a {
  random :: RandomGen<a>;
  randomT :: ((a, RandomToken) ::= RandomToken) = runTokenRandomGen(_, random);
}

-- Uniform random integer on [INT_MIN, INT_MAX]
instance Random Integer {
  random = randomInteger();
  randomT = randomTInteger;
}

-- Uniform random float on [0.0, 1.0)
instance Random Float {
  random = randomFloat();
  randomT = randomTFloat;
}

-- 50/50 true or false
instance Random Boolean {
  random = randomBoolean();
  randomT = randomTBoolean;
}

@{-
Types for which random values can be generated, uniformly distributed over some closed range [min, max].

Note that this is not a subclass of Ord since we may have instances for partial orders.

If a has an instance for Ord, then instances should satisfy:
  runRandomGen(randomRange(min, max)) >= min
  runRandomGen(randomRange(min, max)) <= max
-}
class Random a => RandomRange a {
  randomRange :: (RandomGen<a> ::= a a);
  randomRangeT :: ((a, RandomToken) ::= a a RandomToken) =
    \ min::a max::a token::RandomToken -> runTokenRandomGen(token, randomRange(min, max));
}

instance RandomRange Integer {
  -- Uniform integer in range is actually kinda tricky to do correctly, so this is a primitive
  randomRange = randomRangeInteger;
  randomRangeT = randomRangeTInteger;
}

-- Does not allow for generating NaN or infinities, at the moment
instance RandomRange Float {
  randomRange = \ min::Float max::Float ->
    if min > max then error(s"Empty Float range [${toString(min)}, ${toString(max)}]")
    else map(\ f::Float -> f * (max - min) + min, random);
}

instance RandomRange Boolean {
  randomRange = \ min::Boolean max::Boolean ->
    case min, max of
    | false, false -> pure(false)
    | false, true -> random
    | true, false -> error("Empty Boolean range")
    | true, true -> pure(true)
    end;
}
