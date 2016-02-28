
synthesized attribute functorSyn<a> :: a;
annotation functorTestAnno::Integer;

nonterminal FunctorTestNT with functorSyn<FunctorTestNT>, functorTestAnno;
nonterminal FunctorTestNT2 with functorSyn<FunctorTestNT2>, functorTestAnno;

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

-- Test to ensure it reaches all nils:
equalityTest(hackUnparse(functorValueRes), hackUnparse(functorValue.functorSyn), String, silver_tests);