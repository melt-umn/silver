grammar silver:rewrite;

exports silver:reflect; -- Needed by the extension, so just export it here.

autocopy attribute givenStrategy::Strategy occurs on AST, ASTs, NamedASTs, NamedAST;
synthesized attribute allResult<a>::Maybe<a>;
synthesized attribute oneResult<a>::Maybe<a>;

inherited attribute productionName::String occurs on AST;
autocopy attribute childStrategies::[Strategy] occurs on AST, ASTs;
autocopy attribute annotationStrategies::[Pair<String Strategy>] occurs on AST, NamedASTs, NamedAST;
synthesized attribute congruenceResult<a>::Maybe<a>;

attribute allResult<AST> occurs on AST;
attribute oneResult<AST> occurs on AST;
attribute congruenceResult<AST> occurs on AST;

aspect default production
top::AST ::=
{
  top.allResult = just(top);
  top.oneResult = nothing();
  top.congruenceResult = nothing();
}

aspect production nonterminalAST
top::AST ::= prodName::String children::ASTs annotations::NamedASTs
{
  top.allResult =
    do (bindMaybe, returnMaybe) {
      childrenResult::ASTs <- children.allResult;
      annotationsResult::NamedASTs <- annotations.allResult;
      return nonterminalAST(prodName, childrenResult, annotationsResult);
    };
  top.oneResult =
    case children.oneResult, annotations.oneResult of
    | just(childrenResult), _ -> just(nonterminalAST(prodName, childrenResult, annotations))
    | nothing(), just(annotationsResult) -> just(nonterminalAST(prodName, children, annotationsResult))
    | nothing(), nothing() -> nothing()
    end;
  top.congruenceResult =
    do (bindMaybe, returnMaybe) {
      if prodName != top.productionName then nothing();
      childrenResult::ASTs <- children.congruenceResult;
      annotationsResult::NamedASTs <- annotations.congruenceResult;
      return nonterminalAST(prodName, childrenResult, annotationsResult);
    };
}

aspect production terminalAST
top::AST ::= terminalName::String lexeme::String location::Location
{
  local locAST::AST = reflect(location);
  top.allResult =
    do (bindMaybe, returnMaybe) {
      locASTResult::AST <- decorate top.givenStrategy with { term = locAST; }.result;
      locationResult::Location = reifyUnchecked(locASTResult);
      return terminalAST(terminalName, lexeme, locationResult);
    };
  top.oneResult = top.allResult; -- Exactly one rewritable child
}

aspect production listAST
top::AST ::= vals::ASTs
{
  local h::AST = case vals of consAST(h, _) -> h end;
  local t::AST = case vals of consAST(_, t) -> listAST(t) end;
  top.allResult =
    case vals of
    | consAST(_, _) ->
      do (bindMaybe, returnMaybe) {
        hResult::AST <- decorate top.givenStrategy with { term = h; }.result;
        tResult::AST <- decorate top.givenStrategy with { term = t; }.result;
        return
          case tResult of
          | listAST(a) -> listAST(consAST(hResult, a))
          | _ -> error("Rewrite type error")
          end;
      }
    | nilAST() -> just(top)
    end;
  top.oneResult =
    case vals of
    | consAST(_, _) ->
      case decorate top.givenStrategy with { term = h; }.result,
           decorate top.givenStrategy with { term = t; }.result of
      | just(hResult), _ -> just(listAST(consAST(hResult, case vals of consAST(_, t) -> t end)))
      | nothing(), just(listAST(tResult)) -> just(listAST(consAST(h, tResult)))
      | nothing(), _ -> nothing()
      end
    | nilAST() -> nothing()
    end;
}

attribute allResult<ASTs> occurs on ASTs;
attribute oneResult<ASTs> occurs on ASTs;
attribute congruenceResult<ASTs> occurs on ASTs;

aspect production consAST
top::ASTs ::= h::AST t::ASTs
{
  top.allResult =
    do (bindMaybe, returnMaybe) {
      hResult::AST <- decorate top.givenStrategy with { term = h; }.result;
      tResult::ASTs <- t.allResult;
      return consAST(hResult, tResult);
    };
  top.oneResult =
    case decorate top.givenStrategy with { term = h; }.result, t.oneResult of
    | just(hResult), _ -> just(consAST(hResult, t))
    | nothing(), just(tResult) -> just(consAST(h, tResult))
    | nothing(), nothing() -> nothing()
    end;
  top.congruenceResult =
    do (bindMaybe, returnMaybe) {
      hResult::AST <- decorate head(top.childStrategies) with { term = h; }.result;
      tResult::ASTs <- t.congruenceResult;
      return consAST(hResult, tResult);
    };
  t.childStrategies = tail(top.childStrategies);
}

aspect production nilAST
top::ASTs ::=
{
  top.allResult = just(top);
  top.oneResult = nothing();
  top.congruenceResult = just(top);
}

synthesized attribute bindings::[Pair<String AST>] occurs on NamedASTs;

attribute allResult<NamedASTs> occurs on NamedASTs;
attribute oneResult<NamedASTs> occurs on NamedASTs;
attribute congruenceResult<NamedASTs> occurs on NamedASTs;

aspect production consNamedAST
top::NamedASTs ::= h::NamedAST t::NamedASTs
{
  top.bindings = h.binding :: t.bindings;
  top.allResult =
    do (bindMaybe, returnMaybe) {
      hResult::NamedAST <- h.allResult;
      tResult::NamedASTs <- t.allResult;
      return consNamedAST(hResult, tResult);
    };
  top.oneResult =
    case h.oneResult, t.oneResult of
    | just(hResult), _ -> just(consNamedAST(hResult, t))
    | nothing(), just(tResult) -> just(consNamedAST(h, tResult))
    | nothing(), nothing() -> nothing()
    end;
  top.congruenceResult =
    do (bindMaybe, returnMaybe) {
      hResult::NamedAST <- h.congruenceResult;
      tResult::NamedASTs <- t.congruenceResult;
      return consNamedAST(hResult, tResult);
    };
}

aspect production nilNamedAST
top::NamedASTs ::=
{
  top.bindings = [];
  top.allResult = just(top);
  top.oneResult = nothing();
  top.congruenceResult = just(top);
}

synthesized attribute binding::Pair<String AST> occurs on NamedAST;

attribute allResult<NamedAST> occurs on NamedAST;
attribute oneResult<NamedAST> occurs on NamedAST;
attribute congruenceResult<NamedAST> occurs on NamedAST;

aspect production namedAST
top::NamedAST ::= n::String v::AST
{
  top.binding = pair(n, v);
  top.allResult =
    do (bindMaybe, returnMaybe) {
      vResult::AST <- decorate top.givenStrategy with { term = v; }.result;
      return namedAST(n, vResult);
    };
  top.oneResult = top.allResult; -- Exactly one rewritable child
  top.congruenceResult =
    -- Look up and apply all strategies for the annotation
    -- (it's easier to just handle duplicates than to disallow them.)
    mapMaybe(
      namedAST(n, _),
      foldl(
        \ ma::Maybe<AST> s::Strategy ->
          bindMaybe(ma, \ a::AST -> decorate s with { term = a; }.result),
        just(v),
        lookupAllBy(stringEq, n, top.annotationStrategies)));
}
