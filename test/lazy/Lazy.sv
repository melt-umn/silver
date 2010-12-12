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
IOVal<Integer> ::= largs::[String] i::IO
{
  local attribute args :: String;
  args = implode(" ", largs);

  local attribute q :: Q;
  q = q1(q);
  
  return ioval(print(q.a_q.str, i), 0);
}
