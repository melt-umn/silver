grammar silver_features;

annotation eqa::Integer;
nonterminal EqThing<a> with eqa;
production eqThingA
top::EqThing<a> ::= x::a
{}
production eqThingB
top::EqThing<a> ::= x::String y::Boolean
{}
global eqThingBInt::(EqThing<Integer> ::= String Boolean Integer) = eqThingB(_, _, eqa=_);

derive Eq, Ord on EqThing;

equalityTest(eqThingA(42, eqa=3) == eqThingA(42, eqa=3), true, Boolean, silver_tests);
equalityTest(eqThingBInt("hello", true, 3) == eqThingBInt("hello", true, 3), true, Boolean, silver_tests);
equalityTest(eqThingBInt("hello", true, 3) == eqThingBInt("hello", false, 3), false, Boolean, silver_tests);
equalityTest(eqThingBInt("hello", true, 3) == eqThingBInt("hello", true, 123), false, Boolean, silver_tests);
equalityTest(eqThingA(42, eqa=3) == eqThingA(4, eqa=3), false, Boolean, silver_tests);
equalityTest(eqThingBInt("hello", false, 3) == eqThingA(42, eqa=3), false, Boolean, silver_tests);
equalityTest(eqThingA(eqThingBInt("a", false, 3), eqa=3) == eqThingA(eqThingBInt("a", false, 3), eqa=3), true, Boolean, silver_tests);
equalityTest(eqThingA(eqThingBInt("a", false, 3), eqa=3) == eqThingA(eqThingBInt("b", false, 3), eqa=3), false, Boolean, silver_tests);
equalityTest(eqThingA(eqThingBInt("a", false, 3), eqa=3) == eqThingA(eqThingBInt("a", false, 31234), eqa=3), false, Boolean, silver_tests);

equalityTest(eqThingA(42, eqa=3) != eqThingA(42, eqa=3), false, Boolean, silver_tests);
equalityTest(eqThingBInt("hello", true, 3) != eqThingBInt("hello", true, 3), false, Boolean, silver_tests);
equalityTest(eqThingBInt("hello", true, 3) != eqThingBInt("hello", false, 3), true, Boolean, silver_tests);
equalityTest(eqThingBInt("hello", true, 3) != eqThingBInt("hello", true, 123), true, Boolean, silver_tests);
equalityTest(eqThingA(42, eqa=3) != eqThingA(4, eqa=3), true, Boolean, silver_tests);
equalityTest(eqThingBInt("hello", false, 3) != eqThingA(42, eqa=3), true, Boolean, silver_tests);
equalityTest(eqThingA(eqThingBInt("a", false, 3), eqa=3) != eqThingA(eqThingBInt("a", false, 3), eqa=3), false, Boolean, silver_tests);
equalityTest(eqThingA(eqThingBInt("a", false, 3), eqa=3) != eqThingA(eqThingBInt("b", false, 3), eqa=3), true, Boolean, silver_tests);
equalityTest(eqThingA(eqThingBInt("a", false, 3), eqa=3) != eqThingA(eqThingBInt("a", false, 31234), eqa=3), true, Boolean, silver_tests);

equalityTest(compare(eqThingA(42, eqa=3), eqThingA(42, eqa=3)), 0, Integer, silver_tests);
equalityTest(compare(eqThingBInt("hello", true, 3), eqThingBInt("hello", true, 3)), 0, Integer, silver_tests);
equalityTest(compare(eqThingBInt("hello", true, 3), eqThingBInt("goodbye", true, 3)), 1, Integer, silver_tests);
equalityTest(compare(eqThingBInt("goodbye", true, 3), eqThingBInt("hello", true, 3)), -1, Integer, silver_tests);
equalityTest(compare(eqThingBInt("hello", true, 3), eqThingBInt("hello", false, 3)), 1, Integer, silver_tests);
equalityTest(compare(eqThingBInt("hello", false, 3), eqThingBInt("hello", true, 3)), -1, Integer, silver_tests);
equalityTest(compare(eqThingBInt("hello", true, 3), eqThingBInt("hello", true, 123)), -120, Integer, silver_tests);
equalityTest(compare(eqThingBInt("hello", true, 123), eqThingBInt("hello", true, 3)), 120, Integer, silver_tests);
equalityTest(compare(eqThingA(4, eqa=3), eqThingA(42, eqa=3)), -38, Integer, silver_tests);
equalityTest(compare(eqThingA(42, eqa=3), eqThingA(4, eqa=3)), 38, Integer, silver_tests);
equalityTest(compare(eqThingBInt("hello", false, 3), eqThingA(42, eqa=3)), 1, Integer, silver_tests);
equalityTest(compare(eqThingA(42, eqa=3), eqThingBInt("hello", false, 3)), -1, Integer, silver_tests);
equalityTest(compare(eqThingA(eqThingBInt("a", false, 3), eqa=3), eqThingA(eqThingBInt("a", false, 3), eqa=3)), 0, Integer, silver_tests);
equalityTest(compare(eqThingA(eqThingBInt("a", false, 3), eqa=3), eqThingA(eqThingBInt("b", false, 3), eqa=3)), -1, Integer, silver_tests);
equalityTest(compare(eqThingA(eqThingBInt("a", false, 3), eqa=3), eqThingA(eqThingBInt("a", false, 543), eqa=3)), -540, Integer, silver_tests);
