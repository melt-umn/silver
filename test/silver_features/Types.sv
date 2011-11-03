
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
wrongCode "Duplicate type variable names listed" {
 nonterminal NTTwo<a a>;
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

wrongCode "Duplicate type variable names listed" {
 type TypeTwo<a a> = Integer;
}

----------------------------------------- Foreign type decls

type FType<a> foreign;

global aft1 :: FType<Integer> = error("");

wrongCode "Declaration of global aft2 with type silver_features:FType<String> has initialization expression with type silver_features:FType<Integer>" {
 global aft2 :: FType<String> = aft1;
}

