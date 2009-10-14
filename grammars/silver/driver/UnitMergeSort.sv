grammar silver:driver;

function unitMergeSort
[Unit] ::= c1::[Unit]
{
  return unitMergeSortHelp(mapUnitsList(c1));
}

function unitMergeSortHelp
[Unit] ::= c1::[[Unit]]
{
  return if null(c1) then []
         else if null(tail(c1)) then head(c1)
         else unitMergeSortHelp(unitMergePairs(c1));
}

function unitMergePairs
[[Unit]] ::= c1::[[Unit]]
{
  return if null(c1) then []
         else if null(tail(c1)) then c1
         else cons(mergeUnitsList(head(c1), head(tail(c1))), unitMergePairs(tail(tail(c1))));
}

function mergeUnitsList
[Unit] ::= tds1::[Unit] tds2::[Unit]
{
  local attribute h1 :: Unit;
  h1 = head(tds1);

  local attribute h2 :: Unit;
  h2 = head(tds2);

  return if null(tds2)
         then tds1
         else if null(tds1)
         then tds2
         else if  h1.order  >  h2.order
              then cons( head(tds2) , merge_tds1_tds2_tail )
              else cons( head(tds1) , merge_tds1_tail_tds2 ) ;

  local attribute merge_tds1_tds2_tail :: [Unit] ;
  merge_tds1_tds2_tail = mergeUnitsList( tds1, tail(tds2) ) ;

  local attribute merge_tds1_tail_tds2 :: [Unit] ;
  merge_tds1_tail_tds2 = mergeUnitsList( tail(tds1), tds2 ) ;

}

function mapUnitsList
[[Unit]] ::= tds1::[Unit]
{
  return if null(tds1)
         then []
         else cons([head(tds1)], mapUnitsList(tail(tds1)));
}
