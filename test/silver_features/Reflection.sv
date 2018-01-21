
import core:reflect;
import silver:langutil;
import silver:langutil:pp;

annotation lineNum::Integer;

nonterminal Expr with lineNum;

abstract production addExpr
top::Expr ::= e1::Expr e2::Expr
{}

abstract production intConstExpr
top::Expr ::= i::Integer
{}

abstract production idExpr
top::Expr ::= id::String
{}

abstract production decExpr
top::Expr ::= d::Decorated Expr
{}

global testExpr::Expr = addExpr(intConstExpr(2, lineNum=1), idExpr("asdf", lineNum=2), lineNum=3);

equalityTest(
  show(80, reflect([testExpr, intConstExpr(5, lineNum=4), decExpr(decorate testExpr with {}, lineNum=4)]).pp),
  s"""[silver_features:addExpr(silver_features:intConstExpr(2, silver_features:lineNum=1), silver_features:idExpr("asdf", silver_features:lineNum=2), silver_features:lineNum=3), silver_features:intConstExpr(5, silver_features:lineNum=4), silver_features:decExpr(<OBJECT>, silver_features:lineNum=4)]""",
  String, silver_tests);
  
global reifyRes1::Expr = reify(nonterminalAST("silver_features:intConstExpr", consAST(integerAST(1), nilAST()), consNamedAST(namedAST("silver_features:lineNum", integerAST(2)), nilNamedAST())));

equalityTest(
  hackUnparse(reifyRes1),
  "silver_features:intConstExpr(1)",
  String, silver_tests);
  
equalityTest(reifyRes1.lineNum, 2, Integer, silver_tests);

equalityTest(
  hackUnparse(reify(reflect([testExpr, intConstExpr(5, lineNum=4), decExpr(decorate testExpr with {}, lineNum=4)]))),
  hackUnparse([testExpr, intConstExpr(5, lineNum=4), decExpr(decorate testExpr with {}, lineNum=4)]),
  String, silver_tests);

equalityTest(
  hackUnparse(reify(reflect(pair(pair(1, 2), pair(3, 4))))),
  hackUnparse(pair(pair(1, 2), pair(3, 4))),
  String, silver_tests);
  
nonterminal Foo;

abstract production existentialFoo
top::Foo ::= a
{}

equalityTest(
  hackUnparse(reify(reflect(existentialFoo(existentialFoo(42))))),
  hackUnparse(existentialFoo(existentialFoo(42))),
  String, silver_tests);

global testVal::Pair<Pair<Integer (String ::= Float)> Pair<String Unit>> = pair(pair(1, \ f::Float -> toString(f)), pair("a", unit()));

global reifyRes2::Pair<Pair<Integer (String ::= Float)> Pair<String Unit>> = reify(anyAST(testVal));

equalityTest(
  hackUnparse(reifyRes2),
  hackUnparse(testVal),
  String, silver_tests);

global reifyRes3::Pair<Pair<Integer (String ::= Float)> Pair<String Unit>> = reify(reflect(testVal));

equalityTest(
  hackUnparse(reifyRes3),
  hackUnparse(testVal),
  String, silver_tests);


nonterminal Bar<a>;

abstract production generalBar
top::Bar<(a ::= a)> ::= x::a
{}

global asdf::Bar<(Integer ::= Integer)> = generalBar(1);

annotation anno1::Integer;
annotation anno2::Integer;

nonterminal Baz with anno1, anno2;

abstract production baz
top::Baz ::=
{}

global reifyRes4::Baz = reify(nonterminalAST("silver_features:baz", nilAST(), consNamedAST(namedAST("silver_features:anno1", integerAST(1)), consNamedAST(namedAST("silver_features:anno2", integerAST(2)), nilNamedAST()))));

equalityTest(reifyRes4.anno1, 1, Integer, silver_tests);
equalityTest(reifyRes4.anno2, 2, Integer, silver_tests);

-- TODO: Tests for partial application of functions
