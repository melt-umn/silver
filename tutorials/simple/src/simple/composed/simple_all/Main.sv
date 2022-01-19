grammar simple:composed:simple_all;

{- Export the host language and the two extensions to define the
   composed language.  This allows extensions to build on top of this
   composed language.
-}
exports simple:host;
exports simple:extensions:implication;
exports simple:extensions:repeat_until; 


{- Below we import the host grammar so that it may be used in
   defining a parser and main function for this language.
-}
import simple:concretesyntax as cst;

parser parse :: cst:Root {
  simple:host;
  simple:extensions:do_while;
  simple:extensions:repeat_until; 
  simple:extensions:implication;
} 

function main 
IOVal<Integer> ::= args::[String] io_in::IOToken
{
  return ioval(driver(args, io_in, parse), 0);
}


