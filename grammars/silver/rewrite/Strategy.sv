grammar silver:rewrite;

imports core hiding all;
imports core:monad;

inherited attribute term::AST;
synthesized attribute result::Maybe<AST>;

nonterminal Strategy with term, result;

abstract production id
top::Strategy ::=
{
  top.result = just(top.term);
}

abstract production fail
top::Strategy ::=
{
  top.result = nothing();
}

abstract production sequence
top::Strategy ::= s1::Strategy s2::Strategy
{
  s1.term = top.term;
  s2.term = s1.result.fromJust;
  top.result = bindMaybe(s1.result, \ AST -> s2.result);
}

abstract production choice
top::Strategy ::= s1::Strategy s2::Strategy
{
  s1.term = top.term;
  s2.term = top.term;
  top.result = orElse(s1.result, s2.result);
}

abstract production all
top::Strategy ::= s::Strategy
{
  local term::AST = top.term;
  term.givenStrategy = s;
  top.result = term.allResult;
}

abstract production one
top::Strategy ::= s::Strategy
{
  local term::AST = top.term;
  term.givenStrategy = s;
  top.result = term.oneResult;
}

abstract production rec
top::Strategy ::= ctr::(Strategy ::= Strategy)
{
  forwards to ctr(top);
}

abstract production rule
top::Strategy ::= pattern::ASTExpr result::ASTExpr
{
  pattern.matchWith = top.term;
  result.env = pattern.substitution.fromJust;
  top.result =
    do (bindMaybe, returnMaybe) {
      pattern.substitution;
      return result.value;
    };
}

abstract production require
top::Strategy ::= pattern::ASTExpr cond::ASTExpr
{
  pattern.matchWith = top.term;
  cond.env = pattern.substitution.fromJust;
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

function try
Strategy ::= s::Strategy
{
  return choice(s, id());
}

function repeat
Strategy ::= s::Strategy
{
  return choice(try(s), repeat(s));
}
