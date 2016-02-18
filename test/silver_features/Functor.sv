
synthesized attribute functorSyn<a> :: a;

nonterminal FunctorTestNT with functorSyn<FunctorTestNT>;
nonterminal FunctorTestNT2 with functorSyn<FunctorTestNT2>;

abstract production consFTNT
top::FunctorTestNT ::= h::FunctorTestNT  t::FunctorTestNT
{
  --propagate functor functorSyn;
}

abstract production consFTNT2
top::FunctorTestNT ::= h::FunctorTestNT2  t::FunctorTestNT
{
  --propagate functor functorSyn;
}

abstract production nilFTNT
top::FunctorTestNT ::= i::Integer
{
  propagate functor functorSyn;
}

abstract production nilFTNT2
top::FunctorTestNT2 ::= s::String
{
  --propagate functor functorSyn;
}

global functorValue :: FunctorTestNT =
  consFTNT(
    consFTNT2(
      nilFTNT2("a"),
      nilFTNT(1)),
    consFTNT2(
      nilFTNT2("b"),
      nilFTNT(2)));

-- Test to ensure it reaches all nils:
--equalityTest(hackUnparse(functorValue), hackUnparse(functorValue.functorSyn), String, silver_tests);