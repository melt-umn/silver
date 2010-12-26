grammar silver:composed:Default;

import silver:host;
import silver:host:env;
import silver:translation:java;
import silver:driver;
import silver:util:command;

--import silver:extension:doc;
--import silver:analysis:warnings;

parser rParse::Root {
  silver:host;

  silver:extension:convenience;
  silver:extension:list;
  silver:extension:easyterminal;

  silver:extension:testing;
  -- polymorphism?  polymorphism:functions?
  -- doc?

  silver:modification:let_fix;
  silver:modification:collection;
  silver:modification:patternmatching;
  silver:modification:patternmatching:copper;
  silver:modification:autocopyattr;
  silver:modification:autocopyattr:convenience; -- TODO: we need a condition export, perhaps? Why didn't we include this?
  silver:modification:ffi;
  
  silver:translation:java:concrete_syntax:copper;
}

parser cParse::Command {
  silver:util:command;
}

parser iParse::IRootSpec {
  silver:host:env;

  silver:modification:collection:env_parser;
  silver:modification:autocopyattr:env_parser;
  silver:extension:list:env_parser;

  silver:translation:java:concrete_syntax:copper:env_parser;
}

function main 
IOVal<Integer> ::= largs::[String] i::IO
{
  local attribute args :: String;
  args = implode(" ", largs);

  -- please note that run in BuildProcess.sv will call exit(), so we may not "get back here"
  return ioval((decorate run(i, args) with {rParser = rParse; cParser = cParse; iParser = iParse;}).io,
               0);
}
