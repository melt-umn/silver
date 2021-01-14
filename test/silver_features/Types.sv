import silver:testing;

------------------------------------- Number of parameters to type constructors
terminal ATerminalType 'doesnotmatter';

wrongCode "ATerminalType has kind arity 0, but there are 1 type arguments supplied here" {
 global t :: ATerminalType<String> = error("");
}

nonterminal NTZero;

wrongCode "NTZero has kind arity 0, but there are 1 type arguments supplied here" {
 global t :: NTZero<String> = error("");
}

nonterminal NTOne<a>;

wrongCode "NTOne is not fully applied, it has kind arity 1" {
 global t :: NTOne = error("");
}
wrongCode "NTOne has kind arity 1, but there are 2 type arguments supplied here" {
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

wrongCode "NTTwo is not fully applied, it has kind arity 2" {
 global t :: NTTwo = error("");
}
wrongCode "NTTwo<_ _> is not fully applied, it has kind arity 2" {
 global t :: NTTwo<_ _> = error("");
}
wrongCode "NTTwo<Integer _> is not fully applied, it has kind arity 1" {
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

wrongCode "[] is not fully applied, it has kind arity 1" {
  global badCtrList1::[] = [1, 2, 3];
}
wrongCode "[] has kind arity 1, but there are 2 type arguments supplied here" {
  global badCtrList2::[]<Integer Integer> = [1, 2, 3];
}
wrongCode "[Integer] has kind arity 0, but there are 1 type arguments supplied here" {
  global badCtrList2::[Integer]<Integer> = [1, 2, 3];
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
-- For the moment, errors ignore type names
wrongCode "Argument 2 of function 'silver:core:eq' expected String but argument is of type Integer" {
 global t :: Boolean = astr1 == anum1;
}

wrongCode "repeats type variable names" {
 type TypeTwo<a a> = Integer;
}

wrongCode "NTTwo<a _> is not fully applied, it has kind arity 1" {
 type MyTypeErr<a> = NTTwo<a _>;
}
wrongCode "NTTwo<_ _> is not fully applied, it has kind arity 2" {
 type MyTypeErr = NTTwo<_ _>;
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

