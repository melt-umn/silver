

nonterminal ADecoratedValue with aDecVal;

synthesized attribute aDecVal :: String;

abstract production aDevProd
top::ADecoratedValue ::=
{
  top.aDecVal = "1234";
}

abstract production bouncerDecProd
top::ADecoratedValue ::= v::ADecoratedValue
{
  top.aDecVal = v.aDecVal;
}

function thunkUndecGoWrong
ADecoratedValue ::= ac::ADecoratedValue
{
  -- we have to use a let because the let is a fixed, single thunk while children refs
  -- are differently spawned thunks (maybe that's something we should fix, but)
  return let dec::Decorated ADecoratedValue = ac 
          -- We must evaluate the thunk BEFORE creating the undecorate transform for it,
          -- so to do that, we're going to (ab)use case's strict semantics on the scrutinee
          in case dec of bouncerDecProd(_) -> error("nope")
             -- now we must actually create the undecorate transform. Note this means we have to
             -- need a THUNK that evaluates to the undecorated form of dec. So, we
             -- get that by calling the bouncer with dec (can't just use dec directly!!)
             | _ -> bouncerDecProd(dec) end end;

-- The bug we're testing for was one where transformUndecorate blew up when used on
-- an already evaluated thunk.
}

equalityTest( thunkUndecGoWrong(aDevProd()).aDecVal, "1234", String, silver_tests ) ;


