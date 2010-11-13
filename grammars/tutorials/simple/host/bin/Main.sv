grammar tutorials:simple:host:bin ;

import tutorials:simple:host ;
--import tutorials:simple:concretesyntax ;
--import tutorials:simple:abstractsyntax ;


function main
IO ::= args::String io_in::IO
{
 return driver(args, io_in, parse) ; 
}
