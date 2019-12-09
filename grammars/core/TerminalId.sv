grammar core;

function terminalIdEq
Boolean ::= t1::TerminalId  t2::TerminalId
{
  return t1 == t2;
}

function terminalIdLte
Boolean ::= t1::TerminalId  t2::TerminalId
{
  return t1 <= t2;
}
 
function terminalSetEq
Boolean ::= ts1::[TerminalId] ts2::[TerminalId]
{
  return
    length(ts1) == length(ts2) &&
    all(zipWith(terminalIdEq, sortBy(terminalIdLte, ts1), sortBy(terminalIdLte, ts2)));
}
