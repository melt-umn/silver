

function succ
Integer ::= i::Integer
{ return i + 1; }

-- map tests
equalityTest ( map(succ, [1,2,3,4]), [2,3,4,5],
               [Integer], core_tests ) ;
equalityTest ( map(succ, []), [],
               [Integer], core_tests ) ;

function sub
Integer ::= x::Integer y::Integer
{ return x - y ; }

-- fold tests
equalityTest ( foldl(sub, 1, [3]), -2,
               Integer, core_tests ) ;
equalityTest ( foldl(sub, 10, [2,4]), 4,
               Integer, core_tests ) ;
equalityTest ( foldl(stringConcat, "1 ", ["10 ","2 ","4"]), "1 10 2 4",
               String, core_tests ) ;
equalityTest ( foldr(sub, 2, [10,8,4]), 4, 
               Integer, core_tests ) ;
equalityTest ( foldr(stringConcat, "2", ["10","8","4"]), "10842", 
               String, core_tests ) ;
equalityTest ( foldr1(sub,[10,4,2]), 8, 
               Integer, core_tests ) ;
equalityTest ( foldl1(sub,[10,4,2]), 4, 
               Integer, core_tests ) ;

function even
Boolean ::= i::Integer
{ return case i of 0 -> true | 1 -> false | _ -> even(i-2) end; }

-- filter tests
equalityTest ( filter(even, [1,2,3,4]), [2,4],
               [Integer], core_tests ) ;
equalityTest ( filter(even, [1,2,3]), [2],
               [Integer], core_tests ) ;
equalityTest ( filter(even, [1,5,3,7]), [],
               [Integer], core_tests ) ;
equalityTest ( filter(even, []), [],
               [Integer], core_tests ) ;

-- partition tests

equalityTest ( partition(even, []).fst,   [],
               [Integer], core_tests ) ;
equalityTest ( partition(even, []).snd,   [],
               [Integer], core_tests ) ;
equalityTest ( partition(even, [1]).fst,   [],
               [Integer], core_tests ) ;
equalityTest ( partition(even, [1]).snd,   [1],
               [Integer], core_tests ) ;
equalityTest ( partition(even, [1,2,3,4,5,6,7,8]).fst,   [2,4,6,8],
               [Integer], core_tests ) ;


-- containsBy TODO

-- nubBy tests
equalityTest ( nubBy (equalsInteger, [1,2,3,4,3,2,1]), [1,2,3,4], 
               [Integer], core_tests ) ;

equalityTest ( nubBy (equalsInteger, [1,2,3,4]), [1,2,3,4], 
               [Integer], core_tests ) ;

equalityTest ( nubBy (equalsInteger, [ ]), [ ], 
               [Integer], core_tests ) ;

-- removeBy tests
equalityTest ( removeBy (equalsInteger, 2, [1,2,3,4,3,2,1]), [1,3,4,3,1],
               [Integer], core_tests ) ;

equalityTest ( removeBy (equalsInteger, 9, [1,2,3,4,3,2,1]), [1,2,3,4,3,2,1],
               [Integer], core_tests ) ;

equalityTest ( removeBy (equalsInteger, 9, [ ]), [ ],
               [Integer], core_tests ) ;

-- removeAllBy tests
equalityTest ( removeAllBy (equalsInteger, [], [1,2,3]), [1,2,3],
               [Integer], core_tests ) ;

equalityTest ( removeAllBy (equalsInteger, [1,3,5], [1,2,3,4,5,6]), [2,4,6],
               [Integer], core_tests ) ;

equalityTest ( removeAllBy (equalsInteger, [1,3,5], [1,1,3,4,5,3]), [4],
               [Integer], core_tests ) ;
equalityTest ( removeAllBy (equalsInteger, [1,3,5], [5,3,1,3,5]), [],
               [Integer], core_tests ) ;

-- last TODO
-- drop TODO
-- take TODO

function equals1  Boolean ::= x::Integer { return x==1;}
function notEquals1  Boolean ::= x::Integer { return x!=1;}

-- takeWhile
equalityTest ( takeWhile (equals1, [2,3,1,3,5]), [],
               [Integer], core_tests ) ;
equalityTest ( takeWhile (equals1, [1,1,1,2,3,1,3,5]), [1,1,1],
               [Integer], core_tests ) ;
equalityTest ( takeWhile (equals1, [1,1,1]), [1,1,1],
               [Integer], core_tests ) ;

-- dropWhile
equalityTest ( dropWhile (equals1, [2,3,1,3,5]), [2,3,1,3,5],
               [Integer], core_tests ) ;
equalityTest ( dropWhile (equals1, [1,1,1,2,3,1,3,5]), [2,3,1,3,5],
               [Integer], core_tests ) ;
equalityTest ( dropWhile (equals1, [1,1,1]), [],
               [Integer], core_tests ) ;

-- takeUntil
equalityTest ( takeUntil (equals1, [2,3,1,3,5]), [2,3],
               [Integer], core_tests ) ;
equalityTest ( takeUntil (equals1, [1,1,1,2,3,1,3,5]), [],
               [Integer], core_tests ) ;
equalityTest ( takeUntil (equals1, [2,3,4]), [2,3,4],
               [Integer], core_tests ) ;
