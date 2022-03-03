grammar silver_features:treegen;

imports silver_features;
imports silver:testing;
imports silver:util:random;

terminal A 'a' lexer classes C1;
terminal AB /a|b/ lexer classes C2;
lexer class C2;
lexer class C1 dominates C2;

synthesized attribute name::String;
nonterminal Foo with name;
concrete productions top::Foo
| ab::AB { top.name = ab.lexeme; }

generator genFoo::Foo {
  silver_features:treegen;
}

equalityTest(
  map((.name), runSeedRandomGen(42, sequence(repeat(genFoo(3, 4), 10)))),
  repeat("b", 10),
  [String], silver_tests);
