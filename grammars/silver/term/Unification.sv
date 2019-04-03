grammar silver:term;

imports silver:reflect;

import core:monad;

type Sub = Pair<String AST>;

function unify
Maybe<[Sub]> ::= a::AST b::AST
{
  a.otherTerm = b;
  return a.subs;
}

global filterDirectSubs::([Sub] ::= [Sub]) =
  filter(\ s::Sub -> case s of pair(n1, varAST(n2)) -> n1 != n2 | _ -> true end, _);

function unifySubs
Maybe<[Sub]> ::= s1::[Sub] s2::[Sub]
{
  return
    case s1 of
    | pair(n, a) :: t ->
      case lookupBy(stringEq, n, s2) of
      | just(b) ->
        do (bindMaybe, returnMaybe) {
          s::[Sub] <- unify(a, b);
          s2Items::Pair<[String] [AST]> = unzipPairs(s2);
          newS2::[Sub] =
            filterDirectSubs(zipWith(pair, s2Items.fst, map(applySubs(s, _), s2Items.snd)));
          unifySubs(t, newS2);
        }
      | nothing() ->
        do (bindMaybe, returnMaybe) {
          rest::[Sub] <- unifySubs(t, s2);
          return filterDirectSubs([pair(n, applySubs(s2, a))]) ++ rest;
        }
      end
    | [] -> just(s2)
    end;
}

function applySubs
AST ::= s::[Sub] a::AST
{
  a.appliedSubs = s;
  return a.substituted;
}

inherited attribute otherTerm<a>::a;
attribute otherTerm<AST> occurs on AST;
synthesized attribute subs::Maybe<[Sub]> occurs on AST, ASTs, NamedASTs, NamedAST;

autocopy attribute appliedSubs::[Sub] occurs on AST, ASTs, NamedASTs, NamedAST;
synthesized attribute substituted<a>::a;
attribute substituted<AST> occurs on AST;

aspect production nonterminalAST
top::AST ::= prodName::String children::ASTs annotations::NamedASTs
{
  propagate substituted;
  children.otherTerm = case top.otherTerm of nonterminalAST(_, a, _) -> a end;
  annotations.otherTerm = case top.otherTerm of nonterminalAST(_, _, a) -> a end;
  top.subs =
    case top.otherTerm of
    | nonterminalAST(otherProdName, _, _) ->
      do (bindMaybe, returnMaybe) {
        if prodName != otherProdName then
          nothing();
        else
          return unit();
        childrenSubs::[Pair<String AST>] <- children.subs;
        annotationSubs::[Pair<String AST>] <- annotations.subs;
        unifySubs(childrenSubs, annotationSubs);
      }
    | varAST(n) -> just([pair(n, top)])
    | wildAST() -> just([])
    | _ -> nothing()
    end;
}

aspect production terminalAST
top::AST ::= terminalName::String lexeme::String location::Location
{
  propagate substituted;
  top.subs =
    case top.otherTerm of
    | terminalAST(otherTerminalName, otherLexeme, otherLocation) ->
      if
        terminalName == otherTerminalName &&
        lexeme == otherLexeme &&
        -- Ugh...
        locationLte(location, otherLocation) && locationLte(otherLocation, location)
      then just([])
      else nothing()
    | varAST(n) -> just([pair(n, top)])
    | wildAST() -> just([])
    | _ -> nothing()
    end;
}

aspect production listAST
top::AST ::= vals::ASTs
{
  propagate substituted;
  vals.otherTerm = case top.otherTerm of listAST(a) -> a end;
  top.subs =
    case top.otherTerm of
    | listAST(_) -> vals.subs
    | varAST(n) -> just([pair(n, top)])
    | wildAST() -> just([])
    | _ -> nothing()
    end;
}

aspect production stringAST
top::AST ::= s::String
{
  propagate substituted;
  top.subs =
    case top.otherTerm of
    | stringAST(otherS) -> if s == otherS then just([]) else nothing()
    | varAST(n) -> just([pair(n, top)])
    | wildAST() -> just([])
    | _ -> nothing()
    end;
}

