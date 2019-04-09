grammar silver:term;

imports core hiding all;
import core:monad;

function rewrite
Maybe<AST> ::= s::Strategy term::AST
{
  s.givenTerm = term;
  return s.rewriteResult;
}

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
  local term::AST = top.givenTerm;
  term.givenStrategy = s;
  top.rewriteResult = term.rewriteOne;
}

abstract production all
top::Strategy ::= s::Strategy
{
  local term::AST = top.givenTerm;
  term.givenStrategy = s;
  top.rewriteResult = term.rewriteAll;
}

abstract production rec
top::Strategy ::= f::(Strategy ::= Strategy)
{
  production s::Strategy = f(top);
  s.givenTerm = top.givenTerm;
  top.rewriteResult = s.rewriteResult;
}

abstract production rule
top::Strategy ::= a::AST cond::(Boolean ::= [Sub]) fresh::[String] b::AST
{
  top.rewriteResult =
    do (bindMaybe, returnMaybe) {
      subs::[Sub] <- unify(top.givenTerm, a);
      if cond(subs) then
        return unit();
      else
        nothing();
      freshSubs::[Sub] =
        map(\ n::String -> pair(n, stringAST("_" ++ toString(genInt()))), fresh);
      return applySubs(subs ++ freshSubs, b);
    };
}

autocopy attribute givenStrategy::Strategy occurs on AST, ASTs, NamedASTs, NamedAST;
synthesized attribute rewriteOne<a>::Maybe<a>;
synthesized attribute rewriteAll<a>::Maybe<a>;

attribute rewriteOne<AST> occurs on AST;
attribute rewriteAll<AST> occurs on AST;

aspect default production
top::AST ::=
{
  top.rewriteOne = nothing();
  top.rewriteAll = just(top);
}

aspect production nonterminalAST
top::AST ::= prodName::String children::ASTs annotations::NamedASTs
{
  top.rewriteOne =
    case children.rewriteAll, annotations.rewriteAll of
    | just(childRes), _ -> just(nonterminalAST(prodName, childRes, annotations))
    | _, just(annotationRes) -> just(nonterminalAST(prodName, children, annotationRes))
    | _, _ -> nothing()
    end;
  top.rewriteAll =
    do (bindMaybe, returnMaybe) {
      childRes::ASTs <- children.rewriteAll;
      annotationRes::NamedASTs <- annotations.rewriteAll;
      return nonterminalAST(prodName, childRes, annotationRes);
    };
}

aspect production listAST
top::AST ::= vals::ASTs
{
  top.rewriteOne =
    do (bindMaybe, returnMaybe) {
      valRes::ASTs <- vals.rewriteOne;
      return listAST(valRes);
    };
  top.rewriteAll =
    do (bindMaybe, returnMaybe) {
      valRes::ASTs <- vals.rewriteAll;
      return listAST(valRes);
    };
}

attribute rewriteOne<ASTs> occurs on ASTs;
attribute rewriteAll<ASTs> occurs on ASTs;

aspect production consAST
top::ASTs ::= h::AST t::ASTs
{
  top.rewriteOne =
    case rewrite(top.givenStrategy, h), t.rewriteAll of
    | just(hRes), _ -> just(consAST(hRes, t))
    | _, just(tRes) -> just(consAST(h, tRes))
    | _, _ -> nothing()
    end;
  top.rewriteAll =
    do (bindMaybe, returnMaybe) {
      hRes::AST <- rewrite(top.givenStrategy, h);
      tRes::ASTs <- t.rewriteAll;
      return consAST(hRes, tRes);
    };
}

aspect production nilAST
top::ASTs ::=
{
  top.rewriteOne = nothing();
  top.rewriteAll = just(top);
}

attribute rewriteOne<NamedASTs> occurs on NamedASTs;
attribute rewriteAll<NamedASTs> occurs on NamedASTs;

aspect production consNamedAST
top::NamedASTs ::= h::NamedAST t::NamedASTs
{
  top.rewriteOne =
    case h.rewriteAll, t.rewriteAll of
    | just(hRes), _ -> just(consNamedAST(hRes, t))
    | _, just(tRes) -> just(consNamedAST(h, tRes))
    | _, _ -> nothing()
    end;
  top.rewriteAll =
    do (bindMaybe, returnMaybe) {
      hRes::NamedAST <- h.rewriteAll;
      tRes::NamedASTs <- t.rewriteAll;
      return consNamedAST(hRes, tRes);
    };
}

aspect production nilNamedAST
top::NamedASTs ::=
{
  top.rewriteOne = nothing();
  top.rewriteAll = just(top);
}

attribute rewriteOne<NamedAST> occurs on NamedAST;
attribute rewriteAll<NamedAST> occurs on NamedAST;

aspect production namedAST
top::NamedAST ::= n::String v::AST
{
  top.rewriteOne =
    do (bindMaybe, returnMaybe) {
      res::AST <- rewrite(top.givenStrategy, v);
      return namedAST(n, res);
    };
  top.rewriteAll =
    do (bindMaybe, returnMaybe) {
      res::AST <- rewrite(top.givenStrategy, v);
      return namedAST(n, res);
    };
}

-- Strategy construction helpers/combinators
global directFreshRule::(Strategy ::= AST [String] AST) = rule(_, \ [Sub] -> true, _, _);
global directRule::(Strategy ::= AST AST) = directFreshRule(_, [], _);
global foldSeq::(Strategy ::= [Strategy]) = foldr(seq, id(), _);
global foldChoice::(Strategy ::= [Strategy]) = foldr(choice, fail(), _);

global tryS::(Strategy ::= Strategy) = choice(_, id()); -- TODO: Silver doesn't like globals named 'try'

function repeat
Strategy ::= s::Strategy
{
  return rec(\ self::Strategy -> tryS(seq(s, self)));
}

function bottomUp
Strategy ::= s::Strategy
{
  return rec(\ self::Strategy -> seq(all(self), s));
}

function topDown
Strategy ::= s::Strategy
{
  return rec(\ self::Strategy -> seq(s, all(self)));
}

function onceBottomUp
Strategy ::= s::Strategy
{
  return rec(\ self::Strategy -> choice(one(self), s));
}

function onceTopDown
Strategy ::= s::Strategy
{
  return rec(\ self::Strategy -> choice(s, one(self)));
}

function innermost
Strategy ::= s::Strategy
{
  return repeat(onceBottomUp(s));
}

function outermost
Strategy ::= s::Strategy
{
  return repeat(onceTopDown(s));
}
