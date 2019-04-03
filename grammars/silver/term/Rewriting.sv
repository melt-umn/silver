grammar silver:term;

imports core hiding all;
import core:monad;

inherited attribute givenTerm::AST;
synthesized attribute rewriteResult::Maybe<AST>;

nonterminal Strategy with givenTerm, rewriteResult;

abstract production fail
top::Strategy ::=
{
  top.rewriteResult = nothing();
}

abstract production id
top::Strategy ::=
{
  top.rewriteResult = just(top.givenTerm);
}

abstract production choice
top::Strategy ::= s1::Strategy s2::Strategy
{
  s1.givenTerm = top.givenTerm;
  s2.givenTerm = top.givenTerm;
  top.rewriteResult = orElse(s1.rewriteResult, s2.rewriteResult);
}

abstract production seq
top::Strategy ::= s1::Strategy s2::Strategy
{
  s1.givenTerm = top.givenTerm;
  s2.givenTerm = s1.rewriteResult.fromJust;
  top.rewriteResult =
    do (bindMaybe, returnMaybe) {
      s1.rewriteResult;
      s2.rewriteResult;
    };
}

abstract production neg
top::Strategy ::= s::Strategy
{
  s.givenTerm = top.givenTerm;
  top.rewriteResult =
    case s.rewriteResult of
    | just(_) -> nothing()
    | nothing() -> just(top.givenTerm)
    end;
}

abstract production one
top::Strategy ::= s::Strategy
{
  
}

abstract production all
top::Strategy ::= s::Strategy
{
  
}

abstract production rec
top::Strategy ::= f::(Strategy ::= Strategy)
{
  production s::Strategy = f(top);
  s.givenTerm = top.givenTerm;
  top.rewriteResult = s.rewriteResult;
}

abstract production rule
top::Strategy ::= a::AST b::AST
{
  top.rewriteResult =
    do (bindMaybe, returnMaybe) {
      subs::[Sub] <- unify(top.givenTerm, a);
      return applySubs(subs, b);
    };
}
