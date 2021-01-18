
@@{-
   - 
   - @config title = "Example Docs Page"
   - @config weight 15
   -}

@{-
  -
  - Link to @link[dummyFunction].
  - Link to @link[exFn].
  - Link to @link[badLink]
  -}
function main
IOVal<Integer> ::= args::[String] ioIn::IO
{
  return ioval(ioIn, 0);
}

function dummyFunction
Integer ::=
{
  return 1;
}