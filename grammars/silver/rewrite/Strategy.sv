grammar silver:rewrite;

-- Some of these Strategy productions have very generic names that conflict with core.
-- Users must explicitly import silver:core hiding these names, or perform a qualified import,
-- e.g. import silver:rewrite as s;

imports silver:core hiding all, fail, id, one, repeat, sequence;

function rewriteWith
runtimeTypeable a => Maybe<a> ::= s::Strategy x::a
{
  return map(reifyUnchecked, decorate s with {term = reflect(x);}.result);
}

inherited attribute term::AST;
synthesized attribute result::Maybe<AST>;

nonterminal Strategy with pp, term, result;

-- Basic combinators
abstract production id
top::Strategy ::=
{
  top.pp = pp"id()";
  top.result = just(top.term);
}

abstract production fail
top::Strategy ::=
{
  top.pp = pp"fail()";
  top.result = nothing();
}

abstract production sequence
top::Strategy ::= s1::Strategy s2::Strategy
{
  top.pp = pp"(${s1.pp} <* ${s2.pp})";
  s1.term = top.term;
  s2.term = s1.result.fromJust;
  top.result = bind(s1.result, \ AST -> s2.result);
}

abstract production choice
top::Strategy ::= s1::Strategy s2::Strategy
{
  top.pp = pp"(${s1.pp} <+ ${s2.pp})";
  s1.term = top.term;
  s2.term = top.term;
  top.result = orElse(s1.result, s2.result);
}

-- Traversals
abstract production all
top::Strategy ::= s::Strategy
{
  top.pp = pp"all(${s.pp})";
  local term::AST = top.term;
  term.givenStrategy = s;
  top.result = term.allResult;
}

abstract production some
top::Strategy ::= s::Strategy
{
  top.pp = pp"some(${s.pp})";
  local term::AST = top.term;
  term.givenStrategy = s;
  top.result = term.someResult;
}

abstract production one
top::Strategy ::= s::Strategy
{
  top.pp = pp"one(${s.pp})";
  local term::AST = top.term;
  term.givenStrategy = s;
  top.result = term.oneResult;
}

abstract production traversal
top::Strategy ::= prodName::String childStrategies::[Strategy] annotationStrategies::[Pair<String Strategy>]
{
  top.pp =
    pp"traverse ${text(prodName)}(${
      ppImplode(pp", ",
        map((.pp), childStrategies) ++
        map(\ a::Pair<String Strategy> -> pp"${text(a.fst)}=${a.snd.pp}", annotationStrategies))})";
  local term::AST = top.term;
  term.productionName = prodName;
  term.childStrategies = childStrategies;
  term.annotationStrategies = annotationStrategies;
  top.result = term.traversalResult;
}

abstract production consListCongruence
top::Strategy ::= h::Strategy t::Strategy
{
  top.pp = pp"traverse (${h.pp} :: ${t.pp})";
  local term::AST = top.term;
  term.headStrategy = h;
  term.tailStrategy = t;
  top.result = term.consListCongruenceResult;
}

abstract production nilListCongruence
top::Strategy ::= 
{
  top.pp = pp"traverse []";
  local term::AST = top.term;
  top.result = term.nilListCongruenceResult;
}

-- Rules
abstract production rewriteRule
top::Strategy ::= pattern::ASTPattern result::ASTExpr
{
  top.pp = pp"rule(${pattern.pp} -> ${result.pp})";
  pattern.matchWith = top.term;
  result.substitutionEnv = pattern.substitution.fromJust;
  top.result = do { pattern.substitution; return result.value; };
}

abstract production require
top::Strategy ::= pattern::ASTPattern cond::ASTExpr
{
  top.pp = pp"require(${pattern.pp} -> when ${cond.pp})";
  pattern.matchWith = top.term;
  cond.substitutionEnv = pattern.substitution.fromJust;
  top.result =
    do {
      pattern.substitution;
      case cond.value of
      | booleanAST(b) -> if b then just(unit()) else nothing()
      | _ -> error("require condition should return a boolean")
      end;
      return top.term;
    };
}

-- Checks the type of the current term.
-- fn evaluates to a function with a parameter of the desired type
abstract production requireType
top::Strategy ::= fn::ASTExpr
{
  top.pp = pp"(:: ${fn.pp})";
  fn.substitutionEnv = [];
  top.result =
    case applyAST(fn.value, [just(top.term)], []) of
    | left(msg) -> nothing()
    | right(_) -> just(top.term)
    end;
}

-- Debug
abstract production printTerm
top::Strategy ::=
{
  top.pp = pp"print()";
  top.result = unsafeTrace(just(top.term), printT(show(80, top.term.pp) ++ "\n\n", unsafeIO()));
}

-- Utilities
abstract production rec
top::Strategy ::= ctr::(Strategy ::= Strategy)
{
  forwards to ctr(top);
}

abstract production try
top::Strategy ::= s::Strategy
{
  forwards to s <+ id();
}

abstract production repeat
top::Strategy ::= s::Strategy
{
  forwards to try(s <* repeat(s));
}

abstract production reduce
top::Strategy ::= s::Strategy
{
  forwards to repeat(rec(\ x::Strategy -> some(x) <+ s));
}

abstract production bottomUp
top::Strategy ::= s::Strategy
{
  forwards to all(bottomUp(s)) <* s;
}

abstract production topDown
top::Strategy ::= s::Strategy
{
  forwards to s <* all(topDown(s));
}

abstract production downUp
top::Strategy ::= s1::Strategy s2::Strategy
{
  forwards to s1 <* all(downUp(s1, s2)) <* s2;
}

abstract production allBottomUp
top::Strategy ::= s::Strategy
{
  forwards to all(allBottomUp(s)) <+ s;
}

abstract production allTopDown
top::Strategy ::= s::Strategy
{
  forwards to s <+ all(allTopDown(s));
}

abstract production allDownUp
top::Strategy ::= s1::Strategy s2::Strategy
{
  forwards to s1 <+ all(allDownUp(s1, s2)) <+ s2;
}

abstract production someBottomUp
top::Strategy ::= s::Strategy
{
  forwards to some(someBottomUp(s)) <+ s;
}

abstract production someTopDown
top::Strategy ::= s::Strategy
{
  forwards to s <+ some(someTopDown(s));
}

abstract production someDownUp
top::Strategy ::= s1::Strategy s2::Strategy
{
  forwards to s1 <+ some(someDownUp(s1, s2)) <+ s2;
}

abstract production onceBottomUp
top::Strategy ::= s::Strategy
{
  forwards to one(onceBottomUp(s)) <+ s;
}

abstract production onceTopDown
top::Strategy ::= s::Strategy
{
  forwards to s <+ one(onceTopDown(s));
}

abstract production onceDownUp
top::Strategy ::= s1::Strategy s2::Strategy
{
  forwards to s1 <+ one(onceDownUp(s1, s2)) <+ s2;
}

abstract production innermost
top::Strategy ::= s::Strategy
{
  forwards to bottomUp(try(s <* innermost(s)));
}

abstract production outermost
top::Strategy ::= s::Strategy
{
  forwards to repeat(onceTopDown(s));
}
