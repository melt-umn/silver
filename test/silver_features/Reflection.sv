
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

function reifyResToString
String ::= res::Either<String a>
{
  return case res of
    left(msg) -> msg
  | right(a) -> hackUnparse(a)
  end;
}

global reifyRes1::Either<String Expr> = reify(nonterminalAST("silver_features:intConstExpr", consAST(integerAST(1), nilAST()), consNamedAST(namedAST("silver_features:lineNum", integerAST(2)), nilNamedAST())));

equalityTest(
  reifyResToString(reifyRes1),
  "silver_features:intConstExpr(1)",
  String, silver_tests);

equalityTest(fromRight(reifyRes1, idExpr("error", lineNum=-1)).lineNum, 2, Integer, silver_tests);

equalityTest(
  reifyResToString(reify(reflect([testExpr, intConstExpr(5, lineNum=4), decExpr(decorate testExpr with {}, lineNum=4)]))),
  hackUnparse([testExpr, intConstExpr(5, lineNum=4), decExpr(decorate testExpr with {}, lineNum=4)]),
  String, silver_tests);

equalityTest(
  reifyResToString(reify(reflect(pair(pair(1, 2), pair(3, 4))))),
  hackUnparse(pair(pair(1, 2), pair(3, 4))),
  String, silver_tests);

nonterminal Foo;

abstract production existentialFoo
top::Foo ::= a
{}

equalityTest(
  reifyResToString(reify(reflect(existentialFoo(existentialFoo(42))))),
  hackUnparse(existentialFoo(existentialFoo(42))),
  String, silver_tests);

global testVal::Pair<Pair<Integer (String ::= Float)> Pair<String Unit>> = pair(pair(1, \ f::Float -> toString(f)), pair("a", unit()));

global reifyRes2::Either<String Pair<Pair<Integer (String ::= Float)> Pair<String Unit>>> = reify(anyAST(testVal));

equalityTest(
  reifyResToString(reifyRes2),
  hackUnparse(testVal),
  String, silver_tests);

global reifyRes3::Either<String Pair<Pair<Integer (String ::= Float)> Pair<String Unit>>> = reify(reflect(testVal));

equalityTest(
  reifyResToString(reifyRes3),
  hackUnparse(testVal),
  String, silver_tests);


nonterminal Bar<a>;

abstract production generalBar
top::Bar<(a ::= a)> ::= x::a
{}

global reifyRes4::Either<String Bar<(Integer ::= Integer)>> = reify(reflect(generalBar(1)));

equalityTest(
  reifyResToString(reifyRes4),
  hackUnparse(generalBar(1)),
  String, silver_tests);

annotation anno1::Integer;
annotation anno2::Integer;

nonterminal Baz with anno1, anno2;

abstract production baz
top::Baz ::=
{}

global reifyRes5::Either<String Baz> = reify(nonterminalAST("silver_features:baz", nilAST(), consNamedAST(namedAST("silver_features:anno1", integerAST(1)), consNamedAST(namedAST("silver_features:anno2", integerAST(2)), nilNamedAST()))));

equalityTest(fromRight(reifyRes5, baz(anno1=-1, anno2=-2)).anno1, 1, Integer, silver_tests);
equalityTest(fromRight(reifyRes5, baz(anno1=-1, anno2=-2)).anno2, 2, Integer, silver_tests);

global foldAST::(ASTs ::= [AST]) = foldr(consAST, nilAST(), _);

global reifyRes6::Either<String [[[Integer]]]> = reify(listAST(foldAST([])));

equalityTest(
  reifyResToString(reifyRes6),
  "[]",
  String, silver_tests);

global reifyRes7::Either<String [[[Integer]]]> = reify(listAST(foldAST([listAST(nilAST()), listAST(foldAST([listAST(foldAST([]))])), listAST(foldAST([listAST(foldAST([integerAST(4)]))]))])));

equalityTest(
  reifyResToString(reifyRes7),
  "[[], [[]], [[4]]]",
  String, silver_tests);

equalityTest(
  reifyResToString(reify(listAST(foldAST([listAST(nilAST()), listAST(foldAST([listAST(foldAST([]))])), listAST(foldAST([listAST(foldAST([integerAST(4)]))]))])))),
  "[[], [[]], [[4]]]",
  String, silver_tests);

equalityTest(
  reifyResToString(reify(listAST(foldAST([listAST(nilAST()), listAST(foldAST([listAST(foldAST([floatAST(3.4)]))])), listAST(foldAST([listAST(foldAST([integerAST(4)]))]))])))),
  "Reification error at [_, _, [[?, ...], ...], ...]:\nreify is constructing Float, but found Integer AST.",
  String, silver_tests);

-- TODO: Tests for partial application of functions