equalityTest ( takeUntil (equals1, []), [],
               [Integer], core_tests ) ;

-- positionOf
equalityTest ( positionOf ( equalsInteger, 1, []), -1, Integer, core_tests) ;
equalityTest ( positionOf ( equalsInteger, 1, [1,2]), 0, Integer, core_tests) ;
equalityTest ( positionOf ( equalsInteger, 3, [0,1,2,3]), 3, Integer, core_tests) ;

-- repeat TODO
-- zipWith TODO
-- reverse TODO

-- sortBy
equalityTest ( null(sortBy(stringLte, [])), true,
               Boolean, core_tests ) ;
equalityTest ( sortBy(stringLte, ["cd", "ca", "b", "z", "a"]), 
                          ["a", "b", "ca", "cd", "z"],
               [String], core_tests ) ;

-- groupBy
equalityTest ( null(groupBy(equalsString, [])), true,
               Boolean, core_tests ) ;
equalityTest ( map(head, groupBy(equalsInteger, [1, 1, 2, 1, 2, 2])),     [1,2,1,2],
               [Integer], core_tests ) ;
equalityTest ( head(groupBy(equalsInteger, [1, 1, 2, 1, 2, 2])),       [1,1],
               [Integer], core_tests ) ;
equalityTest ( map(head,groupBy(equalsInteger, [1, 2, 1, 3])),        [1,2,1,3],
               [Integer], core_tests ) ;
equalityTest ( head(tail(groupBy(equalsInteger, [1, 1, 2, 1, 2, 2]))),       [2],
               [Integer], core_tests ) ;
equalityTest ( map(listLength, groupBy(equalsInteger, [1, 1, 2, 1, 2, 2])),      [2,1,1,2],
               [Integer], core_tests ) ;

-- intersperse
equalityTest ( intersperse(1, [2,3,4,5]), [2,1,3,1,4,1,5], [Integer], core_tests ) ;
equalityTest ( intersperse(1, [2]), [2], [Integer], core_tests ) ;
equalityTest ( intersperse(1, []), [], [Integer], core_tests ) ;

-- set operations
equalityTest ( unionBy (equalsInteger, [], []), [],
               [Integer], core_tests ) ;
equalityTest ( unionBy (equalsInteger, [1,2,3], []), [1,2,3],
               [Integer], core_tests ) ;
equalityTest ( unionBy (equalsInteger, [], [1,2,3]), [1,2,3],
               [Integer], core_tests ) ;
equalityTest ( unionBy (equalsInteger, [1,2,3], [4,5,6]), [1,2,3,4,5,6],
               [Integer], core_tests ) ;

equalityTest ( unionBy (equalsInteger, [1,2,3], [1,4,5,6]), [2,3,1,4,5,6],
               [Integer], core_tests ) ;
equalityTest ( unionBy (equalsInteger, [1,2,3], [3,4,5,6,1]), [2,3,4,5,6,1],
               [Integer], core_tests ) ;

equalityTest ( intersectBy (equalsInteger, [], []), [],
               [Integer], core_tests ) ;
equalityTest ( intersectBy (equalsInteger, [1,2,3], []), [],
               [Integer], core_tests ) ;
equalityTest ( intersectBy (equalsInteger, [], [1,2,3]), [],
               [Integer], core_tests ) ;
equalityTest ( intersectBy (equalsInteger, [1,2,3], [4,5,6]), [],
               [Integer], core_tests ) ;

equalityTest ( intersectBy (equalsInteger, [1,2,3], [4,2,6]), [2],
               [Integer], core_tests ) ;
equalityTest ( intersectBy (equalsInteger, [1,2,3], [4,2,3,6]), [2,3],
               [Integer], core_tests ) ;

equalityTest ( unionsBy (equalsInteger, [ [1,2], [2,3], [1,4,5,6] ] ), [1,2,3,4,5,6] ,
                [Integer], core_tests ) ;



---- from lib:extcore!
equalityTest ( equalsList ( equalsInteger, [], []), true, Boolean, core_tests) ;
equalityTest ( equalsList ( equalsInteger, [1], [1]), true, Boolean, core_tests) ;
equalityTest ( equalsList ( equalsInteger, [1,2], [1,2]), true, Boolean, core_tests) ;
equalityTest ( equalsList ( equalsInteger, [1], [2]), false, Boolean, core_tests) ;
equalityTest ( equalsList ( equalsInteger, [1], [1,2]), false, Boolean, core_tests) ;
equalityTest ( equalsList ( equalsInteger, [1,2], []), false, Boolean, core_tests) ;

equalityTest ( notEqualsList ( notEqualsInteger, [], []), false, Boolean, core_tests) ;
equalityTest ( notEqualsList ( notEqualsInteger, [1], [1]), false, Boolean, core_tests) ;
equalityTest ( notEqualsList ( notEqualsInteger, [1,2], [1,2]), false, Boolean, core_tests) ;
equalityTest ( notEqualsList ( notEqualsInteger, [1], [2]), true, Boolean, core_tests) ;
equalityTest ( notEqualsList ( notEqualsInteger, [1], [1,2]), true, Boolean, core_tests) ;
equalityTest ( notEqualsList ( notEqualsInteger, [1,2], []), true, Boolean, core_tests) ;

