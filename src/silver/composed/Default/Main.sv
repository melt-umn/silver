grammar silver:composed:Default;

import core hiding parse;
import silver:driver hiding parse;

import silver:definition:concrete_syntax;
import silver:definition:core;

import silver:util:command;

import silver:definition:env;
import silver:definition:env:parser;

--The command line and interface parsers to use.
--import silver:composed:default:parser:command with parse as cParse;
--import silver:composed:default:parser:interface with parse as iParse;

import silver:util:command;
import silver:definition:core;
import silver:definition:env:parser;

--CORE
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
  silver:extension:autocopyattr;
  silver:extension:easyterminal;
  -- polymorphism?  polymorphism:functions?

  silver:modification:let_fix;
  silver:modification:collection;
  silver:modification:patternmatching;
  silver:modification:patternmatching:copper;
  
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
  silver:extension:list:env_parser;

  silver:translation:java:concrete_syntax:copper:env_parser;
}

--BINDING CHECKING
--import silver:analysis:binding:driver;

--TYPE CHECKING
--import silver:analysis:typechecking:driver;
--import silver:analysis:typechecking:core;
--import silver:analysis:typechecking:concrete_syntax;
--import silver:analysis:typechecking:type:io;
--import silver:analysis:typechecking:type:anytype;

--JAVA TRANSLATION
--import silver:translation:java:driver;
--import silver:translation:java:core;
--import silver:translation:java:env;
--import silver:translation:java:concrete_syntax;
--import silver:translation:java:type:io;
--import silver:translation:java:type:anytype;

--JAVA PARSER GENERATOR
--syntax silver:translation:java:concrete_syntax:copper;

--EXTENSIONS
--syntax silver:extension:convenience;
--syntax silver:extension:list;
--syntax silver:extension:autocopy;
--import silver:extension:doc;
--import silver:extension:doc:driver;
--import silver:extension:doc:copper;
--syntax silver:extension:easyterminal;

--MODIFICATIONS
--syntax silver:modification:let_fix;
--import silver:modification:let_fix:java;
--
--syntax silver:modification:collection;
--import silver:modification:collection:java;
--
--syntax silver:modification:patternmatching;
--import silver:modification:patternmatching;

function main 
IO ::= args::String i::IO {
  return (decorate run(i, args) with {rParser = rParse; cParser = cParse; iParser = iParse;}).io;
}
