grammar tutorials:simple:composed:simple_implication ;

import tutorials:simple:host ;
import tutorials:simple:host:driver ;

parser parse :: Root_c {
  tutorials:simple:host;
  tutorials:simple:extensions:implication;
} 

function main 
IOVal<Integer> ::= largs::[String] io_in::IO
{
  local attribute args :: String;
  args = implode(" ", largs);

  return ioval(driver(args, io_in, parse), 0);
}

