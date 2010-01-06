grammar silver:extension:doc;


function docMergeSort
[Decorated Doc] ::= c1::[Decorated Doc]
{
  return docMergeSortHelp(mapDocsList(c1));
}

function docMergeSortHelp
[Decorated Doc] ::= c1::[[Decorated Doc]]
{
  return if null(c1) then []
         else if null(tail(c1)) then head(c1)
         else docMergeSortHelp(docMergePairs(c1));
}

function docMergePairs
[[Decorated Doc]] ::= c1::[[Decorated Doc]]
{
  return if null(c1) then []
         else if null(tail(c1)) then c1
         else cons(mergeDocsList(head(c1), head(tail(c1))), docMergePairs(tail(tail(c1))));
}

function mergeDocsList
[Decorated Doc] ::= tds1::[Decorated Doc] tds2::[Decorated Doc]
{
  local attribute h1 :: Decorated Doc;
  h1 = head(tds1);

  local attribute h2 :: Decorated Doc;
  h2 = head(tds2);

  return if null(tds2)
         then tds1
         else if null(tds1)
         then tds2
         else if  h1.docOrder  >  h2.docOrder
              then cons( head(tds2) , merge_tds1_tds2_tail )
              else cons( head(tds1) , merge_tds1_tail_tds2 ) ;

  local attribute merge_tds1_tds2_tail :: [Decorated Doc] ;
  merge_tds1_tds2_tail = mergeDocsList( tds1, tail(tds2) ) ;

  local attribute merge_tds1_tail_tds2 :: [Decorated Doc] ;
  merge_tds1_tail_tds2 = mergeDocsList( tail(tds1), tds2 ) ;

}

function mapDocsList
[[Decorated Doc]] ::= tds1::[Decorated Doc]
{
  return if null(tds1)
         then []
         else cons([head(tds1)], mapDocsList(tail(tds1)));
}
