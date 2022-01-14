grammar silver:core;

@{-
Types for which random values can be generated.

This class imposes no restrictions on the range or distribution of random values,
as there may be instances for types with no notion of ordering/equality.
-}
class Random a {
  random :: RandomGen<a>;
}

-- Uniform random integer on [INT_MIN, INT_MAX]
instance Random Integer {
  random = randomInteger();
}

-- Uniform random float on [0.0, 1.0)
instance Random Float {
  random = randomFloat();
}

-- 50/50 true or false
instance Random Boolean {
  random = randomBoolean();
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
}

instance RandomRange Integer {
  -- Uniform integer in range is actually kinda tricky to do correctly, so this is a primitive
  randomRange = randomRangeInteger;
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

-- Monad for computations involving random number generation
nonterminal RandomGen<a>;

production mapRandomGen
top::RandomGen<b> ::= (b ::= a) RandomGen<a>
{}

production apRandomGen
top::RandomGen<b> ::= RandomGen<(b ::= a)> RandomGen<a>
{}

production pureRandomGen
top::RandomGen<a> ::= a
{}

production bindRandomGen
top::RandomGen<b> ::= RandomGen<a> (RandomGen<b> ::= a)
{}

production randomInteger
top::RandomGen<Integer> ::=
{}

production randomRangeInteger
top::RandomGen<Integer> ::= Integer Integer
{}

production randomFloat
top::RandomGen<Float> ::=
{}

production randomBoolean
top::RandomGen<Boolean> ::=
{}

instance Functor RandomGen {
  map = mapRandomGen;
}

instance Apply RandomGen {
  ap = apRandomGen;
}

instance Applicative RandomGen {
  pure = pureRandomGen;
}

instance Bind RandomGen {
  bind = bindRandomGen; 
}

instance Monad RandomGen {}

@{-
  Run a RandomGen computation, using an arbitrary seed.
  Warning: this function is nondeterministic (may vary between runs) and thus impure;
  use at your own risk!
  
  @param r  The computation to run
  @return  The result of the computation
-}
function runRandomGen
a ::= r::RandomGen<a>
{
  return error("foreign function");
} foreign {
  "java": return "common.RandomGen.runRandomGen(originCtx, %r%)";
}

@{-
  Run a RandomGen computation, using an arbitrary seed.
  Warning: this function is nondeterministic (may vary between runs) and thus impure;
  use at your own risk!

  @param seed  The initial seed value for the random number generator
  @param r  The computation to run
  @return  The result of the computation
-}
function runSeedRandomGen
a ::= seed::Integer r::RandomGen<a>
{
  return error("foreign function");
} foreign {
  "java": return "common.RandomGen.runRandomGen(originCtx, %seed%, %r%)";
}

