grammar silver_features;

import silver_features:myeq;

class CFoo a
{
  cx :: a;
}

class CFoo a => CBar a
{}

class CBaz a
{
  cy :: a;
}

instance CBaz a => CFoo [a]
{
  cx = [cy];
}

instance CBaz Integer
{
  cy = 42;
}

instance CBaz String
{
  cy = "hello";
}

instance CBaz a => CBar [a]
{}

global cxi::[Integer] = cx;
equalityTest(hackUnparse(cxi), "[42]", String, silver_tests);

global cxs::[String] = cx;
equalityTest(hackUnparse(cxs), "[\"hello\"]", String, silver_tests);

equalityTest(myeq([1, 2, 3], [1, 2, 3]), true, Boolean, silver_tests);
equalityTest(myeq([1, 2, 3], [1, 2, 3, 2, 1]), false, Boolean, silver_tests);
equalityTest(myeq([1, 2, 3], [1, 2, 1]), false, Boolean, silver_tests);
equalityTest(myeq(pair([1, 2], 3), pair([1, 2], 3)), true, Boolean, silver_tests);
equalityTest(myeq(pair([1, 2], 3), pair([1, 4], 3)), false, Boolean, silver_tests);


wrongCode "Could not find an instance for silver_features:CBaz Float (arising from the instance for silver_features:CFoo [Float], arising from the use of cx)" {
  global cxf::[Float] = cx;
}

wrongCode "Member cy has expected type Float, but the expression has actual type Boolean" {
  instance CBaz Float
  {
    cy = false;
  }
}

wrongCode "must be unqualified" {
  class gram:Blah a {}
}

{- This is a parse error, currently
wrongCode "must be capitalized" {
  class blah a {}
}
-}

wrongCode "Cycle exists" {
  class C1 a => C2 a {}
  class C2 a => C1 a {}
}

wrongCode "is already bound" {
  class M a { f :: ([a] ::= a); f :: (a ::= a a); }
}

wrongCode "is not a type class" {
  instance Maybe Integer {}
}

wrongCode "Overlapping instances" {
  instance CBaz Integer { cy = 24; }
}

wrongCode "Overlapping instances" {
  instance CFoo [a] { cx = []; }
}

warnCode "Orphaned instance" {
  instance MyEq a => MyEq Maybe<a>
  {
    myeq = \ m1::Maybe<a> m2::Maybe<a> ->
      case m1, m2 of
      | just(x), just(y) -> myeq(x, y)
      | nothing(), nothing() -> true
      | _, _ -> false
      end;
  }
}

warnCode "Orphaned default instance" {
  instance MyEq a
  {
    myeq = \ a a -> false;
  }
}
equalityTest(myeq(3.14, 3.14), false, Boolean, silver_tests);

nonterminal ABCD;
production abcd top::ABCD ::= {}

-- Type class from another grammar, but not orphaned
instance MyEq ABCD
{
  myeq = \ ABCD ABCD -> true;
}
equalityTest(myeq(abcd(), abcd()), true, Boolean, silver_tests);

wrongCode "Missing instance member silver_features:cx" {
  instance CFoo ABCD {}
}

wrongCode "Unexpected instance member cy" {
  instance CFoo ABCD
  {
    cy = 42;
  }
}

wrongCode "is no smaller than the instance head" {
  instance CFoo a => CBaz a
  {
    cy = cx;
  }
}

