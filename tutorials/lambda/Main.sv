grammar lambda ;

{- Borrowed from silver's implementation of simple -}

{- Export the four grammars that make up the 'host' language.  This
   allows extension to see the host langauge as one grammar and not
   all the component grammars from which it is composed.
-}

{- We import the concrete syntax and driver grammars in order to build
a parser and specify the main function that specifies the compilation
process.

This parse and version of main are exported, but this causes no
problems in the compsed languages.
-}

import lambda only Program_c, Driver;

parser hostParse :: Program_c {
  lambda:concretesyntax;
  lambda:terminals;
} 

function main 
IOVal<Integer> ::= largs::[String] io_in::IO
{
  return driver(largs, hostParse, io_in) ;
}
