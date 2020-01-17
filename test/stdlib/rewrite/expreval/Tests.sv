grammar stdlib:rewrite:expreval;

import silver:testing;
import lib:extcore;
import stdlib;

global e1::Expr = parseExpr("1 + (2 + 3)");
global result1::Maybe<Expr> = rewriteWith(eval, e1);
equalityTest(result1.isJust, true, Boolean, core_tests);
equalityTest(showExpr(fromMaybe(const(12345), result1)), "6", String, core_tests);
