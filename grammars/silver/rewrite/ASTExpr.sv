grammar silver:rewrite;

imports silver:langutil;
imports silver:langutil:pp;

autocopy attribute env::[Pair<String AST>];
synthesized attribute value::AST;

inherited attribute matchWith<a>::a;
synthesized attribute substitution::Maybe<[Pair<String AST>]>;

nonterminal ASTExpr with pp, env, value, matchWith<AST>, substitution;

aspect default production
top::ASTExpr ::=
{
  top.substitution = error("Can't match against expression");
}

-- AST constructors
abstract production prodCallASTExpr
top::ASTExpr ::= prodName::String children::ASTExprs annotations::NamedASTExprs
{
  top.pp = pp"${text(prodName)}(${ppImplode(pp", ", children.pps ++ annotations.pps)})";
  top.value =
    nonterminalAST(
      prodName,
      foldr(consAST, nilAST(), children.values),
      foldr(consNamedAST, nilNamedAST(), annotations.namedValues));
  
  children.matchWith = case top.matchWith of nonterminalAST(_, c, _) -> c end;
  annotations.matchWith = case top.matchWith of nonterminalAST(_, _, a) -> a.bindings end;
  top.substitution =
    do (bindMaybe, returnMaybe) {
      case top.matchWith of
      | nonterminalAST(otherProdName, _, _) when prodName == otherProdName -> just(unit())
      | _ -> nothing()
      end;
      childrenSubstitution::[Pair<String AST>] <- children.substitution;
      annotationsSubstitution::[Pair<String AST>] <- annotations.substitution;
      return childrenSubstitution ++ annotationsSubstitution;
    };
}

abstract production consListASTExpr
top::ASTExpr ::= h::ASTExpr t::ASTExpr
{
  top.pp = pp"(${h.pp} :: ${t.pp})";
  top.value =
    case t.value of
    | listAST(a) -> listAST(consAST(h.value, a))
    | _ -> error("Rewrite type error")
    end;
  
  h.matchWith = case top.matchWith of listAST(consAST(h, _)) -> h end;
  t.matchWith = case top.matchWith of listAST(consAST(_, t)) -> listAST(t) end;
  top.substitution =
    do (bindMaybe, returnMaybe) {
      case top.matchWith of
      | listAST(consAST(_, _)) -> just(unit())
      | _ -> nothing()
      end;
      hSubstitution::[Pair<String AST>] <- h.substitution;
      tSubstitution::[Pair<String AST>] <- t.substitution;
      return hSubstitution ++ tSubstitution;
    };
}

abstract production nilListASTExpr
top::ASTExpr ::=
{
  top.pp = pp"[]";
  top.value = listAST(nilAST());
  top.substitution =
    case top.matchWith of
    | listAST(nilAST()) -> just([])
    | _ -> nothing()
    end;
}

abstract production listASTExpr
top::ASTExpr ::= vals::ASTExprs
{
  top.pp = pp"[${(ppImplode(pp", ", vals.pps))}]";
  forwards to foldr(consListASTExpr, nilListASTExpr(), vals.astExprs);
}

-- Constants
abstract production stringASTExpr
top::ASTExpr ::= s::String
{
  top.pp = pp"${text(escapeString(s))}";
  top.value = stringAST(s);
  top.substitution =
    case top.matchWith of
    | stringAST(s1) when s == s1 -> just([])
    | _ -> nothing()
    end;
}

abstract production integerASTExpr
top::ASTExpr ::= i::Integer
{
  top.pp = pp"${text(toString(i))}";
  top.value = integerAST(i);
  top.substitution =
    case top.matchWith of
    | integerAST(i1) when i == i1 -> just([])
    | _ -> nothing()
    end;
}

abstract production floatASTExpr
top::ASTExpr ::= f::Float
{
  top.pp = pp"${text(toString(f))}";
  top.value = floatAST(f);
  top.substitution =
    case top.matchWith of
    | floatAST(f1) when f == f1 -> just([])
    | _ -> nothing()
    end;
}

abstract production booleanASTExpr
top::ASTExpr ::= b::Boolean
{
  top.pp = pp"${text(toString(b))}";
  top.value = booleanAST(b);
  top.substitution =
    case top.matchWith of
    | booleanAST(b1) when b == b1 -> just([])
    | _ -> nothing()
    end;
}

