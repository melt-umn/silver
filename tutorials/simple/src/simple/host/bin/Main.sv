grammar simple:host:bin ;

import simple:host ;
import simple:host:driver ;

parser parse :: Root_c {
  simple:concretesyntax;
  simple:terminals;
} 

function main 
IOVal<Integer> ::= largs::[String] io_in::IO
{
  local attribute args :: String;
  args = implode(" ", largs);

  return ioval(driver(args, io_in, parse), 0);
}
