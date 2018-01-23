
import core:reflect;
import silver:langutil;
import silver:langutil:pp;

function lessHackyUnparse
String ::= x::a
{
  return show(80, reflect(x).pp);
}

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
  s"""[silver_features:addExpr(silver_features:intConstExpr(2, silver_features:lineNum=1), silver_features:idExpr("asdf", silver_features:lineNum=2), silver_features:lineNum=3), silver_features:intConstExpr(5, silver_features:lineNum=4), silver_features:decExpr(<OBJECT :: Decorated silver_features:Expr>, silver_features:lineNum=4)]""",
  String, silver_tests);

function reifyResToString
String ::= res::Either<String a>
{
  return case res of
    left(msg) -> msg
  | right(x) -> lessHackyUnparse(x)
  end;
}

global reifyRes1::Either<String Expr> = reify(nonterminalAST("silver_features:intConstExpr", consAST(integerAST(1), nilAST()), consNamedAST(namedAST("silver_features:lineNum", integerAST(2)), nilNamedAST())));

equalityTest(
  reifyResToString(reifyRes1),
  "silver_features:intConstExpr(1, silver_features:lineNum=2)",
  String, silver_tests);

equalityTest(fromRight(reifyRes1, idExpr("error", lineNum=-1)).lineNum, 2, Integer, silver_tests);

equalityTest(
  reifyResToString(reify(reflect([testExpr, intConstExpr(5, lineNum=4), decExpr(decorate testExpr with {}, lineNum=4)]))),
  lessHackyUnparse([testExpr, intConstExpr(5, lineNum=4), decExpr(decorate testExpr with {}, lineNum=4)]),
  String, silver_tests);

equalityTest(
  reifyResToString(reify(reflect(pair(pair(1, 2), pair(3, 4))))),
  lessHackyUnparse(pair(pair(1, 2), pair(3, 4))),
  String, silver_tests);

nonterminal Foo;

abstract production existentialFoo
top::Foo ::= a
{}

equalityTest(
  reifyResToString(reify(reflect(existentialFoo(existentialFoo(42))))),
  lessHackyUnparse(existentialFoo(existentialFoo(42))),
  String, silver_tests);

global testVal::Pair<Pair<Integer (String ::= Float)> Pair<String Unit>> = pair(pair(1, \ f::Float -> toString(f)), pair("a", unit()));

global reifyRes2::Either<String Pair<Pair<Integer (String ::= Float)> Pair<String Unit>>> = reify(anyAST(testVal));

equalityTest(reifyResToString(reifyRes2), lessHackyUnparse(testVal), String, silver_tests);

global reifyRes3::Either<String Pair<Pair<Integer (String ::= Float)> Pair<String Unit>>> = reify(reflect(testVal));

equalityTest(reifyResToString(reifyRes3), lessHackyUnparse(testVal), String, silver_tests);


nonterminal Bar<a>;

abstract production generalBar
top::Bar<(a ::= a)> ::= x::a
{}

global reifyRes4::Either<String Bar<(Integer ::= Integer)>> = reify(reflect(generalBar(1)));

equalityTest(
  reifyResToString(reifyRes4),
  lessHackyUnparse(generalBar(1)),
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

equalityTest(reifyResToString(reifyRes6), "[]", String, silver_tests);

global reifyRes7::Either<String [[[Integer]]]> = reify(listAST(foldAST([listAST(nilAST()), listAST(foldAST([listAST(foldAST([]))])), listAST(foldAST([listAST(foldAST([integerAST(4)]))]))])));

equalityTest(reifyResToString(reifyRes7), "[[], [[]], [[4]]]", String, silver_tests);

equalityTest(
  reifyResToString(reify(listAST(foldAST([listAST(nilAST()), listAST(foldAST([listAST(foldAST([]))])), listAST(foldAST([listAST(foldAST([integerAST(4)]))]))])))),
  "[[], [[]], [[4]]]",
  String, silver_tests);

equalityTest(
  reifyResToString(reify(listAST(foldAST([listAST(nilAST()), listAST(foldAST([listAST(foldAST([floatAST(3.4)]))])), listAST(foldAST([listAST(foldAST([integerAST(4)]))]))])))),
  "Reification error at [_, _, [[?, ...], ...], ...]:\nreify is constructing Float, but found Integer AST.",
  String, silver_tests);

equalityTest(
  reifyResToString(reify(nonterminalAST("silver_features:addExpr", foldAST([nonterminalAST("silver_features:intConstExpr", foldAST([integerAST(42)]), consNamedAST(namedAST("foobar", floatAST(0.1)), nilNamedAST())), nonterminalAST("silver_features:idExpr", foldAST([stringAST("a")]), consNamedAST(namedAST("silver_features:lineNum", integerAST(1)), nilNamedAST()))]), consNamedAST(namedAST("silver_features:lineNum", integerAST(2)), nilNamedAST())))),
  "Reification error at silver_features:addExpr(?, _):\nProduction silver_features:intConstExpr expected silver_features:lineNum annotation(s), but got foobar.",
  String, silver_tests);

equalityTest(
  reifyResToString(reify(nonterminalAST("silver_features:addExpr", foldAST([nonterminalAST("silver_features:intConstExpr", foldAST([integerAST(42)]), consNamedAST(namedAST("silver_features:lineNum", floatAST(0.1)), nilNamedAST())), nonterminalAST("silver_features:idExpr", foldAST([stringAST("a")]), consNamedAST(namedAST("silver_features:lineNum", integerAST(1)), nilNamedAST()))]), consNamedAST(namedAST("silver_features:lineNum", integerAST(2)), nilNamedAST())))),
  "Reification error at silver_features:addExpr(silver_features:intConstExpr(silver_features:lineNum=?), _):\nreify is constructing Integer, but found Float AST.",
  String, silver_tests);

equalityTest(
  reifyResToString(reify(nonterminalAST("silver_features:addExpr", foldAST([nonterminalAST("silver_features:intConstExpr", foldAST([integerAST(42)]), consNamedAST(namedAST("silver_features:lineNum", integerAST(0)), nilNamedAST())), nonterminalAST("silver_features:idExpr", foldAST([stringAST("a"), stringAST("b")]), consNamedAST(namedAST("silver_features:lineNum", integerAST(1)), nilNamedAST()))]), consNamedAST(namedAST("silver_features:lineNum", integerAST(2)), nilNamedAST())))),
  "Reification error at silver_features:addExpr(_, ?):\nProduction silver_features:idExpr expected 1 child(ren), but got 2.",
  String, silver_tests);

function reifySkolem
Either<String a> ::= x::AST
{
  return reify(x);
}

-- This will have some sort of runtime type error involving skolems, but we can't test for it exactly since the exact message may vary.
equalityTest(true, case reifySkolem(floatAST(4.0)) of left(_) -> true | right(_) -> false end, Boolean, silver_tests);

function reifySkolem2
Either<String (a ::= Integer)> ::= 
{
  local fn::(a ::= Integer) = \ i::Integer -> error(toString(i));
  return reify(anyAST(fn));
}

equalityTest(true, case reifySkolem2() of left(_) -> false | right(_) -> true end, Boolean, silver_tests);

-- TODO: Tests for partial application of functions
