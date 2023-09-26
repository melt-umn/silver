
nonterminal PropagateInhTestNT with autoSyn;
nonterminal PropagateInhTestNT2 with autoTest, autoSyn;

-- Tests whether inherited ... occurs is allowed:
inherited attribute autoTest :: Integer occurs on PropagateInhTestNT;

monoid attribute autoSyn :: Integer with 0, +;

propagate autoSyn, autoTest on PropagateInhTestNT, PropagateInhTestNT2;

abstract production consACTNT
top::PropagateInhTestNT ::= h::PropagateInhTestNT  t::PropagateInhTestNT
{}

abstract production consACTNT2
top::PropagateInhTestNT ::= h::PropagateInhTestNT2  t::PropagateInhTestNT
{}

abstract production nilACTNT
top::PropagateInhTestNT ::=
{
  top.autoSyn <- top.autoTest;
}

abstract production nilACTNT2
top::PropagateInhTestNT2 ::=
{
  top.autoSyn <- top.autoTest;
}

-- Basically just "count the nils" but across two types in the tree:

global autoValue :: PropagateInhTestNT =
  consACTNT(
    consACTNT2(
      nilACTNT2(),
      nilACTNT()),
    consACTNT2(
      nilACTNT2(),
      nilACTNT()));

-- Test to ensure it reaches all nils:
equalityTest(decorate autoValue with { autoTest = 1; }.autoSyn, 4 , Integer, silver_tests);

