grammar lazy;

nonterminal Q;

synthesized attribute a_q :: Q occurs on Q;
synthesized attribute str :: String occurs on Q;

abstract production q1
top::Q ::= bot::Q
{
  top.a_q = top;
  top.str = "blah\n";
}

function main
IO ::= args::String i::IO
{
  local attribute q :: Q;
  q = q1(q);
  
  return print(q.a_q.str, i);
}
