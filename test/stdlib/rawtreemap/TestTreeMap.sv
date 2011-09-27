

import silver:testing;
import lib:extcore;
import silver:util:raw:treemap as tm;
import stdlib;

global e :: tm:Map<String Integer> = tm:empty(compareString);

global t1 :: tm:Map<String Integer> =
  tm:add([pair("1", 1), pair("2", 2), pair("3", 3), pair("4", 4), pair("5", 5), pair("6", 6), pair("1", 1), pair("2", 2), pair("0", 0), pair("7", 7)], e);

global t2 :: tm:Map<String Integer> = 
  tm:add([pair("g", 1), pair("f", 2), pair("d", 3), pair("s", 4), pair("a", 5), pair("p", 6), pair("q", 1), pair("h", 2), pair("i", 0), pair("q", 7)], t1);

equalityTest ( tm:lookup("1", t2), [1,1], [Integer], core_tests ) ;
equalityTest ( tm:lookup("2", t2), [2,2], [Integer], core_tests ) ;
equalityTest ( tm:lookup("3", t2), [3], [Integer], core_tests ) ;
equalityTest ( tm:lookup("4", t2), [4], [Integer], core_tests ) ;
equalityTest ( tm:lookup("5", t2), [5], [Integer], core_tests ) ;
equalityTest ( tm:lookup("6", t2), [6], [Integer], core_tests ) ;
equalityTest ( tm:lookup("7", t2), [7], [Integer], core_tests ) ;
equalityTest ( tm:lookup("8", t2), [], [Integer], core_tests ) ;
equalityTest ( tm:lookup("0", t2), [0], [Integer], core_tests ) ;

equalityTest ( tm:lookup("g", t2), [1], [Integer], core_tests ) ;
equalityTest ( tm:lookup("h", t2), [2], [Integer], core_tests ) ;
equalityTest ( tm:lookup("j", t2), [], [Integer], core_tests ) ;
equalityTest ( tm:lookup("k", t2), [], [Integer], core_tests ) ;
equalityTest ( tm:lookup("i", t2), [0], [Integer], core_tests ) ;
equalityTest ( tm:lookup("f", t2), [2], [Integer], core_tests ) ;
equalityTest ( tm:lookup("a", t2), [5], [Integer], core_tests ) ;


global l1 :: [Pair<String Integer>] =
 [pair("hi", 2), pair("hello", 4), pair("hola", 6), pair("quepasa", 11)];

global t6 :: tm:Map<String Integer> = tm:add(l1, t2);

equalityTest ( tm:lookup("1", t6), [1,1], [Integer], core_tests ) ;
equalityTest ( tm:lookup("5", t6), [5], [Integer], core_tests ) ;
equalityTest ( tm:lookup("4", t6), [4], [Integer], core_tests ) ;
equalityTest ( tm:lookup("h", t6), [2], [Integer], core_tests ) ;
equalityTest ( tm:lookup("8", t6), [], [Integer], core_tests ) ;
equalityTest ( tm:lookup("j", t6), [], [Integer], core_tests ) ;
equalityTest ( tm:lookup("i", t6), [0], [Integer], core_tests ) ;
equalityTest ( tm:lookup("f", t6), [2], [Integer], core_tests ) ;
equalityTest ( tm:lookup("a", t6), [5], [Integer], core_tests ) ;
equalityTest ( tm:lookup("hi", t6), [2], [Integer], core_tests ) ;
equalityTest ( tm:lookup("hello", t6), [4], [Integer], core_tests ) ;
equalityTest ( tm:lookup("hola", t6), [6], [Integer], core_tests ) ;
equalityTest ( tm:lookup("quepasa", t6), [11], [Integer], core_tests ) ;

global l2 :: [Pair<String Integer>] =
 tm:toList( tm:add( l1, e ) );

equalityTest ( head(l2).fst, "hello", String, core_tests ) ;
equalityTest ( head(tail(l2)).fst, "hi", String, core_tests ) ;
equalityTest ( head(tail(tail(l2))).fst, "hola", String, core_tests ) ;
equalityTest ( head(tail(tail(tail(l2)))).fst, "quepasa", String, core_tests ) ;

equalityTest ( head(l2).snd, 4, Integer, core_tests ) ;
equalityTest ( head(tail(l2)).snd, 2, Integer, core_tests ) ;
equalityTest ( head(tail(tail(l2))).snd, 6, Integer, core_tests ) ;
equalityTest ( head(tail(tail(tail(l2)))).snd, 11, Integer, core_tests ) ;

equalityTest ( null(tail(tail(tail(tail(l2))))), true, Boolean, core_tests ) ;


