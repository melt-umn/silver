
import silver:util:deque as dq;
import silver:testing ;
import stdlib;

equalityTest ( dq:isEmpty(dq:empty()), true, Boolean, core_tests ) ;

global dq1 :: dq:Deque<Integer> = dq:cons(1, dq:empty()); -- 1

equalityTest ( dq:isEmpty(dq1), false, Boolean, core_tests ) ;

-- head == last
equalityTest ( dq:head(dq1), 1, Integer, core_tests ) ;
equalityTest ( dq:last(dq1), 1, Integer, core_tests ) ;

-- tail/init empty.
equalityTest ( dq:isEmpty(dq:tail(dq1)), true, Boolean, core_tests ) ;
equalityTest ( dq:isEmpty(dq:init(dq1)), true, Boolean, core_tests ) ;

-- test cons
global dq2 :: dq:Deque<Integer> = dq:cons(2, dq1); -- 2 1

equalityTest ( dq:head(dq2), 2, Integer, core_tests ) ;
equalityTest ( dq:last(dq2), 1, Integer, core_tests ) ;

equalityTest ( dq:head(dq:tail(dq2)), 1, Integer, core_tests ) ;
equalityTest ( dq:last(dq:tail(dq2)), 1, Integer, core_tests ) ;

equalityTest ( dq:head(dq:init(dq2)), 2, Integer, core_tests ) ;
equalityTest ( dq:last(dq:init(dq2)), 2, Integer, core_tests ) ;

-- test snoc
global dq3 :: dq:Deque<Integer> = dq:snoc(dq2, 3); -- 2 1 3

equalityTest ( dq:head(dq3), 2, Integer, core_tests ) ;
equalityTest ( dq:last(dq3), 3, Integer, core_tests ) ;

equalityTest ( dq:head(dq:tail(dq3)), 1, Integer, core_tests ) ;
equalityTest ( dq:last(dq:tail(dq3)), 3, Integer, core_tests ) ;

equalityTest ( dq:head(dq:init(dq3)), 2, Integer, core_tests ) ;
equalityTest ( dq:last(dq:init(dq3)), 1, Integer, core_tests ) ;

-- cons enough to rotate
global dq4 :: dq:Deque<Integer> = dq:cons(12, dq:cons(11, dq:cons(10, dq:cons(9, dq:cons(8, dq:cons(7, dq:cons(6, dq:cons(5, dq:cons(4, dq3)))))))));

equalityTest ( dq:head(dq4), 12, Integer, core_tests ) ;
equalityTest ( dq:last(dq4), 3, Integer, core_tests ) ;

-- init enough to rotate
global dq5 :: dq:Deque<Integer> = dq:init(dq:init(dq:init(dq:init(dq:init(dq:init(dq4))))));

equalityTest ( dq:head(dq5), 12, Integer, core_tests ) ;
equalityTest ( dq:last(dq5), 7, Integer, core_tests ) ;

-- tail enough to rotate
global dq6 :: dq:Deque<Integer> = dq:tail(dq:tail(dq:tail(dq:tail(dq:tail(dq:tail(dq4))))));

equalityTest ( dq:head(dq6), 6, Integer, core_tests ) ;
equalityTest ( dq:last(dq6), 3, Integer, core_tests ) ;

global dq7 :: dq:Deque<Integer> = dq:tail(dq:tail(dq:tail(dq:tail(dq:tail(dq:tail(dq5))))));

equalityTest ( dq:isEmpty(dq7), true, Boolean, core_tests ) ;



