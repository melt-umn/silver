grammar silver:langutil:pp;

import silver:reflect;
import silver:langutil:reflect;

@{--
 - hackUnparse takes any value, and produce a string that represents
 - that value.  It is mainly useful for debugging, to display the structure of
 - an abstract syntax term rather than the pretty-printed represention provided
 - by Show/.pp 
 -}
function hackUnparse
String ::= nt::a
{
  return show(80, reflect(nt));
}

@{--
 - Print a stringification of a value when it is demanded by the Silver runtime.
 - When this gets executed may be unpredictable.
 -
 - @param val  The value to evaluate to, printed when evaluated.
 - @return  val, unchanged.
 - @warning see @link[unsafeIO]
 -}
function unsafeTraceDump
a ::= val::a
{
  return unsafeTrace(val, printlnT(hackUnparse(val), unsafeIO()));
}
