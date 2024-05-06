grammar silver_features;

inherited attribute stuff::[Integer];

nonterminal LocalThing with stuff;

production localThing
top::LocalThing ::=
{
  nondecorated a :: Integer = 5;
  nondecorated production b :: LocalThing = localThing();

  local refA :: Integer = a;
  local refB :: LocalThing = b;
  refB.stuff = [1, 2, 3];
}

wrongCode "Cannot define attributes on b" {
aspect production localThing
top::LocalThing ::=
{
  b.stuff = [1, 2, 3];
}
}

wrongCode "Local refBDec has type Decorated silver_features:LocalThing with {silver_features:stuff} but the expression being assigned to it has type silver_features:LocalThing" {
aspect production localThing
top::LocalThing ::=
{
  local refBDec :: Decorated LocalThing = b;
}
}

wrongCode "This is not something that can be shared" {
aspect production localThing
top::LocalThing ::=
{
  local shareB :: LocalThing = @b;
}
}
