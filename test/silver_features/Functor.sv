
functor attribute functorSyn;
synthesized attribute functorTestAnnoSum :: Integer;
annotation functorTestAnno::Integer;

nonterminal FunctorTestNT with functorSyn, functorTestAnnoSum, functorTestAnno;
nonterminal FunctorTestNT2 with functorSyn, functorTestAnnoSum, functorTestAnno;

abstract production consFTNT
top::FunctorTestNT ::= h::FunctorTestNT  t::FunctorTestNT
{
  propagate functorSyn;
  top.functorTestAnnoSum = h.functorTestAnnoSum + t.functorTestAnnoSum + top.functorTestAnno;
}

abstract production consFTNT2
top::FunctorTestNT ::= h::FunctorTestNT2  t::FunctorTestNT
{
  propagate functorSyn;
  top.functorTestAnnoSum = h.functorTestAnnoSum + t.functorTestAnnoSum + top.functorTestAnno;
}

abstract production nilFTNT
top::FunctorTestNT ::= i::Integer
{
  top.functorSyn = nilFTNT(10, functorTestAnno=123); -- test non-propagate
  top.functorTestAnnoSum = top.functorTestAnno;
}

abstract production nilFTNT2
top::FunctorTestNT2 ::= s::String
{
  propagate functorSyn;
  top.functorTestAnnoSum = top.functorTestAnno;
}

wrongCode "Functor attributes do not expect explicit type parameters" {
  nonterminal Foo23 with functorSyn<Integer>;
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

-- Test to ensure it reaches all nils:
equalityTest(hackUnparse(functorValueRes), hackUnparse(functorValue.functorSyn), String, silver_tests);
-- Test to ensure annotations are copied correctly
equalityTest(functorValueRes.functorTestAnnoSum, functorValue.functorSyn.functorTestAnnoSum, Integer, silver_tests);
