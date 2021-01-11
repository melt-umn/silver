import silver:testing;

import silver:compiler:extension:patternmatching;
import silver:compiler:extension:silverconstruction;
import silver:compiler:definition:core;

-- TESTING Silver_Expr

function testExprBool
silver:compiler:definition:core:Expr ::= v1::Boolean
{
    return if v1 then Silver_Expr {true} else Silver_Expr {false};
}

equalityTest(hackUnparse(testExprBool(true)), "silver:compiler:definition:core:trueConst('true')", String, silver_tests);
equalityTest(hackUnparse(testExprBool(false)), "silver:compiler:definition:core:falseConst('false')", String, silver_tests);

equalityTest(hackUnparse(Silver_Expr{1}), "silver:compiler:definition:core:intConst('1')", String, silver_tests);

-- TESTING Silver_Pattern

function testPatternBools
Pattern ::= v1::Boolean v2::Boolean
{
    local a::Pattern = if v1 then Silver_Pattern {true} else Silver_Pattern {false};
    local b::Pattern = if v2 then Silver_Pattern {true} else Silver_Pattern {false};
    return Silver_Pattern { silver:core:pair($Pattern{a}, $Pattern{b}) };
}

equalityTest(hackUnparse(testPatternBools(true, true)), "silver:compiler:extension:patternmatching:prodAppPattern(silver:compiler:definition:core:qNameCons(silver:compiler:definition:core:nameIdLower('silver'), ':', silver:compiler:definition:core:qNameCons(silver:compiler:definition:core:nameIdLower('core'), ':', silver:compiler:definition:core:qNameId(silver:compiler:definition:core:nameIdLower('pair')))), '(', silver:compiler:extension:patternmatching:patternList_snoc(silver:compiler:extension:patternmatching:patternList_one(silver:compiler:extension:patternmatching:truePattern('true')), ',', silver:compiler:extension:patternmatching:truePattern('true')), ')')", String, silver_tests);
equalityTest(hackUnparse(testPatternBools(false, true)), "silver:compiler:extension:patternmatching:prodAppPattern(silver:compiler:definition:core:qNameCons(silver:compiler:definition:core:nameIdLower('silver'), ':', silver:compiler:definition:core:qNameCons(silver:compiler:definition:core:nameIdLower('core'), ':', silver:compiler:definition:core:qNameId(silver:compiler:definition:core:nameIdLower('pair')))), '(', silver:compiler:extension:patternmatching:patternList_snoc(silver:compiler:extension:patternmatching:patternList_one(silver:compiler:extension:patternmatching:falsePattern('false')), ',', silver:compiler:extension:patternmatching:truePattern('true')), ')')", String, silver_tests);

-- TESTING Silver_TypeExpr
--function testTypeExprBool
--TypeExpr ::= v1::Boolean
--{
--    return if v1 then Silver_TypeExpr {true} else Silver_TypeExpr {false};
--}

--equalityTest(hackUnparse(testTypeExprBool(true)), "bogus", String, silver_tests);