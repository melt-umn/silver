grammar tutorials:simple:composed:simple_repeat_until ;

import tutorials:simple:host ;
import tutorials:simple:host:driver ;

parser parse :: Root_c {
  tutorials:simple:host;
  tutorials:simple:extensions:repeat_until;
} 

function main 
IOVal<Integer> ::= largs::[String] io_in::IO
{
  local attribute args :: String;
  args = implode(" ", largs);

  return ioval(driver(args, io_in, parse), 0);
}

