grammar silver:util:random;

@{--
Randomly shuffle the elements of a list.

@param elems The list to shuffle.
@return A RandomGen monadic action to shuffle the list.
-}
function randomShuffle
RandomGen<[a]> ::= elems::[a]
{
  return
    if null(elems) then pure([])
    else do {
      i :: Integer <- randomRange(0, length(elems) - 1);
      let hd :: [a] = take(i, elems);
      let tl :: [a] = drop(i, elems);
      rest :: [a] <- randomShuffle(hd ++ tail(tl));
      return head(tl) :: rest;
    };
}

@{--
Select a random element from a non-empty list.
An error is raised when the list is empty.

@param elems The list from which to select an element.
@return A RandomGen monadic action to select an element from the list.
-}
function randomElem
RandomGen<a> ::= elems::[a]
{
  return if null(elems) then error("randomElem of empty list!") else
    do {
      i :: Integer <- randomRange(0, length(elems) - 1);
      return head(drop(i, elems));
    };
}


@{--
Utility for creating a random value using the token-based random library.
Example:
  production foo::RandomVal<Integer> = randomVal();
  thread randomIn, randomOut on top, foo, top;
  local fooVal::Integer = foo.randomValue;
-}
nonterminal RandomVal<a> with randomIn, randomOut, randomValue<a>;

synthesized attribute randomValue<a>::a;

@{--
Create a random value using the default Random instance for the type.
-}
production randomVal
Random a => top::RandomVal<a> ::=
{
  production result::(a, RandomToken) = randomT(top.randomIn);
  top.randomValue = result.1;
  top.randomOut = result.2;
}

@{--
Create a random value using the default RandomRange instance for the type.

@param min  The minimum bound for the random value.
@param max  The maximum bound for the random value.
-}
production randomRangeVal
RandomRange a => top::RandomVal<a> ::= min::a max::a
{
  production result::(a, RandomToken) = randomRangeT(min, max, top.randomIn);
  top.randomValue = result.1;
  top.randomOut = result.2;
}

@{--
Create a random value using a monadic RandomGen action.

@param g  The random generator to use to generate a value.
-}
production randomGenVal
top::RandomVal<a> ::= g::RandomGen<a>
{
  production result::(a, RandomToken) = runTokenRandomGen(top.randomIn, g);
  top.randomValue = result.1;
  top.randomOut = result.2;
}
