grammar silver:extension:doc:extra;

{- This grammar is just a bunch of imports so that we generated docmentation for things that are not imported into the regular silver build. You should build this grammar with the --doc and --clean flags. -}

import lib:monto;
import lib:errors;
import lib:langproc;
import lib:system;
import lib:xml;

import silver:extension;
import silver:extension:doc;
import silver:util;
import silver:langutil;
import silver:composed;
import silver:modification;
import silver:translation:java;
import silver:definition;

{- Dummy main function that does nothing -}
function main
IOVal<Integer> ::= args::[String] ioIn::IO
{
  return ioval(ioIn, 0);
}
