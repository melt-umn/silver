grammar edu:umn:cs:melt:tutorial:expr:exts:ctrans ;

import edu:umn:cs:melt:tutorial:expr:host ;

synthesized attribute printf_format :: String
  occurs on TypeRep ;

aspect production intType
tr::TypeRep ::= 
{
 tr.printf_format = "%d" ;
}

aspect production booleanType
tr::TypeRep ::= 
{
 tr.printf_format = "%d" ;
}

