grammar tutorials:expr:host:bin ;

import tutorials:expr:concretesyntax ;
import tutorials:expr:driver ;

parser parse :: Root_c
{
  tutorials:expr:concretesyntax ;
  tutorials:expr:terminals ;
}



function main
IO ::= args::String ioIn::IO
{ 
 return  driver(args,parse, ioIn); 
}