aspect production integerAST
top::AST ::= i::Integer
{
  propagate substituted;
  top.subs =
    case top.otherTerm of
    | integerAST(otherI) -> if i == otherI then just([]) else nothing()
    | varAST(n) -> just([pair(n, top)])
    | wildAST() -> just([])
    | _ -> nothing()
    end;
}

aspect production floatAST
top::AST ::= f::Float
{
  propagate substituted;
  top.subs =
    case top.otherTerm of
    | floatAST(otherF) -> if f == otherF then just([]) else nothing()
    | varAST(n) -> just([pair(n, top)])
    | wildAST() -> just([])
    | _ -> nothing()
    end;
}

aspect production booleanAST
top::AST ::= b::Boolean
{
  propagate substituted;
  top.subs =
    case top.otherTerm of
    | booleanAST(otherB) -> if b == otherB then just([]) else nothing()
    | varAST(n) -> just([pair(n, top)])
    | wildAST() -> just([])
    | _ -> nothing()
    end;
}

aspect production anyAST
top::AST ::= x::a
{
  propagate substituted;
  top.subs =
    case top.otherTerm of
    | varAST(n) -> just([pair(n, top)])
    | wildAST() -> just([])
    | _ -> error("Can't unify anyAST")
    end;
}

aspect production varAST
top::AST ::= n::String
{
  top.subs =
    case top.otherTerm of
    | wildAST() -> nothing()
    | t -> just([pair(n, t)])
    end;
  top.substituted = fromMaybe(top, lookupBy(stringEq, n, top.appliedSubs));
}

aspect production wildAST
top::AST ::=
{
  propagate substituted;
  top.subs = just([]);
}

attribute otherTerm<ASTs> occurs on ASTs;
attribute substituted<ASTs> occurs on ASTs;

aspect production consAST
top::ASTs ::= h::AST t::ASTs
{
  propagate substituted;
  h.otherTerm = case top.otherTerm of consAST(a, _) -> a end;
  t.otherTerm = case top.otherTerm of consAST(_, a) -> a end;
  top.subs =
    case top.otherTerm of
    | consAST(_, _) ->
      do (bindMaybe, returnMaybe) {
        hSubs::[Sub] <- h.subs;
        tSubs::[Sub] <- t.subs;
        unifySubs(hSubs, tSubs);
      }
    | nilAST() -> nothing()
    end;
}

aspect production nilAST
top::ASTs ::=
{
  propagate substituted;
  top.subs =
    case top.otherTerm of
    | consAST(_, _) -> nothing()
    | nilAST() -> just([])
    end;
}

-- TODO: Ordering?
attribute otherTerm<NamedASTs> occurs on NamedASTs;
attribute substituted<NamedASTs> occurs on NamedASTs;

aspect production consNamedAST
top::NamedASTs ::= h::NamedAST t::NamedASTs
{
  propagate substituted;
  h.otherTerm = case top.otherTerm of consNamedAST(a, _) -> a end;
  t.otherTerm = case top.otherTerm of consNamedAST(_, a) -> a end;
  top.subs =
    case top.otherTerm of
    | consNamedAST(_, _) ->
      do (bindMaybe, returnMaybe) {
        hSubs::[Sub] <- h.subs;
        tSubs::[Sub] <- t.subs;
        unifySubs(hSubs, tSubs);
      }
    | nilNamedAST() -> nothing()
    end;
}

aspect production nilNamedAST
top::NamedASTs ::=
{
  propagate substituted;
  top.subs =
    case top.otherTerm of
    | consNamedAST(_, _) -> nothing()
    | nilNamedAST() -> just([])
    end;
}

attribute otherTerm<NamedAST> occurs on NamedAST;
attribute substituted<NamedAST> occurs on NamedAST;

aspect production namedAST
top::NamedAST ::= n::String v::AST
{
  propagate substituted;
  v.otherTerm = case top.otherTerm of namedAST(_, a) -> a end;
  top.subs =
    case top.otherTerm of
    | namedAST(otherN, _) -> if n == otherN then v.subs else nothing()
    end;
}
