grammar silver:util:random;

@{-
  Run a RandomGen computation, using an arbitrary seed.
  
  @param r  The computation to run
  @param ioIn  The IO token
  @return  An IOVal containing the result of the computation
-}
function runRandomGenT
IOVal<a> ::= r::RandomGen<a> ioIn::IOToken
{
  return error("foreign function");
} foreign {
  "java": return "%ioIn%.runRandomGen(originCtx, %r%)";
}

@{-
  Run a RandomGen computation, using an arbitrary seed (IO monadic version.)
  
  @param r  The computation to run
-}
production runRandomGen
top::IO<a> ::= r::RandomGen<a>
{
  local res::IOVal<a> = runRandomGenT(r, top.stateIn);
  top.stateOut = res.io;
  top.stateVal = res.iovalue;
}

@{-
  Run a RandomGen computation, using an arbitrary seed.

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
  "java": return "common.RandomGen.evalRandomTokenOp(%token%, (rng) -> common.RandomGen.runRandomGen(originCtx, rng, %r%))";
}
