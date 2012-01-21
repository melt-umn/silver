grammar lambda ;

{- Borrowed from Silver's implementation of Simple -}

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

{- Here, we could include additional modules to be parsed. Only "lambda"
 - is shown because all the necessary .sv files are located within the
 - same directory.
 -}
parser hostParse :: Root_c {
  lambda;
} 

function main 
IOVal<Integer> ::= largs::[String] io_in::IO
{
  return driver(largs, hostParse, io_in) ;
}
