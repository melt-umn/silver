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

equalityTest(hackUnparse(testExprBool(true)), "silver:compiler:definition:core:trueConst('true')", String, silver_construction_tests);
equalityTest(hackUnparse(testExprBool(false)), "silver:compiler:definition:core:falseConst('false')", String, silver_construction_tests);

equalityTest(hackUnparse(Silver_Expr{1}), "silver:compiler:definition:core:intConst('1')", String, silver_construction_tests);
equalityTest(hackUnparse(Silver_Expr{1.343}), "silver:compiler:definition:core:floatConst('1.343')", String, silver_construction_tests);
equalityTest(hackUnparse(Silver_Expr{[dog, doggy, doggies]}), "silver:compiler:modification:list:fullList('[', silver:compiler:definition:core:exprsCons(silver:compiler:definition:core:baseExpr(silver:compiler:definition:core:qNameId(silver:compiler:definition:core:nameIdLower('dog'))), ',', silver:compiler:definition:core:exprsCons(silver:compiler:definition:core:baseExpr(silver:compiler:definition:core:qNameId(silver:compiler:definition:core:nameIdLower('doggy'))), ',', silver:compiler:definition:core:exprsSingle(silver:compiler:definition:core:baseExpr(silver:compiler:definition:core:qNameId(silver:compiler:definition:core:nameIdLower('doggies')))))), ']')", String, silver_construction_tests);

-- TESTING Silver_Pattern

function testPatternBools
Pattern ::= v1::Boolean v2::Boolean
{
    local a::Pattern = if v1 then Silver_Pattern {true} else Silver_Pattern {false};
    local b::Pattern = if v2 then Silver_Pattern {true} else Silver_Pattern {false};
    return Silver_Pattern { silver:core:pair(fst=$Pattern{a}, fst=$Pattern{b}) };
}

equalityTest(hackUnparse(testPatternBools(true, true)), "silver:compiler:extension:patternmatching:prodAppPattern(silver:compiler:definition:core:qNameCons(silver:compiler:definition:core:nameIdLower('silver'), ':', silver:compiler:definition:core:qNameCons(silver:compiler:definition:core:nameIdLower('core'), ':', silver:compiler:definition:core:qNameId(silver:compiler:definition:core:nameIdLower('pair')))), '(', silver:compiler:extension:patternmatching:patternList_snoc(silver:compiler:extension:patternmatching:patternList_one(silver:compiler:extension:patternmatching:truePattern('true')), ',', silver:compiler:extension:patternmatching:truePattern('true')), ')')", String, silver_construction_tests);
equalityTest(hackUnparse(testPatternBools(false, true)), "silver:compiler:extension:patternmatching:prodAppPattern(silver:compiler:definition:core:qNameCons(silver:compiler:definition:core:nameIdLower('silver'), ':', silver:compiler:definition:core:qNameCons(silver:compiler:definition:core:nameIdLower('core'), ':', silver:compiler:definition:core:qNameId(silver:compiler:definition:core:nameIdLower('pair')))), '(', silver:compiler:extension:patternmatching:patternList_snoc(silver:compiler:extension:patternmatching:patternList_one(silver:compiler:extension:patternmatching:falsePattern('false')), ',', silver:compiler:extension:patternmatching:truePattern('true')), ')')", String, silver_construction_tests);

equalityTest(hackUnparse(Silver_Pattern {8}), "silver:compiler:extension:patternmatching:intPattern('8')", String, silver_construction_tests);
equalityTest(hackUnparse(Silver_Pattern {8.99999}), "silver:compiler:extension:patternmatching:fltPattern('8.99999')", String, silver_construction_tests);
equalityTest(hackUnparse(Silver_Pattern {[a,b,c]}), "silver:compiler:extension:patternmatching:listPattern('[', silver:compiler:extension:patternmatching:patternList_snoc(silver:compiler:extension:patternmatching:patternList_snoc(silver:compiler:extension:patternmatching:patternList_one(silver:compiler:extension:patternmatching:varPattern(silver:compiler:definition:core:nameIdLower('a'))), ',', silver:compiler:extension:patternmatching:varPattern(silver:compiler:definition:core:nameIdLower('b'))), ',', silver:compiler:extension:patternmatching:varPattern(silver:compiler:definition:core:nameIdLower('c'))), ']')", String, silver_construction_tests);

-- TESTING Silver_TypeExpr
equalityTest(hackUnparse(Silver_TypeExpr { Boolean }), "silver:compiler:definition:type:syntax:booleanTypeExpr('Boolean')", String, silver_construction_tests);
equalityTest(hackUnparse(Silver_TypeExpr { Integer }), "silver:compiler:definition:type:syntax:integerTypeExpr('Integer')", String, silver_construction_tests);
equalityTest(hackUnparse(Silver_TypeExpr { Float }), "silver:compiler:definition:type:syntax:floatTypeExpr('Float')", String, silver_construction_tests);
equalityTest(hackUnparse(Silver_TypeExpr {Pair <String Integer>}), "silver:compiler:definition:type:syntax:appTypeExpr(silver:compiler:definition:type:syntax:nominalTypeExpr(silver:compiler:definition:core:qNameTypeId('Pair')), silver:compiler:definition:type:syntax:bTypeList('<', silver:compiler:definition:type:syntax:typeListCons(silver:compiler:definition:type:syntax:stringTypeExpr('String'), silver:compiler:definition:type:syntax:typeListSingle(silver:compiler:definition:type:syntax:integerTypeExpr('Integer'))), '>'))", String, silver_construction_tests);
equalityTest (hackUnparse(Silver_TypeExpr { [Integer] }), "silver:compiler:modification:list:listTypeExpr('[', silver:compiler:definition:type:syntax:integerTypeExpr('Integer'), ']')", String, silver_construction_tests);
