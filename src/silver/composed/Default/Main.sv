grammar silver:composed:Default;

--import core hiding parse;

import silver:driver;

import silver:definition:concrete_syntax;
import silver:definition:core;

import silver:util:command;

import silver:definition:env;
import silver:definition:env:parser;

--BINDING CHECKING
import silver:analysis:binding:driver;

--TYPE CHECKING
import silver:analysis:typechecking:driver;
import silver:analysis:typechecking:core;
import silver:analysis:typechecking:concrete_syntax;
import silver:analysis:typechecking:type:io;
import silver:analysis:typechecking:type:anytype;

--JAVA TRANSLATION (still needs importing!)
import silver:translation:java:driver;
import silver:translation:java:core;
import silver:translation:java:env;
import silver:translation:java:concrete_syntax;
import silver:translation:java:type:io;
import silver:translation:java:type:anytype;
import silver:extension:list:java; -- optional!

--EXTENSIONS (that do not add to concrete syntax (those in parser above))

-- not yet used
--import silver:extension:doc;
--import silver:extension:doc:driver;
--import silver:extension:doc:copper;

--MODIFICATIONS translations (syntax goes in parser above)
import silver:modification:let_fix:java;
import silver:modification:collection:java;

--PARSERS
parser rParse::Root {
  silver:definition:core;
  silver:definition:concrete_syntax;
  silver:definition:type:io;
  silver:definition:type:higherorder;
  silver:definition:type:productiontype;
  silver:definition:type:decorated;
  silver:definition:type:anytype;

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
  silver:definition:env:parser;
  silver:definition:core:env_parser;
  silver:definition:concrete_syntax:env_parser;

  silver:modification:collection:env_parser;
  silver:modification:autocopyattr:env_parser;
  silver:extension:list:env_parser;

  silver:translation:java:concrete_syntax:copper:env_parser;
}

function main 
IO ::= args::String i::IO {
  return (decorate run(i, args) with {rParser = rParse; cParser = cParse; iParser = iParse;}).io;
}
