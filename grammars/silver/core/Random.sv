grammar silver:core;

-- Monad for computations involving random number generation.
-- This must live in silver:core because the runtime depends on these productions.
-- Associated utilities are defined in silver:util:random.
data RandomGen<a>
  = mapRandomGen (a ::= b) RandomGen<b>
  | apRandomGen RandomGen<(a ::= b)> RandomGen<b>
  | pureRandomGen a
  | bindRandomGen RandomGen<b> (RandomGen<a> ::= b);

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

production randomToken_
top::RandomGen<RandomToken> ::=
{}

-- Pure random "token" for use with threaded attributes, represented as just a seed value.
-- Unlike IOToken, this is essentially safe to reuse, besides the possibility of
-- repeating random values.
type RandomToken foreign = "Long";

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
