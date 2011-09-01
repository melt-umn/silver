
import silver:testing;
import lib:extcore;
import silver:util:raw:treeset as ts;
import stdlib;

function integerCompare
Integer ::= l::Integer r::Integer
{
  return l - r;
}

equalityTest ( ts:toList(ts:empty(integerCompare)), [], [Integer], core_tests ) ;

global set1 :: ts:Set<Integer> = ts:add([8,2,4,1,1,0], ts:empty(integerCompare));

equalityTest ( ts:toList(set1), [0,1,2,4,8], [Integer], core_tests ) ;

global set2 :: ts:Set<Integer> = ts:add([-1, 4, 7, 11, 4], ts:empty(integerCompare));

equalityTest ( ts:toList(set2), [-1,4,7,11], [Integer], core_tests ) ;

equalityTest ( ts:toList(ts:union(set1,set2)), [-1,0,1,2,4,7,8,11], [Integer], core_tests ) ;

equalityTest ( ts:toList(ts:intersect(set1,set2)), [4], [Integer], core_tests ) ;

equalityTest ( ts:toList(ts:difference(set1,set2)), [0,1,2,8], [Integer], core_tests ) ;
equalityTest ( ts:toList(ts:difference(set2,set1)), [-1,7,11], [Integer], core_tests ) ;

equalityTest ( ts:contains(4, set2), true, Boolean, core_tests ) ;
equalityTest ( ts:contains(5, set2), false, Boolean, core_tests ) ;

equalityTest ( ts:containsAll([4,11], set2), true, Boolean, core_tests ) ;
equalityTest ( ts:containsAll([4,5], set2), false, Boolean, core_tests ) ;

equalityTest ( ts:subset(set1, set2), false, Boolean, core_tests ) ;
equalityTest ( ts:subset(set1, ts:union(set1,set2)), true, Boolean, core_tests ) ;
equalityTest ( ts:subset(set2, ts:union(set1,set2)), true, Boolean, core_tests ) ;

equalityTest ( ts:equals(set1, set2), false, Boolean, core_tests ) ;
equalityTest ( ts:equals(set1, ts:union(set1,set2)), false, Boolean, core_tests ) ;
equalityTest ( ts:equals(set1, set1), true, Boolean, core_tests ) ;

