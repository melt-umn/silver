grammar silver:composed:idetest;

-- This grammar is a duplicate of Default, but...

import silver:host;
import silver:host:env;
import silver:translation:java;
import silver:driver;

--import silver:extension:doc;
import silver:analysis:warnings:defs;
import silver:analysis:warnings:exporting;

--hack for now
import silver:modification:impide;

parser svParse::Root {
  silver:host;

  silver:extension:convenience;

  silver:extension:list;
  silver:extension:easyterminal;

  silver:extension:deprecation;
  silver:extension:testing;
--  silver:extension:concreteSyntaxForTrees ;
  -- doc?

  silver:modification:let_fix;
  silver:modification:collection;
  silver:modification:patternmatching;
  silver:modification:autocopyattr;
  silver:modification:ffi;
  silver:modification:typedecl;
  silver:modification:copper;
  silver:modification:defaultattr;
  
  -- slight hacks, for the moment
  silver:modification:copper_mda;
  silver:modification:impide;
}

parser sviParse::IRoot {
  silver:host:env;

  silver:modification:collection:env_parser;
  silver:modification:autocopyattr:env_parser;
  silver:modification:ffi:env_parser;
  silver:modification:typedecl:env_parser;
  silver:modification:copper:env_parser;
  
  silver:extension:list:env_parser;
}

function main 
IOVal<Integer> ::= args::[String] ioin::IO
{
  return cmdLineRun(args, svParse, sviParse, ioin);
}

--- ... we're adding an ide declaration here

temp_imp_ide_dcl svParse ".sv" ;

{-
Hi Ming:

run got restructured. You can see the invocation above for an example.
It now properly returns an IOVal instead of calling exit() when an error
happens.

It's probably not suitable for you currently, but I'm fixing that...

function getErrors 
[String] ::= args::[String] i::IO
{

  local attribute ru :: Decorated RunUnit;
  ru = decorate run(i, args) with {svParser = svParse; sviParser = sviParse;};

  return ru.errorList;
}
-}
-- Yeah, that's a hack! :D

