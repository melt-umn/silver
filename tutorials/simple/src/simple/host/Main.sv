grammar simple:host;

{- Export the four grammars that make up the 'host' language.  This
   allows extension to see the host langauge as one grammar and not
   all the component grammars from which it is composed.
-}
exports simple:terminals;
exports simple:concretesyntax;
exports simple:abstractsyntax;
exports simple:host:driver;


{- We use the concrete syntax and driver grammars in order to build
   a parser and specify the main function that specifies the compilation
   process.

   This parse and version of main are exported, but this causes no
   problems in the compsed languages.
-}

parser parse :: simple:concretesyntax:Root {
  simple:concretesyntax;
  simple:terminals;
} 

function main 
IOVal<Integer> ::= args::[String] io_in::IO
{
  return ioval(driver(args, io_in, parse), 0);
}

