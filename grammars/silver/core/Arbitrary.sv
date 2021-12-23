grammar silver:core;

@{--
  - Represents primitive (terminal) types for which random values can be generated. 
  -}
class Arbitrary a {
  genArb :: (a ::= Integer);
}

instance Arbitrary Integer {
  genArb = \ depth::Integer -> toInteger(genRand() * toFloat(depth));
}

instance Arbitrary Float {
  genArb = \ depth::Integer -> genRand() * toFloat(depth);
}

instance Arbitrary Boolean {
  genArb = \ depth::Integer -> genRand() > 0.5;
}

instance Arbitrary String {
  genArb = \ depth::Integer ->
    let chars::String = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789`~!@#$%^&*()_-+=\\|[]{}/?'\";:,.<>\n\n\n\t\r                                       "
    in charsToString(take(genArb(depth * 5), randShuffle(concat(repeat(stringToChars(chars), depth / 3)))))
    end;
}

instance Arbitrary Location {
  genArb = \ depth::Integer -> txtLoc("arbitrary at depth " ++ toString(depth));
}

instance Arbitrary a, Arbitrary b => Arbitrary Pair<a b> {
  genArb = \ depth::Integer -> pair(genArb(depth), genArb(depth));
}

instance Arbitrary a, Arbitrary b => Arbitrary Either<a b> {
  genArb = \ depth::Integer -> if genArb(depth) then left(genArb(depth)) else right(genArb(depth));
}
