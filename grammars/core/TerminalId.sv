grammar core;

function terminalIdEq
Boolean ::= t1::TerminalId  t2::TerminalId
{
  return t1 == t2;
}
