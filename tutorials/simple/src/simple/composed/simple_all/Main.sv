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
import simple:host;
import simple:concretesyntax as cst;

parser parse :: cst:Root {
  simple:host;
  simple:extensions:do_while;
  simple:extensions:repeat_until; 
  simple:extensions:implication;
  simple:extensions:matrix;
  simple:extensions:expr_if;
  simple:extensions:expr_let;
} 

function main 
IOVal<Integer> ::= largs::[String] io_in::IO
{
  local attribute args :: String;
  args = implode(" ", largs);

  return ioval(driver(args, io_in, parse), 0);
}

