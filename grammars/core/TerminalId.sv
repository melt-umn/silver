grammar core;

function terminalIdEq
Boolean ::= t1::TerminalId  t2::TerminalId
{
  return t1 == t2;
}
 
function terminalSetEq
Boolean ::= ts1::[TerminalId] ts2::[TerminalId]
{
  return
    length(ts1) == length(ts2) &&
    all(
      zipWith(
        terminalIdEq,
        map(sortBy(terminalIdEq, _), ts1),
        map(sortBy(terminalIdEq, _), ts2)));
}
