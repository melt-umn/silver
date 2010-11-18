grammar tutorials:simple:host:bin ;

import tutorials:simple:host ;
import tutorials:simple:host:driver ;

parser parse :: Root_c {
  tutorials:simple:concretesyntax;
  tutorials:simple:terminals;
} 

function main
IO ::= args::String io_in::IO
{
 return driver(args, io_in, parse) ; 
}
