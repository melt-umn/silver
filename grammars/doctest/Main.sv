@{- Standalone Comment -}

@{--
  - Dummy main function
  -
  - Here is a link: @link[a] 
  -}
function main
IOVal<Integer> ::= args::[String] ioIn::IO
{
  return ioval(ioIn, 0);
}

@{--
  - Another function
  -}
function dummyFunction
Integer ::=
{
  return 1;
}