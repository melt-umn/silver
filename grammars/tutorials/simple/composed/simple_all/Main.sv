grammar tutorials:simple:composed:simple_all ;

import tutorials:simple:host ;
import tutorials:simple:host:driver ;

parser parse :: Root_c {
  tutorials:simple:host;
  tutorials:simple:extensions:repeat_until; 
  tutorials:simple:extensions:implication;
} 

function main
IO ::= args::String io_in::IO
{
  return driver(args, io_in, parse) ; 
}

