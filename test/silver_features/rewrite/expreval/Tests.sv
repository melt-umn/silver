grammar silver_features:rewrite:expreval;

import silver:testing;
import lib:extcore;
import silver_features;

global result1::Maybe<Expr> = rewriteWith(eval, parseExpr("1 + (2 * 3)"));
equalityTest(result1.isJust, true, Boolean, silver_tests);
equalityTest(showExpr(fromMaybe(const(12345), result1)), "7", String, silver_tests);

global result2::Maybe<Expr> = rewriteWith(eval, parseExpr("7 + 4 - ((1 + 1) / 0)"));
equalityTest(result2.isJust, true, Boolean, silver_tests);
equalityTest(showExpr(fromMaybe(const(12345), result2)), "-2 / 0", String, silver_tests);

global result3::Maybe<Expr> = rewriteWith(eval, parseExpr("(2 + 2) / 6"));
equalityTest(result3.isJust, true, Boolean, silver_tests);
equalityTest(showExpr(fromMaybe(const(12345), result3)), "2 / 3", String, silver_tests);

global result4::Maybe<Expr> = rewriteWith(eval, parseExpr("1 + 1 / (1 + 1 / (1 + 1 / (1 + 1 / (1 + 1 / (1 + 1 / (1 + 1))))))"));
equalityTest(result4.isJust, true, Boolean, silver_tests);
equalityTest(showExpr(fromMaybe(const(12345), result4)), "34 / 21", String, silver_tests);
