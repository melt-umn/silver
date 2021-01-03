grammar silver_features:rewrite:expreval;

import silver:testing;
import silver_features;

global test1::Expr = parseExpr("1 + (2 * 3)");
global result1a::Maybe<Expr> = rewriteWith(eval, test1);
equalityTest(result1a.isJust, true, Boolean, silver_tests);
equalityTest(showExpr(fromMaybe(const(12345), result1a)), "7", String, silver_tests);
global result1b::Expr = test1.eval;
equalityTest(showExpr(result1b), "7", String, silver_tests);

global test2::Expr = parseExpr("7 + 4 - ((1 + 1) / 0)");
global result2a::Maybe<Expr> = rewriteWith(eval, test2);
equalityTest(result2a.isJust, true, Boolean, silver_tests);
equalityTest(showExpr(fromMaybe(const(12345), result2a)), "-2 / 0", String, silver_tests);
global result2b::Expr = test2.eval;
equalityTest(showExpr(result2b), "-2 / 0", String, silver_tests);

global test3::Expr = parseExpr("(2 + 2) / 6");
global result3a::Maybe<Expr> = rewriteWith(eval, test3);
equalityTest(result3a.isJust, true, Boolean, silver_tests);
equalityTest(showExpr(fromMaybe(const(12345), result3a)), "2 / 3", String, silver_tests);
global result3b::Expr = test3.eval;
equalityTest(showExpr(result3b), "2 / 3", String, silver_tests);

global test4::Expr = parseExpr("1 + 1 / (1 + 1 / (1 + 1 / (1 + 1 / (1 + 1 / (1 + 1 / (1 + 1))))))");
global result4a::Maybe<Expr> = rewriteWith(eval, test4);
equalityTest(result4a.isJust, true, Boolean, silver_tests);
equalityTest(showExpr(fromMaybe(const(12345), result4a)), "34 / 21", String, silver_tests);
global result4b::Expr = test4.eval;
equalityTest(showExpr(result4b), "34 / 21", String, silver_tests);

global test5::Expr = parseExpr("let a = 1 / 2 in let b = a * 2 in a + b");
global result5a::Maybe<Expr> = rewriteWith(eval, test5);
equalityTest(result5a.isJust, true, Boolean, silver_tests);
equalityTest(showExpr(fromMaybe(const(12345), result5a)), "3 / 2", String, silver_tests);
global result5b::Expr = test5.eval;
equalityTest(showExpr(result5b), "3 / 2", String, silver_tests);

global test6::Expr = parseExpr("0 + 1 * a - 2 / b");
global result6a::Maybe<Expr> = rewriteWith(eval, test6);
equalityTest(result6a.isJust, true, Boolean, silver_tests);
equalityTest(showExpr(fromMaybe(const(12345), result6a)), "((a * b) - 2) / b", String, silver_tests);
global result6b::Expr = test6.eval;
equalityTest(showExpr(result6b), "((a * b) - 2) / b", String, silver_tests);
