grammar silver:translation:java:type:anytype;
import silver:translation:java:core;
import silver:translation:java:env;
import silver:definition:core;
import silver:definition:env;
import silver:definition:type:anytype;

aspect production i_anyTypeTypeRep
top::TypeRep ::= 
{
  top.transType = "common.AnyType";
}
