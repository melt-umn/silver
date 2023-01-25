grammar silver_features;

nonterminal UndecNT with compareTo, isEqual;
flowtype UndecNT = decorate {};
propagate compareTo, isEqual on UndecNT;

production fork1UNT
top::UndecNT ::= a::Decorated! UndecNT b::UndecNT
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
top::UndecNT ::= a::Decorated! UndecNT
{
  undecorates to a;
}

equalityTest(fork2UNT(leafUNT(), leafUNT()), fork2UNT(leafUNT(), leafUNT()), UndecNT, silver_tests);

function mkUNT1
Decorated UndecNT ::=
{
  local a::UndecNT = leafUNT();
  local res::UndecNT = fork1UNT(a, leafUNT());
  return res;
}
equalityTest(new(mkUNT1()), fork2UNT(leafUNT(), leafUNT()), UndecNT, silver_tests);

function mkUNT2
Decorated UndecNT ::=
{
  local a::UndecNT = leafUNT();
  local res::UndecNT = wrapUNT(a);
  return res;
}
equalityTest(new(mkUNT2()), leafUNT(), UndecNT, silver_tests);

function mkUNT3
Decorated UndecNT ::=
{
  local a::UndecNT = leafUNT();
  local res1::UndecNT = wrapUNT(a);
  local res2::UndecNT = fork2UNT(fork1UNT(res1, leafUNT()), leafUNT());
  return res2;
}
equalityTest(new(mkUNT3()), fork2UNT(fork2UNT(leafUNT(), leafUNT()), leafUNT()), UndecNT, silver_tests);
