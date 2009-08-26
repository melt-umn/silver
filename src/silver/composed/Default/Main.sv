grammar silver:composed:Default;

import silver:host;
import silver:host:env;
import silver:translation:java;
import silver:driver;
import silver:util:command;

import silver:extension:list:java;
import silver:modification:let_fix:java;
import silver:modification:collection:java;
import silver:modification:autocopyattr:java;

--EXTENSIONS (that do not add to concrete syntax (those in parser above))
-- not yet used
--import silver:extension:doc;
--import silver:extension:doc:driver;
--import silver:extension:doc:copper;

parser rParse::Root {
  silver:host;

  silver:extension:convenience;
  silver:extension:list;
  silver:extension:easyterminal;
  -- polymorphism?  polymorphism:functions?

  silver:modification:let_fix;
  silver:modification:collection;
  silver:modification:patternmatching;
  silver:modification:patternmatching:copper;
  silver:modification:autocopyattr;
  
  silver:translation:java:concrete_syntax:copper;
}

parser cParse::Command {
  silver:util:command;
}

parser iParse::aRootSpec {
  silver:host:env;

  silver:modification:collection:env_parser;
  silver:modification:autocopyattr:env_parser;
  silver:extension:list:env_parser;

  silver:translation:java:concrete_syntax:copper:env_parser;
}

function main 
IO ::= args::String i::IO {
  return (decorate run(i, args) with {rParser = rParse; cParser = cParse; iParser = iParse;}).io;
}
