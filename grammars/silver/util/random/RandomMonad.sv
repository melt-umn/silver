grammar silver:util:random;

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

@{-
  Run a RandomGen computation, using a random token.

  @param token  The random number generator to use
  @param r  The computation to run
  @return  The result of the computation
-}
function runTokenRandomGen
(a, RandomToken) ::= token::RandomToken r::RandomGen<a>
{
  return error("foreign function");
} foreign {
  "java": return "new silver.core.Ppair(common.RandomGen.runRandomGen(originCtx, %token%, %r%), new java.util.Random(%token%.nextLong()))";
}
