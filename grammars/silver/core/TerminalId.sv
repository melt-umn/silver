grammar silver:core;

-- TODO: Should these be generic list functions?
fun terminalSetEq Boolean ::= ts1::[TerminalId] ts2::[TerminalId] =
  length(ts1) == length(ts2) && all(zipWith(eq, sort(ts1), sort(ts2)));

fun terminalSubset Boolean ::= ts1::[TerminalId] ts2::[TerminalId] =
  -- Probably more efficient than sorting if ts1 is small?
  all(map(contains(_, ts2), ts1));
