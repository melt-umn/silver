grammar silver_features;

import silver_features:myeq;
import silver:reflect;

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
  bazEq :: MyEq a => (Boolean ::= a) = \ x::a -> myeq(x, cy);  
}

instance CBaz a => CFoo [a]
{
  cx = [cy];
}

instance CBaz Integer
{
  cy = 42;
  bazFromInt = \ i::Integer -> i;
  bazEq = myeq(_, 42);  
}

instance CBaz String
{
  cy = "hello";
}

instance CBaz a => CBar [a]
{}

global cxi::[Integer] = cx;
equalityTest(cxi, [42], [Integer], silver_tests);

global cxs::[String] = cx;
equalityTest(cxs, ["hello"], [String], silver_tests);

global bfii::Integer = bazFromInt(24);
global bfis::String = bazFromInt(24);
equalityTest(bfii, 24, Integer, silver_tests);
equalityTest(bfis, "hello", String, silver_tests);

equalityTest(bazEq(42), true, Boolean, silver_tests);
equalityTest(bazEq(1234), false, Boolean, silver_tests);
equalityTest(bazEq("hello"), true, Boolean, silver_tests);
equalityTest(bazEq("abc"), false, Boolean, silver_tests);

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
equalityTest(myRemove(3, [1, 2, 3, 4]), [1, 2, 4], [Integer], silver_tests);

equality attribute isEqTo, isEq;
nonterminal EqPair<a b> with isEqTo, isEq;
production eqPair
MyEq a, MyEq b => top::EqPair<a b> ::= x::a y::b
{
  top.isEq = case top.isEqTo of eqPair(x1, y1) -> myeq(x1, x) && myeq(y1, y) end;
}

equalityTest(decorate eqPair(42, [1, 2, 3]) with {isEqTo=eqPair(42, [1, 2, 3]);}.isEq, true, Boolean, silver_tests);
equalityTest(decorate eqPair(42, [1, 2, 3]) with {isEqTo=eqPair(42, [1, 23, 3]);}.isEq, false, Boolean, silver_tests);
equalityTest(case eqPair(42, [1, 2, 3]), eqPair(42, [1, 2, 3]) of eqPair(w, x), eqPair(y, z) -> myeq(w, y) && myeq(x, z) end, true, Boolean, silver_tests);

nonterminal EqRank1;
production eqR1
Eq a => top::EqRank1 ::= x::a
{}

global eqR1Res::Boolean = case eqR1([1, 2, 3]) of eqR1(a) -> a == a end; 
equalityTest(eqR1Res, true, Boolean, silver_tests);

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

class CQux a {
  cqx :: CQux a => a = cqy;
  cqy :: a;
}

instance CQux Integer {
  cqy = 1234;
}

equalityTest(cqx, 1234, Integer, silver_tests);

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
function mapMaybe
Maybe<b> ::= f::(b ::= a) m::Maybe<a>
{
  return case m of
  | just(x)   -> just(f(x))
  | nothing() -> nothing()
  end;
}

instance MyFunctor Either<a _> {
  myfmap = \ fn::(c ::= b) x::Either<a b> -> case x of left(l) -> left(l) | right(r) -> right(fn(r)) end;
}

instance MyFunctor [] {
  myfmap = map;
}

equalityTest(myfmap(\ x::Integer -> toFloat(x), [1, 2, 3]), [1.0, 2.0, 3.0], [Float], silver_tests);
equalityTest(myfmap(\ x::Integer -> toFloat(x), just(42)).fromJust, 42.0, Float, silver_tests);
equalityTest(myfmap(\ x::Integer -> toFloat(x), left("abc")).fromLeft, "abc", String, silver_tests);
equalityTest(myfmap(\ x::Integer -> toFloat(x), let e::Either<String Integer> = right(42) in e end).fromRight, 42.0, Float, silver_tests);

wrongCode "Ambiguous type variable a (arising from the use of myfmap) prevents the constraint silver_features:MyFunctor silver:core:Either<a _> from being solved" {
  global ambRes::String = hackUnparse(myfmap(\ x::Integer -> toFloat(x), right(42)).fromRight);
}

wrongCode "Either has kind * -> * -> *, but the class MyFunctor expected kind * -> *" {
  instance MyFunctor Either {}
}

