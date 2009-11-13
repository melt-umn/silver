grammar edu:umn:cs:melt:tutorial:expr:host_op_overloading:bin ;
 export edu:umn:cs:melt:tutorial:expr:host_op_overloading:bin ;

import edu:umn:cs:melt:tutorial:expr:host_op_overloading ;
syntax edu:umn:cs:melt:tutorial:expr:host_op_overloading ;

import core ; 

abstract production main
top::Main ::= args::String
{ forwards to driver(args,parse,parse); }

