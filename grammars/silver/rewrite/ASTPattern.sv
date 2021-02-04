grammar silver:rewrite;

inherited attribute matchWith<a>::a;
synthesized attribute substitution::Maybe<[Pair<String AST>]>;

nonterminal ASTPattern with pp, matchWith<AST>, substitution;

-- AST constructors
abstract production prodCallASTPattern
top::ASTPattern ::= prodName::String children::ASTPatterns annotations::NamedASTPatterns
{
  top.pp = pp"${text(prodName)}(${ppImplode(pp", ", children.pps ++ annotations.pps)})";
  
  children.matchWith = case top.matchWith of nonterminalAST(_, c, _) -> c end;
  annotations.matchWith = case top.matchWith of nonterminalAST(_, _, a) -> a.bindings end;
  top.substitution =
    do {
      case top.matchWith of
      | nonterminalAST(otherProdName, _, _) when prodName == otherProdName -> just(unit())
      | _ -> nothing()
      end;
      childrenSubstitution::[Pair<String AST>] <- children.substitution;
      annotationsSubstitution::[Pair<String AST>] <- annotations.substitution;
      return childrenSubstitution ++ annotationsSubstitution;
    };
}

abstract production consListASTPattern
top::ASTPattern ::= h::ASTPattern t::ASTPattern
{
  top.pp = pp"(${h.pp} :: ${t.pp})";
  
  h.matchWith = case top.matchWith of listAST(consAST(h, _)) -> h end;
  t.matchWith = case top.matchWith of listAST(consAST(_, t)) -> listAST(t) end;
  top.substitution =
    do {
      case top.matchWith of
      | listAST(consAST(_, _)) -> just(unit())
      | _ -> nothing()
      end;
      hSubstitution::[Pair<String AST>] <- h.substitution;
      tSubstitution::[Pair<String AST>] <- t.substitution;
      return hSubstitution ++ tSubstitution;
    };
}

abstract production nilListASTPattern
top::ASTPattern ::=
{
  top.pp = pp"[]";
  top.substitution =
    case top.matchWith of
    | listAST(nilAST()) -> just([])
    | _ -> nothing()
    end;
}

abstract production listASTPattern
top::ASTPattern ::= vals::ASTPatterns
{
  top.pp = pp"[${(ppImplode(pp", ", vals.pps))}]";
  forwards to foldr(consListASTPattern, nilListASTPattern(), vals.astPatterns);
}

-- Constants
abstract production stringASTPattern
top::ASTPattern ::= s::String
{
  top.pp = pp"${text(escapeString(s))}";
  top.substitution =
    case top.matchWith of
    | stringAST(s1) when s == s1 -> just([])
    | _ -> nothing()
    end;
}

abstract production integerASTPattern
top::ASTPattern ::= i::Integer
{
  top.pp = pp"${text(toString(i))}";
  top.substitution =
    case top.matchWith of
    | integerAST(i1) when i == i1 -> just([])
    | _ -> nothing()
    end;
}

abstract production floatASTPattern
top::ASTPattern ::= f::Float
{
  top.pp = pp"${text(toString(f))}";
  top.substitution =
    case top.matchWith of
    | floatAST(f1) when f == f1 -> just([])
    | _ -> nothing()
    end;
}

abstract production booleanASTPattern
top::ASTPattern ::= b::Boolean
{
  top.pp = pp"${text(toString(b))}";
  top.substitution =
    case top.matchWith of
    | booleanAST(b1) when b == b1 -> just([])
    | _ -> nothing()
    end;
}

-- Meta stuff
abstract production varASTPattern
top::ASTPattern ::= n::String
{
  top.pp = text(n);
  top.substitution = just([pair(n, top.matchWith)]);
}

abstract production wildASTPattern
top::ASTPattern ::=
{
  top.pp = pp"_";
  top.substitution = just([]);
}

synthesized attribute astPatterns::[ASTPattern];

nonterminal ASTPatterns with pps, astPatterns, matchWith<ASTs>, substitution;

abstract production consASTPattern
top::ASTPatterns ::= h::ASTPattern t::ASTPatterns
{
  top.pps = h.pp :: t.pps;
  top.astPatterns = h :: t.astPatterns;
  
  h.matchWith = case top.matchWith of consAST(h, _) -> h end;
  t.matchWith = case top.matchWith of consAST(_, t) -> t end;
  top.substitution =
    do {
      case top.matchWith of
      | consAST(_, _) -> just(unit())
      | _ -> nothing()
      end;
      hSubstitution::[Pair<String AST>] <- h.substitution;
      tSubstitution::[Pair<String AST>] <- t.substitution;
      return hSubstitution ++ tSubstitution;
    };
}

abstract production nilASTPattern
top::ASTPatterns ::=
{
  top.pps = [];
  top.astPatterns = [];
  top.substitution =
    case top.matchWith of
    | nilAST() -> just([])
    | _ -> nothing()
    end;
}

function appendASTPatterns
ASTPatterns ::= a::ASTPatterns b::ASTPatterns
{
  return
    case a of
    | consASTPattern(h, t) -> consASTPattern(h, appendASTPatterns(t, b))
    | nilASTPattern() -> b
    end;
}

nonterminal NamedASTPatterns with pps, substitutionEnv, matchWith<[Pair<String AST>]>, substitution;

abstract production consNamedASTPattern
top::NamedASTPatterns ::= h::NamedASTPattern t::NamedASTPatterns
{
  top.pps = h.pp :: t.pps;
  top.substitution =
    do {
      hSubstitution::[Pair<String AST>] <- h.substitution;
      tSubstitution::[Pair<String AST>] <- t.substitution;
      return hSubstitution ++ tSubstitution;
    };
  
  h.matchWith = top.matchWith;
  t.matchWith = top.matchWith;
}

abstract production nilNamedASTPattern
top::NamedASTPatterns ::=
{
  top.pps = [];
  top.substitution = just([]);
}

function appendNamedASTPatterns
NamedASTPatterns ::= a::NamedASTPatterns b::NamedASTPatterns
{
  return
    case a of
    | consNamedASTPattern(h, t) -> consNamedASTPattern(h, appendNamedASTPatterns(t, b))
    | nilNamedASTPattern() -> b
    end;
}

nonterminal NamedASTPattern with pp, substitutionEnv, matchWith<[Pair<String AST>]>, substitution;

abstract production namedASTPattern
top::NamedASTPattern ::= n::String v::ASTPattern
{
  top.pp = pp"${text(n)}=${v.pp}";
  
  v.matchWith =
    fromMaybe(
      error("Unexpected annotation " ++ n),
      lookup(n, top.matchWith));
  top.substitution = v.substitution;
}
