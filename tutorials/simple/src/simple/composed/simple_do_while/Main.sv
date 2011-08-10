grammar simple:composed:simple_do_while;

{- This language is composed of the simple_repeat_until language,
   itself a composed language, and the do_while extension.  Thus, we
   export both of these to form a new composed language.  This allows
   extensions to build on top of this composed language.  
-}
exports simple:composed:simple_repeat_until;
exports simple:extensions:do_while;


{- Below we import the 'host' langauge - which in this case is the
   language that included the repeat-until extennsion.  We do this so
   that it may be used in defining a parser and main function for this
   language.  
-}

import simple:composed:simple_repeat_until;
import simple:concretesyntax as cst;

parser parse :: cst:Root {
 simple:composed:simple_repeat_until;
 simple:extensions:do_while;
} 

function main 
IOVal<Integer> ::= largs::[String] io_in::IO
{
  local attribute args :: String;
  args = implode(" ", largs);

  return ioval(driver(args, io_in, parse), 0);
}

