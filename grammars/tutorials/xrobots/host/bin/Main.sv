grammar xrobots:host:bin ;

import xrobots:host ;
import xrobots:host:driver ;
import xrobots:concretesyntax;

parser parse :: Root_c {
  xrobots:concretesyntax;
  xrobots:terminals;
} 

function main
IO ::= args::String io_in::IO
{
 return driver(args, io_in, parse) ; 
}
