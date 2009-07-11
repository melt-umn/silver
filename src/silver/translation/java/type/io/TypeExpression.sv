grammar silver:translation:java:type:io;
import silver:translation:java:env;
import silver:definition:env;
import silver:definition:type:io;

aspect production i_ioTypeRep
top::TypeRep ::= 
{
  top.transType = "Object";
}
