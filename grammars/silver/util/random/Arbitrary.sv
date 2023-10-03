grammar silver:util:random;

-- TODO: Consider adding a Shrink type class for use in conjuction with Arbitrary in randomized testing.
-- See https://hackage.haskell.org/package/QuickCheck-2.14.2/docs/Test-QuickCheck.html#v:shrink

@{--
  - Represents primitive (terminal) types for which arbitrary random values can be generated.
  -}
class Arbitrary a {
  genArb :: (RandomGen<a> ::= Integer);
}

instance Arbitrary Integer {
  genArb = \ dummy::Integer -> randomRange(-100, 100);  -- TODO: Is this a reasonable default?  Revisit.
}

instance Arbitrary Float {
  genArb = \ dummy::Integer -> randomRange(-10.0, 10.0);  -- TODO: Is this a reasonable default?  Revisit.
}

instance Arbitrary Boolean {
  genArb = \ dummy::Integer -> random;
}

instance Arbitrary String {
  -- TODO: Is this a reasonable default?  Revisit.
  genArb = \ depth::Integer -> do {
    let chars :: String = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789`~!@#$%^&*()_-+=\\|[]{}/?'\";:,.<>\n\n\n\t\r                                       ";
    randChars :: [Integer] <- randomShuffle(concat(repeat(stringToChars(chars), depth / 3)));
    len :: Integer <- randomRange(0, max(depth, 1) * 5);
    return charsToString(take(len, randChars));
  };
}

instance Arbitrary Location {
  genArb = \ depth::Integer -> pure(txtLoc("arbitrary at depth " ++ toString(depth)));
}

instance Arbitrary a, Arbitrary b => Arbitrary Pair<a b> {
  genArb = \ depth::Integer -> lift2(pair(fst=_, snd=_), genArb(depth), genArb(depth));
}

instance Arbitrary a, Arbitrary b => Arbitrary Either<a b> {
  genArb = \ depth::Integer ->
    bind(random, \ b::Boolean -> if b then map(left, genArb(depth)) else map(right, genArb(depth)));
}

instance Arbitrary a => Arbitrary [a] {
  genArb = \ depth::Integer ->
    if depth > 0
    then lift2(cons, genArb(depth), genArb(depth - 1))
    else pure([]);
}
