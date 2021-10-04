grammar hello;

function main 
IOVal<Integer> ::= largs::[String] ioin::IO
{
  return ioval(printT(" World!\n",
                 printT("Hello", ioin)),
               0);
}
