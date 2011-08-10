grammar simple:composed:simple_repeat_until;

{- Export the host language and this extension to define the composed
 language.  This allows extensions to build on top of this composed
 language.  
-}
exports simple:host;
exports simple:extensions:repeat_until;


{- Below we import the host grammar so that it may be used in
   defining a parser and main function for this language.
-}
import simple:host;
import simple:concretesyntax as cst;

parser parse :: cst:Root {
  simple:host;
  simple:extensions:repeat_until;
} 

function main 
IOVal<Integer> ::= largs::[String] io_in::IO
{
  local attribute args :: String;
  args = implode(" ", largs);

  return ioval(driver(args, io_in, parse), 0);
}

