grammar performance;

nonterminal Q;

synthesized attribute a_q :: Q occurs on Q;
synthesized attribute str :: String occurs on Q;

abstract production q1
top::Q ::= bot::Q
{
  top.a_q = bot;
  top.str = "q";
}

-- loop!
global q :: Q = q1(q);

equalityTest ( q.str, "q", String, performance_tests ) ;
equalityTest ( q.a_q.str, "q", String, performance_tests ) ;
equalityTest ( q.a_q.a_q.str, "q", String, performance_tests ) ;
equalityTest ( q.a_q.a_q.a_q.str, "q", String, performance_tests ) ;