wrongCode "f has kind * -> *, but there are 2 type arguments supplied here" {
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

global isSingleDigit::(Boolean ::= String) = contains(_, ["1", "2", "3", "4", "5", "6", "7", "8", "9"]);
equalityTest(isSingleDigit("5"), true, Boolean, silver_tests);
equalityTest(isSingleDigit("42"), false, Boolean, silver_tests);

class Semigroupoid (a :: * -> * -> *) {
  sgcompose :: (a<b d> ::= a<c d> a<b c>);
}

instance Semigroupoid (_ ::= _) {
  sgcompose = compose;
}

equalityTest(sgcompose(\ x::Integer -> x * 2, \ s::String -> toInteger(s))("42"), 84, Integer, silver_tests);

wrongCode "(_ ::= _ _) has kind * -> * -> * -> *, but the class Semigroupoid expected kind * -> * -> *" {
  instance Semigroupoid (_ ::= _ _) {
    sgcompose = compose;
  }
}

wrongCode "Member sgcompose has expected type ((b ::= Integer c) ::= (b ::= Integer a) (a ::= Integer c)), but the expression has actual type ((b ::= c) ::= (b ::= a) (a ::= c))" {
  instance Semigroupoid (_ ::= Integer _) {
    sgcompose = compose;
  }
}

wrongCode "is a type alias" {
  -- This caused a kind mismatch crash previously
  type Func<a b> = (b ::= a);
  instance Semigroupoid Func {
    sgcompose = error("compose"); --\f::(d ::= c)  g::(c ::= b) -> \x::b -> f(g(x));
  }
}

wrongCode "a has kind * -> * -> *, but kind * is expected here" {
  function badKind
  Semigroupoid a => Integer ::= a
  {
    return 42;
  }
}

class BoolThing a {
  bteq :: Eq b => (a ::= b b);
}

instance BoolThing Maybe<Unit> {
  bteq = \ x::b y::b -> if x == y then just(unit()) else nothing();
}

equalityTest(bteq(42, 42), just(unit()), Maybe<Unit>, silver_tests);
equalityTest(bteq(234, 42), nothing(), Maybe<Unit>, silver_tests);

class runtimeTypeable a => MyTypeable a {
  myreify :: (a ::= AST) = reifyUnchecked;
}

instance runtimeTypeable a => MyTypeable a {}

instance MyTypeable Integer {
  myreify = \ a::AST -> case a of integerAST(i) -> i | _ -> error("not integer") end;
}

instance runtimeTypeable a, MyTypeable b => MyTypeable Pair<a b> {
  myreify = \ a::AST -> case a of AST { silver:core:pair(fst, snd) } -> pair(reifyUnchecked(fst), myreify(snd)) | _ -> error("not pair") end;
}

equalityTest(myreify(reflect(42)), 42, Integer, silver_tests);
equalityTest(myreify(reflect("hello")), "hello", String, silver_tests);
equalityTest(myreify(reflect(pair("abc", 123))), pair("abc", 123), Pair<String Integer>, silver_tests);

wrongCode "a has kind * -> * -> *, but kind * is expected here" {
  function badKind
  Semigroupoid a, runtimeTypeable a => Integer ::=
  {
    return 42;
  }
}

nonterminal RTNT;
production rtProd
runtimeTypeable a => top::RTNT ::= fn::(Integer ::= a)
{}

global rtCaseRes::Integer =
  case rtProd(\ xs::[Integer] -> length(xs)) of
  | rtProd(f) -> f(reifyUnchecked(reflect([1, 2, 3])))
  end;
equalityTest(rtCaseRes, 3, Integer, silver_tests);

nonterminal SNT;
production sProd
i1 subset i2 => top::SNT ::= x::Decorated a with i2  f::([String] ::= Decorated a with i1)
{}

global sCaseRes::[String] =
  case sProd(decorate mkDExpr() with {env1 = ["a"]; env2 = ["2"];}, \ x::Decorated DExpr with {env2} -> x.env2) of
  | sProd(x, f) -> f(partialUndecorate(x))
  end;
equalityTest(sCaseRes, ["2"], [String], silver_tests);

function transitiveSubset
i1 subset i2, i2 subset i3, i3 subset i4 => Decorated a with i1 ::= x::Decorated a with i4
{
  return partialUndecorate(x);
}

function transitiveSubset2
i1 subset {env1}, {env1, env2} subset i2 => Decorated a with i1 ::= x::Decorated a with i2
{
  return partialUndecorate(x);
}

wrongCode "i3 is not a subset of i2 (arising from the use of partialUndecorate)" {
  function transitiveSubsetBad
  i1 subset i2, i2 subset i3, i3 subset i4 => Decorated a with i3 ::= x::Decorated a with i2
  {
    return partialUndecorate(x);
  }
}

wrongCode "i1 is not a subset of i2 (arising from the use of partialUndecorate)" {
  function transitiveSubsetBad2
  i1 subset {env1, env2}, {env1} subset i2 => Decorated a with i1 ::= x::Decorated a with i2
  {
    return partialUndecorate(x);
  }
}