-- Meta stuff
abstract production varASTExpr
top::ASTExpr ::= n::String
{
  top.pp = text(n);
  top.value =
    fromMaybe(
      error("Unbound variable " ++ n),
      lookupBy(stringEq, n, top.env));
  top.substitution = just([pair(n, top.matchWith)]);
}

abstract production wildASTExpr
top::ASTExpr ::=
{
  top.pp = pp"_";
  top.value = error("Wildcard cannot occur on rule RHS");
  top.substitution = just([]);
}

-- Other constructs
abstract production fnASTExpr
top::ASTExpr ::= fn::(AST ::= [AST])
{
  top.pp = pp"<fn>";
  top.value = anyAST(fn);
}

abstract production applyASTExpr
top::ASTExpr ::= f::ASTExpr args::ASTExprs
{
  top.pp = pp"${f.pp}(${ppImplode(pp", ", args.pps)})";
  local fn::(AST ::= [AST]) = reifyUnchecked(f.value);
  top.value = fn(args.values);
}

abstract production plusASTExpr
top::ASTExpr ::= a::ASTExpr b::ASTExpr
{
  top.pp = pp"(${a.pp} + ${b.pp})";
  top.value =
    case a.value, b.value of
    | integerAST(x), integerAST(y) -> integerAST(x + y)
    | integerAST(x), floatAST(y) -> floatAST(toFloat(x) + y)
    | floatAST(x), integerAST(y) -> floatAST(x + toFloat(y))
    | floatAST(x), floatAST(y) -> floatAST(x + y)
    | _, _ -> error("Invalid values")
    end;
}

synthesized attribute astExprs::[ASTExpr];
synthesized attribute values::[AST];

nonterminal ASTExprs with pps, astExprs, env, values, matchWith<ASTs>, substitution;

abstract production consASTExpr
top::ASTExprs ::= h::ASTExpr t::ASTExprs
{
  top.pps = h.pp :: t.pps;
  top.astExprs = h :: t.astExprs;
  top.values = h.value :: t.values;
  
  h.matchWith = case top.matchWith of consAST(h, _) -> h end;
  t.matchWith = case top.matchWith of consAST(_, t) -> t end;
  top.substitution =
    do (bindMaybe, returnMaybe) {
      case top.matchWith of
      | consAST(_, _) -> just(unit())
      | _ -> nothing()
      end;
      hSubstitution::[Pair<String AST>] <- h.substitution;
      tSubstitution::[Pair<String AST>] <- t.substitution;
      return hSubstitution ++ tSubstitution;
    };
}

abstract production nilASTExpr
top::ASTExprs ::=
{
  top.pps = [];
  top.astExprs = [];
  top.values = [];
  top.substitution =
    case top.matchWith of
    | nilAST() -> just([])
    | _ -> nothing()
    end;
}

synthesized attribute namedValues::[NamedAST];

nonterminal NamedASTExprs with pps, env, namedValues, matchWith<[Pair<String AST>]>, substitution;

abstract production consNamedASTExpr
top::NamedASTExprs ::= h::NamedASTExpr t::NamedASTExprs
{
  top.pps = h.pp :: t.pps;
  top.namedValues = h.namedValue :: t.namedValues;
  top.substitution =
    do (bindMaybe, returnMaybe) {
      hSubstitution::[Pair<String AST>] <- h.substitution;
      tSubstitution::[Pair<String AST>] <- t.substitution;
      return hSubstitution ++ tSubstitution;
    };
  
  h.matchWith = top.matchWith;
  t.matchWith = top.matchWith;
}

abstract production nilNamedASTExpr
top::NamedASTExprs ::=
{
  top.pps = [];
  top.namedValues = [];
  top.substitution = just([]);
}

synthesized attribute namedValue::NamedAST;

nonterminal NamedASTExpr with pp, env, namedValue, matchWith<[Pair<String AST>]>, substitution;

abstract production namedASTExpr
top::NamedASTExpr ::= n::String v::ASTExpr
{
  top.pp = pp"${text(n)}=${v.pp}";
  top.namedValue = namedAST(n, v.value);
  
  v.matchWith =
    fromMaybe(
      error("Unexpected annotation " ++ n),
      lookupBy(stringEq, n, top.matchWith));
  top.substitution = v.substitution;
}
