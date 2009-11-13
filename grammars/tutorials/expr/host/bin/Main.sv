grammar edu:umn:cs:melt:tutorial:expr:host:bin ;
 export edu:umn:cs:melt:tutorial:expr:host:bin ;

import edu:umn:cs:melt:tutorial:expr:host  ;
syntax edu:umn:cs:melt:tutorial:expr:host ;

import core ; 

abstract production main
top::Main ::= args::String
{ forwards to driver(args,parse,parse); }

