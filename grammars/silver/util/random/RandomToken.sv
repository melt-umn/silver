grammar silver:util:random;

@{--
Monadic action to generate a RandomToken in the RandomGen Monad.
-}
global randomToken::RandomGen<RandomToken> = randomToken_();

@{--
Utility attribute pair to thread a random token through an abstract syntax tree.
-}
threaded attribute randomIn, randomOut :: RandomToken;

function randomTInteger
(Integer, RandomToken) ::= token::RandomToken
{
  return error("foreign function");
} foreign {
  "java": return "common.RandomGen.evalRandomTokenOp(%token%, java.util.Random::nextInt)";
}

function randomTFloat
(Float, RandomToken) ::= token::RandomToken
{
  return error("foreign function");
} foreign {
  "java": return "common.RandomGen.evalRandomTokenOp(%token%, java.util.Random::nextFloat)";
}

function randomTBoolean
(Boolean, RandomToken) ::= token::RandomToken
{
  return error("foreign function");
} foreign {
  "java": return "common.RandomGen.evalRandomTokenOp(%token%, java.util.Random::nextBoolean)";
}

function randomRangeTInteger
(Integer, RandomToken) ::= min::Integer max::Integer token::RandomToken
{
  return error("foreign function");
} foreign {
  "java": return "common.RandomGen.evalRandomTokenOp(%token%, (rng) -> common.RandomGen.randomRangeInteger(%min%, %max%, rng))";
}
