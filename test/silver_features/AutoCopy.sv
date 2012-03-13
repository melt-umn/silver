
nonterminal AutoCopyTestNT with autoSyn;
nonterminal AutoCopyTestNT2 with autoTest, autoSyn;

-- Tests whether autocopy ... occurs is allowed:
autocopy attribute autoTest :: Integer occurs on AutoCopyTestNT;

synthesized attribute autoSyn :: Integer;

abstract production consACTNT
top::AutoCopyTestNT ::= h::AutoCopyTestNT  t::AutoCopyTestNT
{
  top.autoSyn = h.autoSyn + t.autoSyn;
}

abstract production consACTNT2
top::AutoCopyTestNT ::= h::AutoCopyTestNT2  t::AutoCopyTestNT
{
  top.autoSyn = h.autoSyn + t.autoSyn;
}

abstract production nilACTNT
top::AutoCopyTestNT ::=
{
  top.autoSyn = top.autoTest;
}

abstract production nilACTNT2
top::AutoCopyTestNT2 ::=
{
  top.autoSyn = top.autoTest;
}

-- Basically just "count the nils" but across two types in the tree:

global autoValue :: AutoCopyTestNT =
  consACTNT(
    consACTNT2(
      nilACTNT2(),
      nilACTNT()),
    consACTNT2(
      nilACTNT2(),
      nilACTNT()));

-- Test to ensure it reaches all nils:
equalityTest(decorate autoValue with { autoTest = 1; }.autoSyn, 4 , Integer, silver_tests);

