grammar silver:translation:java:type:io;
import silver:translation:java:type;
import silver:definition:type;
import silver:definition:type:io;

aspect production ioTypeExp
top::TypeExp ::= 
{
  -- TODO: We might want to make a separate type for this someday, just to catch any translation errors?
  top.transType = "Object";
  top.transClassType = "Object";
}
