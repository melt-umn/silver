grammar silver:core;

-- Monad for computations involving random number generation.
-- This must live in silver:core because the runtime depends on these productions.
-- Associated utilities are defined in silver:util:random.
nonterminal RandomGen<a>;

-- Pure random "token" for use with threaded attributes, represented as just a seed value.
type RandomToken foreign = "Long";

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

production randomToken_
top::RandomGen<RandomToken> ::=
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
