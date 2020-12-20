grammar silver_features:myeq;

class MyEq a
{
  myeq :: (Boolean ::= a a);
}

instance MyEq Integer
{
  myeq = \ x::Integer y::Integer -> x == y;
}

instance MyEq a => MyEq [a]
{
  myeq = \ xs::[a] ys::[a] -> length(xs) == length(ys) && all(zipWith(myeq, xs, ys));
}

instance MyEq a, MyEq b => MyEq Pair<a b>
{
  myeq = \ x::Pair<a b> y::Pair<a b> -> myeq(x.fst, y.fst) && myeq(x.snd, y.snd);
}
