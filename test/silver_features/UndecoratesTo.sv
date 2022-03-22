grammar silver_features;

nonterminal UndecNT with compareTo, isEqual;
propagate compareTo, isEqual on UndecNT;

production fork1UNT
top::UndecNT ::= a::UndecNT b::UndecNT
{
  undecorates to fork2UNT(a, b);
}

production fork2UNT
top::UndecNT ::= a::UndecNT b::UndecNT
{
}

production leafUNT
top::UndecNT ::=
{}

production wrapUNT
top::UndecNT ::= a::UndecNT
{
  undecorates to a;
}

production forkRefUNT
top::UndecNT ::= a::Decorated UndecNT with {}  b::UndecNT
{
  undecorates to fork2UNT(new(a), b);
  forwards to fork1UNT(new(a), b);  -- For well-definedness of isEqual
}

equalityTest(fork1UNT(leafUNT(), leafUNT()), fork1UNT(leafUNT(), leafUNT()), UndecNT, silver_tests);
equalityTest(new(decorate fork1UNT(leafUNT(), leafUNT()) with {}), fork2UNT(leafUNT(), leafUNT()), UndecNT, silver_tests);

equalityTest(wrapUNT(leafUNT()), wrapUNT(leafUNT()), UndecNT, silver_tests);
equalityTest(new(decorate wrapUNT(leafUNT()) with {}), leafUNT(), UndecNT, silver_tests);

equalityTest(
  new(decorate forkRefUNT(decorate wrapUNT(leafUNT()) with {}, fork1UNT(leafUNT(), leafUNT())) with {}),
  fork2UNT(leafUNT(), fork2UNT(leafUNT(), leafUNT())),
  UndecNT, silver_tests);
