import silver:testing;

------------------------------------- Number of parameters to type constructors
terminal ATerminalType 'doesnotmatter';

wrongCode "ATerminalType has 0 type variables" {
 global t :: ATerminalType<String> = error("");
}

nonterminal NTZero;

wrongCode "NTZero has 0 type variables, but there are 1 supplied here" {
 global t :: NTZero<String> = error("");
}

nonterminal NTOne<a>;

wrongCode "NTOne has 1 type variables, but there are 0 supplied here" {
 global t :: NTOne = error("");
}
wrongCode "NTOne has 1 type variables, but there are 2 supplied here" {
 global t :: NTOne<String String> = error("");
}

wrongCode "NTZero' is already bound" {
 nonterminal NTZero;
}

wrongCode "repeats type variable names" {
 nonterminal NTTwoBad<a a>;
}

nonterminal NTTwo<a b>;

synthesized attribute typeTest<a> :: a;

wrongCode "repeats type variable names" {
 attribute typeTest<a> occurs on NTTwo<a a>;
}

--nonterminal IO; -- parse error


-------------------------------------- Type Decls

type MyType<a> = String;

global astr1 :: MyType<Integer> = "hi";
global astr2 :: MyType<String> = "yo";
global astr4 :: MyType<Integer> = astr2;

type MyType2 = Integer;

global anum1 :: MyType2 = 2;
global astr3 :: MyType<MyType2> = toString(anum1);

wrongCode "MyType has 1 type variables, but there are 0 supplied here." {
 global t :: MyType = error("");
}
wrongCode "MyType has 1 type variables, but there are 2 supplied here" {
 global t :: MyType<Integer IntegeR> = error("");
}
-- For the moment, errors ignore type names
wrongCode "Operands to == must be the same type. Instead they are String and Integer" {
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

wrongCode "Operand to toString must be concrete types String, Integer, Float, or Boolean.  Instead it is of type core:Pair<Integer String>" {
  global m3t :: MyType3 = pair(0, "");
  equalityTest(toString(m3t), "<this fails>", String, silver_tests);
}

----------------------------------------- Foreign type decls

type FType<a> foreign;

global aft1 :: FType<Integer> = error("");

wrongCode "Declaration of global aft2 with type silver_features:FType<String> has initialization expression with type silver_features:FType<Integer>" {
 global aft2 :: FType<String> = aft1;
}

