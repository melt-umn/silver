import silver:testing;

------------------------------------- Number/kinds of parameters to type constructors
terminal ATerminalType 'doesnotmatter';

wrongCode "ATerminalType has kind *, but there are 1 type arguments supplied here" {
 global t :: ATerminalType<String> = error("");
}

nonterminal NTZero;

wrongCode "NTZero has kind *, but there are 1 type arguments supplied here" {
 global t :: NTZero<String> = error("");
}

nonterminal NTOne<a>;

wrongCode "NTOne has kind * -> *, but kind * is expected here" {
 global t :: NTOne = error("");
}
wrongCode "NTOne has kind * -> *, but there are 2 type arguments supplied here" {
 global t :: NTOne<String String> = error("");
}

wrongCode "NTZero' is already bound" {
 nonterminal NTZero;
}

wrongCode "repeats type variable names" {
 nonterminal NTTwoBad<a a>;
}
wrongCode "cannot contain _" {
 nonterminal NTTwoBad<a _>;
}

nonterminal NTTwo<a b>;
production ntt top::NTTwo<a b> ::= a b {}

wrongCode "NTTwo has kind * -> * -> *, but kind * is expected here" {
 global t :: NTTwo = error("");
}
wrongCode "NTTwo<_ _> has kind * -> * -> *, but kind * is expected here" {
 global t :: NTTwo<_ _> = error("");
}
wrongCode "NTTwo<Integer _> has kind * -> *, but kind * is expected here" {
 global t :: NTTwo<Integer _> = error("");
}
wrongCode "Missing type argument cannot be followed by a provided argument" {
 global t :: NTTwo<_ Integer> = error("");
}

global t2 :: NTTwo<_ _><Integer _><_><String> = ntt(42, "abc");

synthesized attribute typeTest<a> :: a;

wrongCode "repeats type variable names" {
 attribute typeTest<a> occurs on NTTwo<a a>;
}

wrongCode "Type parameter list cannot contain _" {
 attribute typeTest<a> occurs on NTTwo<a b _>;
}

wrongCode "Attribute type arguments cannot contain _" {
 attribute typeTest<a _> occurs on NTTwo<a b>;
}

global ctrList::[]<Integer> = [1, 2, 3];

wrongCode "[] has kind * -> *, but kind * is expected here" {
  global badCtrList1::[] = [1, 2, 3];
}
wrongCode "[] has kind * -> *, but there are 2 type arguments supplied here" {
  global badCtrList2::[]<Integer Integer> = [1, 2, 3];
}
wrongCode "[Integer] has kind *, but there are 1 type arguments supplied here" {
  global badCtrList2::[Integer]<Integer> = [1, 2, 3];
}

-------------------------------------- Type variables are named properly in error messages
wrongCode "(bar, baz)" {
  global foo::(bar, baz) = 42;
}
wrongCode "(b, a)" {
  global foo::(b, a) = 42;
}
wrongCode "(a1, a)" {
  global foo::(a1, a) = 42;
}
wrongCode "(a, b) has initialization expression with type (silver:core:Pair<c a> ::= c a)" {
  global foo::(a, b) = pair(_, _);
}

-------------------------------------- Type Decls

type MyType<a> = String;

global astr1 :: MyType<Integer> = "hi";
global astr2 :: MyType<String> = "yo";
global astr4 :: MyType<Integer> = astr2;

type MyType2 = Integer;

global anum1 :: MyType2 = 2;
global astr3 :: MyType<MyType2> = toString(anum1);

wrongCode "MyType is a type alias, expecting 1 type arguments." {
 global t :: MyType = error("");
}
wrongCode "MyType expects 1 type arguments, but there are 2 supplied here" {
 global t :: MyType<Integer IntegeR> = error("");
}
wrongCode "MyType is a type alias and cannot be partially applied." {
 global t :: MyType<_> = error("");
}

type MyTypePartial1<a> = NTTwo<a _>;

global mt1::MyTypePartial1<Integer><String> = error("");
wrongCode "silver_features:MyTypePartial1 expects 1 type arguments, but there are 2 supplied here" {
  global mt2::MyTypePartial1<Integer String> = error("");
}

type MyTypePartial2 = NTTwo<_ _>;

wrongCode "MyTypePartial2 has kind * -> * -> *, but there are 1 type arguments supplied here" {
  global mt3::MyTypePartial2<String><Integer> = error("");
}
global mt4::MyTypePartial2<String Integer> = error("");

-- For the moment, errors ignore type names
wrongCode "Argument 2 of function 'silver:core:eq' expected String but argument is of type Integer" {
 global t :: Boolean = astr1 == anum1;
}

wrongCode "repeats type variable names" {
 type TypeTwo<a a> = Integer;
}

----------------------------------------- toString implementations

equalityTest(toString("foo"), "foo",   String, silver_tests);
equalityTest(toString(0),     "0",     String, silver_tests);
equalityTest(toString(0.0),   "0.0",   String, silver_tests);
equalityTest(toString(true),  "true",  String, silver_tests);
equalityTest(toString(false), "false", String, silver_tests);

