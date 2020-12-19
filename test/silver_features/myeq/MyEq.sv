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
