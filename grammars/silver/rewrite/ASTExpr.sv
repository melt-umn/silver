grammar silver:rewrite;

imports silver:langutil;
imports silver:langutil:pp;

autocopy attribute substitutionEnv::[Pair<String AST>];
synthesized attribute value::AST;

inherited attribute matchWith<a>::a;
synthesized attribute substitution::Maybe<[Pair<String AST>]>;

nonterminal ASTExpr with pp, substitutionEnv, value, matchWith<AST>, substitution;

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

abstract production terminalASTExpr
top::ASTExpr ::= terminalName::String lexeme::ASTExpr location::ASTExpr
{
  top.pp = pp"terminal(${text(terminalName)}, ${lexeme.pp}, ${location.pp})";
  top.value =
    case reify(lexeme.value), reify(location.value) of
    | right(l), right(l1) -> terminalAST(terminalName, l, l1)
    | _, _ -> error("Invalid values to terminal constructor")
    end;
}

abstract production anyASTExpr
top::ASTExpr ::= x::a
{
  top.pp = reflect(x).pp;
  top.value = reflect(x);
}

-- Meta stuff
abstract production varASTExpr
top::ASTExpr ::= n::String
{
  top.pp = text(n);
  top.value =
    fromMaybe(
      error("Unbound variable " ++ n),
      lookupBy(stringEq, n, top.substitutionEnv));
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
abstract production applyASTExpr
top::ASTExpr ::= f::ASTExpr args::ASTExprs namedArgs::NamedASTExprs
{
  top.pp = pp"${f.pp}(${ppImplode(pp", ", args.pps ++ namedArgs.pps)})";
  top.value =
    case applyAST(f.value, args.appValues, namedArgs.namedAppValues) of
    | left(msg) -> error(s"Error applying ${show(80, f.pp)}(${show(80, ppImplode(pp", ", args.pps ++ namedArgs.pps))}): ${msg}")
    | right(a) -> a
    end;
}

abstract production andASTExpr
top::ASTExpr ::= a::ASTExpr b::ASTExpr
{
  top.pp = pp"(${a.pp} && ${b.pp})";
  top.value =
    case a.value, b.value of
    | booleanAST(x), booleanAST(y) -> booleanAST(x && y)
    | _, _ -> error("Invalid values")
    end;
}

abstract production orASTExpr
top::ASTExpr ::= a::ASTExpr b::ASTExpr
{
  top.pp = pp"(${a.pp} || ${b.pp})";
  top.value =
    case a.value, b.value of
    | booleanAST(x), booleanAST(y) -> booleanAST(x || y)
    | _, _ -> error("Invalid values")
    end;
}

abstract production notASTExpr
top::ASTExpr ::= a::ASTExpr
{
  top.pp = pp"!${a.pp}";
  top.value =
    case a.value of
    | booleanAST(x) -> booleanAST(!x)
    | _ -> error("Invalid values")
    end;
}

abstract production gtASTExpr
top::ASTExpr ::= a::ASTExpr b::ASTExpr
{
  top.pp = pp"(${a.pp} > ${b.pp})";
  top.value =
    case a.value, b.value of
    | integerAST(x), integerAST(y) -> booleanAST(x > y)
    | floatAST(x), floatAST(y) -> booleanAST(x > y)
    | stringAST(x), stringAST(y) -> booleanAST(x > y)
    | _, _ -> error("Invalid values")
    end;
}

abstract production ltASTExpr
top::ASTExpr ::= a::ASTExpr b::ASTExpr
{
  top.pp = pp"(${a.pp} < ${b.pp})";
  top.value =
    case a.value, b.value of
    | integerAST(x), integerAST(y) -> booleanAST(x < y)
    | floatAST(x), floatAST(y) -> booleanAST(x < y)
    | stringAST(x), stringAST(y) -> booleanAST(x < y)
    | _, _ -> error("Invalid values")
    end;
}

abstract production gteqASTExpr
top::ASTExpr ::= a::ASTExpr b::ASTExpr
{
  top.pp = pp"(${a.pp} >= ${b.pp})";
  top.value =
    case a.value, b.value of
    | integerAST(x), integerAST(y) -> booleanAST(x >= y)
    | floatAST(x), floatAST(y) -> booleanAST(x >= y)
    | stringAST(x), stringAST(y) -> booleanAST(x >= y)
    | _, _ -> error("Invalid values")
    end;
}

abstract production lteqASTExpr
top::ASTExpr ::= a::ASTExpr b::ASTExpr
{
  top.pp = pp"(${a.pp} <= ${b.pp})";
  top.value =
    case a.value, b.value of
    | integerAST(x), integerAST(y) -> booleanAST(x <= y)
    | floatAST(x), floatAST(y) -> booleanAST(x <= y)
    | stringAST(x), stringAST(y) -> booleanAST(x <= y)
    | _, _ -> error("Invalid values")
    end;
}

abstract production eqeqASTExpr
top::ASTExpr ::= a::ASTExpr b::ASTExpr
{
  top.pp = pp"(${a.pp} == ${b.pp})";
  top.value =
    case a.value, b.value of
    | integerAST(x), integerAST(y) -> booleanAST(x == y)
    | floatAST(x), floatAST(y) -> booleanAST(x == y)
    | stringAST(x), stringAST(y) -> booleanAST(x == y)
    | booleanAST(x), booleanAST(y) -> booleanAST(x == y)
    | _, _ -> error("Invalid values")
    end;
}

abstract production neqASTExpr
top::ASTExpr ::= a::ASTExpr b::ASTExpr
{
  top.pp = pp"(${a.pp} != ${b.pp})";
  top.value =
    case a.value, b.value of
    | integerAST(x), integerAST(y) -> booleanAST(x != y)
    | floatAST(x), floatAST(y) -> booleanAST(x != y)
    | stringAST(x), stringAST(y) -> booleanAST(x != y)
    | booleanAST(x), booleanAST(y) -> booleanAST(x != y)
    | _, _ -> error("Invalid values")
    end;
}

abstract production ifThenElseASTExpr
top::ASTExpr ::= c::ASTExpr t::ASTExpr e::ASTExpr
{
  top.pp = pp"(if ${c.pp} then ${t.pp} else ${e.pp})";
  top.value =
    case c.value of
    | booleanAST(true) -> c.value
    | booleanAST(false) -> e.value
    | _ -> error("Invalid values")
    end;
}

abstract production plusASTExpr
top::ASTExpr ::= a::ASTExpr b::ASTExpr
{
  top.pp = pp"(${a.pp} + ${b.pp})";
  top.value =
    case a.value, b.value of
    | integerAST(x), integerAST(y) -> integerAST(x + y)
    | floatAST(x), floatAST(y) -> floatAST(x + y)
    | _, _ -> error("Invalid values")
    end;
}

abstract production multiplyASTExpr
top::ASTExpr ::= a::ASTExpr b::ASTExpr
{
  top.pp = pp"(${a.pp} * ${b.pp})";
  top.value =
    case a.value, b.value of
    | integerAST(x), integerAST(y) -> integerAST(x * y)
    | floatAST(x), floatAST(y) -> floatAST(x * y)
    | _, _ -> error("Invalid values")
    end;
}

abstract production minusASTExpr
top::ASTExpr ::= a::ASTExpr b::ASTExpr
{
  top.pp = pp"(${a.pp} - ${b.pp})";
  top.value =
    case a.value, b.value of
    | integerAST(x), integerAST(y) -> integerAST(x - y)
    | floatAST(x), floatAST(y) -> floatAST(x - y)
    | _, _ -> error("Invalid values")
    end;
}

abstract production divideASTExpr
top::ASTExpr ::= a::ASTExpr b::ASTExpr
{
  top.pp = pp"(${a.pp} / ${b.pp})";
  top.value =
    case a.value, b.value of
    | integerAST(x), integerAST(y) -> integerAST(x / y)
    | floatAST(x), floatAST(y) -> floatAST(x / y)
    | _, _ -> error("Invalid values")
    end;
}

abstract production modulusASTExpr
top::ASTExpr ::= a::ASTExpr b::ASTExpr
{
  top.pp = pp"(${a.pp} % ${b.pp})";
  top.value =
    case a.value, b.value of
    | integerAST(x), integerAST(y) -> integerAST(x % y)
    | floatAST(x), floatAST(y) -> floatAST(x % y)
    | _, _ -> error("Invalid values")
    end;
}

abstract production negASTExpr
top::ASTExpr ::= a::ASTExpr
{
  top.pp = pp"-${a.pp}";
  top.value =
    case a.value of
    | integerAST(x) -> integerAST(-x)
    | floatAST(x) -> floatAST(-x)
    | _ -> error("Invalid values")
    end;
}

abstract production appendASTExpr
top::ASTExpr ::= a::ASTExpr b::ASTExpr
{
  top.pp = pp"(${a.pp} ++ ${b.pp})";
  top.value =
    case a.value, b.value of
    | stringAST(x), stringAST(y) -> stringAST(x ++ y)
    | listAST(x), listAST(y) -> listAST(appendASTs(x, y))
    | _, _ -> error("Invalid values")
    end;
}

abstract production letASTExpr
top::ASTExpr ::= a::NamedASTExprs body::ASTExpr
{
  top.pp = pp"let ${ppImplode(pp", ", a.pps)} in ${body.pp} end";
  top.value = body.value;
  body.substitutionEnv =
    map(\ n::NamedAST -> case n of namedAST(n, a) -> pair(n, a) end, a.namedValues) ++ top.substitutionEnv;
}

abstract production lengthASTExpr
top::ASTExpr ::= a::ASTExpr
{
  top.pp = pp"length(${a.pp})";
  top.value =
    case a.value of
    | stringAST(s) -> integerAST(length(s))
    | listAST(a) -> integerAST(a.astsLength)
    | _ -> error("Invalid values")
    end;
}

abstract production toIntegerASTExpr
top::ASTExpr ::= a::ASTExpr
{
  top.pp = pp"toInteger(${a.pp})";
  top.value =
    case a.value of
    | integerAST(i) -> integerAST(toInteger(i))
    | booleanAST(b) -> integerAST(toInteger(b))
    | floatAST(f) -> integerAST(toInteger(f))
    | stringAST(s) -> integerAST(toInteger(s))
    | _ -> error("Invalid values")
    end;
}

abstract production toBooleanASTExpr
top::ASTExpr ::= a::ASTExpr
{
  top.pp = pp"toBoolean(${a.pp})";
  top.value =
    case a.value of
    | integerAST(i) -> booleanAST(toBoolean(i))
    | booleanAST(b) -> booleanAST(toBoolean(b))
    | floatAST(f) -> booleanAST(toBoolean(f))
    | stringAST(s) -> booleanAST(toBoolean(s))
    | _ -> error("Invalid values")
    end;
}

abstract production toFloatASTExpr
top::ASTExpr ::= a::ASTExpr
{
  top.pp = pp"toFloat(${a.pp})";
  top.value =
    case a.value of
    | integerAST(i) -> floatAST(toFloat(i))
    | booleanAST(b) -> floatAST(toFloat(b))
    | floatAST(f) -> floatAST(toFloat(f))
    | stringAST(s) -> floatAST(toFloat(s))
    | _ -> error("Invalid values")
    end;
}

abstract production toStringASTExpr
top::ASTExpr ::= a::ASTExpr
{
  top.pp = pp"toString(${a.pp})";
  top.value =
    case a.value of
    | integerAST(i) -> stringAST(toString(i))
    | booleanAST(b) -> stringAST(toString(b))
    | floatAST(f) -> stringAST(toString(f))
    | stringAST(s) -> stringAST(toString(s))
    | _ -> error("Invalid values")
    end;
}

-- *Undecorated* pattern match, with no regard to forwarding - not currently used
abstract production matchASTExpr
top::ASTExpr ::= e::ASTExpr pattern::ASTExpr res::ASTExpr fail::ASTExpr
{
  top.pp = pp"case ${e.pp} of ${pattern.pp} -> ${res.pp} | _ -> ${fail.pp} end";
  
  pattern.matchWith = e.value;
  res.substitutionEnv = pattern.substitution.fromJust ++ top.substitutionEnv;
  top.value = if pattern.substitution.isJust then res.value else fail.value;
}

abstract production rewriteASTExpr
top::ASTExpr ::= s::ASTExpr e::ASTExpr
{
  top.pp = pp"rewriteWith(${s.pp}, ${e.pp})";
  
  production st::Strategy = reifyUnchecked(s.value);
  st.term = e.value;
  
  top.value =
    case st.result of
    | just(a) -> AST { core:just(${a}) }
    | nothing() -> AST { core:nothing() }
    end;
}

synthesized attribute astExprs::[ASTExpr];
synthesized attribute values::[AST];
synthesized attribute appValues::[Maybe<AST>];

nonterminal ASTExprs with pps, astExprs, substitutionEnv, values, appValues, matchWith<ASTs>, substitution;

abstract production consASTExpr
top::ASTExprs ::= h::ASTExpr t::ASTExprs
{
  top.pps = h.pp :: t.pps;
  top.astExprs = h :: t.astExprs;
  top.values = h.value :: t.values;
  top.appValues =
    case h of
    | wildASTExpr() -> nothing()
    | _ -> just(h.value)
    end :: t.appValues;
  
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
  top.appValues = [];
  top.substitution =
    case top.matchWith of
    | nilAST() -> just([])
    | _ -> nothing()
    end;
}

function appendASTExprs
ASTExprs ::= a::ASTExprs b::ASTExprs
{
  return
    case a of
    | consASTExpr(h, t) -> consASTExpr(h, appendASTExprs(t, b))
    | nilASTExpr() -> b
    end;
}

synthesized attribute namedValues::[NamedAST];
synthesized attribute namedAppValues::[Pair<String Maybe<AST>>];

nonterminal NamedASTExprs with pps, substitutionEnv, namedValues, namedAppValues, matchWith<[Pair<String AST>]>, substitution;

abstract production consNamedASTExpr
top::NamedASTExprs ::= h::NamedASTExpr t::NamedASTExprs
{
  top.pps = h.pp :: t.pps;
  top.namedValues = h.namedValue :: t.namedValues;
  top.namedAppValues = h.namedAppValue :: t.namedAppValues;
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
  top.namedAppValues = [];
  top.substitution = just([]);
}

function appendNamedASTExprs
NamedASTExprs ::= a::NamedASTExprs b::NamedASTExprs
{
  return
    case a of
    | consNamedASTExpr(h, t) -> consNamedASTExpr(h, appendNamedASTExprs(t, b))
    | nilNamedASTExpr() -> b
    end;
}

synthesized attribute namedValue::NamedAST;
synthesized attribute namedAppValue::Pair<String Maybe<AST>>;

nonterminal NamedASTExpr with pp, substitutionEnv, namedValue, namedAppValue, matchWith<[Pair<String AST>]>, substitution;

abstract production namedASTExpr
top::NamedASTExpr ::= n::String v::ASTExpr
{
  top.pp = pp"${text(n)}=${v.pp}";
  top.namedValue = namedAST(n, v.value);
  top.namedAppValue =
    pair(n,
      case v of
      | wildASTExpr() -> nothing()
      | _ -> just(v.value)
      end);
  
  v.matchWith =
    fromMaybe(
      error("Unexpected annotation " ++ n),
      lookupBy(stringEq, n, top.matchWith));
  top.substitution = v.substitution;
}
