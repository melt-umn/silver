grammar tutorials:xrobots:host:bin ;

import tutorials:xrobots:host ;
import tutorials:xrobots:host:driver ;
import tutorials:xrobots:concretesyntax;

parser parse :: Root_c {
  tutorials:xrobots:concretesyntax;
  tutorials:xrobots:terminals;
} 

function main
IO ::= args::String io_in::IO
{
 return driver(args, io_in, parse) ; 
}
