grammar silver_features:myeq;

class MyEq a
{
  myeq :: (Boolean ::= a a);
  myneq :: (Boolean ::= a a) = \ x::a y::a -> !myeq(x, y);
}

instance MyEq Integer
{
  myeq = \ x::Integer y::Integer -> x == y;
}

instance MyEq String
{
  myeq = \ x::String y::String -> x == y;
}

instance MyEq a => MyEq [a]
{
  myeq = \ xs::[a] ys::[a] -> length(xs) == length(ys) && all(zipWith(myeq, xs, ys));
}

instance MyEq a, MyEq b => MyEq Pair<a b>
{
  myeq = \ x::Pair<a b> y::Pair<a b> -> myeq(x.fst, y.fst) && myeq(x.snd, y.snd);
}
