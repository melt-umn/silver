
import silver:reflect;
import silver:langutil;
import silver:langutil:pp;

functor attribute functorSyn;
annotation functorTestAnno::Integer;

functor attribute functorSyn2 occurs on FunctorTestNT, FunctorTestNT2;
propagate functorSyn2 on FunctorTestNT, FunctorTestNT2;

nonterminal FunctorTestNT with functorSyn, functorTestAnno;
nonterminal FunctorTestNT2 with functorSyn, functorTestAnno;

abstract production consFTNT
top::FunctorTestNT ::= h::FunctorTestNT  t::FunctorTestNT
{
  propagate functorSyn;
}

abstract production consFTNT2
top::FunctorTestNT ::= h::FunctorTestNT2  t::FunctorTestNT
{
  propagate functorSyn;
}

abstract production nilFTNT
top::FunctorTestNT ::= i::Integer
{
  top.functorSyn = nilFTNT(10, functorTestAnno=123); -- test non-propagate
}

abstract production forwardingFTNT
top::FunctorTestNT ::= h::FunctorTestNT2
{
  forwards to
    consFTNT2(
      @h,
      nilFTNT(42, functorTestAnno=top.functorTestAnno),
      functorTestAnno=top.functorTestAnno);
}

abstract production nilFTNT2
top::FunctorTestNT2 ::= s::String
{
  propagate functorSyn;
}

global functorValue :: FunctorTestNT =
  consFTNT(
    consFTNT2(
      nilFTNT2("a", functorTestAnno=1),
      nilFTNT(1, functorTestAnno=2),
      functorTestAnno=3),
    consFTNT2(
      nilFTNT2("b", functorTestAnno=4),
      nilFTNT(2, functorTestAnno=5),
      functorTestAnno=6),
    functorTestAnno=7);

global functorValueRes :: FunctorTestNT =
  consFTNT(
    consFTNT2(
      nilFTNT2("a", functorTestAnno=1),
      nilFTNT(10, functorTestAnno=123),
      functorTestAnno=3),
    consFTNT2(
      nilFTNT2("b", functorTestAnno=4),
      nilFTNT(10, functorTestAnno=123),
      functorTestAnno=6),
    functorTestAnno=7);

-- Test to ensure it reaches all nils and copies annos correctly
equalityTest(show(80, reflect(functorValueRes).pp), show(80, reflect(functorValue.functorSyn).pp), String, silver_tests);

global functorValue2 :: FunctorTestNT =
  consFTNT(
    consFTNT2(
      nilFTNT2("a", functorTestAnno=1),
      nilFTNT(1, functorTestAnno=2),
      functorTestAnno=3),
    forwardingFTNT(
      nilFTNT2("b", functorTestAnno=4),
      functorTestAnno=5),
    functorTestAnno=6);

global functorValueRes2 :: FunctorTestNT =
  consFTNT(
    consFTNT2(
      nilFTNT2("a", functorTestAnno=1),
      nilFTNT(1, functorTestAnno=2),
      functorTestAnno=3),
    consFTNT2(
      nilFTNT2("b", functorTestAnno=4),
      nilFTNT(42, functorTestAnno=5),
      functorTestAnno=5),
    functorTestAnno=6);

-- Test global propagation
equalityTest(show(80, reflect(functorValueRes2).pp), show(80, reflect(functorValue2.functorSyn2).pp), String, silver_tests);

nonterminal FunctorTestNT3 with functorSyn<Integer>;

abstract production ftnt3
top::FunctorTestNT3 ::=
{
  top.functorSyn = 42;
}

equalityTest(ftnt3().functorSyn, 42, Integer, silver_tests);

wrongCode "expects 1 type variables, but 2 were provided" {
  nonterminal Foo23 with functorSyn<Integer Boolean>;
}
