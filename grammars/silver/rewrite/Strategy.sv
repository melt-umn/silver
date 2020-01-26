grammar silver:rewrite;

-- Some of these Strategy productions have very generic names that conflict with core.
-- Users must explicitly import core hiding these names, or perform a qualified import,
-- e.g. import silver:rewrite as s;

imports core hiding all, repeat;
imports core:monad;

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
  top.result = bindMaybe(s1.result, \ AST -> s2.result);
}

abstract production choice
top::Strategy ::= s1::Strategy s2::Strategy
{
  top.pp = pp"(${s1.pp} <+ ${s2.pp})";
  s1.term = top.term;
  s2.term = top.term;
  top.result = orElse(s1.result, s2.result);
}

abstract production all
top::Strategy ::= s::Strategy
{
  top.pp = pp"all(${s.pp})";
  local term::AST = top.term;
  term.givenStrategy = s;
  top.result = term.allResult;
}

abstract production one
top::Strategy ::= s::Strategy
{
  top.pp = pp"one(${s.pp})";
  local term::AST = top.term;
  term.givenStrategy = s;
  top.result = term.oneResult;
}

-- Rules
abstract production rewriteRule
top::Strategy ::= pattern::ASTExpr result::ASTExpr
{
  top.pp = pp"rule(${pattern.pp} -> ${result.pp})";
  pattern.matchWith = top.term;
  result.substitutionEnv = pattern.substitution.fromJust;
  top.result =
    do (bindMaybe, returnMaybe) {
      pattern.substitution;
      return result.value;
    };
}

abstract production require
top::Strategy ::= pattern::ASTExpr cond::ASTExpr
{
  top.pp = pp"require(${pattern.pp} -> when ${cond.pp})";
  pattern.matchWith = top.term;
  cond.substitutionEnv = pattern.substitution.fromJust;
  top.result =
    do (bindMaybe, returnMaybe) {
      pattern.substitution;
      case cond.value of
      | booleanAST(b) -> if b then just(unit()) else nothing()
      | _ -> error("require condition should return a boolean")
      end;
      return top.term;
    };
}

-- Debug
abstract production printTerm
top::Strategy ::=
{
  top.pp = pp"print()";
  top.result = unsafeTrace(just(top.term), print(show(80, top.term.pp) ++ "\n\n", unsafeIO()));
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

abstract production innermost
top::Strategy ::= s::Strategy
{
  forwards to repeat(onceBottomUp(s));
}

abstract production outermost
top::Strategy ::= s::Strategy
{
  forwards to repeat(onceTopDown(s));
}