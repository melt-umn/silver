grammar hello;

function main 
IOVal<Integer> ::= largs::[String] ioin::IOToken
{
  return ioval(printT(" World!\n",
                 printT("Hello", ioin)),
               0);
}
