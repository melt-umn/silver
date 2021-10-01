grammar silver_features;

inherited attribute extraInh1::String occurs on EqExpr;
inherited attribute extraInh2::Integer occurs on EqExpr;

function eqA
attribute compareTo<a {}> occurs on a, attribute isEqual {compareTo} occurs on a =>
Boolean ::= x::a y::a
{
  x.compareTo = y;
  return x.isEqual;
}

equalityTest(eqA(ee1, ee1), true, Boolean, silver_tests);
equalityTest(eqA(ee1, ee2), false, Boolean, silver_tests);
equalityTest(eqA(ee1, ee3), false, Boolean, silver_tests);
equalityTest(eqA(ee2, ee1), false, Boolean, silver_tests);
equalityTest(eqA(ee2, ee2), true, Boolean, silver_tests);
equalityTest(eqA(ee2, ee3), false, Boolean, silver_tests);
equalityTest(eqA(ee3, ee1), false, Boolean, silver_tests);
equalityTest(eqA(ee3, ee2), false, Boolean, silver_tests);
equalityTest(eqA(ee3, ee3), true, Boolean, silver_tests);

function eqB
attribute compareTo<a {}> occurs on a, attribute isEqual {compareTo} occurs on a =>
Boolean ::= x::a y::a
{
  production z::a = x;
  production w::a = z;
  w.compareTo = y;
  return w.isEqual;
}

equalityTest(eqB(ee1, ee1), true, Boolean, silver_tests);
equalityTest(eqB(ee1, ee2), false, Boolean, silver_tests);
equalityTest(eqB(ee1, ee3), false, Boolean, silver_tests);
equalityTest(eqB(ee2, ee1), false, Boolean, silver_tests);
equalityTest(eqB(ee2, ee2), true, Boolean, silver_tests);
equalityTest(eqB(ee2, ee3), false, Boolean, silver_tests);
equalityTest(eqB(ee3, ee1), false, Boolean, silver_tests);
equalityTest(eqB(ee3, ee2), false, Boolean, silver_tests);
equalityTest(eqB(ee3, ee3), true, Boolean, silver_tests);

function eqC
attribute compareTo<a {}> occurs on a, attribute isEqual {compareTo} occurs on a =>
Boolean ::= x::(a ::= ) y::(a ::= )
{
  production z::a = x();
  production w::a = y();
  w.compareTo = z;
  return w.isEqual;
}

equalityTest(eqC(\ -> ee1, \ -> ee1), true, Boolean, silver_tests);
equalityTest(eqC(\ -> ee1, \ -> ee2), false, Boolean, silver_tests);
equalityTest(eqC(\ -> ee1, \ -> ee3), false, Boolean, silver_tests);
equalityTest(eqC(\ -> ee2, \ -> ee1), false, Boolean, silver_tests);
equalityTest(eqC(\ -> ee2, \ -> ee2), true, Boolean, silver_tests);
equalityTest(eqC(\ -> ee2, \ -> ee3), false, Boolean, silver_tests);
equalityTest(eqC(\ -> ee3, \ -> ee1), false, Boolean, silver_tests);
equalityTest(eqC(\ -> ee3, \ -> ee2), false, Boolean, silver_tests);
equalityTest(eqC(\ -> ee3, \ -> ee3), true, Boolean, silver_tests);

class OCEq a {
  ocEq :: (Boolean ::= a a);
}

instance OCEq Integer
{
  ocEq = \ x::Integer y::Integer -> x == y;
}

instance OCEq String
{
  ocEq = \ x::String y::String -> x == y;
}

instance attribute compareTo<a {}> occurs on a, attribute isEqual {compareTo} occurs on a => OCEq a {
  ocEq = \ x::a y::a -> decorate x with {compareTo = decorate y with {};}.isEqual; 
}

equalityTest(ocEq(ee1, ee1), true, Boolean, silver_tests);
equalityTest(ocEq(ee1, ee2), false, Boolean, silver_tests);
equalityTest(ocEq(ee1, ee3), false, Boolean, silver_tests);
equalityTest(ocEq(ee2, ee1), false, Boolean, silver_tests);
equalityTest(ocEq(ee2, ee2), true, Boolean, silver_tests);
equalityTest(ocEq(ee2, ee3), false, Boolean, silver_tests);
equalityTest(ocEq(ee3, ee1), false, Boolean, silver_tests);
equalityTest(ocEq(ee3, ee2), false, Boolean, silver_tests);
equalityTest(ocEq(ee3, ee3), true, Boolean, silver_tests);

