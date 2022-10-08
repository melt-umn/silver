grammar silver_features;

threaded attribute tinh1, tsyn1 :: [Integer] direction = left to right;
threaded attribute tinh2, tsyn2 :: [Integer] direction = right to left;

nonterminal TNT with tinh1, tinh2, tsyn1, tsyn2;

production tFork
top::TNT ::= t1::TNT t2::TNT
{ propagate tinh1, tinh2, tsyn1, tsyn2; }

production tLeaf
top::TNT ::= i::Integer
{
  top.tsyn1 = top.tinh1 ++ [i];
  top.tsyn2 = top.tinh2 ++ [i];
}

production tFwrd
top::TNT ::= f::TNT
{
  propagate tinh1, tinh2, tsyn1, tsyn2;
  forwards to tLeaf(4);
}

global tnt::TNT = tFork(tFork(tLeaf(1), tLeaf(2)), tFwrd(tLeaf(3)));

equalityTest(decorate tnt with {tinh1 = [0];}.tsyn1, [0, 1, 2, 3, 4], [Integer], silver_tests);
equalityTest(decorate tnt with {tinh2 = [0];}.tsyn2, [0, 3, 4, 2, 1], [Integer], silver_tests);
