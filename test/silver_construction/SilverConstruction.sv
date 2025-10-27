grammar silver_construction;

import silver:compiler:extension:patternmatching;
import silver:compiler:extension:silverconstruction;
import silver:compiler:definition:core;
import silver:compiler:definition:type:syntax;
import silver:compiler:modification:list;

-- TESTING Silver_Expr

function testExprBool
silver:compiler:definition:core:Expr ::= v1::Boolean
{
    return if v1 then Silver_Expr {true} else Silver_Expr {false};
}

equalityTest(genericShow(testExprBool(true)), "silver:compiler:definition:core:Expr { true }", String, silver_construction_tests);
equalityTest(genericShow(testExprBool(false)), "silver:compiler:definition:core:Expr { false }", String, silver_construction_tests);

equalityTest(genericShow(Silver_Expr{1}), "silver:compiler:definition:core:Expr { 1 }", String, silver_construction_tests);
equalityTest(genericShow(Silver_Expr{1.343}), "silver:compiler:definition:core:Expr { 1.343 }", String, silver_construction_tests);
equalityTest(genericShow(Silver_Expr{[dog, doggy, doggies]}), "silver:compiler:definition:core:Expr { [ dog, doggy, doggies ] }", String, silver_construction_tests);

-- TESTING Silver_Pattern

function testPatternBools
Pattern ::= v1::Boolean v2::Boolean
{
    local a::Pattern = if v1 then Silver_Pattern {true} else Silver_Pattern {false};
    local b::Pattern = if v2 then Silver_Pattern {true} else Silver_Pattern {false};
    return Silver_Pattern { silver:core:pair(fst=$Pattern{^a}, fst=$Pattern{^b}) };
}

equalityTest(genericShow(testPatternBools(true, true)), "silver:compiler:extension:patternmatching:Pattern { silver:core:pair(fst=true, fst=true) }", String, silver_construction_tests);
equalityTest(genericShow(testPatternBools(false, true)), "silver:compiler:extension:patternmatching:Pattern { silver:core:pair(fst=false, fst=true) }", String, silver_construction_tests);

equalityTest(genericShow(Silver_Pattern {8}), "silver:compiler:extension:patternmatching:Pattern { 8 }", String, silver_construction_tests);
equalityTest(genericShow(Silver_Pattern {8.99999}), "silver:compiler:extension:patternmatching:Pattern { 8.99999 }", String, silver_construction_tests);
equalityTest(genericShow(Silver_Pattern {[a,b,c]}), "silver:compiler:extension:patternmatching:Pattern { [a, b, c] }", String, silver_construction_tests);

-- TESTING Silver_TypeExpr
equalityTest(genericShow(Silver_TypeExpr { Boolean }), "silver:compiler:definition:type:syntax:TypeExpr { Boolean }", String, silver_construction_tests);
equalityTest(genericShow(Silver_TypeExpr { Integer }), "silver:compiler:definition:type:syntax:TypeExpr { Integer }", String, silver_construction_tests);
equalityTest(genericShow(Silver_TypeExpr { Float }), "silver:compiler:definition:type:syntax:TypeExpr { Float }", String, silver_construction_tests);
equalityTest(genericShow(Silver_TypeExpr {Pair <String Integer>}), "silver:compiler:definition:type:syntax:TypeExpr { Pair<String Integer> }", String, silver_construction_tests);
equalityTest (genericShow(Silver_TypeExpr { [Integer] }), "silver:compiler:definition:type:syntax:TypeExpr { [Integer] }", String, silver_construction_tests);
