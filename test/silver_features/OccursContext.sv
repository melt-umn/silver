grammar silver_features;

inherited attribute extraInh1::String occurs on EqExpr;
inherited attribute extraInh2::Integer occurs on EqExpr;

function eqA
attribute isEqualTo<a> occurs on a, attribute isEqual {isEqualTo} occurs on a =>
Boolean ::= x::a y::a
{
  x.isEqualTo = y;
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
attribute isEqualTo<a> occurs on a, attribute isEqual {isEqualTo} occurs on a =>
Boolean ::= x::a y::a
{
  production z::a = x;
  production w::a = z;
  w.isEqualTo = y;
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
attribute isEqualTo<a> occurs on a, attribute isEqual {isEqualTo} occurs on a =>
Boolean ::= x::(a ::= ) y::(a ::= )
{
  production z::a = x();
  production w::a = y();
  w.isEqualTo = z;
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

instance attribute isEqualTo<a> occurs on a, attribute isEqual {isEqualTo} occurs on a => OCEq a {
  ocEq = \ x::a y::a -> decorate x with {isEqualTo = y;}.isEqual; 
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

nonterminal OCEqPair<a b> with isEqualTo, isEqual;
production ocEqPair
attribute isEqualTo<a> occurs on a, attribute isEqual {isEqualTo} occurs on a,
attribute isEqualTo<b> occurs on b, attribute isEqual {isEqualTo} occurs on b =>
top::OCEqPair<a b> ::= x::a y::b
{
  propagate isEqualTo, isEqual;
{-
  x.isEqualTo = case top.isEqualTo of ocEqPair(a, _) -> a end;
  y.isEqualTo = case top.isEqualTo of ocEqPair(_, a) -> a end;
  top.isEqual = x.isEqual && y.isEqual;
  -}
}

equalityTest(decorate ocEqPair(ee1, ee2) with {isEqualTo = ocEqPair(ee1, ee2);}.isEqual, true, Boolean, silver_tests);

-- Not supported: decorated match on polymorphic child
-- equalityTest(case decorate ocEqPair(ee1, ee2) with {isEqualTo = ocEqPair(ee1, ee2);} of ocEqPair(x, y) -> x.isEqual && y.isEqual end, true, Boolean, silver_tests);

equalityTest(ocEq(ocEqPair(ee1, ee2), ocEqPair(ee1, ee2)), true, Boolean, silver_tests);
equalityTest(ocEq(ocEqPair(ee1, ee2), ocEqPair(ee1, ee3)), false, Boolean, silver_tests);

wrongCode "Could not find an instance for attribute silver:core:isEqual {silver:core:isEqualTo} occurs on String (arising from the use of ocEqPair)" {
  global err::OCEqPair<EqExpr String> = ocEqPair(ee1, "abc");
}
wrongCode "Could not find an instance for attribute silver:core:isEqualTo<String> occurs on String (arising from the use of ocEqPair)" {
  global err::OCEqPair<EqExpr String> = ocEqPair(ee1, "abc");
}

synthesized attribute isEqual2::Boolean occurs on OCEqPair<a b>;
aspect production ocEqPair
top::OCEqPair<a b> ::= x::a y::b
{
  top.isEqual2 = x.isEqual && y.isEqual;
}

equalityTest(decorate ocEqPair(ee1, ee2) with {isEqualTo = ocEqPair(ee1, ee2);}.isEqual2, true, Boolean, silver_tests);

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
