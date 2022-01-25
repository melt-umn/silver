grammar silver:util:random;

@{--
Monadic action to generate a RandomToken in the RandomGen Monad.
-}
global randomToken::RandomGen<RandomToken> = randomToken_();

@{--
Utility attribute pair to thread a random token through an abstract syntax tree.
-}
threaded attribute randomIn, randomOut :: RandomToken;

@{--
Utility for creating a random value using the token-based random library.
Example:
  production foo::RandomVal<Integer> = randomVal();
  thread randomIn, randomOut on top, foo, top;
  local fooVal::Integer = foo.randomValue;
-}
nonterminal RandomVal<a> with randomIn, randomOut, randomValue<a>;

synthesized attribute randomValue<a>::a;

production randomVal
Random a => top::RandomVal<a> ::=
{
  production result::(a, RandomToken) = randomT(top.randomIn);
  top.randomValue = result.1;
  top.randomOut = result.2;
}

production randomRangeVal
RandomRange a => top::RandomVal<a> ::= min::a max::a
{
  production result::(a, RandomToken) = randomRangeT(min, max, top.randomIn);
  top.randomValue = result.1;
  top.randomOut = result.2;
}

function randomTInteger
(Integer, RandomToken) ::= token::RandomToken
{
  return error("foreign function");
} foreign {
  "java": return "new silver.core.Ppair(%token%.nextInt(), new java.util.Random(%token%.nextLong()))";
}

function randomTFloat
(Float, RandomToken) ::= token::RandomToken
{
  return error("foreign function");
} foreign {
  "java": return "new silver.core.Ppair(%token%.nextFloat(), new java.util.Random(%token%.nextLong()))";
}

function randomTBoolean
(Boolean, RandomToken) ::= token::RandomToken
{
  return error("foreign function");
} foreign {
  "java": return "new silver.core.Ppair(%token%.nextBoolean(), new java.util.Random(%token%.nextLong()))";
}

function randomRangeTInteger
(Integer, RandomToken) ::= min::Integer max::Integer token::RandomToken
{
  return error("foreign function");
} foreign {
  "java": return "new silver.core.Ppair(common.RandomGen.randomRangeInteger(%min%, %max%, %token%), new java.util.Random(%token%.nextLong()))";
}
