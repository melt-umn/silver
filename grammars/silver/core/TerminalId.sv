grammar silver:core;

-- TODO: Should these be generic list functions?
function terminalSetEq
Boolean ::= ts1::[TerminalId] ts2::[TerminalId]
{
  return length(ts1) == length(ts2) && all(zipWith(eq, sort(ts1), sort(ts2)));
}

function terminalSubset
Boolean ::= ts1::[TerminalId] ts2::[TerminalId]
{
  -- Probably more efficient than sorting if ts1 is small?
  return all(map(contains(_, ts2), ts1));
}
