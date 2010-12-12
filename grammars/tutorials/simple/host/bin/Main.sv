grammar tutorials:simple:host:bin ;

import tutorials:simple:host ;
import tutorials:simple:host:driver ;

parser parse :: Root_c {
  tutorials:simple:concretesyntax;
  tutorials:simple:terminals;
} 

function main 
IOVal<Integer> ::= largs::[String] io_in::IO
{
  local attribute args :: String;
  args = implode(" ", largs);

  return ioval(driver(args, io_in, parse), 0);
}