nonterminal OCEqPair<a b> with compareTo, isEqual;
production ocEqPair
attribute compareTo<a {}> occurs on a, attribute isEqual {compareTo} occurs on a,
attribute compareTo<b {}> occurs on b, attribute isEqual {compareTo} occurs on b =>
top::OCEqPair<a b> ::= x::a y::b
{
  propagate compareTo, isEqual;
{-
  x.compareTo = case top.compareTo of ocEqPair(a, _) -> a end;
  y.compareTo = case top.compareTo of ocEqPair(_, a) -> a end;
  top.isEqual = x.isEqual && y.isEqual;
  -}
}

equalityTest(decorate ocEqPair(ee1, ee2) with {compareTo = decorate ocEqPair(ee1, ee2) with {};}.isEqual, true, Boolean, silver_tests);

function requireDec
Decorated a with i ::= x::Decorated a with i
{ return x; }

-- decorated match on polymorphic child
equalityTest(
  case decorate ocEqPair(ee1, ee2) with {compareTo = decorate ocEqPair(ee1, ee2) with {};} of
  | ocEqPair(x, y) -> requireDec(x).isEqual && requireDec(y).isEqual
  end, true, Boolean, silver_tests);

equalityTest(ocEq(ocEqPair(ee1, ee2), ocEqPair(ee1, ee2)), true, Boolean, silver_tests);
equalityTest(ocEq(ocEqPair(ee1, ee2), ocEqPair(ee1, ee3)), false, Boolean, silver_tests);

wrongCode "Could not find an instance for attribute silver:core:isEqual {silver:core:compareTo} occurs on String (arising from the use of ocEqPair)" {
  global err::OCEqPair<EqExpr String> = ocEqPair(ee1, "abc");
}
wrongCode "Could not find an instance for attribute silver:core:compareTo<String {}> occurs on String (arising from the use of ocEqPair)" {
  global err::OCEqPair<EqExpr String> = ocEqPair(ee1, "abc");
}

synthesized attribute isEqual2::Boolean occurs on OCEqPair<a b>;
aspect production ocEqPair
top::OCEqPair<a b> ::= x::a y::b
{
  top.isEqual2 = x.isEqual && y.isEqual;
}

equalityTest(decorate ocEqPair(ee1, ee2) with {compareTo = decorate ocEqPair(ee1, ee2) with {};}.isEqual2, true, Boolean, silver_tests);

synthesized attribute prodName::String;
nonterminal OCExpr with prodName;
production constOCE
top::OCExpr ::= i::Integer
{ top.prodName = "const"; }
production addOCE
top::OCExpr ::= e1::OCExpr e2::OCExpr
{ top.prodName = "add"; }

inherited attribute prodNameIn::String;
nonterminal OCThing with prodNameIn, prodName;
production ocThing
top::OCThing ::=
{ top.prodName = top.prodNameIn; }

class ProdName1 a {
  prodName1 :: (String ::= a);
}
instance attribute prodName i occurs on a => ProdName1 Decorated a with i {
  prodName1 = (.prodName);
}
instance attribute prodName {} occurs on a => ProdName1 a {
  prodName1 = (.prodName);
}

equalityTest(prodName1(constOCE(42)), "const", String, silver_tests);
equalityTest(prodName1(decorate ocThing() with {prodNameIn = "thing";}), "thing", String, silver_tests);

class attribute prodName {} occurs on a => ProdName2 a {
  prodName2 :: (String ::= a) = (.prodName);
}
instance ProdName2 OCExpr {}

equalityTest(prodName2(constOCE(42)), "const", String, silver_tests);

function prodName3
ProdName2 a => String ::= x::a
{
  return x.prodName;
}

equalityTest(prodName3(constOCE(42)), "const", String, silver_tests);

wrongCode "error: Attribute 'prodName' does not occur on 'a'" {
  function prodName4
  String ::= x::a
  { return x.prodName; }
}

nonterminal OCPolyWrap with prodNameIn, prodName;
production ocPolyWrap
attribute prodNameIn occurs on a, attribute prodName {prodNameIn} occurs on a =>
top::OCPolyWrap ::= x::a
{
  x.prodNameIn = top.prodNameIn;
  top.prodName = x.prodName;
}

equalityTest(decorate ocPolyWrap(ocThing()) with {prodName = "blah";}.prodName, "blah", String, silver_tests);
equalityTest(decorate ocPolyWrap(ocPolyWrap(ocThing())) with {prodName = "blah";}.prodName, "blah", String, silver_tests);
equalityTest(
  case decorate ocPolyWrap(ocThing()) with {prodName = "blah";} of
  | ocPolyWrap(thing) -> thing.prodName
  end, "blah", String, silver_tests);
