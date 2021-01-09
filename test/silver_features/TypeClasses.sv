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
  bazFromInt :: (a ::= Integer) = \ Integer -> cy;
}

instance CBaz a => CFoo [a]
{
  cx = [cy];
}

instance CBaz Integer
{
  cy = 42;
  bazFromInt = \ i::Integer -> i;
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

global bfii::Integer = bazFromInt(24);
global bfis::String = bazFromInt(24);
equalityTest(bfii, 24, Integer, silver_tests);
equalityTest(bfis, "hello", String, silver_tests);

equalityTest(myeq([1, 2, 3], [1, 2, 3]), true, Boolean, silver_tests);
equalityTest(myeq([1, 2, 3], [1, 2, 3, 2, 1]), false, Boolean, silver_tests);
equalityTest(myeq([1, 2, 3], [1, 2, 1]), false, Boolean, silver_tests);
equalityTest(myeq(pair([1, 2], 3), pair([1, 2], 3)), true, Boolean, silver_tests);
equalityTest(myeq(pair([1, 2], 3), pair([1, 4], 3)), false, Boolean, silver_tests);

equalityTest(myneq([1, 2, 3], [1, 2, 3]), false, Boolean, silver_tests);
equalityTest(myneq([1, 2, 3], [1, 2, 3, 2, 1]), true, Boolean, silver_tests);
equalityTest(myneq([1, 2, 3], [1, 2, 1]), true, Boolean, silver_tests);
equalityTest(myneq(pair([1, 2], 3), pair([1, 2], 3)), false, Boolean, silver_tests);
equalityTest(myneq(pair([1, 2], 3), pair([1, 4], 3)), true, Boolean, silver_tests);

function myRemove
MyEq a => [a] ::= x::a xs::[a]
{
  return removeBy(myeq, x, xs);
}
equalityTest(hackUnparse(myRemove(3, [1, 2, 3, 4])), "[1, 2, 4]", String, silver_tests);

equality attribute isEqTo, isEq;
nonterminal EqPair<a b> with isEqTo, isEq;
production eqPair
MyEq a, MyEq b => top::EqPair<a b> ::= x::a y::b
{
  top.isEq = case top.isEqTo of eqPair(x1, y1) -> myeq(x1, x) && myeq(y1, y) end;
}

equalityTest(decorate eqPair(42, [1, 2, 3]) with {isEqTo=eqPair(42, [1, 2, 3]);}.isEq, true, Boolean, silver_tests);
equalityTest(decorate eqPair(42, [1, 2, 3]) with {isEqTo=eqPair(42, [1, 23, 3]);}.isEq, false, Boolean, silver_tests);

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

wrongCode "Undeclared type" {
  instance Blarch Integer {}
}

wrongCode "Undeclared type" {
  instance Blarch a => CBaz a {}
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

class MyFunctor f {
  myfmap :: (f<b> ::= (b ::= a) f<a>);
}

instance MyFunctor Maybe {
  myfmap = mapMaybe;
}

function mapEither
Either<a c> ::= fn::(c ::= b) x::Either<a b>
{
  return case x of left(l) -> left(l) | right(r) -> right(fn(r)) end;
}

instance MyFunctor Either<a _> {
  myfmap = mapEither;
}

instance MyFunctor [] {
  myfmap = map;
}

equalityTest(myfmap(\ x::Integer -> toFloat(x), [1, 2, 3]), [1.0, 2.0, 3.0], [Float], silver_tests);
equalityTest(myfmap(\ x::Integer -> toFloat(x), just(42)).fromJust, 42.0, Float, silver_tests);
equalityTest(myfmap(\ x::Integer -> toFloat(x), left("abc")).fromLeft, "abc", String, silver_tests);
equalityTest(myfmap(\ x::Integer -> toFloat(x), right(42)).fromRight, 42.0, Float, silver_tests);

wrongCode "Either has kind arity 2, but the class MyFunctor expected kind arity 1" {
  instance MyFunctor Either {}
}

wrongCode "f has kind arity 1, but there are 2 type arguments supplied here" {
  class MyFunctorBad f {
    myfmap2 :: (f<b> ::= (b ::= a) f<a b>);
  }
}

class CDefaultVal a {
  cdv1 :: Pair<Integer a>;
  cdv2 :: a = cdv1.snd;
}

instance CDefaultVal String {
  cdv1 = pair(42, "abc");
}

equalityTest(cdv2, "abc", String, silver_tests);

class AmbInst a {
  ambval :: a;
}
instance AmbInst Integer {
  ambval = 42;
}
instance AmbInst Float {
  ambval = 3.14;
}

wrongCode "Ambiguous type variable a (arising from the use of ambval) prevents the constraint silver_features:AmbInst a from being solved." {
  global ambStr::String = hackUnparse(ambval);
}

global intIsEqual::(Boolean ::= Integer Integer) = myeq;
equalityTest(intIsEqual(42, 42), true, Boolean, silver_tests);
equalityTest(intIsEqual(42, 34), false, Boolean, silver_tests);

function myeq2
MyEq a => Boolean ::= x::a y::a
{
  return myeq(x, y);
}
global intIsEqual2::(Boolean ::= Integer Integer) = myeq2;
equalityTest(intIsEqual2(42, 42), true, Boolean, silver_tests);
equalityTest(intIsEqual2(42, 34), false, Boolean, silver_tests);

