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

derive Eq on EqThing;

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
