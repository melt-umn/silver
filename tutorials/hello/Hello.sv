grammar hello;

{-
function main 
IOVal<Integer> ::= largs::[String] ioin::IOToken
{
  return ioval(printT(" World!\n",
                 printT("Hello", ioin)),
               0);
}
-}

{-
function main 
IOVal<Integer> ::= largs::[String] ioin::IOToken
{
  return evalIO( do {
    print("Hello, world!\n");
    return 0;
  }, ioin);
}
-}


function main 
IO<Integer> ::= largs::[String]
{
  return do {
    print("Hello, world!\n");
    return 0;
  };
}