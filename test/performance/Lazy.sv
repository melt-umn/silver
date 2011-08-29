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

-- loop with lists!
global infiniteOnes :: [Integer] = 1 :: infiniteOnes;

equalityTest ( take(5,infiniteOnes), [1,1,1,1,1], [Integer], performance_tests ) ;

-- a lot of tests, to ensure list operations are sufficiently lazy
equalityTest ( head(1 :: error("demanded tail!!")), 1, Integer, performance_tests ) ;
equalityTest ( head([1] ++ error("demanded tail!!")), 1, Integer, performance_tests ) ;
equalityTest ( null([1] ++ error("demanded tail!!")), false, Boolean, performance_tests ) ;
equalityTest ( tail([error("demanded head!!")]), [], [Integer], performance_tests ) ;
equalityTest ( head((1 :: error("demaned tail1")) ++ error("demanded tail2")), 1, Integer, performance_tests ) ;
equalityTest ( head((1 :: error("demaned tail1")) ++ error("demanded tail2") ++ 2 :: error("demanded tail3")), 1, Integer, performance_tests ) ;
equalityTest ( head(((1 :: error("demaned tail1")) ++ error("demanded tail2")) ++ 2 :: error("demanded tail3")), 1, Integer, performance_tests ) ;

global nolist :: [Integer] = error ("oops!");

equalityTest ( null(head(nolist) :: tail(nolist)), false, Boolean, performance_tests ) ;