type MyType3 = Pair<Integer String>;

wrongCode "Operand to toString must be concrete types String, Integer, Float, or Boolean.  Instead it is of type silver:core:Pair<Integer String>" {
  global m3t :: MyType3 = pair(0, "");
  equalityTest(toString(m3t), "<this fails>", String, silver_tests);
}

-------------------------------------- String to/from chars

global stfcTestString :: String = "hello";
global stfcTestChars :: [Integer] =
  [ 104
  , 101
  , 108
  , 108
  , 111
  ];

equalityTest(charsToString(stfcTestChars), stfcTestString, String, silver_tests);
equalityTest(stringToChars(stfcTestString), stfcTestChars, [Integer], silver_tests);

----------------------------------------- Foreign type decls

type FType<a> foreign;

global aft1 :: FType<Integer> = error("");

wrongCode "Declaration of global aft2 with type silver_features:FType<String> has initialization expression with type silver_features:FType<Integer>" {
 global aft2 :: FType<String> = aft1;
}

-------------------------------------- Decorated/InhSet types

inherited attribute env1::[String];
inherited attribute env2::[String];
nonterminal DExpr with env1, env2;
flowtype DExpr = decorate {env1};
production mkDExpr
top::DExpr ::=
{
  production d::DExpr = mkDExpr();
  d.env1 = top.env1;
  d.env2 = top.env2;

  production d1 :: Decorated DExpr = d;
  production d2 :: Decorated DExpr with {env1} = d;
  production d3 :: Decorated DExpr = d;
  production d4 :: Decorated DExpr with {env1, env2} = d;
  production d5 :: Decorated DExpr with {decorate} = d;
  production d6 :: Decorated DExpr with {decorate, env2} = d;
}

global d1 :: Decorated DExpr = decorate mkDExpr() with {env1 = [];};
global d2 :: Decorated DExpr with {env1} = decorate mkDExpr() with {env1 = [];};
global d3 :: Decorated DExpr = decorate mkDExpr() with {env1 = [];};
global d4 :: Decorated DExpr with {env1, env2} = decorate mkDExpr() with {env1 = []; env2 = [];};
global d5 :: Decorated DExpr with {decorate} = decorate mkDExpr() with {env1 = [];};
global d6 :: Decorated DExpr with {decorate, env2} = decorate mkDExpr() with {env1 = []; env2 = [];};

type Inhs1 = {env1};
global d7 :: Decorated DExpr with Inhs1 = decorate mkDExpr() with {env1 = [];};

global d8 :: Decorated DExpr with {env1} = partialUndecorate(decorate mkDExpr() with {env1 = []; env2 = [];});
global d9 :: Decorated DExpr with {env1} = partialUndecorate(decorate mkDExpr() with {env1 = [];});

function getEnv1
{env1} subset i => [String] ::= x::Decorated DExpr with i 
{
  return let y::Decorated DExpr with {env1} = partialUndecorate(x) in y.env1 end;
}

global d10 :: [String] = getEnv1(decorate mkDExpr() with {env1 = []; env2 = [];});
global d11 :: [String] = getEnv1(decorate mkDExpr() with {env1 = [];});

wrongCode "type Decorated silver_features:DExpr with {silver_features:env1, :env2} has initialization expression with type Decorated silver_features:DExpr with {silver_features:env1}" {
  global dBad :: Decorated DExpr with {env1, env2} = decorate mkDExpr() with {env1 = [];};
}

wrongCode "Integer has kind *, but kind InhSet is expected here" {
  global dBad :: Decorated DExpr with Integer = error("");
}

wrongCode "{env1} has kind InhSet, but kind * is expected here" {
  global inhBad :: {env1} = 42;
}

wrongCode "type Decorated silver_features:DExpr with {silver_features:env1} has initialization expression with type Decorated silver_features:DExpr with {silver_features:env2}" {
  global dBad :: Decorated DExpr with {env1} = let res :: Decorated DExpr with {env2} = error("") in res end;
}

wrongCode "Expected return type is Decorated silver_features:DExpr with {silver_features:env1}, but the expression has actual type Decorated silver_features:DExpr with i" {
  function decBad
  Decorated DExpr with {env1} ::= x::Decorated DExpr with i
  {
    return let res :: Decorated DExpr with i = x in res end;
  }
}

wrongCode "{silver_features:env1, :env2} is not a subset of {silver_features:env1} (arising from the use of partialUndecorate)" {
  global dSuper :: Decorated DExpr with {env1, env2} = partialUndecorate(decorate mkDExpr() with {env1 = [];});
}
wrongCode "{silver_features:env2} is not a subset of {silver_features:env1} (arising from the use of partialUndecorate)" {
  global dDisjoint :: Decorated DExpr with {env2} = partialUndecorate(decorate mkDExpr() with {env1 = [];});
}
wrongCode "{silver_features:env1} is not a subset of {silver_features:env2} (arising from the use of getEnv1)" {
  global dDisjoint2 :: [String] = getEnv1(decorate mkDExpr() with {env2 = [];});
}
