grammar simple:host;

{- Export the four grammars that make up the 'host' language.  This
   allows extension to see the host langauge as one grammar and not
   all the component grammars from which it is composed.
-}
exports simple:terminals;
exports simple:concretesyntax;
exports simple:abstractsyntax;
exports simple:host:driver;


{- We import the concrete syntax and driver grammars in order to build
a parser and specify the main function that specifies the compilation
process.

This parse and version of main are exported, but this causes no
problems in the compsed languages.
-}

import simple:concretesyntax only Root;
import simple:host:driver;

parser parse :: Root {
  simple:concretesyntax;
  simple:terminals;
} 

function main 
IOVal<Integer> ::= largs::[String] io_in::IO
{
  local attribute args :: String;
  args = implode(" ", largs);

  return ioval(driver(args, io_in, parse), 0);
}

