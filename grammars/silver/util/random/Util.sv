grammar silver:util:random;

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
