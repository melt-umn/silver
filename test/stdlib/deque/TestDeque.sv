
import silver:util:deque;
import silver:testing ;
import lib:extcore ;
import stdlib;

equalityTest ( dqIsEmpty(dqEmpty()), true, Boolean, core_tests ) ;

global dq1 :: Deque<Integer> = dqCons(1, dqEmpty()); -- 1

equalityTest ( dqIsEmpty(dq1), false, Boolean, core_tests ) ;

-- head == last
equalityTest ( dqHead(dq1), 1, Integer, core_tests ) ;
equalityTest ( dqLast(dq1), 1, Integer, core_tests ) ;

-- tail/init empty.
equalityTest ( dqIsEmpty(dqTail(dq1)), true, Boolean, core_tests ) ;
equalityTest ( dqIsEmpty(dqInit(dq1)), true, Boolean, core_tests ) ;

-- test cons
global dq2 :: Deque<Integer> = dqCons(2, dq1); -- 2 1

equalityTest ( dqHead(dq2), 2, Integer, core_tests ) ;
equalityTest ( dqLast(dq2), 1, Integer, core_tests ) ;

equalityTest ( dqHead(dqTail(dq2)), 1, Integer, core_tests ) ;
equalityTest ( dqLast(dqTail(dq2)), 1, Integer, core_tests ) ;

equalityTest ( dqHead(dqInit(dq2)), 2, Integer, core_tests ) ;
equalityTest ( dqLast(dqInit(dq2)), 2, Integer, core_tests ) ;

-- test snoc
global dq3 :: Deque<Integer> = dqSnoc(dq2, 3); -- 2 1 3

equalityTest ( dqHead(dq3), 2, Integer, core_tests ) ;
equalityTest ( dqLast(dq3), 3, Integer, core_tests ) ;

equalityTest ( dqHead(dqTail(dq3)), 1, Integer, core_tests ) ;
equalityTest ( dqLast(dqTail(dq3)), 3, Integer, core_tests ) ;

equalityTest ( dqHead(dqInit(dq3)), 2, Integer, core_tests ) ;
equalityTest ( dqLast(dqInit(dq3)), 1, Integer, core_tests ) ;

-- cons enough to rotate
global dq4 :: Deque<Integer> = dqCons(12, dqCons(11, dqCons(10, dqCons(9, dqCons(8, dqCons(7, dqCons(6, dqCons(5, dqCons(4, dq3)))))))));

equalityTest ( dqHead(dq4), 12, Integer, core_tests ) ;
equalityTest ( dqLast(dq4), 3, Integer, core_tests ) ;

-- init enough to rotate
global dq5 :: Deque<Integer> = dqInit(dqInit(dqInit(dqInit(dqInit(dqInit(dq4))))));

equalityTest ( dqHead(dq5), 12, Integer, core_tests ) ;
equalityTest ( dqLast(dq5), 7, Integer, core_tests ) ;

-- tail enough to rotate
global dq6 :: Deque<Integer> = dqTail(dqTail(dqTail(dqTail(dqTail(dqTail(dq4))))));

equalityTest ( dqHead(dq6), 6, Integer, core_tests ) ;
equalityTest ( dqLast(dq6), 3, Integer, core_tests ) ;

global dq7 :: Deque<Integer> = dqTail(dqTail(dqTail(dqTail(dqTail(dqTail(dq5))))));

equalityTest ( dqIsEmpty(dq7), true, Boolean, core_tests ) ;



